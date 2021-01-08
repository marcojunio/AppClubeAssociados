/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.project.model.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Marcos André
 * @since 23/11/2020
 */
public class ConnectionFactory {
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        String host = "jdbc:mysql://localhost:3306/clube_associado?useTimezone=true&serverTimezone=UTC";
        
        return DriverManager.getConnection(host,"USER DA CONEXÃO","SENHA DA CONEXÃO");
    }
}
