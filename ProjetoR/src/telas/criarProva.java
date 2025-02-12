/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package telas;

import app.controller.ProvaController;
import app.model.Prova;

import javax.swing.*;

import java.awt.*;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import app.util.ConexaoBanco;
import java.sql.SQLException;



/**
 *
 * @author fabio
 */
public class criarProva extends JFrame {

    /**
     * Creates new form criarProva
     */
    public criarProva() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        voltar = new javax.swing.JButton();
        nomeProva = new javax.swing.JTextField();
        EnviarProva = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Criação de Prova");

        jLabel2.setText("Nome da Prova:");

        voltar.setBackground(new java.awt.Color(51, 153, 255));
        voltar.setForeground(new java.awt.Color(255, 255, 255));
        voltar.setText("Voltar");

        EnviarProva.setBackground(new java.awt.Color(51, 153, 255));
        EnviarProva.setForeground(new java.awt.Color(255, 255, 255));
        EnviarProva.setText("Confirmar");
        EnviarProva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnviarProvaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(voltar)
                            .addGap(157, 157, 157)
                            .addComponent(EnviarProva))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(59, 59, 59)
                            .addComponent(jLabel2)
                            .addGap(79, 79, 79)
                            .addComponent(nomeProva, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(jLabel1)))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2))
                    .addComponent(nomeProva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(voltar)
                    .addComponent(EnviarProva))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EnviarProvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnviarProvaActionPerformed
        // TODO add your handling code here:
        String nome=nomeProva.getText();
        ConexaoBanco conexaoBanco = new ConexaoBanco();
        Connection conexao = conexaoBanco.abrirConexão();
        ProvaController provaController = new ProvaController(conexao);
        Prova prova = new Prova( nome);
        
        try {
            provaController.addProva(prova);
            JOptionPane.showMessageDialog(null, "Prova enviada com sucesso!");
            // Limpar campos após o cadastro
            nomeProva.setText("");
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                JOptionPane.showMessageDialog(null, "Prova já enviada. Por favor, insira uma prova diferente.");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao conectar ou inserir dados: " + e.getMessage());
            }
        } finally {
            conexaoBanco.fecharConexão();
        }
        comparaProva comp = new comparaProva();
        comp.setVisible(true);
        this.dispose(); 
    }//GEN-LAST:event_EnviarProvaActionPerformed
    /*    */
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(criarProva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(criarProva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(criarProva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(criarProva.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new criarProva().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EnviarProva;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField nomeProva;
    private javax.swing.JButton voltar;
    // End of variables declaration//GEN-END:variables
}
