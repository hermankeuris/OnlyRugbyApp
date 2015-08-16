package com.example.herman.ConPlayerSelect;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.herman.Info.Data;
import com.example.herman.scoring.R;
import com.example.herman.scoring.Scoring;

import java.util.ArrayList;

/**
 * Created by Herman on 2015-08-16.
 */
public class ConPlayerSelect extends AppCompatActivity {

    private Data data = Data.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conplayerselect);

        ListView listOfPlayers = (ListView) findViewById(R.id.conPlayerList);
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
                data.getSelectedTeam().addScore(data.getSelectedScoreType());
                Intent intent = new Intent(new Intent(ConPlayerSelect.this, Scoring.class));
                    startActivity(intent);
            }
        });
    }
}
