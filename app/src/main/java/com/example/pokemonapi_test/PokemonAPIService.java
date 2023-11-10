package com.example.pokemonapi_test;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
public interface PokemonAPIService {
    @GET("pokemon/{name}")
    Call<Pokemon> getPokemon(@Path("name") String name);

}
