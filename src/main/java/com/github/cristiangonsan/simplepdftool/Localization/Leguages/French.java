package com.github.cristiangonsan.simplepdftool.Localization.Leguages;

import java.util.Map;

// French.java
public class French extends Language {
    @Override
    public String code() {
        return "fr";
    }

    @Override
    protected void define(Map<String, String> t) {
        // common
        t.put("common.invalid_file", "Fichier invalide.");
        t.put("common.folder_creation_error", "Impossible de créer le dossier : %s");
        t.put("common.process_in_progress", "Un traitement est en cours. Veuillez patienter...");

        // dialog
        t.put("dialog.overwrite.title", "Remplacer");
        t.put("dialog.overwrite.message", "Ce fichier existe déjà. Voulez-vous le remplacer ?");
        t.put("dialog.error.title", "Erreur");

        // process
        t.put("process.finished", "Traitement terminé");
        t.put("process.error", "Erreur : %s");

        // single
        t.put("single.invalid_pdf", "Aucun PDF n'a été sélectionné.");
        t.put("single.folder_not_supported", "> Dossier ajouté : %s\\\nCette opération n'accepte pas les dossiers\n");
        t.put("single.pdf_selected", "> PDF sélectionné : %s\n");
        t.put("single.new_pdf_selected", "> Nouveau PDF sélectionné : %s\n");

        // multi
        t.put("multi.invalid_folder", "Aucun dossier n'a été sélectionné.");
        t.put("multi.file_added", "> %s\n");
        t.put("multi.folder_header", "> Dossier ajouté : %s\\\n");
        t.put("multi.folder_empty", "  - Aucun fichier valide n'a été trouvé dans le dossier\n");
        t.put("multi.folder_file_item", "  - %s\n");

        // main
        t.put("main.presentation", """
                ───────────── Simple PDF Tool v%s ─────────────
                
                Utilitaire simple et pratique pour travailler avec des fichiers PDF.
                Choisissez l'une des opérations suivantes :
                
                - Convertir plusieurs images en un seul PDF (JPG, PNG ou WEBP).
                - Exporter chaque page d'un PDF sous forme d'image.
                - Fusionner plusieurs fichiers PDF en un seul document.
                - Extraire une plage de pages et générer un nouveau PDF.
                
                """);
        t.put("main.window.closing.title", "Terminer le traitement");
        t.put("main.window.closing.message", "Un traitement est en cours. Voulez-vous quitter ?");
        t.put("main.drop.error", "Impossible de traiter l'élément déposé : %s");

        // images_to_pdf
        t.put("images_to_pdf.presentation", """
                ──────────────── FUSIONNER DES IMAGES EN PDF ────────────────
                
                Crée un fichier PDF à partir de plusieurs images.
                
                Sélectionnez les images, un dossier qui les contient
                ou glissez-les directement dans la console.
                Entrée :    JPG, PNG ou WEBP
                Sortie :    PDF
                
                Fichiers sélectionnés :
                """);
        t.put("images_to_pdf.chooser.save.title", "Enregistrer le PDF");
        t.put("images_to_pdf.chooser.save.approve", "Sélectionner le PDF à enregistrer");
        t.put("images_to_pdf.chooser.input.file.title", "Ajouter une image");
        t.put("images_to_pdf.chooser.input.file.approve", "Ajouter une image");
        t.put("images_to_pdf.chooser.input.folder.title", "Ajouter un dossier");
        t.put("images_to_pdf.chooser.input.folder.approve", "Ajouter un dossier");
        t.put("images_to_pdf.invalid_image", "Aucune image valide n'a été sélectionnée.");
        t.put("images_to_pdf.need_one_image", "Sélectionnez au moins une image");
        t.put("images_to_pdf.default_output_name", "Resultat.pdf");
        t.put("images_to_pdf.config_header", "Configuration du PDF :");
        t.put("images_to_pdf.config_size_only", "Taille : %s");
        t.put("images_to_pdf.config_orientation", "Orientation : %s");
        t.put("images_to_pdf.config_size", "Taille      : %s");
        t.put("images_to_pdf.config_margin", "Marge (mm)  : %s");
        t.put("images_to_pdf.process.start", "Création du PDF avec %d pages...");
        t.put("images_to_pdf.process.saving", "Enregistrement du PDF dans : ");
        t.put("images_to_pdf.process.error_title", "Erreur lors de la création du PDF :");

        // pdf_to_images
        t.put("pdf_to_images.presentation", """
                ─────────────── CONVERTIR UN PDF EN IMAGES ───────────────
                
                Génère une image pour chaque page du fichier PDF.
                Vous pouvez ajuster le format de sortie et la résolution (DPI)
                avant de commencer.
                
                Sélectionnez le fichier PDF ou glissez-le sur la console.
                Entrée :    PDF
                Sortie :    JPG ou PNG
                
                Fichier sélectionné :
                """);
        t.put("pdf_to_images.chooser.save.title", "Enregistrer les images");
        t.put("pdf_to_images.chooser.save.approve", "Sélectionner le dossier d'enregistrement");
        t.put("pdf_to_images.chooser.input.file.title", "Ajouter un PDF");
        t.put("pdf_to_images.chooser.input.file.approve", "Ajouter un PDF");
        t.put("pdf_to_images.need_pdf", "Aucun PDF sélectionné");
        t.put("pdf_to_images.config_header", "Configuration des images :");
        t.put("pdf_to_images.config_format", "Format : %s");
        t.put("pdf_to_images.config_dpi", "DPI    : %d");
        t.put("pdf_to_images.process.start", "Exportation du PDF en %d images...");
        t.put("pdf_to_images.process.saving", "Enregistrement des images dans : ");
        t.put("pdf_to_images.process.error_title", "Erreur lors de l'exportation du PDF :");

        // merge_pdfs
        t.put("merge_pdfs.presentation", """
                ──────────────── FUSIONNER PLUSIEURS PDF ────────────────
                
                Fusionne plusieurs fichiers PDF en un seul document.
                L'ordre de sélection définira l'ordre final des pages.
                
                Sélectionnez les fichiers PDF ou glissez-les dans la console.
                Entrée :    PDF
                Sortie :    PDF
                
                Fichiers sélectionnés :
                """);
        t.put("merge_pdfs.chooser.save.title", "Enregistrer le PDF");
        t.put("merge_pdfs.chooser.save.approve", "Sélectionner le PDF à enregistrer");
        t.put("merge_pdfs.chooser.input.file.title", "Ajouter un PDF");
        t.put("merge_pdfs.chooser.input.file.approve", "Ajouter un PDF");
        t.put("merge_pdfs.chooser.input.folder.title", "Ajouter un dossier");
        t.put("merge_pdfs.chooser.input.folder.approve", "Ajouter un dossier");
        t.put("merge_pdfs.invalid_pdf", "Aucun PDF n'a été sélectionné.");
        t.put("merge_pdfs.need_two_pdfs", "Sélectionnez au moins 2 PDF");
        t.put("merge_pdfs.default_output_name", "Resultat.pdf");
        t.put("merge_pdfs.no_config", "Aucune configuration");
        t.put("merge_pdfs.process.start", "Création du PDF avec %d page(s)...");
        t.put("merge_pdfs.process.saving", "Enregistrement du PDF dans : ");
        t.put("merge_pdfs.process.error_title", "Erreur lors de la création du PDF :");

        // split_pdf
        t.put("split_pdf.presentation", """
                ──────────────── DIVISER UN PDF ────────────────
                
                Divise un fichier PDF en plusieurs documents distincts.
                Vous pouvez indiquer la page de début et de fin avant le traitement.
                
                Sélectionnez le fichier PDF ou glissez-le dans la console.
                Entrée :    PDF
                Sortie :    PDF
                
                Fichier sélectionné :
                """);
        t.put("split_pdf.chooser.save.title", "Enregistrer le PDF");
        t.put("split_pdf.chooser.save.approve", "Sélectionner le PDF à enregistrer");
        t.put("split_pdf.chooser.input.file.title", "Ajouter un PDF");
        t.put("split_pdf.chooser.input.file.approve", "Ajouter un PDF");
        t.put("split_pdf.need_pdf", "Aucun PDF sélectionné");
        t.put("split_pdf.default_output_name", "Resultat.pdf");
        t.put("split_pdf.config_header", "Configuration du PDF :");
        t.put("split_pdf.config_start_page", "Page début : %d");
        t.put("split_pdf.config_end_page", "Page fin   : %d");
        t.put("split_pdf.process.start", "Division du PDF...");
        t.put("split_pdf.process.saving", "Enregistrement du PDF : ");
        t.put("split_pdf.process.error_title", "Erreur lors de la division du PDF :");

        // credits
        t.put("credits.presentation", """
                ──────────────── Single PDF Tool ────────────────
                
                Version       : %s
                Développeur   : Cristian González Santos
                Email         : cristiangonsan18@gmail.com
                Github        : https://github.com/CristianGonSan
                
                Technologies utilisées :
                - Java 21
                - Apache PDFBox
                - JDeploy
                
                ISC License
                Copyright <2025> <Cristian González Santos>
                """);
        t.put("credits.select_operation_first", "Sélectionnez d'abord une opération");

        // view
        t.put("view.main.title", "Simple PDF Tool");
        t.put("view.operations.title", "Opérations principales");
        t.put("view.operations.images_to_pdf", "Images → PDF");
        t.put("view.operations.pdf_to_images", "PDF → Images");
        t.put("view.operations.merge_pdfs", "Fusionner des PDF");
        t.put("view.operations.split_pdf", "Diviser un PDF");
        t.put("view.operations.credits", "Crédits");
        t.put("view.sidebar.files.title", "Fichiers");
        t.put("view.sidebar.actions.title", "Actions");
        t.put("view.sidebar.add_file", "Ajouter un fichier");
        t.put("view.sidebar.add_folder", "Ajouter un dossier");
        t.put("view.sidebar.clear", "Vider la liste");
        t.put("view.sidebar.save", "Enregistrer le résultat");
        t.put("view.console.title", "Console de sortie");
        t.put("view.parameters.title", "Paramètres");
        t.put("view.parameters.empty", "Aucun paramètre");
        t.put("view.parameters.images_to_pdf.orientation", "Orientation de la page");
        t.put("view.parameters.images_to_pdf.size", "Taille de la page");
        t.put("view.parameters.images_to_pdf.margin", "Marge (millimètres)");
        t.put("view.parameters.pdf_to_images.format", "Format de l'image :");
        t.put("view.parameters.pdf_to_images.dpi", "DPI :");
        t.put("view.parameters.split_pdf.start_page", "Page de début :");
        t.put("view.parameters.split_pdf.end_page", "Page de fin :");
    }
}