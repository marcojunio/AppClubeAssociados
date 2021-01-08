/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.project.dao;

import br.com.project.model.Bar;
import br.com.project.model.BarProduto;
import br.com.project.model.Clube;
import br.com.project.model.Produto;
import br.com.project.model.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Marcos André
 * @since 25/11/2020
 */
public class BarProdutoDAO implements GenericsDAO<BarProduto, Integer> {

    @Override
    public void inserir(BarProduto obj) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "INSERT INTO bar_produto (id_bar,id_produto,nome_produto) VALUES "
                + "(?,?,?)";

        PreparedStatement pst = conexao.prepareStatement(sql);

        pst.setInt(1, obj.getId_bar().getId());
        pst.setInt(2, obj.getId_produto().getId());
        pst.setString(3, obj.getId_produto().getNome());

        pst.executeUpdate();
    }

    @Override
    public void alterar(BarProduto obj) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "UPDATE bar_produto "
                + "SET id_produto = ?, "
                + "nome_produto = ?, "
                + "WHERE id_bar = ?;";

        PreparedStatement pst = conexao.prepareStatement(sql);

        pst.setInt(1, obj.getId_produto().getId());
        pst.setString(2, obj.getId_produto().getNome());
        pst.setInt(3, obj.getId_bar().getId());

        pst.executeUpdate();

    }

    @Override
    public void apagar(BarProduto obj) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "DELETE FROM bar_produto"
                + "WHERE id_bar = ?;";

        PreparedStatement pst = conexao.prepareStatement(sql);

        pst.setInt(1, obj.getId_bar().getId());

        pst.executeUpdate();
    }

    @Override
    public BarProduto buscarPelaChave(Integer key) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM bar_produto WHERE id_bar = ?;";

        PreparedStatement pst = conexao.prepareStatement(sql);

        BarProduto barProduto = null;

        pst.setInt(1, key);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {

            barProduto = new BarProduto();
            Bar bar = new Bar();
            Produto produto = new Produto();
            Clube clube = new Clube();

            clube.setId(rs.getInt("id_clube"));
            clube.setCnpj(rs.getString("cnpj"));
            clube.setNome(rs.getString("nome"));
            clube.setResponsavel(rs.getString("responsavel"));
            clube.setLocalidade(rs.getString("localidade"));

            bar.setId(rs.getInt("id_bar"));
            bar.setResponsavel(rs.getString("responsavel"));
            bar.setClube(clube);

            produto.setEhPerecivel(rs.getBoolean("perecivel"));
            produto.setMarca(rs.getString("marca"));
            produto.setPreco(rs.getDouble("preco"));
            produto.setId(rs.getInt("id_produto"));
            produto.setNome(rs.getString("nome_produto"));
            produto.setValidade(rs.getDate("validade"));

            barProduto.setId_bar(bar);
            barProduto.setId_produto(produto);
            barProduto.setNomeProduto(produto);
        }

        return barProduto;
    }

    @Override
    public ArrayList<BarProduto> buscarTodos() throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM bar_produto";

        PreparedStatement pst = conexao.prepareStatement(sql);

        ArrayList<BarProduto> lista = new ArrayList<>();

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            BarProduto barProduto = new BarProduto();
            Bar bar = new Bar();
            Produto produto = new Produto();
            Clube clube = new Clube();

            clube.setId(rs.getInt("id_clube"));
            clube.setCnpj(rs.getString("cnpj"));
            clube.setNome(rs.getString("nome"));
            clube.setResponsavel(rs.getString("responsavel"));
            clube.setLocalidade(rs.getString("localidade"));

            bar.setId(rs.getInt("id_bar"));
            bar.setResponsavel(rs.getString("responsavel"));
            bar.setClube(clube);

            produto.setEhPerecivel(rs.getBoolean("perecivel"));
            produto.setMarca(rs.getString("marca"));
            produto.setPreco(rs.getDouble("preco"));
            produto.setId(rs.getInt("id_produto"));
            produto.setNome(rs.getString("nome_produto"));
            produto.setValidade(rs.getDate("validade"));

            barProduto.setId_bar(bar);
            barProduto.setId_produto(produto);
            barProduto.setNomeProduto(produto);
            
            lista.add(barProduto);
        }

        return lista;
    }
    
    public ArrayList<BarProduto> buscarPeloIdBar(Integer idBar) throws SQLException, ClassNotFoundException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "select bp.id_bar,bp.id_produto,bp.nome_produto from bar_produto bp "
                + "inner join bar b on (b.id = bp.id_bar)"
                + "WHERE bp.id_bar like ?;";

        PreparedStatement pst = conexao.prepareStatement(sql);

        ArrayList<BarProduto> lista = new ArrayList<>();
        pst.setString(1, "%" + idBar + "%");

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            BarProduto bp = new BarProduto();
            Bar b = new Bar();
            Produto produto = new Produto();
            Clube clube = new Clube();

            clube.setId(rs.getInt("id_clube"));
            clube.setCnpj(rs.getString("cnpj"));
            clube.setNome(rs.getString("nome"));
            clube.setResponsavel(rs.getString("responsavel"));
            clube.setLocalidade(rs.getString("localidade"));

            b.setId(rs.getInt("id_bar"));
            b.setResponsavel(rs.getString("responsavel"));
            b.setClube(clube);

            produto.setEhPerecivel(rs.getBoolean("perecivel"));
            produto.setMarca(rs.getString("marca"));
            produto.setPreco(rs.getDouble("preco"));
            produto.setId(rs.getInt("id_produto"));
            produto.setNome(rs.getString("nome_produto"));
            produto.setValidade(rs.getDate("validade"));

            bp.setId_bar(b);
            bp.setId_produto(produto);
            bp.setNomeProduto(produto);
            
            lista.add(bp);
        }

        return lista;
    }
    
}
