package com.github.cristiangonsan.simplepdftool.Controllers.Base;

import com.github.cristiangonsan.simplepdftool.Controllers.ProcessState;
import com.github.cristiangonsan.simplepdftool.Utils.FileSupport;
import com.github.cristiangonsan.simplepdftool.View.MainView;

import javax.swing.*;
import java.io.File;
import java.util.List;

/**
 * Lógica compartida por las operaciones que trabajan con un único PDF
 * (PDF a imágenes, dividir PDF). El JFileChooser lo crea y configura cada subclase.
 */
public abstract class SinglePdfOperationController extends OperationController {

    protected File pdf;

    private final JFileChooser inputChooser;

    protected SinglePdfOperationController(MainView mainView, ProcessState processState, JFileChooser inputChooser) {
        super(mainView, processState);
        this.inputChooser = inputChooser;
    }

    @Override
    public void releaseUi() {
        pdf = null;
        progressBar.setValue(0);
    }

    @Override
    public void chooseInputFile() {
        File file = showChooser(inputChooser);
        if (file == null || !validate(file)) return;
        setPdf(file);
    }

    @Override
    public void handleDrop(List<File> files) {
        for (File file : files) {
            if (file.isFile()) {
                if (validate(file)) setPdf(file);
            } else if (file.isDirectory()) {
                console.append("> Carpeta agregada: " + file.getAbsolutePath() + "\\\n"
                        + "  - Esta operación no admite carpetas\n");
            }
        }
    }

    private boolean validate(File file) {
        if (!FileSupport.isValidPdfExtension(file)) {
            showMessage("No se ha seleccionado un pdf.");
            return false;
        }
        return true;
    }

    private void setPdf(File file) {
        String message = pdf == null
                ? "> PDF Seleccionado: " + file.getName() + "\n"
                : "> Nuevo PDF Seleccionado: " + file.getName() + "\n";
        pdf = file;

        enableSaveAndClear();
        console.append(message);
    }
}
