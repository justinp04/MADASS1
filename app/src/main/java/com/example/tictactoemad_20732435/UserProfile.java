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

    EditText enterName, enterSymbol;

    int avatar, p1Avatar, p2Avatar;

    String p1Name, p2Name, inputData;


    AvatarList avatarList;
    int userImage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_user_profile, container, false);
        MainActivityData mainActivityDataViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);
        GameData gameDataViewModel = new ViewModelProvider(requireActivity()).get(GameData.class);

        //Determine which player is being edited
        int playerEdit = mainActivityDataViewModel.getPlayerEdit();
        Log.d("PlayerEdit", "The player is number " + playerEdit);

        //initialise UI components
        imageButton = rootView.findViewById(R.id.selectAvatar);
        saveButton = rootView.findViewById(R.id.SaveUser);
        enterName = rootView.findViewById(R.id.Name);
        enterSymbol = rootView.findViewById(R.id.Symbol);

        imageButton.setImageResource(gameDataViewModel.getPlayer1ImageID());
        enterName.setText(gameDataViewModel.getPlayer1Name());
        enterSymbol.setText(gameDataViewModel.getPlayer1Symbol());

        Bundle userProfile = getArguments();


        if(userProfile != null){
            Log.d("Bundle not Null", "Should enter this bundle");
            avatar = userProfile.getInt("userPicture");
            gameDataViewModel.setPlayer1ImageID(avatar);
            p1Avatar = gameDataViewModel.getPlayer1ImageID();
            imageButton.setImageResource(p1Avatar);
            Log.d("P1", "Image ID " + p1Avatar + " name" + p1Name);
            userProfile.remove("userPicture");
        }

        /*if(userProfile != null && playerEdit == 2){

            avatar = userProfile.getInt("userPicture");
            gameDataViewModel.setPlayer2ImageID(avatar);
            gameDataViewModel.setPlayer2Name(inputData);
            p2Avatar = gameDataViewModel.getPlayer2ImageID();
            imageButton.setImageResource(p2Avatar);
            Log.d("P2", "Image ID " + p2Avatar + " name" + p2Name);
        }*/


        /*if (playerEdit == 1)
        {
            Log.d("This is the first If", "PlayerEdit should be 1 = " + playerEdit);
            gameDataViewModel.setPlayer1ImageID(p1Avatar);
            gameDataViewModel.setPlayer1Name(p1Name);
        }
        else if (playerEdit == 2)
        {
            Log.d("This is the second If", "PlayerEdit should be 2 = " + playerEdit);
            gameDataViewModel.setPlayer2ImageID(p2Avatar);
            gameDataViewModel.setPlayer2Name(p2Name);
        }


        if (playerEdit == 1)
        {
            imageButton.setImageResource(p1Avatar);
            enterName.setText(p1Name);
        }
        else if (playerEdit == 2)
        {
            imageButton.setImageResource(p2Avatar);
            enterName.setText(p2Name);
        }*/

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gameDataViewModel.setPlayer1Name(enterName.getText().toString());
                gameDataViewModel.setPlayer1Symbol(enterSymbol.getText().toString());
                mainActivityDataViewModel.setClickedValue(8);

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gameDataViewModel.setPlayer1Name(enterName.getText().toString());
                gameDataViewModel.setPlayer1Symbol(enterSymbol.getText().toString());
                mainActivityDataViewModel.setClickedValue(4);

            }
        });

        return rootView;
    }
}