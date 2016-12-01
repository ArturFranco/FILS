package com.example.gabriel.fils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * Created by Artur on 10/11/2016.
 *
 * Tela que lista as refeições cadastradas e permite cadastrar novas
 */

public class NovaRefeicao extends AppCompatActivity {

    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private static final String PREFS_NAME = "MyPrefs";
    private String IdAluno;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nova_refeicao_main);

        //Pega dados de autenticação
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        //Se o usuario está logando como profissional de saude, vai para a tela correta
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        int perfil = settings.getInt("perfil", 0);
        if(perfil == 1){
            IdAluno = user.getUid();
        }
        else{
            IdAluno = ProfissionalMainActivity.alunoAtual;
        }

        //Pega referencia do banco de dados
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();

        //Pega a referencia da lista de refeições
        mFirebaseDatabaseReference.child("Atletas").child(IdAluno).child("Refeições").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                //Referencia a listview do xml
                ListView listaDeRefeicoes = (ListView) findViewById(R.id.listaAtividadesRef);

                //Povoa uma lista com os nomes das refeições
                Iterator<DataSnapshot> it = dataSnapshot.getChildren().iterator();
                List<String> list = new ArrayList<String>();
                while (it.hasNext())
                    list.add(it.next().getKey());

                //Atribui a lista de nomes a listview
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(NovaRefeicao.this, android.R.layout.simple_list_item_1, list);
                listaDeRefeicoes.setAdapter(adapter);

                listaDeRefeicoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

                        final Dialog dialog = new Dialog(NovaRefeicao.this);
                        //dialog.setTitle("Relatar Refeição");
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.dialog_novarefeicao);

                        final String entry = (String) parent.getAdapter().getItem(position);

                        //TextView nome = (TextView) dialog.findViewById(R.id.refeicaoNome);

                        //TODO relatar treino
                        Button botaoConfirma = (Button) dialog.findViewById(R.id.botaoConfirmaRef);
                        botaoConfirma.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Calendar c = Calendar.getInstance();
                                Integer ano = c.get(Calendar.YEAR);
                                Integer dia = c.get(Calendar.DAY_OF_MONTH);
                                Integer mes = c.get(Calendar.MONTH);
                                mes++;
                                Integer hora = c.get(Calendar.HOUR_OF_DAY);
                                Integer minutos = c.get(Calendar.MINUTE);
                                Integer segundos = c.get(Calendar.SECOND);
                                String s = entry;
                                String idHistorico = hora.toString()+":"+minutos.toString()+":"+segundos.toString();
                                mFirebaseDatabaseReference.child("Atletas").child(IdAluno).child("Historico").child(ano.toString()).child(mes.toString()).child(dia.toString()).child(idHistorico).child("Tipo").setValue("Refeição");
                                mFirebaseDatabaseReference.child("Atletas").child(IdAluno).child("Historico").child(ano.toString()).child(mes.toString()).child(dia.toString()).child(idHistorico).child("Id").setValue(s.substring(s.lastIndexOf(":") + 1));
                                //Log.d("NovoTreino", day.toString());
                                finish();
                                //dialog.dismiss();
                            }
                        });


                        //Cancela o relato de treino
                        Button botaoCancela = (Button) dialog.findViewById(R.id.botaoCancelaRef);
                        botaoCancela.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });

                        dialog.show();
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        //TODO adicionar a função de adicionar novas refeições
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addRefeicaoButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NovaRefeicao.this, AddRefeicao.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

}


