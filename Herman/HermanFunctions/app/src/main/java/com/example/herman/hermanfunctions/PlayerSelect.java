package com.example.herman.hermanfunctions;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.herman.hermanfunctions.Info.Data;

import java.util.ArrayList;

/**
 * Created by Herman on 2015-08-12.
 */
public class PlayerSelect extends AppCompatActivity {

    private Data data = Data.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playerselect);

        ListView listOfPlayers = (ListView) findViewById(R.id.playerList);
        //ArrayList<String> players = new ArrayList<String>();
        ArrayList<String> players = displayPlatyers();
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
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                if (data.getFunctionType() == "Score") {
                    data.setSelectedPlayer(data.getSelectedTeam().getOnField().get(position));
                    data.getSelectedTeam().addScore(data.getSelectedScoreType());
                    switch (data.getSelectedScoreType()) {
                        case "Try":
                            //AlertDialog.Builder alertDialog = new AlertDialog.Builder(AlertDialogActivity.this);
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(PlayerSelect.this);

                            // Setting Dialog Title
                            alertDialog.setTitle("Successful try..");

                            // Setting Dialog Message
                            alertDialog.setMessage("Scored a conversion kick?");

                            //Setting Negative "NO" Button
                            alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // Write your code here to invoke NO event
                                    Intent intent = new Intent(new Intent(PlayerSelect.this, MainActivity.class));
                                    startActivity(intent);
                                }
                            });

                            // Setting Positive "Yes" Button
                            alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    // Write your code here to invoke YES event
                                    Intent intent = new Intent(new Intent(PlayerSelect.this, PlayerSelect.class));
                                    data.setSelectedScoreType("Conversion Kick");
                                    startActivity(intent);
                                }
                            });

                            // Showing Alert Message
                            alertDialog.show();
                            break;
                        case "Conversion Kick":
                        case "Penalty Kick":
                        case "Drop Kick":
                            Intent intent = new Intent(new Intent(PlayerSelect.this, MainActivity.class));
                            startActivity(intent);
                            break;
                    }
                }
                else if (data.getFunctionType() == "Substitution") {
                    if (data.getOnFieldPlayers())
                    {
                        data.setOnFieldPlayers(false);
                        data.setSelectedPlayer(data.getSelectedTeam().getOnField().get(position));
                        Intent intent = new Intent(new Intent(PlayerSelect.this, PlayerSelect.class));
                        startActivity(intent);
                    }
                    else {
                        data.getSelectedTeam().subPlayers(data.getSelectedPlayer(), data.getSelectedTeam().getReserves().get(position));
                        data.setSelectedPlayer(data.getSelectedTeam().getReserves().get(position));
                        data.setOnFieldPlayers(true);
                        Intent intent = new Intent(new Intent(PlayerSelect.this, MainActivity.class));
                        startActivity(intent);
                    }
                }
            }
        });
    }

    public ArrayList<String> displayPlatyers()
    {
        ArrayList<String> _players = new ArrayList<String>();
        int num = 15;

        if (data.getOnFieldPlayers() && data.getSelectedTeam() != null) {
            if (data.getFunctionType() == "Score")
                num = 16;

            for (int i = 0; i < num; i++) {
                if (data.getSelectedTeam().getOnField().get(i).getJerseyNum() == 0)
                    _players.add("?? " + data.getSelectedTeam().getOnField().get(i).getPlayerName());
                else
                    _players.add(data.getSelectedTeam().getOnField().get(i).getJerseyNum() + " " + data.getSelectedTeam().getOnField().get(i).getPlayerName());
            }
        }
        else if (!data.getOnFieldPlayers() && data.getSelectedTeam() != null)
            for(int i = 0; i < data.getSelectedTeam().getReserves().size(); i++) {
                if (data.getSelectedTeam().getReserves().get(i).getJerseyNum() == 0)
                    _players.add("?? " + data.getSelectedTeam().getReserves().get(i).getPlayerName());
                else
                    _players.add(data.getSelectedTeam().getReserves().get(i).getJerseyNum() + " " + data.getSelectedTeam().getReserves().get(i).getPlayerName());
            }
        return _players;
    }
}