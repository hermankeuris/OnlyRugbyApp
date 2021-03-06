package com.example.herman.or_demo_2_withscoringandsubs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.onlyrugbyDemo2.R;
import com.example.herman.or_demo_2_withscoringandsubs.Info.Data;

/**
 * Created by Herman on 2015-08-17.
 */
public class TeamSelect extends Activity {

    private Data data = Data.getInstance();
    private Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teamselect);

        TextView teamText = (TextView) findViewById(R.id.teamText);

        data.setEndOfFunction(false);

        switch(data.getFunctionType())
        {
            case "Score":
                teamText.setText("Select the team which scored the " + String.valueOf(data.getSelectedScoreType()).toLowerCase() + ":");
                break;
            case "Substitution":
                teamText.setText("Select the team which is substituting a player:");
                break;
            case "Discipline":
                teamText.setText("Select the team whose player must be penalised:");
                break;
            case "Turnover":
                teamText.setText("Select the team who won the turnover:");
                break;
            case "Scrum":
                teamText.setText("Select the team who won the scrum:");
                break;
            case "LineOut":
                if (!data.getLineOutTeam())
                    teamText.setText("Select the team who's line out it is:");
                else
                    teamText.setText("Select the team who won the line out:");
                break;
        }
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
                if (data.getFunctionType().equals("Turnover"))
                {
                    if (data.getSelectedTeam() == data.getTeamOne())
                    {
                        data.addTurnover(data.generateTimeStamp(), data.getTeamOne(), data.getTeamTwo());
                        data.getTeamOne().incrementTurnoversWon();
                        data.getTeamTwo().incrementTurnoversLost();
                    }
                    else
                    {
                        data.addTurnover(data.generateTimeStamp(), data.getTeamTwo(), data.getTeamOne());
                        data.getTeamTwo().incrementTurnoversWon();
                        data.getTeamOne().incrementTurnoversLost();
                    }
                    data.setEndOfFunction(true);
                    Toast.makeText(activity, data.getSelectedTeam().getTeamName() + " now has possession", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else if (data.getFunctionType().equals("Scrum"))
                {


                    if (data.getSelectedTeam() == data.getTeamOne())
                    {
                        data.addScrum(data.generateTimeStamp(), data.getTeamOne(), data.getTeamTwo());

                        data.getTeamOne().incrementScrumsWon();
                        data.getTeamTwo().incrementScrumsLost();
                    }
                    else
                    {
                        data.addScrum(data.generateTimeStamp(), data.getTeamTwo(), data.getTeamOne());

                        data.getTeamTwo().incrementScrumsWon();
                        data.getTeamOne().incrementScrumsLost();
                    }
                    data.setEndOfFunction(true);
                    Toast.makeText(activity, data.getSelectedTeam().getTeamName() + " won the scrum", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else if (data.getFunctionType().equals("LineOut"))
                {
                    if(!data.getLineOutTeam())
                    {
                        data.setLineOutTeam(true);
                        data.getSelectedTeam().setLineOut(true);
                        Intent intent = new Intent(new Intent(TeamSelect.this, TeamSelect.class));
                        startActivity(intent);
                    }
                    else
                    {
                        if (data.getSelectedTeam() == data.getTeamOne())
                        {
                            data.addLineOut(data.generateTimeStamp(), data.getTeamOne(), data.getTeamTwo());

                            if(data.getTeamOne().getLineOut())
                            {
                                data.getTeamOne().incrementOwnLineOutsWon();
                                data.getTeamTwo().incrementOpponentLineOutsLost();
                                Toast.makeText(activity, data.getSelectedTeam().getTeamName() + " won their own lineout", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                data.getTeamOne().incrementOpponentLineOutsWon();;
                                data.getTeamTwo().incrementOwnLineOutsLost();
                                Toast.makeText(activity, data.getTeamTwo().getTeamName() + " won their opponent's lineout", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            data.addLineOut(data.generateTimeStamp(), data.getTeamTwo(), data.getTeamOne());

                            if(data.getTeamOne().getLineOut())
                            {
                                data.getTeamOne().incrementOwnLineOutsLost();
                                data.getTeamTwo().incrementOpponentLineOutsWon();
                                Toast.makeText(activity, data.getTeamTwo().getTeamName() + " won their opponent's lineout", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                data.getTeamOne().incrementOpponentLineOutsLost();;
                                data.getTeamTwo().incrementOwnLineOutsWon();
                                Toast.makeText(activity, data.getSelectedTeam().getTeamName() + " won their own lineout", Toast.LENGTH_SHORT).show();
                            }
                        }

                        data.setEndOfFunction(true);
                        finish();
                    }
                }
                else {
                    Intent intent = new Intent(new Intent(TeamSelect.this, PlayerSelect.class));
                    startActivity(intent);
                }
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

    /**@Override
    protected void onPause() {
    super.onPause();
    finish();
    }**/
    @Override
    protected void onResume() {
        super.onResume();
        if (data.getEndOfFunction())
            finish();
    }
}
