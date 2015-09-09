package com.example.herman.or_demo_2_withscoringandsubs;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.example.herman.or_demo_2_withscoringandsubs.Info.Data;
import com.example.herman.or_demo_2_withscoringandsubs.Info.Player;
import com.example.herman.or_demo_2_withscoringandsubs.Info.Team;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Herman on 2015-08-23.
 */
public class UnitTest extends ApplicationTestCase<Application> {
    public UnitTest() {
        super(Application.class);
    }

    private Data data = Data.getInstance();
    //Test case to add normal players using Team.addPlayer()
    @Test
    public void addTeam1Test() {
        Team testTeam = new Team();

        testTeam.addPlayer(new Player("John One", 1, false));
        testTeam.addPlayer(new Player("John Two", 2, false));
        testTeam.addPlayer(new Player("John Three", 3, false));
        testTeam.addPlayer(new Player("John Four", 4, false));
        testTeam.addPlayer(new Player("John Five", 5, false));
        testTeam.addPlayer(new Player("John Six", 6, false));
        testTeam.addPlayer(new Player("John Seven", 7, false));
        testTeam.addPlayer(new Player("John Eight", 8, false));
        testTeam.addPlayer(new Player("John Nine", 9, false));
        testTeam.addPlayer(new Player("John Ten", 10, false));
        testTeam.addPlayer(new Player("John Eleven", 11, false));
        testTeam.addPlayer(new Player("John Twelve", 12, false));
        testTeam.addPlayer(new Player("John Thirteen", 13, false));
        testTeam.addPlayer(new Player("John Fourteen", 14, false));
        testTeam.addPlayer(new Player("John Fifteen", 15, false));

        //Team One's reserve players
        testTeam.addPlayer(new Player("John Sixteen", 16, true));
        testTeam.addPlayer(new Player("John Seventeen", 17, true));
        testTeam.addPlayer(new Player("John Eighteen", 18, true));
        testTeam.addPlayer(new Player("John Nineteen", 19, true));
        testTeam.addPlayer(new Player("John Twenty", 20, true));
        testTeam.addPlayer(new Player("John TwentyOne", 21, true));
        testTeam.addPlayer(new Player("John TwentyTwo", 22, true));

        assertTrue(teamCheck1(testTeam));
        assertEquals(testTeam.getTeamName(), "Unknown team name");
    }

    //Test case to add normal players using data.setTeamOne(), Team.setOnField() and Team.setReserve()
    //Also test data's teamOne related functions: Data.getTeamOne(), Data.setTeamOne()
    @Test
    public void addTeam2Test() {
        Team testTeam = new Team("Test name");
        //data.setTeamOne();

        ArrayList<Player> playersOneOnField = new ArrayList<Player>();
        ArrayList<Player> playersOneReserve = new ArrayList<Player>();
        ArrayList<Player> playersTwoOnField = new ArrayList<Player>();
        ArrayList<Player> playersTwoReserve = new ArrayList<Player>();

        //Team One's on field players
        playersOneOnField.add(new Player("John One", 1, false));
        playersOneOnField.add(new Player("John Two", 2, false));
        playersOneOnField.add(new Player("John Three", 3, false));
        playersOneOnField.add(new Player("John Four", 4, false));
        playersOneOnField.add(new Player("John Five", 5, false));
        playersOneOnField.add(new Player("John Six", 6, false));
        playersOneOnField.add(new Player("John Seven", 7, false));
        playersOneOnField.add(new Player("John Eight", 8, false));
        playersOneOnField.add(new Player("John Nine", 9, false));
        playersOneOnField.add(new Player("John Ten", 10, false));
        playersOneOnField.add(new Player("John Eleven", 11, false));
        playersOneOnField.add(new Player("John Twelve", 12, false));
        playersOneOnField.add(new Player("John Thirteen", 13, false));
        playersOneOnField.add(new Player("John Fourteen", 14, false));
        playersOneOnField.add(new Player("John Fifteen", 15, false));

        //Team One's reserve players
        playersOneReserve.add(new Player("John Sixteen", 16, true));
        playersOneReserve.add(new Player("John Seventeen", 17, true));
        playersOneReserve.add(new Player("John Eighteen", 18, true));
        playersOneReserve.add(new Player("John Nineteen", 19, true));
        playersOneReserve.add(new Player("John Twenty", 20, true));
        playersOneReserve.add(new Player("John TwentyOne", 21, true));
        playersOneReserve.add(new Player("John TwentyTwo", 22, true));

        data.setTeamOne(testTeam);
        data.getTeamOne().setOnField(playersOneOnField);
        data.getTeamOne().setReserves(playersOneReserve);

        assertTrue(teamCheck1(data.getTeamOne()));
        assertEquals(data.getTeamOne().getTeamName(), "Test name");

        data.setTeamOne(null);
        assertNull(data.getTeamOne());
    }

