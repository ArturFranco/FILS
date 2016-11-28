package com.example.gabriel.fils;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by amandatrigueiro on 27/11/16.
 *
 * Tela para ver consultas agendadas ou marcar uma nova
 *
 */

public class Agendamento extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agendamento_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addAgendamentoButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Agendamento.this, AddAgendamento.class);
                startActivity(intent);
            }
        });

    }

}
