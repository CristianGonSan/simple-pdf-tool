package com.github.cristiangonsan.simplepdftool.Utils;

import com.github.cristiangonsan.simplepdftool.PdfTool.PdfOperation;

public class ConsoleText {

    public static final String MAIN_TEXT = """
            ───────────── Simple PDF Tool v0.1.1 ─────────────
            
            Utilidad sencilla y práctica para trabajar con archivos PDF.
            Elija una de las siguientes operaciones:
            
            - Convertir varias imágenes en un único PDF (JPG, PNG o WEBP).
            - Exportar cada página de un PDF como imagen.
            - Unir varios archivos PDF en un solo documento.
            - Extraer un rango de páginas y generar un PDF nuevo.
            
            """;

    public static String IMAGES_TO_PDF = """
            ──────────────── UNIR IMÁGENES EN PDF ────────────────
            
            Crea un archivo PDF a partir de varias imágenes.
            
            Seleccione las imágenes, una carpeta que las contenga
            o arrástrelas directamente a la consola.
            Entrada:    JPG, PNG o WEBP
            Salida:     PDF
            
            Archivos seleccionados:
            """;

    public static String PDF_TO_IMAGES = """
            ─────────────── CONVERTIR PDF A IMÁGENES ───────────────
            
            Genera una imagen por cada página del archivo PDF.
            Puede ajustar el formato de salida y la resolución (DPI)
            antes de iniciar.
            
            Seleccione el archivo PDF o arrástrelo sobre la consola.
            Entrada:    PDF
            Salida:     JPG o PNG
            
            Archivo seleccionado:
            """;

    public static String MERGE_PDFS = """
            ──────────────── UNIR VARIOS PDF ────────────────
            
            Fusiona varios archivos PDF en un único documento.
            El orden de selección definirá el orden final de las páginas.
            
            Seleccione los archivos PDF o arrástrelos a la consola.
            Entrada:    PDF
            Salida:     PDF
            
            Archivos seleccionados:
            """;

    public static String PDF_SPLIT = """
            ──────────────── DIVIDIR PDF ────────────────
            
            Divide un archivo PDF en varios documentos distintos.
            Puede indicar la página inicial y final antes de procesar.
            
            Seleccione el archivo PDF o arrástrelo a la consola.
            Entrada:    PDF
            Salida:     PDF
            
            Archivo seleccionado:
            """;

    public static String getTextFromPdfOperation(PdfOperation pdfOperation) {
        return switch (pdfOperation) {
            case IMAGES_TO_PDF  -> IMAGES_TO_PDF;
            case PDF_TO_IMAGES  -> PDF_TO_IMAGES;
            case MERGE_PDFS     -> MERGE_PDFS;
            case PDF_SPLIT      -> PDF_SPLIT;
            case null           -> MAIN_TEXT;
        };
    }

    public static String CREDITS = """
            ──────────────── Single PDF Tool ────────────────
            
            Versión: 0.1.1
            Desarrollador : Cristian González Santos
            Email         : cristiangonsan18@gmail.com
            Github        : https://github.com/CristianGonSan
            
            Tecnologías utilizadas:
            - Java 21
            - Apache PDFBox
            - JDeploy
            
            ISC License
            Copyright <2025> <Cristian González Santos>
            """;
}
