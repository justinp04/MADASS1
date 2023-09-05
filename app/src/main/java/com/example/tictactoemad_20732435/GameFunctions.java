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

                int playerTurn = gameDataViewModel.getPlayerState().playerTwoMove(gameDataViewModel.getGameButtons());

                gameDataViewModel.playerTurn.setValue(playerTurn);
                gameDataViewModel.incrementRound();
            } else {
                //change in here for AI view
                ((Button) view).setText("O");
                gameDataViewModel.setPlayer1Turn();
                gameDataViewModel.incrementRound();
            }

            //Fix logic
            if (checkForWin(gameDataViewModel, gameDataViewModel.getWinCondition(), "X")) {
                if (gameDataViewModel.playerTurn.getValue() == 1) {
                    returnString = player2Wins(gameDataViewModel);
                }
            }
            if (checkForWin(gameDataViewModel, gameDataViewModel.getWinCondition(), "O")) {
                if (gameDataViewModel.playerTurn.getValue() == 2) {
                    returnString = player1Wins(gameDataViewModel);
                }
            } else if (gameDataViewModel.getRoundCount() == (gameDataViewModel.getBoardSize() * gameDataViewModel.getBoardSize())) {
                returnString = draw(gameDataViewModel);
            }
        }
        return returnString;
    }

    private static boolean checkForWin(GameData gameDataViewModel, int winCondition, String playerSymbol) {
        int row = gameDataViewModel.getBoardSize();
        int col = gameDataViewModel.getBoardSize();
        Button gameButtons[][] = gameDataViewModel.getGameButtons();
        StringBuilder winBuilder = new StringBuilder();

        for (int i = 0; i < winCondition; i++)
        {
            winBuilder.append(playerSymbol);
        }
        String winCompare = winBuilder.toString();

        String[][] fields = new String[row][col];
        StringBuffer rowBuffer = new StringBuffer();
        StringBuffer colBuffer = new StringBuffer();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                fields[i][j] = gameButtons[i][j].getText().toString();
            }
        }
        //good ^^

        //check row and columns
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                rowBuffer.append(fields[i][j]);
                colBuffer.append(fields[j][i]);
            }
            String rowCheck = rowBuffer.toString();
            String colCheck = colBuffer.toString();

            if (rowCheck.contains(winCompare))
                return true;
            if (colCheck.contains(winCompare))
                return true;

            rowBuffer.delete(0, rowBuffer.length());
            colBuffer.delete(0, colBuffer.length());
        }

        //check back diagonals
        int length = fields.length;
        int diagonalLines = (length * 2) - 1;
        int midLine = (diagonalLines / 2) + 1;
        int buttonsInDiagonal = 0;

        for (int i = 1; i <= diagonalLines; i++) {
            StringBuffer diagBuffer = new StringBuffer();
            int rowIndex, colIndex;
            if (i <= midLine) {
                buttonsInDiagonal++;
                for (int j = 0; j < buttonsInDiagonal; j++) {
                    rowIndex = (i - j) - 1;
                    colIndex = j;
                    diagBuffer.append(fields[rowIndex][colIndex]);
                }
            } else {
                buttonsInDiagonal--;
                for (int j = 0; j < buttonsInDiagonal; j++) {
                    rowIndex = (length - 1) - j;
                    colIndex = (i - length) + j;
                    diagBuffer.append(fields[rowIndex][colIndex]);
                }
            }
            String diagCheck = diagBuffer.toString();
            if (diagCheck.contains(winCompare)) {
                return true;
            }
            diagBuffer.delete(0, rowBuffer.length());
        }

        //check forward diag - not working just yet
        buttonsInDiagonal = 0;
        for (int i = 1; i <= diagonalLines; i++) {
            StringBuffer diagBuffer = new StringBuffer();
            int rowIndex, colIndex;
            if (i <= midLine) {
                buttonsInDiagonal++;
                for (int j = 0; j < buttonsInDiagonal; j++) {
                    rowIndex = (length - j) - 1;
                    colIndex = (i - j) - 1;
                    diagBuffer.append(fields[rowIndex][colIndex]);
                }
            } else {
                buttonsInDiagonal--;
                for (int j = 0; j < buttonsInDiagonal; j++) {
                    colIndex = (length - 1) - j;
                    rowIndex = (i - length) + j;
                    diagBuffer.append(fields[rowIndex][colIndex]);
                }
            }
            String diagCheck = diagBuffer.toString();
            if (diagCheck.contains(winCompare)) {
                return true;
            }
            diagBuffer.delete(0, rowBuffer.length());
        }
        return false;
    }
}
