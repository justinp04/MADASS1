package com.example.tictactoemad_20732435;

import android.widget.Button;

public interface PlayerState
{
    public int playerTwoMove(Button[][] gameBoard);
    @Override
    public String toString();
}
