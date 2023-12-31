package com.example.tictactoemad_20732435;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import java.util.*;
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
    public MutableLiveData<String> player1Name;
    public MutableLiveData<String> player2Name;
    public MutableLiveData<Drawable> player1Image;
    public MutableLiveData<Drawable> player2Image;
    public MutableLiveData<Integer> player1ImageID;
    public MutableLiveData<Integer> player2ImageID;
    public Stack<Button> undoButtons;

    public Drawable avatar1;


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
        undoButtons = new Stack<Button>();
        player1Name = new MutableLiveData<String>();
        player2Name = new MutableLiveData<String>();
        player1Image = new MutableLiveData<Drawable>();
        player2Image = new MutableLiveData<Drawable>();
        player1ImageID = new MutableLiveData<Integer>();
        player2ImageID = new MutableLiveData<Integer>();
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
        player1Name.setValue("Player 1");
        player2Name.setValue("Player 2");
        player1Image.setValue(Drawable.createFromPath(String.valueOf(R.drawable.default_avatar)));
        player2Image.setValue(Drawable.createFromPath(String.valueOf(R.drawable.default_avatar)));
        player1ImageID.setValue(R.drawable.default_avatar);
        player2ImageID.setValue(R.drawable.default_avatar);
    }

    public Bundle getGameStatsBundle(){
        Bundle gameStats = new Bundle();
        int P1wins = player1Points.getValue();
        int P2wins = player2Points.getValue();
        int totalGames = roundCount.getValue();
        int draws = drawCount.getValue();
        int player1Image = player1ImageID.getValue();
        int player2Image = player2ImageID.getValue();
        String P1Name = player1Name.getValue();
        String P2Name = player2Name.getValue();
        gameStats.putInt("P1Wins", P1wins);
        gameStats.putInt("P2Wins", P2wins);
        gameStats.putInt("TotalGames", totalGames);
        gameStats.putInt("Draws", draws);
        gameStats.putInt("P1Avatar", player1Image);
        gameStats.putInt("P2Avatar", player2Image);
        gameStats.putString("P1Name", P1Name);
        gameStats.putString("P2Name", P2Name);

        return gameStats;
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

    // Adds buttons to the stack so that we can add later undo in the correct order.
    public void addButton(Button button)
    {
        undoButtons.add(button);
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

    public void setPlayer1Name(String inString)
    {
        if (inString != null)
        {
            player1Name.setValue(inString);
        }
    }
    public void setPlayer2Name(String inString)
    {
        if (inString != null)
        {
            player2Name.setValue(inString);
        }
    }

    public void setPlayer1Image(Drawable drawable){
        if(drawable != null) {
            player1Image.setValue(drawable);
        }
    }

    public void setPlayer2Image(Drawable drawable){
        if(drawable != null){
            player2Image.setValue(drawable);
        }

    }

    public void setPlayer1ImageID(Integer inPlayer1ImageID) {
        player1ImageID.setValue(inPlayer1ImageID);
    }
    public void setPlayer2ImageID(Integer inPlayer2ImageID) {
        player2ImageID.setValue(inPlayer2ImageID);
    }

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

    public Drawable getPlayer1Image(){
        return player1Image.getValue();
    }

    public Drawable getPlayer2Image(){
        return player2Image.getValue();
    }
    public Integer getPlayer1ImageID(){
        return player1ImageID.getValue();
    }

    public Integer getPlayer2ImageID(){
        return player2ImageID.getValue();
    }

    public PlayerState getPlayerState() {
        return playerState.getValue();
    }
    public Boolean getAiFinished() { return aiFinished.getValue(); }
    public String getPlayer1Name() { return player1Name.getValue(); }
    public String getPlayer2Name() { return player2Name.getValue(); }
}
