package com.example.gabriel.fils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by amandatrigueiro on 27/11/16.
 *
 * Tela para ver consultas agendadas ou marcar uma nova
 *
 */

public class Agendamento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agendamento_main);

        //Quando clica em consultas marcadas, troca para a tela de consultas já marcadas
        Button consultasButton = (Button) findViewById(R.id.consultasButton);
        consultasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Agendamento.this, Consultas.class);
                startActivity(intent);
            }
        });

        //Quando clica em Marcar nova consulta, troca para a tela de marcação
        Button novaConsultaButton = (Button) findViewById(R.id.novaConsultaButton);
        novaConsultaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Agendamento.this, NovaConsulta.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
//
    }

}
