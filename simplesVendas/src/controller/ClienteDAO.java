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
import model.Cliente;
import java.sql.ResultSet;


/**
 *
 * @author TADS
 */
public class ClienteDAO {
    private Connection con;
    
    public ClienteDAO() {
        this.con = Conexao.conectar();
    }

    //Metodo cadastrarCliente
    public void cadastrarCliente(Cliente obj) 
    {
        try {

            //1 passo  - criar o comando sql
            String sql = "insert into tb_clientes (nome,rg,cpf,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado)  values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getTelefone());
            stmt.setString(6, obj.getCelular());
            stmt.setString(7, obj.getCep());
            stmt.setString(8, obj.getEndereco());
            stmt.setInt(9, Integer.parseInt(obj.getNumero()));
            stmt.setString(10, obj.getComplemento());
            stmt.setString(11, obj.getBairro());
            stmt.setString(12, obj.getCidade());
            stmt.setString(13, obj.getUf());

            //3 passo - executar o comando sql
            stmt.execute();
            JOptionPane.showConfirmDialog(null, "Cliente cadastrado!");
            stmt.close();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }
    }
    
    
    public List<Cliente> obterClientes()
    {
        List<Cliente> clientes = new ArrayList<>();
        
        String sql = "SELECT id, nome, rg, cpf, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado FROM tb_clientes;";
        
        try
        {
            PreparedStatement comando = con.prepareStatement(sql);
            ResultSet clientesBD = comando.executeQuery();
            
            while(clientesBD.next())
            {
                Cliente cliente = new Cliente();

                cliente.setId(clientesBD.getInt("id"));
                cliente.setNome(clientesBD.getString("nome"));
                cliente.setRg(clientesBD.getString("rg"));
                cliente.setCpf(clientesBD.getString("cpf"));
                cliente.setEmail(clientesBD.getString("email"));
                cliente.setTelefone(clientesBD.getString("telefone"));
                cliente.setCelular(clientesBD.getString("celular"));
                cliente.setCep(clientesBD.getString("cep"));
                cliente.setEndereco(clientesBD.getString("endereco"));
                cliente.setNumero(String.valueOf(clientesBD.getInt("numero")));
                cliente.setComplemento(clientesBD.getString("complemento"));
                cliente.setBairro(clientesBD.getString("bairro"));
                cliente.setCidade(clientesBD.getString("cidade"));
                cliente.setUf(clientesBD.getString("estado"));
                
                clientes.add(cliente);
           }
        }
        catch(SQLException erro)
        {
            JOptionPane.showMessageDialog(null, "Erro ao obter clientes no BD: " + erro.getMessage());
            
            return null;
        }
        
        return clientes;
    }
    
        public List<Cliente> obterClientes(String nome)
    {
        List<Cliente> clientes = new ArrayList<>();
        
        nome = "%" + nome + "%";
        String sql = "SELECT id, nome, rg, cpf, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado FROM tb_clientes WHERE nome LIKE ?;";
        
        try
        {
            PreparedStatement comando = con.prepareStatement(sql);
            comando.setString(1, nome);
            ResultSet clientesBD = comando.executeQuery();
            
            while(clientesBD.next())
            {
                Cliente cliente = new Cliente();

                cliente.setId(clientesBD.getInt("id"));
                cliente.setNome(clientesBD.getString("nome"));
                cliente.setRg(clientesBD.getString("rg"));
                cliente.setCpf(clientesBD.getString("cpf"));
                cliente.setEmail(clientesBD.getString("email"));
                cliente.setTelefone(clientesBD.getString("telefone"));
                cliente.setCelular(clientesBD.getString("celular"));
                cliente.setCep(clientesBD.getString("cep"));
                cliente.setEndereco(clientesBD.getString("endereco"));
                cliente.setNumero(String.valueOf(clientesBD.getInt("numero")));
                cliente.setComplemento(clientesBD.getString("complemento"));
                cliente.setBairro(clientesBD.getString("bairro"));
                cliente.setCidade(clientesBD.getString("cidade"));
                cliente.setUf(clientesBD.getString("estado"));
                
                clientes.add(cliente);
           }
        }
        catch(SQLException erro)
        {
            JOptionPane.showMessageDialog(null, "Erro ao obter clientes no BD: " + erro.getMessage());
            
            return null;
        }
        
        return clientes;
    }
    
    public boolean deletarCliente(Cliente cliente)
    {
        try
        {
            String sql = "DELETE FROM tb_clientes WHERE id = ?";
            
            PreparedStatement comando = con.prepareStatement(sql);
            comando.setInt(1, cliente.getId());
            comando.execute();
            comando.close();
            
            return true;
        }
        catch(SQLException erro)
        {
            JOptionPane.showMessageDialog(null, "Erro ao deletar cliente do BD: " + erro.getMessage());
            return false;
        }
    }
    
        public void alterarCliente(Cliente cliente) {
        try {
            String sql = "update tb_clientes set nome=?, rg=?, cpf=?, email=?, telefone=?, celular=?, cep=?, endereco=?, numero=?,complemento=?,bairro=?,cidade=?, estado=?  where id =?";

            PreparedStatement comando = con.prepareStatement(sql);
            comando.setString(1, cliente.getNome());
            comando.setString(2, cliente.getRg());
            comando.setString(3, cliente.getCpf());
            comando.setString(4, cliente.getEmail());
            comando.setString(5, cliente.getTelefone());
            comando.setString(6, cliente.getCelular());
            comando.setString(7, cliente.getCep());
            comando.setString(8, cliente.getEndereco());
            comando.setInt(9, Integer.parseInt(cliente.getNumero()));
            comando.setString(10, cliente.getComplemento());
            comando.setString(11, cliente.getBairro());
            comando.setString(12, cliente.getCidade());
            comando.setString(13, cliente.getUf());

            comando.setInt(14, cliente.getId());

            comando.execute();
            comando.close();

            JOptionPane.showMessageDialog(null, "Alterado com Sucesso!");

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
