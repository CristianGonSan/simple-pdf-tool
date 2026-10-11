package com.github.cristiangonsan.simplepdftool.Controllers;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Flag de procesamiento compartido entre el controlador principal
 * y todos los sub controladores.
 */
public final class ProcessState {

    private final AtomicBoolean processing = new AtomicBoolean(false);

    /** @return true si logró marcar el inicio (no había otro proceso activo). */
    public boolean tryStart() {
        return processing.compareAndSet(false, true);
    }

    public void end() {
        processing.set(false);
    }

    public boolean isProcessing() {
        return processing.get();
    }
}
