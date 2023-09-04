package com.example.tictactoemad_20732435;

import android.widget.Button;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GameData extends ViewModel {
    public MutableLiveData<Button[][]> gameButtons;
    public MutableLiveData<Integer> boardSize;
    public MutableLiveData<Integer>playerTurn;
    public MutableLiveData<Integer>roundCount;
    public MutableLiveData<Integer>player1Points;
    public MutableLiveData<Integer>player2Points;
    public MutableLiveData<PlayerState> playerState;
    //winState

    public GameData()
    {
        gameButtons = new MutableLiveData<Button[][]>();
        boardSize = new MutableLiveData<Integer>();
        playerTurn = new MutableLiveData<Integer>();
        roundCount = new MutableLiveData<Integer>();
        player1Points = new MutableLiveData<Integer>();
        player2Points = new MutableLiveData<Integer>();
        playerState = new MutableLiveData<PlayerState>();
        setDefaultValues();
    }

    //Mutators
    public void setDefaultValues() {
        gameButtons.setValue(new Button[3][3]);
        playerTurn.setValue(1);
        roundCount.setValue(0);
        player1Points.setValue(0);
        player2Points.setValue(0);
        boardSize.setValue(3);
        playerState.setValue(new AIPlayerState());
    }
    public void setGameButtons(Button[][] inButtons) {
        gameButtons.setValue(inButtons);
    }

    public void setBoardSize(int size) {
        boardSize.setValue(size);
    }

    public void setPlayer1Turn() {
        playerTurn.setValue(1);
    }

    public void setPlayer2Turn() {
        playerTurn.setValue(2);
    }

    public void incrementRound() {
        int roundIncrease = roundCount.getValue() + 1;

        roundCount.setValue(roundIncrease);
    }

    public void decreaseRound() {
        int roundDecrease = roundCount.getValue() - 1;

        roundCount.setValue(roundDecrease);
    }

    public void incrementPlayer1() {
        int player1Point = player1Points.getValue() + 1;

        player1Points.setValue(player1Point);
    }

    public void incrementPlayer2() {
        int player2Point = player2Points.getValue() + 1;

        player2Points.setValue(player2Point);
    }

    public void setPlayerState(PlayerState inState) {
        playerState.setValue(inState);
    }

    //public void setPlayerState(playerState newState) {

    //Accessors
    public Button[][] getGameButtons() {
        return gameButtons.getValue();
    }

    public int getBoardSize() {
        return boardSize.getValue();
    }

    public int getPlayer1Points() {
        return player1Points.getValue();
    }

    public int getPlayer2Points() {
        return player2Points.getValue();
    }

    public int getPlayerTurn() {
        return playerTurn.getValue();
    }

    public int getRoundCount() {
        return roundCount.getValue();
    }

    public PlayerState getPlayerState() {
        return playerState.getValue();
    }
}
