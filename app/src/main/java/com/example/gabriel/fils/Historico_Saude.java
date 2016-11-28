package com.example.gabriel.fils;

/**
 * Created by leticialapenda on 26/11/16.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;


public class Historico_Saude extends AppCompatActivity {

    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private int tipo;

    private LinearLayout check;

    private CheckBox diabetes;
    private CheckBox hipertensao;
    private CheckBox aterosclerose;
    private CheckBox teve_avc;
    private CheckBox iam;
    private CheckBox doenca_renal;
    private CheckBox doenca_de_figado;
    private CheckBox doenca_de_tireoide;
    private CheckBox outra;

    private CheckBox glicose;
    private CheckBox colesterol;
    private CheckBox triglicerideo;
    private CheckBox createnina;
    private CheckBox acido_urico;
    private CheckBox outra2;

    private CheckBox hipertensao3;
    private CheckBox iam3;
    private CheckBox avc3;
    private CheckBox cirurgia;
    private CheckBox morte_subita;


    private CheckBox tontura;
    private CheckBox dor;
    private CheckBox inchaco;
    private CheckBox falta_ar;
    private CheckBox taquicardia;
    private CheckBox falta_ar_deita;
    private CheckBox apneia;

    private int caso_sim;
    private int caso_nao;

    private String alturas;
    private String pesos;
    private String idades;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        tipo = -1;

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        //Pega referencia do banco de dados
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();


    }




    protected void Click0(View v) {
        setContentView(R.layout.p0_dados_gerais);
        tipo = 0;


        final EditText idade = (EditText) findViewById(R.id.idade);
        final EditText altura = (EditText) findViewById(R.id.altura);
        final EditText peso = (EditText) findViewById(R.id.peso);


        final Button salvar = (Button) findViewById(R.id.salvar);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Dados_Gerais").child("Altura").setValue(altura.getText().toString());
                mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Dados_Gerais").child("Peso").setValue(peso.getText().toString());
                mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Dados_Gerais").child("Idade").setValue(idade.getText().toString());

            VoltarMenu(salvar);
            }
        });



        mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Dados_Gerais").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final com.google.firebase.database.DataSnapshot dataSnapshot){



                if(dataSnapshot.child("Altura").getValue()!=null){
                    alturas = dataSnapshot.child("Altura").getValue().toString();
                    altura.setText(alturas);

                }
                if(dataSnapshot.child("Peso").getValue()!=null){
                    pesos = dataSnapshot.child("Peso").getValue().toString();
                    peso.setText(pesos);

                }
                if(dataSnapshot.child("Idade").getValue()!=null){
                    idades = dataSnapshot.child("Idade").getValue().toString();
                    idade.setText(idades);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    protected void Click1(View v) {
        setContentView(R.layout.p1_problemas_de_saude);
        tipo = 1;

        diabetes = (CheckBox) findViewById(R.id.diabetes);
        hipertensao = (CheckBox) findViewById(R.id.hipertensao);
        aterosclerose = (CheckBox) findViewById(R.id.aterosclerose);
        teve_avc = (CheckBox) findViewById(R.id.avc);
        iam = (CheckBox) findViewById(R.id.iam);
        doenca_renal = (CheckBox) findViewById(R.id.renal);
        doenca_de_figado = (CheckBox) findViewById(R.id.figado);
        doenca_de_tireoide = (CheckBox) findViewById(R.id.tireoide);
        outra = (CheckBox) findViewById(R.id.outra);

        EditText qual = (EditText) findViewById(R.id.qual);

        if (diabetes.isChecked()) {
            mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("Diabetes").setValue(true);
        }
        if (hipertensao.isChecked()) {
            mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("Hipertensao").setValue(true);
        }
        if (aterosclerose.isChecked()) {
            mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("Aterosclerose").setValue(true);
        }
        if (teve_avc.isChecked()) {
            mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("AVC").setValue(true);
        }
        if (iam.isChecked()) {
            mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("IAM").setValue(true);
        }
        if (doenca_renal.isChecked()) {
            mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("Doenca_Renal").setValue(true);
        }
        if (doenca_de_figado.isChecked()) {
            mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("Doenca_de_Figado").setValue(true);
        }
        if (doenca_de_tireoide.isChecked()) {
            mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("Doenca_de_Tireoide").setValue(true);
        }
        if (outra.isChecked()) {
            mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("Outra").setValue(qual.getText().toString());
        }
    }

    protected void Click2(View v) {
        setContentView(R.layout.p2_taxa_alterada);
        tipo = 2;


        glicose = (CheckBox) findViewById(R.id.glicose);
        colesterol = (CheckBox) findViewById(R.id.colesterol);
        triglicerideo = (CheckBox) findViewById(R.id.triglicerideo);
        createnina = (CheckBox) findViewById(R.id.creatinina);
        acido_urico = (CheckBox) findViewById(R.id.acido_urico);
        outra2 = (CheckBox) findViewById(R.id.outra2);

        EditText qual = (EditText) findViewById(R.id.qual);

        if (diabetes.isChecked()) {
            mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Taxas").child("Diabetes").setValue(true);
        }
        if (hipertensao.isChecked()) {
            mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Taxas").child("Hipertensao").setValue(true);
        }
        if (aterosclerose.isChecked()) {
            mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Taxas").child("Aterosclerose").setValue(true);
        }
        if (teve_avc.isChecked()) {
            mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Taxas").child("AVC").setValue(true);
        }
        if (iam.isChecked()) {
            mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Taxas").child("IAM").setValue(true);
        }
        if (outra2.isChecked()) {
            mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Taxas").child("Outra").setValue(qual.getText().toString());
        }

    }

    protected void Click3(View v) {
        setContentView(R.layout.p3_pai_ou_mae);
        tipo = 3;

        hipertensao3 = (CheckBox) findViewById(R.id.hipertensao3);
        iam3 = (CheckBox) findViewById(R.id.iam3);
        avc3 = (CheckBox) findViewById(R.id.avc3);
        cirurgia = (CheckBox) findViewById(R.id.cirurgia);
        morte_subita = (CheckBox) findViewById(R.id.morte_subita);


        if (hipertensao3.isChecked()) {
            mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_familiares").child("Hipertensao").setValue(true);
        }
        if (iam3.isChecked()) {
            mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_familiares").child("IAM").setValue(true);
        }
        if (avc3.isChecked()) {
            mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_familiares").child("AVC").setValue(true);
        }
        if (cirurgia.isChecked()) {
            mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_familiares").child("Cirurgia").setValue(true);
        }
        if (morte_subita.isChecked()) {
            mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_familiares").child("Morte_Subita").setValue(true);
        }

    }

    protected void Click4(View v) {
        setContentView(R.layout.p4_remedios);
        tipo = 4;

        EditText qual = (EditText) findViewById(R.id.qual);
        EditText quanto_tempo = (EditText) findViewById(R.id.quanto_tempo);

        RadioGroup p4 = (RadioGroup)
                findViewById(R.id.radio_remedios);

        switch (p4.getCheckedRadioButtonId()) {
            case R.id.sim:
                caso_sim = 1;
                caso_nao = 0;
                mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Remedios").child("Sim").setValue(true);
                mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Remedios").child("Qual").setValue(false);
                break;
            case R.id.nao:
                caso_nao = 1;
                caso_sim = 0;
                mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Remedios").child("Sim").setValue(false);
                mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Remedios").child("Qual").setValue(true);
                break;
        }


        if (caso_sim == 1) {
            mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Remedios").child("Qual").setValue(qual.getText().toString());
            mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Remedios").child("Quanto_tempo").setValue(quanto_tempo.getText().toString());
        }

    }

    protected void Click5(View v) {
        setContentView(R.layout.p5_repouso);
        tipo = 5;

        tontura = (CheckBox) findViewById(R.id.tontura);
        dor = (CheckBox) findViewById(R.id.dor);
        inchaco = (CheckBox) findViewById(R.id.inchaco);
        falta_ar = (CheckBox) findViewById(R.id.falta_ar);
        taquicardia = (CheckBox) findViewById(R.id.taquicardia);
        falta_ar_deita = (CheckBox) findViewById(R.id.falta_ar_deita);
        apneia = (CheckBox) findViewById(R.id.apneia);


        if (tontura.isChecked()) {
            mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Sintomas_recentes").child("Tontura").setValue(true);
        }
        if (dor.isChecked()) {
            mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Sintomas_recentes").child("Dor").setValue(true);
        }
        if (inchaco.isChecked()) {
            mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Sintomas_recentes").child("Inchaco").setValue(true);
        }
        if (falta_ar.isChecked()) {
            mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Sintomas_recentes").child("Falta_ar").setValue(true);
        }
        if (taquicardia.isChecked()) {
            mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Sintomas_recentes").child("Taquicardia").setValue(true);
        }
        if (falta_ar_deita.isChecked()) {
            mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Sintomas_recentes").child("Falta_ar_deita").setValue(true);
        }
        if (apneia.isChecked()) {
            mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Sintomas_recentes").child("Apneia").setValue(true);
        }

    }



    @Override
    public void onBackPressed () {
        if (tipo == -1) {
            finish();
        } else {
            setContentView(R.layout.menu);
            tipo=-1;
        }
    }


    public void VoltarMenu(View v){
        setContentView(R.layout.menu);
        tipo=-1;
    }
}