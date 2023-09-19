package com.example.tictactoemad_20732435;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        items.add(new LeaderboardItem("John Wick",R.drawable.default_avatar, 4, 3, 6, 0,0));
        items.add(new LeaderboardItem("Caydn Lee",R.drawable.default_avatar, 4, 3, 6, 0,0));
        items.add(new LeaderboardItem("Brandon Hale",R.drawable.default_avatar, 4, 3, 6, 0,0));
        items.add(new LeaderboardItem("Justin Pan",R.drawable.default_avatar, 4, 3, 6, 0,0));


        Collections.sort(items, Collections.reverseOrder());
        return items;
    }
}