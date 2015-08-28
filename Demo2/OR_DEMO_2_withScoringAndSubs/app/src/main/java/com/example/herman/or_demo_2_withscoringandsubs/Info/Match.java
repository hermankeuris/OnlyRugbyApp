package com.example.herman.or_demo_2_withscoringandsubs.Info;

public class Match {

    private Team teamA;
    private Team teamB;
    private int id;
    private String startTime;

    public Match() {
        teamA = new Team();
        teamB = new Team();
    }

    public Match(Match match) {
        this.setId(match.getId());
        this.setStartTime(match.getStartTime());
        this.setTeamA(match.getTeamA());
        this.setTeamB(match.getTeamB());
    }

    public Team getTeamA() {
        return teamA;
    }

    public void setTeamA(Team teamA) {
        this.teamA = new Team(teamA);
    }

    public Team getTeamB() {
        return teamB;
    }

    public void setTeamB(Team teamB) {
        this.teamB = new Team(teamB);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void print() {
        println("Match ID: " + this.getId());
        println("Match Kickoff: " + this.getStartTime());
        println(this.getTeamA().print());
        println(this.getTeamB().print());
    }

    private static void println (String msg) {
        System.out.println(msg);
    }

    private static void print (String msg) {
        System.out.print(msg);
    }
}
