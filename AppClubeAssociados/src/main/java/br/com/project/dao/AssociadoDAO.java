/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.project.dao;

import br.com.project.model.Associado;
import br.com.project.model.Clube;
import br.com.project.model.Mensalidade;
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
public class AssociadoDAO implements GenericsDAO<Associado, Integer> {

    @Override
    public void inserir(Associado obj) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "INSERT INTO associado (id_clube,nome,cpf,rg,telefone,endereco,data_nascimento,conta,agencia,valor_mensalidade,banco)VALUES "
                + "(?,?,?,?,?,?,?,?,?,?,?);";

        PreparedStatement pst = conexao.prepareStatement(sql);

        pst.setInt(1, obj.getClubeParticipante().getId());
        pst.setString(2, obj.getNome());
        pst.setString(3, obj.getCpf());
        pst.setString(4, obj.getRg());
        pst.setString(5, obj.getTelefone());
        pst.setString(6, obj.getEndereco());
        pst.setObject(7, obj.getDataNascimento());
        pst.setInt(8, obj.getConta());
        pst.setInt(9, obj.getAgencia());
        pst.setDouble(10, obj.getMensalidade().getValor());
        pst.setString(11, obj.getBanco());

        pst.executeUpdate();

    }

    @Override
    public void alterar(Associado obj) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "UPDATE associado "
                + "SET id_clube = ?, "
                + "nome = ?, "
                + "cpf = ?, "
                + "rg = ?, "
                + "telefone = ?, "
                + "endereco = ?, "
                + "data_nascimento = ?, "
                + "conta = ?, "
                + "agencia = ?, "
                + "valor_mensalidade = ?, "
                + "banco = ? "
                + "WHERE id = ?;";

        PreparedStatement pst = conexao.prepareStatement(sql);

        pst.setInt(1, obj.getClubeParticipante().getId());
        pst.setString(2, obj.getNome());
        pst.setString(3, obj.getCpf());
        pst.setString(4, obj.getRg());
        pst.setString(5, obj.getTelefone());
        pst.setString(6, obj.getEndereco());
         pst.setObject(7, obj.getDataNascimento());
        pst.setInt(8, obj.getConta());
        pst.setInt(9, obj.getAgencia());
        pst.setDouble(10, obj.getMensalidade().getValor());
        pst.setString(11, obj.getBanco());
        pst.setInt(12, obj.getId());
        
        pst.executeUpdate();
    }

    @Override
    public void apagar(Associado obj) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "DELETE FROM associado "
                + "WHERE id = ?;";

        PreparedStatement pst = conexao.prepareStatement(sql);

        pst.setInt(1, obj.getId());

        pst.executeUpdate();
    }

    @Override
    public Associado buscarPelaChave(Integer key) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM associado a "
                + "INNER JOIN clube cl ON (a.id_clube = cl.id) "
                + "WHERE a.id = ?";

        PreparedStatement pst = conexao.prepareStatement(sql);

        pst.setInt(1, key);

        ResultSet rs = pst.executeQuery();

        Associado a = null;

        while (rs.next()) {

            a = new Associado();
            Clube cl = new Clube();

            cl.setId(rs.getInt("id_clube"));
            cl.setNome("cl.nome");
            cl.setCnpj(rs.getString("cl.cnpj"));
            cl.setResponsavel(rs.getString("cl.responsavel"));
            cl.setLocalidade(rs.getString("cl.localidade"));

            Mensalidade mensalidade = new Mensalidade();
            mensalidade.setValor(rs.getDouble("valor_mensalidade"));

            a.setMensalidade(mensalidade);
            a.setId(rs.getInt("a.id"));
            a.setBanco(rs.getString("a.banco"));
            a.setAgencia(rs.getInt("a.agencia"));
            a.setConta(rs.getInt("a.conta"));
            a.setTelefone(rs.getString("a.telefone"));
            a.setEndereco(rs.getString("a.endereco"));
            a.setRg(rs.getString("a.rg"));
            a.setCpf(rs.getString("a.cpf"));
            a.setNome(rs.getString("a.nome"));
            a.setDataNascimento(rs.getDate("a.data_nascimento"));

            a.setClubeParticipante(cl);

        }

        return a;
    }

    @Override
    public ArrayList<Associado> buscarTodos() throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM associado a "
                + "INNER JOIN clube cl ON (a.id_clube = cl.id);";

        PreparedStatement pst = conexao.prepareStatement(sql);

        ArrayList<Associado> lista = new ArrayList<>();

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            Associado a = new Associado();
            Clube cl = new Clube();

            cl.setId(rs.getInt("id_clube"));
            cl.setNome(rs.getString("cl.nome"));
            cl.setCnpj(rs.getString("cl.cnpj"));
            cl.setResponsavel(rs.getString("cl.responsavel"));
            cl.setLocalidade(rs.getString("cl.localidade"));

            Mensalidade mensalidade = new Mensalidade();
            mensalidade.setValor(rs.getDouble("a.valor_mensalidade"));

            a.setMensalidade(mensalidade);
            a.setId(rs.getInt("a.id"));
            a.setBanco(rs.getString("a.banco"));
            a.setAgencia(rs.getInt("a.agencia"));
            a.setConta(rs.getInt("a.conta"));
            a.setTelefone(rs.getString("a.telefone"));
            a.setEndereco(rs.getString("a.endereco"));
            a.setRg(rs.getString("a.rg"));
            a.setCpf(rs.getString("a.cpf"));
            a.setNome(rs.getString("a.nome"));
            a.setDataNascimento(rs.getDate("a.data_nascimento"));

            a.setClubeParticipante(cl);

            lista.add(a);
        }

        return lista;
    }

    public ArrayList<Associado> buscarPeloNome(String nome) throws SQLException, ClassNotFoundException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "select * from associado a "
                + "inner join clube cl on (a.id_clube = cl.id) "
                + "where a.nome like ?;";

        PreparedStatement pst = conexao.prepareStatement(sql);

        ArrayList<Associado> lista = new ArrayList<>();
        pst.setString(1, "%" + nome + "%");

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            Associado a = new Associado();
            Clube cl = new Clube();

            cl.setId(rs.getInt("id_clube"));
            cl.setNome(rs.getString("cl.nome"));
            cl.setCnpj(rs.getString("cl.cnpj"));
            cl.setResponsavel(rs.getString("cl.responsavel"));
            cl.setLocalidade(rs.getString("cl.localidade"));

            Mensalidade mensalidade = new Mensalidade();
            mensalidade.setValor(rs.getDouble("a.valor_mensalidade"));

            a.setMensalidade(mensalidade);
            a.setId(rs.getInt("a.id"));
            a.setBanco(rs.getString("a.banco"));
            a.setAgencia(rs.getInt("a.agencia"));
            a.setConta(rs.getInt("a.conta"));
            a.setTelefone(rs.getString("a.telefone"));
            a.setEndereco(rs.getString("a.endereco"));
            a.setRg(rs.getString("a.rg"));
            a.setCpf(rs.getString("a.cpf"));
            a.setNome(rs.getString("a.nome"));
            a.setDataNascimento(rs.getDate("a.data_nascimento"));

            a.setClubeParticipante(cl);

            lista.add(a);
        }

        return lista;
    }

}
