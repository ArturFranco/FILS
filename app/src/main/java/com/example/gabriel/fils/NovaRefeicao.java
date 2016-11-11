package com.example.gabriel.fils;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Artur on 10/11/2016.
 *
 * Tela que lista refeições cadastradas e permite adicionar outras
 */


//TODO TUDO
public class NovaRefeicao extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nova_refeicao_main);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addRefeicaoButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}

