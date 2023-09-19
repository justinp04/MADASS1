package com.example.tictactoemad_20732435;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
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

    private TextView textViewPlayer1, textViewPlayer2;
    private TextView textMovesMade, textMovesLeft;
    private TextView textTimer;
    private TextView playerIndicator;
    private String player1Name, player2Name;
    private Integer timerCounter;
    private Button undoButton, pauseButton;

    private CountDownTimer turnTimer;

    // Required empty public constructor
    public GameFunction3x3() {}

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
        textTimer = rootView.findViewById(R.id.timer);
        playerIndicator = rootView.findViewById(R.id.playerTurn);
        player1Name = gameDataViewModel.getPlayer1Name();
        player2Name = gameDataViewModel.getPlayer2Name();
        playerIndicator.setText(player1Name + "'s turn!");


        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", requireContext().getPackageName());
                gameButtons[i][j] = rootView.findViewById(resID);
                gameButtons[i][j].setOnClickListener(this::onClick);

                //if(savedInstanceState != null)
//                {
//                    Log.d("savedInstanceState", "Enter with saved instance state: " + savedInstanceState.get(buttonID).toString());
//                    //The information is saved and it enters the program, but we need to give the buttons their text
//                    gameButtons[i][j].setText(savedInstanceState.get(buttonID).toString());
//
//                    Log.d("ButtonText", "The button text after assignent is: " + gameButtons[i][j].getText());
//                }

                // To save the information before an orientation change.
            }
        }
        gameDataViewModel.setBoardSize(3);
        gameDataViewModel.setGameButtons(gameButtons);

        Button resetButton = rootView.findViewById(R.id.reset_button);
        Button menuButton = rootView.findViewById(R.id.menu_button);
        Button pauseButton = rootView.findViewById(R.id.pause_button);
        undoButton = rootView.findViewById(R.id.undo_button);
        undoButton.setEnabled(false);

        //Initialise the CountDownTime Functions
        timerCounter = 30;
        turnTimer = new CountDownTimer(30000, 1000){
            @Override
            public void onTick(long l)
            {
                textTimer.setText(timerCounter.toString());
                timerCounter--;
            }

            @Override
            public void onFinish() {
                textTimer.setText(timerCounter.toString());
                String toastText = "Out of time! ";
                if (gameDataViewModel.getPlayerTurn() == 1)
                {
                    toastText = toastText + GameFunctions.player2Wins(gameDataViewModel);
                } else if (gameDataViewModel.getPlayerTurn() == 2) {
                    toastText = toastText + GameFunctions.player1Wins(gameDataViewModel);
                }
                Toast.makeText(requireContext(), toastText, Toast.LENGTH_SHORT).show();
                updatePlayerText(gameDataViewModel);
            }
        };

        menuButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setClickedValue(0);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                GameFunctions.resetGame(gameDataViewModel);
                updatePlayerText(gameDataViewModel);
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setClickedValue(5);
            }
        });

        undoButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int movesMade, movesLeft;

                Log.d("UNDOBUTTON", "Click undo");

                if(gameDataViewModel.getRoundCount() > 0)
                {
                    Button buttonToUpdate;
                    // Get the button to be updated.
                    // Remove the last index to make sure the next undo will get a different button
                    buttonToUpdate = gameDataViewModel.undoButtons.pop();

                    // Set the text to null
                    buttonToUpdate.setText("");

                    // Update roundCount value
                    gameDataViewModel.decreaseRound();

                    Toast.makeText(requireContext(), "Undo move", Toast.LENGTH_SHORT).show();

                    // Update the turn count textViews and Update whose turn it is
                    // If it is playerOne's turn
                    if (gameDataViewModel.playerTurn.getValue() == 1)
                    {
                        // Call the AI again if it is an AI player state
                        boolean play1Wins = false;
                        if(gameDataViewModel.getPlayerState() instanceof AIPlayerState)
                        {
                            Log.d("ENTER_INSTANCE_OF", "Has entered instance of " + player1Turn);
                            gameDataViewModel.getPlayerState().playerTwoMove(gameDataViewModel, play1Wins);
                        }
                        gameDataViewModel.setPlayer2Turn();
                    }
                    else
                    {
                        gameDataViewModel.setPlayer1Turn();
                    }

                    updatePlayerText(gameDataViewModel);
                }
                else
                {
                    undoButton.setEnabled(false);
                }
            }
        });

        //Observer to disable buttons while ai is making its move.
        gameDataViewModel.aiFinished.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (gameDataViewModel.getAiFinished())
                {
                    //Before enabling buttons check to see if ai has won.
                    winMessage(GameFunctions.checkPlayer2Wins(gameDataViewModel), gameDataViewModel);
                    for(int i = 0; i < gameButtons.length; i++)
                    {
                        for (int j = 0; j < gameButtons[i].length; j++)
                        {
                            gameButtons[i][j].setEnabled(true);
                        }
                    }
                }
                else if (!gameDataViewModel.getAiFinished())
                {
                    for(int i = 0; i < gameButtons.length; i++)
                    {
                        for (int j = 0; j < gameButtons[i].length; j++) {
                            gameButtons[i][j].setEnabled(false);
                        }
                    }
                }

            }
        });

        return rootView;
    }

    public void onClick(View view) {
        GameData gameDataViewModel = new ViewModelProvider(getActivity()).get(GameData.class);

        //Add button to undo button list
        Button clickedButton = (Button)view;
        if(clickedButton.getText().toString() == "")
        {
            gameDataViewModel.undoButtons.add((Button) view);
        }

        //Run universal onClick function.
        String returnString = GameFunctions.onClick(view, gameDataViewModel);

        //Update on screen game stats
        updatePlayerText(gameDataViewModel);

        //Print win message if game has been won.
        winMessage(returnString, gameDataViewModel);

        // Enable the undo button
        if(!undoButton.isEnabled())
        {
            undoButton.setEnabled(true);
        }
    }

    private void updatePlayerText(GameData gameDataViewModel)
    {
        turnTimer.cancel();
        textViewPlayer1.setText("Player 1: " + gameDataViewModel.getPlayer1Points());
        textViewPlayer2.setText("Player 2: " + gameDataViewModel.getPlayer2Points());
        textMovesLeft.setText("Moves Left: " + (9 - gameDataViewModel.getRoundCount()));
        textMovesMade.setText("Moves Made: " + gameDataViewModel.getRoundCount());

        if(gameDataViewModel.getPlayerTurn() == 1)
        {
            playerIndicator.setText("" + player1Name + "'s turn!");
        }
        else
        {
            playerIndicator.setText("" + player2Name + "'s turn!");
        }

        timerCounter = 30;
        turnTimer.start();
    }

    private void winMessage(String inString, GameData gameDataViewModel)
    {
        //Display win message if it exists.
        if (inString != null) {
            Toast.makeText(requireContext(), inString, Toast.LENGTH_SHORT).show();
            //Update displayed stats
            updatePlayerText(gameDataViewModel);
        }
    }

    //    @Override
//    public void onSaveInstanceState(@NonNull Bundle outState)
//    {
//        super.onSaveInstanceState(outState);
//
//        String buttonId;
//        Button butt;
//
//        // Iterate through all the buttons and save the information stored on them
//        for(int i = 0; i < row; i++)
//        {
//            for(int j = 0; j < col; j++)
//            {
//                buttonId = "button_" + i + j;
//                butt = gameButtons[i][j];
//                outState.putString(buttonId, butt.getText().toString());
//            }
//        }
//
//        // Save the information from the textView elements.
//        outState.putString("MOVESMADE", textMovesMade.getText().toString());
//        outState.putString("MOVESLEFT", textMovesLeft.getText().toString());
//        outState.putString("ROUNDCOUNT", Integer.toString(roundCount));
//    }
}