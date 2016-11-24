package com.example.gabriel.fils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        //Acredito que aqui eu posso pegar uma view diferente para o valor de Integer.toString(args.getInt(ARG_OBJECT))
        View rootView = inflater.inflate(R.layout.fragment_pager_list, container, false);
        Bundle args = getArguments();

        ((TextView) rootView.findViewById(R.id.text)).setText(
                Integer.toString(args.getInt(ARG_OBJECT)));
        return rootView;
    }
}
