package com.example.tictactoemad_20732435;

import android.view.View;
import android.widget.Button;

public class GameFunctions {
    private static GameData gameDataViewModel;

    public GameFunctions(GameData gameDataViewModel) {
        this.gameDataViewModel = gameDataViewModel;
    }

    public static void player1Wins() {
        gameDataViewModel.incrementPlayer1();
        resetBoard();
    }

    public static void player2Wins() {
        gameDataViewModel.incrementPlayer2();
        resetBoard();
    }

    public static void draw() {
        resetBoard();
    }

    public static void resetBoard() {
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

    public static void resetGame() {
        gameDataViewModel.player1Points.setValue(0);
        gameDataViewModel.player2Points.setValue(0);
        resetBoard();
    }

    public static void onClick(View view) {
        if (!((Button) view).getText().toString().equals("")) {
            return;
        }

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

        if (checkForWin()) {
            if (gameDataViewModel.playerTurn.getValue() == 1) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (gameDataViewModel.getRoundCount() == 9) {
            draw();
        }
    }

    private static boolean checkForWin() {
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
