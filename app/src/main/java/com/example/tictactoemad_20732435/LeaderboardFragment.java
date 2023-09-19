package com.example.tictactoemad_20732435;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeaderboardFragment extends Fragment {

    private List<LeaderboardItem> list;
    private RecyclerView recyclerView;
    private LeaderboardAdapter myAdapter;

    private Button backButton;
    public LeaderboardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_leaderboard_fragment, container, false);
        MainActivityData mainActivityDataViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);
        backButton = rootView.findViewById(R.id.back);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setClickedValue(0);
            }
        });
        list = createLeaderboard();

        recyclerView = rootView.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new LeaderboardAdapter(getContext(),list));
        return rootView;


    }

    private List<LeaderboardItem> createLeaderboard(){
        List<LeaderboardItem> items = new ArrayList<LeaderboardItem>();

        Bundle bundle = new Bundle();
        GameData gameDataViewModel = new ViewModelProvider(getActivity()).get(GameData.class);
        bundle = gameDataViewModel.getGameStatsBundle();

        int player1Wins = bundle.getInt("P1Wins");
        int player2Wins = bundle.getInt("P2Wins");
        int totalDraws = bundle.getInt("Draws");
        int totalGames = bundle.getInt("TotalGames");
        int player1Avatar = bundle.getInt("P1Avatar");
        int player2Avatar = bundle.getInt("P2Avatar");
        String player1Name = bundle.getString("P1Name");
        String player2Name = bundle.getString("P2Name");

        int player1loses = (totalDraws + player1Wins) - totalGames;
        int player2loses = (totalDraws + player2Wins) - totalGames;
        items.add(new LeaderboardItem(player1Name,player1Avatar, player1Wins, player1loses, totalDraws, totalGames,0));
        items.add(new LeaderboardItem(player2Name,player2Avatar, player2Wins, player2loses, totalDraws, totalGames,0));

        Collections.sort(items, Collections.reverseOrder());
        return items;
    }
}