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
 * Created by Gabriel on 11/11/2016.
 */

public class AddTreino extends AppCompatActivity {
    private DatabaseReference mFirebaseDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_treino_main);


        Button upload = (Button) findViewById(R.id.uploadNovoTreino);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(), "Toasty!",Toast.LENGTH_LONG);
                toast.show();
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
