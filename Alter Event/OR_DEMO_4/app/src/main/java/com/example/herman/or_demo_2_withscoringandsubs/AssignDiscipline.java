package com.example.herman.or_demo_2_withscoringandsubs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.onlyrugbyDemo2.R;
import com.example.herman.or_demo_2_withscoringandsubs.Info.Data;

/**
 * Created by Muller on 8/22/2015.
 */
public class AssignDiscipline extends Activity
{
    //public static String selectedScoreType;
    private Data data = Data.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectdiscipline);

        TextView disciplineText = (TextView) findViewById(R.id.disciplineText);
        disciplineText.setText("Give the following card to " + String.valueOf(data.getSelectedPlayer().getPlayerName()) + ":");

        data.setEndOfFunction(false);

        View.OnClickListener listener= new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                switch(v.getId())
                {
                    case R.id.whiteCardButton:
                        data.addDiscipline(data.generateTimeStamp(), data.getSelectedTeam(), data.getSelectedPlayer(), "white");
                        data.getSelectedPlayer().giveWhiteCard();
                        break;
                    case R.id.yellowCardButton:
                        data.addDiscipline(data.generateTimeStamp(), data.getSelectedTeam(), data.getSelectedPlayer(), "yellow");
                        data.getSelectedPlayer().giveYellowCard();
                        break;
                    case R.id.redCardButton:
                        data.addDiscipline(data.generateTimeStamp(), data.getSelectedTeam(), data.getSelectedPlayer(), "red");
                        data.getSelectedPlayer().giveRedCard();
                        break;
                }

                data.setEndOfFunction(true);
                finish();
            }
        };

        Button redBtn =(Button) findViewById(R.id.redCardButton);
        Button yellowBtn =(Button) findViewById(R.id.yellowCardButton);
        Button whiteBtn =(Button) findViewById(R.id.whiteCardButton);
        redBtn.setOnClickListener(listener);
        yellowBtn.setOnClickListener(listener);
        whiteBtn.setOnClickListener(listener);
    }
}


