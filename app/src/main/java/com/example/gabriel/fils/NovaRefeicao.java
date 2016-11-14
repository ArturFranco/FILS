package com.example.gabriel.fils;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Artur on 10/11/2016.
 *
 * Tela que lista as refeições cadastrados e permite casdastrar outras
 */

public class NovaRefeicao extends AppCompatActivity {

    private DatabaseReference mFirebaseDatabaseReference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nova_refeicao_main);

        //Pega referencia do banco de dados
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();

        //Pega a referencia da lista de refeicoes
        mFirebaseDatabaseReference.child("Refeições").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                //Referencia a listview do xml
                ListView listaDeAtividades = (ListView) findViewById(R.id.listaAtividadesRef);

                //Povoa uma lista com os nomes das refeições
                Iterator<DataSnapshot> it = dataSnapshot.getChildren().iterator();
                List<String> list = new ArrayList<String>();
                while (it.hasNext())
                    list.add(it.next().getValue().toString());

                //Atribui a lista de nomes a listview
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(NovaRefeicao.this,android.R.layout.simple_list_item_1, list);
                listaDeAtividades.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        //TODO adicionar a funcao de adicionar novas refeições
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addRefeicaoButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NovaRefeicao.this, AddRefeicao.class);
                startActivity(intent);
            }
        });
    }
}


