package com.example.pokemonapi_test.Interfaces;

import com.example.pokemonapi_test.Pokemon;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokemonAPIService {
    @GET("pokemon/{name}")
    Call<Pokemon> getPokemon(@Path("name") String name);
    @GET("pokemon/{id}")
    //TODO: creo que no puedo ahcer arraylist de pokemons
    Call<Pokemon> getPokemonById(@Path("id") int id);
}
