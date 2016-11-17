package com.example.gabriel.fils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Gabriel on 11/11/2016.
 */

public class AddTreino extends AppCompatActivity {
    private DatabaseReference mFirebaseDatabaseReference;
    private int tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_treino_musculacao);
        tipo = 1;



       /* Button upload = (Button) findViewById(R.id.uploadNovoTreino);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
                EditText texto = (EditText) findViewById(R.id.addTreinoText);
                mFirebaseDatabaseReference.child("Treinos").push().setValue(texto.getText().toString());

                Toast toast = Toast.makeText(AddTreino.this, "Treino Salvo",Toast.LENGTH_LONG);
                toast.show();

                finish();
            }
        });*/

        /*ImageButton avancar = (ImageButton) findViewById(R.id.fowardAddTreino);
        avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               setContentView(R.layout.add_treino_corrida);
            }

        });*/

    }

   protected void uploadTreino (View view){
       mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
       EditText texto = (EditText) findViewById(R.id.addTreinoText);
       mFirebaseDatabaseReference.child("Treinos").push().setValue(texto.getText().toString());

       Toast toast = Toast.makeText(AddTreino.this, "Treino Salvo",Toast.LENGTH_LONG);
       toast.show();

       finish();

//        Intent intent = new Intent(AddTreino.this, NovoTreino.class);
//        startActivity(intent);
    }

    protected void passarTipoTreino (View view){
        if(tipo == 1){
            setContentView(R.layout.add_treino_corrida);
            tipo = 2;
        }else if(tipo == 2){
            setContentView(R.layout.add_treino_marcial);
            tipo = 3;
        }else if(tipo == 3){
            setContentView(R.layout.add_treino_outro);
            tipo = 4;
        }else{
            setContentView(R.layout.add_treino_musculacao);
            tipo = 1;
        }
    }

    protected void voltarTipoTreino (View view){
        if(tipo == 1){
            setContentView(R.layout.add_treino_outro);
            tipo = 4;
        }else if(tipo == 2){
            setContentView(R.layout.add_treino_musculacao);
            tipo = 1;
        }else if(tipo == 3){
            setContentView(R.layout.add_treino_corrida);
            tipo = 2;
        }else{
            setContentView(R.layout.add_treino_marcial);
            tipo = 3;
        }
    }

    protected void clickA(View view){
        CheckBox checkB = (CheckBox) findViewById(R.id.checkBoxB);
        CheckBox checkC = (CheckBox) findViewById(R.id.checkBoxC);
        CheckBox checkD = (CheckBox) findViewById(R.id.checkBoxD);
        CheckBox checkE = (CheckBox) findViewById(R.id.checkBoxE);
        checkB.setChecked(false);
        checkC.setChecked(false);
        checkD.setChecked(false);
        checkE.setChecked(false);

        LinearLayout grupoA = (LinearLayout) findViewById(R.id.grupoAView);
        grupoA.setVisibility(View.VISIBLE);
        grupoA.setClickable(true);

        LinearLayout grupoB = (LinearLayout) findViewById(R.id.grupoBView);
        grupoB.setVisibility(View.INVISIBLE);
        grupoB.setClickable(false);

        LinearLayout grupoC = (LinearLayout) findViewById(R.id.grupoCView);
        grupoC.setVisibility(View.INVISIBLE);
        grupoC.setClickable(false);

        LinearLayout grupoD = (LinearLayout) findViewById(R.id.grupoDView);
        grupoD.setVisibility(View.INVISIBLE);
        grupoD.setClickable(false);

        LinearLayout grupoE = (LinearLayout) findViewById(R.id.grupoEView);
        grupoE.setVisibility(View.INVISIBLE);
        grupoE.setClickable(false);
    }
    protected void clickB(View view){
        CheckBox checkA = (CheckBox) findViewById(R.id.checkBoxA);
        CheckBox checkC = (CheckBox) findViewById(R.id.checkBoxC);
        CheckBox checkD = (CheckBox) findViewById(R.id.checkBoxD);
        CheckBox checkE = (CheckBox) findViewById(R.id.checkBoxE);
        checkA.setChecked(false);
        checkC.setChecked(false);
        checkD.setChecked(false);
        checkE.setChecked(false);

        LinearLayout grupoA = (LinearLayout) findViewById(R.id.grupoAView);
        grupoA.setVisibility(View.VISIBLE);
        grupoA.setClickable(true);

        LinearLayout grupoB = (LinearLayout) findViewById(R.id.grupoBView);
        grupoB.setVisibility(View.VISIBLE);
        grupoB.setClickable(true);

        LinearLayout grupoC = (LinearLayout) findViewById(R.id.grupoCView);
        grupoC.setVisibility(View.INVISIBLE);
        grupoC.setClickable(false);

        LinearLayout grupoD = (LinearLayout) findViewById(R.id.grupoDView);
        grupoD.setVisibility(View.INVISIBLE);
        grupoD.setClickable(false);

        LinearLayout grupoE = (LinearLayout) findViewById(R.id.grupoEView);
        grupoE.setVisibility(View.INVISIBLE);
        grupoE.setClickable(false);
    }
    protected void clickC(View view){
        CheckBox checkB = (CheckBox) findViewById(R.id.checkBoxB);
        CheckBox checkA = (CheckBox) findViewById(R.id.checkBoxA);
        CheckBox checkD = (CheckBox) findViewById(R.id.checkBoxD);
        CheckBox checkE = (CheckBox) findViewById(R.id.checkBoxE);
        checkB.setChecked(false);
        checkA.setChecked(false);
        checkD.setChecked(false);
        checkE.setChecked(false);

        LinearLayout grupoA = (LinearLayout) findViewById(R.id.grupoAView);
        grupoA.setVisibility(View.VISIBLE);
        grupoA.setClickable(true);

        LinearLayout grupoB = (LinearLayout) findViewById(R.id.grupoBView);
        grupoB.setVisibility(View.VISIBLE);
        grupoB.setClickable(true);

        LinearLayout grupoC = (LinearLayout) findViewById(R.id.grupoCView);
        grupoC.setVisibility(View.VISIBLE);
        grupoC.setClickable(true);

        LinearLayout grupoD = (LinearLayout) findViewById(R.id.grupoDView);
        grupoD.setVisibility(View.INVISIBLE);
        grupoD.setClickable(false);

        LinearLayout grupoE = (LinearLayout) findViewById(R.id.grupoEView);
        grupoE.setVisibility(View.INVISIBLE);
        grupoE.setClickable(false);
    }
    protected void clickD(View view){
        CheckBox checkB = (CheckBox) findViewById(R.id.checkBoxB);
        CheckBox checkC = (CheckBox) findViewById(R.id.checkBoxC);
        CheckBox checkA = (CheckBox) findViewById(R.id.checkBoxA);
        CheckBox checkE = (CheckBox) findViewById(R.id.checkBoxE);
        checkB.setChecked(false);
        checkC.setChecked(false);
        checkA.setChecked(false);
        checkE.setChecked(false);

        LinearLayout grupoA = (LinearLayout) findViewById(R.id.grupoAView);
        grupoA.setVisibility(View.VISIBLE);
        grupoA.setClickable(true);

        LinearLayout grupoB = (LinearLayout) findViewById(R.id.grupoBView);
        grupoB.setVisibility(View.VISIBLE);
        grupoB.setClickable(true);

        LinearLayout grupoC = (LinearLayout) findViewById(R.id.grupoCView);
        grupoC.setVisibility(View.VISIBLE);
        grupoC.setClickable(true);

        LinearLayout grupoD = (LinearLayout) findViewById(R.id.grupoDView);
        grupoD.setVisibility(View.VISIBLE);
        grupoD.setClickable(true);

        LinearLayout grupoE = (LinearLayout) findViewById(R.id.grupoEView);
        grupoE.setVisibility(View.INVISIBLE);
        grupoE.setClickable(false);
    }
    protected void clickE(View view){
        CheckBox checkB = (CheckBox) findViewById(R.id.checkBoxB);
        CheckBox checkC = (CheckBox) findViewById(R.id.checkBoxC);
        CheckBox checkD = (CheckBox) findViewById(R.id.checkBoxD);
        CheckBox checkA = (CheckBox) findViewById(R.id.checkBoxA);
        checkB.setChecked(false);
        checkC.setChecked(false);
        checkD.setChecked(false);
        checkA.setChecked(false);

        LinearLayout grupoA = (LinearLayout) findViewById(R.id.grupoAView);
        grupoA.setVisibility(View.VISIBLE);
        grupoA.setClickable(true);

        LinearLayout grupoB = (LinearLayout) findViewById(R.id.grupoBView);
        grupoB.setVisibility(View.VISIBLE);
        grupoB.setClickable(true);

        LinearLayout grupoC = (LinearLayout) findViewById(R.id.grupoCView);
        grupoC.setVisibility(View.VISIBLE);
        grupoC.setClickable(true);

        LinearLayout grupoD = (LinearLayout) findViewById(R.id.grupoDView);
        grupoD.setVisibility(View.VISIBLE);
        grupoD.setClickable(true);

        LinearLayout grupoE = (LinearLayout) findViewById(R.id.grupoEView);
        grupoE.setVisibility(View.VISIBLE);
        grupoE.setClickable(true);
    }


}
