package com.example.herman.or_demo_2_withscoringandsubs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.onlyrugbyDemo2.R;
import com.example.herman.or_demo_2_withscoringandsubs.Info.Data;
import com.example.herman.or_demo_2_withscoringandsubs.Info.Player;
import com.example.herman.or_demo_2_withscoringandsubs.Info.Team;

/**
 * Created by Muller on 27/08/2015.
 */
public class MainMenu extends Activity implements View.OnClickListener {
    //A handle to the singleton calss Data
    private Data data = Data.getInstance();


    private Chronometer mChronometer;

    private GameClock clock;
    private boolean firstHalf = true;
    private boolean endOfGame = false;
    private boolean started = false;

    Activity activity = this;
    private String endOfFirstHalf, startOfSecondHalf, endOfSecondHalf;

    private static boolean backPressedTwice = false;

    public static boolean refreshDisplay = true;

    private Button tryBtn;
    private Button penaltyBtn;
    private Button dropBtn;
    private Button lineoutBtn;
    private Button scrumBtn;
    private Button substituteBtn;
    private Button disciplineBtn;
    private Button turnoverBtn;
    private Button listBtn;
    //private Button helpBtn;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_logger);

        Intent i = new Intent(this, MainMenu.class);
        data.setMainIntent(i);

        registerForContextMenu(findViewById(R.id.chronometer));

        clock = new GameClock ((Chronometer) findViewById(R.id.chronometer));
        clock.chronometer.setOnClickListener(this);

        initialise();

        TextView scoreTeamOne = (TextView) findViewById(R.id.team1);
        TextView scoreTeamTwo = (TextView) findViewById(R.id.team2);

        scoreTeamOne.setText(String.valueOf(data.getTeamOne().getScore()));
        scoreTeamTwo.setText(String.valueOf(data.getTeamTwo().getScore()));

        TextView nameTeamOne = (TextView) findViewById(R.id.teamOneLbl);
        TextView nameTeamTwo = (TextView) findViewById(R.id.teamTwoLbl);

        nameTeamOne.setText(String.valueOf(data.getTeamOne().getTeamName()));
        nameTeamTwo.setText(String.valueOf(data.getTeamTwo().getTeamName()));

        tryBtn = (Button) findViewById(R.id.tryBtn);
        penaltyBtn = (Button) findViewById(R.id.penaltyBtn);
        dropBtn = (Button) findViewById(R.id.dropBtn);
        lineoutBtn = (Button) findViewById(R.id.lineoutBtn);
        scrumBtn = (Button) findViewById(R.id.scrumBtn);
        substituteBtn = (Button) findViewById(R.id.substituteBtn);
        disciplineBtn = (Button) findViewById(R.id.disciplineBtn);
        turnoverBtn = (Button) findViewById(R.id.turnoverButton);
        listBtn = (Button) findViewById(R.id.listButton);
        //helpBtn = (Button) findViewById(R.id.helpBtn);