    //Check if Unknown type Players are created
    @Test
    public void addTeam3Test() {
        Team testTeam = new Team();
        Player tempPlayer1 = new Player();
        tempPlayer1.resetCount();

        //Complete unknown player
        testTeam.addPlayer(new Player());

        assertEquals(testTeam.getReserves().get(0), null);
        assertNotNull(testTeam.getOnField().get(0));
        if(testTeam.getOnField().get(0) != null)
        {
            assertEquals(testTeam.getOnField().get(0).getPlayerName(), "Unknown(0)");
            assertEquals(testTeam.getOnField().get(0).getJerseyNum(), 0);
            assertEquals(testTeam.getOnField().get(0).getReserve(), false);
        }

        //Player with unknown name
        testTeam.addPlayer(new Player(null, 1, true));
        assertNotNull(testTeam.getReserves().get(0));
        if(testTeam.getReserves().get(0) != null)
        {
            assertEquals(testTeam.getReserves().get(0).getPlayerName(), "Unknown(1)");
            assertEquals(testTeam.getReserves().get(0).getJerseyNum(), 1);
            assertEquals(testTeam.getReserves().get(0).getReserve(), true);
        }

        //Player with jersey number name
        testTeam.addPlayer(new Player("Fake name", -1, true));
        assertNotNull(testTeam.getReserves().get(1));
        if(testTeam.getReserves().get(1) != null)
        {
            assertEquals(testTeam.getReserves().get(1).getPlayerName(), "Fake name");
            assertEquals(testTeam.getReserves().get(1).getJerseyNum(), 0);
            assertEquals(testTeam.getReserves().get(1).getReserve(), true);
        }
    }

    //Check if scoring works: addScore(), getScore()
    @Test
    public void scoringTest() {
        Team testTeam = new Team();

        assertTrue(testTeam.addScore("Try"));
        assertEquals(testTeam.getScore(), 5);

        assertTrue(testTeam.addScore("Conversion Kick"));
        assertEquals(testTeam.getScore(), 7);

        assertTrue(testTeam.addScore("Penalty Kick"));
        assertEquals(testTeam.getScore(), 10);

        assertTrue(testTeam.addScore("Drop Kick"));
        assertEquals(testTeam.getScore(), 13);

        assertFalse(testTeam.addScore("Dummy"));
        assertEquals(testTeam.getScore(), 13);

        Player testPlayer = new Player();

        assertTrue(testPlayer.playerScore("Try"));
        assertEquals(testPlayer.getPlayerScore()[0], 1);

        assertTrue(testPlayer.playerScore("Conversion Kick"));
        assertEquals(testPlayer.getPlayerScore()[1], 1);

        assertTrue(testPlayer.playerScore("Penalty Kick"));
        assertEquals(testPlayer.getPlayerScore()[2], 1);

        assertTrue(testPlayer.playerScore("Drop Kick"));
        assertEquals(testPlayer.getPlayerScore()[3], 1);

        assertFalse(testPlayer.playerScore("Dummy"));
    }

