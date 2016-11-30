package com.example.gabriel.fils;

import android.content.Intent;
import android.graphics.Bitmap;
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

import static com.example.gabriel.fils.MainActivity.getBitmapFromURL;

/**
 * Created by Felipe on 11/25/2016.
 */

public class AlunoActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = "AlunoActivity";

    //Inicializando variaveis do firebase
    private FirebaseAuth mAuth;
    private DatabaseReference mFirebaseDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Setando o arquivo xml que vai ser usado
        setContentView(R.layout.profissional_activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.profissional_toolbar);
        setSupportActionBar(toolbar);

        //Autenticacao firebase
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();


        //Provavelmente esse é o código para a slide menu
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.profissional_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.profissional_nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //Se algum usuario estiver logado, coloca nome, email e foto no slide menu
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null && !user.isAnonymous()) {
            // Header do slide menu
            View hView =  navigationView.getHeaderView(0);

            //Nome
            TextView nameTextView = (TextView) hView.findViewById(R.id.profissional_nameTextView);
            nameTextView.setText(user.getDisplayName());

            //Email
            TextView emailTextView = (TextView) hView.findViewById(R.id.profissional_emailTextView);
            emailTextView.setText(user.getEmail());

            int SDK_INT = android.os.Build.VERSION.SDK_INT;
            if (SDK_INT > 8) {
                //Codigo complexo que permite que a main thread abra conexoes Http
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();
                StrictMode.setThreadPolicy(policy);

                //Imagem, aumentando a escala
                ImageView profileImage = (ImageView) hView.findViewById(R.id.profissional_profileImageView);
                profileImage.setScaleX((float) 1.9);
                profileImage.setScaleY((float) 1.9);
                Bitmap bitmap = getBitmapFromURL(user.getPhotoUrl().toString());

                profileImage.setImageBitmap(bitmap);
            }

        }

        //Background images
        ImageView imgView = (ImageView) findViewById(R.id.profissional_home1Background);
        Drawable imgDrawable = getResources().getDrawable(R.drawable.home1);
        imgView.setImageDrawable(imgDrawable);

        imgView = (ImageView) findViewById(R.id.profissional_home2Background);
        imgDrawable = getResources().getDrawable(R.drawable.home2);
        imgView.setImageDrawable(imgDrawable);

        imgView = (ImageView) findViewById(R.id.profissional_home3Background);
        imgDrawable = getResources().getDrawable(R.drawable.home3);
        imgView.setImageDrawable(imgDrawable);

        imgView = (ImageView) findViewById(R.id.profissional_home4Background);
        imgDrawable = getResources().getDrawable(R.drawable.home4);
        imgView.setImageDrawable(imgDrawable);

        imgView = (ImageView) findViewById(R.id.profissional_home5Background);
        imgDrawable = getResources().getDrawable(R.drawable.home5);
        imgView.setImageDrawable(imgDrawable);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.profissional_drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profissional_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.profissional_action_settings) {
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

        if (id == R.id.profissional_nav_camera) {
            // Handle the camera action
        } else if (id == R.id.profissional_nav_gallery) {

        } else if (id == R.id.profissional_nav_slideshow) {

        } else if (id == R.id.profissional_nav_manage) {

        } else if (id == R.id.dados_gerais) {

        } else if (id == R.id.profissional_nav_send) {

        } else if (id == R.id.profissional_signout) {
            mAuth.signOut();
            Intent intent = new Intent(AlunoActivity.this, PerfilActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.profissional_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
