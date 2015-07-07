import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import junit;

public class main 
{
    /**
     * Global variables used between all functions
     * */
    //An arraylist containing the two teams which are currently playing
    private ArrayList<Team> teams = new ArrayList<>(2);
    //A variable which keeps track of which team currently has possession of the ball.
    private Team possession = null;
    //The date/time the match began
    private Date matchStart = null;
    //The date/time the first half of the match ended
    private Date firstHalfEnd = null;
    //The date/time the second half of the match started
    private Date firstHalfStart = null;
    //The date/time the match ended
    private Date matchEnd = null;
    
    
    /**
     * Enumeration classes
     */
    //A class to specify the different methods of scoring in rugby
    public enum ScoreType
    {
        Try, PenaltyKick, DropKick,ConversionKick
    }
    //A class to specify the types of punishment a player can receive during match-time
    public enum DisciplineType
    {
        YellowCard, RedCard, None
    }
    
    
    /**
     * General classes used by all functions
     */
    //A class to encapsulate each player'sd individual info.
    public class Player 
    {
        //The player's name
        private String playerName;
        //The player's jersey number
        private int jerseyNum;
        //The number of times an injury forced the player off the field during match-time
        private int matchTimeInjuries;
        //A variable to store if the player is currently being penalised
        private DisciplineType status;
        //A variable to indicate if the player is currently a reserve player (true) or an on-field player (fasle).
        private boolean reservePlayer;
        
        //Constructor with parameters
        Player(String pn, int jn, boolean field)
        {
            playerName = pn;
            jerseyNum = jn;
            matchTimeInjuries = 0;
            status = DisciplineType.None;
            reservePlayer = field;
        }
        
        //Constructor without parameters (i.e. default constructor)
        Player()
        {
            playerName = "Unknown";
            jerseyNum = 99;
            matchTimeInjuries = 0;
            status = DisciplineType.None;
            reservePlayer = false;
        }
        
        //A function which changes the player's name
        public void setPlayerName(String pn)
        {
            playerName = pn;
        }
        
        //A function which returns the player's name
        public String getPlayerName()
        {
            return playerName;
        }
        
        //A function which changes the player's jersey number
        public void setJerseyNum(int jn)
        {
            jerseyNum = jn;
        }
        
        //A function which returns the player's jersey number
        public int getJerseyNum()
        {
            return jerseyNum;
        }
        
        //A function which changes the player's current penalty status
        public void setStatus(DisciplineType s)
        {
            status = s;
        }
        
        //A function which returns the player's current penalty status
        public DisciplineType getStatus()
        {
            return status;
        }
        
        //A function which changes whether a player is on field or not
        public void setReserve(boolean of)
        {
            reservePlayer = of;
        }
        
        //A function which returns whether the player is on field or not
        public boolean getReserve()
        {
            return reservePlayer;
        }
    }
    //A class to encapsulate each player's individual info.
    public class Team
    {
        private String teamName;
        protected ArrayList<Player> onField = new ArrayList<>(15);
        protected ArrayList<Player> reserves = new ArrayList<>(15);
        private int currentMatchScore;
        
        Team(String tm)
        {
            teamName = tm;
            
            currentMatchScore = 0;
        }
        
        public void setTeamName(String tn)
        {
            teamName = tn;
        }
        
        public String getTeamName()
        {
            return teamName;
        }
        
        public void addScore(int num)
        {
            currentMatchScore += num;
        }
    }

    
    /**
     * Helper functions
     */
    
    /**
     * This function gets either the player's name or jersey number (or both) and return the player's info
     * @param team The team the player is on
     * @param name The player's name (can be left blank)
     * @param num The player's jersey number (can be left as 0)
     * @return The player's info is returned
     * 
     * If both "name" and "num" are left blank then a default "Unknown" player is returned
     */
    public Player selectPlayer(Team team, String name, int num)
    {
        for (int i = 0; i < 15; i++)
        {
            if ((team.onField.get(i).getPlayerName() == name) || (team.onField.get(i).getJerseyNum() == num))
                    return team.onField.get(i);
        }
        for (int i = 0; i < 15; i++)
        {
            if ((team.reserves.get(i).getPlayerName() == name) || (team.reserves.get(i).getJerseyNum() == num))
                    return team.reserves.get(i);
        }
        return new Player();
    }
    
    
    /**
     * Basic functionality
     */
    
