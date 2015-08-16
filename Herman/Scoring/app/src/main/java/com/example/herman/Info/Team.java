package com.example.herman.Info;

import com.example.herman.Info.Player;

import java.util.ArrayList;

/**
 * Created by Herman on 2015-08-12.
 */
public class Team {
    private String teamName;
    protected ArrayList<Player> onField = new ArrayList<>(15);
    protected ArrayList<Player> reserves = new ArrayList<>(7);
    private int currentMatchScore;
    private int  teamTry = 0;
    private int  teamConKick = 0;
    private int  teamPenKick = 0;
    private int  teamDropKick = 0;

    public Team(String tm)
    {
        this.teamName = tm;

        this.currentMatchScore = 0;
    }

    public void setTeamName(String tn)
    {
        this.teamName = tn;
    }

    public String getTeamName()
    {
        return this.teamName;
    }

    public ArrayList<Player> getOnField()
    {
        return this.onField;
    }

    public ArrayList<Player> getReserves()
    {
        return this.reserves;
    }

    public void setOnField(ArrayList<Player> _onField)
    {
        System.out.println("1setOnField: " + this.teamName + " first player " + _onField.get(0).getPlayerName());
        this.onField = _onField;
        System.out.println("2setOnField: " + this.teamName + " first player " + this.onField.get(0).getPlayerName());
    }

    public void setReserves(ArrayList<Player> _reserves)
    {
        this.reserves = _reserves;
    }

    public void addScore(String temp)
    {
        switch(temp)
        {
            case "Try":
                this.teamTry++;
                this.currentMatchScore += 5;
                break;
            case "Conversion Kick" :
                this.teamConKick++;
                this.currentMatchScore += 2;
                break;
            case "Penalty Kick":
                this.teamPenKick++;
                this.currentMatchScore += 3;
                break;
            case "Drop Kick":
                this.teamDropKick++;
                this.currentMatchScore += 3;
                break;
        }
    }

    public int getScore()
    {
        return this.currentMatchScore;
    }
}
