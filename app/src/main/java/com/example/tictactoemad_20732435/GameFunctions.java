package com.example.tictactoemad_20732435;

import android.view.View;
import android.widget.Button;

public class GameFunctions {
    //private static GameData gameDataViewModel;

    /*
    public GameFunctions(GameData gameDataViewModel) {
        this.gameDataViewModel = gameDataViewModel;
    }

     */

    public static String player1Wins(GameData gameDataViewModel) {
        gameDataViewModel.incrementPlayer1();
        resetBoard(gameDataViewModel);
        return "Player 1 wins!";
    }

    public static String player2Wins(GameData gameDataViewModel) {
        gameDataViewModel.incrementPlayer2();
        resetBoard(gameDataViewModel);
        return "Player 2 wins!";
    }

    public static String draw(GameData gameDataViewModel) {
        gameDataViewModel.incrementDraws();
        resetBoard(gameDataViewModel);
        return "It's a draw!";
    }

    public static void resetBoard(GameData gameDataViewModel) {
        int row = gameDataViewModel.getBoardSize();
        int col = gameDataViewModel.getBoardSize();
        Button gameButtons[][] = gameDataViewModel.getGameButtons();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                gameButtons[i][j].setText("");
            }
        }

        gameDataViewModel.setGameButtons(gameButtons);
        gameDataViewModel.roundCount.setValue(0); //replace with method
        gameDataViewModel.setPlayer1Turn();
    }

    public static void resetGame(GameData gameDataViewModel) {
        gameDataViewModel.player1Points.setValue(0);
        gameDataViewModel.player2Points.setValue(0);
        resetBoard(gameDataViewModel);
    }

    public static String onClick(View view, GameData gameDataViewModel) {
        String returnString = null;
        if (((Button) view).getText().toString().equals("")) {

            if (gameDataViewModel.playerTurn.getValue() == 1) {
                ((Button) view).setText("X");
                gameDataViewModel.incrementRound();

                int playerTurn = gameDataViewModel.getPlayerState().playerTwoMove(gameDataViewModel.getGameButtons());

                gameDataViewModel.playerTurn.setValue(playerTurn);
            } else {
                //change in here for AI view
                ((Button) view).setText("O");
                gameDataViewModel.setPlayer1Turn();
                gameDataViewModel.incrementRound();
            }
            gameDataViewModel.incrementRound();

            //Fix logic
            if (checkForWin(gameDataViewModel)) {
                if (gameDataViewModel.playerTurn.getValue() == 2) {
                    returnString = player1Wins(gameDataViewModel);
                } else {
                    returnString = player2Wins(gameDataViewModel);
                }
            } else if (gameDataViewModel.getRoundCount() == 9) {
                returnString = draw(gameDataViewModel);
            }
        }
        return returnString;
    }

    private static boolean checkForWin(GameData gameDataViewModel) {
        int row = gameDataViewModel.getBoardSize();
        int col = gameDataViewModel.getBoardSize();
        Button gameButtons[][] = gameDataViewModel.getGameButtons();

        String[][] fields = new String[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                fields[i][j] = gameButtons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < row; i++) {
            if (fields[i][0].equals(fields[i][1])
                    && fields[i][0].equals(fields[i][2])
                    && !fields[i][0].isEmpty()) {
                return true;
            }
        }

        for (int j = 0; j < col; j++) {
            if (fields[0][j].equals(fields[1][j])
                    && fields[0][j].equals(fields[2][j])
                    && !fields[0][j].isEmpty()) {
                return true;
            }
        }

        if (fields[0][0].equals(fields[1][1])
                && fields[0][0].equals(fields[2][2])
                && !fields[0][0].isEmpty()) {
            return true;
        }

        if (fields[0][2].equals(fields[1][1])
                && fields[0][2].equals(fields[2][0])
                && !fields[0][2].isEmpty()) {
            return true;
        }
        return false;
    }
}
