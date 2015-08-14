package com.example.herman.Info;

import com.example.herman.Info.Player;

import java.util.ArrayList;

/**
 * Created by Herman on 2015-08-12.
 */
public class Team {
    private String teamName;
    static protected ArrayList<Player> onField = new ArrayList<>(15);
    static protected ArrayList<Player> reserves = new ArrayList<>(7);
    static private int currentMatchScore;
    static private int  teamTry = 0;
    static private int  teamConKick = 0;
    static private int  teamPenKick = 0;
    static private int  teamDropKick = 0;

    public Team(String tm)
    {
        teamName = tm;

        currentMatchScore = 0;
    }

    public void setTeamName(String tn)
    {
        teamName = tn;
    }

    public String getTeamName()
    {
        return teamName;
    }

    public ArrayList<Player> getOnField()
    {
        return onField;
    }

    public ArrayList<Player> getReserves()
    {
        return reserves;
    }

    public void setOnField(ArrayList<Player> _onFiled)
    {
        this.onField = _onFiled;
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
                teamTry++;
                currentMatchScore += 5;
                break;
            case "Conversion Kick" :
                teamConKick++;
                currentMatchScore += 2;
                break;
            case "Penalty Kick":
                teamPenKick++;
                currentMatchScore += 3;
                break;
            case "Drop Kick":
                teamDropKick++;
                currentMatchScore += 3;
                break;
        }
    }

    public int getScore()
    {
        return currentMatchScore;
    }
}