    //Check if scoring works: addScore(), getScore()
    @Test
    public void substitutionTest() {
        Team testTeam = new Team();


        Player temp1 = new Player(null, 1, false);
        Player temp2 = new Player(null, 2, false);
        Player temp3 = new Player(null, 3, true);
        Player temp4 = new Player(null, 4, true);

        testTeam.addPlayer(temp1);
        testTeam.addPlayer(temp2);
        testTeam.addPlayer(temp3);
        testTeam.addPlayer(temp4);

        assertFalse(testTeam.subPlayers(temp1, temp2));
        assertFalse(testTeam.subPlayers(temp3, temp4));
        assertFalse(testTeam.subPlayers(temp1, null));
        assertFalse(testTeam.subPlayers(null, temp1));
        assertFalse(testTeam.subPlayers(null, null));
        assertFalse(testTeam.subPlayers(temp3, temp1));

        assertTrue(testTeam.subPlayers(temp1, temp3));
        assertEquals(testTeam.getOnField().get(0), temp3);
        assertEquals(testTeam.getReserves().get(0), temp1);
    }

    //Check if turnover works
    @Test
    public void turnoverTest() {
        setDefaultTeams();

        data.setFunctionType("Turnover");
        data.setSelectedTeam("teamOne");
        assertTrue(turnoverRuckLineoutTest());
        assertEquals(data.getTeamOne().getTurnoversWon(), 1);
        assertEquals(data.getTeamOne().getTurnoversLost(), 0);
        assertEquals(data.getTeamTwo().getTurnoversWon(), 0);
        assertEquals(data.getTeamTwo().getTurnoversLost(), 1);

        data.setSelectedTeam("teamTwo");
        assertTrue(turnoverRuckLineoutTest());
        assertEquals(data.getTeamOne().getTurnoversWon(), 1);
        assertEquals(data.getTeamOne().getTurnoversLost(), 1);
        assertEquals(data.getTeamTwo().getTurnoversWon(), 1);
        assertEquals(data.getTeamTwo().getTurnoversLost(), 1);

        data.setSelectedTeam("Dummy");
        assertFalse(turnoverRuckLineoutTest());
        assertEquals(data.getTeamOne().getTurnoversWon(), 1);
        assertEquals(data.getTeamOne().getTurnoversLost(), 1);
        assertEquals(data.getTeamTwo().getTurnoversWon(), 1);
        assertEquals(data.getTeamTwo().getTurnoversLost(), 1);

        data.setFunctionType("Dummy");
        assertFalse(turnoverRuckLineoutTest());
    }

    //Check if ruck works
    @Test
    public void ruckTest() {
        setDefaultTeams();

        data.setFunctionType("Ruck");
        data.setSelectedTeam("teamOne");
        assertTrue(turnoverRuckLineoutTest());
        assertEquals(data.getTeamOne().getRucksWon(), 1);
        assertEquals(data.getTeamOne().getRucksLost(), 0);
        assertEquals(data.getTeamTwo().getRucksWon(), 0);
        assertEquals(data.getTeamTwo().getRucksLost(), 1);

        data.setSelectedTeam("teamTwo");
        assertTrue(turnoverRuckLineoutTest());
        assertEquals(data.getTeamOne().getRucksWon(), 1);
        assertEquals(data.getTeamOne().getRucksLost(), 1);
        assertEquals(data.getTeamTwo().getRucksWon(), 1);
        assertEquals(data.getTeamTwo().getRucksLost(), 1);

        data.setSelectedTeam("Dummy");
        assertFalse(turnoverRuckLineoutTest());
        assertEquals(data.getTeamOne().getRucksWon(), 1);
        assertEquals(data.getTeamOne().getRucksLost(), 1);
        assertEquals(data.getTeamTwo().getRucksWon(), 1);
        assertEquals(data.getTeamTwo().getRucksLost(), 1);

        data.setFunctionType("Dummy");
        assertFalse(turnoverRuckLineoutTest());
    }

