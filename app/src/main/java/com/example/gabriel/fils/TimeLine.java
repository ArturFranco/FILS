package com.example.gabriel.fils;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
/**
 * Created by Vinicius Sanguinete on 20/11/2016.
 */

public class TimeLine extends AppCompatActivity {

    protected int day = 0;
    protected int month = 0;
    protected int year = 0;
    protected int clickDay = 1;

    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    String[] days31 = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
    String[] days30 = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};
    String[] days29 = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29"};
    String[] days28 = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeline_main);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        day = bundle.getInt("days");
        month = bundle.getInt("month");
        year = bundle.getInt("year");
        clickDay = bundle.getInt("day");

        Toast toast = Toast.makeText(TimeLine.this, day+"/"+month+"/"+year,Toast.LENGTH_SHORT);
        toast.show();

        ListView list = (ListView) findViewById(R.id.listDays);

        if(day == 31)
        {
            list.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, days31));
        }
        else if(day == 30)
        {
            list.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, days30));
        }
        else if(day == 29)
        {
            list.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, days29));
        }
        else if(day == 28)
        {
            list.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, days28));
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                //Toast.makeText(getBaseContext(), "Clicou no item na posição " + arg2, Toast.LENGTH_SHORT).show();
                clickDay = arg2 + 1;
                getHistorical();
            }
        });
    }

    public void getHistorical(){

        String[] days30 = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};

        ListView list1 = (ListView) findViewById(R.id.listDays);
        list1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, days31));

/*        Toast toast = Toast.makeText(TimeLine.this, clickDay+"",Toast.LENGTH_LONG);
        toast.show();*/

        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();

        //Pega a referencia da lista de treinos
        mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).child("Historico").child((year+"")).child((month+"")).child((clickDay+"")).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(final com.google.firebase.database.DataSnapshot dataSnapshot) {
                //Referencia a listview do xml
                ListView listaDeAtividades = (ListView) findViewById(R.id.listHours);

                //Povoa uma lista com os nomes dos treinos
                Iterator<DataSnapshot> it = dataSnapshot.getChildren().iterator();
                List<String> list = new ArrayList<String>();
                DataSnapshot aux;
                String desc, t;
                while (it.hasNext()) {
                    aux = it.next();
                    list.add(aux.getKey() + "-" + aux.child("Id").getValue() + aux.child("Tipo").getValue());
                }

                //Atribui a lista de nomes a listview
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(TimeLine.this,android.R.layout.simple_list_item_1, list);
                listaDeAtividades.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}


