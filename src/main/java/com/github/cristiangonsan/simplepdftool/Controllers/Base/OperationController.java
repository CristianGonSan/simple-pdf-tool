package com.github.cristiangonsan.simplepdftool.Controllers.Base;

import com.github.cristiangonsan.simplepdftool.Controllers.ProcessState;
import com.github.cristiangonsan.simplepdftool.Listeners.StepListener;
import com.github.cristiangonsan.simplepdftool.Localization.Lang;
import com.github.cristiangonsan.simplepdftool.Utils.FileSupport;
import com.github.cristiangonsan.simplepdftool.View.MainView;

import javax.swing.*;
import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.function.IntFunction;

/**
 * Base de todas las operaciones. Cada subclase controla su parte de la UI.
 */
public abstract class OperationController {

    @FunctionalInterface
    protected interface Task {
        void run() throws Exception;
    }

    protected final MainView mainView;
    protected final JFrame frame;
    protected final JTextArea console;
    protected final JProgressBar progressBar;
    protected final ProcessState processState;
    protected final String desktopPath = FileSupport.getDesktopPath();

    private volatile boolean errorReported;

    protected OperationController(MainView mainView, ProcessState processState) {
        this.mainView = mainView;
        this.frame = mainView.getFrame();
        this.console = mainView.getConsole();
        this.progressBar = mainView.getProgressBar();
        this.processState = processState;
    }

    // ---------------------------------------------------------------
    // Contrato
    // ---------------------------------------------------------------

    public abstract void prepareUi();

    public abstract void releaseUi();

    public abstract void execute();

    public void chooseInputFile() {
    }

    public void chooseInputFolder() {
    }

    public void handleDrop(List<File> files) {
    }

    public void clear() {
        releaseUi();
        prepareUi();
    }

    // ---------------------------------------------------------------
    // Helpers de UI
    // ---------------------------------------------------------------

    protected void beginPrepare(JButton ownButton) {
        mainView.setEnabledTopButtons(true);
        mainView.setEnabledSideButtons(false);

        console.requestFocusInWindow();
        ownButton.setEnabled(false);
    }

    protected void enableSaveAndClear() {
        mainView.getSaveButton().setEnabled(true);
        mainView.getClearButton().setEnabled(true);
    }

    protected void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    protected int readInt(JTextField field, int def) {
        String text = field.getText().trim();
        if (text.isEmpty()) return def;
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return def;
        }
    }

    // ---------------------------------------------------------------
    // Helpers de JFileChooser
    // ---------------------------------------------------------------

    protected File showChooser(JFileChooser chooser) {
        return chooser.showDialog(frame, null) == JFileChooser.APPROVE_OPTION
                ? chooser.getSelectedFile()
                : null;
    }

    protected File chooseOutputPdf(JFileChooser chooser, String defaultName) {
        chooser.setSelectedFile(new File(desktopPath, defaultName));

        File file = showChooser(chooser);
        if (file == null) return null;

        if (!FileSupport.isValidPdfExtension(file)) {
            showMessage(tr("common.invalid_file"));
            return null;
        }

        if (file.exists()) {
            int option = JOptionPane.showConfirmDialog(frame,
                    tr("dialog.overwrite.message"),
                    tr("dialog.overwrite.title"), JOptionPane.YES_NO_OPTION);
            if (option != JOptionPane.YES_OPTION) return null;
        }
        return file;
    }

    protected File chooseOutputFolder(JFileChooser chooser, String folderName) {
        if (folderName.toLowerCase().endsWith(".pdf")) {
            folderName = folderName.substring(0, folderName.length() - 4);
        }

        chooser.setSelectedFile(new File(desktopPath));
        File base = showChooser(chooser);
        if (base == null) return null;

        File newFolder = new File(base, folderName.trim());
        if (!newFolder.exists() && !newFolder.mkdirs()) {
            showMessage(tr("common.folder_creation_error", newFolder.getAbsolutePath()));
            return null;
        }
        return newFolder;
    }

    // ---------------------------------------------------------------
    // Ejecución en segundo plano
    // ---------------------------------------------------------------

    protected void runAsync(StepListener listener, Task task) {
        if (!processState.tryStart()) {
            showMessage(tr("common.process_in_progress"));
            return;
        }
        errorReported = false;

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                task.run();
                return null;
            }

            @Override
            protected void done() {
                try {
                    get();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (ExecutionException e) {
                    if (!errorReported) {
                        Throwable cause = e.getCause();
                        listener.onError(cause instanceof Exception ex ? ex : e);
                    }
                } finally {
                    processState.end();
                }
            }
        }.execute();
    }

    protected StepListener createListener(IntFunction<String> startText, String savingText, String errorTitle) {
        return new StepListener() {
            @Override
            public void onStart(int totalSteps) {
                SwingUtilities.invokeLater(() -> {
                    progressBar.setMaximum(totalSteps);
                    progressBar.setValue(0);
                    console.append(startText.apply(totalSteps));
                });
            }

            @Override
            public void onProgress(int currentStep, int totalSteps) {
                SwingUtilities.invokeLater(() -> progressBar.setValue(currentStep));
            }

            @Override
            public void onSaving(File output) {
                SwingUtilities.invokeLater(() ->
                        console.append("\n> " + savingText + output.getAbsolutePath() + "\n"));
            }

            @Override
            public void onFinalized() {
                SwingUtilities.invokeLater(() -> {
                    progressBar.setValue(progressBar.getMaximum());
                    console.append("> " + tr("process.finished") + "\n");
                });
            }

            @Override
            public void onError(Exception e) {
                errorReported = true;
                SwingUtilities.invokeLater(() -> {
                    console.append("> " + tr("process.error", e.getMessage()) + "\n");
                    JOptionPane.showMessageDialog(frame, errorTitle + "\n" + e.getMessage(),
                            tr("dialog.error.title"), JOptionPane.ERROR_MESSAGE);
                });
            }
        };
    }

    protected String tr(String key, Object... args) {
        return Lang.translate(key, args);
    }
}