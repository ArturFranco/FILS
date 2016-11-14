package com.example.gabriel.fils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * Created by Gabriel on 10/11/2016.
 *
 * Tela que lista os treinos cadastrados e permite casdastrar novos
 */

public class NovoTreino extends AppCompatActivity {

    private DatabaseReference mFirebaseDatabaseReference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novo_treino_main);

        //Pega referencia do banco de dados
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();

        //Pega a referencia da lista de treinos
        mFirebaseDatabaseReference.child("Treinos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                //Referencia a listview do xml
                ListView listaDeAtividades = (ListView) findViewById(R.id.listaAtividades);

                //Povoa uma lista com os nomes dos treinos
                Iterator<DataSnapshot> it = dataSnapshot.getChildren().iterator();
                List<String> list = new ArrayList<String>();
                while (it.hasNext())
                    list.add(it.next().getValue().toString());

                //Atribui a lista de nomes a listview
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(NovoTreino.this,android.R.layout.simple_list_item_1, list);
                listaDeAtividades.setAdapter(adapter);

                listaDeAtividades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        /*String entry= (String) parent.getAdapter().getItem(position);
                        Toast toast = Toast.makeText(NovoTreino.this, entry.toString(),Toast.LENGTH_SHORT);
                        //toast.setGravity();

                        LayoutInflater inflater = getLayoutInflater();
                        View layout = inflater.inflate(R.layout.popup_treino,
                                (ViewGroup) findViewById(R.id.custom_popup_treino));
                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                        toast.setView(layout);
                        toast.show();*/
                        final Dialog dialog = new Dialog(NovoTreino.this);
                        dialog.setTitle("Relatar Treino");
                        dialog.setContentView(R.layout.dialog_novotreino);

                        String entry = (String) parent.getAdapter().getItem(position);

                        TextView nome = (TextView) dialog.findViewById(R.id.treinoNome);


                        Button botaoConfirma = (Button) dialog.findViewById(R.id.botaoConfirma);
                        botaoConfirma.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                               dialog.dismiss();
                            }
                        });

                        dialog.show();

                        nome.setText(entry.toString());
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        //TODO adicionar a funcao de adicionar novos treinos
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addTreinoButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NovoTreino.this, AddTreino.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }


}
