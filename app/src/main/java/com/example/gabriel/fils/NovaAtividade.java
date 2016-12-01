package com.example.gabriel.fils;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Created by Gabriel on 28/10/2016.
 *
 * Tela para adicionar nova atividade realizada
 */
public class NovaAtividade extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nova_atividade_main);

        //Quando clica em Treinos, troca para a tela de treinos
        Button treinoButton = (Button) findViewById(R.id.treinoButton);
        treinoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NovaAtividade.this, NovoTreino.class);
                startActivity(intent);
            }
        });

        //Quando clica em Refeições, troca para a tela de refeições
        Button refeicaoButton = (Button) findViewById(R.id.refeicaoButton);
        refeicaoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NovaAtividade.this, NovaRefeicao.class);
                startActivity(intent);
            }
        });

        ImageView imgView = (ImageView) findViewById(R.id.addTreinos);
        Drawable imgDrawable = getResources().getDrawable(R.drawable.addtreino);
        imgView.setImageDrawable(imgDrawable);

        imgView = (ImageView) findViewById(R.id.addrefeicao);
        imgDrawable = getResources().getDrawable(R.drawable.addrefeicao);
        imgView.setImageDrawable(imgDrawable);

    }

    @Override
    protected void onStart() {
        super.onStart();
//
    }

}
