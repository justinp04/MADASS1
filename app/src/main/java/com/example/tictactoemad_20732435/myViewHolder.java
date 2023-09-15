package com.example.tictactoemad_20732435;

import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myViewHolder extends RecyclerView.ViewHolder {

    ImageButton imageButton;

    public myViewHolder(@NonNull View itemView){
    super(itemView);

    imageButton = itemView.findViewById(R.id.avatarSelect);

    }

}
