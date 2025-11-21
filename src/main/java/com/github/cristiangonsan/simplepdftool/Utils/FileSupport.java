package com.github.cristiangonsan.simplepdftool.Utils;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FilenameFilter;

public class FileSupport {

    private static final String[] IMAGE_EXTENSIONS = {"jpg", "png", "jpeg", "webp"};

    public static final String[] IMAGE_FORMATS_OPTIONS = {"JPG", "PNG"};

    public static final Integer[] DPI_OPTIONS = {72, 96, 150, 300, 600};

    public static final FileNameExtensionFilter PDF_EXTENSION_FILTER = new FileNameExtensionFilter(
            "PDF (*.pdf)", "pdf"
    );

    public static final FileNameExtensionFilter IMAGE_EXTENSION_FILTER = new FileNameExtensionFilter(
            "Imágenes (*.jpg, *.jpeg, *.png, *.webp)", IMAGE_EXTENSIONS
    );

    public static final FilenameFilter IMAGE_FILE_NAME_FILTER = (folder, name) -> {
        for (String extension : IMAGE_EXTENSIONS) {
            if (name.toLowerCase().endsWith(extension)) return true;
        }
        return false;
    };

    public static final FilenameFilter PDF_FILE_NAME_FILTER = (folder, name) -> name.toLowerCase().endsWith("pdf");


    public static boolean isValidImageExtension(File imageFile) {
        String fileName = imageFile.getName().toLowerCase();
        for (String extension : IMAGE_EXTENSIONS) {
            if (fileName.endsWith(extension)) return true;
        }
        return false;
    }

    public static boolean isValidPdfExtension(File pdfFile) {
        return pdfFile.getName().toLowerCase().endsWith(".pdf");
    }

    public static String getDesktopPath() {
        return FileSystemView.getFileSystemView().getHomeDirectory().getPath();
    }
}
