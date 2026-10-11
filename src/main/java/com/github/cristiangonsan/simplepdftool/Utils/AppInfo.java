package com.github.cristiangonsan.simplepdftool.Utils;

import com.github.cristiangonsan.simplepdftool.SimplePdfTool;

public final class AppInfo {

    public static final String VERSION = resolveVersion();

    private AppInfo() {}

    private static String resolveVersion() {
        String version = SimplePdfTool.class.getPackage().getImplementationVersion();
        return version != null ? version : "dev";
    }
}