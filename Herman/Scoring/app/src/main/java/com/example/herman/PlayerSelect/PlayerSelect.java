package com.example.herman.PlayerSelect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.herman.Info.Player;
import com.example.herman.scoring.R;
import com.example.herman.scoring.Scoring;
import com.example.herman.SelectScoringMethod.SelectScoringMethod;

import java.util.ArrayList;

/**
 * Created by Herman on 2015-08-12.
 */
public class PlayerSelect extends AppCompatActivity {

    public static Player selectedPlayer;

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
            players.add(Scoring.selectedTeam.getOnField().get(i).getJerseyNum() + " " + Scoring.selectedTeam.getOnField().get(i).getPlayerName());
        }


        listOfPlayers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                selectedPlayer = Scoring.selectedTeam.getOnField().get(position);
                switch(SelectScoringMethod.selectedScoreType)
                {
                    case "Try":
                        break;
                    case "Conversion Kick":
                    case "Penalty Kick":
                    case "Drop Kick":
                        Intent intent = new Intent(new Intent(PlayerSelect.this, Scoring.class));
                        startActivity(intent);
                        break;
                }
                Scoring.selectedTeam.addScore(SelectScoringMethod.selectedScoreType);

                /**
                String item = ((TextView) view).getText().toString();

                Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show();
                 **/

            }
        });

/**
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(new Intent(PlayerSelect.this, Scoring.class));
                startActivity(intent);
            }

            private void setupListViewListener() {
                lvItems.setOnItemLongClickListener(
                        new AdapterView.OnItemLongClickListener() {
                            @Override
                            public boolean onClick(AdapterView<?> adapter,
                                                           View item, int pos, long id) {
                                // Remove the item within array at position
                                items.remove(pos);
                                // Refresh the adapter
                                itemsAdapter.notifyDataSetChanged();
                                // Return true consumes the long click event (marks it handled)
                                return true;
                            }

                        });
        };**/
    }
}