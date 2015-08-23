package com.example.herman.hermanfunctions;

import android.test.AndroidTestCase;

import com.example.herman.hermanfunctions.Info.Data;
import com.example.herman.hermanfunctions.Info.Player;
import com.example.herman.hermanfunctions.Info.Team;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Herman on 2015-08-23.
 */
public class HermanFunctionsTests extends TestCase {

    private Data data = Data.getInstance();
    //Test case to add normal players using Team.addPlayer()
    @Test
    public void addTeam1() {
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
    public void addTeam2() {
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
    public void addTeam3() {
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
}
