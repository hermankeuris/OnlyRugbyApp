package com.example.herman.or_demo_2_withscoringandsubs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.onlyrugbyDemo2.R;
import com.example.herman.or_demo_2_withscoringandsubs.Info.Data;

import java.util.ArrayList;

/**
 * Created by Muller on 26/08/2015.
 */
public class helpList extends Activity {

    private Data data = Data.getInstance();
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helplist);

        ListView list = (ListView) findViewById(R.id.helpList);
        ArrayList<String> helpTopics = new ArrayList<String>();
        ArrayAdapter<String> helpAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, helpTopics);
        list.setAdapter(helpAdapter);




        helpTopics.add("1   Getting Started");
        helpTopics.add("2   Tries");
        helpTopics.add("3   Penalty-Kicks");
        helpTopics.add("4   Drop-Kicks");
        helpTopics.add("5   Substitutions");
        helpTopics.add("6   Rucks");
        helpTopics.add("7   Line-outs");
        helpTopics.add("8   Discipline");
        helpTopics.add("9   Turnovers");

        helpTopics.add("10  The timer");

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                TextView helpText;

                switch (position)
                {
                    case 0:
                        setContentView(R.layout.helppage);
                        helpText = (TextView) findViewById(R.id.helpPageText);

                        message = "1. getting started";
                        helpText.setText(message);
                        break;
                    case 1:
                        setContentView(R.layout.helppage);
                        helpText = (TextView) findViewById(R.id.helpPageText);

                        message = "1. Press Try button \n" +
                                "2. Select on of the team who scrored the try \n" +
                                "3. Select the player who scored the try \n" +
                                "4. A popup asking if it was a conversion kick will appear \n" +
                                "5. Select yes if a conversion kick occurred, no otherwise \n" +
                                "6. If yes, select the player";
                        helpText.setText(message);
                        break;
                    case 2:
                        setContentView(R.layout.helppage);
                        helpText = (TextView) findViewById(R.id.helpPageText);

                        message = "1. Press Penalty-Kick button \n" +
                                "2. Select on of the team who scrored the penalty-kick \n" +
                                "3. Select the player who scored the penalty-kick \n";
                        helpText.setText(message);
                        break;
                    case 3:
                        setContentView(R.layout.helppage);
                        helpText = (TextView) findViewById(R.id.helpPageText);

                        message = "1. Press Drop-Kick button \n" +
                                "2. Select on of the team who scrored the drop-kick \n" +
                                "3. Select the player who scored the drop-kick \n";
                        helpText.setText(message);
                        break;
                    case 4:
                        setContentView(R.layout.helppage);
                        helpText = (TextView) findViewById(R.id.helpPageText);

                        message = "1. Press Substitute button \n" +
                                "2. Select on of the team who had a substitution \n" +
                                "3. Select the on-field player went off field \n" +
                                "4. Select the off-field player that came on \n";
                        helpText.setText(message);
                        break;
                    case 5:
                        setContentView(R.layout.helppage);
                        helpText = (TextView) findViewById(R.id.helpPageText);

                        message = "1. Ruck \n";
                        helpText.setText(message);
                        break;
                    case 6:
                        setContentView(R.layout.helppage);
                        helpText = (TextView) findViewById(R.id.helpPageText);

                        message = "1. Line out \n";
                        helpText.setText(message);
                        break;
                    case 7:
                        setContentView(R.layout.helppage);
                        helpText = (TextView) findViewById(R.id.helpPageText);

                        message = "1. Press Discipline button \n" +
                                "2. Select on of the team whose player received a card\n" +
                                "3. Select the player who received the card \n" +
                                "4. Select card that was given \n";
                        helpText.setText(message);
                        break;
                    case 8:
                        setContentView(R.layout.helppage);
                        helpText = (TextView) findViewById(R.id.helpPageText);

                        message = "1. Press Turnover button \n" +
                                  "2. Select the team that won the turnover\n";
                        helpText.setText(message);
                        break;
                }


                }
            }

            );
        }

        @Override
    protected void onPause()
    {
        super.onPause();
        finish();
    }
}