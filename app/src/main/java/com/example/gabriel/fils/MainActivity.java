package com.example.gabriel.fils;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    private static final String PREFS_NAME = "MyPrefs";

    //Inicializando variaveis do firebase
    private FirebaseAuth mAuth;
    private DatabaseReference mFirebaseDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Se o usuario está logando como profissional de saude, vai para a tela correta
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        int perfil = settings.getInt("perfil", 0);
        if(perfil == 2 || perfil == 3){
            Intent intent = new Intent(MainActivity.this, ProfissionalMainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }


        //Setando o arquivo xml que vai ser usado
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Autenticacao firebase
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();

        //Metodo que troca de tela quando botao de nova atividade é clicado
        Button novaAtividadeButton = (Button) findViewById(R.id.novaAtividadeButton);
        novaAtividadeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NovaAtividade.class);
                startActivity(intent);
            }
        });


        // para criar uma tela linkando com Historico, por exemplo
        Button historicoButton = (Button) findViewById(R.id.historicoButton);
        historicoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Historico.class);
                startActivity(intent);
            }
        });


        Button historico_saude = (Button) findViewById(R.id.saudeButton);
        historico_saude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Historico_Saude.class);
                startActivity(intent);
            }
        });

        //para criar uma tela linkando com Agendamento
        Button agendamentoButton = (Button) findViewById(R.id.agendamentoButton);
        agendamentoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Agendamento.class);
                startActivity(intent);
            }
        });


        // para criar uma tela linkando com Metas
        Button metasButton = (Button) findViewById(R.id.metasButton);
        metasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Metas.class);
                startActivity(intent);
            }
        });

        //Provavelmente esse é o código para a slide menu
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //Se algum usuario estiver logado, coloca nome, email e foto no slide menu
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null && !user.isAnonymous()) {
            // Header do slide menu
            View hView =  navigationView.getHeaderView(0);

            //Nome
            TextView nameTextView = (TextView) hView.findViewById(R.id.nameTextView);
            nameTextView.setText(user.getDisplayName());

            //Email
            TextView emailTextView = (TextView) hView.findViewById(R.id.emailTextView);
            emailTextView.setText(user.getEmail());

            //Imagem
            int SDK_INT = android.os.Build.VERSION.SDK_INT;
            if (SDK_INT > 8) {
                //Codigo complexo que permite que a main thread abra conexoes Http
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();
                StrictMode.setThreadPolicy(policy);

                //Imagem, aumentando a escala
                ImageView profileImage = (ImageView) hView.findViewById(R.id.profileImageView);
                profileImage.setScaleX((float) 1.9);
                profileImage.setScaleY((float) 1.9);
                Bitmap bitmap = getBitmapFromURL(user.getPhotoUrl().toString());

                profileImage.setImageBitmap(bitmap);
            }

            //Adicionando uma entrada no Banco de Dados para esse usuario, se nao houver
            DatabaseReference referenciaDatabaseUsuario = mFirebaseDatabaseReference.child("Atletas").child(user.getUid());
            referenciaDatabaseUsuario.child("Nome").setValue(user.getDisplayName());
            referenciaDatabaseUsuario.child("PhotoURL").setValue(user.getPhotoUrl().toString());
        }

        ImageView imgView = (ImageView) findViewById(R.id.home1Background);
        Drawable imgDrawable = getResources().getDrawable(R.drawable.home1);
        imgView.setImageDrawable(imgDrawable);

        imgView = (ImageView) findViewById(R.id.home2Background);
        imgDrawable = getResources().getDrawable(R.drawable.home2);
        imgView.setImageDrawable(imgDrawable);

        imgView = (ImageView) findViewById(R.id.home3Background);
        imgDrawable = getResources().getDrawable(R.drawable.home3);
        imgView.setImageDrawable(imgDrawable);

        imgView = (ImageView) findViewById(R.id.home4Background);
        imgDrawable = getResources().getDrawable(R.drawable.home4);
        imgView.setImageDrawable(imgDrawable);

        imgView = (ImageView) findViewById(R.id.home5Background);
        imgDrawable = getResources().getDrawable(R.drawable.home5);
        imgView.setImageDrawable(imgDrawable);
    }

    public void onStart() {
        super.onStart();
        // Se o usuario nao esta logado, vai para a tela de login
        if (mAuth.getCurrentUser() == null) {
            Intent intent = new Intent(MainActivity.this, PerfilActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //TODO
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.signout) {
            mAuth.signOut();
            Intent intent = new Intent(MainActivity.this, PerfilActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Pega a imagem como um Bitmap a partir de uma URL
    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }

}
