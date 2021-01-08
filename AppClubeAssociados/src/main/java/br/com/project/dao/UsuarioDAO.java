/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.project.dao;

import br.com.project.model.Usuario;
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
public class UsuarioDAO implements GenericsDAO<Usuario, Integer> {

    @Override
    public void inserir(Usuario obj) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "INSERT INTO usuario(user_name,password)VALUES "
                + "(?,?)";

        PreparedStatement pst = conexao.prepareStatement(sql);

        pst.setString(1, obj.getUser_name());
        pst.setString(2, obj.getPassword());

        pst.executeUpdate();
    }

    @Override
    public void alterar(Usuario obj) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "UPDATE usuario "
                + "SET user_name = ?, "
                + "password = ?, "
                + "WHERE id = ?;";

        PreparedStatement pst = conexao.prepareStatement(sql);

        pst.setString(1, obj.getUser_name());
        pst.setString(2, obj.getPassword());
        pst.setInt(3, obj.getId());

        pst.executeUpdate();
    }

    @Override
    public void apagar(Usuario obj) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "DELETE FROM usuario "
                + "WHERE id = ?;";

        PreparedStatement pst = conexao.prepareStatement(sql);

        pst.setInt(1, obj.getId());

        pst.executeUpdate();

    }

    @Override
    public Usuario buscarPelaChave(Integer key) throws ClassNotFoundException, SQLException {

        Connection conexao = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM usuario WHERE id = ?;";

        PreparedStatement pst = conexao.prepareStatement(sql);

        pst.setInt(1, key);

        Usuario usuario = null;

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {

            usuario = new Usuario();

            usuario.setId(rs.getInt("id"));
            usuario.setPassword(rs.getString("password"));
            usuario.setUser_name(rs.getString("user_name"));
        }

        return usuario;
    }

    @Override
    public ArrayList<Usuario> buscarTodos() throws ClassNotFoundException, SQLException {
        
        Connection conexao = ConnectionFactory.getConnection();
        
        String sql = "SELECT * FROM usuario";
        
        PreparedStatement pst = conexao.prepareStatement(sql);
        
        ArrayList<Usuario> lista = new ArrayList<>();
        
        ResultSet rs = pst.executeQuery();
        
        while(rs.next()){
            
            Usuario usuario = new Usuario();

            usuario.setId(rs.getInt("id"));
            usuario.setPassword(rs.getString("password"));
            usuario.setUser_name(rs.getString("user_name"));
            
            lista.add(usuario);
        }
        
        return lista;
    }
    
    public Usuario buscarPorLoginESenha(String login, String senha) throws SQLException, ClassNotFoundException {
        
        Connection c = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM usuario WHERE user_name = ? AND password = ?;";


        PreparedStatement pst = c.prepareStatement(sql);

        pst.setString(1, login);
        pst.setString(2, senha);

        ResultSet rs = pst.executeQuery();

        Usuario usuario = null;

        if (rs.next()) {
            usuario = new Usuario();
            
            usuario.setId(0);
            
            usuario.setPassword(rs.getString("password"));
            usuario.setUser_name(rs.getString("user_name"));
        }

        return usuario;
    }

}
