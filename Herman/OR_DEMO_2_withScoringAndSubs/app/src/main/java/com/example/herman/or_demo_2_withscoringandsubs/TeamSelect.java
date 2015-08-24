package com.example.herman.or_demo_2_withscoringandsubs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.onlyrugbyDemo2.R;
import com.example.herman.or_demo_2_withscoringandsubs.Info.Data;

import java.util.ArrayList;

/**
 * Created by Herman on 2015-08-17.
 */
public class TeamSelect extends AppCompatActivity {

    private Data data = Data.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teamselect);

        /**TextView teamOneScore = (TextView) findViewById(R.id.teamOneScore);
        TextView teamTwoScore = (TextView) findViewById(R.id.teamTwoScore);

        if (data.getFunctionType() == "Score")
        {
            if (data.getTeamOne() != null && data.getTeamTwo() != null) {
                teamOneScore.setText("Score: " + Integer.toString(data.getTeamOne().getScore()));
                teamTwoScore.setText("Score: " + Integer.toString(data.getTeamTwo().getScore()));
            } else {
                teamOneScore.setText("Score: 0");
                teamTwoScore.setText("Score: 0");
            }
        }
        else if (data.getFunctionType() == "Substitution")
        {
            teamOneScore.setText("");
            teamTwoScore.setText("");
        }**/

        //TextView updateMessage =(TextView) findViewById(R.id.updateText);

        /**if (data.getFunctionType() == "Score") {
            if (data.getSelectedTeam() != null)
                updateMessage.setText(data.getSelectedPlayer().getPlayerName() + " of team " + data.getSelectedTeam().getTeamName() + " scored a " + data.getSelectedScoreType());
            else
                updateMessage.setText("");
        }
        else if (data.getFunctionType() == "Substitution")
        {

        }**/

        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.teamOne:
                        data.setSelectedTeam("teamOne");
                        break;
                    case R.id.teamTwo:
                        data.setSelectedTeam("teamTwo");
                        break;
                }

                /**Intent intent = null;
                 if (data.getFunctionType() == "Score") {
                 intent = new Intent(new Intent(TeamSelect.this, SelectScoringMethod.class));
                 }
                 else if (data.getFunctionType() == "Substitution")
                 {
                 intent = new Intent(new Intent(TeamSelect.this, PlayerSelect.class));
                 }**/
                Intent intent = new Intent(new Intent(TeamSelect.this, PlayerSelect.class));
                startActivity(intent);
            }
        };
        Button teamOneBtn =(Button) findViewById(R.id.teamOne);
        Button teamTwoBtn =(Button) findViewById(R.id.teamTwo);
        teamOneBtn.setOnClickListener(listener);
        teamTwoBtn.setOnClickListener(listener);

        teamOneBtn.setText(data.getTeamOne().getTeamName());
        teamTwoBtn.setText(data.getTeamTwo().getTeamName());
    }

    /**public void selectOne()
    {
        data.setSelectedTeam("teamOne");
    }

    public void selectTwo()
    {
        data.setSelectedTeam("teamTwo");
    }**/
}
