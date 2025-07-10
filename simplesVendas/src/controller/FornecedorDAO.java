/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import jdbc.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Fornecedor;
import java.sql.ResultSet;

/**
 *
 * @author Lucas
 */
public class FornecedorDAO 
{
    private Connection con;
    
    public FornecedorDAO() {
        this.con = Conexao.conectar();
    }

    public void cadastrarFornecedor(Fornecedor fornecedor) 
    {
        try {

            String sql = "INSERT INTO tb_fornecedores (nome, cnpj, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setString(3, fornecedor.getEmail());
            stmt.setString(4, fornecedor.getTelefone());
            stmt.setString(5, fornecedor.getCelular());
            stmt.setString(6, fornecedor.getCep());
            stmt.setString(7, fornecedor.getEndereco());
            stmt.setInt(8, fornecedor.getNumero());
            stmt.setString(9, fornecedor.getComplemento());
            stmt.setString(10, fornecedor.getBairro());
            stmt.setString(11, fornecedor.getCidade());
            stmt.setString(12, fornecedor.getEstado());

            stmt.execute();
            JOptionPane.showMessageDialog(null, "Fornecedor cadastrado!");
            stmt.close();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
        }
    }
    
    
    public List<Fornecedor> obterFornecedores()
    {
        List<Fornecedor> fornecedores = new ArrayList<>();
        
        String sql = "SELECT id, nome, cnpj, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado FROM tb_fornecedores;";
        
        try
        {
            PreparedStatement comando = con.prepareStatement(sql);
            ResultSet fornecedoresBD = comando.executeQuery();
            
            while(fornecedoresBD.next())
            {
                Fornecedor fornecedor = new Fornecedor();

                fornecedor.setId(fornecedoresBD.getInt("id"));
                fornecedor.setNome(fornecedoresBD.getString("nome"));
                fornecedor.setCnpj(fornecedoresBD.getString("cnpj"));
                fornecedor.setEmail(fornecedoresBD.getString("email"));
                fornecedor.setTelefone(fornecedoresBD.getString("telefone"));
                fornecedor.setCelular(fornecedoresBD.getString("celular"));
                fornecedor.setCep(fornecedoresBD.getString("cep"));
                fornecedor.setEndereco(fornecedoresBD.getString("endereco"));
                fornecedor.setNumero(fornecedoresBD.getInt("numero"));
                fornecedor.setComplemento(fornecedoresBD.getString("complemento"));
                fornecedor.setBairro(fornecedoresBD.getString("bairro"));
                fornecedor.setCidade(fornecedoresBD.getString("cidade"));
                fornecedor.setEstado(fornecedoresBD.getString("estado"));
                
                fornecedores.add(fornecedor);
           }
        }
        catch(SQLException erro)
        {
            JOptionPane.showMessageDialog(null, "Erro ao obter fornecedores do BD: " + erro.getMessage());
            
            return null;
        }
        
        return fornecedores;
    }
    
        public List<Fornecedor> obterFornecedores(String nome)
    {
        List<Fornecedor> fornecedores = new ArrayList<>();
        
        nome = "%" + nome + "%";
        String sql = "SELECT id, nome, cnpj, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado FROM tb_fornecedores WHERE nome LIKE ?;";
        
        try
        {
            PreparedStatement comando = con.prepareStatement(sql);
            comando.setString(1, nome);
            ResultSet fornecedoresBD = comando.executeQuery();
            
            while(fornecedoresBD.next())
            {
                Fornecedor fornecedor = new Fornecedor();

                fornecedor.setId(fornecedoresBD.getInt("id"));
                fornecedor.setNome(fornecedoresBD.getString("nome"));
                fornecedor.setCnpj(fornecedoresBD.getString("cnpj"));
                fornecedor.setEmail(fornecedoresBD.getString("email"));
                fornecedor.setTelefone(fornecedoresBD.getString("telefone"));
                fornecedor.setCelular(fornecedoresBD.getString("celular"));
                fornecedor.setCep(fornecedoresBD.getString("cep"));
                fornecedor.setEndereco(fornecedoresBD.getString("endereco"));
                fornecedor.setNumero(fornecedoresBD.getInt("numero"));
                fornecedor.setComplemento(fornecedoresBD.getString("complemento"));
                fornecedor.setBairro(fornecedoresBD.getString("bairro"));
                fornecedor.setCidade(fornecedoresBD.getString("cidade"));
                fornecedor.setEstado(fornecedoresBD.getString("estado"));
                
                fornecedores.add(fornecedor);
           }
        }
        catch(SQLException erro)
        {
            JOptionPane.showMessageDialog(null, "Erro ao obter fornecedores do BD: " + erro.getMessage());
            
            return null;
        }
        catch(Exception erro)
        {
            JOptionPane.showMessageDialog(null, "Erro ao obter fornecedores do BD: " + erro.getMessage());
            
            return null;
        }
        
        return fornecedores;
    }
        
