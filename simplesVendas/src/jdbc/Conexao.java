/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author TADS
 */
public class Conexao {    
    public static void main(String[] args)
    {
        Connection minhaConexao = conectar();
    }
    
    public static Connection conectar()
    {
        Connection conexao = null;
        
        String driver = "com.mysql.cj.jdbc.Driver";
        
        String url = "jdbc:mysql://localhost:3306/bdvendas?useTimezone=true&serverTimezone=UTC";
        String user = "root";
        String senha = "root";
        
        try
        {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, senha);
            return conexao;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Ops, algo deu errado: " + e.getMessage());
            return null;
        }
    }
    
    public static Connection desconectar() {
        Connection conexao = null;
        try {
            conexao.close();
        } catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, e);
        }

        return conexao;
    }
}
