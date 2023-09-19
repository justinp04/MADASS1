package com.example.tictactoemad_20732435;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LeaderboardViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView nameView, Wins, Draws, Losses, Totalgames, WinPercent;
    public LeaderboardViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.imageview);
        nameView = itemView.findViewById(R.id.name);
        Wins = itemView.findViewById(R.id.wins);
        Draws = itemView.findViewById(R.id.draws);
        Losses = itemView.findViewById(R.id.losses);
        Totalgames = itemView.findViewById(R.id.totalgames);
        WinPercent = itemView.findViewById(R.id.winpercent);
    }
}
