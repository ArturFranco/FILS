package com.example.gabriel.fils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Lucas Felix on 14/11/2016.
 */

public class Metas extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefs";
    private String IdAluno;
    SeekBar seek_barPeso;
    TextView text_viewPeso;
    SeekBar seek_barGordura;
    TextView text_viewGordura;
    SeekBar seek_barCorrida;
    TextView text_viewCorrida;
    SeekBar seek_barTreino;
    TextView text_viewTreino;

    private FirebaseAuth mAuth;
    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseUser user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.metas_main);

        //Autenticacao firebase
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        user = mAuth.getCurrentUser();

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        int perfil = settings.getInt("perfil", 0);
        if(perfil == 1){
            IdAluno = user.getUid();

        }
        else{
            IdAluno = ProfissionalMainActivity.alunoAtual;
        }

        seekbarrPeso();
        seekbarrGordura();
        seekbarrCorrida();
        seekbarrTreino();

        mFirebaseDatabaseReference.child("Atletas").child(IdAluno).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if( dataSnapshot.child("Metas").child("Peso").getValue() != null )
                    seek_barPeso.setProgress( dataSnapshot.child("Metas").child("Peso").getValue(Integer.class)-20);

                if( dataSnapshot.child("Metas").child("Gordura").getValue() != null )
                    seek_barGordura.setProgress( dataSnapshot.child("Metas").child("Gordura").getValue(Integer.class));

                if( dataSnapshot.child("Metas").child("Corrida").getValue() != null )
                    seek_barCorrida.setProgress( dataSnapshot.child("Metas").child("Corrida").getValue(Integer.class)-1);

                if( dataSnapshot.child("Metas").child("Treino").getValue() != null )
                    seek_barTreino.setProgress( dataSnapshot.child("Metas").child("Treino").getValue(Integer.class)-2);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void seekbarrPeso(){
        seek_barPeso = (SeekBar) findViewById(R.id.seekBarPeso);
        text_viewPeso = (TextView) findViewById(R.id.textseekbarPeso);
        text_viewPeso.setText(""+(seek_barPeso.getProgress()+20)+" Kg");

        seek_barPeso.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int progress_value;
                    @Override
                    public void onProgressChanged( SeekBar seekBar , int progress , boolean fromUser ) {
                        progress_value = progress;
                        text_viewPeso.setText(""+(progress+20)+" Kg");
                        //Toast.makeText(Metas.this, "SeekBar in progress", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onStartTrackingTouch( SeekBar seekBar ) {
                        //Toast.makeText(Metas.this, "SeekBar in StartTracking", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        text_viewPeso.setText(""+(progress_value+20)+" Kg");
                        mFirebaseDatabaseReference.child("Atletas").child(IdAluno).child("Metas").child("Peso").setValue(progress_value+20);
                        //Toast.makeText(Metas.this, "SeekBar in StopTracking", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void seekbarrGordura(){
        seek_barGordura = (SeekBar) findViewById(R.id.seekBarGordura);
        text_viewGordura = (TextView) findViewById(R.id.textseekbarGordura);
        text_viewGordura.setText(""+seek_barGordura.getProgress()+"% de Gordura");

        seek_barGordura.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int progress_value;
                    @Override
                    public void onProgressChanged( SeekBar seekBar , int progress , boolean fromUser ) {
                        progress_value = progress;
                        text_viewGordura.setText(""+progress+"% de Gordura");
                        //Toast.makeText(Metas.this, "SeekBar in progress", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onStartTrackingTouch( SeekBar seekBar ) {
                        //Toast.makeText(Metas.this, "SeekBar in StartTracking", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        text_viewGordura.setText(""+progress_value+"% de Gordura");
                        mFirebaseDatabaseReference.child("Atletas").child(IdAluno).child("Metas").child("Gordura").setValue(progress_value);
                        //Toast.makeText(Metas.this, "SeekBar in StopTracking", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void seekbarrCorrida(){
        seek_barCorrida = (SeekBar) findViewById(R.id.seekBarCorrida);
        text_viewCorrida = (TextView) findViewById(R.id.textseekbarCorrida);
        text_viewCorrida.setText(""+(seek_barCorrida.getProgress()+1)+" Km");

        seek_barCorrida.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int progress_value;
                    @Override
                    public void onProgressChanged( SeekBar seekBar , int progress , boolean fromUser ) {
                        progress_value = progress;
                        text_viewCorrida.setText(""+(progress+1)+" Km");
                        //Toast.makeText(Metas.this, "SeekBar in progress", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onStartTrackingTouch( SeekBar seekBar ) {
                        //Toast.makeText(Metas.this, "SeekBar in StartTracking", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        text_viewCorrida.setText(""+(progress_value+1)+" Km");
                        mFirebaseDatabaseReference.child("Atletas").child(IdAluno).child("Metas").child("Corrida").setValue(progress_value+1);
                        //Toast.makeText(Metas.this, "SeekBar in StopTracking", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void seekbarrTreino(){
        seek_barTreino = (SeekBar) findViewById(R.id.seekBarTreino);
        text_viewTreino = (TextView) findViewById(R.id.textseekbarTreino);
        text_viewTreino.setText(""+(seek_barTreino.getProgress()+2)+" dias sem faltar!");

        seek_barTreino.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int progress_value;
                    @Override
                    public void onProgressChanged( SeekBar seekBar , int progress , boolean fromUser ) {
                        progress_value = progress;
                        text_viewTreino.setText(""+(progress+2)+" dias sem faltar!");
                        //Toast.makeText(Metas.this, "SeekBar in progress", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onStartTrackingTouch( SeekBar seekBar ) {
                        //Toast.makeText(Metas.this, "SeekBar in StartTracking", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        text_viewTreino.setText(""+(progress_value+2)+" dias sem faltar!");
                        mFirebaseDatabaseReference.child("Atletas").child(IdAluno).child("Metas").child("Treino").setValue(progress_value+2);
                        //Toast.makeText(Metas.this, "SeekBar in StopTracking", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

}
