/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.project.dao;

import br.com.project.model.Bar;
import br.com.project.model.Clube;
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
public class BarDAO implements GenericsDAO<Bar, Integer> {

    @Override
    public void inserir(Bar obj) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "INSERT INTO bar (id_clube,id,responsavel) VALUES "
                + "(?,?,?);";

        PreparedStatement pst = conexao.prepareStatement(sql);

        pst.setInt(1, obj.getClube().getId());
        pst.setObject(2, obj.getId());
        pst.setString(3, obj.getResponsavel());

        pst.executeUpdate();
    }

    @Override
    public void alterar(Bar obj) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "UPDATE bar "
                + "SET responsavel = ?, "
                + "WHERE id = ?;";

        PreparedStatement pst = conexao.prepareStatement(sql);

        pst.setString(1, obj.getResponsavel());
        pst.setInt(2, obj.getId());

        pst.executeUpdate();
    }

    @Override
    public void apagar(Bar obj) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "DELETE FROM bar "
                + "WHERE id = ?;";

        PreparedStatement pst = conexao.prepareStatement(sql);

        pst.setInt(1, obj.getId());

        pst.executeUpdate();
    }

    @Override
    public Bar buscarPelaChave(Integer key) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM bar "
                + "WHERE id = ?;";

        PreparedStatement pst = conexao.prepareStatement(sql);

        pst.setInt(1, key);

        Bar bar = null;

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {

            bar = new Bar();
            Clube clube = new Clube();

            clube.setId(rs.getInt("id_clube"));
            clube.setNome(rs.getString("nome"));
            clube.setLocalidade(rs.getString("localidade"));
            clube.setResponsavel(rs.getString("responsavel"));
            clube.setCnpj(rs.getString("cnpj"));

            bar.setClube(clube);
            bar.setId(rs.getInt("id"));
            bar.setResponsavel(rs.getString("responsavel"));
        }

        return bar;
    }

    @Override
    public ArrayList<Bar> buscarTodos() throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM bar;";

        PreparedStatement pst = conexao.prepareStatement(sql);

        ArrayList<Bar> lista = new ArrayList<>();

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            Bar bar = new Bar();
            Clube clube = new Clube();

            clube.setId(rs.getInt("id_clube"));
            clube.setNome(rs.getString("nome"));
            clube.setLocalidade(rs.getString("localidade"));
            clube.setResponsavel(rs.getString("responsavel"));
            clube.setCnpj(rs.getString("cnpj"));

            bar.setClube(clube);
            bar.setId(rs.getInt("id"));
            bar.setResponsavel(rs.getString("responsavel"));

        }

        return lista;
    }
    
    public ArrayList<Bar> buscarPeloNome(String responsavel) throws SQLException, ClassNotFoundException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "select * from bar b "
                + "inner join clube cl on (b.id_clube = cl.id)"
                + "where b.responsavel like ?;";

        PreparedStatement pst = conexao.prepareStatement(sql);

        ArrayList<Bar> lista = new ArrayList<>();
        pst.setString(1, "%" + responsavel + "%");

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            Bar b = new Bar();
            Clube cl = new Clube();

            cl.setId(rs.getInt("id_clube"));
            cl.setNome(rs.getString("nome"));
            cl.setLocalidade(rs.getString("localidade"));
            cl.setResponsavel(rs.getString("responsavel"));
            cl.setCnpj(rs.getString("cnpj"));

            b.setClube(cl);
            b.setId(rs.getInt("id"));
            b.setResponsavel(rs.getString("b.responsavel"));
            
            lista.add(b);
        }

        return lista;
    }

}
