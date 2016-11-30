package com.example.gabriel.fils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.gabriel.fils.MainActivity.getBitmapFromURL;

/**
 * Created by Felipe on 11/30/2016.
 */

public class NotificacaoAdapter extends ArrayAdapter<Notificacao> {
    public NotificacaoAdapter(Context context, ArrayList<Notificacao> notificacoes) {
        super(context, 0, notificacoes);
    }

    private DatabaseReference mFirebaseDatabaseReference;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Notificacao notificacao = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            if(notificacao.descricao.equals("Solicitacao Personal") || notificacao.descricao.equals("Solicitacao Nutricionista")) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_notificacao_solicitacao_prof, parent, false);
            }
        }

        if(notificacao.descricao.equals("Solicitacao Personal") || notificacao.descricao.equals("Solicitacao Nutricionista")) {
            // Lookup view for data population
            TextView tvDescricao = (TextView) convertView.findViewById(R.id.notificacao_Descricao);
            // Populate the data into the template view using the data object
            if(notificacao.descricao.equals("Solicitacao Personal")){
                tvDescricao.setText("Solicitação do Personal Trainer");
            }else{
                tvDescricao.setText("Solicitação do Nutricionista");
            }

            mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
            DatabaseReference profissionalReference;

            if(notificacao.descricao.equals("Solicitacao Personal")){
                profissionalReference = mFirebaseDatabaseReference.child("Personais");
            }else{
                profissionalReference = mFirebaseDatabaseReference.child("Nutricionistas");
            }

            final View cv = convertView;

            //Aqui eu tenho o userID do profissional, com o qual posso achar seu nome e foto
            profissionalReference.child(notificacao.id).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    TextView nome = (TextView) cv.findViewById(R.id.notificacao_Nome);
                    nome.setText(dataSnapshot.child("Nome").getValue().toString());

                    if(dataSnapshot.hasChild("Dados_Gerais")) {
                        if(dataSnapshot.child("Dados_Gerais").hasChild("Idade")) {
                            TextView idade = (TextView) cv.findViewById(R.id.notificacao_Idade);
                            idade.setText(dataSnapshot.child("Dados_Gerais").child("Idade").getValue().toString() + " anos");
                        }

                        if(dataSnapshot.child("Dados_Gerais").hasChild("TempoExperiencia")) {
                            TextView exp = (TextView) cv.findViewById(R.id.notificacao_Experiencia);
                            exp.setText("Experiência: " + dataSnapshot.child("Dados_Gerais").child("TempoExperiencia").getValue().toString() + " anos");
                        }

                        if(dataSnapshot.child("Dados_Gerais").hasChild("LocalTrabalho")) {
                            TextView localidade = (TextView) cv.findViewById(R.id.notificacao_Localidade);
                            localidade.setText("Localidade: " + dataSnapshot.child("Dados_Gerais").child("LocalTrabalho").getValue().toString());
                        }

                        if(dataSnapshot.child("Dados_Gerais").hasChild("InformacaoAdicional")) {
                            TextView extras = (TextView) cv.findViewById(R.id.notificacao_Extras);
                            extras.setText(dataSnapshot.child("Dados_Gerais").child("InformacaoAdicional").getValue().toString());
                        }
                    }

                    //Imagem
                    int SDK_INT = android.os.Build.VERSION.SDK_INT;
                    if (SDK_INT > 8) {
                        //Codigo complexo que permite que a main thread abra conexoes Http
                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                                .permitAll().build();
                        StrictMode.setThreadPolicy(policy);

                        //Imagem, aumentando a escala
                        ImageView profileImage = (ImageView) cv.findViewById(R.id.notificacao_Photo);

                        Bitmap bitmap = getBitmapFromURL(dataSnapshot.child("PhotoURL").getValue().toString());
                        profileImage.setImageBitmap(bitmap);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

        // Return the completed view to render on screen
        return convertView;
    }
}

