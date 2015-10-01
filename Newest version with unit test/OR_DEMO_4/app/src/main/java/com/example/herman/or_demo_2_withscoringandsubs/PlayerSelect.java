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
import com.example.herman.or_demo_2_withscoringandsubs.Info.Player;

import java.util.ArrayList;

/**
 * Created by Herman on 2015-08-12.
 */
public class PlayerSelect extends Activity {

    private Data data = Data.getInstance();
    private Activity activity = this;
    private Player sub;

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
        ArrayAdapter<String> playersAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, players);
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

                if(data.getSelectedTeam().getOnField().get(position).getRedCard()) {
                    Toast.makeText(activity, "This player has a red card, select another player", Toast.LENGTH_SHORT).show();
                    /**Intent intent = new Intent(new Intent(PlayerSelect.this, PlayerSelect.class));
                    startActivity(intent);**/
                }
                else if(data.getSelectedTeam().getOnField().get(position).getYellowCard()) {
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
                                data.setSelectedPlayer(data.getSelectedTeam().getOnField().get(temp));
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
                    if (data.getFunctionType() == "Score")
                    {
                        data.setSelectedPlayer(data.getSelectedTeam().getOnField().get(position));
                        data.getSelectedTeam().addScore(data.getSelectedScoreType());
                        switch (data.getSelectedScoreType())
                        {
                            case "Try":
                                //AlertDialog.Builder alertDialog = new AlertDialog.Builder(AlertDialogActivity.this);
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(PlayerSelect.this);

                                // Setting Dialog Title
                                alertDialog.setTitle("Successful try..");

                                // Setting Dialog Message
                                alertDialog.setMessage("Did the team score the subsequent conversion kick?");

                                // Setting Positive "Yes" Button
                                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener()
                                {
                                    public void onClick(DialogInterface dialog, int which)
                                    {

                                        // Write your code here to invoke YES event
                                        //Intent intent = new Intent(new Intent(PlayerSelect.this, PlayerSelect.class));
                                        data.setSelectedScoreType("Conversion Kick");
                                        TextView playerText = (TextView) findViewById(R.id.playerText);
                                        playerText.setText("Select the player which scored the " + String.valueOf(data.getSelectedScoreType()).toLowerCase() + ":");

                                        //data.addToEvents("Try+Penalty", "0", data.getSelectedTeam(), null, data.getSelectedTeam().getOnField().get(position), null, null);
                                        data.addTryConversion("0", data.getSelectedTeam(), data.getSelectedTeam().getOnField().get(position), data.getSelectedTeam().getOnField().get(position));
                                        //startActivity(intent);
                                    }
                                });

                                //Setting Negative "NO" Button
                                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        data.getSelectedTeam().getOnField().get(position).print();
                                        //data.addToEvents("Try", "1", data.getSelectedTeam(), null, data.getSelectedTeam().getOnField().get(position), null, null);
                                        data.addTry("0", data.getSelectedTeam(), data.getSelectedTeam().getOnField().get(position));
                                        data.setEndOfFunction(true);
                                        finish();
                                    }
                                });

                                // Showing Alert Message
                                alertDialog.show();
                                break;
                            case "Conversion Kick":
                                data.setEndOfFunction(true);
                                finish();
                                break;
                            case "Penalty Kick":
                                data.addPenaltyKick("0", data.getSelectedTeam(), data.getSelectedTeam().getOnField().get(position));
                                data.setEndOfFunction(true);
                                finish();
                                break;
                            case "Drop Kick":
                                data.addDropKick("0", data.getSelectedTeam(), data.getSelectedTeam().getOnField().get(position));
                                data.setEndOfFunction(true);
                                finish();
                                break;
                        }
                    }
                    else if (data.getFunctionType() == "Substitution")
                    {

                        if (data.getOnFieldPlayers())
                        {
                            System.out.println("aaaaaaaaaaaaaaaaaaaa");
                            data.setOnFieldPlayers(false);
                            data.setSelectedPlayer(data.getSelectedTeam().getOnField().get(position));
                            sub = data.getSelectedTeam().getOnField().get(position);
                            /*Intent intent = new Intent(new Intent(PlayerSelect.this, PlayerSelect.class));
                            startActivity(intent);*/
                        }
                        else {
                            System.out.println("bbbbbbbbbbbbbbbbbbbbbb");
                            data.getSelectedTeam().subPlayers(data.getSelectedPlayer(), data.getSelectedTeam().getReserves().get(position));
                            data.setSelectedPlayer(data.getSelectedTeam().getReserves().get(position));

                            data.addToEvents("Substitution", "0:00", data.getSelectedTeam() ,null, sub, data.getSelectedTeam().getReserves().get(position), null);
                            data.setOnFieldPlayers(true);
                            data.setEndOfFunction(true);
                            finish();
                        }
                    }
                    else if (data.getFunctionType() == "Discipline")
                    {
                        data.setSelectedPlayer(data.getSelectedTeam().getOnField().get(position));
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
        int num = 15;

        if (data.getOnFieldPlayers() && data.getSelectedTeam() != null) {
            if (data.getFunctionType() == "Score")
                num = 16;

            for (int i = 0; i < num; i++) {
                if (data.getSelectedTeam().getOnField().get(i).getCurr_position() == 0)
                    _players.add("?? " + data.getSelectedTeam().getOnField().get(i).getPlayerName());
                else
                    if(data.getSelectedTeam().getOnField().get(i).getRedCard())
                        _players.add(data.getSelectedTeam().getOnField().get(i).getCurr_position() + " " + data.getSelectedTeam().getOnField().get(i).getPlayerName() + " [Red Card]");
                    else if(data.getSelectedTeam().getOnField().get(i).getYellowCard()) {
                        _players.add(data.getSelectedTeam().getOnField().get(i).getCurr_position() + " " + data.getSelectedTeam().getOnField().get(i).getPlayerName() + " [Yellow Card]");
                    }
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
}