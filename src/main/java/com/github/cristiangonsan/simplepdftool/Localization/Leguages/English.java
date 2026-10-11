package com.github.cristiangonsan.simplepdftool.Localization.Leguages;

import java.util.Map;

// English.java
public class English extends Language {
    @Override
    public String code() {
        return "en";
    }

    @Override
    protected void define(Map<String, String> t) {
        // common
        t.put("common.invalid_file", "Invalid file.");
        t.put("common.folder_creation_error", "Could not create folder: %s");
        t.put("common.process_in_progress", "A process is currently in progress. Please wait...");

        // dialog
        t.put("dialog.overwrite.title", "Overwrite");
        t.put("dialog.overwrite.message", "This file already exists. Do you want to overwrite it?");
        t.put("dialog.error.title", "Error");

        // process
        t.put("process.finished", "Process finished");
        t.put("process.error", "Error: %s");

        // single
        t.put("single.invalid_pdf", "No PDF has been selected.");
        t.put("single.folder_not_supported", "> Folder added: %s\\\nThis operation does not support folders\n");
        t.put("single.pdf_selected", "> Selected PDF: %s\n");
        t.put("single.new_pdf_selected", "> New PDF Selected: %s\n");

        // multi
        t.put("multi.invalid_folder", "No folder has been selected.");
        t.put("multi.file_added", "> %s\n");
        t.put("multi.folder_header", "> Folder added: %s\\\n");
        t.put("multi.folder_empty", "  - No valid files found in the folder\n");
        t.put("multi.folder_file_item", "  - %s\n");

        // main
        t.put("main.presentation", """
                ───────────── Simple PDF Tool v%s ─────────────
                
                Simple and practical utility to work with PDF files.
                Choose one of the following operations:
                - Convert multiple images into a single PDF (JPG, PNG, or WEBP).
                - Export each page of a PDF as an image.
                - Merge multiple PDF files into a single document.
                - Extract a page range and generate a new PDF.
                
                """);
        t.put("main.window.closing.title", "Finish Process");
        t.put("main.window.closing.message", "A process is currently in progress. Do you want to exit?");
        t.put("main.drop.error", "Could not process dropped item: %s");

        // images_to_pdf
        t.put("images_to_pdf.presentation", """
                ──────────────── MERGE IMAGES TO PDF ────────────────
                
                Create a PDF file from multiple images.
                
                Select the images, a folder containing them,
                or drag and drop them directly onto the console.
                Input:      JPG, PNG, or WEBP
                Output:     PDF
                
                Selected files:
                """);
        t.put("images_to_pdf.chooser.save.title", "Save PDF");
        t.put("images_to_pdf.chooser.save.approve", "Select PDF to save");
        t.put("images_to_pdf.chooser.input.file.title", "Add Image");
        t.put("images_to_pdf.chooser.input.file.approve", "Add Image");
        t.put("images_to_pdf.chooser.input.folder.title", "Add Folder");
        t.put("images_to_pdf.chooser.input.folder.approve", "Add Folder");
        t.put("images_to_pdf.invalid_image", "No valid image has been selected.");
        t.put("images_to_pdf.need_one_image", "Select at least one image");
        t.put("images_to_pdf.default_output_name", "Result.pdf");
        t.put("images_to_pdf.config_header", "PDF Configuration:");
        t.put("images_to_pdf.config_size_only", "Size: %s");
        t.put("images_to_pdf.config_orientation", "Orientation : %s");
        t.put("images_to_pdf.config_size", "Size        : %s");
        t.put("images_to_pdf.config_margin", "Margin (mm) : %s");
        t.put("images_to_pdf.process.start", "Creating PDF with %d page(s)...");
        t.put("images_to_pdf.process.saving", "Saving PDF to: ");
        t.put("images_to_pdf.process.error_title", "Error creating the PDF:");

        // pdf_to_images
        t.put("pdf_to_images.presentation", """
                ─────────────── CONVERT PDF TO IMAGES ───────────────
                
                Generate an image for each page of the PDF file.
                You can adjust the output format and resolution (DPI)
                before starting.
                
                Select the PDF file or drag and drop it onto the console.
                Input:      PDF
                Output:     JPG or PNG
                
                Selected file:
                """);
        t.put("pdf_to_images.chooser.save.title", "Save Images");
        t.put("pdf_to_images.chooser.save.approve", "Select folder to save");
        t.put("pdf_to_images.chooser.input.file.title", "Add PDF");
        t.put("pdf_to_images.chooser.input.file.approve", "Add PDF");
        t.put("pdf_to_images.need_pdf", "No PDF selected");
        t.put("pdf_to_images.config_header", "Image Configuration:");
        t.put("pdf_to_images.config_format", "Format : %s");
        t.put("pdf_to_images.config_dpi", "DPI    : %d");
        t.put("pdf_to_images.process.start", "Exporting PDF to %d images...");
        t.put("pdf_to_images.process.saving", "Saving images to: ");
        t.put("pdf_to_images.process.error_title", "Error exporting the PDF:");

        // merge_pdfs
        t.put("merge_pdfs.presentation", """
                ──────────────── MERGE MULTIPLE PDFS ────────────────
                
                Merge multiple PDF files into a single document.
                The selection order will define the final page order.
                
                Select the PDF files or drag and drop them to the console.
                Input:      PDF
                Output:     PDF
                
                Selected files:
                """);
        t.put("merge_pdfs.chooser.save.title", "Save PDF");
        t.put("merge_pdfs.chooser.save.approve", "Select PDF to save");
        t.put("merge_pdfs.chooser.input.file.title", "Add PDF");
        t.put("merge_pdfs.chooser.input.file.approve", "Add PDF");
        t.put("merge_pdfs.chooser.input.folder.title", "Add Folder");
        t.put("merge_pdfs.chooser.input.folder.approve", "Add Folder");
        t.put("merge_pdfs.invalid_pdf", "No PDF has been selected.");
        t.put("merge_pdfs.need_two_pdfs", "Select at least 2 PDFs");
        t.put("merge_pdfs.default_output_name", "Result.pdf");
        t.put("merge_pdfs.no_config", "No configurations");
        t.put("merge_pdfs.process.start", "Creating PDF with %d page(s)...");
        t.put("merge_pdfs.process.saving", "Saving PDF to: ");
        t.put("merge_pdfs.process.error_title", "Error creating the PDF:");

        // split_pdf
        t.put("split_pdf.presentation", """
                ──────────────── SPLIT PDF ────────────────
                
                Split a PDF file into multiple separate documents.
                You can specify the start and end pages before processing.
                
                Select the PDF file or drag and drop it to the console.
                Input:      PDF
                Output:     PDF
                
                Selected file:
                """);
        t.put("split_pdf.chooser.save.title", "Save PDF");
        t.put("split_pdf.chooser.save.approve", "Select PDF to save");
        t.put("split_pdf.chooser.input.file.title", "Add PDF");
        t.put("split_pdf.chooser.input.file.approve", "Add PDF");
        t.put("split_pdf.need_pdf", "No PDF selected");
        t.put("split_pdf.default_output_name", "Result.pdf");
        t.put("split_pdf.config_header", "PDF Configuration:");
        t.put("split_pdf.config_start_page", "Start page : %d");
        t.put("split_pdf.config_end_page", "End page   : %d");
        t.put("split_pdf.process.start", "Splitting PDF...");
        t.put("split_pdf.process.saving", "Saving PDF: ");
        t.put("split_pdf.process.error_title", "Error splitting the PDF:");

        // credits
        t.put("credits.presentation", """
                ──────────────── Single PDF Tool ────────────────
                
                Version: %s
                Developer : Cristian González Santos
                Email     : cristiangonsan18@gmail.com
                Github    : https://github.com/CristianGonSan
                
                Technologies used:
                - Java 21
                - Apache PDFBox
                - JDeploy
                
                ISC License
                Copyright <2025> <Cristian González Santos>
                """);
        t.put("credits.select_operation_first", "Please select an operation first");

        // view
        t.put("view.main.title", "Simple PDF Tool");
        t.put("view.operations.title", "Main operations");
        t.put("view.operations.images_to_pdf", "Images → PDF");
        t.put("view.operations.pdf_to_images", "PDF → Images");
        t.put("view.operations.merge_pdfs", "Merge PDFs");
        t.put("view.operations.split_pdf", "Split PDF");
        t.put("view.operations.credits", "Credits");
        t.put("view.sidebar.files.title", "Files");
        t.put("view.sidebar.actions.title", "Actions");
        t.put("view.sidebar.add_file", "Add file");
        t.put("view.sidebar.add_folder", "Add folder");
        t.put("view.sidebar.clear", "Clear list");
        t.put("view.sidebar.save", "Save result");
        t.put("view.console.title", "Output console");
        t.put("view.parameters.title", "Parameters");
        t.put("view.parameters.empty", "No parameters");
        t.put("view.parameters.images_to_pdf.orientation", "Page orientation");
        t.put("view.parameters.images_to_pdf.size", "Page size");
        t.put("view.parameters.images_to_pdf.margin", "Margin (millimeters)");
        t.put("view.parameters.pdf_to_images.format", "Image format:");
        t.put("view.parameters.pdf_to_images.dpi", "DPI:");
        t.put("view.parameters.split_pdf.start_page", "Start page:");
        t.put("view.parameters.split_pdf.end_page", "End page:");
    }
}