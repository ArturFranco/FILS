package com.example.gabriel.fils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Artur on 24/11/2016.
 */

public class AddAgendamento extends AppCompatActivity {

    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private Spinner spinnerProfissionais;
    public String tipo;
    public String profi;
    public int currentYear = 0;
    public int currentMonth= 0;
    public int daysMonth= 0;
    public int currentDayofMonth= 0;
    public int hour = 0;
    public int minute = 0;
    TextView currentData;
    public int perfil = 1;
    public int sec =0;
    public int flag =0;
    public String key;
    View v;

    private static final String PREFS_NAME = "MyPrefs";
    private String IdAluno;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novo_agendamento);
        //Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
        flag = 0;

        spinnerProfissionais = (Spinner) findViewById(R.id.spinnerProfissionais);
        ArrayAdapter adapterGrupo = ArrayAdapter.createFromResource(this, R.array.profissionais , android.R.layout.simple_spinner_dropdown_item);
        spinnerProfissionais.setAdapter(adapterGrupo);

        currentData = (TextView) findViewById(R.id.year);
        final Calendar c = Calendar.getInstance();
        currentYear = c.get(Calendar.YEAR);
        currentMonth = c.get(Calendar.MONTH);
        currentMonth++;
        currentDayofMonth = c.get(Calendar.DAY_OF_MONTH);
        hour = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);
        sec = c.get(Calendar.SECOND);
        setDaysMonth();

        //Pega dados de autenticação
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        perfil = settings.getInt("perfil", 0);
//        Toast.makeText(getApplicationContext(), perfil+"", Toast.LENGTH_SHORT).show();

        if(perfil == 1){
            IdAluno = user.getUid();
        }
        else{
            IdAluno = ProfissionalMainActivity.alunoAtual;
        }
