package com.example.gabriel.fils;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Felipe on 11/19/2016.
 */

public class PerfilActivity extends AppCompatActivity {
    private static final String TAG = "PerfilActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Setando o arquivo xml que vai ser usado
        setContentView(R.layout.perfil_main);

        //Metodo para quando o botao Atleta é clicado
        Button loginAnonimoButton = (Button) findViewById(R.id.atletaButton);
        loginAnonimoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PerfilActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        ImageView imgView = (ImageView) findViewById(R.id.perfilBackground);
        Drawable imgDrawable=getResources().getDrawable(R.drawable.login_background);
        imgView.setImageDrawable(imgDrawable);
    }

}
