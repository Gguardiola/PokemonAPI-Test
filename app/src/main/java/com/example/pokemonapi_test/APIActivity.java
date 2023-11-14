package com.example.pokemonapi_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.pokemonapi_test.Interfaces.FragmentHandler;

public class APIActivity extends AppCompatActivity implements FragmentHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apiactivity);
    }

    @Override
    public void changeFragment(int k) {


        Toast.makeText(this, "Estoy en APIActivity", Toast.LENGTH_SHORT).show();
    }
}