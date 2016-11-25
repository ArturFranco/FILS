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
import android.util.Log;
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

public class ProfissionalMainActivity extends AppCompatActivity {

    // When requested, this adapter returns a DemoObjectFragment,
    // representing an object in the collection.
    DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
    ViewPager mViewPager;

    private static final String TAG = "ProfMainActivity";
    private static final String PREFS_NAME = "MyPrefs";

    public static String perfilString;

    //Inicializando variaveis do firebase
    private FirebaseAuth mAuth;
    private DatabaseReference mFirebaseDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Codigo do Swipe Views with Tabs
        //---------------------------------------------------------------------------------------------------

        setContentView(R.layout.fragment_pager);

        // ViewPager and its adapters use support library
        // fragments, so use getSupportFragmentManager.
        mDemoCollectionPagerAdapter =
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
        actionBar.addTab(actionBar.newTab().setText("Perfil").setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText("Alunos").setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText("Agenda").setTabListener(tabListener));

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

        //--------------------------------------------------------------------------------------------------

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


        //Se algum usuario estiver logado, coloca nome, email e foto no slide menu
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null && !user.isAnonymous()) {
            //Adicionando entrada no Banco de Dados para esse usuario, se nao houver
            DatabaseReference referenciaDatabaseUsuario = mFirebaseDatabaseReference.child(perfilString).child(user.getUid());
            referenciaDatabaseUsuario.child("Nome").setValue(user.getDisplayName());
            referenciaDatabaseUsuario.child("PhotoURL").setValue(user.getPhotoUrl().toString());
        }
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

}

