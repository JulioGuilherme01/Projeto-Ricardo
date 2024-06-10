/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.swing.JFileChooser;


/**
 *
 * @author julio
 */
public class Modelo {
    public static void baixarModeloGabarito() {
        String caminhoOrigem = "src/Modelo/GabaritoLimpo.jpg";
        File arquivoOrigem = new File(caminhoOrigem);
        if (!arquivoOrigem.exists()) {
            System.out.println("Arquivo não encontrado: " + caminhoOrigem);
            return;
        }

        // Configuração do JFileChooser
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salvar Imagem Como");
        fileChooser.setSelectedFile(new File(arquivoOrigem.getName()));
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Imagens", "jpg", "jpeg", "png"));

        // Janela pro Usuário escolher onde salvar o gabaritomodelo
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File arquivoDestino = fileChooser.getSelectedFile();
            try {
                Files.copy(arquivoOrigem.toPath(), arquivoDestino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Imagem copiada com sucesso para " + arquivoDestino.getAbsolutePath());
            } catch (IOException e) {
                System.out.println("Erro ao baixar a imagem: " + e.getMessage());
            }
        } else {
            System.out.println("Nenhum caminho de destino selecionado.");
        }
    }
    
}
