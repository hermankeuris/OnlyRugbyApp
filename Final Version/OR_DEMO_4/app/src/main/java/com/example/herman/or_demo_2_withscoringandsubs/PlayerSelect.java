package com.example.herman.or_demo_2_withscoringandsubs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.onlyrugbyDemo2.R;
import com.example.herman.or_demo_2_withscoringandsubs.Info.Data;

import java.util.ArrayList;

/**
 * Created by Herman on 2015-08-12.
 */
public class PlayerSelect extends Activity {

    private Data data = Data.getInstance();
    private Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playerselect);

        TextView playerText = (TextView) findViewById(R.id.playerText);

        data.setEndOfFunction(false);

        switch(data.getFunctionType())
        {
            case "Score":
                playerText.setText("Select the player which scored the " + String.valueOf(data.getSelectedScoreType()).toLowerCase() + ":");
                break;
            case "Substitution":
                if(data.getOnFieldPlayers())
                    playerText.setText("Select the player to substitute:");
                else
                    playerText.setText("Select the player's substitute:");
                break;
            case "Discipline":
                playerText.setText("Select the player to penalise:");
                break;
        }

        ListView listOfPlayers = (ListView) findViewById(R.id.playerList);
        //ArrayList<String> players = new ArrayList<String>();
        ArrayList<String> players = displayPlayers();
        /*ArrayAdapter<String> playersAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, players);
        listOfPlayers.setAdapter(playersAdapter);*/
        ArrayAdapter<String> playersAdapter = new CustomListAdapter(this , R.layout.custom_list , players);
        listOfPlayers.setAdapter(playersAdapter);

        /*if (data.getOnFieldPlayers())
            for(int i = 0; i < 15; i++)
            {
                players.add(data.getSelectedTeam().getOnField().get(i).getJerseyNum() + " " + data.getSelectedTeam().getOnField().get(i).getPlayerName());
            }
        else
            for(int i = 0; i < 7; i++)
            {
                players.add(data.getSelectedTeam().getReserves().get(i).getJerseyNum() + " " + data.getSelectedTeam().getReserves().get(i).getPlayerName());
            }*/

        listOfPlayers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position,
                                    long id) {
                final int temp = position;
                data.setSelectedPlayer(data.getSelectedTeam().getPlayers().get(temp));

                if(data.getSelectedTeam().getPlayers().get(position).getRedCard() && data.getOnFieldPlayers()) {
                    Toast.makeText(activity, "This player has a red card, select another player", Toast.LENGTH_SHORT).show();
                    /**Intent intent = new Intent(new Intent(PlayerSelect.this, PlayerSelect.class));
                     startActivity(intent);**/
                }
                else if(data.getSelectedTeam().getPlayers().get(position).getYellowCard() && data.getOnFieldPlayers()) {
                    if (data.getFunctionType().equals("Discipline"))
                    {
                        //AlertDialog.Builder alertDialog = new AlertDialog.Builder(AlertDialogActivity.this);
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(PlayerSelect.this);

                        // Setting Dialog Title
                        alertDialog.setTitle("Yellow card..");

                        // Setting Dialog Message
                        alertDialog.setMessage("Do you want to remove this player's yellow card?");

                        // Setting Positive "Yes" Button
                        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                // Write your code here to invoke YES event
                                //data.setSelectedPlayer(data.getSelectedTeam().getPlayers().get(temp));
                                data.getSelectedPlayer().setYellowCard(false);
                                data.setEndOfFunction(true);
                                finish();
                            }
                        });

                        //Setting Negative "NO" Button
                        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to invoke NO event
                                Toast.makeText(activity, "This player has a yellow card, select another player", Toast.LENGTH_SHORT).show();
                                /**Intent intent = new Intent(new Intent(PlayerSelect.this, PlayerSelect.class));
                                 startActivity(intent);**/
                            }
                        });

                        // Showing Alert Message
                        alertDialog.show();
                    }else {
                        Toast.makeText(activity, "This player has a yellow card, select another player", Toast.LENGTH_SHORT).show();
                        /**Intent intent = new Intent(new Intent(PlayerSelect.this, PlayerSelect.class));
                         startActivity(intent);**/
                    }
                }
                else {
                    if (data.getFunctionType() == "Score") {
                        data.setSelectedPlayer(data.getSelectedTeam().getPlayers().get(position));
                        if (data.getSelectedScoreType() != "Conversion Kick")
                            data.getSelectedTeam().addScore(data.getSelectedScoreType());
                        switch (data.getSelectedScoreType()) {
                            case "Try":
                                data.getSelectedPlayer().playerScore("Try");
                                data.setConversionTryPlayer(data.getSelectedPlayer());

                                data.setSelectedScoreType("Conversion Kick");
                                break;
                            case "Conversion Kick":
                                data.getSelectedPlayer().playerAttemptConversion();
                                TextView playerText = (TextView) findViewById(R.id.playerText);
                                playerText.setText("Select the player which scored the " + String.valueOf(data.getSelectedScoreType()).toLowerCase() + ":");

                                //AlertDialog.Builder alertDialog = new AlertDialog.Builder(AlertDialogActivity.this);
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(PlayerSelect.this);

                                alertDialog.setCancelable(false);

                                // Setting Dialog Title
                                alertDialog.setTitle("Conversion attempt..");

                                // Setting Dialog Message
                                alertDialog.setMessage("Was the conversion kick successful?");

                                // Setting Positive "Yes" Button
                                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        data.getSelectedPlayer().playerScore("Conversion Kick");
                                        data.getSelectedTeam().addScore("Conversion Kick");
                                        data.addTryConversion(data.generateTimeStamp(), data.getSelectedTeam(), data.getConversionTryPlayer(), data.getSelectedPlayer());
                                        data.setEndOfFunction(true);
                                        finish();
                                    }
                                });

                                //Setting Negative "NO" Button
                                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        data.addTry(data.generateTimeStamp(), data.getSelectedTeam(), data.getSelectedPlayer());
                                        data.setEndOfFunction(true);
                                        finish();
                                    }
                                });

                                // Showing Alert Message
                                alertDialog.show();
                                break;
                            case "Penalty Kick":
                                data.addPenaltyKick(data.generateTimeStamp(), data.getSelectedTeam(), data.getSelectedTeam().getPlayers().get(position));
                                data.setEndOfFunction(true);
                                data.getSelectedPlayer().playerScore("Penalty Kick");
                                finish();
                                break;
                            case "Drop Kick":
                                data.addDropKick(data.generateTimeStamp(), data.getSelectedTeam(), data.getSelectedTeam().getPlayers().get(position));
                                data.setEndOfFunction(true);
                                data.getSelectedPlayer().playerScore("Drop Kick");
                                finish();
                                break;
                        }
                    }
                    else if (data.getFunctionType() == "Substitution") {
                        if (data.getOnFieldPlayers())
                        {
                            data.setOnFieldPlayers(false);
                            data.setSelectedPlayer(data.getSelectedTeam().getPlayers().get(position));
                            data.setSubstitutePlayer(data.getSelectedTeam().getPlayers().get(position));
                            Intent intent = new Intent(new Intent(PlayerSelect.this, PlayerSelect.class));
                            startActivity(intent);
                        }
                        else {
                            data.addSubstitution(data.generateTimeStamp(), data.getSelectedTeam(), data.getSubstitutePlayer(), data.getSelectedTeam().getPlayers().get(position));
                            //data.getSelectedTeam().subPlayers(data.getSelectedPlayer(), data.getSelectedTeam().getPlayers().get(position));
                            data.setSelectedPlayer(data.getSelectedTeam().getPlayers().get(position));
                            data.setOnFieldPlayers(true);
                            data.setEndOfFunction(true);

                            finish();
                        }
                    }
                    else if (data.getFunctionType() == "Discipline")
                    {
                        data.setSelectedPlayer(data.getSelectedTeam().getPlayers().get(position));
                        Intent intent = new Intent(new Intent(PlayerSelect.this, AssignDiscipline.class));
                        startActivity(intent);
                    }
                }
            }
        });
    }

    public ArrayList<String> displayPlayers()
    {
        ArrayList<String> _players = new ArrayList<String>();
        /*int num = 15;

        if (data.getOnFieldPlayers() && data.getSelectedTeam() != null) {
            if (data.getFunctionType() == "Score")
                num = 16;

            for (int i = 0; i < num; i++) {
                if (data.getSelectedTeam().getOnField().get(i).getCurr_position() == 0)
                    _players.add("?? " + data.getSelectedTeam().getOnField().get(i).getPlayerName());
                else
                    _players.add(data.getSelectedTeam().getOnField().get(i).getCurr_position() + " " + data.getSelectedTeam().getOnField().get(i).getPlayerName());
            }
        }
        else if (!data.getOnFieldPlayers() && data.getSelectedTeam() != null)
            for(int i = 0; i < data.getSelectedTeam().getReserves().size(); i++) {
                if (data.getSelectedTeam().getReserves().get(i).getCurr_position() == 0)
                    _players.add("?? " + data.getSelectedTeam().getReserves().get(i).getPlayerName());
                else
                    _players.add(data.getSelectedTeam().getReserves().get(i).getCurr_position() + " " + data.getSelectedTeam().getReserves().get(i).getPlayerName());
            }
        return _players;*/
        if (data.getSelectedTeam() != null) {
            for (int i = 0; i < 23; i++) {
                if (data.getSelectedTeam().getPlayers().get(i).getCurr_position() == 0)
                    _players.add("?? " + data.getSelectedTeam().getPlayers().get(i).getPlayerName());
                else
                    _players.add(data.getSelectedTeam().getPlayers().get(i).getCurr_position() + " " + data.getSelectedTeam().getPlayers().get(i).getPlayerName());
            }
        }

        return _players;
    }

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

    @Override
    public void onBackPressed() {
        if (data.getSelectedScoreType() != "Conversion kick")
            super.onBackPressed();
        data.setOnFieldPlayers(true);
    }
}