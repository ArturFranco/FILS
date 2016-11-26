package com.example.gabriel.fils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Gabriel on 19/11/2016.
 */

public class AddGrupo extends AppCompatActivity {
    private Spinner spinnerGrupo;
    private Spinner spinnerExercicios;
    private ArrayAdapter adapterExercicio;
    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_grupo_main);
        Bundle extras = getIntent().getExtras();
        /*if (extras != null){
            TextView titulo = (TextView) findViewById(R.id.grupoTitulo);
            titulo.setText(extras.getString("titulo"));
        }*/


        //Pega dados de autenticação
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();


        //Pega referencia do banco de dados
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();

        final String titulo = extras.getString("titulo");

        System.out.println(titulo);

        TextView tituloView = (TextView) findViewById(R.id.grupoTitulo);
        tituloView.setText(titulo);

        spinnerGrupo = (Spinner) findViewById(R.id.spinnerGrupo);

        ArrayAdapter adapterGrupo = ArrayAdapter.createFromResource(this, R.array.grupos_musculares, android.R.layout.simple_spinner_dropdown_item);

        spinnerGrupo.setAdapter(adapterGrupo);

        spinnerExercicios = (Spinner) findViewById(R.id.spinnerExercicios);

        mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("temp").child("Treino").child(titulo).child("Exercicios").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final com.google.firebase.database.DataSnapshot dataSnapshot) {
                //Referencia a listview do xml
                ListView listaDeExercicios = (ListView) findViewById(R.id.listaExecicios);

                //Povoa uma lista com os nomes dos exercicios, séries e repetições
                Iterator<DataSnapshot> it = dataSnapshot.getChildren().iterator();
                List<String> list = new ArrayList<String>();
                DataSnapshot aux;
                while (it.hasNext()) {
                    aux = it.next();
                    list.add(aux.child("Descricao").getValue() + " / " + aux.child("Series").getValue() + " / " + aux.child("Repeticoes").getValue());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddGrupo.this, android.R.layout.simple_list_item_1, list);
                listaDeExercicios.setAdapter(adapter);

                listaDeExercicios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        AdapterView.OnItemSelectedListener escolhaGrupo = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String item = spinnerGrupo.getSelectedItem().toString();

                System.out.println(item);

                switch (item) {
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

        Button botaoAdd = (Button) findViewById(R.id.addExerButton);
        botaoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = getIntent();
                Spinner exercicio = (Spinner) findViewById(R.id.spinnerExercicios);
                EditText series = (EditText) findViewById(R.id.serieText);
                EditText repeticoes = (EditText) findViewById(R.id.repText);
                if (series.getText().toString().contentEquals("") || repeticoes.getText().toString().contentEquals("")) {
                    Toast.makeText(getApplicationContext(), "Dados Incompletos", Toast.LENGTH_SHORT).show();
                } else {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("temp").child("Treino").child(titulo).child("Exercicios").child(exercicio.getSelectedItem().toString()).child("Descricao").setValue(exercicio.getSelectedItem().toString());
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("temp").child("Treino").child(titulo).child("Exercicios").child(exercicio.getSelectedItem().toString()).child("Series").setValue(series.getText().toString());
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("temp").child("Treino").child(titulo).child("Exercicios").child(exercicio.getSelectedItem().toString()).child("Repeticoes").setValue(repeticoes.getText().toString());
                }

            }
        });

        Button botaoSalvar = (Button) findViewById(R.id.salvarGrupoButton);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(AddGrupo.this);
                dialog.setTitle("Descrição");
                dialog.setContentView(R.layout.dialog_salvar_grupo);

                mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("temp").child("Treino").child(titulo).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(final com.google.firebase.database.DataSnapshot dataSnapshot) {
                        EditText descText = (EditText) dialog.findViewById(R.id.descricaoText);
                        if (dataSnapshot.child("Descricao").getValue() != null)
                            descText.setText(dataSnapshot.child("Descricao").getValue().toString());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                Button botaoConfirmarDialog = (Button) dialog.findViewById(R.id.botaoConfirma);
                botaoConfirmarDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent returnIntent = getIntent();
                        EditText descText = (EditText) dialog.findViewById(R.id.descricaoText);
                        returnIntent.putExtra("result", descText.getText().toString());
                        mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("temp").child("Treino").child(titulo).child("Descricao").setValue(descText.getText().toString());
                        setResult(RESULT_OK, returnIntent);
                        finish();
                    }
                });

                Button botaoCancelarDialog = (Button) dialog.findViewById(R.id.botaoCancela);
                botaoCancelarDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });

        Button botaoRefazer = (Button) findViewById(R.id.refazerGrupoButton);
        botaoRefazer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("temp").child("Treino").child(titulo).removeValue();
                EditText series = (EditText) findViewById(R.id.serieText);
                EditText repeticoes = (EditText) findViewById(R.id.repText);
                series.setText("");
                repeticoes.setText("");
            }
        });
    }

}
