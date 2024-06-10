package app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexaoBanco {
    private Connection conexao;
    
    public Connection abrirConexão(){
        try {
            String url = "jdbc:mysql://localhost:3306/examinerdb";
            String user = "root";
            String password = "admin";
            
            conexao = DriverManager.getConnection(url, user, password);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conexao;
    }
    
    public void fecharConexão(){
        try {
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

