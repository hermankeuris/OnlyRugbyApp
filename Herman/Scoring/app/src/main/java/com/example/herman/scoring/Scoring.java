package com.example.herman.scoring;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.herman.Info.Data;
import com.example.herman.Info.Player;
import com.example.herman.Info.Team;
import com.example.herman.SelectScoringMethod.SelectScoringMethod;


import java.util.ArrayList;

public class Scoring extends AppCompatActivity {

    private Data data = Data.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoring);

        TextView teamOneScore =(TextView) findViewById(R.id.teamOneScore);
        TextView teamTwoScore =(TextView) findViewById(R.id.teamTwoScore);

        if (data.getTeamOne() != null && data.getTeamTwo() != null) {
                teamOneScore.setText("Score: " + Integer.toString(data.getTeamOne().getScore()));
                teamTwoScore.setText("Score: " + Integer.toString(data.getTeamTwo().getScore()));
        }
        else
        {
            teamOneScore.setText("Score: 0");
            teamTwoScore.setText("Score: 0");
        }

        TextView updateMessage =(TextView) findViewById(R.id.updateText);
        if (data.getSelectedTeam() != null)
            updateMessage.setText(data.getSelectedPlayer().getPlayerName() + " of team " + data.getSelectedTeam().getTeamName() + " scored a " + data.getSelectedScoreType());
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
                        selectTwo();
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
        if (data.getTeamOne() == null && data.getTeamTwo() == null) {
            data.setTeamOne(new Team("Pretoria Highschool"));
            data.setTeamTwo(new Team("Bloemfontein Highschool"));

            //The lists of team members
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

            //Team Two's on field players
            playersTwoOnField.add(new Player("Paul One", 1, false));
            playersTwoOnField.add(new Player("Paul Two", 2, false));
            playersTwoOnField.add(new Player("Paul Three", 3, false));
            playersTwoOnField.add(new Player("Paul Four", 4, false));
            playersTwoOnField.add(new Player("Paul Five", 5, false));
            playersTwoOnField.add(new Player("Paul Six", 6, false));
            playersTwoOnField.add(new Player("Paul Seven", 7, false));
            playersTwoOnField.add(new Player("Paul Eight", 8, false));
            playersTwoOnField.add(new Player("Paul Nine", 9, false));
            playersTwoOnField.add(new Player("Paul Ten", 10, false));
            playersTwoOnField.add(new Player("Paul Eleven", 11, false));
            playersTwoOnField.add(new Player("Paul Twelve", 12, false));
            playersTwoOnField.add(new Player("Paul Thirteen", 13, false));
            playersTwoOnField.add(new Player("Paul Fourteen", 14, false));
            playersTwoOnField.add(new Player("Paul Fifteen", 15, false));

            //Team Two's reserve players
            playersTwoReserve.add(new Player("Paul Sixteen", 16, true));
            playersTwoReserve.add(new Player("Paul Seventeen", 17, true));
            playersTwoReserve.add(new Player("Paul Eighteen", 18, true));
            playersTwoReserve.add(new Player("Paul Nineteen", 19, true));
            playersTwoReserve.add(new Player("Paul Twenty", 20, true));
            playersTwoReserve.add(new Player("Paul TwentyOne", 21, true));
            playersTwoReserve.add(new Player("Paul TwentyTwo", 22, true));

            //Assign the players to their teams
            data.getTeamOne().setOnField(playersOneOnField);
            data.getTeamOne().setReserves(playersOneReserve);

            data.getTeamTwo().setOnField(playersTwoOnField);
            data.getTeamTwo().setReserves(playersTwoReserve);
        }
        /**
         //End of dummy data
         **/

        teamOneBtn.setText(data.getTeamOne().getTeamName());
        teamTwoBtn.setText(data.getTeamTwo().getTeamName());
    }

    public void selectOne()
    {
        data.setSelectedTeam("teamOne");
    }

    public void selectTwo()
    {
        data.setSelectedTeam("teamTwo");
    }
}
