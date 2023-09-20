package com.example.tictactoemad_20732435;


import static android.app.PendingIntent.getActivity;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myViewHolder> {

    Context context;
    List<item> items;
    Activity activity;
    MainActivityData mainActivityData;


    private List<Integer> avatarOptions;
    
    public myAdapter(Context context, List<item> items, MainActivityData mainActivityData) {
        this.context = context;
        this.items = items;
        this.mainActivityData = mainActivityData;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_view, parent, false);
        myViewHolder myViewHolder = new myViewHolder(view, parent);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        int imageID = items.get(position).getImage();
        holder.imageButton.setImageResource(imageID);
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedAvatarResource = items.get(position).getImage();
                Bundle bundle = new Bundle();
                bundle.putInt("userPicture", selectedAvatarResource);

                UserProfile userProfile = new UserProfile();
                UserProfile2 userProfile2 = new UserProfile2();
                userProfile.setArguments(bundle);
                userProfile2.setArguments(bundle);

                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();

                // Start a new transaction
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                // Replace the existing fragment with the new one (replace R.id.frameLayoutId with your FrameLayout ID)
                if (mainActivityData.getPlayerEdit() == 1)
                {
                    transaction.replace(R.id.fragment_game, userProfile);
                }
                else
                {
                    transaction.replace(R.id.fragment_game, userProfile2);
                }

                // Commit the transaction
                transaction.commit();
                // Navigate to the UserProfileFragment
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
