package com.github.cristiangonsan.simplepdftool.Controllers.Base;

import com.github.cristiangonsan.simplepdftool.Controllers.ProcessState;
import com.github.cristiangonsan.simplepdftool.View.MainView;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Lógica compartida por las operaciones que trabajan con una lista de archivos
 * (imágenes a PDF, unir PDFs). Los JFileChooser los crea y configura cada subclase.
 */
public abstract class MultiFileOperationController extends OperationController {

    protected final List<File> fileList = new ArrayList<>();

    private final JFileChooser inputChooser;
    private final JFileChooser folderChooser;

    protected MultiFileOperationController(MainView mainView, ProcessState processState, JFileChooser inputChooser, JFileChooser folderChooser) {
        super(mainView, processState);
        this.inputChooser = inputChooser;
        this.folderChooser = folderChooser;
    }

    /** ¿El archivo es de un tipo válido para esta operación? (muestra su propio mensaje si no). */
    protected abstract boolean validateInputFile(File file);

    /** Archivos válidos dentro de la carpeta (puede devolver null). */
    protected abstract File[] listFolder(File folder);

    @Override
    public void releaseUi() {
        fileList.clear();
        progressBar.setValue(0);
    }

    @Override
    public void chooseInputFile() {
        File file = showChooser(inputChooser);
        if (file == null || !validateInputFile(file)) return;
        addFile(file);
    }

    @Override
    public void chooseInputFolder() {
        File folder = showChooser(folderChooser);
        if (folder == null) return;

        if (folder.isDirectory()) {
            addFolder(folder);
        } else {
            showMessage(tr("multi.invalid_folder"));
        }
    }

    @Override
    public void handleDrop(List<File> files) {
        for (File file : files) {
            if (file.isFile()) {
                if (validateInputFile(file)) addFile(file);
            } else if (file.isDirectory()) {
                addFolder(file);
            }
        }
    }

    private void addFile(File file) {
        fileList.add(file);
        enableSaveAndClear();
        console.append(tr("multi.file_added", file.getName()));
    }

    private void addFolder(File folder) {
        File[] newFiles = listFolder(folder);

        StringBuilder sb = new StringBuilder();
        sb.append(tr("multi.folder_header", folder.getAbsolutePath()));

        if (newFiles == null || newFiles.length == 0) {
            sb.append(tr("multi.folder_empty"));
        } else {
            fileList.addAll(List.of(newFiles));
            enableSaveAndClear();
            for (File f : newFiles) {
                sb.append(tr("multi.folder_file_item", f.getName()));
            }
        }
        console.append(sb.toString());
    }
}