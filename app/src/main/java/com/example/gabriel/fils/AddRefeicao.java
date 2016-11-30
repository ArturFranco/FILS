package com.example.gabriel.fils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
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
 * Created by Artur on 24/11/2016.
 */

public class AddRefeicao extends AppCompatActivity {
    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private Spinner spinnerGruposAlimentares;
    private Spinner spinnerAlimentos;
    ArrayAdapter adapterAlimentos;
    private boolean emptylistAlimentos;

    private List<String> listaAlimentosRefeicao = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_refeicao_main);

        //Pega dados de autenticação
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        //Pega referencia do banco de dados
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();

        spinnerGruposAlimentares = (Spinner) findViewById(R.id.spinnerGrupoAlimento);

        ArrayAdapter adapterGrupo = ArrayAdapter.createFromResource(this, R.array.grupos_alimentares , android.R.layout.simple_spinner_dropdown_item);

        spinnerGruposAlimentares.setAdapter(adapterGrupo);

        spinnerAlimentos = (Spinner) findViewById(R.id.spinnerAlimento);

        AdapterView.OnItemSelectedListener escolhaGrupo = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view , int position, long id) {

                final String item = spinnerGruposAlimentares.getSelectedItem().toString();

                System.out.println(item);

                //Pega a referencia da lista de alimentos
                mFirebaseDatabaseReference.child("ALIMENTOS").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(final com.google.firebase.database.DataSnapshot dataSnapshot) {
                        //Referencia a listview do xml
                        //ListView listaDeAlimentosSalvos = (ListView) findViewById(R.id.listaAlimentosSalvos);

                        //Povoa uma lista com os nomes dos alimentos
                        Iterator<DataSnapshot> it = dataSnapshot.getChildren().iterator();
                        List<String> list = new ArrayList<String>();
                        DataSnapshot aux;
                        while (it.hasNext()) {
                            aux = it.next();
                            if(aux.child("Categoria").getValue().toString().equals(item))
                                list.add(aux.child("Descrição dos alimentos").getValue().toString());
                        }

                        //Atribui a lista de nomes a listview
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddRefeicao.this, android.R.layout.simple_spinner_dropdown_item, list);
                        spinnerAlimentos.setAdapter(adapter);

                        /*listaDeAlimentosSalvos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        });*/

                        /*listaDeAtividades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                String entry = (String) parent.getAdapter().getItem(position);
                                final Dialog dialog = new Dialog(NovoTreino.this);


                                if(entry.startsWith("Corrida")){
                                    dialog.setTitle("Relatar Treino");
                                    dialog.setContentView(R.layout.dialog_novotreino_corrida);

                                    TextView nome = (TextView) dialog.findViewById(R.id.treinoNome);
                                    String s = entry.toString();
                                    nome.setText(s.substring(s.lastIndexOf(":") + 1));


                                    //DatabaseReference ref = mFirebaseDatabaseReference.child("Treino").child(s.substring(s.lastIndexOf(":") + 2));
                                    TextView dur = (TextView) dialog.findViewById(R.id.duracaoText);
                                    dur.setText(" " + dataSnapshot.child(s.substring(s.lastIndexOf(":") + 2)).child("Duracao").getValue().toString() + "min");

                                    TextView dis = (TextView) dialog.findViewById(R.id.distText);
                                    dis.setText(" " + dataSnapshot.child(s.substring(s.lastIndexOf(":") + 2)).child("Distancia").getValue().toString() + "km");

                                }else if(entry.startsWith("Outro")){
                                    dialog.setTitle("Relatar Treino");
                                    dialog.setContentView(R.layout.dialog_novotreino_outro);

                                    TextView nome = (TextView) dialog.findViewById(R.id.treinoNome);
                                    String s = entry.toString();
                                    nome.setText(s.substring(s.lastIndexOf(":") + 1));


                                    //DatabaseReference ref = mFirebaseDatabaseReference.child("Treino").child(s.substring(s.lastIndexOf(":") + 2));
                                    TextView dur = (TextView) dialog.findViewById(R.id.duracaoText);
                                    dur.setText(" " + dataSnapshot.child(s.substring(s.lastIndexOf(":") + 2)).child("Duracao").getValue().toString() + "h");

                                }

                                //TODO relatar treino
                                Button botaoConfirma = (Button) dialog.findViewById(R.id.botaoConfirma);
                                botaoConfirma.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                    }
                                });

                                //Cancela o relato de treino
                                Button botaoCancela = (Button) dialog.findViewById(R.id.botaoCancela);
                                botaoCancela.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                    }
                                });

                                dialog.show();
                            }
                        });*/
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                spinnerAlimentos.setAdapter(adapterAlimentos);
                // Toast.makeText(getApplicationContext(), "Item escolhido: "+item,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        };

        spinnerGruposAlimentares.setOnItemSelectedListener(escolhaGrupo);

        Button botaoAdd = (Button) findViewById(R.id.buttonAddAlimento);
        botaoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent returnIntent = getIntent();
                ListView listaDeAlimentosSalvos = (ListView) findViewById(R.id.listaAlimentosSalvos);
                Spinner alimentos = (Spinner) findViewById(R.id.spinnerAlimento);
                EditText qtdAlimento = (EditText) findViewById(R.id.qtdAlimento);

                if (qtdAlimento.getText().toString().contentEquals("")) {
                    Toast.makeText(getApplicationContext(), "Dados Incompletos", Toast.LENGTH_SHORT).show();
                } else {

                    listaAlimentosRefeicao.add(alimentos.getSelectedItem().toString() + " / " + qtdAlimento.getText().toString());

                    emptylistAlimentos = listaAlimentosRefeicao.isEmpty();

                    //Atribui a lista de nomes a listview
                    ArrayAdapter<String> adapterAlimentos = new ArrayAdapter<String>(AddRefeicao.this, android.R.layout.simple_list_item_1, listaAlimentosRefeicao);
                    listaDeAlimentosSalvos.setAdapter(adapterAlimentos);

                    //mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Refeições").child("Nome da Refeição").child(alimentos.getSelectedItem().toString());
                    //mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Refeições").child("Nome da Refeição").child(alimentos.getSelectedItem().toString()).child("Quantidade (g)").setValue(qtdAlimento.getText().toString());
                }
            }
        });

        Button botaoSalvar = (Button) findViewById(R.id.buttonSalvarRefeicao);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(AddRefeicao.this);
                //dialog.setTitle("Descrição");
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_salvar_refeicao);

                /*mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Refeições").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(final com.google.firebase.database.DataSnapshot dataSnapshot) {
                        EditText descTextRef = (EditText) dialog.findViewById(R.id.descricaoTextRef);
                        if (dataSnapshot.getValue() != null)
                            descTextRef.setText(dataSnapshot.getValue().toString());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });*/

                Button botaoConfirmarDialog = (Button) dialog.findViewById(R.id.botaoConfirma);
                botaoConfirmarDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent returnIntent = getIntent();
                        EditText descTextRef = (EditText) dialog.findViewById(R.id.descricaoTextRef);
                        DatabaseReference mestreSousa = mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Refeições").child(descTextRef.getText().toString());

                        Iterator<String> it = listaAlimentosRefeicao.iterator();
                        String aux;
                        while (it.hasNext()) {
                            aux = it.next();
                            String[] infoAlimento = aux.split(" / ");
                            mestreSousa.child(infoAlimento[0]).child("Quantidade (g)").setValue(infoAlimento[1]);
                        }
                        //mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Refeições").child(descTextRef.getText().toString()).child(alimentos.getSelectedItem().toString());
                        //mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Refeições").child(alimentos.getSelectedItem().toString()).child("Quantidade (g)").setValue(qtdAlimento.getText().toString());
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

                if(emptylistAlimentos){
                    Toast.makeText(getApplicationContext(), "Lista está vazia", Toast.LENGTH_SHORT).show();
                }else{
                    dialog.show();
                }

            }
        });

        Button botaoRefazer = (Button) findViewById(R.id.buttonRefazerRefeicao);
        botaoRefazer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Refeições").removeValue();
                EditText qtdAlimento = (EditText) findViewById(R.id.qtdAlimento);
                qtdAlimento.setText("");
            }
        });
    }
}


