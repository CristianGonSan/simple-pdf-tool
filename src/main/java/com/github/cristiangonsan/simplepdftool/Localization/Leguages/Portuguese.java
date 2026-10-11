package com.github.cristiangonsan.simplepdftool.Localization.Leguages;

import java.util.Map;

// Portuguese.java
public class Portuguese extends Language {
    @Override
    public String code() {
        return "pt";
    }

    @Override
    protected void define(Map<String, String> t) {
        // common
        t.put("common.invalid_file", "Arquivo inválido.");
        t.put("common.folder_creation_error", "Não foi possível criar a pasta: %s");
        t.put("common.process_in_progress", "Há um processo em andamento. Por favor, aguarde...");

        // dialog
        t.put("dialog.overwrite.title", "Substituir");
        t.put("dialog.overwrite.message", "Este arquivo já existe. Deseja substituí-lo?");
        t.put("dialog.error.title", "Erro");

        // process
        t.put("process.finished", "Processo concluído");
        t.put("process.error", "Erro: %s");

        // single
        t.put("single.invalid_pdf", "Nenhum PDF foi selecionado.");
        t.put("single.folder_not_supported", "> Pasta adicionada: %s\\\nEsta operação não admite pastas\n");
        t.put("single.pdf_selected", "> PDF selecionado: %s\n");
        t.put("single.new_pdf_selected", "> Novo PDF selecionado: %s\n");

        // multi
        t.put("multi.invalid_folder", "Nenhuma pasta foi selecionada.");
        t.put("multi.file_added", "> %s\n");
        t.put("multi.folder_header", "> Pasta adicionada: %s\\\n");
        t.put("multi.folder_empty", "  - Nenhum arquivo válido foi encontrado na pasta\n");
        t.put("multi.folder_file_item", "  - %s\n");

        // main
        t.put("main.presentation", """
                ───────────── Simple PDF Tool v%s ─────────────
                
                Utilitário simples e prático para trabalhar com arquivos PDF.
                Escolha uma das seguintes operações:
                
                - Converter várias imagens em um único PDF (JPG, PNG ou WEBP).
                - Exportar cada página de um PDF como imagem.
                - Unir vários arquivos PDF em um único documento.
                - Extrair um intervalo de páginas e gerar um novo PDF.
                
                """);
        t.put("main.window.closing.title", "Finalizar processo");
        t.put("main.window.closing.message", "Há um processo em andamento. Deseja sair?");
        t.put("main.drop.error", "Não foi possível processar o item arrastado: %s");

        // images_to_pdf
        t.put("images_to_pdf.presentation", """
                ──────────────── UNIR IMAGENS EM PDF ────────────────
                
                Cria um arquivo PDF a partir de várias imagens.
                
                Selecione as imagens, uma pasta que as contenha
                ou arraste-as diretamente para o console.
                Entrada:    JPG, PNG ou WEBP
                Saída:      PDF
                
                Arquivos selecionados:
                """);
        t.put("images_to_pdf.chooser.save.title", "Salvar PDF");
        t.put("images_to_pdf.chooser.save.approve", "Selecionar PDF para salvar");
        t.put("images_to_pdf.chooser.input.file.title", "Adicionar imagem");
        t.put("images_to_pdf.chooser.input.file.approve", "Adicionar imagem");
        t.put("images_to_pdf.chooser.input.folder.title", "Adicionar pasta");
        t.put("images_to_pdf.chooser.input.folder.approve", "Adicionar pasta");
        t.put("images_to_pdf.invalid_image", "Nenhuma imagem válida foi selecionada.");
        t.put("images_to_pdf.need_one_image", "Selecione pelo menos uma imagem");
        t.put("images_to_pdf.default_output_name", "Resultado.pdf");
        t.put("images_to_pdf.config_header", "Configuração do PDF:");
        t.put("images_to_pdf.config_size_only", "Tamanho: %s");
        t.put("images_to_pdf.config_orientation", "Orientação  : %s");
        t.put("images_to_pdf.config_size", "Tamanho     : %s");
        t.put("images_to_pdf.config_margin", "Margem (mm)  : %s");
        t.put("images_to_pdf.process.start", "Criando PDF com %d páginas...");
        t.put("images_to_pdf.process.saving", "Salvando PDF em: ");
        t.put("images_to_pdf.process.error_title", "Erro ao criar o PDF:");

        // pdf_to_images
        t.put("pdf_to_images.presentation", """
                ─────────────── CONVERTER PDF EM IMAGENS ───────────────
                
                Gera uma imagem para cada página do arquivo PDF.
                Você pode ajustar o formato de saída e a resolução (DPI)
                antes de iniciar.
                
                Selecione o arquivo PDF ou arraste-o para o console.
                Entrada:    PDF
                Saída:      JPG ou PNG
                
                Arquivo selecionado:
                """);
        t.put("pdf_to_images.chooser.save.title", "Salvar imagens");
        t.put("pdf_to_images.chooser.save.approve", "Selecionar pasta para salvar");
        t.put("pdf_to_images.chooser.input.file.title", "Adicionar PDF");
        t.put("pdf_to_images.chooser.input.file.approve", "Adicionar PDF");
        t.put("pdf_to_images.need_pdf", "Nenhum PDF selecionado");
        t.put("pdf_to_images.config_header", "Configuração das imagens:");
        t.put("pdf_to_images.config_format", "Formato : %s");
        t.put("pdf_to_images.config_dpi", "DPI     : %d");
        t.put("pdf_to_images.process.start", "Exportando PDF para %d imagens...");
        t.put("pdf_to_images.process.saving", "Salvando imagens em: ");
        t.put("pdf_to_images.process.error_title", "Erro ao exportar o PDF:");

        // merge_pdfs
        t.put("merge_pdfs.presentation", """
                ──────────────── UNIR VÁRIOS PDFs ────────────────
                
                Mescla vários arquivos PDF em um único documento.
                A ordem de seleção definirá a ordem final das páginas.
                
                Selecione os arquivos PDF ou arraste-os para o console.
                Entrada:    PDF
                Saída:      PDF
                
                Arquivos selecionados:
                """);
        t.put("merge_pdfs.chooser.save.title", "Salvar PDF");
        t.put("merge_pdfs.chooser.save.approve", "Selecionar PDF para salvar");
        t.put("merge_pdfs.chooser.input.file.title", "Adicionar PDF");
        t.put("merge_pdfs.chooser.input.file.approve", "Adicionar PDF");
        t.put("merge_pdfs.chooser.input.folder.title", "Adicionar pasta");
        t.put("merge_pdfs.chooser.input.folder.approve", "Adicionar pasta");
        t.put("merge_pdfs.invalid_pdf", "Nenhum PDF foi selecionado.");
        t.put("merge_pdfs.need_two_pdfs", "Selecione pelo menos 2 PDFs");
        t.put("merge_pdfs.default_output_name", "Resultado.pdf");
        t.put("merge_pdfs.no_config", "Sem configurações");
        t.put("merge_pdfs.process.start", "Criando PDF com %d página(s)...");
        t.put("merge_pdfs.process.saving", "Salvando PDF em: ");
        t.put("merge_pdfs.process.error_title", "Erro ao criar o PDF:");

        // split_pdf
        t.put("split_pdf.presentation", """
                ──────────────── DIVIDIR PDF ────────────────
                
                Divide um arquivo PDF em vários documentos distintos.
                Você pode indicar a página inicial e final antes de processar.
                
                Selecione o arquivo PDF ou arraste-o para o console.
                Entrada:    PDF
                Saída:      PDF
                
                Arquivo selecionado:
                """);
        t.put("split_pdf.chooser.save.title", "Salvar PDF");
        t.put("split_pdf.chooser.save.approve", "Selecionar PDF para salvar");
        t.put("split_pdf.chooser.input.file.title", "Adicionar PDF");
        t.put("split_pdf.chooser.input.file.approve", "Adicionar PDF");
        t.put("split_pdf.need_pdf", "Nenhum PDF selecionado");
        t.put("split_pdf.default_output_name", "Resultado.pdf");
        t.put("split_pdf.config_header", "Configuração do PDF:");
        t.put("split_pdf.config_start_page", "Pág. inicial : %d");
        t.put("split_pdf.config_end_page", "Pág. final   : %d");
        t.put("split_pdf.process.start", "Dividindo PDF...");
        t.put("split_pdf.process.saving", "Salvando PDF: ");
        t.put("split_pdf.process.error_title", "Erro ao dividir o PDF:");

        // credits
        t.put("credits.presentation", """
                ──────────────── Single PDF Tool ────────────────
                
                Versão: %s
                Desenvolvedor : Cristian González Santos
                Email         : cristiangonsan18@gmail.com
                Github        : https://github.com/CristianGonSan
                
                Tecnologias utilizadas:
                - Java 21
                - Apache PDFBox
                - JDeploy
                
                ISC License
                Copyright <2025> <Cristian González Santos>
                """);
        t.put("credits.select_operation_first", "Selecione uma operação primeiro");

        // view
        t.put("view.main.title", "Simple PDF Tool");
        t.put("view.operations.title", "Operações principais");
        t.put("view.operations.images_to_pdf", "Imagens → PDF");
        t.put("view.operations.pdf_to_images", "PDF → Imagens");
        t.put("view.operations.merge_pdfs", "Unir PDFs");
        t.put("view.operations.split_pdf", "Dividir PDF");
        t.put("view.operations.credits", "Créditos");
        t.put("view.sidebar.files.title", "Arquivos");
        t.put("view.sidebar.actions.title", "Ações");
        t.put("view.sidebar.add_file", "Adicionar arquivo");
        t.put("view.sidebar.add_folder", "Adicionar pasta");
        t.put("view.sidebar.clear", "Limpar lista");
        t.put("view.sidebar.save", "Salvar resultado");
        t.put("view.console.title", "Console de saída");
        t.put("view.parameters.title", "Parâmetros");
        t.put("view.parameters.empty", "Sem parâmetros");
        t.put("view.parameters.images_to_pdf.orientation", "Orientação da página");
        t.put("view.parameters.images_to_pdf.size", "Tamanho da página");
        t.put("view.parameters.images_to_pdf.margin", "Margem (milímetros)");
        t.put("view.parameters.pdf_to_images.format", "Formato da imagem:");
        t.put("view.parameters.pdf_to_images.dpi", "DPI:");
        t.put("view.parameters.split_pdf.start_page", "Página inicial:");
        t.put("view.parameters.split_pdf.end_page", "Página final:");
    }
}