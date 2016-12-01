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
        currentMonth = c.get(Calendar.MONTH);
        currentMonth++;
        currentdayofMonth = c.get(Calendar.DAY_OF_MONTH);

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

        currentMonth = 1;
        daysMonth = 31;
        toTimeLine();
    }
    protected void fevereiro(View view) {

        currentMonth = 2;
        if(isPealYear(currentYear)){
            daysMonth = 29;
        }
        else {
            daysMonth = 28;
        }

        toTimeLine();
    }
    protected void marco(View view) {
        currentMonth = 3;
        daysMonth = 31;
        toTimeLine();
    }
    protected void abril(View view) {
        currentMonth = 4;
        daysMonth = 30;
        toTimeLine();
    }
    protected void maio(View view) {
        currentMonth = 5;
        daysMonth = 31;
        toTimeLine();
    }
    protected void junho(View view) {
        currentMonth = 6;
        daysMonth = 30;
        toTimeLine();
    }
    protected void julho(View view) {
        currentMonth = 7;
        daysMonth = 31;
        toTimeLine();
    }
    protected void agosto(View view) {
        currentMonth = 8;
        daysMonth = 31;
        toTimeLine();
    }
    protected void setembro(View view) {
        currentMonth = 9;
        daysMonth = 30;
        toTimeLine();
    }
    protected void outubro(View view) {
        currentMonth = 10;
        daysMonth = 31;
        toTimeLine();
    }
    protected void novembro(View view) {
        currentMonth = 11;
        daysMonth = 30;
        toTimeLine();
    }
    protected void dezembro(View view) {
        currentMonth = 12;
        daysMonth = 31;
        toTimeLine();
    }

    public void toTimeLine(){

        Intent intent = new Intent(Historico.this, TimeLine.class);

        Bundle bundle = new Bundle();
        bundle.putInt("month",getCurrentMonth());
        bundle.putInt("year",getCurrentYear());
        bundle.putInt("days",getDaysMonth());
        bundle.putInt("day",getCurrentDay());

        intent.putExtras(bundle);

        startActivity(intent);
    }

    public int getDaysMonth(){
        return this.daysMonth;
    }
    public int getCurrentYear(){
        return this.currentYear;
    }
    public int getCurrentDay(){
        return this.currentdayofMonth;
    }
    public int getCurrentMonth(){
        return this.currentMonth;
    }
    public boolean isPealYear(int year){
        if((year % 4 == 0) && ( (year % 100 != 0) || (year % 400 == 0))){
            return true;
        }
        return false;
    }
    @Override
    protected void onStart() {
        super.onStart();

    }
}

