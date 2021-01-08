/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.project.dao;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *@author Marcos André 
 *@since 24/11/2020 
*/

public interface GenericsDAO<O,K> {
    
    public void inserir(O obj) throws ClassNotFoundException,SQLException;
    public void alterar(O obj) throws ClassNotFoundException,SQLException;
    public void apagar(O obj) throws ClassNotFoundException,SQLException;
    public O buscarPelaChave(K key) throws ClassNotFoundException,SQLException;
    public ArrayList<O> buscarTodos() throws ClassNotFoundException,SQLException;
    
}
