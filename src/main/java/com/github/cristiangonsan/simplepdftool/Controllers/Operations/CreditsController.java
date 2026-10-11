package com.github.cristiangonsan.simplepdftool.Controllers.Operations;

import com.github.cristiangonsan.simplepdftool.Controllers.Base.OperationController;
import com.github.cristiangonsan.simplepdftool.Controllers.ProcessState;
import com.github.cristiangonsan.simplepdftool.Utils.AppInfo;
import com.github.cristiangonsan.simplepdftool.View.MainView;

import javax.swing.*;
import java.io.File;
import java.util.List;

/**
 * "Operación" de solo lectura que muestra los créditos.
 * Evita tener un estado "sin operación" (null) en el controlador principal.
 */
public class CreditsController extends OperationController {

    private final JButton button;

    public CreditsController(MainView mainView, ProcessState processState) {
        super(mainView, processState);
        button = mainView.getCreditsButton();
    }

    @Override
    public void prepareUi() {
        beginPrepare(button);
        mainView.showEmptyParametersPanel();
        console.setText(tr("credits.presentation", AppInfo.VERSION));
    }

    @Override
    public void releaseUi() {
        progressBar.setValue(0);
    }

    @Override
    public void execute() {
        // Nada que ejecutar
    }

    @Override
    public void handleDrop(List<File> files) {
        showMessage(tr("credits.select_operation_first"));
    }
}