//        System.out.println("hora: "+hour+" minuto: "+"Dias no mês:"+daysMonth);
        /*Toast toast = Toast.makeText(AddAgendamento.this, "hora: "+hour+" minuto: "+ minute +"Dias no mês:"+daysMonth,Toast.LENGTH_SHORT);
        toast.show();*/

        setText();


        AdapterView.OnItemSelectedListener escolhaGrupo = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view , int position, long id) {

                final String item = spinnerProfissionais.getSelectedItem().toString();
                tipo = item;
                if (tipo == "Personal") {
                    profi = "Personais";
                    key = "Atividade com Personal";

                }else if(tipo == "Nutricionista"){
                    profi = "Nutricionistas";
                    key = "Atividade com Nutricionista";

                }else{
                    if(perfil == 1){
                        key = "Atividade com Profissional";
                    }else if(perfil == 2){
                        profi = "Personais";
                        key = "Atividade com Atleta";
                    }else if (perfil == 3){
                        profi = "Nutricionistas";
                        key = "Atividade com Atleta";
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}

        };
        spinnerProfissionais.setOnItemSelectedListener(escolhaGrupo);
        //Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();

        Button botaoSalvar = (Button) findViewById(R.id.buttonsalvarAgendamento);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_SHORT).show();
                /*final Dialog dialog = new Dialog(AddAgendamento.this);
                //dialog.setTitle("Descrição");
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_salvar_refeicao);*/
                //setContentView(R.layout.dialog_salvar_agendamento);

                //Toast.makeText(getApplicationContext(), "3", Toast.LENGTH_SHORT).show();

               /* Button botaoConfirmarDialog = (Button) dialog.findViewById(R.id.botaoConfirma);
                botaoConfirmarDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {*/
                        /*
                        EditText descTextRef = (EditText) dialog.findViewById(R.id.descricaoText);
                        key = descTextRef.getText().toString();
                        Toast.makeText(getApplicationContext(), "5", Toast.LENGTH_SHORT).show();*/

                        /*setResult(RESULT_OK, returnIntent);
                        finish();*/

                        /*if (descricao.getText().toString().contentEquals("")) {
                            Toast.makeText(getApplicationContext(), "Dados Incompletos", Toast.LENGTH_SHORT).show();
                        }*/

                        if (perfil == 1) {//ATLETAS

                            //Toast.makeText(getApplicationContext(), ":" + key, Toast.LENGTH_SHORT).show();
                            mFirebaseDatabaseReference.child("Atletas").child(IdAluno).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.hasChild(tipo)) {

                                        mFirebaseDatabaseReference.child("Atletas").child(IdAluno).child("Eventos").child(currentYear + "").child(currentMonth + "").child(currentDayofMonth + "").child(hour + ":" + minute + ":" + sec).child("Descricao").setValue(key);
                                        mFirebaseDatabaseReference.child("Atletas").child(IdAluno).child("Eventos").child(currentYear + "").child(currentMonth + "").child(currentDayofMonth + "").child(hour + ":" + minute + ":" + sec).child("Flag").setValue(true);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Erro. Você não possui um " + tipo + " associado.", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                            /*setResult(RESULT_OK, returnIntent);
                            finish();*/

                            /*Intent intent = new Intent(AddAgendamento.this, Agendamento.class);
                            startActivity(intent);*/

                        } else { //PROFISIONAIS

                            mFirebaseDatabaseReference.child(profi).child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    //pegar o aluno com IdAluno --------------------------
                                    //key = mFirebaseDatabaseReference.child(tipo).child(user.getUid()).child("Alunos").getKey();
                                    //Toast.makeText(getApplicationContext(), ":"+key, Toast.LENGTH_SHORT).show();
                                    //mFirebaseDatabaseReference.child(profi).child(user.getUid()).child("Alunos").child(IdAluno).child("Eventos").child(currentYear + "").child(currentMonth + "").child(currentDayofMonth + "").child(hour + ":" + minute + ":" + sec).child("Flag").setValue(true);

                                    mFirebaseDatabaseReference.child(profi).child(user.getUid()).child("Eventos").child(currentYear + "").child(currentMonth + "").child(currentDayofMonth + "").child(hour + ":" + minute + ":" + sec).child("Descricao").setValue(key);
                                    mFirebaseDatabaseReference.child(profi).child(user.getUid()).child("Eventos").child(currentYear + "").child(currentMonth + "").child(currentDayofMonth + "").child(hour + ":" + minute + ":" + sec).child("Flag").setValue(true);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                           /* setResult(RESULT_OK, returnIntent);
                            finish();*/
                        }
                        Intent intent = new Intent(AddAgendamento.this, Agendamento.class);
                        startActivity(intent);
                        /*setResult(RESULT_OK, returnIntent);
                        finish();*/
                /*    }
                });

                Button botaoCancelarDialog = (Button) dialog.findViewById(R.id.botaoCancela);
                botaoCancelarDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });*/
            }
        });
    }

    public void timeBackMin(View view){

        if(minute == 0){
            minute = 60;
        }
        else{
            minute--;
        }
        setDaysMonth();
        setText();
    }
    public void timeNextMin(View view){

        if(minute == 60){
            minute = 0;
        }
        else{
            minute++;
        }
        setDaysMonth();
        setText();

    }
    public void timeBackHour(View view){

        if(hour == 0){
            hour = 23;
        }
        else{
            hour--;
        }
        setDaysMonth();
        setText();


    }
    public void timeNextHour(View view){

        if(hour == 23){
            hour = 0;
        }
        else{
            hour++;
        }
        setDaysMonth();
        setText();

    }
    public void dateNextYear(View view){

        currentYear++;
        setDaysMonth();
        setText();


    }
    public void dateBackYear(View view){

        currentYear--;
        setDaysMonth();
        setText();
    }
    public void dateNextMonth(View view){

        if(currentMonth == 12){
            currentMonth = 1;
        }
        else{
            currentMonth++;
        }
        setDaysMonth();
        setText();

    }
    public void dateBackMonth(View view){

        if(currentMonth == 1){
            currentMonth = 12;
        }
        else{
            currentMonth--;
        }
        setDaysMonth();
        setText();
    }
    public void dateNextDay(View view){

        if(currentDayofMonth == daysMonth){
            currentDayofMonth = 1;
        }
        else{
            currentDayofMonth++;
        }
        setDaysMonth();
        setText();
    }
    public void dateBackDay(View view){

        if(currentDayofMonth == 1){
            currentDayofMonth = daysMonth;
        }
        else{
            currentDayofMonth--;
        }
        setDaysMonth();
        setText();
    }

    public void setText(){
        if(perfil == 1){
            currentData = (TextView) findViewById(R.id.txt_tipo_agendamento);
            currentData.setText("Com qual profissional você deseja agendar?");

            spinnerProfissionais = (Spinner) findViewById(R.id.spinnerProfissionais);
            ArrayAdapter adapterGrupo = ArrayAdapter.createFromResource(this, R.array.profissionais , android.R.layout.simple_spinner_dropdown_item);
            spinnerProfissionais.setAdapter(adapterGrupo);
        }else{
            spinnerProfissionais = (Spinner) findViewById(R.id.spinnerProfissionais);
            ArrayAdapter adapterGrupo = ArrayAdapter.createFromResource(this, R.array.nada , android.R.layout.simple_spinner_dropdown_item);
            spinnerProfissionais.setAdapter(adapterGrupo);
            currentData = (TextView) findViewById(R.id.txt_tipo_agendamento);
            currentData.setText("Agende com seu Atleta:");
        }
        //DAY
        currentData = (TextView) findViewById(R.id.dateDay);
        currentData.setText(new StringBuilder().append(currentDayofMonth));
        //MONTH
        currentData = (TextView) findViewById(R.id.dateMonth);
        currentData.setText(new StringBuilder().append(currentMonth));
        //YEAR
        currentData = (TextView) findViewById(R.id.dateYear);
        currentData.setText(new StringBuilder().append(currentYear));
        //Hour
        currentData = (TextView) findViewById(R.id.timeHour);
        currentData.setText(new StringBuilder().append(hour));
        //Minute
        currentData = (TextView) findViewById(R.id.timeMin);
        currentData.setText(new StringBuilder().append(minute));
    }
    public void setDaysMonth(){
        Calendar mycal = new GregorianCalendar(currentYear, currentMonth-1, 1);
        daysMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
        if(currentDayofMonth > daysMonth){
            currentDayofMonth = daysMonth;
        }
    }
}



