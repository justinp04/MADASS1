package com.example.tictactoemad_20732435;

import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Button;

import androidx.lifecycle.ViewModelProvider;

import java.util.Random;

public class AIPlayerState implements PlayerState {
    @Override
    public int playerTwoMove(GameData gameDataViewModel, boolean play1WinStatus) {
        //Timer to allow the AI to wait a second before changing the tile.
        CountDownTimer timer = new CountDownTimer(1000, 1000) {

            @Override
            public void onTick(long l) {
                //do nothing, wait
                gameDataViewModel.setAiNotFinished();
            }

            @Override
            public void onFinish() {
                int boardSize = gameDataViewModel.getBoardSize();
                int round = gameDataViewModel.getRoundCount();
                if (round < boardSize * boardSize) {
                    Button tile = generateRandomTile(gameDataViewModel.getGameButtons());

                    // Add the button that the AI added to the undo list.
                    gameDataViewModel.addButton(tile);

                    tile.setText(gameDataViewModel.getPlayer2Symbol());
                    gameDataViewModel.incrementRound();

                    // Change the player turn to player one's turn
                    gameDataViewModel.setPlayer1Turn();
                    gameDataViewModel.setAiFinished();
                    Log.d("AiFinish", "AiFinish");
                }
            }
        };

        int finalRound = gameDataViewModel.getBoardSize() * gameDataViewModel.getBoardSize();

        if (gameDataViewModel.getRoundCount() == 0) {
            timer.cancel();
        }
        //Only start if last round wasn't a draw or if player 1 didn't win.
        else if ((gameDataViewModel.getRoundCount() < finalRound - 1) && (!play1WinStatus))
        {
            timer.start();
        }

        return 1;
    }

    private Button generateRandomTile(Button[][] gameBoard) {
        Random rand = new Random();
        Button tile = null;
        int boardLength = gameBoard.length;
        boolean emptyTile = false;
        do {
            int horizPoint = rand.nextInt(boardLength);
            int vertPoint = rand.nextInt(boardLength);
            tile = gameBoard[horizPoint][vertPoint];

            if (tile.getText().toString().equals(""))
                emptyTile = true;
        } while (emptyTile == false);

        return tile;
    }

    @Override
    public String toString() {
        return "AI";
    }
}