package com.example.pokemonapi_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pokemonapi_test.Interfaces.FragmentHandler;
import com.example.pokemonapi_test.Interfaces.PokemonAPIService;
import com.example.pokemonapi_test.MainFragments.BerriesFragment;
import com.example.pokemonapi_test.MainFragments.ItemsFragment;
import com.example.pokemonapi_test.MainFragments.PokeDexFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements FragmentHandler {
    private Fragment[] fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragments = new Fragment[3];
        fragments[0] = new PokeDexFragment();
        fragments[1] = new BerriesFragment();
        fragments[2] = new ItemsFragment();

       // Button b= (Button) findViewById(R.id.callAPI_BTN);
       // TextView t = (TextView) findViewById(R.id.poketxt_TXT);
       // ImageView imgV = (ImageView) findViewById(R.id.pokeimg_IMG);

        ViewModelHandler model = new ViewModelProvider(this).get(ViewModelHandler.class);

        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                ((TextView)findViewById(R.id.pokedex_txt)).setText(model.getName());
            }
        };

        model.getModel().observe(this, nameObserver);

     //   b.setOnClickListener(new View.OnClickListener() {
     //       @Override
     //       public void onClick(View view) {
     //           Retrofit retrofit = new Retrofit.Builder()
     //                   .baseUrl("https://pokeapi.co/api/v2/")
     //                   .addConverterFactory(GsonConverterFactory.create())
     //                   .build();
     //           PokemonAPIService pokeapis = retrofit.create(PokemonAPIService.class);
     //           pokeapis.getPokemon("ditto").enqueue(new Callback<Pokemon>() {
     //               @Override
     //               public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
     //                   Pokemon p = response.body();
     //                   Toast.makeText(getApplicationContext(), p.getName(), Toast.LENGTH_SHORT).show();
     //                   t.setText(p.getName() + p.getHeight()+p.getWeight());
     //                   Glide.with(getApplicationContext()).load(p.sprites.front_default).into(imgV);
     //                   model.setName(t.getText().toString());
     //               }
//
     //               @Override
     //               public void onFailure(Call<Pokemon> call, Throwable t) {
     //                   Toast.makeText(getApplicationContext(), "FAILED", Toast.LENGTH_SHORT).show();
     //               }
     //           });
     //       }
     //   });
    }

    @Override
    public void changeFragment(int k) {
        FragmentManager fgmng = getSupportFragmentManager();
        FragmentTransaction trans = fgmng.beginTransaction();

        switch(k){
            case 1:
                trans.replace(R.id.fragmentContainerMain, fragments[0]);
                Toast.makeText(this, "FRAGMENT 1!", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                trans.replace(R.id.fragmentContainerMain, fragments[1]);
                Toast.makeText(this, "FRAGMENT 2!", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                trans.replace(R.id.fragmentContainerMain, fragments[2]);
                Toast.makeText(this, "FRAGMENT 3!", Toast.LENGTH_SHORT).show();
                break;
        }
        trans.commit();
    }
}