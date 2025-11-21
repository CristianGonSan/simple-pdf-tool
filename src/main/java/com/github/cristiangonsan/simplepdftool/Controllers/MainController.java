package com.github.cristiangonsan.simplepdftool.Controllers;

import com.github.cristiangonsan.simplepdftool.Image.FileImageProvider;
import com.github.cristiangonsan.simplepdftool.Listeners.StepListener;
import com.github.cristiangonsan.simplepdftool.PdfTool.*;
import com.github.cristiangonsan.simplepdftool.Utils.ConsoleText;
import com.github.cristiangonsan.simplepdftool.Utils.FileSupport;
import com.github.cristiangonsan.simplepdftool.View.MainView;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainController {

    private final MainView mainView;
    private final JFrame frame;

    private JTextArea console;

    private JButton
            imagesToPdfButton,
            pdfToImagesButton,
            mergePdfsButton,
            splitPdfButton,
            addFileButton,
            addFolderButton,
            clearButton,
            creditsButton,
            saveButton;

    private JComboBox<PdfPageLayout.PageOrientation> pageOrientationCombo;
    private JComboBox<PdfPageLayout.PageSize> pageSizeCombo;
    private JComboBox<String> imageFormatsCombo;
    private JComboBox<Integer> imageDpiCombo;

    private JTextField
            pageMarginField,
            startPageField,
            endPageField;

    private final JProgressBar progressBar;

    //Files
    private final JFileChooser fileChooser;
    private final ArrayList<File> fileList = new ArrayList<>();
    private File pdfUnique;

    private final String desktopPath;

    //Control
    private PdfOperation pdfOperation = null;
    private boolean isProcessing;


    public MainController() {
        mainView = new MainView();
        frame = mainView.getFrame();

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (isProcessing) {
                    int option = JOptionPane.showConfirmDialog(
                            frame,
                            "Hay un proceso en curso ¿Deseas salir?",
                            "Finalizar proceso",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (option == JOptionPane.YES_OPTION) {
                        frame.dispose();
                    }
                } else {
                    frame.dispose();
                }
            }
        });

        fileChooser = new JFileChooser();
        desktopPath = FileSupport.getDesktopPath();

        progressBar = mainView.getProgressBar();
        initConsole();
        initOptionsButtons();
        initSideButtons();
        initParameters();

        frame.setVisible(true);
    }


    //Inicialización
    private void initConsole() {
        console = mainView.getConsole();
        console.setText(ConsoleText.MAIN_TEXT);
        console.setDropTarget(dropTarget);
    }

    private void initOptionsButtons() {
        imagesToPdfButton = mainView.getImagesToPdfButton();
        pdfToImagesButton = mainView.getPdfToImagesButton();
        mergePdfsButton = mainView.getMergePdfsButton();
        splitPdfButton = mainView.getSplitPdfButton();
        creditsButton = mainView.getCreditsButton();

        addPdfAction(imagesToPdfButton, PdfOperation.IMAGES_TO_PDF);
        addPdfAction(pdfToImagesButton, PdfOperation.PDF_TO_IMAGES);
        addPdfAction(mergePdfsButton, PdfOperation.MERGE_PDFS);
        addPdfAction(splitPdfButton, PdfOperation.PDF_SPLIT);

        creditsButton.addActionListener(e -> showCredits());
    }

    private void initSideButtons() {
        addFileButton = mainView.getAddFileButton();
        addFolderButton = mainView.getAddFolderButton();
        clearButton = mainView.getClearButton();
        saveButton = mainView.getSaveButton();

        mainView.setEnabledSideButtons(false);

        addFileButton.addActionListener(e -> chooseInputFile());
        addFolderButton.addActionListener(e -> chooseInputFolder());
        clearButton.addActionListener(e -> clear());
        saveButton.addActionListener(e -> save());

    }

    private void initParameters() {
        pageOrientationCombo = mainView.getPageOrientationCombo();
        pageSizeCombo = mainView.getPageSizesCombo();
        imageFormatsCombo = mainView.getImageFormatsCombo();
        imageDpiCombo = mainView.getImageDpiCombo();

        pageMarginField = mainView.getPageMargeField();
        startPageField = mainView.getStartPageField();
        endPageField = mainView.getEndPageField();
    }

    //Helper
    private void addPdfAction(JButton button, PdfOperation operation) {
        button.addActionListener(e -> {
            if (checkProcessing()) return;
            clearFiles();
            setPdfOperation(operation);
        });
    }


    //Cambio de operación
    private void setPdfOperation(PdfOperation newPdfOperation) {
        pdfOperation = newPdfOperation;

        mainView.setEnabledTopButtons(true);
        mainView.setEnabledSideButtons(false);

        switch (pdfOperation) {
            case IMAGES_TO_PDF -> {
                imagesToPdfButton.setEnabled(false);

                addFileButton.setEnabled(true);
                addFolderButton.setEnabled(true);

                mainView.showImagesToPdfParametersPanel();
            }
            case PDF_TO_IMAGES -> {
                pdfToImagesButton.setEnabled(false);

                addFileButton.setEnabled(true);

                mainView.showPdfToImagesParametersPanel();
            }
            case MERGE_PDFS -> {
                mergePdfsButton.setEnabled(false);

                addFileButton.setEnabled(true);
                addFolderButton.setEnabled(true);

                mainView.showEmptyParametersPanel();
            }
            case PDF_SPLIT -> {
                splitPdfButton.setEnabled(false);

                addFileButton.setEnabled(true);

                mainView.showSlitPdfParametersPanel();
            }
        }

        console.setText(ConsoleText.getTextFromPdfOperation(pdfOperation));
    }


    private void clear() {
        if (checkProcessing()) return;
        clearFiles();
        setPdfOperation(pdfOperation);
    }

    private void save() {
        if (checkProcessing()) return;
        switch (pdfOperation) {
            case IMAGES_TO_PDF -> saveImagesToPDF();
            case PDF_TO_IMAGES -> savePdfToImages();
            case MERGE_PDFS -> saveMergePdfs();
            case PDF_SPLIT -> savePdfSplit();
        }
    }

    private void showCredits() {
        if (checkProcessing()) return;
        pdfOperation = null;

        mainView.setEnabledTopButtons(true);
        mainView.setEnabledSideButtons(false);
        mainView.showEmptyParametersPanel();
        creditsButton.setEnabled(false);

        clearFiles();
        console.setText(ConsoleText.CREDITS);
    }

    private void startProcess() {
        isProcessing = true;
    }

    private void endProcess() {
        isProcessing = false;
    }

    //Reinicia la interfaz
    private void reset(String consoleText) {
        pdfOperation = null;
        pdfUnique = null;
        fileList.clear();

        mainView.setEnabledTopButtons(true);
        mainView.setEnabledSideButtons(false);

        console.setText(Objects.requireNonNullElse(consoleText, ConsoleText.MAIN_TEXT));
    }


    private void saveImagesToPDF() {
        File outputFile = chooseOutputFile("Resultado.pdf");

        if (outputFile != null) {
            PdfPageLayout.PageOrientation pageOrientation
                    = (PdfPageLayout.PageOrientation) pageOrientationCombo.getSelectedItem();
            PdfPageLayout.PageSize pageSize
                    = (PdfPageLayout.PageSize) pageSizeCombo.getSelectedItem();
            float pageMargin = getNumeric(pageMarginField, 0);

            StringBuilder text = new StringBuilder();
            text.append("\n> Configuración del PDF:");

            if (pageSize == PdfPageLayout.PageSize.NULL) {
                text.append("\n  - Tamaño: ").append(pageSize);
            } else {
                text.append("\n  - Orientación : ").append(pageOrientation)
                        .append("\n  - Tamaño      : ").append(pageSize)
                        .append("\n  - Margen (mm) : ").append(pageMargin);
            }

            text.append("\n");
            console.append(text.toString());

            PdfPageLayout builder = new PdfPageLayout(pageOrientation, pageSize, pageMargin);

            SwingWorker<Void, Integer> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() {
                    try (FileImageProvider provider = new FileImageProvider(fileList)) {
                        PdfImageConverter.imagesToPDF(provider, outputFile, builder, imageToPdfListener);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    return null;
                }
            };
            worker.execute();
        }
    }

    private void savePdfToImages() {
        if (pdfUnique != null) {
            File newFolder = chooseOutputFolder(pdfUnique.getName());

            if (newFolder != null) {
                String format = (String) imageFormatsCombo.getSelectedItem();
                int dpi = (int) imageDpiCombo.getSelectedItem();

                String text = "\n> Configuración de las imágenes:" +
                        "\n  - Formato : " + format +
                        "\n  - DPI     : " + dpi +
                        "\n";

                console.append(text);

                SwingWorker<Void, Integer> worker = new SwingWorker<>() {
                    @Override
                    protected Void doInBackground() {
                        try {
                            PdfImageConverter.pdfToImages(pdfUnique, newFolder, format, dpi, pdfToImagesListener);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        return null;
                    }
                };
                worker.execute();
            }
        }
    }

    private void saveMergePdfs() {
        if (fileList.size() >= 2) {
            File outputFile = chooseOutputFile("Resultado.pdf");

            if (outputFile != null) {
                String text = """
                        
                        > Sin configuraciones:
                        
                        """;

                console.append(text);

                SwingWorker<Void, Integer> worker = new SwingWorker<>() {
                    @Override
                    protected Void doInBackground() {
                        PdfMerger.merge(fileList, outputFile, mergePdfsListener);
                        return null;
                    }
                };
                worker.execute();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Seleccione al menos 2 PDFs");
        }
    }

    private void savePdfSplit() {
        if (pdfUnique != null) {
            File outputFile = chooseOutputFile("Resultado.pdf");

            if (outputFile != null) {
                int startPage   = getNumeric(startPageField, 1);
                int endPage     = getNumeric(endPageField, 1);

                String text = "\n> Configuración del PDF:" +
                        "\n  - Pag. inicio : " + startPage +
                        "\n  - Pag. final  : " + endPage +
                        "\n";

                console.append(text);

                SwingWorker<Void, Integer> worker = new SwingWorker<>() {
                    @Override
                    protected Void doInBackground() {
                        try {
                            PdfSplitter.split(pdfUnique, outputFile, startPage, endPage, pdfSplitListener);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        return null;
                    }
                };
                worker.execute();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "No hay pdf seleccionado");
        }
    }


    private final StepListener imageToPdfListener = new StepListener() {
        @Override
        public void onStart(int totalSteps) {
            startProcess();
            SwingUtilities.invokeLater(() -> {
                progressBar.setMaximum(totalSteps);
                progressBar.setValue(0);
                console.append("\n> Creando PDF con " + totalSteps + " páginas...\n");
            });
        }

        @Override
        public void onProgress(int currentStep, int totalSteps) {
            SwingUtilities.invokeLater(() -> progressBar.setValue(currentStep));
        }

        @Override
        public void onSaving(File output) {
            SwingUtilities.invokeLater(() -> console.append("\n> Guardando PDF en: " + output.getAbsolutePath() + "\n"));
        }


        @Override
        public void onFinalized() {
            endProcess();
            SwingUtilities.invokeLater(() -> {
                progressBar.setValue(progressBar.getMaximum());
                console.append("> Proceso finalizado\n");
            });
        }

        @Override
        public void onError(Exception e) {
            endProcess();
            SwingUtilities.invokeLater(() -> {
                console.append("> Error: " + e.getMessage() + "\n");
                JOptionPane.showMessageDialog(frame, "Error al crear el PDF:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            });
        }
    };

    private final StepListener pdfToImagesListener = new StepListener() {
        @Override
        public void onStart(int totalSteps) {
            startProcess();
            SwingUtilities.invokeLater(() -> {
                progressBar.setMaximum(totalSteps);
                progressBar.setValue(0);
                console.append("\n> Exportando PDF a " + totalSteps + " imágenes...\n");
            });
        }

        @Override
        public void onProgress(int currentStep, int totalSteps) {
            SwingUtilities.invokeLater(() -> progressBar.setValue(currentStep));
        }

        @Override
        public void onSaving(File output) {
            SwingUtilities.invokeLater(() -> console.append("\n> Guardando imágenes en: " + output.getAbsolutePath() + "\n"));
        }

        @Override
        public void onFinalized() {
            endProcess();
            SwingUtilities.invokeLater(() -> {
                progressBar.setValue(progressBar.getMaximum());
                console.append("> Proceso finalizado\n");
            });
        }

        @Override
        public void onError(Exception e) {
            endProcess();
            SwingUtilities.invokeLater(() -> {
                console.append("> Error: " + e.getMessage() + "\n");
                JOptionPane.showMessageDialog(frame, "Error al exportar el PDF:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            });
        }
    };

    private final StepListener mergePdfsListener = new StepListener() {
        @Override
        public void onStart(int totalSteps) {
            startProcess();
            SwingUtilities.invokeLater(() -> {
                progressBar.setMaximum(totalSteps);
                progressBar.setValue(0);
                console.append("\n> Creando PDF con " + totalSteps + " página(s)...\n");
            });
        }

        @Override
        public void onProgress(int currentStep, int totalSteps) {
            SwingUtilities.invokeLater(() -> progressBar.setValue(currentStep));
        }

        @Override
        public void onSaving(File output) {
            SwingUtilities.invokeLater(() -> console.append("\n> Guardando PDF en: " + output.getAbsolutePath() + "\n"));
        }

        @Override
        public void onFinalized() {
            endProcess();
            SwingUtilities.invokeLater(() -> {
                progressBar.setValue(progressBar.getMaximum());
                console.append("> Proceso finalizado\n");
            });
        }

        @Override
        public void onError(Exception e) {
            endProcess();
            SwingUtilities.invokeLater(() -> {
                console.append("> Error: " + e.getMessage() + "\n");
                JOptionPane.showMessageDialog(frame, "Error al crear el PDF:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            });
        }
    };

    private final StepListener pdfSplitListener = new StepListener() {
        @Override
        public void onStart(int totalSteps) {
            startProcess();
            SwingUtilities.invokeLater(() -> {
                progressBar.setMaximum(totalSteps);
                progressBar.setValue(0);
                console.append("\n> Dividiendo PDF...\n");
            });
        }

        @Override
        public void onProgress(int currentStep, int totalSteps) {
            SwingUtilities.invokeLater(() -> progressBar.setValue(currentStep));
        }

        @Override
        public void onSaving(File output) {
            SwingUtilities.invokeLater(() -> console.append("\n> Guardando PDF: " + output.getAbsolutePath() + "\n"));
        }

        @Override
        public void onFinalized() {
            endProcess();
            SwingUtilities.invokeLater(() -> {
                progressBar.setValue(progressBar.getMaximum());
                console.append("> Proceso finalizado\n");
            });
        }

        @Override
        public void onError(Exception e) {
            endProcess();
            SwingUtilities.invokeLater(() -> {
                console.append("> Error: " + e.getMessage() + "\n");
                JOptionPane.showMessageDialog(frame, "Error al dividir el PDF:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            });
        }
    };


    private final DropTarget dropTarget = new DropTarget() {
        public synchronized void drop(DropTargetDropEvent e) {
            if (checkProcessing()) return;
            if (pdfOperation == null) {
                JOptionPane.showMessageDialog(frame, "Seleccione una operación primero");
                return;
            }
            e.acceptDrop(DnDConstants.ACTION_COPY);
            try {
                String path = e.getTransferable().getTransferData(DataFlavor.javaFileListFlavor).toString();
                path = path.substring(1, path.length() - 1);

                File file = new File(path);

                if (file.isFile()) {
                    if (validateInputFile(file)) {
                        addFile(file);
                    }
                } else {
                    addFolder(file);
                }
            } catch (UnsupportedFlavorException | IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    };

    //Entrada de archivos
    private void addFile(File newFile) {
        String message;
        if (pdfOperation == PdfOperation.PDF_TO_IMAGES || pdfOperation == PdfOperation.PDF_SPLIT) {
            message = pdfUnique == null ?
                    "> PDF Seleccionado: " + newFile.getName() + "\n" :
                    "> Nuevo PDF Seleccionado: " + newFile.getName() + "\n";
            pdfUnique = newFile;
        } else {
            fileList.add(newFile);
            int i = fileList.size();
            message = "> " + newFile.getName() + "\n";
        }

        clearButton.setEnabled(true);
        saveButton.setEnabled(true);

        console.append(message);
    }

    private void addFolder(File folder) {
        File[] newFiles = switch (pdfOperation) {
            case IMAGES_TO_PDF  -> folder.listFiles(FileSupport.IMAGE_FILE_NAME_FILTER);
            case MERGE_PDFS     -> folder.listFiles(FileSupport.PDF_FILE_NAME_FILTER);
            default -> null;
        };

        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append("> Carpeta agregada: ").append(folder.getAbsolutePath()).append("\\\n");

        if (newFiles == null || newFiles.length == 0) {
            stringBuffer.append("  - No se encontraron archivos válidos en la carpeta\n");
        } else {
            fileList.addAll(List.of(newFiles));

            saveButton.setEnabled(true);
            clearButton.setEnabled(true);

            for (File f : newFiles) {
                stringBuffer.append("  - ").append(f.getName()).append("\n");
            }
        }
        console.append(stringBuffer.toString());
    }

    private void clearFiles() {
        progressBar.setValue(0);
        pdfUnique = null;
        fileList.clear();
        System.gc();
    }


    private boolean checkProcessing() {
        if (isProcessing) {
            JOptionPane.showMessageDialog(frame, "Hay un proceso en curso. Por favor espere...");
        }

        return isProcessing;
    }


    private void chooseInputFile() {
        if (checkProcessing()) return;

        FileFilter filter = switch (pdfOperation) {
            case IMAGES_TO_PDF -> FileSupport.IMAGE_EXTENSION_FILTER;
            case PDF_TO_IMAGES, MERGE_PDFS, PDF_SPLIT -> FileSupport.PDF_EXTENSION_FILTER;
        };

        String title = switch (pdfOperation) {
            case IMAGES_TO_PDF -> "Agregar imagen";
            case PDF_TO_IMAGES, MERGE_PDFS, PDF_SPLIT -> "Agregar PDF";
        };

        File file = chooseFile(JFileChooser.FILES_ONLY, filter, null, title);

        if (file == null || !validateInputFile(file)) return;

        addFile(file);
    }

    private void chooseInputFolder() {
        if (checkProcessing()) return;

        File folder = chooseFile(JFileChooser.DIRECTORIES_ONLY, null, null, "Agregar Carpeta");

        if (folder == null) return;

        if (folder.isDirectory()) {
            addFolder(folder);
        } else {
            JOptionPane.showMessageDialog(frame, "No se ha seleccionado una carpeta.");
        }
    }


    private File chooseOutputFile(String fileName) {
        File preselected = new File(desktopPath + File.separator + fileName);

        File file = chooseFile(JFileChooser.FILES_ONLY, FileSupport.PDF_EXTENSION_FILTER, preselected, "Seleccionar PDF para guardar");

        if (file == null) {
            return null;
        }

        if (FileSupport.isValidPdfExtension(file)) {
            if (file.exists()) {
                if (JOptionPane.showConfirmDialog(frame,
                        "Este archivo ya existe ¿Desea sobreescribirlo?",
                        "Sobreescribir", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    return file;
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Archivo invalido.");
        }

        return file;
    }

    private File chooseOutputFolder(String folderName) {
        if (folderName.toLowerCase().endsWith(".pdf")) {
            folderName = folderName.substring(0, folderName.length() - 4);
        }

        File base = chooseFile(JFileChooser.DIRECTORIES_ONLY, null,
                new File(desktopPath + File.separator), "Seleccionar carpeta para guardar");

        if (base == null) return null;

        File newFolder = new File(base, folderName);

        if (!newFolder.exists() && !newFolder.mkdirs()) {
            JOptionPane.showMessageDialog(frame, "No se pudo crear la carpeta: " + newFolder.getAbsolutePath());
            return null;
        }

        return newFolder;
    }


    private File chooseFile(int selectionMode, FileFilter filter, File preselected, String title) {
        fileChooser.resetChoosableFileFilters();
        fileChooser.setFileSelectionMode(selectionMode);

        if (filter != null) {
            fileChooser.setFileFilter(filter);
        }

        if (preselected != null) {
            fileChooser.setSelectedFile(preselected);
        }

        return fileChooser.showDialog(frame, title == null ? "Abrir" : title) == JFileChooser.APPROVE_OPTION
                ? fileChooser.getSelectedFile()
                : null;
    }

    private boolean validateInputFile(File inputFile) {
        switch (pdfOperation) {
            case IMAGES_TO_PDF -> {
                if (!FileSupport.isValidImageExtension(inputFile)) {
                    JOptionPane.showMessageDialog(frame, "No se ha seleccionado una imagen valida.");
                    return false;
                }
            }
            default -> {
                if (!FileSupport.isValidPdfExtension(inputFile)) {
                    JOptionPane.showMessageDialog(frame, "No se ha seleccionado un pdf.");
                    return false;
                }
            }
        }
        return true;
    }

    private int getNumeric(JTextField field, int def) {
        String text = field.getText();
        if (text.isEmpty()) {
            return def;
        }
        return Integer.parseInt(text);
    }

}
