package com.example.gabriel.fils;

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
    private List<Exercicio> grupoA;
    private List<Exercicio> grupoB;
    private List<Exercicio> grupoC;
    private List<Exercicio> grupoD;
    private List<Exercicio> grupoE;

    public Treino(char t){
        this.tipo = t;

    }

    public void setGrupoA(String d, List<Exercicio> lista){
        descricaoA = d;
        this.grupoA = lista;
    }

    public void setGrupoB(String d, List<Exercicio> lista){
        descricaoB = d;
        this.grupoB = lista;
    }

    public void setGrupoC(String d, List<Exercicio> lista){
        descricaoC = d;
        this.grupoC = lista;
    }

    public void setGrupoD(String d, List<Exercicio> lista){
        descricaoD = d;
        this.grupoD = lista;
    }

    public void setGrupoE(String d, List<Exercicio> lista){
        descricaoE = d;
        this.grupoE = lista;
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
    }
}
