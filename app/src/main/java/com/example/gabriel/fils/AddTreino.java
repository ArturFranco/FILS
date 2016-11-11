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
 * Created by Gabriel on 11/11/2016.
 */

public class AddTreino extends AppCompatActivity {
    private DatabaseReference mFirebaseDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_treino_main);


       // Button upload = (Button) findViewById(R.id.uploadNovoTreino);
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();

        EditText texto = (EditText) findViewById(R.id.addTreinoText);

    }

    protected void uploadTreino (View view){
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        EditText texto = (EditText) findViewById(R.id.addTreinoText);
        mFirebaseDatabaseReference.child("Treinos").push().setValue(texto.getText().toString());
    }


}
