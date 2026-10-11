package com.github.cristiangonsan.simplepdftool.Localization.Leguages;

import java.util.Map;

// Spanish.java
public class Spanish extends Language {
    @Override
    public String code() {
        return "es";
    }

    @Override
    protected void define(Map<String, String> t) {
        // common
        t.put("common.invalid_file", "Archivo inválido.");
        t.put("common.folder_creation_error", "No se pudo crear la carpeta: %s");
        t.put("common.process_in_progress", "Hay un proceso en curso. Por favor espere...");

        // dialog
        t.put("dialog.overwrite.title", "Sobreescribir");
        t.put("dialog.overwrite.message", "Este archivo ya existe ¿Desea sobreescribirlo?");
        t.put("dialog.error.title", "Error");

        // process
        t.put("process.finished", "Proceso finalizado");
        t.put("process.error", "Error: %s");

        // single
        t.put("single.invalid_pdf", "No se ha seleccionado un pdf.");
        t.put("single.folder_not_supported", "> Carpeta agregada: %s\\\n  - Esta operación no admite carpetas\n");
        t.put("single.pdf_selected", "> PDF Seleccionado: %s\n");
        t.put("single.new_pdf_selected", "> Nuevo PDF Seleccionado: %s\n");

        // multi
        t.put("multi.invalid_folder", "No se ha seleccionado una carpeta.");
        t.put("multi.file_added", "> %s\n");
        t.put("multi.folder_header", "> Carpeta agregada: %s\\\n");
        t.put("multi.folder_empty", "  - No se encontraron archivos válidos en la carpeta\n");
        t.put("multi.folder_file_item", "  - %s\n");

        // main
        t.put("main.presentation", """
                ───────────── Simple PDF Tool v%s ─────────────
                
                Utilidad sencilla y práctica para trabajar con archivos PDF.
                Elija una de las siguientes operaciones:
                
                - Convertir varias imágenes en un único PDF (JPG, PNG o WEBP).
                - Exportar cada página de un PDF como imagen.
                - Unir varios archivos PDF en un solo documento.
                - Extraer un rango de páginas y generar un PDF nuevo.
                
                """);
        t.put("main.window.closing.title", "Finalizar proceso");
        t.put("main.window.closing.message", "Hay un proceso en curso ¿Deseas salir?");
        t.put("main.drop.error", "No se pudo procesar lo arrastrado: %s");

        // images_to_pdf
        t.put("images_to_pdf.presentation", """
                ──────────────── UNIR IMÁGENES EN PDF ────────────────
                
                Crea un archivo PDF a partir de varias imágenes.
                
                Seleccione las imágenes, una carpeta que las contenga
                o arrástrelas directamente a la consola.
                Entrada:    JPG, PNG o WEBP
                Salida:     PDF
                
                Archivos seleccionados:
                """);
        t.put("images_to_pdf.chooser.save.title", "Guardar PDF");
        t.put("images_to_pdf.chooser.save.approve", "Seleccionar PDF para guardar");
        t.put("images_to_pdf.chooser.input.file.title", "Agregar imagen");
        t.put("images_to_pdf.chooser.input.file.approve", "Agregar imagen");
        t.put("images_to_pdf.chooser.input.folder.title", "Agregar Carpeta");
        t.put("images_to_pdf.chooser.input.folder.approve", "Agregar Carpeta");
        t.put("images_to_pdf.invalid_image", "No se ha seleccionado una imagen valida.");
        t.put("images_to_pdf.need_one_image", "Seleccione al menos una imagen");
        t.put("images_to_pdf.default_output_name", "Resultado.pdf");
        t.put("images_to_pdf.config_header", "Configuración del PDF:");
        t.put("images_to_pdf.config_size_only", "Tamaño: %s");
        t.put("images_to_pdf.config_orientation", "Orientación : %s");
        t.put("images_to_pdf.config_size", "Tamaño      : %s");
        t.put("images_to_pdf.config_margin", "Margen (mm) : %s");
        t.put("images_to_pdf.process.start", "Creando PDF con %d páginas...");
        t.put("images_to_pdf.process.saving", "Guardando PDF en: ");
        t.put("images_to_pdf.process.error_title", "Error al crear el PDF:");

        // pdf_to_images
        t.put("pdf_to_images.presentation", """
                ─────────────── CONVERTIR PDF A IMÁGENES ───────────────
                
                Genera una imagen por cada página del archivo PDF.
                Puede ajustar el formato de salida y la resolución (DPI)
                antes de iniciar.
                
                Seleccione el archivo PDF o arrástrelo sobre la consola.
                Entrada:    PDF
                Salida:     JPG o PNG
                
                Archivo seleccionado:
                """);
        t.put("pdf_to_images.chooser.save.title", "Guardar imágenes");
        t.put("pdf_to_images.chooser.save.approve", "Seleccionar carpeta para guardar");
        t.put("pdf_to_images.chooser.input.file.title", "Agregar PDF");
        t.put("pdf_to_images.chooser.input.file.approve", "Agregar PDF");
        t.put("pdf_to_images.need_pdf", "No hay pdf seleccionado");
        t.put("pdf_to_images.config_header", "Configuración de las imágenes:");
        t.put("pdf_to_images.config_format", "Formato : %s");
        t.put("pdf_to_images.config_dpi", "DPI     : %d");
        t.put("pdf_to_images.process.start", "Exportando PDF a %d imágenes...");
        t.put("pdf_to_images.process.saving", "Guardando imágenes en: ");
        t.put("pdf_to_images.process.error_title", "Error al exportar el PDF:");

        // merge_pdfs
        t.put("merge_pdfs.presentation", """
                ──────────────── UNIR VARIOS PDF ────────────────
                
                Fusiona varios archivos PDF en un único documento.
                El orden de selección definirá el orden final de las páginas.
                
                Seleccione los archivos PDF o arrástrelos a la consola.
                Entrada:    PDF
                Salida:     PDF
                
                Archivos seleccionados:
                """);
        t.put("merge_pdfs.chooser.save.title", "Guardar PDF");
        t.put("merge_pdfs.chooser.save.approve", "Seleccionar PDF para guardar");
        t.put("merge_pdfs.chooser.input.file.title", "Agregar PDF");
        t.put("merge_pdfs.chooser.input.file.approve", "Agregar PDF");
        t.put("merge_pdfs.chooser.input.folder.title", "Agregar Carpeta");
        t.put("merge_pdfs.chooser.input.folder.approve", "Agregar Carpeta");
        t.put("merge_pdfs.invalid_pdf", "No se ha seleccionado un pdf.");
        t.put("merge_pdfs.need_two_pdfs", "Seleccione al menos 2 PDFs");
        t.put("merge_pdfs.default_output_name", "Resultado.pdf");
        t.put("merge_pdfs.no_config", "Sin configuraciones");
        t.put("merge_pdfs.process.start", "Creando PDF con %d página(s)...");
        t.put("merge_pdfs.process.saving", "Guardando PDF en: ");
        t.put("merge_pdfs.process.error_title", "Error al crear el PDF:");

        // split_pdf
        t.put("split_pdf.presentation", """
                ──────────────── DIVIDIR PDF ────────────────
                
                Divide un archivo PDF en varios documentos distintos.
                Puede indicar la página inicial y final antes de procesar.
                
                Seleccione el archivo PDF o arrástrelo a la consola.
                Entrada:    PDF
                Salida:     PDF
                
                Archivo seleccionado:
                """);
        t.put("split_pdf.chooser.save.title", "Guardar PDF");
        t.put("split_pdf.chooser.save.approve", "Seleccionar PDF para guardar");
        t.put("split_pdf.chooser.input.file.title", "Agregar PDF");
        t.put("split_pdf.chooser.input.file.approve", "Agregar PDF");
        t.put("split_pdf.need_pdf", "No hay pdf seleccionado");
        t.put("split_pdf.default_output_name", "Resultado.pdf");
        t.put("split_pdf.config_header", "Configuración del PDF:");
        t.put("split_pdf.config_start_page", "Pag. inicio : %d");
        t.put("split_pdf.config_end_page", "Pag. final  : %d");
        t.put("split_pdf.process.start", "Dividiendo PDF...");
        t.put("split_pdf.process.saving", "Guardando PDF: ");
        t.put("split_pdf.process.error_title", "Error al dividir el PDF:");

        // credits
        t.put("credits.presentation", """
                ──────────────── Single PDF Tool ────────────────
                
                Versión: %s
                Desarrollador : Cristian González Santos
                Email         : cristiangonsan18@gmail.com
                Github        : https://github.com/CristianGonSan
                
                Tecnologías utilizadas:
                - Java 21
                - Apache PDFBox
                - JDeploy
                
                ISC License
                Copyright <2025> <Cristian González Santos>
                """);
        t.put("credits.select_operation_first", "Seleccione una operación primero");

        // view
        t.put("view.main.title", "Simple PDF Tool");
        t.put("view.operations.title", "Operaciones principales");
        t.put("view.operations.images_to_pdf", "Imágenes → PDF");
        t.put("view.operations.pdf_to_images", "PDF → Imágenes");
        t.put("view.operations.merge_pdfs", "Unir PDFs");
        t.put("view.operations.split_pdf", "Dividir PDF");
        t.put("view.operations.credits", "Créditos");
        t.put("view.sidebar.files.title", "Archivos");
        t.put("view.sidebar.actions.title", "Acciones");
        t.put("view.sidebar.add_file", "Agregar archivo");
        t.put("view.sidebar.add_folder", "Agregar carpeta");
        t.put("view.sidebar.clear", "Limpiar lista");
        t.put("view.sidebar.save", "Guardar resultado");
        t.put("view.console.title", "Consola de salida");
        t.put("view.parameters.title", "Parámetros");
        t.put("view.parameters.empty", "Sin parámetros");
        t.put("view.parameters.images_to_pdf.orientation", "Orientación de página");
        t.put("view.parameters.images_to_pdf.size", "Tamaño de página");
        t.put("view.parameters.images_to_pdf.margin", "Margen (milímetros)");
        t.put("view.parameters.pdf_to_images.format", "Formato de imagen:");
        t.put("view.parameters.pdf_to_images.dpi", "DPI:");
        t.put("view.parameters.split_pdf.start_page", "Página de inicio:");
        t.put("view.parameters.split_pdf.end_page", "Página final:");
    }
}
