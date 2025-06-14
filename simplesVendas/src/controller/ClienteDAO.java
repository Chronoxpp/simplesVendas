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
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }
    }
    
    
    public List<Cliente> obterClientes()
    {
        List<Cliente> clientes = new ArrayList<>();
        
        String sql = "SELECT id, nome, rg, cpf, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado FROM tb_clientes";
        
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
}
