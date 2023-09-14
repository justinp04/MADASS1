package com.example.tictactoemad_20732435;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Settings#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Settings extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int winCondition = 4;

    public Settings() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Settings.
     */
    // TODO: Rename and change types and number of parameters
    public static Settings newInstance(String param1, String param2) {
        Settings fragment = new Settings();
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
                             Bundle savedInstanceStatse) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        MainActivityData mainActivityDataViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);
        GameData gameDataViewModel = new ViewModelProvider(getActivity()).get(GameData.class);

        Button gameMode3x3 = rootView.findViewById(R.id.gamemode3x3);
        Button gameMode4x4 = rootView.findViewById(R.id.gamemode4x4);
        Button gameMode5x5 = rootView.findViewById(R.id.gamemode5x5);
        Button winCondition3 = rootView.findViewById(R.id.inARow3);
        Button winCondition4 = rootView.findViewById(R.id.inARow4);
        Button winCondition5 = rootView.findViewById(R.id.inARow5);
        Button iconCrosses = rootView.findViewById(R.id.icon_crosses);
        Button iconCircles = rootView.findViewById(R.id.icon_circles);
        ViewGroup constraintLayout = rootView.findViewById(R.id.fragment_settings);

        int boardsize = gameDataViewModel.getBoardSize(); 

        if (boardsize == 3)
        {
            setUpButtons3x3(winCondition4, winCondition5);
        }
        else if (boardsize == 4)
        {
            setUpButtons4x4(winCondition4, winCondition5);
        }
        else if (boardsize == 5)
        {
            setUpButtons5x5(winCondition4, winCondition5);
        }

        gameMode3x3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameDataViewModel.setBoardSize(3);
                gameDataViewModel.setWinCondition(3);
                setUpButtons3x3(winCondition4, winCondition5);
            }
        });
        gameMode4x4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameDataViewModel.setBoardSize(4);
                if (gameDataViewModel.getWinCondition() == 5)
                {
                    gameDataViewModel.setWinCondition(4);
                }
                setUpButtons4x4(winCondition4, winCondition5);
            }
        });
        gameMode5x5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameDataViewModel.setBoardSize(5);
                setUpButtons5x5(winCondition4, winCondition5);
            }
        });
        winCondition3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameDataViewModel.setWinCondition(3);
                Toast.makeText(requireContext(), "3 In A Row", Toast.LENGTH_SHORT).show();
            }
        });
        winCondition4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameDataViewModel.setWinCondition(4);
                Toast.makeText(requireContext(), "4 In A Row", Toast.LENGTH_SHORT).show();
            }
        });
        winCondition5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameDataViewModel.setWinCondition(5);
                Toast.makeText(requireContext(), "5 In A Row", Toast.LENGTH_SHORT).show();
            }
        });
        iconCrosses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(requireContext(), "You are now Crosses", Toast.LENGTH_SHORT).show();
            }
        });
        iconCircles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(requireContext(), "You are now Circles", Toast.LENGTH_SHORT).show();
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }

    //Functions to disable/enable buttons based on board size

    private void setUpButtons3x3(Button button4x4, Button button5x5)
    {
        button4x4.setEnabled(false);
        button5x5.setEnabled(false);
    }

    private void setUpButtons4x4(Button button4x4, Button button5x5)
    {
        button4x4.setEnabled(true);
        button5x5.setEnabled(false);
    }

    private void setUpButtons5x5(Button button4x4, Button button5x5)
    {
        button4x4.setEnabled(true);
        button5x5.setEnabled(true);
    }

    public void updateWinCondition(int winCondition)
    {
        this.winCondition = winCondition;
    }


}