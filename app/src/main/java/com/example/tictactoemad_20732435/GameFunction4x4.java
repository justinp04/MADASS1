package com.example.tictactoemad_20732435;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameFunction4x4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameFunction4x4 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    int row = 4, col = 4;
    private Button[][] gameButtons = new Button[row][col];
    Settings settings;
    private boolean player1Turn = true;
    private int roundCount;
    private int player1Points;
    private int player2Points;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    private TextView textMovesMade;
    private TextView textMovesLeft;

    public GameFunction4x4() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameFunction4x4.
     */
    // TODO: Rename and change types and number of parameters
    public static GameFunction4x4 newInstance(String param1, String param2) {
        GameFunction4x4 fragment = new GameFunction4x4();
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
        View rootView = inflater.inflate(R.layout.fragment_game_function4x4, container, false);
        MainActivityData mainActivityDataViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);
        GameData gameDataViewModel = new ViewModelProvider(getActivity()).get(GameData.class);
        textViewPlayer1 = rootView.findViewById(R.id.player1_score);
        textViewPlayer2 = rootView.findViewById(R.id.player2_score);
        textMovesLeft = rootView.findViewById(R.id.movesLeft);
        textMovesMade = rootView.findViewById(R.id.movesMade);

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
        gameDataViewModel.setBoardSize(4);
        gameDataViewModel.setGameButtons(gameButtons);

        Button resetButton = rootView.findViewById(R.id.reset_button);
        Button settingsButton = rootView.findViewById(R.id.settings_button);
        Button menuButton = rootView.findViewById(R.id.menu_button);
        Button pauseButton = rootView.findViewById(R.id.pause_button);
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
                GameFunctions.resetGame(gameDataViewModel);
                updatePlayerText();
            }
        });
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setClickedValue(5);
            }
        });

        gameDataViewModel.aiFinished.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (gameDataViewModel.getAiFinished())
                {
                    //Before enabling buttons check to see if ai has won.
                    winMessage(GameFunctions.checkPlayer2Wins(gameDataViewModel));
                    for(int i = 0; i < gameButtons.length; i++)
                    {
                        for (int j = 0; j < gameButtons[i].length; j++) {
                            gameButtons[i][j].setEnabled(true);
                        }
                    }
                } else if (!gameDataViewModel.getAiFinished()) {
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

    public void onClick(View view)
    {
        GameData gameDataViewModel = new ViewModelProvider(getActivity()).get(GameData.class);
        String returnString = GameFunctions.onClick(view, gameDataViewModel);
        if (returnString != null)
        {
            Toast.makeText(requireContext(), returnString, Toast.LENGTH_SHORT).show();
        }
        updatePlayerText();
    }

    private void updatePlayerText()
    {
        GameData gameDataViewModel = new ViewModelProvider(getActivity()).get(GameData.class);
        textViewPlayer1.setText("Player 1: " + gameDataViewModel.getPlayer1Points());
        textViewPlayer2.setText("Player 2: " + gameDataViewModel.getPlayer2Points());
        textMovesLeft.setText("Moves Left: " + (16 - gameDataViewModel.getRoundCount()));
        textMovesMade.setText("Moves Made: " + gameDataViewModel.getRoundCount());
    }

    private void winMessage(String inString)
    {
        //Update displayed stats
        updatePlayerText();
        if (inString != null) {
            Toast.makeText(requireContext(), inString, Toast.LENGTH_SHORT).show();
        }
    }
}