package com.example.gabriel.fils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Lucas Felix on 14/11/2016.
 */

public class Metas extends AppCompatActivity {

    SeekBar seek_barPeso;
    TextView text_viewPeso;
    SeekBar seek_barGordura;
    TextView text_viewGordura;
    SeekBar seek_barCorrida;
    TextView text_viewCorrida;
    SeekBar seek_barTreino;
    TextView text_viewTreino;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.metas_main);
        seekbarrPeso();
        seekbarrGordura();
        seekbarrCorrida();
        seekbarrTreino();
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
                        //Toast.makeText(Metas.this, "SeekBar in StopTracking", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

}
