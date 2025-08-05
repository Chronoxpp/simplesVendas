/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import jdbc.Conexao;
import model.Funcionario;

/**
 *
 * @author GERAL
 */
public class FuncionarioDAO {
    private Connection con;
    
    public FuncionarioDAO() {
        this.con = Conexao.conectar();
    }

    //Metodo cadastrarCliente
    public void cadastrarFuncionario(Funcionario funcionario) 
    {
        try {

            //1 passo  - criar o comando sql
            String sql = "INSERT INTO tb_funcionarios (nome,rg,cpf,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado,senha, cargo, nivel_acesso)  values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getRg());
            stmt.setString(3, funcionario.getCpf());
            stmt.setString(4, funcionario.getEmail());
            stmt.setString(5, funcionario.getTelefone());
            stmt.setString(6, funcionario.getCelular());
            stmt.setString(7, funcionario.getCep());
            stmt.setString(8, funcionario.getEndereco());
            stmt.setInt(9, Integer.parseInt(funcionario.getNumero()));
            stmt.setString(10, funcionario.getComplemento());
            stmt.setString(11, funcionario.getBairro());
            stmt.setString(12, funcionario.getCidade());
            stmt.setString(13, funcionario.getUf());
            stmt.setString(14, funcionario.getSenha());
            stmt.setString(15, funcionario.getCargo());
            stmt.setString(16, funcionario.getNivelAcesso());

            //3 passo - executar o comando sql
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Funcionario cadastrado!");
            stmt.close();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }
    }
    
    
    public List<Funcionario> obterFuncionarios()
    {
        List<Funcionario> funcionarios = new ArrayList<>();
        
        String sql = "SELECT id, nome, rg, cpf, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado, senha, cargo, nivel_acesso FROM tb_funcionarios;";
        
        try
        {
            PreparedStatement comando = con.prepareStatement(sql);
            ResultSet funcionariosBD = comando.executeQuery();
            
            while(funcionariosBD.next())
            {
                Funcionario funcionario = new Funcionario();

                funcionario.setId(funcionariosBD.getInt("id"));
                funcionario.setNome(funcionariosBD.getString("nome"));
                funcionario.setRg(funcionariosBD.getString("rg"));
                funcionario.setCpf(funcionariosBD.getString("cpf"));
                funcionario.setEmail(funcionariosBD.getString("email"));
                funcionario.setTelefone(funcionariosBD.getString("telefone"));
                funcionario.setCelular(funcionariosBD.getString("celular"));
                funcionario.setCep(funcionariosBD.getString("cep"));
                funcionario.setEndereco(funcionariosBD.getString("endereco"));
                funcionario.setNumero(String.valueOf(funcionariosBD.getInt("numero")));
                funcionario.setComplemento(funcionariosBD.getString("complemento"));
                funcionario.setBairro(funcionariosBD.getString("bairro"));
                funcionario.setCidade(funcionariosBD.getString("cidade"));
                funcionario.setUf(funcionariosBD.getString("estado"));
                funcionario.setSenha(funcionariosBD.getString("senha"));
                funcionario.setCargo(funcionariosBD.getString("cargo"));
                funcionario.setNivelAcesso(funcionariosBD.getString("nivel_acesso"));
                
                funcionarios.add(funcionario);
           }
        }
        catch(SQLException erro)
        {
            JOptionPane.showMessageDialog(null, "Erro ao obter funcionarios no BD: " + erro.getMessage());
            
            return null;
        }
        
        return funcionarios;
    }
    
       public List<Funcionario> obterFuncionarios(String nome)
    {
        List<Funcionario> funcionarios = new ArrayList<>();
        
        nome = "%" + nome + "%";
        String sql = "SELECT id, nome, rg, cpf, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado, senha, cargo, nivel_acesso FROM tb_funcionarios WHERE nome LIKE ?;";
        
        try
        {
            PreparedStatement comando = con.prepareStatement(sql);
            comando.setString(1, nome);
            ResultSet funcionariosBD = comando.executeQuery();
            
            while(funcionariosBD.next())
            {
                Funcionario funcionario = new Funcionario();

                funcionario.setId(funcionariosBD.getInt("id"));
                funcionario.setNome(funcionariosBD.getString("nome"));
                funcionario.setRg(funcionariosBD.getString("rg"));
                funcionario.setCpf(funcionariosBD.getString("cpf"));
                funcionario.setEmail(funcionariosBD.getString("email"));
                funcionario.setTelefone(funcionariosBD.getString("telefone"));
                funcionario.setCelular(funcionariosBD.getString("celular"));
                funcionario.setCep(funcionariosBD.getString("cep"));
                funcionario.setEndereco(funcionariosBD.getString("endereco"));
                funcionario.setNumero(String.valueOf(funcionariosBD.getInt("numero")));
                funcionario.setComplemento(funcionariosBD.getString("complemento"));
                funcionario.setBairro(funcionariosBD.getString("bairro"));
                funcionario.setCidade(funcionariosBD.getString("cidade"));
                funcionario.setUf(funcionariosBD.getString("estado"));
                funcionario.setSenha(funcionariosBD.getString("senha"));
                funcionario.setCargo(funcionariosBD.getString("cargo"));
                funcionario.setNivelAcesso(funcionariosBD.getString("nivel_acesso"));
                
                funcionarios.add(funcionario);
           }
        }
        catch(SQLException erro)
        {
            JOptionPane.showMessageDialog(null, "Erro ao obter funcionarios no BD: " + erro.getMessage());
            
            return null;
        }
        
        return funcionarios;
    }
    
    public boolean deletarFuncionario(Funcionario funcionario)
    {
        try
        {
            String sql = "DELETE FROM tb_funcionarios WHERE id = ?";
            
            PreparedStatement comando = con.prepareStatement(sql);
            comando.setInt(1, funcionario.getId());
            comando.execute();
            comando.close();
            
            return true;
        }
        catch(SQLException erro)
        {
            JOptionPane.showMessageDialog(null, "Erro ao deletar funcionario do BD: " + erro.getMessage());
            return false;
        }
    }
    
        public void alterarFuncionario(Funcionario funcionario) {
        try {
            String sql = "UPDATE tb_funcionarios SET nome=?, rg=?, cpf=?, email=?, telefone=?, celular=?, cep=?, endereco=?, numero=?,complemento=?,bairro=?,cidade=?, estado=?, senha=?, cargo=?, nivel_acesso=?  WHERE id =?";

            PreparedStatement comando = con.prepareStatement(sql);
            comando.setString(1, funcionario.getNome());
            comando.setString(2, funcionario.getRg());
            comando.setString(3, funcionario.getCpf());
            comando.setString(4, funcionario.getEmail());
            comando.setString(5, funcionario.getTelefone());
            comando.setString(6, funcionario.getCelular());
            comando.setString(7, funcionario.getCep());
            comando.setString(8, funcionario.getEndereco());
            comando.setInt(9, Integer.parseInt(funcionario.getNumero()));
            comando.setString(10, funcionario.getComplemento());
            comando.setString(11, funcionario.getBairro());
            comando.setString(12, funcionario.getCidade());
            comando.setString(13, funcionario.getUf());
            comando.setString(14, funcionario.getSenha());
            comando.setString(15, funcionario.getCargo());
            comando.setString(16, funcionario.getNivelAcesso());

            comando.setInt(17, funcionario.getId());
            
            comando.execute();
            comando.close();
            System.out.print("ID: " + funcionario.getId());
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
