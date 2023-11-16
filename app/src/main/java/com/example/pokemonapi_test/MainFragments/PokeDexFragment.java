package com.example.pokemonapi_test.MainFragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pokemonapi_test.APIPokemonRecyclerViewAdapter;
import com.example.pokemonapi_test.Interfaces.PokemonAPIService;
import com.example.pokemonapi_test.Pokemon;
import com.example.pokemonapi_test.R;
import com.example.pokemonapi_test.ViewModelHandler;

import java.sql.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokeDexFragment extends Fragment {
    ViewModelHandler model;

    Button testRecall;

    RecyclerView pokemonList;

    ArrayList<Pokemon> lPokemons;

    private ArrayList<Pokemon> getPokemons(int limit){
        ArrayList <Pokemon> aux_lPokemons = new ArrayList();
        Log.d("GET POKEMONS API TEST","0");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PokemonAPIService pokeapis = retrofit.create(PokemonAPIService.class);
        int id = limit;
        for (int item = 0; item <= limit; item++){
            int finalItem = item;
            pokeapis.getPokemonById(item).enqueue(new Callback<Pokemon>() {
                @Override
                public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                    Log.d("APICALL POKE",String.valueOf(finalItem));
                    Pokemon p = response.body();
                    aux_lPokemons.add(p);
                }

                @Override
                public void onFailure(Call<Pokemon> call, Throwable t) {
                    Toast.makeText(getContext(), "FAILED", Toast.LENGTH_SHORT).show();
                }
            });
        }
        Log.d("FINISH?","NO");
        return aux_lPokemons;
    }
    public PokeDexFragment() {

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

        pokemonList = (RecyclerView)v.findViewById(R.id.pokedex_recycler);
        lPokemons = getPokemons(20);
        //TODO: crear el characterAdapter, ver line 167 de recyclerTest
        APIPokemonRecyclerViewAdapter adapter;
        adapter = new APIPokemonRecyclerViewAdapter(lPokemons, this.getContext());
        RecyclerView.LayoutManager l = new LinearLayoutManager(this.getContext());
        pokemonList.setLayoutManager(l);
        pokemonList.setItemAnimator(new DefaultItemAnimator());
        pokemonList.setAdapter(adapter);

        testRecall = (Button) v.findViewById(R.id.recall_btn);
        testRecall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lPokemons.get(1).setName("pep");
                adapter.setItems(lPokemons);
                Log.d("TEST RECALL",String.valueOf(lPokemons.get(1).getName()));
            }
        });

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