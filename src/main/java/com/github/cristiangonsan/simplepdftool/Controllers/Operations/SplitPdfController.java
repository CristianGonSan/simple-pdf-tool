package com.github.cristiangonsan.simplepdftool.Controllers.Operations;

import com.github.cristiangonsan.simplepdftool.Controllers.ProcessState;
import com.github.cristiangonsan.simplepdftool.Controllers.Base.SinglePdfOperationController;
import com.github.cristiangonsan.simplepdftool.Listeners.StepListener;
import com.github.cristiangonsan.simplepdftool.Localization.Lang;
import com.github.cristiangonsan.simplepdftool.PdfTool.PdfSplitter;
import com.github.cristiangonsan.simplepdftool.Utils.FileSupport;
import com.github.cristiangonsan.simplepdftool.View.MainView;

import javax.swing.*;
import java.io.File;

public class SplitPdfController extends SinglePdfOperationController {

    private final JButton button;
    private final JTextField startPageField;
    private final JTextField endPageField;

    private final JFileChooser outputChooser;

    public SplitPdfController(MainView mainView, ProcessState processState) {
        super(mainView, processState, createInputChooser());

        button = mainView.getSplitPdfButton();
        startPageField = mainView.getStartPageField();
        endPageField = mainView.getEndPageField();

        outputChooser = new JFileChooser();
        outputChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        outputChooser.setFileFilter(FileSupport.PDF_EXTENSION_FILTER);
        outputChooser.setDialogTitle(tr("split_pdf.chooser.save.title"));
        outputChooser.setApproveButtonText(tr("split_pdf.chooser.save.approve"));
    }

    private static JFileChooser createInputChooser() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setFileFilter(FileSupport.PDF_EXTENSION_FILTER);
        chooser.setDialogTitle(Lang.translate("split_pdf.chooser.input.file.title"));
        chooser.setApproveButtonText(Lang.translate("split_pdf.chooser.input.file.approve"));
        return chooser;
    }

    @Override
    public void prepareUi() {
        beginPrepare(button);
        mainView.getAddFileButton().setEnabled(true);
        mainView.showSlitPdfParametersPanel();
        console.setText(tr("split_pdf.presentation"));
    }

    @Override
    public void execute() {
        if (pdf == null) {
            showMessage(tr("split_pdf.need_pdf"));
            return;
        }

        File source = pdf;
        File outputFile = chooseOutputPdf(outputChooser, tr("split_pdf.default_output_name"));
        if (outputFile == null) return;

        int startPage = readInt(startPageField, 1);
        int endPage = readInt(endPageField, 1);

        console.append("\n> " + tr("split_pdf.config_header") +
                "\n" + tr("split_pdf.config_start_page", startPage) +
                "\n" + tr("split_pdf.config_end_page", endPage) +
                "\n");

        StepListener listener = createListener(
                total -> "\n> " + tr("split_pdf.process.start") + "\n",
                tr("split_pdf.process.saving"),
                tr("split_pdf.process.error_title"));

        runAsync(listener, () ->
                PdfSplitter.split(source, outputFile, startPage, endPage, listener));
    }
}