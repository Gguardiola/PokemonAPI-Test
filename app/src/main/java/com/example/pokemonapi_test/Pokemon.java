package com.example.pokemonapi_test;

import java.util.List;

public class Pokemon {

    String name;
    int height;
    int weight;
    int id;
    Sprites sprites;
    List<AbilityData> abilities;


    public class Sprites{
        public String front_default;
    }

    public class Ability{
        public String name;
        public String url;
    }

    public class AbilityData{
        Ability ability;
        boolean is_hidden;
        int slot;
    }

    //GETTERS AND SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }
    public int getId() { return id; }
    public void setId(int i) { this.id = id; }

    public void setWeight(int weight) {
        this.weight = weight;
    }


}
