package com.github.cristiangonsan.simplepdftool.Controllers.Operations;

import com.github.cristiangonsan.simplepdftool.Controllers.Base.MultiFileOperationController;
import com.github.cristiangonsan.simplepdftool.Controllers.ProcessState;
import com.github.cristiangonsan.simplepdftool.Image.FileImageProvider;
import com.github.cristiangonsan.simplepdftool.Listeners.StepListener;
import com.github.cristiangonsan.simplepdftool.Localization.Lang;
import com.github.cristiangonsan.simplepdftool.PdfTool.PdfImageConverter;
import com.github.cristiangonsan.simplepdftool.PdfTool.PdfPageLayout;
import com.github.cristiangonsan.simplepdftool.Utils.FileSupport;
import com.github.cristiangonsan.simplepdftool.View.MainView;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImagesToPdfController extends MultiFileOperationController {

    private final JButton button;
    private final JComboBox<PdfPageLayout.PageOrientation> pageOrientationCombo;
    private final JComboBox<PdfPageLayout.PageSize> pageSizeCombo;
    private final JTextField pageMarginField;

    private final JFileChooser outputChooser;

    public ImagesToPdfController(MainView mainView, ProcessState processState) {
        super(mainView, processState, createInputChooser(), createFolderChooser());

        button = mainView.getImagesToPdfButton();
        pageOrientationCombo = mainView.getPageOrientationCombo();
        pageSizeCombo = mainView.getPageSizesCombo();
        pageMarginField = mainView.getPageMargeField();

        outputChooser = new JFileChooser();
        outputChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        outputChooser.setFileFilter(FileSupport.PDF_EXTENSION_FILTER);
        outputChooser.setDialogTitle(tr("images_to_pdf.chooser.save.title"));
        outputChooser.setApproveButtonText(tr("images_to_pdf.chooser.save.approve"));
    }

    private static JFileChooser createInputChooser() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setFileFilter(FileSupport.IMAGE_EXTENSION_FILTER);
        chooser.setDialogTitle(Lang.translate("images_to_pdf.chooser.input.file.title"));
        chooser.setApproveButtonText(Lang.translate("images_to_pdf.chooser.input.file.approve"));
        return chooser;
    }

    private static JFileChooser createFolderChooser() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogTitle(Lang.translate("images_to_pdf.chooser.input.folder.title"));
        chooser.setApproveButtonText(Lang.translate("images_to_pdf.chooser.input.folder.approve"));
        return chooser;
    }

    @Override
    public void prepareUi() {
        beginPrepare(button);
        mainView.getAddFileButton().setEnabled(true);
        mainView.getAddFolderButton().setEnabled(true);
        mainView.showImagesToPdfParametersPanel();
        console.setText(tr("images_to_pdf.presentation"));
    }

    @Override
    protected boolean validateInputFile(File file) {
        if (!FileSupport.isValidImageExtension(file)) {
            showMessage(tr("images_to_pdf.invalid_image"));
            return false;
        }
        return true;
    }

    @Override
    protected File[] listFolder(File folder) {
        return folder.listFiles(FileSupport.IMAGE_FILE_NAME_FILTER);
    }

    @Override
    public void execute() {
        if (fileList.isEmpty()) {
            showMessage(tr("images_to_pdf.need_one_image"));
            return;
        }

        File outputFile = chooseOutputPdf(outputChooser, tr("images_to_pdf.default_output_name"));
        if (outputFile == null) return;

        var orientation = (PdfPageLayout.PageOrientation) pageOrientationCombo.getSelectedItem();
        var size = (PdfPageLayout.PageSize) pageSizeCombo.getSelectedItem();
        float margin = readInt(pageMarginField, 0);

        StringBuilder text = new StringBuilder("\n> " + tr("images_to_pdf.config_header"));
        if (size == PdfPageLayout.PageSize.NULL) {
            text.append("\n").append(tr("images_to_pdf.config_size_only", size));
        } else {
            text.append("\n").append(tr("images_to_pdf.config_orientation", orientation))
                    .append("\n").append(tr("images_to_pdf.config_size", size))
                    .append("\n").append(tr("images_to_pdf.config_margin", margin));
        }
        text.append("\n");
        console.append(text.toString());

        PdfPageLayout layout = new PdfPageLayout(orientation, size, margin);
        List<File> files = new ArrayList<>(fileList);

        StepListener listener = createListener(
                total -> "\n> " + tr("images_to_pdf.process.start", total) + "\n",
                tr("images_to_pdf.process.saving"),
                tr("images_to_pdf.process.error_title"));

        runAsync(listener, () -> {
            try (FileImageProvider provider = new FileImageProvider(files)) {
                PdfImageConverter.imagesToPDF(provider, outputFile, layout, listener);
            }
        });
    }
}