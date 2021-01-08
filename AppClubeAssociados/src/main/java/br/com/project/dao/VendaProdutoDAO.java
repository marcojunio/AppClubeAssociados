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
import br.com.project.model.VendaProduto;
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
 * @since 26/11/2020
 */
public class VendaProdutoDAO implements GenericsDAO<VendaProduto, Integer> {

    @Override
    public void inserir(VendaProduto obj) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "INSERT INTO comanda_venda_produto (id_comanda,id_produto,nome_produto,valor_produto) VALUES "
                + "(?,?,?,?)";

        PreparedStatement pst = conexao.prepareStatement(sql);

        pst.setInt(1, obj.getId_comanda().getId());
        pst.setInt(2, obj.getId_produto().getId());
        pst.setString(3, obj.getDescricaoProduto());
        pst.setDouble(4, obj.getValor());

    }

    @Override
    public void alterar(VendaProduto obj) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void apagar(VendaProduto obj) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public VendaProduto buscarPelaChave(Integer key) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "SELECT cv.id_comanda, c.id_cliente, cv.nome_produto, p.preco "
                + "FROM comanda c "
                + "INNER JOIN "
                + "comanda_venda_produto cv ON cv.id_comanda = c.id "
                + "INNER JOIN "
                + "produto p ON p.id = cv.id_produto "
                + "WHERE cv.id_comanda = ?;";

        PreparedStatement pst = conexao.prepareStatement(sql);

        pst.setInt(1, key);

        VendaProduto cv = null;

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {

            cv = new VendaProduto();
            Comanda c = new Comanda();
            Produto p = new Produto();
            Associado cliente = new Associado();
            Bar bar = new Bar();

            bar.setId(rs.getInt("id_bar"));
            cliente.setId(rs.getInt("id_cliente"));
            
            c.setId(rs.getInt("id_comanda"));
            c.setId_cliente(cliente);
            c.setId_bar(bar);
            c.setData_atendimento(LocalDateTime.parse(rs.getString("data_atendimento").replace(".0", ""),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
            c.setValor(rs.getDouble("valor"));
           
            p.setEhPerecivel(rs.getBoolean("perecivel"));
            p.setId(rs.getInt("id"));
            p.setMarca(rs.getString("marca"));
            p.setNome(rs.getString("p.nome_produto"));
            p.setPreco(rs.getDouble("p.preco"));
            p.setValidade(rs.getDate("validade"));
                         
            cv.setId_produto(p);
            cv.setValor(p.getPreco());
            cv.setDescricaoProduto(p.getNome());
            cv.setId_comanda(c);
        }
        
        return cv;

    }

    @Override
    public ArrayList<VendaProduto> buscarTodos() throws ClassNotFoundException, SQLException {
        
        Connection conexao = ConnectionFactory.getConnection();
        
        String sql = "SELECT * FROM comanda_venda_produto ";
        
        PreparedStatement pst = conexao.prepareStatement(sql);
        
        ArrayList<VendaProduto> lista = new ArrayList<>();
        
        ResultSet rs = pst.executeQuery();
        
        while (rs.next()) {

            VendaProduto cv = new VendaProduto();
            Comanda c = new Comanda();
            Produto p = new Produto();
            Associado cliente = new Associado();
            Bar bar = new Bar();

            bar.setId(rs.getInt("id_bar"));
            cliente.setId(rs.getInt("id_cliente"));
            
            c.setId(rs.getInt("id_comanda"));
            c.setId_cliente(cliente);
            c.setId_bar(bar);
            c.setData_atendimento(LocalDateTime.parse(rs.getString("data_atendimento").replace(".0", ""),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
            c.setValor(rs.getDouble("valor"));
           
            p.setEhPerecivel(rs.getBoolean("perecivel"));
            p.setId(rs.getInt("id"));
            p.setMarca(rs.getString("marca"));
            p.setNome(rs.getString("p.nome_produto"));
            p.setPreco(rs.getDouble("p.preco"));
            p.setValidade(rs.getDate("validade"));
                         
            cv.setId_produto(p);
            cv.setValor(p.getPreco());
            cv.setDescricaoProduto(p.getNome());
            cv.setId_comanda(c);
            
            lista.add(cv);
        }
        
        return lista;
    }

}
