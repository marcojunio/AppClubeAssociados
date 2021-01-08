/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.project.model;

/**
 * @author Marcos André
 * @since  26/11/2020
 */
public class VendaProduto {
    
   private Comanda id_comanda;
   private Produto id_produto;
   private String descricaoProduto;
   private double valor;

    public VendaProduto() {
    }

    public VendaProduto(Comanda id_comanda, Produto id_produto, String descricaoProduto, double valor) {
        this.id_comanda = id_comanda;
        this.id_produto = id_produto;
        this.descricaoProduto = descricaoProduto;
        this.valor = valor;
    }
    
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }
    
    public Comanda getId_comanda() {
        return id_comanda;
    }

    public void setId_comanda(Comanda id_comanda) {
        this.id_comanda = id_comanda;
    }

    public Produto getId_produto() {
        return id_produto;
    }

    public void setId_produto(Produto id_produto) {
        this.id_produto = id_produto;
    }

}
