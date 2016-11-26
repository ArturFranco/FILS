package com.example.gabriel.fils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Gabriel on 11/11/2016.
 */

public class AddTreino extends AppCompatActivity {
    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private int tipo;

    private LinearLayout grupoA;
    private LinearLayout grupoB;
    private LinearLayout grupoC;
    private LinearLayout grupoD;
    private LinearLayout grupoE;

    private CheckBox checkA;
    private CheckBox checkB;
    private CheckBox checkC;
    private CheckBox checkD;
    private CheckBox checkE;

    private Spinner tiposOutro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_treino_musculacao);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        tipo = 1;

        grupoA = (LinearLayout) findViewById(R.id.grupoAView);
        grupoB = (LinearLayout) findViewById(R.id.grupoBView);
        grupoC = (LinearLayout) findViewById(R.id.grupoCView);
        grupoD = (LinearLayout) findViewById(R.id.grupoDView);
        grupoE = (LinearLayout) findViewById(R.id.grupoEView);

        checkA = (CheckBox) findViewById(R.id.checkBoxA);
        checkB = (CheckBox) findViewById(R.id.checkBoxB);
        checkC = (CheckBox) findViewById(R.id.checkBoxC);
        checkD = (CheckBox) findViewById(R.id.checkBoxD);
        checkE = (CheckBox) findViewById(R.id.checkBoxE);
        

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
      /* mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
       EditText texto = (EditText) findViewById(R.id.addTreinoText);
       mFirebaseDatabaseReference.child("Treinos").push().setValue(texto.getText().toString());*/

       Toast toast = Toast.makeText(AddTreino.this, "Função Ainda Não Implementada",Toast.LENGTH_LONG);
       toast.show();

       finish();

