package com.example.pokemonapi_test;

import android.annotation.SuppressLint;
import android.view.ViewGroup;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class APIPokemonRecyclerViewAdapter extends RecyclerView.Adapter<APIPokemonRecyclerViewAdapter.MyViewHolder>{

    private ArrayList<Pokemon> lPokemons;
    private Context context;
    private OnClickListener onClickListener;

    private Button editBtn, deleteBtn;

    private int selectedItem;

    private MyViewHolder lastItem = null;

    public APIPokemonRecyclerViewAdapter(Context context) {
        this.lPokemons = new ArrayList<>();
        Pokemon pokePlaceholder = new Pokemon();
        pokePlaceholder.setName("Pedro Sanchez");
        pokePlaceholder.setId(1);
        pokePlaceholder.setHeight(20);
        pokePlaceholder.setWeight(25);
        lPokemons.add(pokePlaceholder);
        this.context = context;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView pokeImg;
        private TextView pokeNum, pokeName, pokeH, pokeW;

        private CardView pokeCard;

        private CardView charItem;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pokeImg = itemView.findViewById(R.id.poke_img);
            pokeName = itemView.findViewById(R.id.poke_name);
            pokeW = itemView.findViewById(R.id.poke_weight);
            pokeH = itemView.findViewById(R.id.poke_height);
            pokeNum = itemView.findViewById(R.id.poke_num);
            pokeCard = itemView.findViewById(R.id.poke_card);

        }
    }
    @NonNull
    @Override
    public APIPokemonRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewHolder = LayoutInflater.from(context).inflate(R.layout.activity_recycler_pokedex, parent, false);
        viewHolder.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        return new MyViewHolder(viewHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull APIPokemonRecyclerViewAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.pokeName.setText(lPokemons.get(position).getName());
        Log.d("TEST POKE",String.valueOf(lPokemons.get(position).getName()));
        Toast.makeText(context,"HOLA",Toast.LENGTH_SHORT).show();
        holder.pokeH.setText(String.valueOf(lPokemons.get(position).getHeight()));
        holder.pokeW.setText(String.valueOf(lPokemons.get(position).getWeight()));
        holder.pokeNum.setText(String.valueOf(lPokemons.get(position).getId()));
        //Glide.with(holder.itemView.getContext()).load(lPokemons.get(position).sprites.front_default).into(holder.pokeImg);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(position);
                }
                else{
                    if(lastItem != null) {
                        lastItem.pokeCard.setBackgroundColor(Color.parseColor("#EEEEEE"));
                    }
                    lastItem = holder;
                    holder.pokeCard.setBackgroundColor(Color.parseColor("#D4EFDF"));
                    setSelectedItem(position);
                    //deleteBtn.setEnabled(true);
                    //editBtn.setEnabled(true);
                }
            }
        });

    }
    private void setSelectedItem(int position){
        this.selectedItem = position;
        Log.d("ITEM SELECTED: ", String.valueOf(lPokemons.get(selectedItem).getName()));
    }
    public void clearSelected(){
        lastItem.pokeCard.setBackgroundColor(Color.parseColor("#EEEEEE"));

    }
    public void addItem(Pokemon iPokemons) {
        ArrayList<Pokemon> pokeAux = new ArrayList<>(lPokemons);
        pokeAux.add(iPokemons);
        this.lPokemons = pokeAux;
    }
    public interface OnClickListener {
        void onClick(int position);
    }
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
    @Override
    public int getItemCount() {
        return lPokemons.size();
    }
}
