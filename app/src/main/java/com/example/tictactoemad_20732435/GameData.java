package com.example.tictactoemad_20732435;

import android.widget.Button;

public class GameData {
    private Button[][] gameButtons;
    private int boardSize;
    private int playerTurn;
    private int roundCount;
    private int player1Points;
    private int player2Points;
    private PlayerState playerState;
    //winState

    //Mutators
    private void setGameButtons(Button[][] inButtons) {
        gameButtons = inButtons;
    }

    public void setBoardSize(int size) {
        boardSize = size;
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

    public void setPlayerState(PlayerState inState) {
        playerState = inState;
    }

    public void setDefaultValues() {
        playerTurn = 1;
        roundCount = 0;
        player1Points = 0;
        player2Points = 0;
        boardSize = 3;
        playerState = new AIPlayerState();
    }

    //public void setPlayerState(playerState newState) {

    //Accessors
    public Button[][] getGameButtons() {
        return gameButtons;
    }

    public int getBoardSize() {
        return boardSize;
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

    public PlayerState getPlayerState() {
        return playerState;
    }
}
