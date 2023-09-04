package com.example.tictactoemad_20732435;

public class Item implements Comparable<Item>{

    String name;
    Integer wins;
    int losses;
    int draws;
    int totalgames;
    int image;
    float winpercent;

    public Item(String name, int image, Integer wins, int losses, int draws, int totalgames, int winpercent)
    {
        this.totalgames = totalgames;
        this.name = name;
        this.image = image;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
        this.winpercent = winpercent;
        // Needs to be calculated when updated values are passed
        calculatetotalgames();
        calculateWinPercentage();
}

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
        calculatetotalgames();
        calculateWinPercentage();
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {

        this.losses = losses;
        calculatetotalgames();
        calculateWinPercentage();
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
        calculatetotalgames();
        calculateWinPercentage();
    }

    public int getTotalgames() {
        return totalgames;
    }

    public float getWinpercent() {
        return winpercent;
    }
    public void calculatetotalgames(){
        totalgames = wins + losses + draws;
    }

    public void calculateWinPercentage(){
        if (totalgames > 0)
        {
            winpercent = Math.round((float)((double)wins/(double)totalgames * 100));
        }
        else
        {
            winpercent = 0;
        }
    }

    @Override
    public int compareTo(Item other) {
        return this.wins.compareTo(other.wins);
    }
}


