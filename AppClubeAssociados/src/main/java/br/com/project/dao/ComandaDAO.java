/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.project.dao;

import br.com.project.model.Associado;
import br.com.project.model.Bar;
import br.com.project.model.Comanda;
import br.com.project.model.Produto;
import br.com.project.model.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * @author Marcos André
 * @since 25/11/2020
 */
public class ComandaDAO implements GenericsDAO<Comanda, Integer> {

    @Override
    public void inserir(Comanda obj) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "INSERT INTO comanda (id,id_cliente,id_bar,data_atendimento,valor) VALUES "
                + "(?,?,?,?,?)";

        PreparedStatement pst = conexao.prepareStatement(sql);

        pst.setInt(1, obj.getId());
        pst.setInt(2, obj.getId_cliente().getId());
        pst.setInt(3, obj.getId_bar().getId());
        pst.setObject(4, obj.getData_atendimento());
        pst.setObject(5, obj.getValor());

        pst.executeUpdate();
    }

    @Override
    public void alterar(Comanda obj) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "UPDATE comanda "
                + "SET data_atendimento = ?, "
                + "valor = ?, "
                + "WHERE id = ?;";

        PreparedStatement pst = conexao.prepareStatement(sql);

        pst.setObject(1, obj.getData_atendimento());
        pst.setObject(2, obj.getValor());
        pst.setInt(3, obj.getId());

        pst.executeUpdate();
    }

    @Override
    public void apagar(Comanda obj) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "DELETE FROM comanda WHERE id = ?;";

        PreparedStatement pst = conexao.prepareStatement(sql);

        pst.setInt(1, obj.getId());

        pst.executeUpdate();
    }

    @Override
    public Comanda buscarPelaChave(Integer key) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM comanda WHERE id = ?;";

        PreparedStatement pst = conexao.prepareStatement(sql);

        Comanda comanda = null;

        pst.setInt(1, key);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {

            comanda = new Comanda();
            Associado cliente = new Associado();
            Bar bar = new Bar();
            ArrayList<Produto> produtos = new ArrayList<>();
            Produto produto = new Produto();

            bar.setId(rs.getInt("id_bar"));
            cliente.setId(rs.getInt("id_cliente"));
            produto.setNome(rs.getString("consumo"));

            produtos.add(produto);

            comanda.setId(rs.getInt("id"));
            comanda.setId_cliente(cliente);
            comanda.setId_bar(bar);
            comanda.setData_atendimento(LocalDateTime.parse(rs.getString("data_atendimento").replace(".0", ""),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
            comanda.setValor(rs.getDouble("valor"));

        }
        return comanda;
    }

    @Override
    public ArrayList<Comanda> buscarTodos() throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM comanda;";

        PreparedStatement pst = conexao.prepareStatement(sql);

        ArrayList<Comanda> lista = new ArrayList<>();

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            Comanda comanda = new Comanda();
            Associado cliente = new Associado();
            Bar bar = new Bar();

            bar.setId(rs.getInt("id_bar"));
            cliente.setId(rs.getInt("id_cliente"));

            comanda.setId(rs.getInt("id"));
            comanda.setId_cliente(cliente);
            comanda.setId_bar(bar);
            comanda.setData_atendimento(LocalDateTime.parse(rs.getString("data_atendimento").replace(".0", ""),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
            comanda.setValor(rs.getDouble("valor"));

            lista.add(comanda);
        }

        return lista;
    }
    
    
}
