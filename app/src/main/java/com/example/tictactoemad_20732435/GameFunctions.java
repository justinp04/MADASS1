package com.example.tictactoemad_20732435;

import android.content.Context;
import android.widget.Button;
import android.widget.Toast;

public class GameFunctions {
    int row, col;
    private boolean playerTurn;
    private int player1Points;
    private int player2Points;
    private int roundCount;
    //private PlayerState playerState;
    //private WinConditionState winConditionState;
    private Button GameButtons[][];

    private void player1Wins()
    {
        player1Points++;
        updatePointsText();
        resetBoard();
    }
    private void player2Wins()
    {
        player2Points++;
        updatePointsText();
        resetBoard();
    }
    private void draw()
    {
        resetBoard();
    }

    private void updatePointsText()
    {
        textViewPlayer1.setText("Player 1: " + player1Points);
        textViewPlayer2.setText("Player 2: " + player2Points);
    }

    private void updateMovesText()
    {
        textMovesMade.setText("Moves Made: " + roundCount);
        textMovesLeft.setText("Moves Left " + (9 - roundCount));
    }

    private void resetBoard()
    {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                gameButtons[i][j].setText("");
            }
        }
        roundCount = 0;
        player1Turn = true;
    }
    private void resetGame()
    {
        player1Points = 0;
        player2Points = 0;
        updatePointsText();
        resetBoard();
    }
}