    /**
     * A function that updates and stores the relevant variables when someone scores
     * @param _scoringTeam The name of the team that scored
     * @param _score The type of score it was (e.g. try)
     * @param scoringPlayerName The name of the player who scored (can be left blank)
     * @param scoringPlayerNum The jersey number of the player who scored (can be left blank)
     * @param tryAssisted Indicates if, in the case of a try, the scoring player was assisted by a team mate (true) or not (false)
     * @param assistName If tryAssisted is true then this variable indicates the name of the player who assisted the scoring player (can be left blank)
     * @param assistNum If tryAssisted is true then this variable indicates the jersey number of the player who assisted the scoring player (can be left blank)
     * @param conversion Indicates, if the previous score was type try, if the subsequent conversion kick was successful
     * @param kickerName In the case of a conversion kick this variable indicates the name of the kicker
     * @param kickerNum In the case of a conversion kick this variable indicates the jersey number of the kicker
     */
    public ScoreAnswer score (String _scoringTeam, ScoreType _score, String scoringPlayerName, int scoringPlayerNum, Boolean tryAssisted, String assistName, int assistNum, Boolean conversion, String kickerName, int kickerNum)
    {
        //The team who scored
        Team scoringTeam = null;
        //The type of score
        ScoreType score = null;
        //The player who scored
        Player scoringPlayer = null;
        //The player who assisted the try (if the score type was try and the scoring player was indeed assisted)
        Player assist = null;
        //The player who scored the conversion kick (if the original score was type try and the subsequent conversion kick was successful)
        Player kicker = null;
        
        //A flag for error detection
        boolean error = false;
        
        //Check which team scored and save it to the scoringTeam variable
        if (teams.get(0).getTeamName() == _scoringTeam)
        {
            scoringTeam = teams.get(0);
        }
        else if (teams.get(1).getTeamName() == _scoringTeam)
        {
            scoringTeam = teams.get(1);
        }
        else
        {
            error = true;
        }
        
        //Check which type of score it was
        switch (score)
        {
            case Try:
                //Add 5 points to the scoring team's points
                scoringTeam.addScore(5);
                
                //Check if the try was assisted by another player
                if (tryAssisted)
                    assist= selectPlayer(scoringTeam, assistName, assistNum);
                
                //Check if the subsequent conversion kick was successful
                if (conversion)
                {
                    //Add 2 points to the scoring team's points
                    scoringTeam.addScore(2);
                    kicker = selectPlayer(scoringTeam, kickerName, kickerNum);
                }
                break;
            case PenaltyKick:
                //Add 3 points to the scoring team's points
                scoringTeam.addScore(3);
                break;
            case DropKick:
                //Add 3 points to the scoring team's points
                scoringTeam.addScore(3);
                break;
            case ConversionKick:
                //Add 2 points to the scoring team's points
                scoringTeam.addScore(2);
                break;
            default:
                error = true;
                break;
        }
        scoringPlayer = selectPlayer(scoringTeam, scoringPlayerName, scoringPlayerNum);
        
        //Log all the information about the score (including the date/time when the score occured)
        return new ScoreAnswer(scoringTeam, score, scoringPlayer, assist, kicker, new Date(),error);

        //Select team
        //Select score type
        //Select player
        //Select assist
        //Log score time
    }

