package com.github.cristiangonsan.simplepdftool.Listeners;

public interface SingleStepListener {
    void onProgress(int currentStep, int totalSteps);

    void onFinalized();

    void onError(Exception e);
}
