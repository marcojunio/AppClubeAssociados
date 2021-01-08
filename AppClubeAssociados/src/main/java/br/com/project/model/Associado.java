/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.project.model;

import java.util.Date;

/**
 *
 * @author Marcos André
 * @since 16/11/2020
 */
public class Associado extends Pessoa {
    
    private Integer id;
    private String banco;
    private int agencia;
    private int conta;
    private Mensalidade mensalidade;
    private Clube clubeParticipante;

    public Associado(Integer id, String banco, int agencia, int conta, Mensalidade mensalidade, Clube clubeParticipante, String nome, String cpf, String rg, String telefone, String endereco, Date dataNascimento) {
        super(nome, cpf, rg, telefone, endereco, dataNascimento);
        this.id = id;
        this.banco = banco;
        this.agencia = agencia;
        this.conta = conta;
        this.mensalidade = mensalidade;
        this.clubeParticipante = clubeParticipante;
    }
    
    public Associado() {
    }

    public Mensalidade getMensalidade() {
        return mensalidade;
    }

    public void setMensalidade(Mensalidade mensalidade) {
        this.mensalidade = mensalidade;
    }

    public Clube getClubeParticipante() {
        return clubeParticipante;
    }

    public void setClubeParticipante(Clube clubeParticipante) {
        this.clubeParticipante = clubeParticipante;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public int getConta() {
        return conta;
    }

    public void setConta(int conta) {
        this.conta = conta;
    }
    
    
    
    
    
}
