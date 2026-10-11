package com.github.cristiangonsan.simplepdftool.Localization;

import com.github.cristiangonsan.simplepdftool.Localization.Leguages.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public final class Lang {

    private static final Map<String, Map<String, String>> languages = new HashMap<>();
    private static final String DEFAULT_CODE = "en";
    private static volatile Map<String, String> current;

    static {
        register(new Spanish());
        register(new English());
        register(new Portuguese());
        register(new French());
        register(new Russian());
        use(Locale.getDefault().getLanguage());
    }

    private Lang() {}

    private static void register(Language language) {
        languages.put(language.code(), language.build());
    }

    public static void use(String code) {
        current = languages.getOrDefault(code, languages.get(DEFAULT_CODE));
    }

    public static String translate(String key, Object... args) {
        String text = current.get(key);
        if (text == null) text = languages.get(DEFAULT_CODE).get(key);
        if (text == null) return key;
        return args.length == 0 ? text : text.formatted(args);
    }
}