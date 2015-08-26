package com.example.herman.or_demo_2_withscoringandsubs.Info;

/**
 * Created by Herman on 2015-08-12.
 */
public class Data {

    private static Data instance = null;
    private static String selectedTeam = null;
    private static Team teamOne = null;
    private static Team teamTwo = null;
    private static String selectedScoreType = null;
    private static Player selectedPlayer = null;
    private static Boolean onFieldTeam = true;
    private static String functionType = null;
    private static Boolean setLineOutTeam = false;

    private Data() {}

    public static synchronized Data getInstance() {
        if (instance == null) {
            System.out.println("*******Should only happen once!*******");
            instance = new Data();
        }
        return instance;
    }

    public void setSelectedTeam(String s)
    {
        selectedTeam = s;
    }

    public Team getSelectedTeam()
    {
        if (selectedTeam == "teamOne")
            return teamOne;
        else if (selectedTeam == "teamTwo")
            return teamTwo;
        else
            return null;
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

    public void setOnFieldPlayers(Boolean b)
    {
        onFieldTeam = b;
    }

    public Boolean getOnFieldPlayers()
    {
        return onFieldTeam;
    }

    public void setSelectedPlayer(Player p)
    {
        selectedPlayer = p;
    }

    public Player getSelectedPlayer()
    {
        return selectedPlayer;
    }

    public void setFunctionType(String s)
    {
        functionType = s;
    }

    public String getFunctionType()
    {
        return functionType;
    }

    public void setLineOutTeam(Boolean b)
    {
        setLineOutTeam = b;
    }

    public Boolean getLineOutTeam()
    {
        return setLineOutTeam;
    }
}

