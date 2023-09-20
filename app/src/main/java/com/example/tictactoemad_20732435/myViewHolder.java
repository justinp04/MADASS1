package com.example.tictactoemad_20732435;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myViewHolder extends RecyclerView.ViewHolder {

    ImageButton imageButton;

    public myViewHolder(@NonNull View itemView, ViewGroup parent){
    super(itemView);

    int hSize = parent.getMeasuredHeight() /3;
    ViewGroup.LayoutParams lp = itemView.getLayoutParams();
    lp.height = hSize;
    imageButton = itemView.findViewById(R.id.avatarSelect);

    }

}
