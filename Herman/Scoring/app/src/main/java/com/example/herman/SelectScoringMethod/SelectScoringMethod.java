package com.example.herman.SelectScoringMethod;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.herman.PlayerSelect.PlayerSelect;
import com.example.herman.scoring.R;

/**
 * Created by Herman on 2015-08-12.
 */

public class SelectScoringMethod extends AppCompatActivity{

    public static String selectedScoreType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectscoringmethod);

        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(new Intent(SelectScoringMethod.this, PlayerSelect.class));
                startActivity(intent);

                switch(v.getId()){
                    case R.id.Try:
                        selectTry();
                        break;
                    case R.id.PenaltyKick:
                        selectPenKick();
                        break;
                    case R.id.DropKick:
                        selectDropKick();
                        break;
                }
            }
        };
        Button tryBtn =(Button) findViewById(R.id.Try);
        Button penKickBtn =(Button) findViewById(R.id.PenaltyKick);
        Button dropKickBtn =(Button) findViewById(R.id.DropKick);
        tryBtn.setOnClickListener(listener);
        penKickBtn.setOnClickListener(listener);
        dropKickBtn.setOnClickListener(listener);
    }

    public void selectTry()
    {
        selectedScoreType = "Try";
    }

    public void selectPenKick()
    {
        selectedScoreType = "Penalty Kick";
    }

    public void selectDropKick()
    {
        selectedScoreType = "Drop Kick";
    }
}