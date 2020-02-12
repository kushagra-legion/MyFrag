package com.example.myfrag;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class BlankFragmentOne extends Fragment {
    Button button, button1;
    View root;
    Fragment frag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_blank_fragment_one, container, false);
        button = root.findViewById(R.id.button3);
        button1 = root.findViewById(R.id.button4);
        button.setVisibility(View.INVISIBLE);
        button1.setVisibility(View.INVISIBLE);
        int c1 = Example.c;
        if (c1 != 0) {
            button.setVisibility(View.VISIBLE);
            button1.setVisibility(View.VISIBLE);
        }
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frag = new BlankFragmentTwo();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_place, frag);
                ft.commit();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frag = new BlankFragmentThree();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_place, frag);
                ft.commit();
            }
        });

        return root;
    }
}

