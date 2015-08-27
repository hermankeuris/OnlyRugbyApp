package com.example.herman.or_demo_2_withscoringandsubs.Info;

/**
 * Created by Herman on 2015-08-12.
 */
public class Player {
    //The player's name
    private String playerName;
    //The player's jersey number
    private int jerseyNum;
    //The number of times an injury forced the player off the field during match-time
    private int matchTimeInjuries;
    //A variable to store if the player is currently being penalised
    //static private Data.DisciplineType status;
    //A variable to indicate if the player is currently a reserve player (true) or an on-field player (fasle).
    private boolean reservePlayer;

    private int index;
    static private int count = 0;

    private int  playerTry = 0;
    private int  playerConKick = 0;
    private int  playerPenKick = 0;
    private int  playerDropKick = 0;

    //The number of times a player has received a certain card
    private int numWhiteCards = 0;
    private int numYellowCards = 0;
    private int numRedCards = 0;

    private boolean redCard = false;
    private boolean yellowCard = false;

    //Constructor with parameters
    public Player(String pn, int jn, boolean field)
    {
        index = count;
        count++;

        if(pn == null || pn == "")
            playerName = "Unknown(" + index + ")";
        else
            playerName = pn;

        if(jn < 0)
            jerseyNum = 0;
        else
            jerseyNum = jn;

        matchTimeInjuries = 0;
        //status = Data.DisciplineType.None;

        reservePlayer = field;
    }

    //Constructor without parameters (i.e. default constructor)
    public Player()
    {
        index = count;
        count++;

        playerName = "Unknown(" + index + ")";
        jerseyNum = 0;
        matchTimeInjuries = 0;
        //status = Data.DisciplineType.None;
        reservePlayer = false;
    }

    //A function which changes the player's name
    public void setPlayerName(String pn)
    {
        if(pn == null || pn == "")
            playerName = "Unknown(" + index + ")";
        else
            playerName = pn;
    }

    //A function which returns the player's name
    public String getPlayerName()
    {
        return playerName;
    }

    //A function which changes the player's jersey number
    public boolean setJerseyNum(int jn) {
        if (jn > 0) {
            jerseyNum = jn;
            return true;
        }
        return false;
    }

    //A function which returns the player's jersey number
    public int getJerseyNum()
    {
        return jerseyNum;
    }

    //A function which changes the player's current penalty status
    /*public void setStatus(Data.DisciplineType s)
    {
        status = s;
    }*/

    //A function which returns the player's current penalty status
    /*public Data.DisciplineType getStatus()
    {
        return status;
    }*/

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

    public boolean playerScore(String temp)
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
            default:
                return false;
        }
        return true;
    }

    public int[] getPlayerScore()
    {
        int[] answer = {playerTry, playerConKick, playerPenKick, playerDropKick};
        return answer;
    }

    public void resetCount()
    {
        count = 0;
    }

    public void giveWhiteCard(){numWhiteCards++;}
    public void giveYellowCard(){numYellowCards++;
    yellowCard = true;}
    public void giveRedCard(){numRedCards++;
    redCard = true;
    yellowCard = false;}

    public int getWhiteCardCount(){return numWhiteCards;}
    public int getYellowCardCount(){return numYellowCards;}
    public int getRedCardCount(){return numRedCards;}

    public boolean getRedCard(){return redCard;}
    public boolean getYellowCard(){return yellowCard;}
}
