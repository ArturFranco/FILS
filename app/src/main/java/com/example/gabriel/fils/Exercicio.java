package com.example.gabriel.fils;

/**
 * Created by Gabriel on 19/11/2016.
 */

public class Exercicio {
    private String nome;
    private int repeticoes;
    private int series;

    public Exercicio(String n, int r, int s){
        this.nome = n;
        this.repeticoes = r;
        this.series = s;
    }

    public String getNome(){
        return this.nome;
    }

    public int getRepeticoes(){
        return this.repeticoes;
    }

    public int getSeries(){
        return this.series;
    }

    public void setRepeticoes(int n){
        this.repeticoes = n;
    }

    public void setSeries(int n){
        this.series = n;
    }

    public void setNome(String n){
        this.nome = n;
    }

}
