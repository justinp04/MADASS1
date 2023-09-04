package com.example.tictactoemad_20732435;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private  StartMenuFragment startMenuFragment = new StartMenuFragment();
    private GameFunction3x3 gameFunction3x3 = new GameFunction3x3();
    private GameFunction4x4 gameFunction4x4 = new GameFunction4x4();
    private GameFunction5x5 gameFunction5x5 = new GameFunction5x5();
    private Settings settings = new Settings();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadMenuFragment();

        MainActivityData mainActivityDataViewModel = new ViewModelProvider(this).get(MainActivityData.class);
        GameData gameDataViewModel = new ViewModelProvider(this).get(GameData.class);
        mainActivityDataViewModel.clickedValue.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (mainActivityDataViewModel.getClickedValue() == 0)
                {
                    loadMenuFragment();
                }
                if (mainActivityDataViewModel.getClickedValue() == 1)
                {
                    loadGame3x3Fragment();
                }
                if (mainActivityDataViewModel.getClickedValue() == 2)
                {
                    loadGame4x4Fragment();
                }
                if (mainActivityDataViewModel.getClickedValue() == 3)
                {
                    loadGame5x5Fragment();
                }
                if (mainActivityDataViewModel.getClickedValue() == 4)
                {
                    loadSettingsFragment();
                }
            }
        });
    }

    private void loadMenuFragment()
    {
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.fragment_game);
        if (frag==null)
        {
            fm.beginTransaction().add(R.id.fragment_game, startMenuFragment).commit();
        }
        else {
            fm.beginTransaction().replace(R.id.fragment_game,startMenuFragment).commit();
        }
    }
    private void loadGame3x3Fragment()
    {
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.fragment_game);
        if (frag==null)
        {
            fm.beginTransaction().add(R.id.fragment_game,gameFunction3x3).commit();
        }
        else {
            fm.beginTransaction().replace(R.id.fragment_game,gameFunction3x3).commit();
        }
    }

    private void loadGame4x4Fragment()
    {
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.fragment_game);
        if (frag==null)
        {
            fm.beginTransaction().add(R.id.fragment_game,gameFunction4x4).commit();
        }
        else {
            fm.beginTransaction().replace(R.id.fragment_game,gameFunction4x4).commit();
        }
    }

    private void loadGame5x5Fragment()
    {
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.fragment_game);
        if (frag==null)
        {
            fm.beginTransaction().add(R.id.fragment_game,gameFunction5x5).commit();
        }
        else {
            fm.beginTransaction().replace(R.id.fragment_game,gameFunction5x5).commit();
        }
    }

    private void loadSettingsFragment()
    {
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.fragment_game);
        if (frag==null)
        {
            fm.beginTransaction().add(R.id.fragment_game, settings).commit();
        }
        else {
            fm.beginTransaction().replace(R.id.fragment_game, settings).commit();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}