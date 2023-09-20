package com.example.tictactoemad_20732435;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class AvatarList extends Fragment {

    private List<item> list;

    private List<Integer> avatarOptions = new ArrayList<>();
    private RecyclerView recyclerView;

    private ImageButton imageButton;
    int spanCount = 3;
    public AvatarList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_avatarlist, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerview);
        list = createAvatarList();
        avatarOptions = createResourceIdList();

        //myAdapter adapter = new myAdapter(avatarOptions);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), spanCount);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(new myAdapter(getContext(),list, new ViewModelProvider(getActivity()).get(MainActivityData.class)));


        return rootView;

    }

    private List<item> createAvatarList(){
        List<item> items = new ArrayList<item>();

        items.add(new item(1,R.drawable.avatar1));
        items.add(new item(2,R.drawable.avatar2));
        items.add(new item(3,R.drawable.avatar3));
        items.add(new item(4,R.drawable.avatar4));
        items.add(new item(5,R.drawable.avatar5));
        items.add(new item(6,R.drawable.avatar6));
        items.add(new item(7, R.drawable.avatar7));
        items.add(new item(8,R.drawable.avatar8));
        items.add(new item(9,R.drawable.avatar9));
        items.add(new item(10,R.drawable.avatar10));
        items.add(new item(11,R.drawable.avatar11));
        items.add(new item(12,R.drawable.avatar12));
        items.add(new item(13,R.drawable.avatar13));
        items.add(new item(14,R.drawable.avatar14));
        items.add(new item(15,R.drawable.avatar15));

        return items;
    }

    private List<Integer> createResourceIdList(){
        List<Integer> id = new ArrayList<Integer>();

        id.add(R.drawable.avatar1);
        id.add(R.drawable.avatar2);
        id.add(R.drawable.avatar3);
        id.add(R.drawable.avatar4);
        id.add(R.drawable.avatar5);
        id.add(R.drawable.avatar6);
        id.add(R.drawable.avatar7);
        id.add(R.drawable.avatar8);
        id.add(R.drawable.avatar9);
        id.add(R.drawable.avatar10);
        id.add(R.drawable.avatar11);
        id.add(R.drawable.avatar12);
        id.add(R.drawable.avatar13);
        id.add(R.drawable.avatar14);
        id.add(R.drawable.avatar15);

        return id;
    }
}