//        Intent intent = new Intent(AddTreino.this, NovoTreino.class);
//        startActivity(intent);
    }

    protected void uploadCorrida(View view){
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        EditText desc = (EditText) findViewById(R.id.descCorrida);
        EditText dur = (EditText) findViewById(R.id.durCorrida);
        EditText dist = (EditText) findViewById(R.id.distCorrida);
        String descricao = desc.getText().toString();

        if(desc.getText().toString().contentEquals("") || dur.getText().toString().contentEquals("") || dist.getText().toString().contentEquals("")){
            Toast.makeText(getApplicationContext(), "Dados Incompletos", Toast.LENGTH_SHORT).show();
        }else {

            mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Treinos").child(descricao).child("Tipo").setValue("Corrida");
            mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Treinos").child(descricao).child("Descricao").setValue(descricao);
            mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Treinos").child(descricao).child("Duracao").setValue(dur.getText().toString());
            mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Treinos").child(descricao).child("Distancia").setValue(dist.getText().toString());

            Toast toast = Toast.makeText(AddTreino.this, "Treino Salvo", Toast.LENGTH_LONG);
            toast.show();

            finish();
        }
    }

    protected void uploadOutro(View view){
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        Spinner tipoOutro = (Spinner) findViewById(R.id.spinnerOutro);
        EditText durOutro = (EditText) findViewById(R.id.duracaoOutro);
        String descricao = tipoOutro.getSelectedItem().toString();

        if(tipoOutro.getSelectedItem().toString().contentEquals("") || durOutro.getText().toString().contentEquals("")){
            Toast.makeText(getApplicationContext(), "Dados Incompletos", Toast.LENGTH_SHORT).show();
        }else {
            mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Treinos").child(descricao).child("Tipo").setValue("Outro");
            mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Treinos").child(descricao).child("Descricao").setValue(descricao);
            mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Treinos").child(descricao).child("Duracao").setValue(durOutro.getText().toString());
            Toast toast = Toast.makeText(AddTreino.this, "Treino Salvo",Toast.LENGTH_LONG);
            toast.show();
            finish();
        }

    }

    protected void passarTipoTreino (View view){
        if(tipo == 1){
            setContentView(R.layout.add_treino_corrida);
            tipo = 2;
        }else if(tipo == 2){
            setContentView(R.layout.add_treino_outro);
            tiposOutro = (Spinner) findViewById(R.id.spinnerOutro);

            ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.outros_esportes , android.R.layout.simple_spinner_dropdown_item);
            tiposOutro.setAdapter(adapter);
            tipo = 3;
        }else{
            setContentView(R.layout.add_treino_musculacao);
            grupoA = (LinearLayout) findViewById(R.id.grupoAView);
            grupoB = (LinearLayout) findViewById(R.id.grupoBView);
            grupoC = (LinearLayout) findViewById(R.id.grupoCView);
            grupoD = (LinearLayout) findViewById(R.id.grupoDView);
            grupoE = (LinearLayout) findViewById(R.id.grupoEView);

            checkA = (CheckBox) findViewById(R.id.checkBoxA);
            checkB = (CheckBox) findViewById(R.id.checkBoxB);
            checkC = (CheckBox) findViewById(R.id.checkBoxC);
            checkD = (CheckBox) findViewById(R.id.checkBoxD);
            checkE = (CheckBox) findViewById(R.id.checkBoxE);
            tipo = 1;
        }
    }

    protected void voltarTipoTreino (View view){
        if(tipo == 1){
            setContentView(R.layout.add_treino_outro);
            tiposOutro = (Spinner) findViewById(R.id.spinnerOutro);

            ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.outros_esportes , android.R.layout.simple_spinner_dropdown_item);
            tiposOutro.setAdapter(adapter);
            tipo = 3;
        }else if(tipo == 2){
            setContentView(R.layout.add_treino_musculacao);
            grupoA = (LinearLayout) findViewById(R.id.grupoAView);
            grupoB = (LinearLayout) findViewById(R.id.grupoBView);
            grupoC = (LinearLayout) findViewById(R.id.grupoCView);
            grupoD = (LinearLayout) findViewById(R.id.grupoDView);
            grupoE = (LinearLayout) findViewById(R.id.grupoEView);

            checkA = (CheckBox) findViewById(R.id.checkBoxA);
            checkB = (CheckBox) findViewById(R.id.checkBoxB);
            checkC = (CheckBox) findViewById(R.id.checkBoxC);
            checkD = (CheckBox) findViewById(R.id.checkBoxD);
            checkE = (CheckBox) findViewById(R.id.checkBoxE);
            tipo = 1;
        }else{
            setContentView(R.layout.add_treino_corrida);
            tipo = 2;
        }
    }

    protected void clickA(View view){
        checkB.setChecked(false);
        checkC.setChecked(false);
        checkD.setChecked(false);
        checkE.setChecked(false);

        if (checkA.isChecked()){
            grupoA.setVisibility(View.VISIBLE);
            grupoA.setClickable(true);
        }else{
            grupoA.setVisibility(View.INVISIBLE);
            grupoA.setClickable(false);
        }

        grupoB.setVisibility(View.INVISIBLE);
        grupoB.setClickable(false);

        grupoC.setVisibility(View.INVISIBLE);
        grupoC.setClickable(false);

        grupoD.setVisibility(View.INVISIBLE);
        grupoD.setClickable(false);

        grupoE.setVisibility(View.INVISIBLE);
        grupoE.setClickable(false);
    }
    protected void clickB(View view){
        checkA.setChecked(false);
        checkC.setChecked(false);
        checkD.setChecked(false);
        checkE.setChecked(false);

        if(checkB.isChecked()){
            grupoA.setVisibility(View.VISIBLE);
            grupoA.setClickable(true);


            grupoB.setVisibility(View.VISIBLE);
            grupoB.setClickable(true);
        }else {
            grupoA.setVisibility(View.INVISIBLE);
            grupoA.setClickable(false);


            grupoB.setVisibility(View.INVISIBLE);
            grupoB.setClickable(false);
        }

        grupoC.setVisibility(View.INVISIBLE);
        grupoC.setClickable(false);

        grupoD.setVisibility(View.INVISIBLE);
        grupoD.setClickable(false);

        grupoE.setVisibility(View.INVISIBLE);
        grupoE.setClickable(false);
    }
    protected void clickC(View view){
        checkB.setChecked(false);
        checkA.setChecked(false);
        checkD.setChecked(false);
        checkE.setChecked(false);

        if (checkC.isChecked()) {
            grupoA.setVisibility(View.VISIBLE);
            grupoA.setClickable(true);

            grupoB.setVisibility(View.VISIBLE);
            grupoB.setClickable(true);

            grupoC.setVisibility(View.VISIBLE);
            grupoC.setClickable(true);
        }else {
            grupoA.setVisibility(View.INVISIBLE);
            grupoA.setClickable(false);

            grupoB.setVisibility(View.INVISIBLE);
            grupoB.setClickable(false);

            grupoC.setVisibility(View.INVISIBLE);
            grupoC.setClickable(false);
        }
        grupoD.setVisibility(View.INVISIBLE);
        grupoD.setClickable(false);

        grupoE.setVisibility(View.INVISIBLE);
        grupoE.setClickable(false);
    }
    protected void clickD(View view){
        checkB.setChecked(false);
        checkC.setChecked(false);
        checkA.setChecked(false);
        checkE.setChecked(false);

        if(checkD.isChecked()) {
            grupoA.setVisibility(View.VISIBLE);
            grupoA.setClickable(true);

            grupoB.setVisibility(View.VISIBLE);
            grupoB.setClickable(true);

            grupoC.setVisibility(View.VISIBLE);
            grupoC.setClickable(true);

            grupoD.setVisibility(View.VISIBLE);
            grupoD.setClickable(true);
        }else{
            grupoA.setVisibility(View.INVISIBLE);
            grupoA.setClickable(false);

            grupoB.setVisibility(View.INVISIBLE);
            grupoB.setClickable(false);

            grupoC.setVisibility(View.INVISIBLE);
            grupoC.setClickable(false);

            grupoD.setVisibility(View.INVISIBLE);
            grupoD.setClickable(false);
        }
        grupoE.setVisibility(View.INVISIBLE);
        grupoE.setClickable(false);
    }
    protected void clickE(View view){
        checkB.setChecked(false);
        checkC.setChecked(false);
        checkD.setChecked(false);
        checkA.setChecked(false);
        if(checkE.isChecked()){
            grupoA.setVisibility(View.VISIBLE);
            grupoA.setClickable(true);

            grupoB.setVisibility(View.VISIBLE);
            grupoB.setClickable(true);

            grupoC.setVisibility(View.VISIBLE);
            grupoC.setClickable(true);

            grupoD.setVisibility(View.VISIBLE);
            grupoD.setClickable(true);

            grupoE.setVisibility(View.VISIBLE);
            grupoE.setClickable(true);
        }else{
            grupoA.setVisibility(View.INVISIBLE);
            grupoA.setClickable(false);

            grupoB.setVisibility(View.INVISIBLE);
            grupoB.setClickable(false);

            grupoC.setVisibility(View.INVISIBLE);
            grupoC.setClickable(false);

            grupoD.setVisibility(View.INVISIBLE);
            grupoD.setClickable(false);

            grupoE.setVisibility(View.INVISIBLE);
            grupoE.setClickable(false);
        }
    }

    protected void groupAClick(View view){
        Intent intent = new Intent(AddTreino.this, AddGrupo.class);
        intent.putExtra("titulo", "Grupo A");
        startActivityForResult(intent, 1);
    }

    protected void groupBClick(View v){
        Intent intent = new Intent(AddTreino.this, AddGrupo.class);
        intent.putExtra("titulo", "Grupo B");
        startActivityForResult(intent, 2);
    }
    protected void groupCClick(View v){
        Intent intent = new Intent(AddTreino.this, AddGrupo.class);
        intent.putExtra("titulo", "Grupo C");
        startActivityForResult(intent, 3);
    }
    protected void groupDClick(View v){
        Intent intent = new Intent(AddTreino.this, AddGrupo.class);
        intent.putExtra("titulo", "Grupo D");
        startActivityForResult(intent, 4);
    }
    protected void groupEClick(View v){
        Intent intent = new Intent(AddTreino.this, AddGrupo.class);
        intent.putExtra("titulo", "Grupo E");
        startActivityForResult(intent, 5);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                TextView grupoAdesc = (TextView) findViewById(R.id.grupoAdescription);
                grupoAdesc.setText(data.getStringExtra("result"));
            }
        }else if(requestCode == 2){
            if(resultCode == Activity.RESULT_OK){
                TextView grupoBdesc = (TextView) findViewById(R.id.grupoBdescription);
                grupoBdesc.setText(data.getStringExtra("result"));
            }
        }else if(requestCode == 3){
            if(resultCode == Activity.RESULT_OK){
                TextView grupoCdesc = (TextView) findViewById(R.id.grupoCdescription);
                grupoCdesc.setText(data.getStringExtra("result"));
            }
        }else if(requestCode == 4){
            if(resultCode == Activity.RESULT_OK){
                TextView grupoDdesc = (TextView) findViewById(R.id.grupoDdescription);
                grupoDdesc.setText(data.getStringExtra("result"));
            }
        }else if(requestCode == 5){
            if(resultCode == Activity.RESULT_OK){
                TextView grupoEdesc = (TextView) findViewById(R.id.grupoEdescription);
                grupoEdesc.setText(data.getStringExtra("result"));
            }
        }
    }
}
