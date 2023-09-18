package com.example.tictactoemad_20732435;

import android.widget.Button;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GameData extends ViewModel {
    public MutableLiveData<Button[][]> gameButtons;
    public MutableLiveData<Integer> winCondition;
    public MutableLiveData<Integer> boardSize;
    public MutableLiveData<Integer> playerTurn;
    public MutableLiveData<Integer> roundCount;
    public MutableLiveData<Integer> player1Points;
    public MutableLiveData<Integer> player2Points;
    public MutableLiveData<Integer> drawCount;
    public MutableLiveData<PlayerState> playerState;

    public MutableLiveData<Boolean> aiFinished;
    public MutableLiveData<String> player1Symbol;
    public MutableLiveData<String> player2Symbol;

    public GameData() {
        gameButtons = new MutableLiveData<Button[][]>();
        boardSize = new MutableLiveData<Integer>();
        playerTurn = new MutableLiveData<Integer>();
        roundCount = new MutableLiveData<Integer>();
        player1Points = new MutableLiveData<Integer>();
        player2Points = new MutableLiveData<Integer>();
        drawCount = new MutableLiveData<Integer>();
        playerState = new MutableLiveData<PlayerState>();
        winCondition = new MutableLiveData<Integer>();
        aiFinished = new MutableLiveData<Boolean>();
        player1Symbol = new MutableLiveData<String>();
        player2Symbol = new MutableLiveData<String>();
        setDefaultValues();
    }

    //Mutators
    public void setDefaultValues() {
        gameButtons.setValue(new Button[3][3]);
        playerTurn.setValue(1);
        roundCount.setValue(0);
        player1Points.setValue(0);
        player2Points.setValue(0);
        drawCount.setValue(0);
        boardSize.setValue(3);
        winCondition.setValue(3);
        playerState.setValue(new AIPlayerState());
        player1Symbol.setValue("X");
        player2Symbol.setValue("O");
        aiFinished.setValue(true);
    }

    public void setGameButtons(Button[][] inButtons) {
        gameButtons.setValue(inButtons);
    }

    public void setPlayer1Symbol(String s)
    {
        player1Symbol.setValue(s);
    }

    public void setPlayer2Symbol(String s)
    {
        player2Symbol.setValue(s);
    }

    public void setBoardSize(int size) {
        boardSize.setValue(size);
    }

    public void setPlayer1Turn() {
        playerTurn.setValue(1);
    }
    public void setWinCondition(int win)
    {
        winCondition.setValue(win);
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

    public void incrementDraws() {
        int draws = drawCount.getValue() + 1;
        drawCount.setValue(draws);
    }

    public void setPlayerState(PlayerState inState) {
        playerState.setValue(inState);
    }

    public void setAiFinished() { aiFinished.setValue(true); }
    public void setAiNotFinished() { aiFinished.setValue(false); }

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

    public String getPlayer1Symbol()
    {
        return player1Symbol.getValue();
    }

    public String getPlayer2Symbol()
    {
        return player2Symbol.getValue();
    }

    public int getDrawCount() {
        return drawCount.getValue();
    }

    public int getWinCondition()
    {
        return winCondition.getValue();
    }

    public PlayerState getPlayerState() {
        return playerState.getValue();
    }
    public Boolean getAiFinished() { return aiFinished.getValue(); }
}
