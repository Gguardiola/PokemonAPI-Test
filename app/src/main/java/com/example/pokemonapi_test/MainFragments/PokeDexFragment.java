package com.example.pokemonapi_test.MainFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pokemonapi_test.R;
import com.example.pokemonapi_test.ViewModelHandler;

public class PokeDexFragment extends Fragment {
    ViewModelHandler model;
    public PokeDexFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        ViewModelHandler model = new ViewModelProvider(this).get(ViewModelHandler.class);
        TextView t = (TextView)getView().findViewById(R.id.pokedex_txt);
        t.setText(model.getName());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_poke_dex_fragment, container, false);

        model = new ViewModelProvider(getActivity()).get(ViewModelHandler.class);

        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                TextView t = (TextView)v.findViewById(R.id.pokedex_txt);
                t.setText(s);
            }
        };

        model.getModel().observe(getActivity(), nameObserver);

        return v;
    }
}