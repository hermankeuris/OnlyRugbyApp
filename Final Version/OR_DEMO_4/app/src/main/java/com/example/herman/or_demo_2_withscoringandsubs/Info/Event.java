package com.example.herman.or_demo_2_withscoringandsubs.Info;

public class Event
{
    private String eventType;

    private String time;
    private String timeStamp;

    private Team teamOne;
    private Team teamTwo;

    private Player playerOne;
    private Player playerTwo;

    private String disciplineType;

    private Boolean deleted = false;
    private Boolean altered = false;

    public void setEventValues(String eventType, String time ,Team teamOne, Team teamTwo, Player playerOne, Player playerTwo, String disciplineType )
    {
        this.eventType = eventType;
        this.time = setTimeToCorrectFormat(this.time);
        this.teamOne = teamOne;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public void addTry(String time, Team teamOne,Player playerOne)
    {
        this.eventType = "Try";
        this.time = time;
        this.timeStamp = setTimeToCorrectFormat(this.time);
        this.teamOne = teamOne;
        this.playerOne = playerOne;
    }

    public void addTryConversion(String time, Team teamOne,Player playerOne, Player playerTwo)
    {
        this.eventType = "TryConversion";
        this.time = time;
        this.timeStamp = setTimeToCorrectFormat(this.time);
        this.teamOne = teamOne;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public void addPenaltyKick(String time, Team teamOne,Player playerOne)
    {
        this.eventType = "PenaltyKick";
        this.time = time;
        this.timeStamp = setTimeToCorrectFormat(this.time);
        this.teamOne = teamOne;
        this.playerOne = playerOne;
    }

    public void addDropKick(String time, Team teamOne,Player playerOne)
    {
        this.eventType = "DropKick";
        this.time = time;
        this.timeStamp = setTimeToCorrectFormat(this.time);
        this.teamOne = teamOne;
        this.playerOne = playerOne;
    }

    public void addScrum(String time, Team teamOne, Team teamTwo)
    {
        this.eventType = "Scrum";
        this.time = time;
        this.timeStamp = setTimeToCorrectFormat(this.time);
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
    }

    public void addLineOut(String time, Team teamOne, Team teamTwo)
    {
        this.eventType = "LineOut";
        this.time = time;
        this.timeStamp = setTimeToCorrectFormat(this.time);
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
    }

    public void addDiscipline(String time, Team teamOne, Player playerOne, String disciplineType)
    {
        this.eventType = "Discipline";
        this.time = time;
        this.timeStamp = setTimeToCorrectFormat(this.time);
        this.teamOne = teamOne;
        this.playerOne = playerOne;
        this.disciplineType = disciplineType;
    }

    public void addTurnover(String time, Team teamOne, Team teamTwo)
    {//Probleem: whose line out is it?
        this.eventType = "Turnover";
        this.time = time;
        this.timeStamp = setTimeToCorrectFormat(this.time);
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
    }

    public void addClockEvent(String time, String clockEvent)
    {
        this.time = time;
        this.timeStamp = setTimeToCorrectFormat(this.time);
        this.eventType = clockEvent;
    }

    public void addSubstitution(String time, Team teamOne,Player playerOne, Player playerTwo)
    {
        this.time = time;
        this.eventType = "Substitution";
        this.timeStamp = setTimeToCorrectFormat(this.time);
        this.teamOne = teamOne;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
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

    public String getDescription()
    {
        String description = "";

        if(eventType.equals("Try"))
        {
            description = timeStamp.substring(timeStamp.indexOf(" ") + 1) + " " + teamOne.getTeamName() + ": Try by " + playerOne.getPlayerName();

            if(deleted)
                description += " deleted";
            if(altered)
                description += " altered";
            return description;
        }
        else if(eventType.equals("TryConversion"))
        {
            description = timeStamp.substring(timeStamp.indexOf(" ") + 1) + " " + teamOne.getTeamName() + ": Try by " + playerOne.getPlayerName()
                    + ", Conversion-kick by " + playerTwo.getPlayerName();
            return description;
        }
        else if(eventType.equals("PenaltyKick"))
        {
            description = timeStamp.substring(timeStamp.indexOf(" ") + 1) + " " + teamOne.getTeamName() + ": Penalty-Kick by " + playerOne.getPlayerName();
            return description;
        }
        else if(eventType.equals("DropKick"))
        {
            description = timeStamp.substring(timeStamp.indexOf(" ") + 1) + " " + teamOne.getTeamName() + ": Drop-Kick by " + playerOne.getPlayerName();
            return description;
        }
        else if(eventType.equals("Scrum"))
        {
            description = timeStamp.substring(timeStamp.indexOf(" ") + 1) + " " + teamOne.getTeamName() + ": Won the Scrum ";
            return description;
        }
        else if(eventType.equals("LineOut"))
        {//Probleem: whose line out is it?
            description = timeStamp.substring(timeStamp.indexOf(" ") + 1) + " " + teamOne.getTeamName() + ": Won the Line-Out ";
            return description;
        }
        else if(eventType.equals("Discipline"))
        {
            description = timeStamp.substring(timeStamp.indexOf(" ") + 1) + " " + teamOne.getTeamName() + ": " + playerOne.getPlayerName() + " received " + disciplineType + " card";
            return description;
        }
        else if(eventType.equals("Turnover"))
        {
            description = timeStamp.substring(timeStamp.indexOf(" ") + 1) + " " + teamOne.getTeamName() + ": Won the Turnover";
            return description;
        }
        else if(eventType.equals("Substitution"))
        {
            description = timeStamp.substring(timeStamp.indexOf(" ") + 1) + " " + teamOne.getTeamName() + ": Player " + playerOne.getPlayerName()
                    + " was substituted for " + playerTwo.getPlayerName();
            return description;
        }
        else if(eventType.equals("StartOfGame"))
        {
            description = timeStamp.substring(timeStamp.indexOf(" ") + 1) + " " + "Start Of Game";
            return description;
        }
        else if(eventType.equals("StartSecondHalf"))
        {
            description = timeStamp.substring(timeStamp.indexOf(" ") + 1) + " " + "Start Of Second Half";
            return description;
        }
        else if(eventType.equals("EndOfGame"))
        {
            description = timeStamp.substring(timeStamp.indexOf(" ") + 1) + " " + "End of Game";
            return description;
        }
        else
        {
            description = "eventType";
            return description;
        }
    }

    public String getTimeStamp()
    {
        return timeStamp;
    }

    public void setTimeStamp(String in)
    {
        timeStamp = in;
    }

    public String setTimeToCorrectFormat(String timeIn)
    {
        if(timeIn.charAt(7) != '-')
            timeIn = timeIn.substring(0, 5) + '0' + timeIn.substring(5);

        if(timeIn.charAt(10) != ' ')
            timeIn = timeIn.substring(0, 8) + '0' + timeIn.substring(8);

        if(timeIn.charAt(13) != ':')
            timeIn = timeIn.substring(0, 11) + '0' + timeIn.substring(11);

        if(timeIn.charAt(16) != ':')
            timeIn = timeIn.substring(0, 14) + '0' + timeIn.substring(14);

        if(timeIn.length() < 19)
            timeIn = timeIn.substring(0, 17) + '0' + timeIn.substring(17);

        return timeIn;
    }

    public void setDeleted(){deleted = true;}
    public void setAltered(){altered = true;}

    public Boolean isDeleted(){return deleted;}
    public Boolean isAltered(){return altered;}


}
