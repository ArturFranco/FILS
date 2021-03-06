package com.example.gabriel.fils;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.ListView;
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
import java.util.Iterator;
import java.util.List;

/**
 * Created by amandatrigueiro on 27/11/16.
 *
 * Tela para ver consultas agendadas ou marcar uma nova
 *
 */

public class Agendamento extends AppCompatActivity {

    public CalendarView calendar;

    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    protected int day = 0;
    protected int month = 0;
    protected int year = 0;
    public String hour;
    public String minute;

    private static final String PREFS_NAME = "MyPrefs";
    private String IdAluno;

    public String profi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agendamento_main);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        //Se o usuario está logando como profissional de saude, vai para a tela correta
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        int perfil = settings.getInt("perfil", 0);
        if(perfil == 1){
            IdAluno = user.getUid();
            profi = "Atletas";
        }
        else{
            IdAluno = ProfissionalMainActivity.alunoAtual;
            if(perfil == 2){
                profi = "Personais";
            }else{
                profi = "Nutricionistas";
            }
        }

        final Calendar c1 = Calendar.getInstance();
        year = c1.get(Calendar.YEAR);
        month = c1.get(Calendar.MONTH);
        day = c1.get(Calendar.DAY_OF_MONTH);
        setMonth(month+1);
        getDiary();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addAgendamentoButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Agendamento.this, AddAgendamento.class);
                startActivity(intent);
            }
        });

        calendar = (CalendarView) findViewById(R.id.calendar);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int y, int m, int d) {
                //month = month +1;
                /*Toast toast = Toast.makeText(Agendamento.this, m+"/",Toast.LENGTH_SHORT);
                toast.show();*/
                setDay(d);
                setMonth(m+1);
                setYear(y);
                /*Toast toast = Toast.makeText(Agendamento.this, getDay()+"/"+getMonth()+"/"+getYear(),Toast.LENGTH_SHORT);
                toast.show();*/
                getDiary();
            }

        });

        ImageView imgView = (ImageView) findViewById(R.id.calendarBackground);
        Drawable imgDrawable = getResources().getDrawable(R.drawable.calendarbackground);
        imgView.setImageDrawable(imgDrawable);
    }

    public void setDay(int i){
        this.day = i;
    }
    public void setMonth(int i){
        this.month = i;
    }
    public void setYear(int i){
        this.year = i;
    }
    public int getDay(){
        return this.day;
    }
    public int getMonth(){
        return this.month;
    }
    public int getYear(){
        return this.year;
    }

    public void getDiary(){

        /*Toast toast = Toast.makeText(Agendamento.this, getMonth()+"",Toast.LENGTH_SHORT);
        toast.show();*/

        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();

        //Pega a referencia da lista de treinos
        mFirebaseDatabaseReference.child(profi).child(user.getUid()).child("Eventos").child((year+"")).child((month+"")).child((day+"")).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(final com.google.firebase.database.DataSnapshot dataSnapshot) {
                //Referencia a listview do xml
                ListView listaDeAtividades = (ListView) findViewById(R.id.agendamento);

                //Povoa uma lista com os nomes dos treinos
                Iterator<DataSnapshot> it = dataSnapshot.getChildren().iterator();
                List<String> list = new ArrayList<String>();
                DataSnapshot aux;
                while (it.hasNext()) {
                    aux = it.next();
                    setTime(aux.getKey());

                    list.add(getTime() + " - " + aux.child("Descricao").getValue());
                }

                //Atribui a lista de nomes a listview
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Agendamento.this,android.R.layout.simple_list_item_1, list);
                listaDeAtividades.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
    public void setTime(String aux) {
        String[] aux1 = aux.split(":");
        this.hour = aux1[0];
        this.minute = aux1[1];
        if(this.hour.length() < 2 ){
            this.hour = 0+this.hour;
        }
        if(this.minute.length() < 2 ){
            this.minute = 0+this.minute;
        }
    }
    public String getTime(){
        return this.hour+":"+this.minute;
    }

}
