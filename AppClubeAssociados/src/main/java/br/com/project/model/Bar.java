/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.project.model;

import java.util.ArrayList;

/**
 * @author Marcos André
 * @since 23/11/2020
 */
public class Bar {
    
    private Integer id;
    private Clube clube;
    private String responsavel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Bar() {
    }

    public Bar(Clube clube, String responsavel) {
        this.clube = clube;
        this.responsavel = responsavel;
    }
    
    public Clube getClube() {
        return clube;
    }

    public void setClube(Clube clube) {
        this.clube = clube;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
}
