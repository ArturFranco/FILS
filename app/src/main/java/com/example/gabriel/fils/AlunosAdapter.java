package com.example.gabriel.fils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.gabriel.fils.MainActivity.getBitmapFromURL;

/**
 * Created by Felipe on 11/26/2016.
 */

public class AlunosAdapter extends ArrayAdapter<Aluno> {
    public AlunosAdapter(Context context, ArrayList<Aluno> alunos) {
        super(context, 0, alunos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Aluno aluno = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_aluno, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvNome);
        // Populate the data into the template view using the data object
        tvName.setText(aluno.nome);

        //Imagem
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            //Codigo complexo que permite que a main thread abra conexoes Http
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            //Imagem, aumentando a escala
            ImageView profileImage = (ImageView) convertView.findViewById(R.id.tvPhoto);

            Bitmap bitmap = getBitmapFromURL(aluno.photoURL);
            profileImage.setImageBitmap(bitmap);

            //convertView.setMinimumHeight(2*(profileImage.getHeight()));
        }

        // Return the completed view to render on screen
        return convertView;
    }
}
