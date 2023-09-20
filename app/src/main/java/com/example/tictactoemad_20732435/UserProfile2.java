package com.example.tictactoemad_20732435;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class UserProfile2 extends Fragment {
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
        View rootView = inflater.inflate(R.layout.fragment_user_profile2, container, false);
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

        imageButton.setImageResource(gameDataViewModel.getPlayer2ImageID());
        enterName.setText(gameDataViewModel.getPlayer2Name());
        enterSymbol.setText(gameDataViewModel.getPlayer2Symbol());

        Bundle userProfile = getArguments();

        if(userProfile != null){

            avatar = userProfile.getInt("userPicture");
            gameDataViewModel.setPlayer2ImageID(avatar);
            p2Avatar = gameDataViewModel.getPlayer2ImageID();
            imageButton.setImageResource(p2Avatar);
            Log.d("P2", "Image ID " + p2Avatar + " name" + p2Name);
            userProfile.remove("userPicture");
        }

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gameDataViewModel.setPlayer2Name(enterName.getText().toString());
                gameDataViewModel.setPlayer2Symbol(enterSymbol.getText().toString());
                mainActivityDataViewModel.setClickedValue(8);

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gameDataViewModel.setPlayer2Name(enterName.getText().toString());
                gameDataViewModel.setPlayer2Symbol(enterSymbol.getText().toString());
                mainActivityDataViewModel.setClickedValue(4);

            }
        });

        return rootView;
    }
}