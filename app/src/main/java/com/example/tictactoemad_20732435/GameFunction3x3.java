package com.example.tictactoemad_20732435;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    int row = 3, col = 3;
    private Button[][] gameButtons = new Button[row][col];
    private boolean player1Turn = true;

    // Round count tracks how many moves have been made by both players
    private int roundCount, player1Points, player2Points;
    private int winCondition = 3;

    private TextView textViewPlayer1, textViewPlayer2;
    private TextView textMovesMade, textMovesLeft;
    private Button undoButton;
    private LinkedList<Button> undoList = new LinkedList<>();

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
    public static GameFunction3x3 newInstance(String param1, String param2)
    {
        GameFunction3x3 fragment = new GameFunction3x3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (getArguments() != null)
        {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_game_function3x3, container, false);
        MainActivityData mainActivityDataViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);
        GameData gameDataViewModel = new ViewModelProvider(getActivity()).get(GameData.class);

        textViewPlayer1 = rootView.findViewById(R.id.player1_score);
        textViewPlayer2 = rootView.findViewById(R.id.player2_score);
        textMovesMade = rootView.findViewById(R.id.movesMade);
        textMovesLeft = rootView.findViewById(R.id.movesLeft);

        roundCount = 0;

        // Nested for loop to set onClickListeners for the gameButtons.
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", requireContext().getPackageName());

                gameButtons[i][j] = rootView.findViewById(resID);
                //gameButtons[i][j].setText("X");

                gameButtons[i][j].setOnClickListener(this::onClick);

//                if(savedInstanceState != null)
//                {
//                    Log.d("savedInstanceState", "Enter with saved instance state: " + savedInstanceState.get(buttonID).toString());
//                    //The information is saved and it enters the program, but we need to give the buttons their text
//                    gameButtons[i][j].setText(savedInstanceState.get(buttonID).toString());
//
//                    Log.d("ButtonText", "The button text after assignent is: " + gameButtons[i][j].getText());
//                }
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
            Toast.makeText(requireContext(), returnString, Toast.LENGTH_SHORT).show();
        }
    }

    // To save the information before an orientation change.
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