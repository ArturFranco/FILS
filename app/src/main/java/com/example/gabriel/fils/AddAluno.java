package com.example.gabriel.fils;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Felipe on 11/29/2016.
 */

public class AddAluno extends AppCompatActivity {

    private DatabaseReference mFirebaseDatabaseReference;
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
        textView = (TextView) findViewById(R.id.emailAlunoTextView);

        Button buscarButton = (Button) findViewById(R.id.buscarAlunoButton);
        buscarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = textView.getText().toString();
                textView.setText("");
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
                    if (aux.child("Email").getValue().equals(email)) {
                        adapter.add(new Aluno(aux.child("Nome").getValue().toString(), aux.child("PhotoURL").getValue().toString(), aux.getKey()));
                    }
                }

                listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Aluno entry = (Aluno) parent.getAdapter().getItem(position);
                        //ProfissionalMainActivity.alunoAtual = entry.userID;

                        //Intent intent = new Intent(AddAluno.this, AlunoActivity.class);
                        //startActivity(intent);
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

