package com.example.tictactoemad_20732435;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class UserProfile extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_user_profile, container, false);

        ImageButton imageButton = rootView.findViewById(R.id.selectAvatar);
        MainActivityData mainActivityDataViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);
        GameData gameDataViewModel = new ViewModelProvider(requireActivity()).get(GameData.class);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mainActivityDataViewModel.setClickedValue(8);

            }
        });

        /*gameDataViewModel.player2Image.observe(this, new Observer<Drawable>() {
            @Override
            public void onChanged(Integer integer) {
                int player2Image = gameDataViewModel.getPlayer2ImageID();

                Log.d("Image ID avatar 2", "This is the ID of avatar 2" + player2Image);

                if(gameDataViewModel.getPlayer2ImageID() == player2Image) {
                    imageButton.setImageResource(R.drawable.avatar2);

                }
                if(gameDataViewModel.getPlayer2ImageID() == player2Image) {
                    imageButton.setImageResource(R.drawable.avatar2);

                }
            }
        });*/





        return rootView;
    }
}