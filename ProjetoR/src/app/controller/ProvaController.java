package app.controller;

import app.model.Prova;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import telas.criarProva;

public class ProvaController extends criarProva {
    Connection conexao;
    ResultSet rs = null;

    // Construtor
    
    public ProvaController(Connection conexao) {
        this.conexao = conexao;
    }
    public void addProva(Prova prova) throws SQLException {
        String sql = "INSERT INTO Prova (nome_prova) VALUES (?)";
        PreparedStatement ps = null;
        try {
            ps = this.conexao.prepareStatement(sql);
            ps.setString(1, prova.getNome());

            ps.executeUpdate();
            System.out.println("Dados inseridos com sucesso.");
        } catch (SQLException e) {
            if (e.getSQLState().equals("23000")) { // Código de erro de chave duplicada no MySQL
                throw new SQLException("Erro: Nome duplicado.", e);
            } else {
                throw e;
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public void enviarNota(String nome, int nota) {
        try {
            String sql = "UPDATE prova SET nota= ? where nome_prova= ?;";
            PreparedStatement ps;
            ps = conexao.prepareStatement(sql);
            ps.setInt(1, nota);
            ps.setString(2, nome);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Nota inseridos com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ou inserir dados: " + e.getMessage());
        }
    }

    public ArrayList<Prova> ListaProva() {
        ArrayList<Prova> provas = new ArrayList<>();
        String sql = "SELECT * FROM prova";
        try {
            Statement declaracao = conexao.createStatement();
            ResultSet resposta = declaracao.executeQuery(sql);

            while (resposta.next()) {
                int id = resposta.getInt("id");
                String nome = resposta.getString("nome_prova");
                int nota = resposta.getInt("nota");

                Prova prova = new Prova(id, nome, nota);
                provas.add(prova);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return provas;
    }

    public void apagarTudo() {
        String sql = "DELETE FROM Prova";
        PreparedStatement ps;
        try {
            ps = this.conexao.prepareStatement(sql);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

