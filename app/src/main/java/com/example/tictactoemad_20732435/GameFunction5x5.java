package com.example.tictactoemad_20732435;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameFunction5x5#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameFunction5x5 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    int row = 5, col = 5;
    private Button[][] gameButtons = new Button[row][col];
    private boolean player1Turn = true;
    private int roundCount;
    private int player1Points;
    private int player2Points;
    private int winCondition = 5;

    private Integer timerCounter;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    private TextView textMovesMade;
    private TextView textMovesLeft;
    private TextView textTimer;
    private CountDownTimer turnTimer;

    public GameFunction5x5() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameFunction5x5.
     */
    // TODO: Rename and change types and number of parameters
    public static GameFunction5x5 newInstance(String param1, String param2) {
        GameFunction5x5 fragment = new GameFunction5x5();
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
        View rootView = inflater.inflate(R.layout.fragment_game_function5x5, container, false);
        MainActivityData mainActivityDataViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);
        GameData gameDataViewModel = new ViewModelProvider(getActivity()).get(GameData.class);

        textViewPlayer1 = rootView.findViewById(R.id.player1_score);
        textViewPlayer2 = rootView.findViewById(R.id.player2_score);
        textMovesMade = rootView.findViewById(R.id.movesMade);
        textMovesLeft = rootView.findViewById(R.id.movesLeft);
        textTimer = rootView.findViewById(R.id.timer);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", requireContext().getPackageName());
                gameButtons[i][j] = rootView.findViewById(resID);
                gameButtons[i][j].setOnClickListener(this::onClick);

            }
        }

        gameDataViewModel.setBoardSize(5);
        gameDataViewModel.setGameButtons(gameButtons);

        Button resetButton = rootView.findViewById(R.id.reset_button);
        Button settingsButton = rootView.findViewById(R.id.settings_button);
        Button menuButton = rootView.findViewById(R.id.menu_button);
        Button pauseButton = rootView.findViewById(R.id.pause_button);

        //Initialise the CountDownTime Functions
        timerCounter = 30;
        turnTimer = new CountDownTimer(30000, 1000){
            @Override
            public void onTick(long l) {
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
            public void onClick(View view) {
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

        return rootView;
    }

    public void onClick(View view) {
        GameData gameDataViewModel = new ViewModelProvider(getActivity()).get(GameData.class);
        String returnString = GameFunctions.onClick(view, gameDataViewModel);
        if (returnString != null) {
            Toast.makeText(requireContext(), returnString, Toast.LENGTH_SHORT).show();
        }
        updatePlayerText(gameDataViewModel);
    }

    private void updatePlayerText(GameData gameDataViewModel)
    {
        turnTimer.cancel();
        textViewPlayer1.setText("Player 1: " + gameDataViewModel.getPlayer1Points());
        textViewPlayer2.setText("Player 2: " + gameDataViewModel.getPlayer2Points());
        textMovesLeft.setText("Moves Left: " + (9 - gameDataViewModel.getRoundCount()));
        textMovesMade.setText("Moves Made: " + gameDataViewModel.getRoundCount());
        timerCounter = 30;
        turnTimer.start();
    }
}