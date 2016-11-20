package com.example.gabriel.fils;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Felipe on 11/19/2016.
 */

public class PerfilActivity extends AppCompatActivity {
    private static final String TAG = "PerfilActivity";

    private static final String PREFS_NAME = "MyPrefs";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Setando o arquivo xml que vai ser usado
        setContentView(R.layout.perfil_main);

        //Metodo para quando o botao Atleta é clicado
        Button atletaButton = (Button) findViewById(R.id.atletaButton);
        atletaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Salvado a escolha do usiario nas SharedPreferences
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                settings.edit().putInt("perfil", 1).commit();
                //Muda para a tela de login
                Intent intent = new Intent(PerfilActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        //Metodo para quando o botao Personal é clicado
        Button personalButton = (Button) findViewById(R.id.personalButton);
        personalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Salvado a escolha do usiario nas SharedPreferences
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                settings.edit().putInt("perfil", 2).commit();
                //Muda para a tela de login
                Intent intent = new Intent(PerfilActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        //Metodo para quando o botao Nutricionista é clicado
        Button nutricionistaButton = (Button) findViewById(R.id.nutricionistaButton);
        nutricionistaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Salvado a escolha do usiario nas SharedPreferences
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                settings.edit().putInt("perfil", 3).commit();
                //Muda para a tela de login
                Intent intent = new Intent(PerfilActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        //Carregando a imagem de background
        ImageView imgView = (ImageView) findViewById(R.id.perfilBackground);
        Drawable imgDrawable=getResources().getDrawable(R.drawable.login_background);
        imgView.setImageDrawable(imgDrawable);
    }

}
