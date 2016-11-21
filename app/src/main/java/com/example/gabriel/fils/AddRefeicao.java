package com.example.gabriel.fils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

/**
 * Created by Artur on 14/11/2016.
 */

public class AddRefeicao extends AppCompatActivity {
    private DatabaseReference mFirebaseDatabaseReference;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_refeicao_main);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.elvGruposAlimentares);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        /*Button upload = (Button) findViewById(R.id.uploadNovaRefeicao);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
                EditText texto = (EditText) findViewById(R.id.addRefeicaoText);
                mFirebaseDatabaseReference.child("Refeições").push().setValue(texto.getText().toString());

                Toast toast = Toast.makeText(AddRefeicao.this, "Refeição Salva",Toast.LENGTH_LONG);
                toast.show();

                finish();
            }
        });*/

    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Grupo A");
        listDataHeader.add("Grupo B");
        listDataHeader.add("Grupo C");

        // Adding child data
        List<String> grupoA = new ArrayList<String>();
        grupoA.add("Arroz");
        grupoA.add("Aveia");

        List<String> grupoB = new ArrayList<String>();
        grupoB.add("Alface");
        grupoB.add("Tomate");

        List<String> grupoC = new ArrayList<String>();
        grupoC.add("Manteiga");

        listDataChild.put(listDataHeader.get(0), grupoA); // Header, Child data
        listDataChild.put(listDataHeader.get(1), grupoB);
        listDataChild.put(listDataHeader.get(2), grupoC);
    }

   /* protected void uploadTreino (View view){
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        EditText texto = (EditText) findViewById(R.id.addTreinoText);
        mFirebaseDatabaseReference.child("Treinos").push().setValue(texto.getText().toString());

        String toastText = "Treino Adicionado!";

        Context context = getApplicationContext();

        Toast toast = Toast.makeText(context, toastText,Toast.LENGTH_LONG);
        toast.show();

//        Intent intent = new Intent(AddTreino.this, NovoTreino.class);
//        startActivity(intent);
    }
*/

}