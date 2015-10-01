package com.example.herman.or_demo_2_withscoringandsubs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
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
    private boolean started = false;
    private GameClock clock;
    private boolean firstHalf = true;
    private boolean endOfGame = false;

    Activity activity = this;
    private String endOfFirstHalf, startOfSecondHalf, endOfSecondHalf;

    private static boolean backPressedTwice = false;

    public static boolean refreshDisplay = true;

    private Button tryBtn;
    private Button penaltyBtn;
    private Button dropBtn;
    private Button lineoutBtn;
    private Button ruckBtn;
    private Button substituteBtn;
    private Button disciplineBtn;
    private Button turnoverBtn;
    private Button listBtn;
    //private Button helpBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_logger);

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
        ruckBtn = (Button) findViewById(R.id.ruckBtn);
        substituteBtn = (Button) findViewById(R.id.substituteBtn);
        disciplineBtn = (Button) findViewById(R.id.disciplineBtn);
        turnoverBtn = (Button) findViewById(R.id.turnoverButton);
        listBtn = (Button) findViewById(R.id.listButton);
        //helpBtn = (Button) findViewById(R.id.helpBtn);

        tryBtn.setEnabled(false);
        penaltyBtn.setEnabled(false);
        dropBtn.setEnabled(false);
        lineoutBtn.setEnabled(false);
        ruckBtn.setEnabled(false);
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
            case R.id.ruckBtn:
                data.setFunctionType("Ruck");
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
                                    }
                else
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
            //case R.id.lineoutBtn:
            //if(depth == 0)
            //{
            //       inflater.inflate(R.menu.lineout_menu, menu);
                /*}
                else if(depth == 1 && teams)
                {
                    inflater.inflate(R.menu.lineout_menu_teams, menu);
                }*/
            //   break;
            case R.id.chronometer:
                if(!endOfGame) {
                    if (firstHalf)
                        inflater.inflate(R.menu.gameclock_menu, menu);
                    else
                        inflater.inflate(R.menu.gameclock_menu_second_half, menu);
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
                if(!clock.started())
                {
                    System.out.println("Clicked Play first time");
                    clock.chronometer.setBase(SystemClock.elapsedRealtime());//stoppedMilliseconds
                    clock.start();
                }
                else
                {
                    //System.out.println("Clicked Play");
                    clock.start();
                }

                tryBtn.setEnabled(true);
                penaltyBtn.setEnabled(true);
                dropBtn.setEnabled(true);
                lineoutBtn.setEnabled(true);
                ruckBtn.setEnabled(true);
                substituteBtn.setEnabled(true);
                disciplineBtn.setEnabled(true);
                turnoverBtn.setEnabled(true);
                listBtn.setEnabled(true);
                //helpBtn.setEnabled(true);

                Toast.makeText(getApplicationContext(), "Timer Started at: "+clock.chronometer.getText().toString(), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.startSecondHalf:

                Toast.makeText(getApplicationContext(), "Second half begun: "+clock.chronometer.getText().toString(), Toast.LENGTH_SHORT).show();

                TextView mytextview = (TextView) findViewById(R.id.whichHalf);
                mytextview.setText("2nd Half");
                clock.chronometer.stop();


                firstHalf = false;
                return true;
            case R.id.endGameClock:
                // Toast.makeText(getApplicationContext(), "Game has ended: "+clock.chronometer.getText().toString(), Toast.LENGTH_SHORT).show();
                endOfGame = true;
                clock.chronometer.stop();
                return true;


            case R.id.pausegame:
                clock.stop();
                Toast.makeText(getApplicationContext(), "Timer Stopped at: "+clock.chronometer.getText().toString(), Toast.LENGTH_SHORT).show();
                //create pop up prompt to ask for a reason for the pause
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
        else
        {
            data.setTeamOne(new Team("Pretoria Highschool"));
            data.setTeamTwo(new Team("Bloemfontein Highschool"));

            //Team One's on field players
            data.getTeamOne().addPlayer(new Player("John One", 1, false));
            data.getTeamOne().addPlayer(new Player("John Two", 2, false));
            data.getTeamOne().addPlayer(new Player("John Three", 3, false));
            data.getTeamOne().addPlayer(new Player("John Four", 4, false));
            data.getTeamOne().addPlayer(new Player("John Five", 5, false));
            data.getTeamOne().addPlayer(new Player("John Six", 6, false));
            data.getTeamOne().addPlayer(new Player("John Seven", 7, false));
            data.getTeamOne().addPlayer(new Player("John Eight", 8, false));
            data.getTeamOne().addPlayer(new Player("John Nine", 9, false));
            data.getTeamOne().addPlayer(new Player("John Ten", 10, false));
            data.getTeamOne().addPlayer(new Player("John Eleven", 11, false));
            data.getTeamOne().addPlayer(new Player("John Twelve", 12, false));
            data.getTeamOne().addPlayer(new Player("John Thirteen", 13, false));
            data.getTeamOne().addPlayer(new Player("John Fourteen", 14, false));
            data.getTeamOne().addPlayer(new Player("John Fifteen", 15, false));

            //Team One's reserve players
            data.getTeamOne().addPlayer(new Player("John Sixteen", 16, true));
            data.getTeamOne().addPlayer(new Player("John Seventeen", 17, true));
            data.getTeamOne().addPlayer(new Player("John Eighteen", 18, true));
            data.getTeamOne().addPlayer(new Player("John Nineteen", 19, true));
            data.getTeamOne().addPlayer(new Player("John Twenty", 20, true));
            data.getTeamOne().addPlayer(new Player("John TwentyOne", 21, true));
            data.getTeamOne().addPlayer(new Player("John TwentyTwo", 22, true));

            //Team Two's on field players
            data.getTeamTwo().addPlayer(new Player("Paul One", 1, false));
            data.getTeamTwo().addPlayer(new Player("Paul Two", 2, false));
            data.getTeamTwo().addPlayer(new Player("Paul Three", 3, false));
            data.getTeamTwo().addPlayer(new Player("Paul Four", 4, false));
            data.getTeamTwo().addPlayer(new Player("Paul Five", 5, false));
            data.getTeamTwo().addPlayer(new Player("Paul Six", 6, false));
            data.getTeamTwo().addPlayer(new Player("Paul Seven", 7, false));
            data.getTeamTwo().addPlayer(new Player("Paul Eight", 8, false));
            data.getTeamTwo().addPlayer(new Player("Paul Nine", 9, false));
            data.getTeamTwo().addPlayer(new Player("Paul Ten", 10, false));
            data.getTeamTwo().addPlayer(new Player("Paul Eleven", 11, false));
            data.getTeamTwo().addPlayer(new Player("Paul Twelve", 12, false));
            data.getTeamTwo().addPlayer(new Player("Paul Thirteen", 13, false));
            data.getTeamTwo().addPlayer(new Player("Paul Fourteen", 14, false));
            data.getTeamTwo().addPlayer(new Player("Paul Fifteen", 15, false));

            //Team Two's reserve players
            data.getTeamTwo().addPlayer(new Player("Paul Sixteen", 16, true));
            data.getTeamTwo().addPlayer(new Player("Paul Seventeen", 17, true));
            data.getTeamTwo().addPlayer(new Player("Paul Eighteen", 18, true));
            data.getTeamTwo().addPlayer(new Player("Paul Nineteen", 19, true));
            data.getTeamTwo().addPlayer(new Player("Paul Twenty", 20, true));
            data.getTeamTwo().addPlayer(new Player("Paul TwentyOne", 21, true));
            data.getTeamTwo().addPlayer(new Player("Paul TwentyTwo", 22, true));
        }
    }
}
