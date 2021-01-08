/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.project.dao;

import br.com.project.model.Produto;
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
public class ProdutoDAO implements GenericsDAO<Produto, Integer> {

    @Override
    public void inserir(Produto obj) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "INSERT INTO produto "
                + "(nome,marca,preco,validade,perecivel) VALUES "
                + "(?,?,?,?,?)";

        PreparedStatement pst = conexao.prepareCall(sql);

        pst.setString(1, obj.getNome());
        pst.setString(2, obj.getMarca());
        pst.setDouble(3, obj.getPreco());
        pst.setObject(4, obj.getValidade());
        pst.setBoolean(5, obj.ehPerecivel());

        pst.executeUpdate();
    }

    @Override
    public void alterar(Produto obj) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "UPDATE produto "
                + "SET nome = ?, "
                + "marca = ?, "
                + "preco = ?, "
                + "validade = ?, "
                + "perecivel = ? "
                + "WHERE id = ?;";

        PreparedStatement pst = conexao.prepareCall(sql);

        pst.setString(1, obj.getNome());
        pst.setString(2, obj.getMarca());
        pst.setDouble(3, obj.getPreco());
        pst.setObject(4, obj.getValidade());
        pst.setBoolean(5, obj.ehPerecivel());
        pst.setInt(6, obj.getId());

        pst.executeUpdate();
    }

    @Override
    public void apagar(Produto obj) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "DELETE FROM produto "
                + "WHERE id = ?;";

        PreparedStatement pst = conexao.prepareStatement(sql);

        pst.setInt(1, obj.getId());

        pst.executeUpdate();
    }

    @Override
    public Produto buscarPelaChave(Integer key) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM produto "
                + "WHERE id = ?;";

        PreparedStatement pst = conexao.prepareStatement(sql);

        pst.setInt(1, key);

        Produto produto = null;

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {

            produto = new Produto();
            
            produto.setEhPerecivel(rs.getBoolean("perecivel"));
            produto.setId(rs.getInt("id"));
            produto.setMarca(rs.getString("marca"));
            produto.setNome(rs.getString("nome"));
            produto.setPreco(rs.getDouble("preco"));
            produto.setValidade(rs.getDate("validade"));
            
        }

        return produto;

    }

    @Override
    public ArrayList<Produto> buscarTodos() throws ClassNotFoundException, SQLException {
        
        Connection conexao = ConnectionFactory.getConnection();
        
        String sql = "SELECT * FROM produto";
        
        PreparedStatement pst = conexao.prepareStatement(sql);
        
        ArrayList<Produto> lista = new ArrayList<>();
        
        ResultSet rs = pst.executeQuery();
        
        while(rs.next()){
        
            Produto produto = new Produto();
            
            produto.setEhPerecivel(rs.getBoolean("perecivel"));
            produto.setId(rs.getInt("id"));
            produto.setMarca(rs.getString("marca"));
            produto.setNome(rs.getString("nome"));
            produto.setPreco(rs.getDouble("preco"));
            produto.setValidade(rs.getDate("validade"));
            
            lista.add(produto);
        
        }
        
        return lista;
    }
    
    public ArrayList<Produto> buscarPeloNome(String nome) throws SQLException, ClassNotFoundException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM produto where nome LIKE ?;";

        PreparedStatement pst = conexao.prepareStatement(sql);

        ArrayList<Produto> lista = new ArrayList<>();
        pst.setString(1, "%" + nome + "%");

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            Produto produto = new Produto();
            
            produto.setEhPerecivel(rs.getBoolean("perecivel"));
            produto.setId(rs.getInt("id"));
            produto.setMarca(rs.getString("marca"));
            produto.setNome(rs.getString("nome"));
            produto.setPreco(rs.getDouble("preco"));
            produto.setValidade(rs.getDate("validade"));
            

            lista.add(produto);
        }

        return lista;
    }


}
