package com.example.herman.or_demo_2_withscoringandsubs.Info;

import android.content.Intent;

import com.example.android.onlyrugbyDemo2.R;

import java.util.ArrayList;
import java.util.Calendar;

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
    private static int eventsListSize;
    private static Player conversionTryPlayer;
    private static Player substitutePlayer;

    private static Boolean alterSwap = false;
    public static String swapTimestamp;
    private static Boolean customTimeStamp = false;
    private static String timeStampString;
    private static String currentEventString = "Current events";

    public int swapIndex;
    public int[] timeEventIndexes = new int[3];

    private static Intent mainIntent;

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

    public void resetEventsList()
    {
        eventsList = new ArrayList();
    }

    public void addToEvents(String eventType, String time ,Team teamOne, Team teamTwo, Player playerOne, Player platerTwo,String disciplineType)
    {
        Event temp = new Event();
        temp.setEventValues(eventType, time, teamOne, teamTwo, playerOne, platerTwo, disciplineType);
        eventsList.add(temp);
    }

    public void addTry(String time, Team teamOne, Player playerOne)
    {
        Event temp = new Event();
        temp.addTry(time, teamOne, playerOne);
        eventsList.add(temp);
    }

    public void addTryConversion(String time, Team teamOne, Player playerOne, Player playerTwo)
    {
        Event temp = new Event();
        temp.addTryConversion(time, teamOne, playerOne, playerTwo);
        eventsList.add(temp);
    }

    public void addPenaltyKick(String time, Team teamOne, Player playerOne)
    {
        Event temp = new Event();
        temp.addPenaltyKick(time, teamOne, playerOne);
        eventsList.add(temp);
    }

    public void addDropKick(String time, Team teamOne, Player playerOne)
    {
        Event temp = new Event();
        temp.addDropKick(time, teamOne, playerOne);
        eventsList.add(temp);
    }

    public void addScrum(String time, Team teamOne, Team teamTwo)
    {
        Event temp = new Event();
        temp.addScrum(time, teamOne, teamTwo);
        eventsList.add(temp);
    }

    public void addLineOut(String time, Team teamOne,Team teamTwo)
    {
        Event temp = new Event();
        temp.addLineOut(time, teamOne, teamTwo);
        eventsList.add(temp);
    }

    public void addDiscipline(String time, Team teamOne,Player playerOne, String card)
    {
        Event temp = new Event();
        temp.addDiscipline(time, teamOne, playerOne, card);
        eventsList.add(temp);
    }

    public void addTurnover(String time, Team teamOne,Team teamTwo)
    {
        Event temp = new Event();
        temp.addTurnover(time, teamOne, teamTwo);
        eventsList.add(temp);
    }

    public void addSubstitution(String time, Team teamOne, Player playerOne,Player playerTwo)
    {
        Event temp = new Event();
        temp.addSubstitution(time, teamOne, playerOne, playerTwo);
        eventsList.add(temp);
    }

    public void addClockEvent(String time, String event)
    {
        Event temp = new Event();
        temp.addClockEvent(time, event);
        eventsList.add(temp);

        timeEventIndexes[0] = 0;

        for(int i = 0; i < eventsList.size(); i++)
        {
            if(eventsList.get(i).getEvent().equals("StartSecondHalf"))
                timeEventIndexes[1] = i;
        }

        if(event.equals("EndOfGame"))
            timeEventIndexes[2] = eventsList.size()-1;
    }

    public void setEventIndex(int i) {eventIndex = i;}

    public int getEventIndex() {return eventIndex;}

    public String getDescriptionAt(int index)
    {
        currentEventString = eventsList.get(index).getDescription();
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

    public String generateTimeStamp()
    {
        Calendar calendar = Calendar.getInstance();;
        String timeStamp;
        int year,month,day,hour,minute,second;

        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        if(calendar.get(Calendar.AM_PM) == Calendar.AM)
            hour = calendar.get(Calendar.HOUR);
        else
            hour = calendar.get(Calendar.HOUR) + 12;

        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);

        if(!customTimeStamp) {
            timeStamp = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
        }
        else
        {
            timeStamp = year + "-" + month + "-" + day + " " + timeStampString;
        }
        customTimeStamp = false;
        return timeStamp;
    }

    public void setConversionTryPlayer(Player ccp)
    {
        conversionTryPlayer = ccp;
    }

    public Player getConversionTryPlayer()
    {
        return conversionTryPlayer;
    }

    public void setSubstitutePlayer(Player sp)
    {
        substitutePlayer = sp;
    }

    public Player getSubstitutePlayer()
    {
        return substitutePlayer;
    }

    public void setCustomTimeStamp(Boolean in)
    {
        customTimeStamp = in;
    }

    public void setCustomTimeStampString(String in)
    {
        timeStampString = in;
    }

    public void setMainIntent(Intent in)
    {
        mainIntent = in;
    }

    public Intent getMainIntent() {return mainIntent;}

    public int getEventsListSize() {return eventsListSize;}

    //==========================================================================
    //                              ALTER EVENT STUFF
    //==========================================================================

    public void setSwapIndex(int i) {swapIndex = i;alterSwap = true;}

    public Boolean getAlterSwap()
    {
        return alterSwap;
    }

    public void swapAtIndexAndEnd()
    {
        Event temp = eventsList.get(eventsList.size() - 1);

        System.out.println("swapIndex  " + swapIndex);

        temp.setTimeStamp(eventsList.get(swapIndex).getTimeStamp());
        eventsList.set(swapIndex, temp);

        deleteEventAtIndex(eventsList.size() - 1);
        eventsList.remove(eventsList.size() - 1);

        alterSwap = false;
    }

    public void setSwapTimestamp(String swapTimestamp)
    {
        this.swapTimestamp = swapTimestamp;
    }

    public String getSwapTimestamp()
    {
        return swapTimestamp;
    }

    public int[] getTimeEventIndexes()
    {
        return timeEventIndexes;
    }

    private void deleteEventAtIndex(int in)
    {
        int index = in;
        Event temp = eventsList.get(in);//getEventAtIndex(index);
        int playerIndexOne = 0, playerIndexTwo = 0;

        if(temp.getEvent().equals("Try"))
        {
            if(temp.getTeamOne().hashCode() == getTeamOne().hashCode())
            {
                getTeamOne().decreaseTries();
                getTeamOne().decreaseScore(5);

                for(int i = 0; i < getTeamOne().getPlayers().size(); i++)
                {
                    if(temp.getPlayerOne().hashCode() == getTeamOne().getPlayers().get(i).hashCode()) {
                        System.out.println("Player " + temp.getPlayerOne().getPlayerName());
                        playerIndexOne = i;
                    }
                }

                getTeamOne().getPlayers().get(playerIndexOne).decreaseTries();
            }
            else
            {
                getTeamTwo().decreaseTries();
                getTeamTwo().decreaseScore(5);

                for(int i = 0; i < getTeamTwo().getPlayers().size(); i++)
                {
                    if(temp.getPlayerOne().hashCode() == getTeamTwo().getPlayers().get(i).hashCode()) {
                        playerIndexOne = i;
                    }
                }

                getTeamTwo().getPlayers().get(playerIndexOne).decreaseTries();
            }
        }
        else if(temp.getEvent().equals("TryConversion"))
        {
            if(temp.getTeamOne().hashCode() == getTeamOne().hashCode())
            {
                getTeamOne().decreaseTries();
                getTeamOne().decreaseConversionKicks();
                getTeamOne().decreaseScore(7);

                for(int i = 0; i < getTeamOne().getPlayers().size(); i++)
                {
                    if(temp.getPlayerOne().hashCode() == getTeamOne().getPlayers().get(i).hashCode()) {
                        playerIndexOne = i;
                    }
                }

                getTeamOne().getPlayers().get(playerIndexOne).decreaseTries();

                for(int i = 0; i < getTeamOne().getPlayers().size(); i++)
                {
                    if(temp.getPlayerTwo().hashCode() == getTeamOne().getPlayers().get(i).hashCode()) {
                        playerIndexOne = i;
                    }
                }
                getTeamOne().getPlayers().get(playerIndexTwo).decreaseConversionKicks();
            }
            else
            {
                getTeamTwo().decreaseTries();
                getTeamTwo().decreaseConversionKicks();
                getTeamTwo().decreaseScore(7);

                for(int i = 0; i < getTeamTwo().getPlayers().size(); i++)
                {
                    if(temp.getPlayerOne().hashCode() == getTeamTwo().getPlayers().get(i).hashCode()) {
                        playerIndexOne = i;
                    }
                }

                getTeamTwo().getPlayers().get(playerIndexOne).decreaseTries();

                for(int i = 0; i < getTeamOne().getPlayers().size(); i++)
                {
                    if(temp.getPlayerTwo().hashCode() == getTeamTwo().getPlayers().get(i).hashCode()) {
                        playerIndexOne = i;
                    }
                }

                getTeamOne().getPlayers().get(playerIndexTwo).decreaseConversionKicks();
            }
        }
        if(temp.getEvent().equals("PenaltyKick"))
        {
            if(temp.getTeamOne().hashCode() == getTeamOne().hashCode())
            {
                getTeamOne().decreasePenaltyKicks();
                getTeamOne().decreaseScore(3);

                for(int i = 0; i < getTeamOne().getPlayers().size(); i++)
                {
                    if(temp.getPlayerOne().hashCode() == getTeamOne().getPlayers().get(i).hashCode()) {
                        playerIndexOne = i;
                    }
                }

                getTeamOne().getPlayers().get(playerIndexOne).decreasePenaltyKicks();
            }
            else
            {
                getTeamTwo().decreasePenaltyKicks();
                getTeamTwo().decreaseScore(3);

                for(int i = 0; i < getTeamTwo().getPlayers().size(); i++)
                {
                    if(temp.getPlayerOne().hashCode() == getTeamTwo().getPlayers().get(i).hashCode()) {
                        playerIndexOne = i;
                    }
                }

                getTeamTwo().getPlayers().get(playerIndexOne).decreasePenaltyKicks();
            }
        }
        else if(temp.getEvent().equals("DropKick"))
        {
            if(temp.getTeamOne().hashCode() == getTeamOne().hashCode())
            {
                getTeamOne().decreaseDropKicks();
                getTeamOne().decreaseScore(3);

                for(int i = 0; i < getTeamOne().getPlayers().size(); i++)
                {
                    if(temp.getPlayerOne().hashCode() == getTeamOne().getPlayers().get(i).hashCode()) {
                        playerIndexOne = i;
                    }
                }

                getTeamOne().getPlayers().get(playerIndexOne).decreaseDropKicks();
            }
            else
            {
                getTeamTwo().decreaseDropKicks();
                getTeamTwo().decreaseScore(3);

                for(int i = 0; i < getTeamTwo().getPlayers().size(); i++)
                {
                    if(temp.getPlayerOne().hashCode() == getTeamTwo().getPlayers().get(i).hashCode()) {
                        playerIndexOne = i;
                    }
                }

                getTeamTwo().getPlayers().get(playerIndexOne).decreaseDropKicks();
            }
        }
        else if(temp.getEvent().equals("Scrum"))
        {
            if(temp.getTeamOne().hashCode() == getTeamOne().hashCode())
            {
                getTeamOne().decreaseScrumsWon();
                getTeamTwo().decreaseScrumsLost();
            }
            else
            {
                System.out.println("Team " + temp.getTeamOne().getTeamName());

                getTeamTwo().decreaseScrumsWon();
                getTeamOne().decreaseScrumsLost();
            }
        }
        else if(temp.getEvent().equals("LineOut"))//?? how to increment/decrement lossess
        {
            if(temp.getTeamOne().hashCode() == getTeamOne().hashCode())
            {
                //getTeamOne().decreaseRuckWon();
                //getTeamTwo().decreaseRuckLost();
            }
            else
            {
                //getTeamTwo().decreaseRuckWon();
                //getTeamOne().decreaseRuckLost();
            }
        }
        else if(temp.getEvent().equals("Discipline"))
        {
            if (temp.getTeamOne().hashCode() == getTeamOne().hashCode())
            {
                for (int i = 0; i < getTeamOne().getPlayers().size(); i++)
                {
                    if (temp.getPlayerOne().hashCode() == getTeamOne().getPlayers().get(i).hashCode()) {
                        playerIndexOne = i;
                    }
                }
                System.out.println("Index " + playerIndexOne);

                if (getTeamOne().getPlayers().get(playerIndexOne).getYellowCard())
                {
                    getTeamOne().getPlayers().get(playerIndexOne).removeYellowCard();
                }
                else
                {   System.out.println("Remove mah card, yo " + playerIndexOne);
                    getTeamOne().getPlayers().get(playerIndexOne).removeRedCard();
                }
            }
            else
            {
                System.out.println("Team " + temp.getTeamOne().getTeamName());

                for (int i = 0; i < getTeamOne().getPlayers().size(); i++)
                {
                    if (temp.getPlayerOne().hashCode() == getTeamTwo().getPlayers().get(i).hashCode()) {
                        playerIndexOne = i;
                    }
                }

                if (getTeamTwo().getPlayers().get(playerIndexOne).getYellowCard()) {
                    getTeamTwo().getPlayers().get(playerIndexOne).removeYellowCard();
                } else {
                    getTeamTwo().getPlayers().get(playerIndexOne).removeRedCard();
                }
            }
        }
        else if(temp.getEvent().equals("Turnover"))
        {
            if(temp.getTeamOne().hashCode() == getTeamOne().hashCode())
            {
                getTeamOne().decreaseTurnoverWon();
                getTeamTwo().decreaseTurnoverLost();
            }
            else
            {
                getTeamTwo().decreaseTurnoverWon();
                getTeamOne().decreaseTurnoverLost();
            }
        }
        else if(temp.getEvent().equals("Substitution"))
        {
            if(temp.getTeamOne().hashCode() == getTeamOne().hashCode())
            {
                for(int i = 0; i < getTeamOne().getPlayers().size(); i++)
                {
                    if(temp.getPlayerTwo().hashCode() == getTeamOne().getPlayers().get(i).hashCode()) {
                        playerIndexOne = i;
                    }
                }

                for(int i = 0; i < getTeamOne().getPlayers().size(); i++)
                {
                    if(temp.getPlayerOne().hashCode() == getTeamOne().getPlayers().get(i).hashCode()) {
                        playerIndexTwo = i;
                    }
                }

                //getTeamOne().swapOnFieldAndReserves(playerIndexOne, playerIndexTwo);
            }
            else
            {
                for (int i = 0; i < getTeamOne().getPlayers().size(); i++) {
                    if (temp.getPlayerTwo().hashCode() == getTeamTwo().getPlayers().get(i).hashCode()) {
                        playerIndexOne = i;
                    }
                }

                for (int i = 0; i < getTeamTwo().getPlayers().size(); i++) {
                    if (temp.getPlayerOne().hashCode() == getTeamTwo().getPlayers().get(i).hashCode()) {
                        playerIndexTwo = i;
                    }
                }

                //getTeamTwo().swapOnFieldAndReserves(playerIndexOne, playerIndexTwo);
            }
        }

    }
}

