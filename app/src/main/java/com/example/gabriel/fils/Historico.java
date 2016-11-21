package com.example.gabriel.fils;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Vinicius Sanguinete on 19/11/16.
 */

public class Historico extends AppCompatActivity {
    private DatabaseReference mFirebaseDatabaseReference;
    CalendarView calendar;
    private int year = 0;
    private int currentYear = 0;
    private int month= 0;
    private int currentMonth= 0;
    private int daysMonth= 0;
    private int currentdayofMonth= 0;
    TextView currentData;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historico_main);

        currentData = (TextView) findViewById(R.id.year);
        final Calendar c = Calendar.getInstance();
        currentYear = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        daysMonth = c.get(Calendar.DAY_OF_MONTH);

       /* long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
        String dateString = sdf.format(date);*/
        currentData.setText(new StringBuilder().append(currentYear));

    }

    protected void previousYear(View view) {
        currentYear = currentYear - 1;
        setContentView(R.layout.historico_main);
        currentData = (TextView) findViewById(R.id.year);
        currentData.setText(new StringBuilder().append(currentYear));
    }
    protected void nextYear(View view) {
        currentYear = currentYear + 1;
        setContentView(R.layout.historico_main);
        currentData = (TextView) findViewById(R.id.year);
        currentData.setText(new StringBuilder().append(currentYear));
    }

    protected void janeiro(View view) {
        //currentMonth = 1;
        setContentView(R.layout.timeline_main);



        /*ListView daysOfWeek = (ListView) findViewById(R.id.listDays);

        daysMonth = 31;

        List<String> listDays = new ArrayList<String>();
        int i = 0;
        while(i < daysMonth){
            listDays.add(String.valueOf(i+1));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listDays);

        daysOfWeek.setAdapter(adapter);*/
    }
    protected void fevereiro(View view) {
        //currentMonth = 2;
        setContentView(R.layout.timeline_main);
    }
    protected void marco(View view) {
        //currentMonth = 3;
        setContentView(R.layout.timeline_main);
    }
    protected void abril(View view) {
        //currentMonth = 4;
        setContentView(R.layout.timeline_main);
    }
    protected void maio(View view) {
        //currentMonth = 5;
        setContentView(R.layout.timeline_main);
    }
    protected void junho(View view) {
        //currentMonth = 6;
        setContentView(R.layout.timeline_main);
    }
    protected void julho(View view) {
        //currentMonth = 7;
        setContentView(R.layout.timeline_main);
    }
    protected void agosto(View view) {
        //currentMonth = 8;
        setContentView(R.layout.timeline_main);
    }
    protected void setembro(View view) {
        //currentMonth = 9;
        setContentView(R.layout.timeline_main);
    }
    protected void outubro(View view) {
        //currentMonth = 10;
        setContentView(R.layout.timeline_main);
    }
    protected void novembro(View view) {
        //currentMonth = 11;
        setContentView(R.layout.timeline_main);
    }
    protected void dezembro(View view) {
        //currentMonth = 12;
        setContentView(R.layout.timeline_main);
    }

    public int getDaysMonth(){
        return this.daysMonth;
    }
    public int getCurrentYear(){
        return this.currentYear;
    }
    public int getCurrentMonth(){
        return this.currentMonth;
    }
    @Override
    protected void onStart() {
        super.onStart();
//
    }
}

