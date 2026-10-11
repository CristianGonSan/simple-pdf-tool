package com.github.cristiangonsan.simplepdftool.Localization.Leguages;

import java.util.Map;

// Russian.java
public class Russian extends Language {
    @Override
    public String code() {
        return "ru";
    }

    @Override
    protected void define(Map<String, String> t) {
        // common
        t.put("common.invalid_file", "Недопустимый файл.");
        t.put("common.folder_creation_error", "Не удалось создать папку: %s");
        t.put("common.process_in_progress", "Выполняется процесс. Пожалуйста, подождите...");

        // dialog
        t.put("dialog.overwrite.title", "Перезаписать");
        t.put("dialog.overwrite.message", "Этот файл уже существует. Перезаписать его?");
        t.put("dialog.error.title", "Ошибка");

        // process
        t.put("process.finished", "Процесс завершён");
        t.put("process.error", "Ошибка: %s");

        // single
        t.put("single.invalid_pdf", "PDF-файл не выбран.");
        t.put("single.folder_not_supported", "> Добавлена папка: %s\\\nЭта операция не поддерживает папки\n");
        t.put("single.pdf_selected", "> Выбран PDF: %s\n");
        t.put("single.new_pdf_selected", "> Выбран новый PDF: %s\n");

        // multi
        t.put("multi.invalid_folder", "Папка не выбрана.");
        t.put("multi.file_added", "> %s\n");
        t.put("multi.folder_header", "> Добавлена папка: %s\\\n");
        t.put("multi.folder_empty", "  - В папке не найдено подходящих файлов\n");
        t.put("multi.folder_file_item", "  - %s\n");

        // main
        t.put("main.presentation", """
                ───────────── Simple PDF Tool v%s ─────────────
                
                Простая и удобная утилита для работы с PDF-файлами.
                Выберите одну из следующих операций:
                
                - Преобразовать несколько изображений в один PDF (JPG, PNG или WEBP).
                - Экспортировать каждую страницу PDF как изображение.
                - Объединить несколько PDF-файлов в один документ.
                - Извлечь диапазон страниц и создать новый PDF.
                
                """);
        t.put("main.window.closing.title", "Завершение процесса");
        t.put("main.window.closing.message", "Выполняется процесс. Вы хотите выйти?");
        t.put("main.drop.error", "Не удалось обработать перетащенный элемент: %s");

        // images_to_pdf
        t.put("images_to_pdf.presentation", """
                ──────────────── ОБЪЕДИНЕНИЕ ИЗОБРАЖЕНИЙ В PDF ────────────────
                
                Создаёт PDF-файл из нескольких изображений.
                
                Выберите изображения, папку с ними
                или перетащите их прямо в консоль.
                Вход:      JPG, PNG или WEBP
                Выход:     PDF
                
                Выбранные файлы:
                """);
        t.put("images_to_pdf.chooser.save.title", "Сохранить PDF");
        t.put("images_to_pdf.chooser.save.approve", "Выбрать PDF для сохранения");
        t.put("images_to_pdf.chooser.input.file.title", "Добавить изображение");
        t.put("images_to_pdf.chooser.input.file.approve", "Добавить изображение");
        t.put("images_to_pdf.chooser.input.folder.title", "Добавить папку");
        t.put("images_to_pdf.chooser.input.folder.approve", "Добавить папку");
        t.put("images_to_pdf.invalid_image", "Допустимое изображение не выбрано.");
        t.put("images_to_pdf.need_one_image", "Выберите хотя бы одно изображение");
        t.put("images_to_pdf.default_output_name", "Результат.pdf");
        t.put("images_to_pdf.config_header", "Настройки PDF:");
        t.put("images_to_pdf.config_size_only", "Размер: %s");
        t.put("images_to_pdf.config_orientation", "Ориентация : %s");
        t.put("images_to_pdf.config_size", "Размер     : %s");
        t.put("images_to_pdf.config_margin", "Поля (мм)  : %s");
        t.put("images_to_pdf.process.start", "Создание PDF из %d страниц...");
        t.put("images_to_pdf.process.saving", "Сохранение PDF в: ");
        t.put("images_to_pdf.process.error_title", "Ошибка при создании PDF:");

        // pdf_to_images
        t.put("pdf_to_images.presentation", """
                ─────────────── ПРЕОБРАЗОВАНИЕ PDF В ИЗОБРАЖЕНИЯ ───────────────
                
                Создаёт по одному изображению для каждой страницы PDF-файла.
                Перед запуском можно настроить формат вывода и разрешение (DPI).
                
                Выберите PDF-файл или перетащите его на консоль.
                Вход:      PDF
                Выход:     JPG или PNG
                
                Выбранный файл:
                """);
        t.put("pdf_to_images.chooser.save.title", "Сохранить изображения");
        t.put("pdf_to_images.chooser.save.approve", "Выбрать папку для сохранения");
        t.put("pdf_to_images.chooser.input.file.title", "Добавить PDF");
        t.put("pdf_to_images.chooser.input.file.approve", "Добавить PDF");
        t.put("pdf_to_images.need_pdf", "PDF-файл не выбран");
        t.put("pdf_to_images.config_header", "Настройки изображений:");
        t.put("pdf_to_images.config_format", "Формат : %s");
        t.put("pdf_to_images.config_dpi", "DPI    : %d");
        t.put("pdf_to_images.process.start", "Экспорт PDF в %d изображений...");
        t.put("pdf_to_images.process.saving", "Сохранение изображений в: ");
        t.put("pdf_to_images.process.error_title", "Ошибка при экспорте PDF:");

        // merge_pdfs
        t.put("merge_pdfs.presentation", """
                ──────────────── ОБЪЕДИНЕНИЕ НЕСКОЛЬКИХ PDF ────────────────
                
                Объединяет несколько PDF-файлов в один документ.
                Порядок выбора определяет итоговый порядок страниц.
                
                Выберите PDF-файлы или перетащите их в консоль.
                Вход:      PDF
                Выход:     PDF
                
                Выбранные файлы:
                """);
        t.put("merge_pdfs.chooser.save.title", "Сохранить PDF");
        t.put("merge_pdfs.chooser.save.approve", "Выбрать PDF для сохранения");
        t.put("merge_pdfs.chooser.input.file.title", "Добавить PDF");
        t.put("merge_pdfs.chooser.input.file.approve", "Добавить PDF");
        t.put("merge_pdfs.chooser.input.folder.title", "Добавить папку");
        t.put("merge_pdfs.chooser.input.folder.approve", "Добавить папку");
        t.put("merge_pdfs.invalid_pdf", "PDF-файл не выбран.");
        t.put("merge_pdfs.need_two_pdfs", "Выберите как минимум 2 PDF-файла");
        t.put("merge_pdfs.default_output_name", "Результат.pdf");
        t.put("merge_pdfs.no_config", "Без настроек");
        t.put("merge_pdfs.process.start", "Создание PDF из %d стр....");
        t.put("merge_pdfs.process.saving", "Сохранение PDF в: ");
        t.put("merge_pdfs.process.error_title", "Ошибка при создании PDF:");

        // split_pdf
        t.put("split_pdf.presentation", """
                ──────────────── РАЗДЕЛЕНИЕ PDF ────────────────
                
                Разделяет PDF-файл на несколько отдельных документов.
                Перед обработкой можно указать начальную и конечную страницы.
                
                Выберите PDF-файл или перетащите его в консоль.
                Вход:      PDF
                Выход:     PDF
                
                Выбранный файл:
                """);
        t.put("split_pdf.chooser.save.title", "Сохранить PDF");
        t.put("split_pdf.chooser.save.approve", "Выбрать PDF для сохранения");
        t.put("split_pdf.chooser.input.file.title", "Добавить PDF");
        t.put("split_pdf.chooser.input.file.approve", "Добавить PDF");
        t.put("split_pdf.need_pdf", "PDF-файл не выбран");
        t.put("split_pdf.default_output_name", "Результат.pdf");
        t.put("split_pdf.config_header", "Настройки PDF:");
        t.put("split_pdf.config_start_page", "Начальная стр. : %d");
        t.put("split_pdf.config_end_page", "Конечная стр.  : %d");
        t.put("split_pdf.process.start", "Разделение PDF...");
        t.put("split_pdf.process.saving", "Сохранение PDF: ");
        t.put("split_pdf.process.error_title", "Ошибка при разделении PDF:");

        // credits
        t.put("credits.presentation", """
                ──────────────── Single PDF Tool ────────────────
                
                Версия        : %s
                Разработчик   : Cristian González Santos
                Email         : cristiangonsan18@gmail.com
                Github        : https://github.com/CristianGonSan
                
                Используемые технологии:
                - Java 21
                - Apache PDFBox
                - JDeploy
                
                ISC License
                Copyright <2025> <Cristian González Santos>
                """);
        t.put("credits.select_operation_first", "Сначала выберите операцию");

        // view
        t.put("view.main.title", "Simple PDF Tool");
        t.put("view.operations.title", "Основные операции");
        t.put("view.operations.images_to_pdf", "Изображения → PDF");
        t.put("view.operations.pdf_to_images", "PDF → Изображения");
        t.put("view.operations.merge_pdfs", "Объединить PDF");
        t.put("view.operations.split_pdf", "Разделить PDF");
        t.put("view.operations.credits", "Авторы");
        t.put("view.sidebar.files.title", "Файлы");
        t.put("view.sidebar.actions.title", "Действия");
        t.put("view.sidebar.add_file", "Добавить файл");
        t.put("view.sidebar.add_folder", "Добавить папку");
        t.put("view.sidebar.clear", "Очистить список");
        t.put("view.sidebar.save", "Сохранить результат");
        t.put("view.console.title", "Консоль вывода");
        t.put("view.parameters.title", "Параметры");
        t.put("view.parameters.empty", "Нет параметров");
        t.put("view.parameters.images_to_pdf.orientation", "Ориентация страницы");
        t.put("view.parameters.images_to_pdf.size", "Размер страницы");
        t.put("view.parameters.images_to_pdf.margin", "Поля (миллиметры)");
        t.put("view.parameters.pdf_to_images.format", "Формат изображения:");
        t.put("view.parameters.pdf_to_images.dpi", "DPI:");
        t.put("view.parameters.split_pdf.start_page", "Начальная страница:");
        t.put("view.parameters.split_pdf.end_page", "Конечная страница:");
    }
}