package com.example.herman.or_demo_2_withscoringandsubs;

import com.example.herman.or_demo_2_withscoringandsubs.Info.Data;
import com.example.herman.or_demo_2_withscoringandsubs.Info.Player;
import com.example.herman.or_demo_2_withscoringandsubs.Info.Team;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Muller on 28/08/2015.
 */
public class IndexTest {
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

    //Test case to add normal players using data.setTeamOne(), Team.setPlayers() and Team.setReserve()
    //Also test data's teamOne related functions: Data.getTeamOne(), Data.setTeamOne()
    @Test
    public void addTeam2Test() {
        Team testTeam = new Team("Test name");
        //data.setTeamOne();

        ArrayList<Player> playersOne = new ArrayList<Player>();

        //Team One's on field players
        playersOne.add(new Player("John One", 1, false));
        playersOne.add(new Player("John Two", 2, false));
        playersOne.add(new Player("John Three", 3, false));
        playersOne.add(new Player("John Four", 4, false));
        playersOne.add(new Player("John Five", 5, false));
        playersOne.add(new Player("John Six", 6, false));
        playersOne.add(new Player("John Seven", 7, false));
        playersOne.add(new Player("John Eight", 8, false));
        playersOne.add(new Player("John Nine", 9, false));
        playersOne.add(new Player("John Ten", 10, false));
        playersOne.add(new Player("John Eleven", 11, false));
        playersOne.add(new Player("John Twelve", 12, false));
        playersOne.add(new Player("John Thirteen", 13, false));
        playersOne.add(new Player("John Fourteen", 14, false));
        playersOne.add(new Player("John Fifteen", 15, false));

        //Team One's reserve players
        playersOne.add(new Player("John Sixteen", 16, true));
        playersOne.add(new Player("John Seventeen", 17, true));
        playersOne.add(new Player("John Eighteen", 18, true));
        playersOne.add(new Player("John Nineteen", 19, true));
        playersOne.add(new Player("John Twenty", 20, true));
        playersOne.add(new Player("John TwentyOne", 21, true));
        playersOne.add(new Player("John TwentyTwo", 22, true));

        data.setTeamOne(testTeam);
        data.getTeamOne().setPlayers(playersOne);

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

        //assertEquals(testTeam.getPlayers(), new ArrayList<>(7));
        assertNotNull(testTeam.getPlayers().get(0));
        if(testTeam.getPlayers().get(0) != null)
        {
            assertEquals(testTeam.getPlayers().get(0).getPlayerName(), "Unknown(0)");
            assertEquals(testTeam.getPlayers().get(0).getCurr_position(), 0);
            assertEquals(testTeam.getPlayers().get(0).getReserve(), false);
        }

        //Player with unknown name
        testTeam.addPlayer(new Player(null, 1, true));
        assertNotNull(testTeam.getPlayers().get(0));
        if(testTeam.getPlayers().get(0) != null)
        {
            assertEquals(testTeam.getPlayers().get(0).getPlayerName(), "Unknown(1)");
            assertEquals(testTeam.getPlayers().get(0).getCurr_position(), 1);
            assertEquals(testTeam.getPlayers().get(0).getReserve(), true);
        }

        //Player with jersey number name
        testTeam.addPlayer(new Player("Fake name", -1, true));
        assertNotNull(testTeam.getPlayers().get(1));
        if(testTeam.getPlayers().get(1) != null)
        {
            assertEquals(testTeam.getPlayers().get(1).getPlayerName(), "Fake name");
            assertEquals(testTeam.getPlayers().get(1).getCurr_position(), 0);
            assertEquals(testTeam.getPlayers().get(1).getReserve(), true);
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

        /*assertFalse(testTeam.subPlayers(temp1, temp2));
        assertFalse(testTeam.subPlayers(temp3, temp4));
        assertFalse(testTeam.subPlayers(temp1, null));
        assertFalse(testTeam.subPlayers(null, temp1));
        assertFalse(testTeam.subPlayers(null, null));
        assertFalse(testTeam.subPlayers(temp3, temp1));

        assertTrue(testTeam.subPlayers(temp1, temp3));*/
        assertNotEquals(testTeam.getPlayers().get(0), temp3);
        assertEquals(testTeam.getPlayers().get(0), temp1);
    }

    //Check if turnover works
    @Test
    public void turnoverTest() {
        setDefaultTeams();

        data.setFunctionType("Turnover");
        data.setSelectedTeam("teamOne");
        assertTrue(turnoverScrumLineoutTest());
        assertEquals(data.getTeamOne().getTurnoversWon(), 1);
        assertEquals(data.getTeamOne().getTurnoversLost(), 0);
        assertEquals(data.getTeamTwo().getTurnoversWon(), 0);
        assertEquals(data.getTeamTwo().getTurnoversLost(), 1);

        data.setSelectedTeam("teamTwo");
        assertTrue(turnoverScrumLineoutTest());
        assertEquals(data.getTeamOne().getTurnoversWon(), 1);
        assertEquals(data.getTeamOne().getTurnoversLost(), 1);
        assertEquals(data.getTeamTwo().getTurnoversWon(), 1);
        assertEquals(data.getTeamTwo().getTurnoversLost(), 1);

        data.setSelectedTeam("Dummy");
        assertFalse(turnoverScrumLineoutTest());
        assertEquals(data.getTeamOne().getTurnoversWon(), 1);
        assertEquals(data.getTeamOne().getTurnoversLost(), 1);
        assertEquals(data.getTeamTwo().getTurnoversWon(), 1);
        assertEquals(data.getTeamTwo().getTurnoversLost(), 1);

        data.setFunctionType("Dummy");
        assertFalse(turnoverScrumLineoutTest());
    }

    //Check if Scrum works
    @Test
    public void ScrumTest() {
        setDefaultTeams();

        data.setFunctionType("Scrum");
        data.setSelectedTeam("teamOne");
        assertTrue(turnoverScrumLineoutTest());
        assertEquals(data.getTeamOne().getScrumsWon(), 1);
        assertEquals(data.getTeamOne().getScrumsLost(), 0);
        assertEquals(data.getTeamTwo().getScrumsWon(), 0);
        assertEquals(data.getTeamTwo().getScrumsLost(), 1);

        data.setSelectedTeam("teamTwo");
        assertTrue(turnoverScrumLineoutTest());
        assertEquals(data.getTeamOne().getScrumsWon(), 1);
        assertEquals(data.getTeamOne().getScrumsLost(), 1);
        assertEquals(data.getTeamTwo().getScrumsWon(), 1);
        assertEquals(data.getTeamTwo().getScrumsLost(), 1);

        data.setSelectedTeam("Dummy");
        assertFalse(turnoverScrumLineoutTest());
        assertEquals(data.getTeamOne().getScrumsWon(), 1);
        assertEquals(data.getTeamOne().getScrumsLost(), 1);
        assertEquals(data.getTeamTwo().getScrumsWon(), 1);
        assertEquals(data.getTeamTwo().getScrumsLost(), 1);

        data.setFunctionType("Dummy");
        assertFalse(turnoverScrumLineoutTest());
    }

    //Check if line out works
    @Test
    public void lineOutTest() {
        setDefaultTeams();

        data.setFunctionType("LineOut");
        data.setSelectedTeam("teamOne");
        assertTrue(turnoverScrumLineoutTest());
        assertTrue(turnoverScrumLineoutTest());
        assertEquals(data.getTeamOne().getOwnLineOutsWon(), 1);
        assertEquals(data.getTeamOne().getOwnLineOutsLost(), 0);
        assertEquals(data.getTeamOne().getOpponentLineOutsWon(), 0);
        assertEquals(data.getTeamOne().getOpponentLineOutsLost(), 0);
        assertEquals(data.getTeamTwo().getOwnLineOutsWon(), 0);
        assertEquals(data.getTeamTwo().getOwnLineOutsLost(), 0);
        assertEquals(data.getTeamTwo().getOpponentLineOutsWon(), 0);
        assertEquals(data.getTeamTwo().getOpponentLineOutsLost(), 1);
        data.setLineOutTeam(false);

        data.setFunctionType("LineOut");
        data.setSelectedTeam("teamOne");
        assertTrue(turnoverScrumLineoutTest());
        data.setSelectedTeam("teamTwo");
        assertTrue(turnoverScrumLineoutTest());
        assertEquals(data.getTeamOne().getOwnLineOutsWon(), 1);
        assertEquals(data.getTeamOne().getOwnLineOutsLost(), 1);
        assertEquals(data.getTeamOne().getOpponentLineOutsWon(), 0);
        assertEquals(data.getTeamOne().getOpponentLineOutsLost(), 0);
        assertEquals(data.getTeamTwo().getOwnLineOutsWon(), 0);
        assertEquals(data.getTeamTwo().getOwnLineOutsLost(), 0);
        assertEquals(data.getTeamTwo().getOpponentLineOutsWon(), 1);
        assertEquals(data.getTeamTwo().getOpponentLineOutsLost(), 1);
        data.setLineOutTeam(false);

        data.setFunctionType("LineOut");
        data.setSelectedTeam("teamTwo");
        assertTrue(turnoverScrumLineoutTest());
        //assertEquals(data.getTeamOne().getOwnLineOutsLost(), 1);
        //assertTrue(turnoverScrumLineoutTest());
        assertEquals(data.getTeamOne().getOwnLineOutsWon(), 1);
        assertEquals(data.getTeamOne().getOwnLineOutsLost(), 1);
        assertEquals(data.getTeamOne().getOpponentLineOutsWon(), 0);
        //assertEquals(data.getTeamOne().getOpponentLineOutsLost(), 1);
        //assertEquals(data.getTeamTwo().getOwnLineOutsWon(), 1);
        assertEquals(data.getTeamTwo().getOwnLineOutsLost(), 0);
        assertEquals(data.getTeamTwo().getOpponentLineOutsWon(), 1);
        assertEquals(data.getTeamTwo().getOpponentLineOutsLost(), 1);
        data.setLineOutTeam(false);

        data.setFunctionType("LineOut");
        data.setSelectedTeam("teamTwo");
        assertTrue(turnoverScrumLineoutTest());
        data.setSelectedTeam("teamOne");
        assertTrue(turnoverScrumLineoutTest());
        //assertEquals(data.getTeamOne().getOwnLineOutsWon(), 1);
        assertEquals(data.getTeamOne().getOwnLineOutsLost(), 1);
        //assertEquals(data.getTeamOne().getOpponentLineOutsWon(), 1);
        //assertEquals(data.getTeamOne().getOpponentLineOutsLost(), 1);
        //assertEquals(data.getTeamTwo().getOwnLineOutsWon(), 1);
        //assertEquals(data.getTeamTwo().getOwnLineOutsLost(), 1);
        assertEquals(data.getTeamTwo().getOpponentLineOutsWon(), 1);
        //assertEquals(data.getTeamTwo().getOpponentLineOutsLost(), 1);
        data.setLineOutTeam(false);

        data.setSelectedTeam("Dummy");
        assertFalse(turnoverScrumLineoutTest());
        //assertTrue(turnoverScrumLineoutTest());
        //assertEquals(data.getTeamOne().getOwnLineOutsWon(), 1);
        assertEquals(data.getTeamOne().getOwnLineOutsLost(), 1);
        //assertEquals(data.getTeamOne().getOpponentLineOutsWon(), 1);
        //assertEquals(data.getTeamOne().getOpponentLineOutsLost(), 1);
        //assertEquals(data.getTeamTwo().getOwnLineOutsWon(), 1);
        //assertEquals(data.getTeamTwo().getOwnLineOutsLost(), 1);
        //assertEquals(data.getTeamTwo().getOpponentLineOutsWon(), 1);
        //assertEquals(data.getTeamTwo().getOpponentLineOutsLost(), 1);
        data.setLineOutTeam(false);

        data.setFunctionType("Dummy");
        assertFalse(turnoverScrumLineoutTest());
    }

    //Helper functions:
    public boolean teamCheck1(Team t)
    {
        boolean check = true;

        if (t.getPlayers().size() > 0 && !(t.getPlayers().get(0).getCurr_position() == 1 && t.getPlayers().get(0).getPlayerName() == "John One" && t.getPlayers().get(0).getReserve() == false))
            check = false;
        if (t.getPlayers().size() > 1 && !(t.getPlayers().get(1).getCurr_position() == 2 && t.getPlayers().get(1).getPlayerName() == "John Two" && t.getPlayers().get(1).getReserve() == false))
            check = false;
        if (t.getPlayers().size() > 2 && !(t.getPlayers().get(2).getCurr_position() == 3 && t.getPlayers().get(2).getPlayerName() == "John Three" && t.getPlayers().get(2).getReserve() == false))
            check = false;
        if (t.getPlayers().size() > 3 && !(t.getPlayers().get(3).getCurr_position() == 4 && t.getPlayers().get(3).getPlayerName() == "John Four" && t.getPlayers().get(3).getReserve() == false))
            check = false;
        if (t.getPlayers().size() > 4 && !(t.getPlayers().get(4).getCurr_position() == 5 && t.getPlayers().get(4).getPlayerName() == "John Five" && t.getPlayers().get(4).getReserve() == false))
            check = false;
        if (t.getPlayers().size() > 5 && !(t.getPlayers().get(5).getCurr_position() == 6 && t.getPlayers().get(5).getPlayerName() == "John Six" && t.getPlayers().get(5).getReserve() == false))
            check = false;
        if (t.getPlayers().size() > 6 && !(t.getPlayers().get(6).getCurr_position() == 7 && t.getPlayers().get(6).getPlayerName() == "John Seven" && t.getPlayers().get(6).getReserve() == false))
            check = false;
        if (t.getPlayers().size() > 7 && !(t.getPlayers().get(7).getCurr_position() == 8 && t.getPlayers().get(7).getPlayerName() == "John Eight" && t.getPlayers().get(7).getReserve() == false))
            check = false;
        if (t.getPlayers().size() > 8 && !(t.getPlayers().get(8).getCurr_position() == 9 && t.getPlayers().get(8).getPlayerName() == "John Nine" && t.getPlayers().get(8).getReserve() == false))
            check = false;
        if (t.getPlayers().size() > 9 && !(t.getPlayers().get(9).getCurr_position() == 10 && t.getPlayers().get(9).getPlayerName() == "John Ten" && t.getPlayers().get(9).getReserve() == false))
            check = false;
        if (t.getPlayers().size() > 10 && !(t.getPlayers().get(10).getCurr_position() == 11 && t.getPlayers().get(10).getPlayerName() == "John Eleven" && t.getPlayers().get(10).getReserve() == false))
            check = false;
        if (t.getPlayers().size() > 11 && !(t.getPlayers().get(11).getCurr_position() == 12 && t.getPlayers().get(11).getPlayerName() == "John Twelve" && t.getPlayers().get(11).getReserve() == false))
            check = false;
        if (t.getPlayers().size() > 12 && !(t.getPlayers().get(12).getCurr_position() == 13 && t.getPlayers().get(12).getPlayerName() == "John Thirteen" && t.getPlayers().get(12).getReserve() == false))
            check = false;
        if (t.getPlayers().size() > 13 && !(t.getPlayers().get(13).getCurr_position() == 14 && t.getPlayers().get(13).getPlayerName() == "John Fourteen" && t.getPlayers().get(13).getReserve() == false))
            check = false;
        if (t.getPlayers().size() > 14 && !(t.getPlayers().get(14).getCurr_position() == 15 && t.getPlayers().get(14).getPlayerName() == "John Fifteen" && t.getPlayers().get(14).getReserve() == false))
            check = false;
        if (t.getPlayers().size() > 15 && !(t.getPlayers().get(15).getCurr_position() == 16 && t.getPlayers().get(15).getPlayerName() == "John Sixteen" && t.getPlayers().get(15).getReserve() == true))
            check = false;
        if (t.getPlayers().size() > 16 && !(t.getPlayers().get(16).getCurr_position() == 17 && t.getPlayers().get(16).getPlayerName() == "John Seventeen" && t.getPlayers().get(16).getReserve() == true))
            check = false;
        if (t.getPlayers().size() > 17 && !(t.getPlayers().get(17).getCurr_position() == 18 && t.getPlayers().get(17).getPlayerName() == "John Eighteen" && t.getPlayers().get(17).getReserve() == true))
            check = false;
        if (t.getPlayers().size() > 18 && !(t.getPlayers().get(18).getCurr_position() == 19 && t.getPlayers().get(18).getPlayerName() == "John Nineteen" && t.getPlayers().get(18).getReserve() == true))
            check = false;
        if (t.getPlayers().size() > 19 && !(t.getPlayers().get(19).getCurr_position() == 20 && t.getPlayers().get(19).getPlayerName() == "John Twenty" && t.getPlayers().get(19).getReserve() == true))
            check = false;
        if (t.getPlayers().size() > 20 && !(t.getPlayers().get(20).getCurr_position() == 21 && t.getPlayers().get(20).getPlayerName() == "John TwentyOne" && t.getPlayers().get(20).getReserve() == true))
            check = false;
        if (t.getPlayers().size() > 21 && !(t.getPlayers().get(21).getCurr_position() == 22 && t.getPlayers().get(21).getPlayerName() == "John TwentyTwo" && t.getPlayers().get(21).getReserve() == true))
            check = false;

        return check;
    }

    public boolean turnoverScrumLineoutTest()
    {
        if(data.getSelectedTeam() != data.getTeamOne() && data.getSelectedTeam() != data.getTeamTwo())
            return false;

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
        else if (data.getFunctionType().equals("Scrum"))
        {
            if (data.getSelectedTeam() == data.getTeamOne())
            {
                data.getTeamOne().incrementScrumsWon();
                data.getTeamTwo().incrementScrumsLost();
                return true;
            }
            else if (data.getSelectedTeam() == data.getTeamTwo())
            {
                data.getTeamTwo().incrementScrumsWon();
                data.getTeamOne().incrementScrumsLost();
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