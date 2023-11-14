package com.example.pokemonapi_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.pokemonapi_test.Interfaces.FragmentHandler;

public class APIMenu extends Fragment {
    private Button pokeDexBtn, berriesBtn, itemsBtn, openButton;
    public APIMenu() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_a_p_i_menu, container, false);



        pokeDexBtn = (Button) v.findViewById(R.id.PokeDex_btn);
        berriesBtn = (Button) v.findViewById(R.id.Berries_btn);
        itemsBtn = (Button) v.findViewById(R.id.Items_btn);

        openButton = (Button) v.findViewById(R.id.APITest_Btn);

        pokeDexBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity mainact = getActivity();
                ((FragmentHandler)mainact).changeFragment(1);
            }
        });

        berriesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity mainact = getActivity();
                ((FragmentHandler)mainact).changeFragment(2);
            }
        });

        itemsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity mainact = getActivity();
                ((FragmentHandler)mainact).changeFragment(3);
            }
        });

        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), APIActivity.class);
                startActivity(i);
            }
        });

        return v;
    }
}