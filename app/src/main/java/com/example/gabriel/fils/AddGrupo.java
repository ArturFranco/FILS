package com.example.gabriel.fils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Gabriel on 19/11/2016.
 */

public class AddGrupo extends AppCompatActivity {
    private Spinner spinnerGrupo;
    private Spinner spinnerExercicios;
    ArrayAdapter adapterExercicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_grupo_main);
        Bundle extras = getIntent().getExtras();
        /*if (extras != null){
            TextView titulo = (TextView) findViewById(R.id.grupoTitulo);
            titulo.setText(extras.getString("titulo"));
        }*/
        String titulo = extras.getString("titulo");

        TextView tituloView = (TextView) findViewById(R.id.grupoTitulo);
        tituloView.setText(titulo);

        spinnerGrupo = (Spinner) findViewById(R.id.spinnerGrupo);

        ArrayAdapter adapterGrupo = ArrayAdapter.createFromResource(this, R.array.grupos_musculares , android.R.layout.simple_spinner_dropdown_item);

        spinnerGrupo.setAdapter(adapterGrupo);


        spinnerExercicios = (Spinner) findViewById(R.id.spinnerExercicios);

        AdapterView.OnItemSelectedListener escolhaGrupo = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view , int position, long id) {

                String item = spinnerGrupo.getSelectedItem().toString();

                System.out.println(item);

                switch (item){
                    case "Bíceps":
                        adapterExercicio = ArrayAdapter.createFromResource(AddGrupo.this, R.array.biceps, android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case "Tríceps":
                        adapterExercicio = ArrayAdapter.createFromResource(AddGrupo.this, R.array.triceps, android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case "Antebraço":
                        adapterExercicio = ArrayAdapter.createFromResource(AddGrupo.this, R.array.antebraco, android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case "Costas":
                        adapterExercicio = ArrayAdapter.createFromResource(AddGrupo.this, R.array.costas, android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case "Lombar":
                        adapterExercicio = ArrayAdapter.createFromResource(AddGrupo.this, R.array.lombar, android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case "Peito":
                        adapterExercicio = ArrayAdapter.createFromResource(AddGrupo.this, R.array.peito, android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case "Ombros":
                        adapterExercicio = ArrayAdapter.createFromResource(AddGrupo.this, R.array.ombros, android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case "Quadríceps":
                        adapterExercicio = ArrayAdapter.createFromResource(AddGrupo.this, R.array.quadriceps, android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case "Posterior":
                        adapterExercicio = ArrayAdapter.createFromResource(AddGrupo.this, R.array.posterior, android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case "Glúteos":
                        adapterExercicio = ArrayAdapter.createFromResource(AddGrupo.this, R.array.gluteos, android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case "Panturrilhas":
                        adapterExercicio = ArrayAdapter.createFromResource(AddGrupo.this, R.array.panturrilhas, android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case "Abdominais":
                        adapterExercicio = ArrayAdapter.createFromResource(AddGrupo.this, R.array.abdominais, android.R.layout.simple_spinner_dropdown_item);
                        break;
                }

                spinnerExercicios.setAdapter(adapterExercicio);
               // Toast.makeText(getApplicationContext(), "Item escolhido: "+item,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        spinnerGrupo.setOnItemSelectedListener(escolhaGrupo);


        Button botao = (Button) findViewById(R.id.salvarGrupoButton);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = getIntent();
                EditText texto = (EditText) findViewById(R.id.descMusc);
                returnIntent.putExtra("result", texto.getText().toString());
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }
}
