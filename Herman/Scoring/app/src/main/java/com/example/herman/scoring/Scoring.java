package com.example.herman.scoring;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.herman.Info.Player;
import com.example.herman.Info.Team;
import com.example.herman.PlayerSelect.PlayerSelect;
import com.example.herman.SelectScoringMethod.SelectScoringMethod;

import java.util.ArrayList;

public class Scoring extends AppCompatActivity {

    public static Team selectedTeam;
    public static Team teamOne;
    public static Team teamTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoring);

        TextView teamOneScore =(TextView) findViewById(R.id.teamOneScore);
        TextView teamTwoScore =(TextView) findViewById(R.id.teamTwoScore);

        if (selectedTeam != null)
            if(selectedTeam.getTeamName() == teamOne.getTeamName())
                teamOneScore.setText("Score: " + Integer.toString(selectedTeam.getScore()));
            else
                teamTwoScore.setText("Score: " + Integer.toString(selectedTeam.getScore()));
        else
        if(selectedTeam == teamOne)
            teamOneScore.setText("Score: 0");
        else
            teamTwoScore.setText("Score: 0");

        TextView updateMessage =(TextView) findViewById(R.id.updateText);
        if (selectedTeam != null)
            updateMessage.setText(PlayerSelect.selectedPlayer.getPlayerName() + " of team " + selectedTeam.getTeamName() + " scored a " + SelectScoringMethod.selectedScoreType);
        else
            updateMessage.setText("");

        OnClickListener listener=new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(new Intent(Scoring.this, SelectScoringMethod.class));
                startActivity(intent);

                switch(v.getId()){
                    case R.id.teamOne:
                        selectOne();
                        break;
                    case R.id.teamTwo:
                        selectOne();
                        break;
                }
            }
        };
        Button teamOneBtn =(Button) findViewById(R.id.teamOne);
        Button teamTwoBtn =(Button) findViewById(R.id.teamTwo);
        teamOneBtn.setOnClickListener(listener);
        teamTwoBtn.setOnClickListener(listener);

        /**
        //For testing only
         **/
        teamOne = new Team("Pretoria Highschool");
        teamTwo = new Team("Bloemfontein Highschool");

        //The lists of team members
        ArrayList<Player> playersOne = new ArrayList<Player>();
        ArrayList<Player> playersTwo = new ArrayList<Player>();

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

        //Team Two's on field players
        playersOne.add(new Player("Paul One", 1, false));
        playersOne.add(new Player("Paul Two", 2, false));
        playersOne.add(new Player("Paul Three", 3, false));
        playersOne.add(new Player("Paul Four", 4, false));
        playersOne.add(new Player("Paul Five", 5, false));
        playersOne.add(new Player("Paul Six", 6, false));
        playersOne.add(new Player("Paul Seven", 7, false));
        playersOne.add(new Player("Paul Eight", 8, false));
        playersOne.add(new Player("Paul Nine", 9, false));
        playersOne.add(new Player("Paul Ten", 10, false));
        playersOne.add(new Player("Paul Eleven", 11, false));
        playersOne.add(new Player("Paul Twelve", 12, false));
        playersOne.add(new Player("Paul Thirteen", 13, false));
        playersOne.add(new Player("Paul Fourteen", 14, false));
        playersOne.add(new Player("Paul Fifteen", 15, false));

        //Team Two's reserve players
        playersOne.add(new Player("Paul Sixteen", 16, true));
        playersOne.add(new Player("Paul Seventeen", 17, true));
        playersOne.add(new Player("Paul Eighteen", 18, true));
        playersOne.add(new Player("Paul Nineteen", 19, true));
        playersOne.add(new Player("Paul Twenty", 20, true));
        playersOne.add(new Player("Paul TwentyOne", 21, true));
        playersOne.add(new Player("Paul TwentyTwo", 22, true));

        //Assign the players to their teams
        teamOne.setOnField(playersOne);
        teamTwo.setOnField(playersTwo);
        /**
        //End of dummy data
         **/

        teamOneBtn.setText(teamOne.getTeamName());
        teamTwoBtn.setText(teamTwo.getTeamName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scoring, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void selectOne()
    {
        selectedTeam = teamOne;
    }

    public void selectTwo()
    {
        selectedTeam = teamTwo;
    }
}
