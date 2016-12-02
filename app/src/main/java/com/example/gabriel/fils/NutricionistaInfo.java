package com.example.gabriel.fils;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.gabriel.fils.MainActivity.getBitmapFromURL;

/**
 * Created by Gabriel on 01/12/2016.
 */

public class NutricionistaInfo extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        mFirebaseDatabaseReference.child("Atletas").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Nutricionista")){
                    mFirebaseDatabaseReference.child("Nutricionistas").child(dataSnapshot.child("Nutricionista").getValue().toString()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot2) {

                            setContentView(R.layout.nutricionista_info);
                            ImageView foto = (ImageView) findViewById(R.id.photoNutriInfo);
                            Bitmap bitmap = getBitmapFromURL(dataSnapshot2.child("PhotoURL").getValue().toString());
                            foto.setImageBitmap(bitmap);

                            TextView nutriNome = (TextView) findViewById(R.id.NutriInfoNome);
                            nutriNome.setText(dataSnapshot2.child("Nome").getValue().toString());

                            TextView nutriIdade = (TextView) findViewById(R.id.idadeNutriText);
                            TextView nutriExp = (TextView) findViewById(R.id.experienciaNutri);
                            TextView nutriLocal = (TextView) findViewById(R.id.localNutri);
                            TextView nutriAdicional = (TextView) findViewById(R.id.adInfoNutri);

                            if(dataSnapshot2.hasChild("Dados_Gerais")){

                                nutriIdade.setText(dataSnapshot2.child("Dados_Gerais").child("Idade").getValue().toString());
                                nutriExp.setText(dataSnapshot2.child("Dados_Gerais").child("TempoExperiencia").getValue().toString());
                                nutriLocal.setText(dataSnapshot2.child("Dados_Gerais").child("LocalTrabalho").getValue().toString());
                                nutriAdicional.setText(dataSnapshot2.child("Dados_Gerais").child("InformacaoAdicional").getValue().toString());
                            }else{
                                nutriIdade.setText("Não Informado");
                                nutriExp.setText("Não Informado");
                                nutriLocal.setText("Não Informado");
                                nutriAdicional.setText("Não Informado");
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }else{
                    setContentView(R.layout.nutricionista_info_empty);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
