package com.example.herman.or_demo_2_withscoringandsubs.Info;

import java.util.ArrayList;

/**
 * Created by Herman on 2015-08-12.
 */
public class Team {
    private String teamName;
    protected ArrayList<Player> onField = new ArrayList<>(16);
    protected ArrayList<Player> reserves = new ArrayList<>(7);
    private int currentMatchScore;
    private int  teamTry = 0;
    private int  teamConKick = 0;
    private int  teamPenKick = 0;
    private int  teamDropKick = 0;
    private int teamTurnoversWon = 0;
    private int teamTurnoversLost = 0;
    private int teamRucksWon = 0;
    private int teamRucksLost = 0;
    private int ownTeamLineOutsWon = 0;
    private int opponentTeamLineOutsWon = 0;
    private int ownTeamLineOutsLost = 0;
    private int opponentTeamLineOutsLost = 0;
    private boolean lineOut = false;

    public Team(String tm)
    {
        this.teamName = tm;

        this.currentMatchScore = 0;

        for(int i = 0; i < 7; i++)
            reserves.add(new Player());
    }

    public Team()
    {
        this.teamName = "Unknown team name";

        this.currentMatchScore = 0;

        for(int i = 0; i < 7; i++)
            reserves.add(new Player());
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
        for (int i = 0; i < 15; i++)
        {
            this.onField.add(_onField.get(i));
        }
        onField.add(new Player("Unknown Player", 0, false));
    }

    public boolean addPlayer(Player p)
     {
         if(p.getReserve())
         {
             for (int i = 0; i < reserves.size(); i++)
             {
                 if (reserves.get(i).getJerseyNum() == p.getJerseyNum() && p.getJerseyNum() != 0)
                 {
                     return false;
                 }
             }
             /**if(reserves.size() < 7)
             {
                 reserves.add(p);
                 return true;
             }
             else
                 return false;**/
             for (int i = 0; i < reserves.size(); i++)
             {
                 if (reserves.get(i).getJerseyNum() == 0)
                 {
                     reserves.set(i, p);
                     return true;
                 }
             }
             return false;
         }
         else
         {
             for (int i = 0; i < onField.size(); i++)
             {
                 if (onField.get(i).getJerseyNum() == p.getJerseyNum() && p.getJerseyNum() != 0)
                 {
                     return false;
                 }
             }
             if(onField.size() < 15)
             {
                 onField.add(p);
                 if(onField.size() == 15)
                 {
                     onField.add(new Player("Unknown Player", 0, false));
                 }
                 return true;
             }
             else
                 return false;
         }
     }

    public void setReserves(ArrayList<Player> _reserves)
    {
        for (int i = 0; i < 7; i++)
        {
            if (i < _reserves.size())
                this.reserves.set(i, _reserves.get(i));
        }
    }

    public boolean addScore(String temp)
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
            default:
                return false;
        }
        return true;
    }

    public int getScore()
    {
        return this.currentMatchScore;
    }

    public boolean subPlayers(Player first, Player second)
    {
        int temp1 = onField.indexOf(first);
        int temp2 = reserves.indexOf(second);

        if(first != null && second != null && temp1 != -1 && temp2 != -1) {
            onField.set(onField.indexOf(first), second);
            reserves.set(reserves.indexOf(second), first);
            return true;
        }
        else
            return false;
    }

    public void incrementTurnoversWon()
    {
        teamTurnoversWon++;
    }

    public void incrementTurnoversLost()
    {teamTurnoversLost++;

    }

    public int getTurnoversWon()
    {
        return teamTurnoversWon;
    }

    public int getTurnoversLost() {
        return teamTurnoversLost;
    }

    public void incrementRucksWon()
    {
        teamRucksWon++;
    }

    public void incrementRucksLost()
    {teamRucksLost++;

    }

    public int getRucksWon()
    {
        return teamRucksWon;
    }

    public int getRucksLost() {
        return teamRucksLost;
    }

    public void incrementOwnLineOutsWon()
    {
        ownTeamLineOutsWon++;
    }

    public void incrementOwnLineOutsLost()
    {ownTeamLineOutsLost++;
    }

    public int getOwnLineOutsWon()
    {
        return ownTeamLineOutsWon;
    }

    public int getOwnLineOutsLost() {
        return ownTeamLineOutsLost;
    }

    public void incrementOpponentLineOutsWon()
    {
        opponentTeamLineOutsWon++;
    }

    public void incrementOpponentLineOutsLost()
    {opponentTeamLineOutsLost++;
    }

    public int getOpponentLineOutsWon()
    {
        return opponentTeamLineOutsWon;
    }

    public int getOpponentLineOutsLost() {
        return opponentTeamLineOutsLost;
    }

    public void setLineOut(boolean b)
    {
        lineOut = b;
    }

    public boolean getLineOut()
    {
        return lineOut;
    }

}