    //Check if line out works
    @Test
    public void lineOutTest() {
        setDefaultTeams();

        data.setFunctionType("LineOut");
        data.setSelectedTeam("teamOne");
        assertTrue(turnoverRuckLineoutTest());
        assertTrue(turnoverRuckLineoutTest());
        assertEquals(data.getTeamOne().getOwnLineOutsWon(), 1);
        assertEquals(data.getTeamOne().getOwnLineOutsLost(), 0);
        assertEquals(data.getTeamOne().getOpponentLineOutsWon(), 0);
        assertEquals(data.getTeamOne().getOpponentLineOutsLost(), 0);
        assertEquals(data.getTeamTwo().getOwnLineOutsWon(), 0);
        assertEquals(data.getTeamTwo().getOwnLineOutsLost(), 0);
        assertEquals(data.getTeamTwo().getOpponentLineOutsWon(), 0);
        assertEquals(data.getTeamTwo().getOpponentLineOutsLost(), 1);

        data.setFunctionType("LineOut");
        data.setSelectedTeam("teamOne");
        assertTrue(turnoverRuckLineoutTest());
        data.setSelectedTeam("teamTwo");
        assertTrue(turnoverRuckLineoutTest());
        assertEquals(data.getTeamOne().getOwnLineOutsWon(), 1);
        assertEquals(data.getTeamOne().getOwnLineOutsLost(), 1);
        assertEquals(data.getTeamOne().getOpponentLineOutsWon(), 0);
        assertEquals(data.getTeamOne().getOpponentLineOutsLost(), 0);
        assertEquals(data.getTeamTwo().getOwnLineOutsWon(), 0);
        assertEquals(data.getTeamTwo().getOwnLineOutsLost(), 0);
        assertEquals(data.getTeamTwo().getOpponentLineOutsWon(), 1);
        assertEquals(data.getTeamTwo().getOpponentLineOutsLost(), 1);

        data.setFunctionType("LineOut");
        data.setSelectedTeam("teamTwo");
        assertTrue(turnoverRuckLineoutTest());
        assertTrue(turnoverRuckLineoutTest());
        assertEquals(data.getTeamOne().getOwnLineOutsWon(), 1);
        assertEquals(data.getTeamOne().getOwnLineOutsLost(), 1);
        assertEquals(data.getTeamOne().getOpponentLineOutsWon(), 0);
        assertEquals(data.getTeamOne().getOpponentLineOutsLost(), 1);
        assertEquals(data.getTeamTwo().getOwnLineOutsWon(), 1);
        assertEquals(data.getTeamTwo().getOwnLineOutsLost(), 0);
        assertEquals(data.getTeamTwo().getOpponentLineOutsWon(), 1);
        assertEquals(data.getTeamTwo().getOpponentLineOutsLost(), 1);

        data.setFunctionType("LineOut");
        data.setSelectedTeam("teamTwo");
        assertTrue(turnoverRuckLineoutTest());
        data.setSelectedTeam("teamOne");
        assertTrue(turnoverRuckLineoutTest());
        assertEquals(data.getTeamOne().getOwnLineOutsWon(), 1);
        assertEquals(data.getTeamOne().getOwnLineOutsLost(), 1);
        assertEquals(data.getTeamOne().getOpponentLineOutsWon(), 1);
        assertEquals(data.getTeamOne().getOpponentLineOutsLost(), 1);
        assertEquals(data.getTeamTwo().getOwnLineOutsWon(), 1);
        assertEquals(data.getTeamTwo().getOwnLineOutsLost(), 1);
        assertEquals(data.getTeamTwo().getOpponentLineOutsWon(), 1);
        assertEquals(data.getTeamTwo().getOpponentLineOutsLost(), 1);

        data.setSelectedTeam("Dummy");
        assertFalse(turnoverRuckLineoutTest());
        assertTrue(turnoverRuckLineoutTest());
        assertEquals(data.getTeamOne().getOwnLineOutsWon(), 1);
        assertEquals(data.getTeamOne().getOwnLineOutsLost(), 1);
        assertEquals(data.getTeamOne().getOpponentLineOutsWon(), 1);
        assertEquals(data.getTeamOne().getOpponentLineOutsLost(), 1);
        assertEquals(data.getTeamTwo().getOwnLineOutsWon(), 1);
        assertEquals(data.getTeamTwo().getOwnLineOutsLost(), 1);
        assertEquals(data.getTeamTwo().getOpponentLineOutsWon(), 1);
        assertEquals(data.getTeamTwo().getOpponentLineOutsLost(), 1);

        data.setFunctionType("Dummy");
        assertFalse(turnoverRuckLineoutTest());
    }

