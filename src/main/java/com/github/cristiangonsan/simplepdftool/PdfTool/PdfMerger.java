package com.github.cristiangonsan.simplepdftool.PdfTool;

import com.github.cristiangonsan.simplepdftool.Listeners.StepListener;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PdfMerger {

    public static void merge(ArrayList<File> fileList, File outputFile, StepListener listener) {
        merge(fileList.toArray(new File[0]), outputFile, listener);
    }

    public static void merge(File[] pdfs, File outputFile, StepListener listener) {
        PDFMergerUtility merger = new PDFMergerUtility();
        merger.setDestinationFileName(outputFile.getAbsolutePath());

        int total = pdfs.length;
        if (listener != null) listener.onStart(total);

        try {
            for (int i = 0; i < total; i++) {
                merger.addSource(pdfs[i]);
                if (listener != null) listener.onProgress(i + 1, total);
            }

            if (listener != null) listener.onSaving(outputFile);
            merger.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
            if (listener != null) listener.onFinalized();

        } catch (IOException e) {
            if (listener != null) listener.onError(e);
        }
    }
}
