/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.project.model;

import br.com.project.model.Bar;
import br.com.project.model.Produto;

/**
 * @author Marcos André
 * @since 25/11/2020
 */
public class BarProduto {

    private Bar id_bar;
    private Produto id_produto;
    private Produto nomeProduto;

    public BarProduto() {
    }

    public BarProduto(Bar id_bar, Produto id_produto, Produto nomeProduto) {
        this.id_bar = id_bar;
        this.id_produto = id_produto;
        this.nomeProduto = nomeProduto;
    }

    public Bar getId_bar() {
        return id_bar;
    }

    public void setId_bar(Bar id_bar) {
        this.id_bar = id_bar;
    }

    public Produto getId_produto() {
        return id_produto;
    }

    public void setId_produto(Produto id_produto) {
        this.id_produto = id_produto;
    }

    public Produto getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(Produto nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

}