    //Helper functions:
    public boolean teamCheck1(Team t)
    {
        boolean check = true;

        if (!(t.getOnField().get(0).getJerseyNum() == 1 && t.getOnField().get(0).getPlayerName() == "John One" && t.getOnField().get(0).getReserve() == false))
            check = false;
        if (!(t.getOnField().get(1).getJerseyNum() == 2 && t.getOnField().get(0).getPlayerName() == "John Two" && t.getOnField().get(0).getReserve() == false))
            check = false;
        if (!(t.getOnField().get(2).getJerseyNum() == 3 && t.getOnField().get(0).getPlayerName() == "John Three" && t.getOnField().get(0).getReserve() == false))
            check = false;
        if (!(t.getOnField().get(3).getJerseyNum() == 4 && t.getOnField().get(0).getPlayerName() == "John Four" && t.getOnField().get(0).getReserve() == false))
            check = false;
        if (!(t.getOnField().get(4).getJerseyNum() == 5 && t.getOnField().get(0).getPlayerName() == "John Five" && t.getOnField().get(0).getReserve() == false))
            check = false;
        if (!(t.getOnField().get(5).getJerseyNum() == 6 && t.getOnField().get(0).getPlayerName() == "John Six" && t.getOnField().get(0).getReserve() == false))
            check = false;
        if (!(t.getOnField().get(6).getJerseyNum() == 7 && t.getOnField().get(0).getPlayerName() == "John Seven" && t.getOnField().get(0).getReserve() == false))
            check = false;
        if (!(t.getOnField().get(7).getJerseyNum() == 8 && t.getOnField().get(0).getPlayerName() == "John Eight" && t.getOnField().get(0).getReserve() == false))
            check = false;
        if (!(t.getOnField().get(8).getJerseyNum() == 9 && t.getOnField().get(0).getPlayerName() == "John Nine" && t.getOnField().get(0).getReserve() == false))
            check = false;
        if (!(t.getOnField().get(9).getJerseyNum() == 10 && t.getOnField().get(0).getPlayerName() == "John Ten" && t.getOnField().get(0).getReserve() == false))
            check = false;
        if (!(t.getOnField().get(10).getJerseyNum() == 11 && t.getOnField().get(0).getPlayerName() == "John Eleven" && t.getOnField().get(0).getReserve() == false))
            check = false;
        if (!(t.getOnField().get(11).getJerseyNum() == 12 && t.getOnField().get(0).getPlayerName() == "John Twelve" && t.getOnField().get(0).getReserve() == false))
            check = false;
        if (!(t.getOnField().get(12).getJerseyNum() == 13 && t.getOnField().get(0).getPlayerName() == "John Thirteen" && t.getOnField().get(0).getReserve() == false))
            check = false;
        if (!(t.getOnField().get(13).getJerseyNum() == 14 && t.getOnField().get(0).getPlayerName() == "John Fourteen" && t.getOnField().get(0).getReserve() == false))
            check = false;
        if (!(t.getOnField().get(14).getJerseyNum() == 15 && t.getOnField().get(0).getPlayerName() == "John Fifteen" && t.getOnField().get(0).getReserve() == false))
            check = false;
        if (!(t.getOnField().get(15).getJerseyNum() == 16 && t.getOnField().get(0).getPlayerName() == "John Sixteen" && t.getOnField().get(0).getReserve() == true))
            check = false;
        if (!(t.getOnField().get(16).getJerseyNum() == 17 && t.getOnField().get(0).getPlayerName() == "John Seventeen" && t.getOnField().get(0).getReserve() == true))
            check = false;
        if (!(t.getOnField().get(17).getJerseyNum() == 18 && t.getOnField().get(0).getPlayerName() == "John Eighteen" && t.getOnField().get(0).getReserve() == true))
            check = false;
        if (!(t.getOnField().get(18).getJerseyNum() == 19 && t.getOnField().get(0).getPlayerName() == "John Nineteen" && t.getOnField().get(0).getReserve() == true))
            check = false;
        if (!(t.getOnField().get(19).getJerseyNum() == 20 && t.getOnField().get(0).getPlayerName() == "John Twenty" && t.getOnField().get(0).getReserve() == true))
            check = false;
        if (!(t.getOnField().get(20).getJerseyNum() == 21 && t.getOnField().get(0).getPlayerName() == "John TwentyOne" && t.getOnField().get(0).getReserve() == true))
            check = false;
        if (!(t.getOnField().get(21).getJerseyNum() == 22 && t.getOnField().get(0).getPlayerName() == "John TwentyTwo" && t.getOnField().get(0).getReserve() == true))
            check = false;

        return check;
    }

