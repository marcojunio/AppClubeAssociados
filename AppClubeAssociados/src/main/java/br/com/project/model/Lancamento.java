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
public class Lancamento {
    
    private Integer id;
    private Clube clube;
    private String ref_inicial;
    private String ref_final;
    private ArrayList<Mensalidade> mensalidadesArrecadadas;
    
    public Lancamento() {
    }

    public Lancamento(Integer id, Clube clube, String ref_inicial, String ref_final, ArrayList<Mensalidade> mensalidadesArrecadadas) {
        this.id = id;
        this.clube = clube;
        this.ref_inicial = ref_inicial;
        this.ref_final = ref_final;
        this.mensalidadesArrecadadas = mensalidadesArrecadadas;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Clube getClube() {
        return clube;
    }

    public void setClube(Clube clube) {
        this.clube = clube;
    }

    public String getRef_inicial() {
        return ref_inicial;
    }

    public void setRef_inicial(String ref_inicial) {
        this.ref_inicial = ref_inicial;
    }

    public String getRef_final() {
        return ref_final;
    }

    public void setRef_final(String ref_final) {
        this.ref_final = ref_final;
    }
    
    public double totalMensalidade(){
        double total = 0; 
        
        for(int i = 0 ; i < mensalidadesArrecadadas.size(); i++){
            total += mensalidadesArrecadadas.get(i).getValor();
        }
        
            return total;
    }
}
