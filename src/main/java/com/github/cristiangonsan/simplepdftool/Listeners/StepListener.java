package com.github.cristiangonsan.simplepdftool.Listeners;

import java.io.File;

public interface StepListener {
    void onStart(int totalSteps);

    void onProgress(int currentStep, int totalSteps);

    void onSaving(File output);
    void onFinalized();

    void onError(Exception e);
}