    public boolean turnoverRuckLineoutTest()
    {
        if (data.getFunctionType().equals("Turnover"))
        {
            if (data.getSelectedTeam() == data.getTeamOne())
            {
                data.getTeamOne().incrementTurnoversWon();
                data.getTeamTwo().incrementTurnoversLost();
                return true;
            }
            else if (data.getSelectedTeam() == data.getTeamTwo())
            {
                data.getTeamTwo().incrementTurnoversWon();
                data.getTeamOne().incrementTurnoversLost();
                return true;
            }
            return false;
        }
        else if (data.getFunctionType().equals("Ruck"))
        {
            if (data.getSelectedTeam() == data.getTeamOne())
            {
                data.getTeamOne().incrementRucksWon();
                data.getTeamTwo().incrementRucksLost();
                return true;
            }
            else if (data.getSelectedTeam() == data.getTeamTwo())
            {
                data.getTeamTwo().incrementRucksWon();
                data.getTeamOne().incrementRucksLost();
                return true;
            }
            return false;
        }
        else if (data.getFunctionType().equals("LineOut"))
        {
            if(!data.getLineOutTeam())
            {
                data.setLineOutTeam(true);
                data.getSelectedTeam().setLineOut(true);
                return true;
            }
            else
            {
                if (data.getSelectedTeam() == data.getTeamOne())
                {
                    if(data.getTeamOne().getLineOut())
                    {
                        data.getTeamOne().incrementOwnLineOutsWon();
                        data.getTeamTwo().incrementOpponentLineOutsLost();
                        return true;
                    }
                    else
                    {
                        data.getTeamOne().incrementOpponentLineOutsWon();;
                        data.getTeamTwo().incrementOwnLineOutsLost();
                        return true;
                    }
                }
                else if (data.getSelectedTeam() == data.getTeamTwo())
                {
                    if(data.getTeamOne().getLineOut())
                    {
                        data.getTeamOne().incrementOwnLineOutsLost();
                        data.getTeamTwo().incrementOpponentLineOutsWon();
                        return true;
                    }
                    else
                    {
                        data.getTeamOne().incrementOpponentLineOutsLost();;
                        data.getTeamTwo().incrementOwnLineOutsWon();
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

    public void setDefaultTeams()
    {
        data.setTeamOne(new Team("Team A"));
        data.setTeamTwo(new Team("Team B"));

        //Team One's on field players
        data.getTeamOne().addPlayer(new Player("TeamA, Player One", 1, false));
        data.getTeamOne().addPlayer(new Player("TeamA, Player Two", 2, false));
        data.getTeamOne().addPlayer(new Player("TeamA, Player Three", 3, false));
        data.getTeamOne().addPlayer(new Player("TeamA, Player Four", 4, false));
        data.getTeamOne().addPlayer(new Player("TeamA, Player Five", 5, false));
        data.getTeamOne().addPlayer(new Player("TeamA, Player Six", 6, false));
        data.getTeamOne().addPlayer(new Player("TeamA, Player Seven", 7, false));
        data.getTeamOne().addPlayer(new Player("TeamA, Player Eight", 8, false));
        data.getTeamOne().addPlayer(new Player("TeamA, Player Nine", 9, false));
        data.getTeamOne().addPlayer(new Player("TeamA, Player Ten", 10, false));
        data.getTeamOne().addPlayer(new Player("TeamA, Player Eleven", 11, false));
        data.getTeamOne().addPlayer(new Player("TeamA, Player Twelve", 12, false));
        data.getTeamOne().addPlayer(new Player("TeamA, Player Thirteen", 13, false));
        data.getTeamOne().addPlayer(new Player("TeamA, Player Fourteen", 14, false));
        data.getTeamOne().addPlayer(new Player("TeamA, Player Fifteen", 15, false));

        //Team One's reserve players
        data.getTeamOne().addPlayer(new Player("TeamA, Player Sixteen", 16, true));
        data.getTeamOne().addPlayer(new Player("TeamA, Player Seventeen", 17, true));
        data.getTeamOne().addPlayer(new Player("TeamA, Player Eighteen", 18, true));
        data.getTeamOne().addPlayer(new Player("TeamA, Player Nineteen", 19, true));
        data.getTeamOne().addPlayer(new Player("TeamA, Player Twenty", 20, true));
        data.getTeamOne().addPlayer(new Player("TeamA, Player TwentyOne", 21, true));
        data.getTeamOne().addPlayer(new Player("TeamA, Player TwentyTwo", 22, true));

        //Team Two's on field players
        data.getTeamTwo().addPlayer(new Player("TeamB, Player One", 1, false));
        data.getTeamTwo().addPlayer(new Player("TeamB, Player Two", 2, false));
        data.getTeamTwo().addPlayer(new Player("TeamB, Player Three", 3, false));
        data.getTeamTwo().addPlayer(new Player("TeamB, Player Four", 4, false));
        data.getTeamTwo().addPlayer(new Player("TeamB, Player Five", 5, false));
        data.getTeamTwo().addPlayer(new Player("TeamB, Player Six", 6, false));
        data.getTeamTwo().addPlayer(new Player("TeamB, Player Seven", 7, false));
        data.getTeamTwo().addPlayer(new Player("TeamB, Player Eight", 8, false));
        data.getTeamTwo().addPlayer(new Player("TeamB, Player Nine", 9, false));
        data.getTeamTwo().addPlayer(new Player("TeamB, Player Ten", 10, false));
        data.getTeamTwo().addPlayer(new Player("TeamB, Player Eleven", 11, false));
        data.getTeamTwo().addPlayer(new Player("TeamB, Player Twelve", 12, false));
        data.getTeamTwo().addPlayer(new Player("TeamB, Player Thirteen", 13, false));
        data.getTeamTwo().addPlayer(new Player("TeamB, Player Fourteen", 14, false));
        data.getTeamTwo().addPlayer(new Player("TeamB, Player Fifteen", 15, false));

        //Team Two's reserve players
        data.getTeamTwo().addPlayer(new Player("TeamB, Player Sixteen", 16, true));
        data.getTeamTwo().addPlayer(new Player("TeamB, Player Seventeen", 17, true));
        data.getTeamTwo().addPlayer(new Player("TeamB, Player Eighteen", 18, true));
        data.getTeamTwo().addPlayer(new Player("TeamB, Player Nineteen", 19, true));
        data.getTeamTwo().addPlayer(new Player("TeamB, Player Twenty", 20, true));
        data.getTeamTwo().addPlayer(new Player("TeamB, Player TwentyOne", 21, true));
        data.getTeamTwo().addPlayer(new Player("TeamB, Player TwentyTwo", 22, true));
    }
}