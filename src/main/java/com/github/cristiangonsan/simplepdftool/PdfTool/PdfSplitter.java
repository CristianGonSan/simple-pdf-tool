package com.github.cristiangonsan.simplepdftool.PdfTool;

import com.github.cristiangonsan.simplepdftool.Listeners.StepListener;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

public class PdfSplitter {

    public static void split(File pdf, File outputFile, int startPage, int endPage, StepListener listener) {
        try (PDDocument document = PDDocument.load(pdf)) {
            int totalPage = document.getNumberOfPages();

            startPage = Math.max(1, Math.min(startPage, totalPage));
            endPage   = Math.max(startPage, Math.min(endPage, totalPage));

            if (listener != null) listener.onStart(totalPage);

            int totalSteps = (endPage - startPage) + 1;
            int currentStep = 1;
            try (PDDocument newDocument = new PDDocument()) {
                for (int i = startPage; i <= endPage; i++) {
                    newDocument.addPage(document.getPage(i - 1));

                    if (listener != null) listener.onProgress(currentStep++, totalSteps);
                }

                if (listener != null) listener.onSaving(outputFile);
                newDocument.save(outputFile);
                if (listener != null) listener.onFinalized();
            }
        } catch (IOException e) {
            if (listener != null) listener.onError(e);
        }
    }
}
