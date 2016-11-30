package com.example.gabriel.fils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by leticialapenda on 30/11/16.
 */

public class PersonalDadosGerais extends AppCompatActivity {

    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private int tipo;

    private String idades;
    private String local_trabalhos;
    private String tempo_experiencias;
    private String informacao_adicionals;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_dados_gerais);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        //Pega referencia do banco de dados
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();


        final EditText idade = (EditText) findViewById(R.id.idade);
        final EditText local_trabalho = (EditText) findViewById(R.id.local_trabalho);
        final EditText tempo_experiencia = (EditText) findViewById(R.id.tempo_experiencia);
        final EditText informacao_adicional = (EditText) findViewById(R.id.informacao_adicional);



        final Button salvar = (Button) findViewById(R.id.salvar);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mFirebaseDatabaseReference.child("Personais").child(user.getUid()).child("Dados_Gerais").child("Idade").setValue(idade.getText().toString());
                mFirebaseDatabaseReference.child("Personais").child(user.getUid()).child("Dados_Gerais").child("LocalTrabalho").setValue(local_trabalho.getText().toString());
                mFirebaseDatabaseReference.child("Personais").child(user.getUid()).child("Dados_Gerais").child("TempoExperiencia").setValue(tempo_experiencia.getText().toString());
                mFirebaseDatabaseReference.child("Personais").child(user.getUid()).child("Dados_Gerais").child("InformacaoAdicional").setValue(informacao_adicional.getText().toString());

                onBackPressed();
            }
        });


        mFirebaseDatabaseReference.child("Personais").child(user.getUid()).child("Dados_Gerais").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final com.google.firebase.database.DataSnapshot dataSnapshot){



                if(dataSnapshot.child("Idade").getValue()!=null){
                    idades = dataSnapshot.child("Idade").getValue().toString();
                    idade.setText(idades);

                }
                if(dataSnapshot.child("LocalTrabalho").getValue()!=null){
                    local_trabalhos = dataSnapshot.child("LocalTrabalho").getValue().toString();
                    local_trabalho.setText(local_trabalhos);

                }
                if(dataSnapshot.child("TempoExperiencia").getValue()!=null){
                    tempo_experiencias = dataSnapshot.child("TempoExperiencia").getValue().toString();
                    tempo_experiencia.setText(tempo_experiencias);

                }
                if(dataSnapshot.child("InformacaoAdicional").getValue()!=null){
                    informacao_adicionals = dataSnapshot.child("InformacaoAdicional").getValue().toString();
                    informacao_adicional.setText(informacao_adicionals);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}



