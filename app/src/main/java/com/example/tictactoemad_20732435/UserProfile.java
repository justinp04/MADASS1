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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class UserProfile extends Fragment {
    ImageButton imageButton;

    Button saveButton;

    EditText enterName;

    AvatarList avatarList;
    int userImage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_user_profile, container, false);
        MainActivityData mainActivityDataViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);
        GameData gameDataViewModel = new ViewModelProvider(requireActivity()).get(GameData.class);

        int playerEdit = mainActivityDataViewModel.getPlayerEdit();

        Bundle bundle = getArguments();
        if(bundle != null){
            userImage = bundle.getInt("userPicture");
            if (playerEdit == 1)
            {
                gameDataViewModel.setPlayer1ImageID(userImage);
            }
            else if (playerEdit == 2)
            {
                gameDataViewModel.setPlayer2ImageID(userImage);
            }
        }

        imageButton = rootView.findViewById(R.id.selectAvatar);
        saveButton = rootView.findViewById(R.id.SaveUser);
        enterName = rootView.findViewById(R.id.Name);
        if (playerEdit == 1)
        {
            imageButton.setImageResource(gameDataViewModel.getPlayer1ImageID());
            enterName.setText(gameDataViewModel.getPlayer1Name());
        }
        else if (playerEdit == 2)
        {
            imageButton.setImageResource(gameDataViewModel.getPlayer2ImageID());
            enterName.setText(gameDataViewModel.getPlayer2Name());
        }

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setPlayerName(gameDataViewModel, playerEdit, enterName.getText().toString());
                mainActivityDataViewModel.setClickedValue(8);

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setPlayerName(gameDataViewModel, playerEdit, enterName.getText().toString());
                mainActivityDataViewModel.setClickedValue(4);

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

    private void setPlayerName(GameData gameData, int playerEdit, String playerName)
    {
        if (playerEdit == 1)
        {
            gameData.setPlayer1Name(playerName);
        } else if (playerEdit == 2) {
            gameData.setPlayer2Name(playerName);

        }
    }
}