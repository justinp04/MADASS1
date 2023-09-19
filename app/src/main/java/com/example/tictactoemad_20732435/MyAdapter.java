package com.example.tictactoemad_20732435;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;

    List<Item> items;

    public MyAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.leaderboard_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nameView.setText(items.get(position).getName());
        holder.imageView.setImageResource(items.get(position).getImage());
        holder.Totalgames.setText(String.valueOf(items.get(position).getTotalgames()));
        holder.Wins.setText(String.valueOf(items.get(position).getWins()));
        holder.Draws.setText(String.valueOf(items.get(position).getDraws()));
        holder.Losses.setText(String.valueOf(items.get(position).getLosses()));
        holder.WinPercent.setText(String.valueOf(items.get(position).getWinpercent()));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
