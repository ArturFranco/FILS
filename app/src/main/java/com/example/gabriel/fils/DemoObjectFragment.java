package com.example.gabriel.fils;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.example.gabriel.fils.MainActivity.getBitmapFromURL;

/**
 * Created by Felipe on 11/23/2016.
 */

// Instances of this class are fragments representing a single
// object in our collection.
public class DemoObjectFragment extends Fragment
        implements NavigationView.OnNavigationItemSelectedListener{

    public static final String ARG_OBJECT = "object";
    private static final String TAG = "DemoObjectFragment";

    //Declarando variaveis do firebase
    private FirebaseAuth mAuth;
    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseUser user;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //Pega dados de autenticação
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        //Pega referencia do banco de dados
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();

        // The last two arguments ensure LayoutParams are inflated
        // properly.
        Bundle args = getArguments();

        //Tela "Perfil"
        if(args.getInt(ARG_OBJECT) == 1) {
            rootView = inflater.inflate(R.layout.fragment_pager_perfil, container, false);

            NavigationView navigationView = (NavigationView) rootView.findViewById(R.id.profissional_nav_view);
            navigationView.setNavigationItemSelectedListener(this);

            //Se algum usuario estiver logado, coloca nome, email e foto no slide menu
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

        }else if(args.getInt(ARG_OBJECT) == 2){ //Tela "Alunos"
            rootView = inflater.inflate(R.layout.fragment_pager_alunos, container, false);

            //Pega a referencia da lista de treinos
            mFirebaseDatabaseReference.child(ProfissionalMainActivity.perfilString).child(user.getUid()).child("Alunos").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(final com.google.firebase.database.DataSnapshot dataSnapshot) {
                    //Referencia a listview do xml
                    ListView listaDeAlunos = (ListView) rootView.findViewById(R.id.listaAlunos);

                    // Construct the data source
                    ArrayList<Aluno> arrayOfAlunos = new ArrayList<Aluno>();
                    // Create the adapter to convert the array to views
                    final AlunosAdapter adapter = new AlunosAdapter(getActivity(), arrayOfAlunos);
                    // Attach the adapter to a ListView
                    listaDeAlunos.setAdapter(adapter);

                    DatabaseReference atletasReference = mFirebaseDatabaseReference.child("Atletas");

                    //Povoa uma lista com os nomes dos treinos
                    Iterator<DataSnapshot> it = dataSnapshot.getChildren().iterator();
                    //List<String> list = new ArrayList<String>();
                    DataSnapshot aux;
                    while (it.hasNext()) {
                        //Aqui eu tenho o userID do aluno, com o qual posso achar seu nome e foto
                        aux = it.next();
                        atletasReference.child(aux.getValue().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                                adapter.add(new Aluno(dataSnapshot.child("Nome").getValue().toString(), dataSnapshot.child("PhotoURL").getValue().toString(), dataSnapshot.getKey()));
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }

                    listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            Aluno entry =  (Aluno) parent.getAdapter().getItem(position);
                            ProfissionalMainActivity.alunoAtual = entry.userID;

                            Intent intent = new Intent(getActivity(), AlunoActivity.class);
                            startActivity(intent);
                        }
                    });

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.addAlunoButton);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), AddAluno.class);
                    startActivity(intent);
                }
            });

        }else{ //Tela "Agenda"
            rootView = inflater.inflate(R.layout.fragment_pager_agenda, container, false);
        }

        return rootView;
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
            Intent intent = new Intent(getActivity(), PerfilActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

        return true;
    }



}
