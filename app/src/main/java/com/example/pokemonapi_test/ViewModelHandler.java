package com.example.pokemonapi_test;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelHandler extends ViewModel {
    private MutableLiveData<String> name = new MutableLiveData<String>();

    public String getName(){
        return name.getValue();
    }

    public void setName(String s){
        name.setValue(s);
    }

    public MutableLiveData<String> getModel(){
        return name;
    }
}
