package com.example.gabriel.fils;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
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

/**
 * Created by Felipe on 11/19/2016.
 */

public class ProfissionalMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "ProfissionalMainActivity";
    private static final String PREFS_NAME = "MyPrefs";

    public static String perfilString;

    //Inicializando variaveis do firebase
    private FirebaseAuth mAuth;
    private DatabaseReference mFirebaseDatabaseReference;
    DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
    ViewPager mViewPager;

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

        //Salvando o perfil do usuario
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        int perfil = settings.getInt("perfil", 0);
        if(perfil == 2){
            perfilString = "Personais";
        }else{
            perfilString = "Nutricionistas";
        }


        // para criar uma tela linkando com Historico, por exemplo
        Button button1 = (Button) findViewById(R.id.profissional_botao1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfissionalMainActivity.this, CollectionDemoActivity .class);
                startActivity(intent);
            }
        });

        //------------------------------------------------------------------------------------------------
        // Parte do Swipe Views with Tabs
        // ViewPager and its adapters use support library
        // fragments, so use getSupportFragmentManager.
 /*       mDemoCollectionPagerAdapter =
                new DemoCollectionPagerAdapter(
                        getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);


        //adicionando abas
        final ActionBar actionBar = getSupportActionBar();

        // Specify that tabs should be displayed in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create a tab listener that is called when the user changes tabs.
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
                // When the tab is selected, switch to the
                // corresponding page in the ViewPager.
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

            }
        };

        // Add 3 tabs, specifying the tab's text and TabListener
        for (int i = 0; i < 2; i++) {
            actionBar.addTab(
                    actionBar.newTab()
                            .setText("Tab " + (i + 1))
                            .setTabListener(tabListener));
        }

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        // When swiping between pages, select the
                        // corresponding tab.
                        getSupportActionBar().setSelectedNavigationItem(position);
                    }
                });

*/
        //------------------------------------------------------------------------------------------------

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

            //Adicionando entrada no Banco de Dados para esse usuario, se nao houver
            DatabaseReference referenciaDatabaseUsuario = mFirebaseDatabaseReference.child(perfilString).child(user.getUid());
            referenciaDatabaseUsuario.child("Nome").setValue(user.getDisplayName());
            referenciaDatabaseUsuario.child("PhotoURL").setValue(user.getPhotoUrl().toString());
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

    public void onStart() {
        super.onStart();
        // Se o usuario nao esta logado, vai para a tela de login
        if (mAuth.getCurrentUser() == null) {
            Intent intent = new Intent(ProfissionalMainActivity.this, PerfilActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

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

        } else if (id == R.id.profissional_nav_share) {

        } else if (id == R.id.profissional_nav_send) {

        } else if (id == R.id.profissional_signout) {
            mAuth.signOut();
            Intent intent = new Intent(ProfissionalMainActivity.this, PerfilActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.profissional_drawer_layout);
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

