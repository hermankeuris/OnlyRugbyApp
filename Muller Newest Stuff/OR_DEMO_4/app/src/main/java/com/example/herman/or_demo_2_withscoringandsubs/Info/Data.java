package com.example.herman.or_demo_2_withscoringandsubs.Info;

import java.util.ArrayList;

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
    private static Boolean offlineMode = false;
    private static Boolean endOfFunction = false;

    //==========================================================================
    //                              NEW EVENT STUFF
    //==========================================================================
    private static ArrayList<Event> eventsList = new ArrayList();
    private static int eventIndex;

    private Data() {}

    public static synchronized Data getInstance() {
        if (instance == null) {
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

    public void setOfflineMode(Boolean b)
    {
        offlineMode = b;
    }

    public Boolean getOfflineMode()
    {
        return offlineMode;
    }

    public void setEndOfFunction(Boolean b)
    {
        endOfFunction = b;
    }

    public Boolean getEndOfFunction()
    {
        return endOfFunction;
    }

    //==========================================================================
    //                              NEW EVENT STUFF
    //==========================================================================

    public ArrayList getEvents(){return eventsList;}

    public void addToEvents(String eventType, String time ,Team teamOne, Team teamTwo, Player playerOne, Player platerTwo,String disciplineType)
    {
        Event temp = new Event();
        temp.setEventValues(eventType, time, teamOne, teamTwo, playerOne, platerTwo, disciplineType);
        eventsList.add(temp);
    }

    public void addTry(String time ,Team teamOne, Player playerOne)
    {
        Event temp = new Event();
        temp.addTry(time, teamOne, playerOne);
        eventsList.add(temp);
    }

    public void addTryConversion(String time ,Team teamOne, Player playerOne, Player playerTwo)
    {
        Event temp = new Event();
        temp.addTryConversion(time, teamOne, playerOne, playerTwo);
        eventsList.add(temp);
    }

    public void addPenaltyKick(String time ,Team teamOne, Player playerOne)
    {
        Event temp = new Event();
        temp.addPenaltyKick(time, teamOne, playerOne);
        eventsList.add(temp);
    }

    public void addDropKick(String time ,Team teamOne, Player playerOne)
    {
        Event temp = new Event();
        temp.addDropKick(time, teamOne, playerOne);
        eventsList.add(temp);
    }

    public void addRuck(String time ,Team teamOne, Team teamTwo)
    {
        Event temp = new Event();
        temp.addRuck(time, teamOne, teamTwo);
        eventsList.add(temp);
    }

    public void addLineOut(String time ,Team teamOne,Team teamTwo)
    {
        Event temp = new Event();
        temp.addLineOut(time, teamOne, teamTwo);
        eventsList.add(temp);
    }

    public void addDiscipline(String time ,Team teamOne,Player playerOne, String card)
    {
        Event temp = new Event();
        temp.addDiscipline(time, teamOne, playerOne, card);
        eventsList.add(temp);
    }

    public void addTurnover(String time ,Team teamOne,Team teamTwo)
    {
        Event temp = new Event();
        temp.addTurnover(time, teamOne, teamTwo);
        eventsList.add(temp);
    }

    public void setEventIndex(int i) {eventIndex = i;}

    public int getEventIndex() {return eventIndex;}

    public String getDescriptionAt(int index)
    {
        return eventsList.get(index).getDescription();
    }

    public void removeEventAtIndex(int index)
    {
        eventsList.remove(index);
    }

    public Event getEventAtIndex(int index)
    {
        return eventsList.get(index);
    }
}

