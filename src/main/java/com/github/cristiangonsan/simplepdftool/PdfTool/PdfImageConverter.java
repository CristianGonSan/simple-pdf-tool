package com.github.cristiangonsan.simplepdftool.PdfTool;

import com.github.cristiangonsan.simplepdftool.Image.ImageProvider;
import com.github.cristiangonsan.simplepdftool.Listeners.StepListener;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PdfImageConverter {

    public static void imagesToPDF(ImageProvider imageProvider, File outputPdfFile, PdfPageLayout pdfPageLayout, StepListener listener) {
        try (PDDocument document = new PDDocument()) {
            int total = imageProvider.getTotal();
            if (listener != null) listener.onStart(total);

            while (imageProvider.hasNext()) {
                BufferedImage image = imageProvider.nextImage();
                pdfPageLayout.addImagePage(document, image);
                image.flush();

                if (listener != null) listener.onProgress(imageProvider.getIndex() + 1, total);
            }

            if (listener != null) listener.onSaving(outputPdfFile);
            document.save(outputPdfFile);
            if (listener != null) listener.onFinalized();

        } catch (IOException e) {
            if (listener != null) listener.onError(e);
        }
    }

    public static void pdfToImages(File pdf, File outputFolder, String format, int dpi, StepListener listener) {
        try (PDDocument document = PDDocument.load(pdf)) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);

            int total = document.getNumberOfPages();
            if (listener != null) listener.onStart(total);

            if (listener != null) listener.onSaving(outputFolder);

            for (int i = 0; i < total; i++) {
                BufferedImage image = pdfRenderer.renderImageWithDPI(i, dpi);

                String fileName = outputFolder.getAbsolutePath() + File.separator + "pag_" + (i + 1) + "." + format.toLowerCase();
                ImageIO.write(image, format, new File(fileName));
                image.flush();

                if (listener != null) listener.onProgress(i, total);
            }

            if (listener != null) listener.onFinalized();
        } catch (IOException e) {
            if (listener != null) listener.onError(e);
        }
    }

}
