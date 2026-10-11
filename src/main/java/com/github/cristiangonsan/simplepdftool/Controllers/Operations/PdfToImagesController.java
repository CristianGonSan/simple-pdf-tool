package com.github.cristiangonsan.simplepdftool.Controllers.Operations;

import com.github.cristiangonsan.simplepdftool.Controllers.ProcessState;
import com.github.cristiangonsan.simplepdftool.Controllers.Base.SinglePdfOperationController;
import com.github.cristiangonsan.simplepdftool.Listeners.StepListener;
import com.github.cristiangonsan.simplepdftool.Localization.Lang;
import com.github.cristiangonsan.simplepdftool.PdfTool.PdfImageConverter;
import com.github.cristiangonsan.simplepdftool.Utils.FileSupport;
import com.github.cristiangonsan.simplepdftool.View.MainView;

import javax.swing.*;
import java.io.File;

public class PdfToImagesController extends SinglePdfOperationController {

    private final JButton button;
    private final JComboBox<String> imageFormatsCombo;
    private final JComboBox<Integer> imageDpiCombo;

    private final JFileChooser outputChooser;

    public PdfToImagesController(MainView mainView, ProcessState processState) {
        super(mainView, processState, createInputChooser());

        button = mainView.getPdfToImagesButton();
        imageFormatsCombo = mainView.getImageFormatsCombo();
        imageDpiCombo = mainView.getImageDpiCombo();

        outputChooser = new JFileChooser();
        outputChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        outputChooser.setDialogTitle(tr("pdf_to_images.chooser.save.title"));
        outputChooser.setApproveButtonText(tr("pdf_to_images.chooser.save.approve"));
    }

    private static JFileChooser createInputChooser() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setFileFilter(FileSupport.PDF_EXTENSION_FILTER);
        chooser.setDialogTitle(Lang.translate("pdf_to_images.chooser.input.file.title"));
        chooser.setApproveButtonText(Lang.translate("pdf_to_images.chooser.input.file.approve"));
        return chooser;
    }

    @Override
    public void prepareUi() {
        beginPrepare(button);
        mainView.getAddFileButton().setEnabled(true);
        mainView.showPdfToImagesParametersPanel();
        console.setText(tr("pdf_to_images.presentation"));
    }

    @Override
    public void execute() {
        if (pdf == null) {
            showMessage(tr("pdf_to_images.need_pdf"));
            return;
        }

        File source = pdf;
        File outputFolder = chooseOutputFolder(outputChooser, source.getName());
        if (outputFolder == null) return;

        String format = (String) imageFormatsCombo.getSelectedItem();
        int dpi = (int) (imageDpiCombo.getSelectedItem() != null ? imageDpiCombo.getSelectedItem() : 72);

        console.append("\n> " + tr("pdf_to_images.config_header") +
                "\n" + tr("pdf_to_images.config_format", format) +
                "\n" + tr("pdf_to_images.config_dpi", dpi) +
                "\n");

        StepListener listener = createListener(
                total -> "\n> " + tr("pdf_to_images.process.start", total) + "\n",
                tr("pdf_to_images.process.saving"),
                tr("pdf_to_images.process.error_title"));

        runAsync(listener, () ->
                PdfImageConverter.pdfToImages(source, outputFolder, format, dpi, listener));
    }
}