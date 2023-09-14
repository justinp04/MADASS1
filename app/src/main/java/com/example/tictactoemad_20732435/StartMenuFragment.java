package com.example.tictactoemad_20732435;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StartMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
/*Date created: 28/08/2023*/
public class StartMenuFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StartMenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StartMenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StartMenuFragment newInstance(String param1, String param2) {
        StartMenuFragment fragment = new StartMenuFragment();
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
                             Bundle savedInstanceState)
    {
        View rootView =inflater.inflate(R.layout.fragment_start_menu, container, false);
        // Inflate the layout for this fragment
        MainActivityData mainActivityDataViewModel = new ViewModelProvider(getActivity())
                .get(MainActivityData.class);
        GameData gameDataViewModel = new ViewModelProvider(getActivity()).get(GameData.class);

        Button twoPlayerGame = rootView.findViewById(R.id.button2P);
        Button AIGame = rootView.findViewById(R.id.buttonAI);
        Button settings = rootView.findViewById(R.id.settingButton);
        Button leaderboard = rootView.findViewById(R.id.leaderboardButton);

        twoPlayerGame.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                gameDataViewModel.setPlayerState(new HumanPlayerState());
                mainActivityDataViewModel.setClickedValue(1);
            }
        });

        AIGame.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                gameDataViewModel.setDefaultValues();
                gameDataViewModel.setPlayerState(new AIPlayerState());
                mainActivityDataViewModel.setClickedValue(1);
                //set clicked value for ai game mode
            }
        });

        settings.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mainActivityDataViewModel.setClickedValue(4);
            }
        });

        leaderboard.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //set clicked value for leaderboard
            }
        });

        return rootView;
    }
}