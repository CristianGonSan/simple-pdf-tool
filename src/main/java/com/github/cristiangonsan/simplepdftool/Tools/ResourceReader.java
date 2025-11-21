package com.github.cristiangonsan.simplepdftool.Tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ResourceReader {

    public static String readAsString(String resourcesPath) {
        return ResourceReader.readAsString(resourcesPath, StandardCharsets.UTF_8);
    }

    public static String readAsString(String resourcesPath, Charset charset) {
        InputStream is = ResourceReader.class.getResourceAsStream(resourcesPath);
        if (is == null) {
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
           return sb.toString();
        } catch (IOException ex) {
            return "Error al cargar txt.";
        }
    }
}
