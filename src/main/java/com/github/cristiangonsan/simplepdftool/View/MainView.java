package com.github.cristiangonsan.simplepdftool.View;

import com.github.cristiangonsan.simplepdftool.PdfTool.PdfPageLayout;
import com.github.cristiangonsan.simplepdftool.Utils.FileSupport;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class MainView {
    private final JFrame frame;
    private JTextArea console;
    private JProgressBar progressBar;

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

    private JComboBox<PdfPageLayout.PageOrientation>    pageOrientationCombo;
    private JComboBox<PdfPageLayout.PageSize>           pageSizesCombo;
    private JComboBox<String>                           imageFormatsCombo;
    private JComboBox<Integer>                          imageDpiCombo;

    private JTextField pageMargeField, startPageField, endPageField;

    private final JPanel parametersPanel;

    private CardLayout cardLayout;



    public MainView() {
        setupLookAndFeel();
        frame = createMainFrame();

        JPanel topPanel = createMainOperationsPanel();
        JPanel sidePanel = createLeftSidePanel();
        JPanel centerPanel = createCenterPanel();
        JPanel bottomPanel = createBottomPanel();

        topPanel.setPreferredSize(new Dimension(0, 70));

        parametersPanel = createParametersPanel();

        JPanel base = new JPanel(new BorderLayout(10, 10));
        base.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        base.add(topPanel, BorderLayout.NORTH);
        base.add(sidePanel, BorderLayout.WEST);
        base.add(parametersPanel, BorderLayout.EAST);
        base.add(centerPanel, BorderLayout.CENTER);
        base.add(bottomPanel, BorderLayout.SOUTH);

        frame.setContentPane(base);
        frame.setLocationRelativeTo(null);
    }



    private void setupLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("Button.font", new Font("Segoe UI", Font.PLAIN, 14));
            UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 14));
            UIManager.put("TextField.font", new Font("Consolas", Font.PLAIN, 14));
            UIManager.put("Button.focus", new Color(0, 0, 0, 0)); // sin borde de enfoque
        } catch (Exception ignored) {
        }
    }

    private JFrame createMainFrame() {
        JFrame f = new JFrame("Simple PDF Tool");
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.setSize(1000, 650);
        f.setMinimumSize(new Dimension(850, 550));
        f.setLayout(new BorderLayout(10, 10));

        ImageIcon icono = new ImageIcon(Objects.requireNonNull(getClass().getResource("/img/icon.png")));
        f.setIconImage(icono.getImage());

        return f;
    }

    private JPanel createMainOperationsPanel() {
        JPanel topPanel = new JPanel(new GridLayout(1, 5, 10, 10));
        topPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Operaciones principales"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        imagesToPdfButton   = new JButton("Imágenes → PDF");
        pdfToImagesButton   = new JButton("PDF → Imágenes");
        mergePdfsButton     = new JButton("Unir PDFs");
        splitPdfButton      = new JButton("Dividir PDF");
        creditsButton       = new JButton("Créditos");

        imagesToPdfButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pdfToImagesButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        mergePdfsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        splitPdfButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        creditsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        topPanel.add(imagesToPdfButton);
        topPanel.add(pdfToImagesButton);
        topPanel.add(mergePdfsButton);
        topPanel.add(splitPdfButton);
        topPanel.add(creditsButton);

        return topPanel;
    }

    private JPanel createLeftSidePanel() {
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));

        Dimension widthPanel = new Dimension(180, 0);

        sidePanel.setMinimumSize(widthPanel);
        sidePanel.setPreferredSize(widthPanel);
        sidePanel.setMaximumSize(widthPanel);

        JPanel filesPanel = new JPanel();
        filesPanel.setLayout(new BoxLayout(filesPanel, BoxLayout.Y_AXIS));
        filesPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Archivos"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        JPanel actionsPanel = new JPanel();
        actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.Y_AXIS));
        actionsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Acciones"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        sidePanel.add(filesPanel);
        sidePanel.add(actionsPanel);

        addFileButton = createSideButton("Agregar archivo");
        filesPanel.add(addFileButton);
        filesPanel.add(Box.createVerticalStrut(8));

        addFolderButton = createSideButton("Agregar carpeta");
        filesPanel.add(addFolderButton);
        filesPanel.add(Box.createVerticalStrut(8));
        filesPanel.add(Box.createVerticalGlue());

        clearButton = createSideButton("Limpiar lista");
        actionsPanel.add(clearButton);
        actionsPanel.add(Box.createVerticalStrut(8));

        saveButton = createSideButton("Guardar resultado");
        actionsPanel.add(saveButton);
        actionsPanel.add(Box.createVerticalStrut(8));
        actionsPanel.add(Box.createVerticalGlue());

        return sidePanel;
    }

    private JButton createSideButton(String text) {
        Dimension minimumSize   = new Dimension(0, 35);
        Dimension preferredSize = new Dimension(0, 35);
        Dimension maximumSize   = new Dimension(500, 35);

        JButton newButton = new JButton(text);
        newButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newButton.setMinimumSize(minimumSize);
        newButton.setPreferredSize(preferredSize);
        newButton.setMaximumSize(maximumSize);
        newButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return newButton;
    }

    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));

        console = new JTextArea(12, 50);
        console.setEditable(false);
        console.setFont(new Font("Consolas", Font.PLAIN, 14));
        console.setBackground(Color.BLACK);
        console.setForeground(Color.GREEN);
        console.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JScrollPane consoleScroll = new JScrollPane(console);
        consoleScroll.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Consola de salida"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        centerPanel.add(consoleScroll, BorderLayout.CENTER);

        return centerPanel;
    }

    private JPanel createParametersPanel() {
        cardLayout = new CardLayout();
        JPanel parametersPanel = new JPanel(cardLayout);

        Dimension widthPanel = new Dimension(200, 0);

        parametersPanel.setMinimumSize(widthPanel);
        parametersPanel.setPreferredSize(widthPanel);
        parametersPanel.setMaximumSize(widthPanel);

        parametersPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Parámetros"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        JPanel pdfToImagesParametersPanel   = createVerticalPanel();
        JPanel slitPdfParametersPanel       = createVerticalPanel();
        JPanel emptyParametersPanel         = createVerticalPanel();
        JPanel imagesToPdfParametersPanel   = createVerticalPanel();

        Dimension d1    = new Dimension(500, 30);
        Dimension d2    = new Dimension(200, 25);
        Font font       = new Font("Segoe UI", Font.PLAIN, 14);

        pageOrientationCombo = new JComboBox<>(PdfPageLayout.PageOrientation.values());
        pageOrientationCombo.setMaximumSize(d1);
        pageOrientationCombo.setFont(font);

        pageSizesCombo = new JComboBox<>(PdfPageLayout.PageSize.values());
        pageSizesCombo.setMaximumSize(d1);
        pageSizesCombo.setSelectedIndex(1);
        pageSizesCombo.setFont(font);
        pageSizesCombo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        imageFormatsCombo = new JComboBox<>(FileSupport.IMAGE_FORMATS_OPTIONS);
        imageFormatsCombo.setMaximumSize(d1);
        imageFormatsCombo.setFont(font);
        imageFormatsCombo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        imageDpiCombo = new JComboBox<>(FileSupport.DPI_OPTIONS);
        imageDpiCombo.setMaximumSize(d1);
        imageDpiCombo.setSelectedIndex(2);
        imageDpiCombo.setFont(font);
        imageDpiCombo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        pageMargeField = new JTextField(4);
        pageMargeField.setMaximumSize(d1);
        pageMargeField.setText("0");
        pageMargeField.setHorizontalAlignment(SwingConstants.LEFT);
        pageMargeField.setFont(font);

        startPageField = new JTextField(4);
        startPageField.setMaximumSize(d1);
        startPageField.setText("1");
        startPageField.setHorizontalAlignment(SwingConstants.LEFT);
        startPageField.setFont(font);

        endPageField = new JTextField(4);
        endPageField.setMaximumSize(d1);
        endPageField.setText("1");
        endPageField.setHorizontalAlignment(SwingConstants.LEFT);
        endPageField.setFont(font);

        KeyAdapter numericAdapter = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    if (!Character.isISOControl(c)) {
                        Toolkit.getDefaultToolkit().beep();
                    }
                    e.consume();
                }
            }
        };

        pageMargeField.addKeyListener(numericAdapter);
        startPageField.addKeyListener(numericAdapter);
        endPageField.addKeyListener(numericAdapter);

        pageMargeField.setPreferredSize(d1);
        startPageField.setPreferredSize(d1);
        endPageField.setPreferredSize(d1);

        JLabel label = new JLabel("Sin parámetros");

        // Añadir elementos como pares verticales
        imagesToPdfParametersPanel.add(createLabeledField("Orientación de página", pageOrientationCombo));
        imagesToPdfParametersPanel.add(createLabeledField("Tamaño de página", pageSizesCombo));
        imagesToPdfParametersPanel.add(createLabeledField("Margen (milímetros)", pageMargeField));

        pdfToImagesParametersPanel.add(createLabeledField("Formato de imagen:", imageFormatsCombo));
        pdfToImagesParametersPanel.add(createLabeledField("DPI:", imageDpiCombo));

        slitPdfParametersPanel.add(createLabeledField("Página de inicio:", startPageField));
        slitPdfParametersPanel.add(createLabeledField("Página final:", endPageField));

        emptyParametersPanel.add(label);

        parametersPanel.add(imagesToPdfParametersPanel, "IMAGES_TO_PDF");
        parametersPanel.add(pdfToImagesParametersPanel, "PDF_TO_IMAGES");
        parametersPanel.add(slitPdfParametersPanel, "SLIT_PDF");
        parametersPanel.add(emptyParametersPanel, "EMPTY");

        cardLayout.show(parametersPanel, "EMPTY");
        return parametersPanel;
    }

    private JPanel createVerticalPanel() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setAlignmentX(Component.LEFT_ALIGNMENT);
        return p;
    }

    private JPanel createLabeledField(String texto, JComponent comp) {
        JPanel cont = new JPanel();
        cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));
        cont.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lbl = new JLabel(texto);
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        comp.setAlignmentX(Component.LEFT_ALIGNMENT);

        cont.add(lbl);
        cont.add(Box.createVerticalStrut(4));
        cont.add(comp);
        cont.add(Box.createVerticalStrut(8)); // Espaciado

        return cont;
    }

    private JPanel createBottomPanel() {
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(progressBar, BorderLayout.CENTER);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        return bottomPanel;
    }



    public void showImagesToPdfParametersPanel() {
        cardLayout.show(parametersPanel, "IMAGES_TO_PDF");
    }

    public void showPdfToImagesParametersPanel() {
        cardLayout.show(parametersPanel, "PDF_TO_IMAGES");
    }

    public void showSlitPdfParametersPanel() {
        cardLayout.show(parametersPanel, "SLIT_PDF");
    }

    public void showEmptyParametersPanel() {
        cardLayout.show(parametersPanel, "EMPTY");
    }

    public void showParametersPanel(String name) {
        cardLayout.show(parametersPanel, name);
    }

    public void setEnabledTopButtons(boolean enabled) {
        imagesToPdfButton.setEnabled(enabled);
        pdfToImagesButton.setEnabled(enabled);
        mergePdfsButton.setEnabled(enabled);
        splitPdfButton.setEnabled(enabled);
        creditsButton.setEnabled(enabled);
    }

    public void setEnabledSideButtons(boolean enabled) {
        addFileButton.setEnabled(enabled);
        addFolderButton.setEnabled(enabled);
        clearButton.setEnabled(enabled);
        saveButton.setEnabled(enabled);
    }

    public void setEnabledAllButtons(boolean enabled) {
        imagesToPdfButton.setEnabled(enabled);
        pdfToImagesButton.setEnabled(enabled);
        mergePdfsButton.setEnabled(enabled);
        splitPdfButton.setEnabled(enabled);
        creditsButton.setEnabled(enabled);

        addFileButton.setEnabled(enabled);
        addFolderButton.setEnabled(enabled);
        clearButton.setEnabled(enabled);
        saveButton.setEnabled(enabled);
    }



    public JFrame getFrame() {
        return frame;
    }

    public JTextArea getConsole() {
        return console;
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public JButton getImagesToPdfButton() {
        return imagesToPdfButton;
    }

    public JButton getPdfToImagesButton() {
        return pdfToImagesButton;
    }

    public JButton getMergePdfsButton() {
        return mergePdfsButton;
    }

    public JButton getSplitPdfButton() {
        return splitPdfButton;
    }

    public JButton getAddFileButton() {
        return addFileButton;
    }

    public JButton getAddFolderButton() {
        return addFolderButton;
    }

    public JButton getClearButton() {
        return clearButton;
    }

    public JButton getCreditsButton() {
        return creditsButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JComboBox<PdfPageLayout.PageOrientation> getPageOrientationCombo() {
        return pageOrientationCombo;
    }

    public JComboBox<PdfPageLayout.PageSize> getPageSizesCombo() {
        return pageSizesCombo;
    }

    public JTextField getPageMargeField() {
        return pageMargeField;
    }

    public JComboBox<String> getImageFormatsCombo() {
        return imageFormatsCombo;
    }

    public JComboBox<Integer> getImageDpiCombo() {
        return imageDpiCombo;
    }

    public JTextField getStartPageField() {
        return startPageField;
    }

    public JTextField getEndPageField() {
        return endPageField;
    }
}
