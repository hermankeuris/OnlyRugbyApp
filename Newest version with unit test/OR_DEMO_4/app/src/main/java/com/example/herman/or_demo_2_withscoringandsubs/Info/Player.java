package com.example.herman.or_demo_2_withscoringandsubs.Info;

public class Player {

    //The player's id
    private int id = -1;
    //The player's name
    private String name = "";
    //The player's surname
    private String surname = "";
    //The player's jersey number
    private int curr_position = -1;

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
    public Player(String pn, int jn, boolean field) {
        index = count;
        count++;

        if(pn == null || pn == "")
            name = "Unknown(" + index + ")";
        else
            name = pn;

        if(jn < 0)
            curr_position = 0;
        else
            curr_position = jn;

        matchTimeInjuries = 0;
        //status = Data.DisciplineType.None;

        reservePlayer = field;
    }

    //Copy Constructor
    Player(Player player) {
        this.setId(player.getId());
        this.setName(player.getName());
        this.setSurname(player.getSurname());
        this.setCurr_position(player.getCurr_position());
        this.setReserve(player.getReserve());
    }

    //Constructor without parameters (i.e. default constructor)
    public Player() {
        index = count;
        count++;

        name = "Unknown(" + index + ")";
        curr_position = 0;
        matchTimeInjuries = 0;
        //status = Data.DisciplineType.None;
        reservePlayer = false;
    }

    //A function which changes the player's name
    public void setPlayerName(String pn) {
        if(pn == null || pn == "")
            name = "Unknown(" + index + ")";
        else
            name = pn;
    }

    //A function which returns the player's name
    public String getPlayerName() {
        return name;
    }

    //A function which changes the player's jersey number
    public boolean setCurr_position(int jn) {
        if (jn > 0) {
            curr_position = jn;
            return true;
        }
        return false;
    }

    //A function which returns the player's jersey number
    public int getCurr_position() {
        return curr_position;
    }

    //A function which changes the player's current penalty status
    /*public void setStatus(Data.DisciplineType s) {
        status = s;
    }*/

    //A function which returns the player's current penalty status
    /*public Data.DisciplineType getStatus() {
        return status;
    }*/

    //A function which changes whether a player is on field or not
    public void setReserve(boolean of) {
        reservePlayer = of;
    }

    //A function which returns whether the player is on field or not
    public boolean getReserve() {
        return reservePlayer;
    }

    public boolean playerScore(String temp) {
        switch(temp) {
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

    public int[] getPlayerScore() {
        int[] answer = {playerTry, playerConKick, playerPenKick, playerDropKick};
        return answer;
    }

    public void resetCount() {
        count = 0;
    }

    public void giveWhiteCard() {
        numWhiteCards++;
    }

    public void giveYellowCard() {
        numYellowCards++;
        yellowCard = true;
    }

    public void giveRedCard() {
        numRedCards++;
        redCard = true;
        yellowCard = true;
    }

    public int getWhiteCardCount() {
        return numWhiteCards;
    }

    public int getYellowCardCount() {
        return numYellowCards;
    }

    public int getRedCardCount() {
        return numRedCards;
    }

    public boolean getRedCard() {
        return redCard;
    }

    public boolean getYellowCard() {
        return yellowCard;
    }

    public void setYellowCard(Boolean b) {
        yellowCard = b;
    }
	
	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String print() {
        String printer = "\t[";
        printer += "Player ID: " + this.getId();
        printer += "\tPlayer Name: " + this.getName();
        printer += "\tPlayer Surname: " + this.getSurname();
        printer += "\tPlayer Position: " + this.getCurr_position();
        printer += "],";

        return printer;
    }

    public void decreaseTries()
    {
        playerTry--;
    }

    public void decreaseConversionKicks()
    {
        playerConKick--;
    }
    public void decreasePenaltyKicks()
    {
        playerPenKick--;
    }
    public void decreaseDropKicks()
    {
        playerDropKick--;
    }
    public void decreaseWhiteCards()
    {
        numWhiteCards--;
    }
    public void removeYellowCard()
    {
        numYellowCards--;
        yellowCard = false;

    }
    public void removeRedCard()
    {
        numRedCards--;
        redCard = false;
    }
}