package com.example.gabriel.fils;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
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

/**
 * Created by Felipe on 11/29/2016.
 */

public class AddAluno extends AppCompatActivity {

    private DatabaseReference mFirebaseDatabaseReference;
    private DatabaseReference userDatabaseReference;

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private String email;
    private TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_aluno_main);

        //Pega dados de autenticação
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        //Pega referencia do banco de dados
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        userDatabaseReference = mFirebaseDatabaseReference.child(ProfissionalMainActivity.perfilString).child(user.getUid());

        textView = (TextView) findViewById(R.id.emailAlunoTextView);

        Button buscarButton = (Button) findViewById(R.id.buscarAlunoButton);
        buscarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = textView.getText().toString();
                updateList();
            }
        });

        ImageView imgView = (ImageView) findViewById(R.id.addAlunoBackground);
        Drawable imgDrawable=getResources().getDrawable(R.drawable.background);
        imgView.setImageDrawable(imgDrawable);
    }

    void updateList(){
        //Pega a referencia da lista de alunos encontrados
        mFirebaseDatabaseReference.child("Atletas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final com.google.firebase.database.DataSnapshot dataSnapshot) {
                //Referencia a listview do xml
                ListView listaDeAlunos = (ListView) findViewById(R.id.listaAlunosEncontrados);

                // Construct the data source
                ArrayList<Aluno> arrayOfAlunos = new ArrayList<Aluno>();
                // Create the adapter to convert the array to views
                final AlunosAdapter adapter = new AlunosAdapter(AddAluno.this, arrayOfAlunos);
                // Attach the adapter to a ListView
                listaDeAlunos.setAdapter(adapter);

                //Povoa uma lista com os nomes dos treinos
                Iterator<DataSnapshot> it = dataSnapshot.getChildren().iterator();
                //List<String> list = new ArrayList<String>();
                DataSnapshot aux;
                while (it.hasNext()) {
                    aux = it.next();
                    if (aux.child("Email").getValue().toString().contains(email)) {
                        adapter.add(new Aluno(aux.child("Nome").getValue().toString(), aux.child("PhotoURL").getValue().toString(), aux.getKey()));
                    }
                }

                listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        final Aluno entry = (Aluno) parent.getAdapter().getItem(position);
                        final Dialog dialog = new Dialog(AddAluno.this);

                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.dialog_add_aluno);

                        TextView pergunta = (TextView) dialog.findViewById(R.id.label1);
                        pergunta.setText("Deseja adicionar " + entry.nome + " como atleta?");


                        Button botaoConfirma = (Button) dialog.findViewById(R.id.botaoConfirma);
                        botaoConfirma.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                userDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                                        boolean achou = false;
                                        if(dataSnapshot.hasChild("Alunos")){
                                            Iterator<DataSnapshot> it = dataSnapshot.child("Alunos").getChildren().iterator();
                                            while(it.hasNext()){
                                                if(it.next().getValue().equals(entry.userID)){
                                                    Toast.makeText(AddAluno.this, entry.nome + " já foi cadastrado como atleta",
                                                            Toast.LENGTH_SHORT).show();
                                                    achou = true;
                                                }
                                            }

                                        }

                                        if (!achou){
                                            Toast.makeText(AddAluno.this, "Convite enviado para " + entry.nome,
                                                    Toast.LENGTH_SHORT).show();

                                            //userDatabaseReference.child("Alunos_Pendentes").push().setValue(entry.userID);

                                            if (ProfissionalMainActivity.perfilString.equals("Personais")){
                                                mFirebaseDatabaseReference.child("Atletas").child(entry.userID).child("Notificacoes").push().setValue(new Notificacao("Solicitacao Personal", user.getUid().toString()));
                                            }else{//Nutricionistas
                                                mFirebaseDatabaseReference.child("Atletas").child(entry.userID).child("Notificacoes").push().setValue(new Notificacao("Solicitacao Nutricionista", user.getUid().toString()));
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });

                                finish();

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
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }


}

