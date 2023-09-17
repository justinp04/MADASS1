package com.example.tictactoemad_20732435;

import android.widget.Button;

public class HumanPlayerState implements PlayerState
{
    @Override
    public int playerTwoMove(GameData gameDataViewModel, boolean play1WinStatus)
    {
        return 2;
    }

    @Override
    public String toString()
    {
        return "2 Player";
    }
}
