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
    private int roundCount;
    private int player1Points;
    private int player2Points;
    private int winCondition = 3;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    private TextView textMovesMade;
    private TextView textMovesLeft;
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
        textViewPlayer1 = rootView.findViewById(R.id.player1_score);
        textViewPlayer2 = rootView.findViewById(R.id.player2_score);
        textMovesMade = rootView.findViewById(R.id.movesMade);
        textMovesLeft = rootView.findViewById(R.id.movesLeft);

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
        Button resetButton = rootView.findViewById(R.id.reset_button);
        Button settingsButton = rootView.findViewById(R.id.settings_button);
        Button menuButton = rootView.findViewById(R.id.menu_button);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setClickedValue(0);
            }
        });
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setClickedValue(4);
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                resetGame();
            }
        });
        // Inflate the layout for this fragment

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

                    // Update the turn count textViews
                    textMovesMade.setText("Moves made: " + roundCount);
                    textMovesLeft.setText("Moves left: " + (9 - roundCount));

                    // Update whose turn it is
                    player1Turn = !player1Turn;

                    if(roundCount == 0)
                    {
                        undoButton.setEnabled(false);
                    }
                }
            }
        });

        return rootView;
    }

    public void onClick(View view)
    {
        if (!((Button) view).getText().toString().equals("")) 
        {
            Button currentButton = (Button)view;
        }

        // If the undo button is currently disabled, enable the undo button
        if(!undoButton.isEnabled())
        {
            undoButton.setEnabled(true);
        }

        if (!(currentButton).getText().toString().equals(""))
        {
            Toast.makeText(requireContext(), "Invalid Move!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (player1Turn) {
            ((Button) view).setText("X");
        }
        else
        {
            //change in here for AI view
            ((Button) view).setText("O");
        }
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
            draw();
            updateMovesText();
        }
        else
        {
            player1Turn = !player1Turn; //swaps turn each time
        }
    }

    private boolean checkForWin() {
        String[][] fields = new String[row][col];

        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
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

    private void player1Wins()
    {
        player1Points++;
        Toast.makeText(requireContext(), "Player 1 Wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }
    private void player2Wins()
    {
        player2Points++;
        Toast.makeText(requireContext(), "Player 2 Wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }
    private void draw()
    {
        Context MainActivity;
        Toast.makeText(requireContext(), "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointsText()
    {
        textViewPlayer1.setText("Player 1: " + player1Points);
        textViewPlayer2.setText("Player 2: " + player2Points);
    }

    private void updateMovesText()
    {
        textMovesMade.setText("Moves Made: " + roundCount);
        textMovesLeft.setText("Moves Left " + (9 - roundCount));
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
        updatePointsText();
        resetBoard();
    }
}