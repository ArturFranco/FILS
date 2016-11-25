package com.example.gabriel.fils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Felipe on 11/23/2016.
 */

// Instances of this class are fragments representing a single
// object in our collection.
public class DemoObjectFragment extends Fragment {
    public static final String ARG_OBJECT = "object";
    private static final String TAG = "DemoObjectFragment";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        Bundle args = getArguments();

        View rootView = null;
        //ListView listView = (ListView) rootView.findViewById(android.R.id.list);
        if(args.getInt(ARG_OBJECT) == 1) {
            rootView = inflater.inflate(R.layout.fragment_pager_perfil, container, false);
        }else if(args.getInt(ARG_OBJECT) == 2){
            rootView = inflater.inflate(R.layout.fragment_pager_alunos, container, false);
        }else{
            rootView = inflater.inflate(R.layout.fragment_pager_agenda, container, false);
        }

        //((TextView) rootView.findViewById(R.id.text)).setText(
         //       Integer.toString(args.getInt(ARG_OBJECT)));
        return rootView;
    }
}
