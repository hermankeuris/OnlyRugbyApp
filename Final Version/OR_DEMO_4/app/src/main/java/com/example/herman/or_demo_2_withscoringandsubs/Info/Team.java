package com.example.herman.or_demo_2_withscoringandsubs.Info;

import java.util.ArrayList;

public class Team {
    //Name of the team
    private String teamName;
    protected ArrayList<Player> players = new ArrayList<>(23);
    private int currentMatchScore;
    private int  teamTry = 0;
    private int  teamConKick = 0;
    private int  teamPenKick = 0;
    private int  teamDropKick = 0;
    private int teamTurnoversWon = 0;
    private int teamTurnoversLost = 0;
    private int teamScrumsWon = 0;
    private int teamScrumsLost = 0;
    private int ownTeamLineOutsWon = 0;
    private int opponentTeamLineOutsWon = 0;
    private int ownTeamLineOutsLost = 0;
    private int opponentTeamLineOutsLost = 0;
    private boolean lineOut = false;

    public Team(String tm)
    {
        this.teamName = tm;

        this.currentMatchScore = 0;

        for(int i = 0; i < 22; i++)
            players.add(new Player());
        players.add(new Player("Unknown Player", 0, false));
    }

    public Team()
    {
        this.teamName = "Unknown team name";

        this.currentMatchScore = 0;

        for(int i = 0; i < 22; i++)
            players.add(new Player());
        players.add(new Player("Unknown Player", 0, false));
    }

    public Team(Team team) {
        setTeamName(team.getTeamName());
        this.currentMatchScore = team.getScore();
        setPlayers(team.getPlayers());
    }

    public void setTeamName(String tn)
    {
        this.teamName = tn;
    }

    public String getTeamName()
    {
        return this.teamName;
    }

    public ArrayList<Player> getPlayers()
    {
        return this.players;
    }

    public void setPlayers(ArrayList<Player> p)
    {
        this.players = new ArrayList<Player>();

        for (int i = 0; i < players.size() - 1; i++)
        {
            if (p.get(i) != null)
                this.players.add(p.get(i));
        }
    }

    public boolean addPlayer(Player p)
    {
        for (int i = 0; i < players.size(); i++)
        {
            if (players.get(i).getCurr_position() == p.getCurr_position() && p.getCurr_position() != 0)
            {
                return false;
            }
        }

        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getCurr_position() == 0) {
                players.set(i, p);
                return true;
            }
        }

        return false;
    }

    public boolean addScore(String temp) {
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

    public int getScore() {
        return this.currentMatchScore;
    }

    /*public boolean subPlayers(Player first, Player second) {
        int temp1 = onField.indexOf(first);
        int temp2 = reserves.indexOf(second);

        if(first != null && second != null && temp1 != -1 && temp2 != -1) {
            onField.set(onField.indexOf(first), second);
            reserves.set(reserves.indexOf(second), first);
            return true;
        }
        else
            return false;
    }*/

    public void incrementTurnoversWon() {
        teamTurnoversWon++;
    }

    public void incrementTurnoversLost() {
        teamTurnoversLost++;
    }

    public int getTurnoversWon() {
        return teamTurnoversWon;
    }

    public int getTurnoversLost() {
        return teamTurnoversLost;
    }

    public void incrementScrumsWon() {
        teamScrumsWon++;
    }

    public void incrementScrumsLost() {
        teamScrumsLost++;
    }

    public int getPenaltyKicks() {
        return teamPenKick;
    }

    public int getScrumsWon()
    {
        return teamScrumsWon;
    }

    public int getScrumsLost() {
        return teamScrumsLost;
    }

    public void incrementOwnLineOutsWon() {
        ownTeamLineOutsWon++;
    }

    public void incrementOwnLineOutsLost() {
        ownTeamLineOutsLost++;
    }

    public int getOwnLineOutsWon() {
        return ownTeamLineOutsWon;
    }

    public int getOwnLineOutsLost() {
        return ownTeamLineOutsLost;
    }

    public void incrementOpponentLineOutsWon() {
        opponentTeamLineOutsWon++;
    }

    public void incrementOpponentLineOutsLost() {
        opponentTeamLineOutsLost++;
    }

    public int getOpponentLineOutsWon() {
        return opponentTeamLineOutsWon;
    }

    public int getOpponentLineOutsLost() {
        return opponentTeamLineOutsLost;
    }

    public void setLineOut(boolean b) {
        lineOut = b;
    }

    public boolean getLineOut() {
        return lineOut;
    }

    public Player getPlayer(int i) {
        return players.get(i);
    }

    public String print() {
        String printer = "";
        printer += "Team Name: " + this.getTeamName();

        for (int i = 0; i < players.size(); ++i) {
            printer += this.getPlayer(i).print();
        }

        return printer;
    }

    //==========================================================================
    //                              NEW EVENT STUFF
    //==========================================================================

    public void decreaseTries() {
        teamTry--;
    }

    public void decreaseConversionKicks() {
        teamConKick--;
    }

    public void decreasePenaltyKicks() {
        teamPenKick--;
    }

    public void decreaseDropKicks() {
        teamDropKick--;
    }

    public void decreaseScore(int score) {
        currentMatchScore -= score;
    }

    public void decreaseTurnoverLost() {
        teamTurnoversLost--;
    }

    public void decreaseTurnoverWon() {
        teamTurnoversWon--;
    }

    public void decreaseScrumsWon() {
        teamScrumsWon--;
    }

    public void decreaseScrumsLost() {
        teamScrumsLost--;
    }

    public void decreaseOwnTeamLineOutsWon() {
        ownTeamLineOutsWon--;
    }

    public void decreaseOpponentTeamLineOutsWon() {
        opponentTeamLineOutsWon--;
    }

    public void decreaseOwnTeamLineOutsLost() {
        opponentTeamLineOutsLost--;
    }

    public void decreaseOpponentTeamLineOutsLost() {
        opponentTeamLineOutsLost--;
    }

    /*public void swapOnFieldAndReserves(int onFieldIndex, int reservesIndex)
    {
        Player tempPlayerOne = onField.get(onFieldIndex);
        Player tempPlayerTwo = reserves.get(reservesIndex);

        onField.set(onFieldIndex, tempPlayerTwo);
        reserves.set(reservesIndex, tempPlayerOne);
    }*/

}
