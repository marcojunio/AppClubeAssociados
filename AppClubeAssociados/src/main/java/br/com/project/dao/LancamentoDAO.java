/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.project.dao;

import br.com.project.model.Clube;
import br.com.project.model.Lancamento;
import br.com.project.model.Mensalidade;
import br.com.project.model.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Marcos André
 * @since 26/11/2020
 */
public class LancamentoDAO implements GenericsDAO<Lancamento, Integer> {

    @Override
    public void inserir(Lancamento obj) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "INSERT INTO lancamento (id,id_clube,ref_inicial,ref_final,totalMensalidades) VALUES "
                + "(?,?,?,?,?)";

        PreparedStatement pst = conexao.prepareStatement(sql);

        pst.setInt(1, obj.getId());
        pst.setInt(2, obj.getClube().getId());
        pst.setString(3, obj.getRef_inicial());
        pst.setString(4, obj.getRef_final());
        //pst.setObject(5, obj.());

        pst.executeUpdate();
    }

    @Override
    public void alterar(Lancamento obj) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "UPDATE lancamento "
                + "SET ref_inicial = ?, "
                + "ref_final = ?, "
                + "totalMensalidades = ?, "
                + "WHERE id = ?;";

        PreparedStatement pst = conexao.prepareStatement(sql);

        pst.setInt(1, obj.getId());

        pst.executeUpdate();
    }

    @Override
    public void apagar(Lancamento obj) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "DELETE FROM lancamento WHERE id = ?;";

        PreparedStatement pst = conexao.prepareStatement(sql);

        pst.setInt(1, obj.getId());

        pst.executeUpdate();
    }

    @Override
    public Lancamento buscarPelaChave(Integer key) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM lancamento WHERE id = ?;";

        PreparedStatement pst = conexao.prepareStatement(sql);

        Lancamento lancamento = null;

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {

            lancamento = new Lancamento();
            Clube clube = new Clube();
            clube.setId(rs.getInt("id_clube"));
            ArrayList<Mensalidade> mensalidadesArrecadadas = new ArrayList<>();
            Mensalidade mensalidade = new Mensalidade();

            mensalidade.setValor(rs.getDouble("total_mensalidades"));

            mensalidadesArrecadadas.add(mensalidade);

            lancamento.setId(rs.getInt("id"));
            lancamento.setClube(clube);
            lancamento.setRef_final(rs.getString("ref_final"));
            lancamento.setRef_inicial(rs.getString("ref_inicial"));
            // lancamento.setMensalidadesArrecadadas(mensalidadesArrecadadas);
        }

        return lancamento;
    }

    @Override
    public ArrayList<Lancamento> buscarTodos() throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM lancamento;";

        PreparedStatement pst = conexao.prepareStatement(sql);

        ArrayList<Lancamento> lancamentos = new ArrayList<>();

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            Lancamento lancamento = new Lancamento();
            Clube clube = new Clube();
            clube.setId(rs.getInt("id_clube"));
            ArrayList<Mensalidade> mensalidadesArrecadadas = new ArrayList<>();
            Mensalidade mensalidade = new Mensalidade();

            mensalidade.setValor(rs.getDouble("total_mensalidades"));

            mensalidadesArrecadadas.add(mensalidade);

            lancamento.setId(rs.getInt("id"));
            lancamento.setClube(clube);
            lancamento.setRef_final(rs.getString("ref_final"));
            lancamento.setRef_inicial(rs.getString("ref_inicial"));
            // lancamento.setMensalidadesArrecadadas(mensalidadesArrecadadas);

            lancamentos.add(lancamento);
        }

        return lancamentos;
    }

    public double valorMensalidadeCliente(Integer id) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "SELECT SUM(c.valor + a.valor_mensalidade)  FROM associado a "
                + "INNER JOIN comanda c "
                + "ON (c.id_cliente = a.id) WHERE a.id = ?;";

        PreparedStatement pst = conexao.prepareStatement(sql);

        pst.setInt(1, id);

        ResultSet rs = pst.executeQuery();

        rs.next();

        return rs.getInt(1);
    }

    public double valorTotalFaturamento(Integer id) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "SELECT "
                + "SUM(c.valor + a.valor_mensalidade) "
                + "FROM "
                + "associado a "
                + "INNER JOIN "
                + "comanda c ON c.id_cliente = a.id "
                + "INNER JOIN "
                + "bar b ON b.id = c.id_bar "
                + "INNER JOIN "
                + "clube cl ON cl.id = b.id_clube "
                + "WHERE cl.id = ?;";

        PreparedStatement pst = conexao.prepareStatement(sql);

        pst.setInt(1, id);

        ResultSet rs = pst.executeQuery();

        rs.next();

        return rs.getInt(1);
    }

}
