package com.example.herman.Info;

import android.app.Application;

/**
 * Created by Herman on 2015-08-14.
 */
public class MyApp  extends Application {

    private static Team selectedTeam = null;
    private static Team teamOne = null;
    private static Team teamTwo = null;
    private static String selectedScoreType = null;
    private static Player selectedPlayer = null;

    public void setSelectedTeam(Team t)
    {
        selectedTeam = t;
    }

    public Team getSelectedTeam()
    {
        return selectedTeam;
    }

    public void setTeamOne(Team t)
    {
        teamOne = t;
    }

    public Team getTeamOne()
    {
        return teamOne;
    }

    public void setTeamTwo(Team t)
    {
        teamTwo = t;
    }

    public Team getTeamTwo()
    {
        return teamTwo;
    }

    public void setSelectedScoreType(String s)
    {
        selectedScoreType = s;
    }

    public String getSelectedScoreType()
    {
        return selectedScoreType;
    }

    public void setSelectedPlayer(Player p)
    {
        selectedPlayer = p;
    }

    public Player getSelectedPlayer()
    {
        return selectedPlayer;
    }
}
