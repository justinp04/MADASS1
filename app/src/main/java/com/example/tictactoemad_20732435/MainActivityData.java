package com.example.tictactoemad_20732435;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityData extends ViewModel {
    public MutableLiveData<Integer> clickedValue;
    public MutableLiveData<Integer> playerEdit;

    public MainActivityData() {
        clickedValue = new MediatorLiveData<Integer>();
        clickedValue.setValue(0);
        playerEdit = new MediatorLiveData<Integer>();
        playerEdit.setValue(0);
    }

    public int getClickedValue()
    {
        return clickedValue.getValue();
    }

    public void setClickedValue(int value)
    {
        clickedValue.setValue(value);
    }
    public int getPlayerEdit()
    {
        return playerEdit.getValue();
    }

    public void setPlayerEdit(int value)
    {
        playerEdit.setValue(value);
    }

}
