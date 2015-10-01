package com.example.herman.or_demo_2_withscoringandsubs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.onlyrugbyDemo2.R;
import com.example.herman.or_demo_2_withscoringandsubs.Info.Data;

public class createNewEvent extends Activity
{
    private Data data = Data.getInstance();

    private String message;

    private Button tryBtn;
    private Button penaltyBtn;
    private Button dropBtn;
    private Button lineoutBtn;
    private Button ruckBtn;
    private Button substituteBtn;
    private Button disciplineBtn;
    private Button turnoverBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        tryBtn = (Button) findViewById(R.id.tryBtn);
        penaltyBtn = (Button) findViewById(R.id.penaltyBtn);
        dropBtn = (Button) findViewById(R.id.dropBtn);
        lineoutBtn = (Button) findViewById(R.id.lineOutBtn);
        ruckBtn = (Button) findViewById(R.id.ruckBtn);
        substituteBtn = (Button) findViewById(R.id.substituteBtn);
        disciplineBtn = (Button) findViewById(R.id.disciplineBtn);
        turnoverBtn = (Button) findViewById(R.id.turnoverBtn);

        View.OnClickListener listener= new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(new Intent(createNewEvent.this, TeamSelect.class));

                switch(v.getId()) {

                    case R.id.penaltyBtn:
                        data.setFunctionType("Score");
                        data.setSelectedScoreType("Penalty Kick");
                        startActivity(intent);
                        break;
                    case R.id.tryBtn:
                        data.setFunctionType("Score");
                        data.setSelectedScoreType("Try");
                        startActivity(intent);
                        break;
                    case R.id.dropBtn:
                        data.setFunctionType("Score");
                        data.setSelectedScoreType("Drop Kick");
                        startActivity(intent);
                        break;
                    case R.id.substituteBtn:
                        data.setFunctionType("Substitution");
                        startActivity(intent);
                        break;
                    case R.id.disciplineBtn:
                        data.setFunctionType("Discipline");
                        startActivity(intent);
                        break;
                    case R.id.turnoverBtn:
                        data.setFunctionType("Turnover");
                        startActivity(intent);
                        break;
                    case R.id.ruckBtn:
                        data.setFunctionType("Ruck");
                        startActivity(intent);
                        break;
                    case R.id.lineoutBtn:
                        data.setFunctionType("LineOut");
                        startActivity(intent);
                        break;
                }

                data.setEndOfFunction(true);
                finish();
            }
        };

        tryBtn.setOnClickListener(listener);
        penaltyBtn.setOnClickListener(listener);
        dropBtn.setOnClickListener(listener);
        lineoutBtn.setOnClickListener(listener);
        ruckBtn.setOnClickListener(listener);
        substituteBtn.setOnClickListener(listener);
        disciplineBtn.setOnClickListener(listener);
        turnoverBtn.setOnClickListener(listener);
    }
}