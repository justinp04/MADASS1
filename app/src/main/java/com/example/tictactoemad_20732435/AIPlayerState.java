package com.example.tictactoemad_20732435;

import android.widget.Button;

import androidx.lifecycle.ViewModelProvider;

import java.util.Random;

public class AIPlayerState implements PlayerState
{
    @Override
    public int playerTwoMove(Button[][] gameBoard)
    {
        try
        {
            Thread.sleep(500);
        }
        catch (InterruptedException e)
        {
            //Do nothing
        }
        finally
        {
            Button tile = generateRandomTile(gameBoard);
            tile.setText("O");
            return 1;
        }
    }

    private Button generateRandomTile(Button[][] gameBoard)
    {
        Random rand = new Random();
        Button tile = null;
        int boardLength = gameBoard.length;
        boolean emptyTile = false;
        do
        {
            int horizPoint = rand.nextInt(boardLength);
            int vertPoint = rand.nextInt(boardLength);
            tile = gameBoard[horizPoint][vertPoint];

            if (tile.getText().toString().equals(""))
                emptyTile = true;
        }while (emptyTile == false);

        return tile;
    }

    @Override
    public String toString()
    {
        return "AI";
    }
}