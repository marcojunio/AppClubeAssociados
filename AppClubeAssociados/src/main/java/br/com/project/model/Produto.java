/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.project.model;

import java.util.Date;


/**
 * @author Marcos André
 * @since 23/11/2020
 */
public class Produto {
    
    private Integer id;
    private String nome;
    private String marca;
    private Date validade;
    private boolean ehPerecivel;
    private double preco;

    public Produto(String nome, String marca, Date validade, boolean ehPerecivel, double preco) {
        this.nome = nome;
        this.marca = marca;
        this.validade = validade;
        this.ehPerecivel = ehPerecivel;
        this.preco = preco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    public Produto() {
    }

    public boolean ehPerecivel() {
        return ehPerecivel;
    }

    public void setEhPerecivel(boolean ehPerecivel) {
        this.ehPerecivel = ehPerecivel;
    }
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }     
}
