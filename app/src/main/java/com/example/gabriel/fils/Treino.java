package com.example.gabriel.fils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gabriel on 19/11/2016.
 */

public class Treino {
    private char tipo;
    private String descricaoA;
    private String descricaoB;
    private String descricaoC;
    private String descricaoD;
    private String descricaoE;
    private List<String> nomesGrupoA;
    private List<String> nomesGrupoB;
    private List<String> nomesGrupoC;
    private List<String> nomesGrupoD;
    private List<String> nomesGrupoE;
    private List<Integer> repGrupoA;
    private List<Integer> repGrupoB;
    private List<Integer> repGrupoC;
    private List<Integer> repGrupoD;
    private List<Integer> repGrupoE;
    private List<Integer> seriesGrupoA;
    private List<Integer> seriesGrupoB;
    private List<Integer> seriesGrupoC;
    private List<Integer> seriesGrupoD;
    private List<Integer> seriesGrupoE;
    /*private List<Exercicio> grupoA;
    private List<Exercicio> grupoB;
    private List<Exercicio> grupoC;
    private List<Exercicio> grupoD;
    private List<Exercicio> grupoE;*/

    public Treino(char t){
        this.tipo = t;
        this.nomesGrupoA = new ArrayList<String>();
        this.nomesGrupoB = new ArrayList<String>();
        this.nomesGrupoC = new ArrayList<String>();
        this.nomesGrupoD = new ArrayList<String>();
        this.nomesGrupoE = new ArrayList<String>();


    }

    public void addNameGrupo(char grupo, String d, List<Exercicio> lista){
        descricaoA = d;
        //this.grupoA = lista;
    }

    public void setGrupoB(String d, List<Exercicio> lista){
        descricaoB = d;
       // this.grupoB = lista;
    }

    public void setGrupoC(String d, List<Exercicio> lista){
        descricaoC = d;
       // this.grupoC = lista;
    }

    public void setGrupoD(String d, List<Exercicio> lista){
        descricaoD = d;
       // this.grupoD = lista;
    }

    public void setGrupoE(String d, List<Exercicio> lista){
        descricaoE = d;
      //  this.grupoE = lista;
    }

 /*   public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public List<Exercicio> getGrupoA(){
        return this.grupoA;
    }

    public List<Exercicio> getGrupoB() {
        return grupoB;
    }

    public List<Exercicio> getGrupoC() {
        return grupoC;
    }

    public List<Exercicio> getGrupoD() {
        return grupoD;
    }

    public List<Exercicio> getGrupoE() {
        return grupoE;
    }*/
}
