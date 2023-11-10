package com.example.pokemonapi_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b= (Button) findViewById(R.id.callAPI_BTN);
        TextView t = (TextView) findViewById(R.id.poketxt_TXT);
        ImageView imgV = (ImageView) findViewById(R.id.pokeimg_IMG);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://pokeapi.co/api/v2/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                PokemonAPIService pokeapis = retrofit.create(PokemonAPIService.class);
                pokeapis.getPokemon("ditto").enqueue(new Callback<Pokemon>() {
                    @Override
                    public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                        Pokemon p = response.body();
                        Toast.makeText(getApplicationContext(), p.getName(), Toast.LENGTH_SHORT).show();
                        t.setText(p.getName() + p.getHeight()+p.getWeight());
                        Glide.with(getApplicationContext()).load(p.sprites.front_default).into(imgV);

                    }

                    @Override
                    public void onFailure(Call<Pokemon> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "FAILED", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}