package com.example.tictactoemad_20732435;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardViewHolder> {

    Context context;

    List<LeaderboardItem> items;

    public LeaderboardAdapter(Context context, List<LeaderboardItem> items) {
        this.context = context;
        this.items = items;
    }


    @NonNull
    @Override
    public LeaderboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LeaderboardViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderboardViewHolder holder, int position) {
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
