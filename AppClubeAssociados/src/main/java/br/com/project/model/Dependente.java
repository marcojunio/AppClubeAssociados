/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.project.model;

import br.com.project.model.enumeration.EnumParentesco;
import java.util.Date;

/**
 * @author Marcos André
 * @since 16/11/2020
 */
public class Dependente extends Pessoa{
    
    private Integer id;
    private EnumParentesco parente;
    private Associado responsavel;
    private boolean isCasado;
    private int idade; 
    
    public Dependente() {
    }

    public Dependente(Integer id, EnumParentesco parente, Associado responsavel, String nome, String cpf, String rg, String telefone, String endereco, Date dataNascimento) {
        super(nome, cpf, rg, telefone, endereco, dataNascimento);
        this.id = id;
        this.parente = parente;
        this.responsavel = responsavel;
    }

    public boolean isIsCasado() {
        return isCasado;
    }

    public void setIsCasado(boolean isCasado) {
        this.isCasado = isCasado;
    }
    
    public Associado getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Associado responsavel) {
        this.responsavel = responsavel;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getIdade() {
        return idade;
    }
    
    public boolean ehMaiorDeIdade(){
        return idade >= 18;
    } 
    public EnumParentesco getParente() {
        return parente;
    }

    public void setParente(EnumParentesco parente) {
        this.parente = parente;
    }
    
}
