package com.example.herman.Info;

import com.example.herman.Info.Data;

/**
 * Created by Herman on 2015-08-12.
 */
public class Player {
    //The player's name
    private String playerName;
    //The player's jersey number
    private int jerseyNum;
    //The number of times an injury forced the player off the field during match-time
    static private int matchTimeInjuries;
    //A variable to store if the player is currently being penalised
    static private Data.DisciplineType status;
    //A variable to indicate if the player is currently a reserve player (true) or an on-field player (fasle).
    private boolean reservePlayer;

    static private int  playerTry = 0;
    static private int  playerConKick = 0;
    static private int  playerPenKick = 0;
    static private int  playerDropKick = 0;

    //Constructor with parameters
    public Player(String pn, int jn, boolean field)
    {
        playerName = pn;
        jerseyNum = jn;
        matchTimeInjuries = 0;
        status = Data.DisciplineType.None;
        reservePlayer = field;
    }

    //Constructor without parameters (i.e. default constructor)
    public Player()
    {
        playerName = "Unknown";
        jerseyNum = 99;
        matchTimeInjuries = 0;
        status = Data.DisciplineType.None;
        reservePlayer = false;
    }

    //A function which changes the player's name
    public void setPlayerName(String pn)
    {
        playerName = pn;
    }

    //A function which returns the player's name
    public String getPlayerName()
    {
        return playerName;
    }

    //A function which changes the player's jersey number
    public void setJerseyNum(int jn)
    {
        jerseyNum = jn;
    }

    //A function which returns the player's jersey number
    public int getJerseyNum()
    {
        return jerseyNum;
    }

    //A function which changes the player's current penalty status
    public void setStatus(Data.DisciplineType s)
    {
        status = s;
    }

    //A function which returns the player's current penalty status
    public Data.DisciplineType getStatus()
    {
        return status;
    }

    //A function which changes whether a player is on field or not
    public void setReserve(boolean of)
    {
        reservePlayer = of;
    }

    //A function which returns whether the player is on field or not
    public boolean getReserve()
    {
        return reservePlayer;
    }

    public void playerScore(String temp)
    {
        switch(temp)
        {
            case "Try":
                playerTry++;
                break;
            case "Conversion Kick" :
                playerConKick++;
                break;
            case "Penalty Kick":
                playerPenKick++;
                break;
            case "Drop Kick":
                playerDropKick++;
                break;
        }
    }
}
