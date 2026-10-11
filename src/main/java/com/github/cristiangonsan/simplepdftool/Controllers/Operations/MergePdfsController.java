package com.github.cristiangonsan.simplepdftool.Controllers.Operations;

import com.github.cristiangonsan.simplepdftool.Controllers.Base.MultiFileOperationController;
import com.github.cristiangonsan.simplepdftool.Controllers.ProcessState;
import com.github.cristiangonsan.simplepdftool.Listeners.StepListener;
import com.github.cristiangonsan.simplepdftool.Localization.Lang;
import com.github.cristiangonsan.simplepdftool.PdfTool.PdfMerger;
import com.github.cristiangonsan.simplepdftool.Utils.FileSupport;
import com.github.cristiangonsan.simplepdftool.View.MainView;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MergePdfsController extends MultiFileOperationController {

    private final JButton button;
    private final JFileChooser outputChooser;

    public MergePdfsController(MainView mainView, ProcessState processState) {
        super(mainView, processState, createInputChooser(), createFolderChooser());

        button = mainView.getMergePdfsButton();

        outputChooser = new JFileChooser();
        outputChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        outputChooser.setFileFilter(FileSupport.PDF_EXTENSION_FILTER);
        outputChooser.setDialogTitle(tr("merge_pdfs.chooser.save.title"));
        outputChooser.setApproveButtonText(tr("merge_pdfs.chooser.save.approve"));
    }

    private static JFileChooser createInputChooser() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setFileFilter(FileSupport.PDF_EXTENSION_FILTER);
        chooser.setDialogTitle(Lang.translate("merge_pdfs.chooser.input.file.title"));
        chooser.setApproveButtonText(Lang.translate("merge_pdfs.chooser.input.file.approve"));
        return chooser;
    }

    private static JFileChooser createFolderChooser() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogTitle(Lang.translate("merge_pdfs.chooser.input.folder.title"));
        chooser.setApproveButtonText(Lang.translate("merge_pdfs.chooser.input.folder.approve"));
        return chooser;
    }

    @Override
    public void prepareUi() {
        beginPrepare(button);
        mainView.getAddFileButton().setEnabled(true);
        mainView.getAddFolderButton().setEnabled(true);
        mainView.showEmptyParametersPanel();
        console.setText(tr("merge_pdfs.presentation"));
    }

    @Override
    protected boolean validateInputFile(File file) {
        if (!FileSupport.isValidPdfExtension(file)) {
            showMessage(tr("merge_pdfs.invalid_pdf"));
            return false;
        }
        return true;
    }

    @Override
    protected File[] listFolder(File folder) {
        return folder.listFiles(FileSupport.PDF_FILE_NAME_FILTER);
    }

    @Override
    public void execute() {
        if (fileList.size() < 2) {
            showMessage(tr("merge_pdfs.need_two_pdfs"));
            return;
        }

        File outputFile = chooseOutputPdf(outputChooser, tr("merge_pdfs.default_output_name"));
        if (outputFile == null) return;

        console.append("\n> " + tr("merge_pdfs.no_config") + "\n");

        List<File> files = new ArrayList<>(fileList);

        StepListener listener = createListener(
                total -> "\n> " + tr("merge_pdfs.process.start", total) + "\n",
                tr("merge_pdfs.process.saving"),
                tr("merge_pdfs.process.error_title"));

        runAsync(listener, () -> PdfMerger.merge(files, outputFile, listener));
    }
}