package app.controller;

import app.model.Usuario;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import telas.login;
import telas.criarProva;


public class UsuarioController extends login {
    Connection conexao;
    ResultSet rs = null;

    // Construtor
    public UsuarioController(Connection conexao) {
        this.conexao = conexao;
    }

    // Método para adicionar um novo usuário
    public void addUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (nome, senha, RGM) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = this.conexao.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSenha());
            ps.setInt(3, usuario.getRGM());

            ps.executeUpdate();
            System.out.println("Dados inseridos com sucesso.");
        } catch (SQLException e) {
            if (e.getSQLState().equals("23000")) { // Código de erro de chave duplicada no MySQL
                throw new SQLException("Erro: RGM duplicado.", e);
            } else {
                throw e;
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public void logar(int RGM, String senha) {
        try {
            String sql = "SELECT * FROM usuario WHERE RGM = ? AND senha = ?";
            PreparedStatement ps;
            ps = conexao.prepareStatement(sql);
            ps.setInt(1, RGM);
            ps.setString(2, senha);
            rs = ps.executeQuery();

            if (rs.next()) {
                String nomeUsuario = rs.getString("nome");
                JOptionPane.showMessageDialog(null, "Acesso Liberado");
                JOptionPane.showMessageDialog(null, "Bem vindo " + nomeUsuario);
                criarProva prov = new  criarProva();
                prov.setVisible(true);
                
            } else {
                JOptionPane.showMessageDialog(null, "Acesso Negado");
                JOptionPane.showMessageDialog(null, "Usuario e/ou Senha esta(ão) errado(s) ou não esta(ão) registrado(s)");
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ou inserir dados: " + e.getMessage());
        }
    }

    public ArrayList<Usuario> ListaUsuario() {
        ArrayList<Usuario> Usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        try {
            Statement declaracao = conexao.createStatement();
            ResultSet resposta = declaracao.executeQuery(sql);

            while (resposta.next()) {
                int id = resposta.getInt("id");
                int RGM = resposta.getInt("RGM");
                String nome = resposta.getString("nome");
                String senha = resposta.getString("senha");

                Usuario usuario = new Usuario(id, RGM, nome, senha);
                Usuarios.add(usuario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Usuarios;
    }

    public void apagarTudo() {
        String sql = "DELETE FROM usuario";
        PreparedStatement ps;
        try {
            ps = this.conexao.prepareStatement(sql);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
