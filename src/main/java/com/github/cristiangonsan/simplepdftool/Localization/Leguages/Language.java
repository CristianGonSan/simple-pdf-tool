package com.github.cristiangonsan.simplepdftool.Localization.Leguages;

import java.util.HashMap;
import java.util.Map;

// Language.java
public abstract class Language {
    public abstract String code(); // "es", "en"
    protected abstract void define(Map<String, String> t);

    public final Map<String, String> build() {
        Map<String, String> map = new HashMap<>();
        define(map);
        return map;
    }
}