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
        Bundle bundle = getArguments();
        if(bundle != null){
            userImage = bundle.getInt("userPicture");
            gameDataViewModel.setPlayer1ImageID(userImage);
        }

        imageButton = rootView.findViewById(R.id.selectAvatar);
        saveButton = rootView.findViewById(R.id.SaveUser);
        enterName = rootView.findViewById(R.id.Name);
        imageButton.setImageResource(gameDataViewModel.getPlayer1ImageID());

        enterName.setText(gameDataViewModel.getPlayer1Name());

        Bundle userProfile = new Bundle();
        userProfile.putInt("UserImage", userImage);
        //userProfile.putString("UserName", enterName);



        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mainActivityDataViewModel.setClickedValue(8);

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameDataViewModel.setPlayer1Name(enterName.getText().toString());
                mainActivityDataViewModel.setClickedValue(4);
            }
        });

            return rootView;
    }

}