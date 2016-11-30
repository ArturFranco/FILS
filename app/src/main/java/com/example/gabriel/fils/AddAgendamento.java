package com.example.gabriel.fils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by amandatrigueiro on 28/11/16.
 */

public class AddAgendamento extends AppCompatActivity {

    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private int tipo;

    private LinearLayout check;

    private CheckBox personal;
    private CheckBox nutricionista;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novo_agendamento);

        personal = (CheckBox) findViewById(R.id.personal);
        nutricionista = (CheckBox) findViewById(R.id.nutricionista);



    }
}
