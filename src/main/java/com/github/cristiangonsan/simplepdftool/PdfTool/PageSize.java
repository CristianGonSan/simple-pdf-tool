package com.github.cristiangonsan.simplepdftool.PdfTool;

import org.apache.pdfbox.pdmodel.common.PDRectangle;

public record PageSize(String name, PDRectangle pdRectangle) {
    public static final float POINTS_PER_INCH = 72.0F;
    public static final float POINTS_PER_MM = 2.8346457F;

    public static final PageSize[] PAGE_SIZES = {
            new PageSize("El mismo que la imagen", null),
            new PageSize("Carta", PDRectangle.LETTER),
            new PageSize("Tabloide", PDRectangle.TABLOID),
            new PageSize("Oficio", PDRectangle.LEGAL),
            new PageSize("A0", PDRectangle.A0),
            new PageSize("A1", PDRectangle.A1),
            new PageSize("A2", PDRectangle.A2),
            new PageSize("A3", PDRectangle.A3),
            new PageSize("A4", PDRectangle.A4),
            new PageSize("A5", PDRectangle.A5),
            new PageSize("A6", PDRectangle.A6)
    };

    public float getWidthToMillimeters() {
        return pdRectangle == null ? 0 : pdRectangle.getWidth() / POINTS_PER_MM;
    }

    public float getHeightToMillimeters() {
        return pdRectangle == null ? 0 : pdRectangle.getHeight() / POINTS_PER_MM;
    }

    @Override
    public String toString() {
        if (pdRectangle == null) {
            return name;
        }

        return name + " (" + (int) getHeightToMillimeters() + "x" + (int) getWidthToMillimeters() + " mm)";
    }
}
