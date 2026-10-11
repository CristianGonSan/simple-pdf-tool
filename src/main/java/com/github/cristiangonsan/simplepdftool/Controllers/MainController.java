package com.github.cristiangonsan.simplepdftool.Controllers;

import com.github.cristiangonsan.simplepdftool.Controllers.Base.OperationController;
import com.github.cristiangonsan.simplepdftool.Controllers.Operations.*;
import com.github.cristiangonsan.simplepdftool.Localization.Lang;
import com.github.cristiangonsan.simplepdftool.Utils.AppInfo;
import com.github.cristiangonsan.simplepdftool.View.MainView;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

public class MainController {

    private final MainView mainView;
    private final JFrame frame;
    private final JTextArea console;

    private final ProcessState processState = new ProcessState();

    /** Operación activa; null solo al iniciar. */
    private OperationController current;

    public MainController() {
        mainView = new MainView();
        frame = mainView.getFrame();
        console = mainView.getConsole();

        console.setText(tr("main.presentation", AppInfo.VERSION));
        console.setDropTarget(createDropTarget());

        initOperations();
        initSideButtons();
        initWindowListener();

        frame.setVisible(true);
    }

    // ---------------------------------------------------------------
    // Inicialización
    // ---------------------------------------------------------------

    private void initOperations() {
        bind(mainView.getImagesToPdfButton(), new ImagesToPdfController(mainView, processState));
        bind(mainView.getPdfToImagesButton(), new PdfToImagesController(mainView, processState));
        bind(mainView.getMergePdfsButton(), new MergePdfsController(mainView, processState));
        bind(mainView.getSplitPdfButton(), new SplitPdfController(mainView, processState));
        bind(mainView.getCreditsButton(), new CreditsController(mainView, processState));
    }

    private void initSideButtons() {
        mainView.setEnabledSideButtons(false);

        mainView.getAddFileButton().addActionListener(e -> withCurrent(OperationController::chooseInputFile));
        mainView.getAddFolderButton().addActionListener(e -> withCurrent(OperationController::chooseInputFolder));
        mainView.getClearButton().addActionListener(e -> withCurrent(OperationController::clear));
        mainView.getSaveButton().addActionListener(e -> withCurrent(OperationController::execute));
    }

    private void initWindowListener() {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (processState.isProcessing()) {
                    int option = JOptionPane.showConfirmDialog(
                            frame,
                            tr("main.window.closing.message"),
                            tr("main.window.closing.title"),
                            JOptionPane.YES_NO_OPTION
                    );
                    if (option != JOptionPane.YES_OPTION) return;
                }
                frame.dispose();
            }
        });
    }

    // ---------------------------------------------------------------
    // Control de la operación actual
    // ---------------------------------------------------------------

    private void bind(JButton button, OperationController controller) {
        button.addActionListener(e -> select(controller));
    }

    private void select(OperationController next) {
        if (checkProcessing()) return;

        if (current != null) current.releaseUi();
        current = next;
        current.prepareUi();
    }

    /** Ejecuta la acción sobre la operación actual si no hay un proceso activo. */
    private void withCurrent(Consumer<OperationController> action) {
        if (checkProcessing() || current == null) return;
        action.accept(current);
    }

    private boolean checkProcessing() {
        boolean processing = processState.isProcessing();
        if (processing) {
            JOptionPane.showMessageDialog(frame, tr("common.process_in_progress"));
        }
        return processing;
    }

    // ---------------------------------------------------------------
    // Drag & drop
    // ---------------------------------------------------------------

    private DropTarget createDropTarget() {
        return new DropTarget() {
            @Override
            public void drop(DropTargetDropEvent e) {
                if (checkProcessing()) {
                    e.rejectDrop();
                    return;
                }
                if (current == null) {
                    e.rejectDrop();
                    JOptionPane.showMessageDialog(frame, tr("credits.select_operation_first"));
                    return;
                }
                if (!e.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                    e.rejectDrop();
                    return;
                }

                e.acceptDrop(DnDConstants.ACTION_COPY);
                boolean success = false;
                try {
                    @SuppressWarnings("unchecked")
                    List<File> files = (List<File>) e.getTransferable()
                            .getTransferData(DataFlavor.javaFileListFlavor);

                    current.handleDrop(files);
                    success = true;
                } catch (UnsupportedFlavorException | IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame,
                            tr("main.drop.error", ex.getMessage()),
                            tr("dialog.error.title"), JOptionPane.ERROR_MESSAGE);
                } finally {
                    e.dropComplete(success);
                }
            }
        };
    }

    private String tr(String key, Object... args) {
        return Lang.translate(key, args);
    }
}