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

/**
 * Created by Artur on 14/11/2016.
 */

public class AddRefeicao extends AppCompatActivity {
    private DatabaseReference mFirebaseDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_refeicao_main);


        Button upload = (Button) findViewById(R.id.uploadNovaRefeicao);
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
        });

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