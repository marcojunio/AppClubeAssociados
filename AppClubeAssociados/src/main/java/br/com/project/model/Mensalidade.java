/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.project.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *@author Marcos André
 *@since 23/11/2020
 */
public class Mensalidade {
    
    private double valor;
    private Date dtVencimento;

    public Mensalidade() {
    }

    public Mensalidade(BigDecimal valor, Date dtVencimento) {
        this.dtVencimento = dtVencimento;
    }
    
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getDtVencimento() {
        return dtVencimento;
    }

    public void setDtVencimento(Date dtVencimento) {
        this.dtVencimento = dtVencimento;
    }
}