        tryBtn.setEnabled(false);
        penaltyBtn.setEnabled(false);
        dropBtn.setEnabled(false);
        lineoutBtn.setEnabled(false);
        scrumBtn.setEnabled(false);
        substituteBtn.setEnabled(false);
        disciplineBtn.setEnabled(false);
        turnoverBtn.setEnabled(false);
        listBtn.setEnabled(false);
        //helpBtn.setEnabled(false);
    }

    @Override
    public void onClick(View view) {
        String chronoText = clock.chronometer.getText().toString();
        String array[] = chronoText.split(":");
        int stoppedMilliseconds = 0;

        Intent intent = new Intent(new Intent(MainMenu.this, TeamSelect.class));

        switch(view.getId()) {
            case R.id.tryBtn:
                data.setFunctionType("Score");
                data.setSelectedScoreType("Try");
                startActivity(intent);
                break;
            case R.id.penaltyBtn:
                data.setFunctionType("Score");
                data.setSelectedScoreType("Penalty Kick");
                startActivity(intent);
                break;
            case R.id.dropBtn:
                data.setFunctionType("Score");
                data.setSelectedScoreType("Drop Kick");
                startActivity(intent);
                break;
            case R.id.substituteBtn:
                data.setFunctionType("Substitution");
                startActivity(intent);
                break;
            case R.id.disciplineBtn:
                data.setFunctionType("Discipline");
                startActivity(intent);
                break;
            case R.id.turnoverButton:
                data.setFunctionType("Turnover");
                startActivity(intent);
                break;
            case R.id.scrumBtn:
                data.setFunctionType("Scrum");
                startActivity(intent);
                break;
            case R.id.lineoutBtn:
                data.setFunctionType("LineOut");
                startActivity(intent);
                break;
            case R.id.helpButton:
                data.setFunctionType("Help");
                Intent intent1 = new Intent(new Intent(MainMenu.this, helpList.class));
                startActivity(intent1);
                break;
            case R.id.listButton:
                data.setFunctionType("EventList");
                listBtn.setText("Pressed");
                Intent intent2 = new Intent(new Intent(MainMenu.this, eventsList.class));
                startActivity(intent2);
                break;
            case R.id.chronometer:
                if (array.length == 2)
                {
                    stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 1000 + Integer.parseInt(array[1]) * 1000;
                }
                else if (array.length == 3)
                {
                    stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 60 * 1000 + Integer.parseInt(array[1]) * 60 * 1000 + Integer.parseInt(array[2]) * 1000;
                }

                if (!started)
                {
                    clock.chronometer.setBase(SystemClock.elapsedRealtime() - stoppedMilliseconds);
                    activity.openContextMenu(view);
                    started = true;

                } else
                {
                    activity.openContextMenu(view);
                    started = false;
                }
                break;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        switch (v.getId())
        {
            case R.id.chronometer:
                if(!endOfGame)
                {
                    if (firstHalf)
                    {
                        if(!clock.gameStarted)
                        {
                            inflater.inflate(R.menu.gameclock_start_half, menu);
                            //clock.startClock();
                        }
                        else if(clock.clockPlaying)
                        {
                            inflater.inflate(R.menu.gameclock_menu_first_half_playing, menu);
                            //clock.pauseClock();
                        }
                        else if(clock.clockPaused)
                        {
                            inflater.inflate(R.menu.gameclock_menu_first_half_paused, menu);
                            clock.playClock();
                        }
                    }
                    else
                        if(!clock.gameStarted)
                        {
                            inflater.inflate(R.menu.gameclock_start_half, menu);
                            //clock.startClock();
                            //clock.secondHalfStarted = true;
                        }
                        else if(clock.clockPlaying)
                        {
                            inflater.inflate(R.menu.gameclock_menu_second_half_playing, menu);
                            clock.pauseClock();
                        }
                        else if(clock.clockPaused)
                        {
                            inflater.inflate(R.menu.gameclock_menu_second_half_paused, menu);
                            clock.playClock();
                        }
                }
                break;
        }

    }

    @Override
    public boolean onContextItemSelected(final MenuItem item)
    {
        View v = item.getActionView();
        // Do the action by id here
        switch (item.getItemId())
        {
            //lineouts, replace output with json
            case R.id.lineoutteam:
                //depth=1;
                //teams = true;
                break;
            case R.id.team1:
                System.out.println("Lineout goes to team 1");
                //teams = false;
                //depth = 0;
                return true;
            case R.id.team2:
                System.out.println("Lineout goes to team 2");
                //teams = false;
                //depth = 0;
                return true;
            case R.id.lineoutplayer:
                //depth=1;
                return false;
            case R.id.lineoutplayernumberlist:
                System.out.println("Selected the player number list");//need to find a way to actually display a list
                return false;
            case R.id.lineoutplayernamelist:
                System.out.println("Selected the player name list");//need to find a way to actually display a list
            case R.id.lineoutwonteam:
                //depth=1;
                return false;
            case R.id.lineoutwonteam1:
                System.out.println("Lineout won by team 1");
                return false;
            case R.id.lineoutwonteam2:
                System.out.println("Lineout won by team 2");
                return false;
            //gameclock
            case R.id.startgame:
                //chronometer.getCurrentClockTime();
                if(!clock.started)
                {
                    data.addClockEvent(data.generateTimeStamp(), "StartOfGame");
                    System.out.println("Clicked Play first time");
                    clock.chronometer.setBase(SystemClock.elapsedRealtime());//stoppedMilliseconds
                    clock.startClock();
		    clock.start();
                }
                else
                {
                    //System.out.println("Clicked Play");
			clock.startClock();
                    clock.start();
                }

                tryBtn.setEnabled(true);
                penaltyBtn.setEnabled(true);
                dropBtn.setEnabled(true);
                lineoutBtn.setEnabled(true);
                scrumBtn.setEnabled(true);
                substituteBtn.setEnabled(true);
                disciplineBtn.setEnabled(true);
                turnoverBtn.setEnabled(true);
                listBtn.setEnabled(true);
                //helpBtn.setEnabled(true);

                Toast.makeText(getApplicationContext(), "Timer Started at: "+clock.chronometer.getText().toString(), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.startSecondHalf:
                data.addClockEvent(data.generateTimeStamp(), "StartSecondHalf");

                Toast.makeText(getApplicationContext(), "Second half begun: "+clock.chronometer.getText().toString(), Toast.LENGTH_SHORT).show();

                TextView mytextview = (TextView) findViewById(R.id.whichHalf);
                mytextview.setText("2nd Half");
                clock.chronometer.stop();
                clock.secondHalfStarted = true;

                firstHalf = false;
                clock.stopForBreak();
                clock.setCurrentTime(2400000);
                return true;
            case R.id.endGameClock:
                // Toast.makeText(getApplicationContext(), "Game has ended: "+clock.chronometer.getText().toString(), Toast.LENGTH_SHORT).show();
                data.addClockEvent(data.generateTimeStamp(), "EndOfGame");
                endOfGame = true;
                clock.chronometer.stop();
                return true;


            case R.id.pausegame:
                clock.stop();
                Toast.makeText(getApplicationContext(), "Timer Stopped at: "+clock.chronometer.getText().toString(), Toast.LENGTH_SHORT).show();
                //create pop up prompt to ask for a reason for the pause
	    clock.pauseClock();
                return true;
            case R.id.pausereason1:
                System.out.println("Injury");
                //openContextMenu(v);
                return true;
            case R.id.pausereason2:
                System.out.println("Substitution");
                //openContextMenu(v);
                Intent intent = new Intent(new Intent(MainMenu.this, TeamSelect.class));
                data.setFunctionType("Substitution");
                startActivity(intent);
                return true;
            case R.id.pausereason3:
                System.out.println("Replace me with a prompt");
                //openContextMenu(v);
            case R.id.resetclock:
                clock.reset();
                return true;
        }
        /*final View v = item.getActionView();
        v.post(new Runnable()
        {
            @Override
            public void run()
            {
                v.showContextMenu();
            }
        });*/
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView scoreTeamOne = (TextView) findViewById(R.id.team1);
        TextView scoreTeamTwo = (TextView) findViewById(R.id.team2);

        if(data.getTeamOne() != null && data.getTeamTwo() != null) {
            scoreTeamOne.setText(String.valueOf(data.getTeamOne().getScore()));
            scoreTeamTwo.setText(String.valueOf(data.getTeamTwo().getScore()));
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        /*// Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }*/
        TextView scoreTeamOne = (TextView) findViewById(R.id.team1);
        TextView scoreTeamTwo = (TextView) findViewById(R.id.team2);
        TextView nameTeamOne = (TextView) findViewById(R.id.teamOneLbl);
        TextView nameTeamTwo = (TextView) findViewById(R.id.teamTwoLbl);

        if(data.getTeamOne() != null && data.getTeamTwo() != null) {
            scoreTeamOne.setText(String.valueOf(data.getTeamOne().getScore()));
            scoreTeamTwo.setText(String.valueOf(data.getTeamTwo().getScore()));
            nameTeamOne.setText(String.valueOf(data.getTeamOne().getTeamName()));
            nameTeamTwo.setText(String.valueOf(data.getTeamTwo().getTeamName()));
        }

        tryBtn.setEnabled(true);
        penaltyBtn.setEnabled(true);
        dropBtn.setEnabled(true);
        lineoutBtn.setEnabled(true);
        scrumBtn.setEnabled(true);
        substituteBtn.setEnabled(true);
        disciplineBtn.setEnabled(true);
        turnoverBtn.setEnabled(true);
        listBtn.setEnabled(true);
    }

    @Override
    public void onBackPressed() {
        //AlertDialog.Builder alertDialog = new AlertDialog.Builder(AlertDialogActivity.this);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainMenu.this);

        // Setting Dialog Title
        alertDialog.setTitle("Ending match logger..");

        // Setting Dialog Message
        alertDialog.setMessage("Are you sure you want to end this match log session?");

        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                // Write your code here to invoke YES event
                clock.gameStarted = false;
                clock.reset();
                finish();
            }
        });

        //Setting Negative "NO" Button
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to invoke NO event
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

    public void initialise()
    {
        if(data.getOfflineMode())
        {
            setTitle("OnlyRugby Offline Score Keeper");

            data.setTeamOne(new Team("Blue Bulls"));
            data.setTeamTwo(new Team("Sharks"));

            //Team One's on field players
            data.getTeamOne().addPlayer(new Player("Pierre Schoeman", 1, false));
            data.getTeamOne().addPlayer(new Player("Bandise Maku", 2, false));
            data.getTeamOne().addPlayer(new Player("Marcel van der Merwe", 3, false));
            data.getTeamOne().addPlayer(new Player("Jacques du Plessis", 4, false));
            data.getTeamOne().addPlayer(new Player("Marvin Orie", 5, false));
            data.getTeamOne().addPlayer(new Player("Deon Stegmann", 6, false));
            data.getTeamOne().addPlayer(new Player("Lappies Labuschagne (c)", 7, false));
            data.getTeamOne().addPlayer(new Player("Arno Botha", 8, false));
            data.getTeamOne().addPlayer(new Player("Francois Hougaard", 9, false));
            data.getTeamOne().addPlayer(new Player("Tian Schoeman", 10, false));
            data.getTeamOne().addPlayer(new Player("Jamba Ulengo", 11, false));
            data.getTeamOne().addPlayer(new Player("Burger Odendaal", 12, false));
            data.getTeamOne().addPlayer(new Player("Jan Serfontein", 13, false));
            data.getTeamOne().addPlayer(new Player("Travis Ismaiel", 14, false));
            data.getTeamOne().addPlayer(new Player("Warrick Gelant", 15, false));

            //Team One's reserve players
            data.getTeamOne().addPlayer(new Player("Jaco Visagie", 16, true));
            data.getTeamOne().addPlayer(new Player("Werner Kruger", 17, true));
            data.getTeamOne().addPlayer(new Player("RG Snyman", 18, true));
            data.getTeamOne().addPlayer(new Player("Ruan Steenkamp", 19, true));
            data.getTeamOne().addPlayer(new Player("Ivan van Zyl", 20, true));
            data.getTeamOne().addPlayer(new Player("Louis Fouche", 21, true));
            data.getTeamOne().addPlayer(new Player("Dries Swanepoel", 22, true));

            //Team Two's on field players
            data.getTeamTwo().addPlayer(new Player("Thomas du Toit", 1, false));
            data.getTeamTwo().addPlayer(new Player("Franco Marais", 2, false));
            data.getTeamTwo().addPlayer(new Player("Gerhard Engelbrecht", 3, false));
            data.getTeamTwo().addPlayer(new Player("Etienne Oosthuizen", 4, false));
            data.getTeamTwo().addPlayer(new Player("Dave McDuling", 5, false));
            data.getTeamTwo().addPlayer(new Player("Francois Kleinhans", 6, false));
            data.getTeamTwo().addPlayer(new Player("Christiaan de Bruin", 7, false));
            data.getTeamTwo().addPlayer(new Player("Philip van der Walt (c)", 8, false));
            data.getTeamTwo().addPlayer(new Player("Cobus Reinach", 9, false));
            data.getTeamTwo().addPlayer(new Player("Joe Pietersen", 10, false));
            data.getTeamTwo().addPlayer(new Player("S'bura Sithole", 11, false));
            data.getTeamTwo().addPlayer(new Player("Andre Esterhuizen", 12, false));
            data.getTeamTwo().addPlayer(new Player("Paul Jordaan", 13, false));
            data.getTeamTwo().addPlayer(new Player("Wandile Mjekevu", 14, false));
            data.getTeamTwo().addPlayer(new Player("Odwa Ndungane", 15, false));

            //Team Two's reserve players
            data.getTeamTwo().addPlayer(new Player("Kyle Cooper", 16, true));
            data.getTeamTwo().addPlayer(new Player("Mzamo Majola", 17, true));
            data.getTeamTwo().addPlayer(new Player("Marco Wentzel", 18, true));
            data.getTeamTwo().addPlayer(new Player("Jean Deysel", 19, true));
            data.getTeamTwo().addPlayer(new Player("Michael Claassens", 20, true));
            data.getTeamTwo().addPlayer(new Player("Heimar Williams", 21, true));
            data.getTeamTwo().addPlayer(new Player("Garth April", 22, true));
        }
        else
        {
                        data.setTeamOne(new Team("Blue Bulls"));
            data.setTeamTwo(new Team("Sharks"));

            //Team One's on field players
            data.getTeamOne().addPlayer(new Player("Pierre Schoeman", 1, false));
            data.getTeamOne().addPlayer(new Player("Bandise Maku", 2, false));
            data.getTeamOne().addPlayer(new Player("Marcel van der Merwe", 3, false));
            data.getTeamOne().addPlayer(new Player("Jacques du Plessis", 4, false));
            data.getTeamOne().addPlayer(new Player("Marvin Orie", 5, false));
            data.getTeamOne().addPlayer(new Player("Deon Stegmann", 6, false));
            data.getTeamOne().addPlayer(new Player("Lappies Labuschagne (c)", 7, false));
            data.getTeamOne().addPlayer(new Player("Arno Botha", 8, false));
            data.getTeamOne().addPlayer(new Player("Francois Hougaard", 9, false));
            data.getTeamOne().addPlayer(new Player("Tian Schoeman", 10, false));
            data.getTeamOne().addPlayer(new Player("Jamba Ulengo", 11, false));
            data.getTeamOne().addPlayer(new Player("Burger Odendaal", 12, false));
            data.getTeamOne().addPlayer(new Player("Jan Serfontein", 13, false));
            data.getTeamOne().addPlayer(new Player("Travis Ismaiel", 14, false));
            data.getTeamOne().addPlayer(new Player("Warrick Gelant", 15, false));

            //Team One's reserve players
            data.getTeamOne().addPlayer(new Player("Jaco Visagie", 16, true));
            data.getTeamOne().addPlayer(new Player("Werner Kruger", 17, true));
            data.getTeamOne().addPlayer(new Player("RG Snyman", 18, true));
            data.getTeamOne().addPlayer(new Player("Ruan Steenkamp", 19, true));
            data.getTeamOne().addPlayer(new Player("Ivan van Zyl", 20, true));
            data.getTeamOne().addPlayer(new Player("Louis Fouche", 21, true));
            data.getTeamOne().addPlayer(new Player("Dries Swanepoel", 22, true));

            //Team Two's on field players
            data.getTeamTwo().addPlayer(new Player("Thomas du Toit", 1, false));
            data.getTeamTwo().addPlayer(new Player("Franco Marais", 2, false));
            data.getTeamTwo().addPlayer(new Player("Gerhard Engelbrecht", 3, false));
            data.getTeamTwo().addPlayer(new Player("Etienne Oosthuizen", 4, false));
            data.getTeamTwo().addPlayer(new Player("Dave McDuling", 5, false));
            data.getTeamTwo().addPlayer(new Player("Francois Kleinhans", 6, false));
            data.getTeamTwo().addPlayer(new Player("Christiaan de Bruin", 7, false));
            data.getTeamTwo().addPlayer(new Player("Philip van der Walt (c)", 8, false));
            data.getTeamTwo().addPlayer(new Player("Cobus Reinach", 9, false));
            data.getTeamTwo().addPlayer(new Player("Joe Pietersen", 10, false));
            data.getTeamTwo().addPlayer(new Player("S'bura Sithole", 11, false));
            data.getTeamTwo().addPlayer(new Player("Andre Esterhuizen", 12, false));
            data.getTeamTwo().addPlayer(new Player("Paul Jordaan", 13, false));
            data.getTeamTwo().addPlayer(new Player("Wandile Mjekevu", 14, false));
            data.getTeamTwo().addPlayer(new Player("Odwa Ndungane", 15, false));

            //Team Two's reserve players
            data.getTeamTwo().addPlayer(new Player("Kyle Cooper", 16, true));
            data.getTeamTwo().addPlayer(new Player("Mzamo Majola", 17, true));
            data.getTeamTwo().addPlayer(new Player("Marco Wentzel", 18, true));
            data.getTeamTwo().addPlayer(new Player("Jean Deysel", 19, true));
            data.getTeamTwo().addPlayer(new Player("Michael Claassens", 20, true));
            data.getTeamTwo().addPlayer(new Player("Heimar Williams", 21, true));
            data.getTeamTwo().addPlayer(new Player("Garth April", 22, true));
        }
    }
}
