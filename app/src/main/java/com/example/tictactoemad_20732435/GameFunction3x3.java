package com.example.tictactoemad_20732435;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.*;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameFunction3x3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameFunction3x3 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    int row = 3, col = 3;
    private Button[][] gameButtons = new Button[row][col];
    private boolean player1Turn = true;

    // Round count tracks how many moves have been made by both players
    private int roundCount;
    private int player1Points;
    private int player2Points;
    private int winCondition = 3;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    private TextView textMovesMade;
    private TextView textMovesLeft;

    private Button undoButton;

    private LinkedList<Button> undoList = new LinkedList<>();
    public GameFunction3x3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameFunction3x3.
     */
    // TODO: Rename and change types and number of parameters
    public static GameFunction3x3 newInstance(String param1, String param2) {
        GameFunction3x3 fragment = new GameFunction3x3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_game_function3x3, container, false);
        MainActivityData mainActivityDataViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);
        GameData gameDataViewModel = new ViewModelProvider(getActivity()).get(GameData.class);

        textViewPlayer1 = rootView.findViewById(R.id.player1_score);
        textViewPlayer2 = rootView.findViewById(R.id.player2_score);
        textMovesMade = rootView.findViewById(R.id.movesMade);
        textMovesLeft = rootView.findViewById(R.id.movesLeft);

        roundCount = 0;

        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", requireContext().getPackageName());
                gameButtons[i][j] = rootView.findViewById(resID);
                gameButtons[i][j].setOnClickListener(this::onClick);
            }
        }
        gameDataViewModel.setBoardSize(3);
        gameDataViewModel.setGameButtons(gameButtons);

        Button resetButton = rootView.findViewById(R.id.reset_button);
        Button settingsButton = rootView.findViewById(R.id.settings_button);
        Button menuButton = rootView.findViewById(R.id.menu_button);
        undoButton = rootView.findViewById(R.id.undo_button);
        undoButton.setEnabled(false);

        menuButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setClickedValue(0);
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mainActivityDataViewModel.setClickedValue(4);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                GameFunctions.resetGame(gameDataViewModel);
            }
        });

        undoButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int movesMade, movesLeft;

                if(roundCount > 0)
                {
                    // Get the button to be updated.
                    // Remove the last index to make sure the next undo will get a different button
                    Button buttonToUpdate = undoList.removeLast();

                    // Set the text to null
                    buttonToUpdate.setText("");

                    // Update roundCount value
                    roundCount--;

                    Toast.makeText(requireContext(), "Undo move", Toast.LENGTH_SHORT).show();

                    // Update the turn count textViews
                    textMovesMade.setText("Moves made: " + roundCount);
                    textMovesLeft.setText("Moves left: " + (9 - roundCount));

                    // Update whose turn it is
                    player1Turn = !player1Turn;
                }
                else
                {
                    undoButton.setEnabled(false);
                }
            }
        });

        return rootView;
    }

    public void onClick(View view)
    {
        GameData gameDataViewModel = new ViewModelProvider(getActivity()).get(GameData.class);
        String returnString = GameFunctions.onClick(view, gameDataViewModel);
        if (returnString != null)
        {
            Button currentButton = (Button)view;
        }

        // If the there is the button already has a value
        if(!undoButton.isEnabled())
        {
            undoButton.setEnabled(true);
        }

        if (!(currentButton).getText().toString().equals(""))
        {
            Toast.makeText(requireContext(), "Invalid Move!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (player1Turn)
        {
            currentButton.setText("X");
        }
        else
        {
            //change in here for AI view
            currentButton.setText("O");
        }

        // Add the most recently added button to the undo list.
        undoList.addLast(currentButton);

        // Update the round count by one to keep track of the number of turns
        roundCount++;
        updateMovesText();

        if (checkForWin()) {
            if (player1Turn) {
                player1Wins();
                updateMovesText();
            }
            else
            {
                player2Wins();
                updateMovesText();
            }
        }
        else if (roundCount == 9)
        {
            Toast.makeText(requireContext(), returnString, Toast.LENGTH_SHORT).show();
        }
        updateText();
    }

    private void updateText()
    {
        GameData gameDataViewModel = new ViewModelProvider(getActivity()).get(GameData.class);
        int player1Points = gameDataViewModel.getPlayer1Points();
        int player2Points = gameDataViewModel.getPlayer2Points();
        int roundCount = gameDataViewModel.getRoundCount();
        int movesLeft = (gameDataViewModel.getBoardSize() * gameDataViewModel.getBoardSize()) - gameDataViewModel.getRoundCount();

        textViewPlayer1.setText("Player 1: " + player1Points);
        textViewPlayer2.setText("Player 2: " + player2Points);
        textMovesMade.setText("Moves Made: " + roundCount);
        textMovesLeft.setText("Moves Left: " + movesLeft);
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
        roundCount = 0;
        undoList.clear();
        updatePointsText();
        resetBoard();
    }
}