    public Fornecedor obterFornecedor(int id)
    {
        String sql = "SELECT nome, cnpj, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado FROM tb_fornecedores WHERE id=?;";
        
        Fornecedor fornecedor = new Fornecedor();
        try
        {
            PreparedStatement comando = con.prepareStatement(sql);
            comando.setInt(1, id);
            ResultSet fornecedoresBD = comando.executeQuery();
            
            while(fornecedoresBD.next())
            {
                fornecedor.setId(id);
                fornecedor.setNome(fornecedoresBD.getString("nome"));
                fornecedor.setCnpj(fornecedoresBD.getString("cnpj"));
                fornecedor.setEmail(fornecedoresBD.getString("email"));
                fornecedor.setTelefone(fornecedoresBD.getString("telefone"));
                fornecedor.setCelular(fornecedoresBD.getString("celular"));
                fornecedor.setCep(fornecedoresBD.getString("cep"));
                fornecedor.setEndereco(fornecedoresBD.getString("endereco"));
                fornecedor.setNumero(fornecedoresBD.getInt("numero"));
                fornecedor.setComplemento(fornecedoresBD.getString("complemento"));
                fornecedor.setBairro(fornecedoresBD.getString("bairro"));
                fornecedor.setCidade(fornecedoresBD.getString("cidade"));
                fornecedor.setEstado(fornecedoresBD.getString("estado"));
           }
        }
        catch(SQLException erro)
        {
            JOptionPane.showMessageDialog(null, "Erro ao obter fornecedores do BD: " + erro.getMessage());
            
            return null;
        }
        catch(Exception erro)
        {
            JOptionPane.showMessageDialog(null, "Erro ao obter fornecedores do BD: " + erro.getMessage());
            
            return null;
        }
        
        return fornecedor;
    }
    
    public boolean deletarFornecedor(Fornecedor fornecedor)
    {
        try
        {
            String sql = "DELETE FROM tb_fornecedores WHERE id = ?";
            
            PreparedStatement comando = con.prepareStatement(sql);
            comando.setInt(1, fornecedor.getId());
            comando.execute();
            comando.close();
            
            return true;
        }
        catch(SQLException erro)
        {
            JOptionPane.showMessageDialog(null, "Erro ao deletar fornecedor do BD: " + erro.getMessage());
            return false;
        }
    }
    
        public void alterarfornecedor(Fornecedor fornecedor) {
        try {
            String sql = "UPDATE tb_fornecedores SET nome=?, cnpj=?, email=?, telefone=?, celular=?, cep=?, endereco=?, numero=?, complemento=?, bairro=?, cidade=?, estado=? WHERE id =?";

            PreparedStatement comando = con.prepareStatement(sql);
            comando.setString(1, fornecedor.getNome());
            comando.setString(2, fornecedor.getCnpj());
            comando.setString(3, fornecedor.getEmail());
            comando.setString(4, fornecedor.getTelefone());
            comando.setString(5, fornecedor.getCelular());
            comando.setString(6, fornecedor.getCep());
            comando.setString(7, fornecedor.getEndereco());
            comando.setInt(8, fornecedor.getNumero());
            comando.setString(9, fornecedor.getComplemento());
            comando.setString(10, fornecedor.getBairro());
            comando.setString(11, fornecedor.getCidade());
            comando.setString(12, fornecedor.getEstado());

            comando.setInt(13, fornecedor.getId());
            
            comando.execute();
            comando.close();
            System.out.print("ID: " + fornecedor.getId());
            JOptionPane.showMessageDialog(null, "Fornecedor alterado com sucesso!");
        }
        catch (SQLException erro)
        {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
        }
        catch (Exception erro)
        {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
        }
    }
}
