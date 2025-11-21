package com.github.cristiangonsan.simplepdftool.PdfTool;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class PdfPageLayout {
    public static final float POINTS_PER_INCH   = 72.0F;
    public static final float POINTS_PER_MM     = 2.8346457F;

    private final PageOrientation pageOrientation;
    private final PageSize pageSizeType;
    private final float marginInPoints;

    private final PDRectangle pdfPageRectangle;

    private final float contentX, contentY, contentWidth, contentHeight;



    public PdfPageLayout(PageOrientation pageOrientation, PageSize pageSizeType, float millimetersMargin) {
        this.pageOrientation = pageOrientation;
        this.pageSizeType = pageSizeType;

        if (millimetersMargin < 0) {
            millimetersMargin = 0;
        }

        marginInPoints = millimetersMargin * POINTS_PER_MM;

        if (pageSizeType == PageSize.NULL) {
            pdfPageRectangle = null;
            contentX = 0;
            contentY = 0;
            contentWidth = 0;
            contentHeight = 0;
        } else {
            PDRectangle pdRectangle = pageSizeType.pdRectangle;

            if (pageOrientation == PageOrientation.HORIZONTAL) {
                pdfPageRectangle = new PDRectangle(pdRectangle.getHeight(), pdRectangle.getWidth());
            } else {
                pdfPageRectangle = new PDRectangle(pdRectangle.getWidth(), pdRectangle.getHeight());
            }

            contentX = marginInPoints;
            contentY = marginInPoints;
            contentWidth = pdfPageRectangle.getWidth() - (marginInPoints * 2);
            contentHeight = pdfPageRectangle.getHeight() - (marginInPoints * 2);
        }
    }



    public void addImagePage(PDDocument document, BufferedImage image) throws IOException {
        if (pageSizeType == PageSize.NULL) {
            PDRectangle imageRectangle = new PDRectangle(image.getWidth(), image.getHeight());

            PDPage page = new PDPage(imageRectangle);
            document.addPage(page);

            var pdImage = LosslessFactory.createFromImage(document, image);

            try (PDPageContentStream content = new PDPageContentStream(document, page)) {
                content.drawImage(pdImage, 0, 0, image.getWidth(), image.getHeight());
            }

            return;
        }

        PDPage page = new PDPage(pdfPageRectangle);
        document.addPage(page);

        float imgWidth  = image.getWidth();
        float imgHeight = image.getHeight();

        float scale = Math.min(contentWidth / imgWidth, contentHeight / imgHeight);

        float drawWidth     = imgWidth * scale;
        float drawHeight    = imgHeight * scale;

        float x = contentX + (contentWidth - drawWidth) / 2;
        float y = contentY + (contentHeight - drawHeight) / 2;

        var pdImage = LosslessFactory.createFromImage(document, image);
        try (PDPageContentStream content = new PDPageContentStream(document, page)) {
            content.drawImage(pdImage, x, y, drawWidth, drawHeight);
        }
    }

    public static float millimetersToPt(float millimeters) {
        return millimeters * POINTS_PER_MM;
    }



    public PageOrientation getOrientation() {
        return pageOrientation;
    }

    public PageSize getSize() {
        return pageSizeType;
    }

    public float getMarginInPoints() {
        return marginInPoints;
    }



    public enum PageOrientation {
        VERTICAL("Vertical"),
        HORIZONTAL("Horizontal");

        final String name;

        PageOrientation(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public enum PageSize {
        NULL("Igual a la imagen", null),
        LETTER("Carta", PDRectangle.LETTER),
        TABLOID("Tabloide", PDRectangle.TABLOID),
        LEGAL("Oficio", PDRectangle.LEGAL),
        A0("A0", PDRectangle.A0),
        A1("A1", PDRectangle.A1),
        A2("A2", PDRectangle.A2),
        A3("A3", PDRectangle.A3),
        A4("A4", PDRectangle.A4),
        A5("A5", PDRectangle.A5),
        A6("A6", PDRectangle.A6);

        final String name;
        final PDRectangle pdRectangle;

        PageSize(String name, PDRectangle pdRectangle) {
            this.name = name;
            this.pdRectangle = pdRectangle;
        }

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

            return name + " (" + (int) getWidthToMillimeters() + "x" + (int) getHeightToMillimeters() + " mm)";
        }
    }
}
