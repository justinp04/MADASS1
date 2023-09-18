package com.example.tictactoemad_20732435;

import android.widget.Button;

public interface PlayerState
{
    public int playerTwoMove(GameData gameDataViewModel, boolean play1WinStatus);
    @Override
    public String toString();
}
