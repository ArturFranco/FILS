package com.example.gabriel.fils;

/**
 * Created by Felipe on 11/30/2016.
 */

public class Notificacao {
    // pode ser:
    // "Solicitacao Personal"
    // "Solicitacao Nutricionista"
    // "Meta"
    // "Novo Treino"
    // "Nova Refeicao"
    // "Agendamento Personal"
    // "Agendamento Nutricionista"
    // "Agendamento Aluno"
    public String descricao;
    public String id; // pode identificar uma pessoal, um treino, uma dieta ou um atributo (para as metas)

    public Notificacao(String descricao, String id) {
        this.descricao = descricao;
        this.id = id;
    }
}
