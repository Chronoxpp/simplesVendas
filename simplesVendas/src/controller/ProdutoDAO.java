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
import model.Produto;
import model.Fornecedor;
import java.sql.ResultSet;

/**
 *
 * @author Lucas
 */
public class ProdutoDAO {
    private Connection con;
    
    public ProdutoDAO() {
        this.con = Conexao.conectar();
    }

    public void cadastrarProduto(Produto produto) 
    {
        try {

            String sql = "INSERT INTO tb_produtos (descricao, preco, qtd_estoque, for_id) VALUES (?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, produto.getDescricao());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getEstoque());
            stmt.setInt(4, produto.getFornecedor().getId());

            stmt.execute();
            JOptionPane.showMessageDialog(null, "Produto cadastrado!");
            stmt.close();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
        }
    }
    
    
    public List<Produto> obterProdutos()
    {
        List<Produto> produtos = new ArrayList<>();
        
        String sql = "SELECT id, descricao, preco, qtd_estoque, for_id FROM tb_produtos;";
        
        try
        {
            PreparedStatement comando = con.prepareStatement(sql);
            ResultSet produtosBD = comando.executeQuery();
            
            while(produtosBD.next())
            {
                Produto produto = new Produto();

                produto.setId(produtosBD.getInt("id"));
                produto.setDescricao(produtosBD.getString("descricao"));
                produto.setPreco(produtosBD.getDouble("preco"));
                produto.setEstoque(produtosBD.getInt("qtd_estoque"));
                FornecedorDAO fornecedorDao = new FornecedorDAO();
                Fornecedor fornecedor = fornecedorDao.obterFornecedor(produtosBD.getInt("for_id"));
                produto.setFornecedor(fornecedor);
                
                produtos.add(produto);
           }
        }
        catch(SQLException erro)
        {
            JOptionPane.showMessageDialog(null, "Erro ao obter produtos no BD: " + erro.getMessage());
            
            return null;
        }
        
        return produtos;
    }
    
        public List<Produto> obterProdutos(String descricao)
    {
        List<Produto> produtos = new ArrayList<>();
        
        descricao = "%" + descricao + "%";
        String sql = "SELECT id, descricao, preco, qtd_estoque, for_id FROM tb_produtos WHERE descricao LIKE ?;";
        
        try
        {
            PreparedStatement comando = con.prepareStatement(sql);
            comando.setString(1, descricao);
            ResultSet produtosBD = comando.executeQuery();
            
            while(produtosBD.next())
            {
                Produto produto = new Produto();

                produto.setId(produtosBD.getInt("id"));
                produto.setDescricao(produtosBD.getString("descricao"));
                produto.setPreco(produtosBD.getDouble("preco"));
                produto.setEstoque(produtosBD.getInt("qtd_estoque"));
                FornecedorDAO fornecedorDao = new FornecedorDAO();
                Fornecedor fornecedor = fornecedorDao.obterFornecedor(produtosBD.getInt("for_id"));
                produto.setFornecedor(fornecedor);
                
                produtos.add(produto);
           }
        }
        catch(SQLException erro)
        {
            JOptionPane.showMessageDialog(null, "Erro ao obter produtos no BD: " + erro.getMessage());
            
            return null;
        }
        catch(Exception erro)
        {
            JOptionPane.showMessageDialog(null, "Erro ao obter produtos no BD: " + erro.getMessage());
            
            return null;
        }
        
        return produtos;
    }
    
    public boolean deletarProduto(Produto produto)
    {
        try
        {
            String sql = "DELETE FROM tb_produtos WHERE id = ?";
            
            PreparedStatement comando = con.prepareStatement(sql);
            comando.setInt(1, produto.getId());
            comando.execute();
            comando.close();
            
            return true;
        }
        catch(SQLException erro)
        {
            JOptionPane.showMessageDialog(null, "Erro ao deletar produto do BD: " + erro.getMessage());
            return false;
        }
    }
    
        public void alterarProduto(Produto produto) {
        try {
            String sql = "UPDATE tb_produtos SET descricao=?, preco=?, qtd_estoque=?, for_id=? WHERE id =?";

            PreparedStatement comando = con.prepareStatement(sql);
            comando.setString(1, produto.getDescricao());
            comando.setDouble(2, produto.getPreco());
            comando.setInt(3, produto.getEstoque());
            comando.setInt(4, produto.getFornecedor().getId());

            comando.setInt(5, produto.getId());
            
            comando.execute();
            comando.close();
            System.out.print("ID: " + produto.getId());
            JOptionPane.showMessageDialog(null, "Produto alterado com sucesso!");
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
