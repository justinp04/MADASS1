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

    private List<Item> list;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;

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
                //mainActivityDataViewModel.setClickedValue(//Set this to the value of the main menu fragment);
            }
        });
        list = createLeaderboard();

        recyclerView = rootView.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new MyAdapter(getContext(),list));
        return rootView;


    }

    private List<Item> createLeaderboard(){
        List<Item> items = new ArrayList<Item>();

        items.add(new Item("John Wick",R.drawable.pngitem_2076277, 4, 3, 6, 0,0));
        items.add(new Item("John Wick",R.drawable.pngitem_2076277, 4, 3, 6, 0,0));
        items.add(new Item("John Taylor",R.drawable.pngitem_2076277, 5, 8, 5, 0,0));
        items.add(new Item("Loser hat",R.drawable.pngitem_2076277, 3, 2, 4, 0,0));
        items.add(new Item("John Wick",R.drawable.pngitem_2076277, 8, 1, 3, 0,0));
        items.add(new Item("John Wick",R.drawable.pngitem_2076277, 6, 3, 6, 0,0));
        items.add(new Item("John Taylor",R.drawable.pngitem_2076277, 9, 8, 5, 0,0));
        items.add(new Item("Loser hat",R.drawable.pngitem_2076277, 11, 2, 4, 0,0));
        items.add(new Item("Loser hat",R.drawable.pngitem_2076277, 11, 2, 4, 0,0));
        items.add(new Item("John Wick",R.drawable.pngitem_2076277, 20, 1, 3, 0,0));
        items.add(new Item("John Wick",R.drawable.pngitem_2076277, 4, 3, 6, 0,0));
        items.add(new Item("John Taylor",R.drawable.pngitem_2076277, 5, 8, 5, 0,0));
        items.add(new Item("Loser hat",R.drawable.pngitem_2076277, 3, 2, 4, 0,0));
        items.add(new Item("John Wick",R.drawable.pngitem_2076277, 8, 1, 3, 0,0));
        items.add(new Item("John Wick",R.drawable.pngitem_2076277, 6, 3, 6, 0,0));
        items.add(new Item("John Taylor",R.drawable.pngitem_2076277, 9, 8, 5, 0,0));
        items.add(new Item("Loser hat",R.drawable.pngitem_2076277, 11, 2, 4, 0,0));
        items.add(new Item("John Wick",R.drawable.pngitem_2076277, 20, 1, 3, 0,0));
        items.add(new Item("Loser hat",R.drawable.pngitem_2076277, 11, 2, 4, 0,0));
        items.add(new Item("John Wick",R.drawable.pngitem_2076277, 20, 1, 3, 0,0));
        items.add(new Item("John Wick",R.drawable.pngitem_2076277, 4, 3, 6, 0,0));
        items.add(new Item("John Taylor",R.drawable.pngitem_2076277, 5, 8, 5, 0,0));
        items.add(new Item("Loser hat",R.drawable.pngitem_2076277, 3, 2, 4, 0,0));
        items.add(new Item("John Wick",R.drawable.pngitem_2076277, 8, 1, 3, 0,0));
        items.add(new Item("John Wick",R.drawable.pngitem_2076277, 6, 3, 6, 0,0));
        items.add(new Item("John Taylor",R.drawable.pngitem_2076277, 9, 8, 5, 0,0));
        items.add(new Item("Loser hat",R.drawable.pngitem_2076277, 11, 2, 4, 0,0));
        items.add(new Item("John Wick",R.drawable.pngitem_2076277, 20, 1, 3, 0,0));



        Collections.sort(items, Collections.reverseOrder());
        return items;
    }
}