    /**
     * This function substitutes one on filed player with a reserve player fromthe same team and also indicates if the substitution was due to injury
     * @param _team
     * @param playerOffName
     * @param playerOffNum
     * @param playerOnName
     * @param playerOnNum
     * @param _injury 
     */
    public SubsAnswer substitutions (String  _team, String playerOffName, int playerOffNum, String playerOnName, int playerOnNum, boolean _injury)
    {
        //The team who is substituting a player
        Team team = null;
        //The player who is coming off the field
        Player playerOff = null;
        //The player who is now going on the filed
        Player playerOn = null;
        //A flag to indicate if the substitution is injury related
        boolean injury;
        
        //A flag for error detection
        boolean error = false;
        
        //Check which team is substituting a player and save it to the team variable
        if (teams.get(0).getTeamName() == _team)
        {
            team = teams.get(0);
        }
        else if (teams.get(1).getTeamName() == _team)
        {
            team = teams.get(1);
        }
        else
        {
            error = true;
        }
        
        //Get the players' info
        playerOff = selectPlayer(team, playerOffName, playerOffNum);
        playerOn = selectPlayer(team, playerOnName, playerOnNum);
        
        //Make sure neither players are currently being penalised
        if (playerOff.getStatus() == DisciplineType.None && playerOff.getStatus() == DisciplineType.None)
        {
            //Get the index of each player in bothe the onFiled and reserve arraylists
            int tempOffIndex = team.onField.indexOf(playerOff);
            int tempOnIndex = team.reserves.indexOf(playerOn);
            
            //Set the players' "reserve" flags to indicate which player will now be on the field and which will be in reserve
            playerOff.setReserve(true);
            playerOn.setReserve(false);
            
            //Swap the players on the onFiled and reserve arraylists
            team.onField.set(tempOffIndex, playerOn);
            team.reserves.set(tempOnIndex, playerOff);
        }
        else
            error = true;
        
        injury = _injury;
        
        //Log all the information about the substitution (including the date/time when the sub occured)
        return new SubsAnswer(team, playerOn, playerOn, injury, new Date(), error);
        
        //Select team
        //Select on field player
        //Select reserve player
        //Specify if substitution was due to injury
        //Log sub time
    }

    /**
     * A function which logs when a ruck occurs and who won it
     * @param _team The team who won the ruck
     */
    public void ruck (String  _team)
    {
        //The team who won the ruck
        Team team = null;

        //Aflag for error detection
        boolean error = false;
        
        //Check which team won the ruck and save it to the team variable
        if (teams.get(0).getTeamName() == _team)
        {
            team = teams.get(0);
        }
        else if (teams.get(1).getTeamName() == _team)
        {
            team = teams.get(1);
        }
        else
        {
            error = true;
        }
        
        //Checking if possession of the ball has changed due to 
        if (team != possession)
        {
            //Call the "possession()" function to indicate the ball has changed possession.
        }
        
        
        //Select which team won the ruck
        //Call possession()
        //Log ruck
    }
    
    
    /**
     * Unit test classes
     */
    
    public class ScoreAnswer
    {
        //The team who scored
        public Team scoringTeam;
        //The type of score
        public ScoreType score;
        //The player who scored
        public Player scoringPlayer;
        //The player who assisted the try (if the score type was try and the scoring player was indeed assisted)
        public Player assist;
        //The player who scored the conversion kick (if the original score was type try and the subsequent conversion kick was successful)
        public Player kicker;
        //The date and time that the score was logged
        public Date date = null;
        //A flag for error detection
        public boolean error;
        
        ScoreAnswer (Team st, ScoreType s, Player sp, Player a, Player k, Date d, boolean e)
        {
            scoringTeam = st;
            score = s;
            scoringPlayer = sp;
            assist = a;
            kicker = k;
            date = d;
            error = e;
        }
    }
    
    public class SubsAnswer
    {
        //The team who scored
        public Team team;
        //The player who is coming off the field
        public Player playerOff = null;
        //The player who is now going on the filed
        public Player playerOn = null;
        //A flag to indicate if the substitution is injury related
        public boolean injury;
        //The date and time that the substitution was logged
        public Date date = null;
        //A flag for error detection
        public boolean error;
        
        SubsAnswer (Team t, Player pf, Player pn, boolean i, Date d, boolean e)
        {
            team = t;
            playerOff = pf;
            playerOn = pn;
            injury = i;
            date = d;
            error = e;
        }
    }
    
