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

import org.w3c.dom.Text;

import static com.example.gabriel.fils.MainActivity.getBitmapFromURL;

/**
 * Created by Gabriel on 01/12/2016.
 */

public class PersonalInfo extends AppCompatActivity {
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
                if(dataSnapshot.hasChild("Personal")){
                    mFirebaseDatabaseReference.child("Personais").child(dataSnapshot.child("Personal").getValue().toString()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot2) {

                            setContentView(R.layout.personal_info);
                            ImageView foto = (ImageView) findViewById(R.id.photoPersonalInfo);
                            Bitmap bitmap = getBitmapFromURL(dataSnapshot2.child("PhotoURL").getValue().toString());
                            foto.setImageBitmap(bitmap);

                            TextView personalNome = (TextView) findViewById(R.id.personalInfoNome);
                            personalNome.setText(dataSnapshot2.child("Nome").getValue().toString());

                            TextView personalIdade = (TextView) findViewById(R.id.idadePersonalText);
                            TextView personalExp = (TextView) findViewById(R.id.experienciaPersonal);
                            TextView personalLocal = (TextView) findViewById(R.id.localPersonal);
                            TextView personalAdicional = (TextView) findViewById(R.id.adInfoPersonal);

                            if(dataSnapshot2.hasChild("Dados_Gerais")){

                                personalIdade.setText(dataSnapshot2.child("Dados_Gerais").child("Idade").getValue().toString());
                                personalExp.setText(dataSnapshot2.child("Dados_Gerais").child("TempoExperiencia").getValue().toString());
                                personalLocal.setText(dataSnapshot2.child("Dados_Gerais").child("LocalTrabalho").getValue().toString());
                                personalAdicional.setText(dataSnapshot2.child("Dados_Gerais").child("InformacaoAdicional").getValue().toString());
                            }else{
                                personalIdade.setText("Não Informado");
                                personalExp.setText("Não Informado");
                                personalLocal.setText("Não Informado");
                                personalAdicional.setText("Não Informado");
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }else{
                    setContentView(R.layout.personal_info_empty);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
