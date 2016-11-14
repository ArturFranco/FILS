package com.example.gabriel.fils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Artur on 14/11/2016.
 */

public class AddRefeicao extends AppCompatActivity {
    private DatabaseReference mFirebaseDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_refeicao_main);


        // Button upload = (Button) findViewById(R.id.uploadNovaRefeicao);
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();

        EditText texto = (EditText) findViewById(R.id.addRefeicaoText);

    }

    protected void uploadRefeicao (View view){
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        EditText texto = (EditText) findViewById(R.id.addRefeicaoText);
        mFirebaseDatabaseReference.child("Refeições").push().setValue(texto.getText().toString());
    }

}