	/**
     * Function that loads information from the database via the server
     * TODO
     * 1: Discuss how the different types of information are to be loaded for different situations
     */
    public void loadInfo ()
    {
    	//no real implementation while decisions around storing data still needs to be made - only dummy data
		teams.add(new Team("Team 1"));
    	teams.add(new Team("Team 2"));

    	for (int k = 0; k <= 1; ++k) //for the two (2) different teams
    	{
    		for (int i = 1; i <= 15; ++i) //for the fifteen (15) main players
    		{
    			teams.get(k).onField.add(new Player("P" + i + "(T" + (k+1) + ")", i, false)); //False, assuming the player is NOT a reserve player
    		}
    		
    		for (int i = 16; i <= 30; ++i) //for the fifteen (15) reserve players
    		{
    			teams.get(k).onField.add(new Player("P" + i + "(T" + (k+1) + ")", i, true)); //False, assuming the player IS a reserve player
    		}
    	}
    	
    	echo("Team 1: ");
    	for (int k = 0; k <= 1; ++k) //for the two (2) different teams
    	{
    		for (int i = 0; i < 30; ++i) //for the fifteen (15) main players
    		{
    			if (i != 0) echo(", ");
    			echo(teams.get(k).onField.get(i).playerName + "");
    		}
    		echoln("");
    		if (k == 0) echo("Team 2: ");
    	}
    }
    
    
    /**
     * TODO
	* 1: To be implemented fully in Phase 3, showing who tackled who
	* 		For now, only showing that a tackle took place at this time frame
	* 2: Write a deep copy for Team constructor (copy constructor)
     * @param tackler The team who performed the tackle
     */
    public void tackle (Team tackler)
    {
    	Team tackled = teams.get(0).equals(tackler) ? tackler : teams.get(1);
    	String message = "Confirm that " + tackler.getTeamName() + " Tackled " + tackled.getTeamName() + "?";
    	//call confirmOrder(message) to confirm selection
    	//send info to server with time stamp
    	
    	/*
		* Info to be sent to the server includes:
		* 1: Which team/player had possession of the ball when the tackle took place
		* 2: Tackler/s
		* 3: Tackled (will be same as the one having possession, could possibly be left out)
		* 4: Time stamp
		*/
    	
    	/*
		* Different ways to get time stamp:
		* new Date().getTime();
		* new Date().toString();
		* System.currentTimeMillis();  <-- seems to be the fastest
		* new Timestamp(new Date().getTime());
		* new Timestamp(System.currentTimeMillis());
		*/
    }
    
    
    /**
     * Indicates that a maul took place at time stamp
     * TODO
     * 1: Possibly to be implemented fully in Phase 3, showing who took part in the maul
	* 		For now, only showing that a maul took place at this time frame
	* 2: Use try/catch blocks to catch any errors and call log function to log faults
     */
    public void maul ()
    {
    	String message = "Confirm that a Maul took place?";
    	//call confirmOrder(message) to confirm selection
    	//send info to server to log a maul took place
    	
    	/*
		* Info to be sent to the server includes:
		* 1: Which team/player had possession of the ball when the maul took place
		* 2: Possible advantage for any teams
		* 3: Time stamp
		*/
    	
    	/*
		* Different ways to get time stamp:
		* new Date().getTime();
		* new Date().toString();
		* System.currentTimeMillis();  <-- seems to be the fastest
		* new Timestamp(new Date().getTime());
		* new Timestamp(System.currentTimeMillis());
		*/
    }
	
	 /**
     * A helper function to shorten displaying a message to the console on a new line
     * @param msg The message to be printed to the console
     */
    public void echoln(String msg)
    {
    	System.out.println(msg);
    }
    
    /**
     * A helper function to shorten displaying a message to the console on an existing line
     * @param msg The message to be printed to the console
     */
    public void echo(String msg)
    {
    	System.out.print(msg);
    }
    
    /**
     * Unit tests
     */
    //@Test
    public void testScoring()
    {
        
    }
    //@Test
    public void testSubstitutions()
    {

    }
    
    //Run tests
    public static void main(String[] args)
    {
        
    }
}