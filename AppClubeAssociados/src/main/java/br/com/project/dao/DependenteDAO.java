/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.project.dao;

import br.com.project.model.Associado;
import br.com.project.model.Dependente;
import br.com.project.model.Mensalidade;
import br.com.project.model.enumeration.EnumParentesco;
import br.com.project.model.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Marcos André
 * @since 24/11/2020
 */
public class DependenteDAO implements GenericsDAO<Dependente, Integer> {

    @Override
    public void inserir(Dependente obj) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "INSERT INTO dependente (id_responsavel,parentesco,nome,idade,cpf,data_nascimento,rg,telefone,endereco) VALUES "
                + "(?,?,?,?,?,?,?,?,?)";

        PreparedStatement pst = conexao.prepareStatement(sql);

        pst.setInt(1, obj.getResponsavel().getId());
        pst.setString(2, obj.getParente().toString());
        pst.setString(3, obj.getNome());
        pst.setInt(4, obj.getIdade());
        pst.setString(5, obj.getCpf());
        pst.setObject(6, obj.getDataNascimento());
        pst.setString(7, obj.getRg());
        pst.setString(8, obj.getTelefone());
        pst.setString(9, obj.getEndereco());

        pst.executeUpdate();
    }

    @Override
    public void alterar(Dependente obj) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "UPDATE dependente "
                + "SET parentesco = ?, "
                + "nome = ?, "
                + "cpf = ?, "
                + "data_nascimento = ?, "
                + "rg = ?, "
                + "telefone = ?, "
                + "endereco = ? "
                + "WHERE id = ?;";

        PreparedStatement pst = conexao.prepareStatement(sql);

        pst.setString(1, obj.getParente().toString());
        pst.setString(2, obj.getNome());
        pst.setString(3, obj.getCpf());
        pst.setObject(4, obj.getDataNascimento());
        pst.setString(5, obj.getRg());
        pst.setString(6, obj.getTelefone());
        pst.setString(7, obj.getEndereco());
        pst.setInt(8, obj.getId());

        pst.executeUpdate();
    }

    @Override
    public void apagar(Dependente obj) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "DELETE FROM dependente "
                + "WHERE id = ?;";

        PreparedStatement pst = conexao.prepareStatement(sql);

        pst.setInt(1, obj.getId());

        pst.executeUpdate();
    }

    @Override
    public Dependente buscarPelaChave(Integer key) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM dependente d "
                + "INNER JOIN associado a ON (d.id_responsavel = a.id) "
                + "WHERE d.id = ?;";

        PreparedStatement pst = conexao.prepareStatement(sql);

        Dependente d = null;
        
        pst.setInt(1, key);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {

            d = new Dependente();
            Associado a = new Associado();

            Mensalidade mensalidade = new Mensalidade();
            mensalidade.setValor(rs.getDouble("valor_mensalidade"));
            
            a.setId(rs.getInt("id_responsavel"));
            a.setMensalidade(mensalidade);
            a.setBanco(rs.getString("a.banco"));
            a.setAgencia(rs.getInt("a.agencia"));
            a.setConta(rs.getInt("a.conta"));
            a.setTelefone(rs.getString("a.telefone"));
            a.setEndereco(rs.getString("a.endereco"));
            a.setRg(rs.getString("a.rg"));
            a.setCpf(rs.getString("a.cpf"));
            a.setNome(rs.getString("a.nome"));
            a.setDataNascimento(rs.getDate("a.data_nascimento"));
            
            
            d.setIdade(rs.getInt("d.idade"));
            d.setId(rs.getInt("d.id"));
            d.setResponsavel(a);
            d.setParente(EnumParentesco.valueOf(EnumParentesco.class, rs.getString("d.parentesco")));
            d.setNome(rs.getString("d.nome"));
            d.setCpf(rs.getString("d.cpf"));
            d.setDataNascimento(rs.getDate("d.data_nascimento"));
            d.setRg(rs.getString("d.rg"));
            d.setTelefone(rs.getString("d.telefone"));
            d.setEndereco(rs.getString("d.endereco"));
        }

        return d;
    }

    @Override
    public ArrayList<Dependente> buscarTodos() throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM dependente d INNER JOIN associado a ON (d.id_responsavel = a.id);";

        PreparedStatement pst = conexao.prepareStatement(sql);

        ArrayList<Dependente> lista = new ArrayList<>();

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            Dependente d = new Dependente();
            Associado a = new Associado();

            Mensalidade mensalidade = new Mensalidade();
            mensalidade.setValor(rs.getDouble("valor_mensalidade"));

            a.setMensalidade(mensalidade);
            a.setBanco(rs.getString("a.banco"));
            a.setAgencia(rs.getInt("a.agencia"));
            a.setConta(rs.getInt("a.conta"));
            a.setTelefone(rs.getString("a.telefone"));
            a.setEndereco(rs.getString("a.endereco"));
            a.setRg(rs.getString("a.rg"));
            a.setCpf(rs.getString("a.cpf"));
            a.setNome(rs.getString("a.nome"));
            a.setDataNascimento(rs.getDate("a.data_nascimento"));
            a.setId(rs.getInt("id_responsavel"));

            d.setIdade(rs.getInt("d.idade"));
            d.setId(rs.getInt("d.id"));
            d.setResponsavel(a);
            d.setParente(EnumParentesco.valueOf(EnumParentesco.class, rs.getString("d.parentesco")));
            d.setNome(rs.getString("d.nome"));
            d.setCpf(rs.getString("d.cpf"));
            d.setDataNascimento(rs.getDate("d.data_nascimento"));
            d.setRg(rs.getString("d.rg"));
            d.setTelefone(rs.getString("d.telefone"));
            d.setEndereco(rs.getString("d.endereco"));
            
            lista.add(d);
        }
        
        return lista;
    }
    
     public ArrayList<Dependente> buscarPeloNome(String nome) throws SQLException, ClassNotFoundException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "SELECT * from dependente d "
                + "INNER JOIN associado a ON (d.id_responsavel = a.id)"
                + "WHERE d.nome like ?;";

        PreparedStatement pst = conexao.prepareStatement(sql);

        ArrayList<Dependente> lista = new ArrayList<>();
        pst.setString(1, "%" + nome + "%");

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            Dependente d = new Dependente();
            Associado a = new Associado();
            
            Mensalidade mensalidade = new Mensalidade();
            mensalidade.setValor(rs.getDouble("a.valor_mensalidade"));

            a.setMensalidade(mensalidade);
            a.setBanco(rs.getString("a.banco"));
            a.setAgencia(rs.getInt("a.agencia"));
            a.setConta(rs.getInt("a.conta"));
            a.setTelefone(rs.getString("a.telefone"));
            a.setEndereco(rs.getString("a.endereco"));
            a.setRg(rs.getString("a.rg"));
            a.setCpf(rs.getString("a.cpf"));
            a.setNome(rs.getString("a.nome"));
            a.setDataNascimento(rs.getDate("a.data_nascimento"));
            a.setId(rs.getInt("id_responsavel"));

            d.setIdade(rs.getInt("d.idade"));
            d.setId(rs.getInt("d.id"));
            d.setResponsavel(a);
            d.setParente(EnumParentesco.valueOf(EnumParentesco.class, rs.getString("d.parentesco")));
            d.setNome(rs.getString("d.nome"));
            d.setCpf(rs.getString("d.cpf"));
            d.setDataNascimento(rs.getDate("d.data_nascimento"));
            d.setRg(rs.getString("d.rg"));
            d.setTelefone(rs.getString("d.telefone"));
            d.setEndereco(rs.getString("d.endereco"));
            

            lista.add(d);
        }

        return lista;
    }


}
