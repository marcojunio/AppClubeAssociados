/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.project.dao;

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
public class ClubeDAO implements GenericsDAO<Clube, Integer> {

    @Override
    public void inserir(Clube obj) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "INSERT INTO clube "
                + "(nome,cnpj,responsavel,localidade) VALUES "
                + "(?,?,?,?);";

        PreparedStatement pst = conexao.prepareStatement(sql);

        pst.setString(1, obj.getNome());
        pst.setString(2, obj.getCnpj());
        pst.setString(3, obj.getResponsavel());
        pst.setString(4, obj.getLocalidade());

        pst.executeUpdate();
    }

    @Override
    public void alterar(Clube obj) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "UPDATE clube "
                + "SET nome = ?, "
                + "cnpj = ?, "
                + "responsavel = ?, "
                + "localidade = ? "
                + "WHERE id = ?;";

        PreparedStatement pst = conexao.prepareStatement(sql);

        pst.setString(1, obj.getNome());
        pst.setString(2, obj.getCnpj());
        pst.setString(3, obj.getResponsavel());
        pst.setString(4, obj.getLocalidade());
        pst.setInt(5, obj.getId());

        pst.executeUpdate();
    }

    @Override
    public void apagar(Clube obj) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "DELETE FROM clube "
                + "WHERE id = ?;";

        PreparedStatement pst = conexao.prepareStatement(sql);

        pst.setInt(1, obj.getId());

        pst.executeUpdate();

    }

    @Override
    public Clube buscarPelaChave(Integer key) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM clube "
                + "WHERE id = ?;";

        PreparedStatement pst = conexao.prepareStatement(sql);

        pst.setInt(1, key);

        ResultSet rs = pst.executeQuery();

        Clube clube = null;

        if (rs.next()) {

            clube = new Clube();

            clube.setId(rs.getInt("id"));
            clube.setCnpj(rs.getString("cnpj"));
            clube.setNome(rs.getString("nome"));
            clube.setResponsavel(rs.getString("responsavel"));
            clube.setLocalidade(rs.getString("localidade"));
        }

        return clube;
    }

    @Override
    public ArrayList<Clube> buscarTodos() throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM clube;";

        PreparedStatement pst = conexao.prepareStatement(sql);

        ArrayList<Clube> lista = new ArrayList<>();

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            Clube clube = new Clube();

            clube.setId(rs.getInt("id"));
            clube.setCnpj(rs.getString("cnpj"));
            clube.setNome(rs.getString("nome"));
            clube.setResponsavel(rs.getString("responsavel"));
            clube.setLocalidade(rs.getString("localidade"));

            lista.add(clube);

        }

        return lista;
    }
    
     public ArrayList<Clube> buscarPeloNome(String nome) throws SQLException, ClassNotFoundException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM clube "
                + "WHERE nome like ?";

        PreparedStatement pst = conexao.prepareStatement(sql);

        ArrayList<Clube> lista = new ArrayList<>();
        pst.setString(1, "%" + nome + "%");

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

         
            Clube cl = new Clube();

            cl.setId(rs.getInt("id"));
            cl.setCnpj(rs.getString("cnpj"));
            cl.setNome(rs.getString("nome"));
            cl.setResponsavel(rs.getString("responsavel"));
            cl.setLocalidade(rs.getString("localidade"));
            
            lista.add(cl);
        }

        return lista;
    }

}
