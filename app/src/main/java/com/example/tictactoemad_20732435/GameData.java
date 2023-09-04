package com.example.tictactoemad_20732435;

import android.widget.Button;

public class GameData {
    private Button[][] gameButtons;
    private int playerTurn;
    private int roundCount;
    private int player1Points;
    private int player2Points;
    //PlayerState
    //winState

    //Mutators
    private void setGameButtons(Button[][] inButtons) {
        gameButtons = inButtons;
    }

    public void setPlayer1Turn() {
        playerTurn = 1;
    }

    public void setPlayer2Turn() {
        playerTurn = 2;
    }

    public void incrementRound() {
        roundCount++;
    }

    public void decreaseRound() {
        roundCount--;
    }

    public void incrementPlayer1() {
        player1Points++;
    }

    public void incrementPlayer2() {
        player2Points++;
    }

    //public void setPlayerState(playerState newState) {


    //Accessors
    public Button[][] getGameButtons() {
        return gameButtons;
    }

    public int getPlayer1Points() {
        return player1Points;
    }

    public int getPlayer2Points() {
        return player2Points;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public int getRoundCount() {
        return roundCount;
    }
}
