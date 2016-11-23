package com.example.gabriel.fils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Gabriel on 19/11/2016.
 */

public class AddGrupo extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_grupo_main);
        Bundle extras = getIntent().getExtras();
        /*if (extras != null){
            TextView titulo = (TextView) findViewById(R.id.grupoTitulo);
            titulo.setText(extras.getString("titulo"));
        }*/
        String grupo = extras.getString("tipo");

        TextView titulo = (TextView) findViewById(R.id.grupoTitulo);
        titulo.setText("Grupo "+grupo);

        Button botao = (Button) findViewById(R.id.bot);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = getIntent();
                EditText texto = (EditText) findViewById(R.id.testeTexto);
                returnIntent.putExtra("result", texto.getText().toString());
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }
}
