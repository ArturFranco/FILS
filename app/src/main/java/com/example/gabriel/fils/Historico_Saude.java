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

    private String quals;

    private String trues = "true";
    private String falses = "false";


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



    //---------------------------------------------------------
    //CLICK0
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


        final Button proximo = (Button) findViewById(R.id.proximo);
        proximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Dados_Gerais").child("Altura").setValue(altura.getText().toString());
                mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Dados_Gerais").child("Peso").setValue(peso.getText().toString());
                mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Dados_Gerais").child("Idade").setValue(idade.getText().toString());

                Click1(proximo);
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



    //--------------------------------------------------------
    //CLICK1
    protected void Click1(View v) {
        setContentView(R.layout.p1_problemas_de_saude);
        tipo = 1;

        final CheckBox diabetes = (CheckBox) findViewById(R.id.diabetes);
        final CheckBox hipertensao = (CheckBox) findViewById(R.id.hipertensao);
        final CheckBox aterosclerose = (CheckBox) findViewById(R.id.aterosclerose);
        final CheckBox teve_avc = (CheckBox) findViewById(R.id.avc);
        final CheckBox iam = (CheckBox) findViewById(R.id.iam);
        final CheckBox doenca_renal = (CheckBox) findViewById(R.id.renal);
        final CheckBox doenca_de_figado = (CheckBox) findViewById(R.id.figado);
        final CheckBox doenca_de_tireoide = (CheckBox) findViewById(R.id.tireoide);
        final CheckBox outra = (CheckBox) findViewById(R.id.outra);

        final EditText qual = (EditText) findViewById(R.id.qual);


        final Button salvar = (Button) findViewById(R.id.salvar);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (diabetes.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("Diabetes").setValue(trues.toString());
                }
                else{
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("Diabetes").setValue(falses.toString());
                }


                if (hipertensao.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("Hipertensao").setValue(trues.toString());
                }
                else{
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("Hipertensao").setValue(falses.toString());

                }


                if (aterosclerose.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("Aterosclerose").setValue(trues.toString());
                }
                else{
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("Aterosclerose").setValue(falses.toString());
                }


                if (teve_avc.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("AVC").setValue(trues.toString());
                }
                else {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("AVC").setValue(falses.toString());
                }


                if (iam.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("IAM").setValue(trues.toString());
                }
                else{
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("IAM").setValue(falses.toString());
                }


                if (doenca_renal.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("Doenca_Renal").setValue(trues.toString());
                }
                else {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("Doenca_Renal").setValue(falses.toString());
                }


                if (doenca_de_figado.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("Doenca_de_Figado").setValue(trues.toString());
                }
                else
                {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("Doenca_de_Figado").setValue(falses.toString());
                }


                if (doenca_de_tireoide.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("Doenca_de_Tireoide").setValue(trues.toString());
                }
                else{
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("Doenca_de_Tireoide").setValue(falses.toString());
                }


                if (outra.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("Outra").setValue(qual.getText().toString());
                }else {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("Outra").setValue(null);
                }

                VoltarMenu(salvar);
            }
        });



        final Button proximo = (Button) findViewById(R.id.proximo);
        proximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (diabetes.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("Diabetes").setValue(trues.toString());
                }
                else{
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("Diabetes").setValue(falses.toString());
                }


                if (hipertensao.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("Hipertensao").setValue(trues.toString());
                }
                else{
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("Hipertensao").setValue(falses.toString());

                }


                if (aterosclerose.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("Aterosclerose").setValue(trues.toString());
                }
                else{
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("Aterosclerose").setValue(falses.toString());
                }


                if (teve_avc.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("AVC").setValue(trues.toString());
                }
                else {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("AVC").setValue(falses.toString());
                }


                if (iam.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("IAM").setValue(trues.toString());
                }
                else{
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("IAM").setValue(falses.toString());
                }


                if (doenca_renal.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("Doenca_Renal").setValue(trues.toString());
                }
                else {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("Doenca_Renal").setValue(falses.toString());
                }


                if (doenca_de_figado.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("Doenca_de_Figado").setValue(trues.toString());
                }
                else
                {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("Doenca_de_Figado").setValue(falses.toString());
                }


                if (doenca_de_tireoide.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("Doenca_de_Tireoide").setValue(trues.toString());
                }
                else{
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("Doenca_de_Tireoide").setValue(falses.toString());
                }


                if (outra.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("Outra").setValue(qual.getText().toString());
                }else {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").child("Outra").setValue(null);
                }

                Click2(proximo);
            }
        });



        mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_Saude").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final com.google.firebase.database.DataSnapshot dataSnapshot){

                //dataSnapshot.child("Outra").getValue()!=null

                if(dataSnapshot.child("Diabetes").getValue().toString().equals("true")){
                    diabetes.setChecked(true);
                }else{
                    diabetes.setChecked(false);
                }

                if(dataSnapshot.child("Hipertensao").getValue().toString().equals("true")){
                  hipertensao.setChecked(true);
                }else{
                    hipertensao.setChecked(false);
                }


                if(dataSnapshot.child("Aterosclerose").getValue().toString().equals("true")){
                    aterosclerose.setChecked(true);
                }else{
                    aterosclerose.setChecked(false);
                }


                if(dataSnapshot.child("AVC").getValue().toString().equals("true")){
                    teve_avc.setChecked(true);
                }else{
                    teve_avc.setChecked(false);
                }


                if(dataSnapshot.child("IAM").getValue().toString().equals("true")){
                    iam.setChecked(true);
                }else{
                    iam.setChecked(false);
                }


                if(dataSnapshot.child("Doenca_Renal").getValue().toString().equals("true")){
                    doenca_renal.setChecked(true);
                }else{
                    doenca_renal.setChecked(false);
                }


                if(dataSnapshot.child("Doenca_de_Figado").getValue().toString().equals("true")){
                    doenca_de_figado.setChecked(true);
                }else{
                    doenca_de_figado.setChecked(false);
                }


                if(dataSnapshot.child("Doenca_de_Tireoide").getValue().toString().equals("true")){
                    doenca_de_tireoide.setChecked(true);
                }else{
                    doenca_de_tireoide.setChecked(false);
                }


                if(dataSnapshot.child("Outra").getValue()!=null){
                    outra.setChecked(true);
                    quals = dataSnapshot.child("Outra").getValue().toString();
                    qual.setText(quals);
                }else{
                    outra.setChecked(false);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }



    //-----------------------------------------------------------------------
    //CLICK2
    protected void Click2(View v) {
        setContentView(R.layout.p2_taxa_alterada);
        tipo = 2;


        final CheckBox glicose = (CheckBox) findViewById(R.id.glicose);
        final CheckBox colesterol = (CheckBox) findViewById(R.id.colesterol);
        final CheckBox triglicerideo = (CheckBox) findViewById(R.id.triglicerideo);
        final CheckBox createnina = (CheckBox) findViewById(R.id.creatinina);
        final CheckBox acido_urico = (CheckBox) findViewById(R.id.acido_urico);
        final CheckBox outra2 = (CheckBox) findViewById(R.id.outra2);

        final EditText qual = (EditText) findViewById(R.id.qual);


        final Button salvar = (Button) findViewById(R.id.salvar);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (glicose.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Taxas").child("Glicose").setValue(trues.toString());
                } else {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Taxas").child("Glicose").setValue(falses.toString());
                }


                if (colesterol.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Taxas").child("Colesterol").setValue(trues.toString());
                } else {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Taxas").child("Colesterol").setValue(falses.toString());
                }


                if (triglicerideo.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Taxas").child("Triglicerideo").setValue(trues.toString());
                } else {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Taxas").child("Triglicerideo").setValue(falses.toString());
                }


                if (createnina.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Taxas").child("Createnina").setValue(trues.toString());
                } else {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Taxas").child("Createnina").setValue(falses.toString());
                }


                if (acido_urico.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Taxas").child("Acido_Urico").setValue(trues.toString());
                } else {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Taxas").child("Acido_Urico").setValue(falses.toString());
                }

                if (outra2.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Taxas").child("Outra").setValue(qual.getText().toString());
                }
                else{
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Taxas").child("Outra").setValue(null);
                }

                VoltarMenu(salvar);
            }

        });



        final Button proximo = (Button) findViewById(R.id.proximo);
        proximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (glicose.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Taxas").child("Glicose").setValue(trues.toString());
                } else {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Taxas").child("Glicose").setValue(falses.toString());
                }


                if (colesterol.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Taxas").child("Colesterol").setValue(trues.toString());
                } else {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Taxas").child("Colesterol").setValue(falses.toString());
                }


                if (triglicerideo.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Taxas").child("Triglicerideo").setValue(trues.toString());
                } else {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Taxas").child("Triglicerideo").setValue(falses.toString());
                }


                if (createnina.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Taxas").child("Createnina").setValue(trues.toString());
                } else {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Taxas").child("Createnina").setValue(falses.toString());
                }


                if (acido_urico.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Taxas").child("Acido_Urico").setValue(trues.toString());
                } else {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Taxas").child("Acido_Urico").setValue(falses.toString());
                }

                if (outra2.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Taxas").child("Outra").setValue(qual.getText().toString());
                }
                else{
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Taxas").child("Outra").setValue(null);
                }

                Click3(proximo);
            }

        });



        mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Taxas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final com.google.firebase.database.DataSnapshot dataSnapshot){

                //dataSnapshot.child("Outra").getValue()!=null

                if(dataSnapshot.child("Glicose").getValue().toString().equals("true")){
                    glicose.setChecked(true);
                }else{
                    glicose.setChecked(false);
                }

                if(dataSnapshot.child("Colesterol").getValue().toString().equals("true")){
                    colesterol.setChecked(true);
                }else{
                    colesterol.setChecked(false);
                }


                if(dataSnapshot.child("Triglicerideo").getValue().toString().equals("true")){
                    triglicerideo.setChecked(true);
                }else{
                    triglicerideo.setChecked(false);
                }


                if(dataSnapshot.child("Createnina").getValue().toString().equals("true")){
                    createnina.setChecked(true);
                }else{
                    createnina.setChecked(false);
                }


                if(dataSnapshot.child("Acido_Urico").getValue().toString().equals("true")){
                    acido_urico.setChecked(true);
                }else{
                    acido_urico.setChecked(false);
                }


                if(dataSnapshot.child("Outra").getValue()!=null){
                    outra2.setChecked(true);
                    quals = dataSnapshot.child("Outra").getValue().toString();
                    qual.setText(quals);
                }else{
                    outra2.setChecked(false);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }



    //-----------------------------------------------
    //CLICK3

    protected void Click3(View v) {
        setContentView(R.layout.p3_pai_ou_mae);
        tipo = 3;

        final CheckBox hipertensao3 = (CheckBox) findViewById(R.id.hipertensao3);
        final CheckBox iam3 = (CheckBox) findViewById(R.id.iam3);
        final CheckBox avc3 = (CheckBox) findViewById(R.id.avc3);
        final CheckBox cirurgia = (CheckBox) findViewById(R.id.cirurgia);
        final CheckBox morte_subita = (CheckBox) findViewById(R.id.morte_subita);


        final Button salvar = (Button) findViewById(R.id.salvar);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (hipertensao3.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_familiares").child("Hipertensao").setValue(trues.toString());
                }else{
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_familiares").child("Hipertensao").setValue(falses.toString());
                }


                if (iam3.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_familiares").child("IAM").setValue(trues.toString());
                }else{
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_familiares").child("IAM").setValue(falses.toString());
                }


                if (avc3.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_familiares").child("AVC").setValue(trues.toString());
                }else{
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_familiares").child("AVC").setValue(falses.toString());
                }


                if (cirurgia.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_familiares").child("Cirurgia").setValue(trues.toString());
                }else{
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_familiares").child("Cirurgia").setValue(falses.toString());
                }


                if (morte_subita.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_familiares").child("Morte_Subita").setValue(trues.toString());
                }else{
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_familiares").child("Morte_Subita").setValue(falses.toString());
                }


                VoltarMenu(salvar);
            }

        });



        final Button proximo = (Button) findViewById(R.id.proximo);
        proximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (hipertensao3.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_familiares").child("Hipertensao").setValue(trues.toString());
                }else{
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_familiares").child("Hipertensao").setValue(falses.toString());
                }


                if (iam3.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_familiares").child("IAM").setValue(trues.toString());
                }else{
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_familiares").child("IAM").setValue(falses.toString());
                }


                if (avc3.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_familiares").child("AVC").setValue(trues.toString());
                }else{
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_familiares").child("AVC").setValue(falses.toString());
                }


                if (cirurgia.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_familiares").child("Cirurgia").setValue(trues.toString());
                }else{
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_familiares").child("Cirurgia").setValue(falses.toString());
                }


                if (morte_subita.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_familiares").child("Morte_Subita").setValue(trues.toString());
                }else{
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_familiares").child("Morte_Subita").setValue(falses.toString());
                }


                Click4(proximo);
            }

        });



        mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Problemas_familiares").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final com.google.firebase.database.DataSnapshot dataSnapshot){

                //dataSnapshot.child("Outra").getValue()!=null

                if(dataSnapshot.child("Hipertensao").getValue().toString().equals("true")){
                    hipertensao3.setChecked(true);
                }else{
                    hipertensao3.setChecked(false);
                }

                if(dataSnapshot.child("IAM").getValue().toString().equals("true")){
                    iam3.setChecked(true);
                }else{
                    iam3.setChecked(false);
                }


                if(dataSnapshot.child("AVC").getValue().toString().equals("true")){
                    avc3.setChecked(true);
                }else{
                    avc3.setChecked(false);
                }


                if(dataSnapshot.child("Cirurgia").getValue().toString().equals("true")){
                    cirurgia.setChecked(true);
                }else{
                    cirurgia.setChecked(false);
                }


                if(dataSnapshot.child("Morte_Subita").getValue().toString().equals("true")){
                    morte_subita.setChecked(true);
                }else{
                    morte_subita.setChecked(false);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }





    //----------------------------------------------
    //CLICK4
    protected void Click4(View v) {
        setContentView(R.layout.p4_remedios);
        tipo = 4;

        final EditText qual = (EditText) findViewById(R.id.qual);
        final EditText quanto_tempo = (EditText) findViewById(R.id.quanto_tempo);

        final RadioGroup p4 = (RadioGroup)
                findViewById(R.id.radio_remedios);



        final Button salvar = (Button) findViewById(R.id.salvar);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                switch (p4.getCheckedRadioButtonId()) {
                    case R.id.sim:
                        caso_sim = 1;
                        caso_nao = 0;
                        mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Remedios").child("Sim").setValue(trues.toString());
                        mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Remedios").child("Qual").setValue(falses.toString());
                        break;
                    case R.id.nao:
                        caso_nao = 1;
                        caso_sim = 0;
                        mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Remedios").child("Sim").setValue(falses.toString());
                        mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Remedios").child("Qual").setValue(trues.toString());
                        break;
                }


                if (caso_sim == 1) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Remedios").child("Qual").setValue(qual.getText().toString());
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Remedios").child("Quanto_tempo").setValue(quanto_tempo.getText().toString());
                }


                VoltarMenu(salvar);
            }

        });




        final Button proximo = (Button) findViewById(R.id.proximo);
        proximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                switch (p4.getCheckedRadioButtonId()) {
                    case R.id.sim:
                        caso_sim = 1;
                        caso_nao = 0;
                        mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Remedios").child("Sim").setValue(trues.toString());
                        mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Remedios").child("Qual").setValue(falses.toString());
                        break;
                    case R.id.nao:
                        caso_nao = 1;
                        caso_sim = 0;
                        mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Remedios").child("Sim").setValue(falses.toString());
                        mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Remedios").child("Qual").setValue(trues.toString());
                        break;
                }


                if (caso_sim == 1) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Remedios").child("Qual").setValue(qual.getText().toString());
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Remedios").child("Quanto_tempo").setValue(quanto_tempo.getText().toString());
                }


                Click5(proximo);
            }

        });




        mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Remedios").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final com.google.firebase.database.DataSnapshot dataSnapshot){

                //dataSnapshot.child("Outra").getValue()!=null

                if(dataSnapshot.child("Sim").getValue().toString().equals("true")){
                    p4.check(R.id.sim);

                    if(dataSnapshot.child("Qual").getValue()!=null){
                        quals = dataSnapshot.child("Qual").getValue().toString();
                        qual.setText(quals);
                    }

                    if(dataSnapshot.child("Quanto_tempo").getValue()!=null){
                        quals = dataSnapshot.child("Quanto_tempo").getValue().toString();
                        quanto_tempo.setText(quals);
                    }

                }else{
                    p4.check(R.id.nao);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }




    //----------------------------------------------
    //CLICK5
    protected void Click5(View v) {
        setContentView(R.layout.p5_repouso);
        tipo = 5;


        final CheckBox tontura = (CheckBox) findViewById(R.id.tontura);
        final CheckBox dor = (CheckBox) findViewById(R.id.dor);
        final CheckBox inchaco = (CheckBox) findViewById(R.id.inchaco);
        final CheckBox falta_ar = (CheckBox) findViewById(R.id.falta_ar);
        final CheckBox taquicardia = (CheckBox) findViewById(R.id.taquicardia);
        final CheckBox falta_ar_deita = (CheckBox) findViewById(R.id.falta_ar_deita);
        final CheckBox apneia = (CheckBox) findViewById(R.id.apneia);


        final Button salvar = (Button) findViewById(R.id.salvar);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (tontura.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Sintomas_recentes").child("Tontura").setValue(trues.toString());
                }else{
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Sintomas_recentes").child("Tontura").setValue(falses.toString());
                }


                if (dor.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Sintomas_recentes").child("Dor").setValue(trues.toString());
                }else{
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Sintomas_recentes").child("Dor").setValue(falses.toString());
                }


                if (inchaco.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Sintomas_recentes").child("Inchaco").setValue(trues.toString());
                }else{
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Sintomas_recentes").child("Inchaco").setValue(falses.toString());
                }


                if (falta_ar.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Sintomas_recentes").child("Falta_ar").setValue(trues.toString());
                }else{
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Sintomas_recentes").child("Falta_ar").setValue(falses.toString());
                }


                if (taquicardia.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Sintomas_recentes").child("Taquicardia").setValue(trues.toString());
                }else{
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Sintomas_recentes").child("Taquicardia").setValue(falses.toString());
                }


                if (falta_ar_deita.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Sintomas_recentes").child("Falta_ar_deita").setValue(trues.toString());
                }else{
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Sintomas_recentes").child("Falta_ar_deita").setValue(falses.toString());
                }


                if (apneia.isChecked()) {
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Sintomas_recentes").child("Apneia").setValue(trues.toString());
                }else{
                    mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Sintomas_recentes").child("Apneia").setValue(falses.toString());
                }

                VoltarMenu(salvar);
            }

        });



        mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico_Saude").child("Sintomas_recentes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final com.google.firebase.database.DataSnapshot dataSnapshot){

                //dataSnapshot.child("Outra").getValue()!=null

                if(dataSnapshot.child("Tontura").getValue().toString().equals("true")){
                    tontura.setChecked(true);
                }else{
                    tontura.setChecked(false);
                }

                if(dataSnapshot.child("Dor").getValue().toString().equals("true")){
                    dor.setChecked(true);
                }else{
                    dor.setChecked(false);
                }


                if(dataSnapshot.child("Inchaco").getValue().toString().equals("true")){
                    inchaco.setChecked(true);
                }else{
                    inchaco.setChecked(false);
                }


                if(dataSnapshot.child("Falta_ar").getValue().toString().equals("true")){
                    falta_ar.setChecked(true);
                }else{
                    falta_ar.setChecked(false);
                }


                if(dataSnapshot.child("Taquicardia").getValue().toString().equals("true")){
                    taquicardia.setChecked(true);
                }else{
                    taquicardia.setChecked(false);
                }

                if(dataSnapshot.child("Apneia").getValue().toString().equals("true")){
                    apneia.setChecked(true);
                }else{
                    apneia.setChecked(false);
                }

                if(dataSnapshot.child("Falta_ar_deita").getValue().toString().equals("true")){
                    falta_ar_deita.setChecked(true);
                }else{
                    falta_ar_deita.setChecked(false);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



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