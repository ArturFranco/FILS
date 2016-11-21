package com.example.gabriel.fils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Lucas Felix on 14/11/2016.
 */

public class Metas extends AppCompatActivity {

    Spinner metas_opcoes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.metas_main);

        metas_opcoes = (Spinner) findViewById(R.id.spinnerMetas);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.metas_escolher , android.R.layout.simple_spinner_dropdown_item);
        metas_opcoes.setAdapter(adapter);

        //Seleciona apetando o botao
        Button ok = (Button) findViewById(R.id.buttonOk);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = metas_opcoes.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(), "Item escolhido: "+item,Toast.LENGTH_SHORT).show();
            }
        });

        //Seleciona apenas clicando
        AdapterView.OnItemSelectedListener escolha = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view , int position, long id) {
                String item = metas_opcoes.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(), "Item escolhido: "+item,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        metas_opcoes.setOnItemSelectedListener(escolha);

    }
}
