/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.project.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *@author Marcos André
 *@since 25/11/2020 
 */
public class Comanda {
    
    private Integer id;
    private Associado id_cliente;
    private Bar id_bar;
    private ArrayList<VendaProduto> produtosConsumidos;
    private double valor;
    private LocalDateTime data_atendimento;

    public Comanda() {
    }

    public Comanda(Associado id_cliente, Bar id_bar, ArrayList<VendaProduto> produtosConsumidos, double valor, LocalDateTime data_atendimento) {
        this.id_cliente = id_cliente;
        this.id_bar = id_bar;
        this.produtosConsumidos = produtosConsumidos;
        this.valor = valor;
        this.data_atendimento = data_atendimento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Associado getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Associado id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Bar getId_bar() {
        return id_bar;
    }

    public void setId_bar(Bar id_bar) {
        this.id_bar = id_bar;
    }

    public ArrayList<VendaProduto> getProdutosConsumidos() {
        return produtosConsumidos;
    }

    public void setProdutosConsumidos(ArrayList<VendaProduto> produtosConsumidos) {
        this.produtosConsumidos = produtosConsumidos;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDateTime getData_atendimento() {
        return data_atendimento;
    }

    public void setData_atendimento(LocalDateTime data_atendimento) {
        this.data_atendimento = data_atendimento;
    }
    
}
