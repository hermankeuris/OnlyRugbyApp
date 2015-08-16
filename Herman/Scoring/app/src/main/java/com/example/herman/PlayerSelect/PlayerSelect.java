package com.example.herman.PlayerSelect;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.herman.ConPlayerSelect.ConPlayerSelect;
import com.example.herman.Info.Data;
import com.example.herman.scoring.R;
import com.example.herman.scoring.Scoring;

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
        ArrayList<String> players = new ArrayList<String>();
        ArrayAdapter<String> playersAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, players);
        listOfPlayers.setAdapter(playersAdapter);

        for(int i = 0; i < 15; i++)
        {
            players.add(data.getSelectedTeam().getOnField().get(i).getJerseyNum() + " " + data.getSelectedTeam().getOnField().get(i).getPlayerName());
        }

        listOfPlayers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                data.setSelectedPlayer(data.getSelectedTeam().getOnField().get(position));
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
                                Intent intent = new Intent(new Intent(PlayerSelect.this, Scoring.class));
                                startActivity(intent);
                            }
                        });

                        // Setting Positive "Yes" Button
                        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                // Write your code here to invoke YES event
                                Intent intent = new Intent(new Intent(PlayerSelect.this, ConPlayerSelect.class));
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
                        Intent intent = new Intent(new Intent(PlayerSelect.this, Scoring.class));
                        startActivity(intent);
                        break;
                }
                data.getSelectedTeam().addScore(data.getSelectedScoreType());
            }
        });
    }
}