package com.example.herman.or_demo_2_withscoringandsubs.Info;

public class Event
{
    private String eventType;

    private String time;

    private Team teamOne;
    private Team teamTwo;

    private Player playerOne;
    private Player playerTwo;

    private String disciplineType;



    public void setEventValues(String eventType, String time ,Team teamOne, Team teamTwo, Player playerOne, Player playerTwo, String disciplineType )
    {
        this.eventType = eventType;
        this.time = time;
        this.teamOne = teamOne;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public void addTry(String time ,Team teamOne,Player playerOne)
    {
        this.eventType = "Try";
        this.time = time;
        this.teamOne = teamOne;
        this.playerOne = playerOne;
    }

    public void addTryConversion(String time ,Team teamOne,Player playerOne, Player playerTwo)
    {
        this.eventType = "TryConversion";
        this.time = time;
        this.teamOne = teamOne;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public void addPenaltyKick(String time ,Team teamOne,Player playerOne)
    {
        this.eventType = "PenaltyKick";
        this.time = time;
        this.teamOne = teamOne;
        this.playerOne = playerOne;
    }

    public void addDropKick(String time ,Team teamOne,Player playerOne)
    {
        this.eventType = "DropKick";
        this.time = time;
        this.teamOne = teamOne;
        this.playerOne = playerOne;
    }

    public void addRuck(String time ,Team teamOne, Team teamTwo)
    {
        this.eventType = "Ruck";
        this.time = time;
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
    }

    public void addLineOut(String time , Team teamOne, Team teamTwo)
    {
        this.eventType = "LineOut";
        this.time = time;
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
    }

    public void addDiscipline(String time , Team teamOne, Player playerOne, String disciplineType)
    {
        this.eventType = "Discipline";
        this.time = time;
        this.teamOne = teamOne;
        this.playerOne = playerOne;
        this.disciplineType = disciplineType;
    }

    public void addTurnover(String time ,Team teamOne, Team teamTwo)
    {//Probleem: whose line out is it?
        this.eventType = "Turnover";
        this.time = time;
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
    }


    public String getEvent()
    {
        return eventType;
    }

    public String getTime()
    {
        return time;
    }

    public Player getPlayerOne()
    {
        return playerOne;
    }

    public Player getPlayerTwo()
    {
        return playerTwo;
    }

    public Team getTeamOne()
    {
        return teamOne;
    }

    public Team getTeamTwo()
    {
        return teamTwo;
    }

    public String getDescription()
    {
        System.out.println("Desc");
        String description = "";

        if(eventType.equals("Try"))
        {
            description = teamOne.getTeamName() + ": Try by " + playerOne.getPlayerName();
            return description;
        }
        else if(eventType.equals("TryConversion"))
        {
            description = teamOne.getTeamName() + ": Try by " + playerOne.getPlayerName()
                    + ", Conversion-kick by " + playerOne.getPlayerName();
            return description;
        }
        else if(eventType.equals("PenaltyKick"))
        {
            description = teamOne.getTeamName() + ": Penalty-Kick by " + playerOne.getPlayerName();
            return description;
        }
        else if(eventType.equals("DropKick"))
        {
            description = teamOne.getTeamName() + ": Drop-Kick by " + playerOne.getPlayerName();
            return description;
        }
        else if(eventType.equals("Ruck"))
        {
            description = teamOne.getTeamName() + ": Won the Ruck ";
            return description;
        }
        else if(eventType.equals("LineOut"))
        {//Probleem: whose line out is it?
            description = teamOne.getTeamName() + ": Won the Line-Out ";
            return description;
        }
        else if(eventType.equals("Discipline"))
        {
            description = teamOne.getTeamName() + ": " + playerOne.getPlayerName() + " received " + disciplineType + " card";
            return description;
        }
        else if(eventType.equals("Turnover"))
        {
            description = teamOne.getTeamName() + ": Won the Turnover";
            return description;
        }
        else
        {
            description = "eventType";
            return description;
        }
    }


}
