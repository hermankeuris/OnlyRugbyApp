package com.example.herman.or_demo_2_withscoringandsubs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.onlyrugbyDemo2.R;
import com.example.herman.or_demo_2_withscoringandsubs.Info.Data;
import com.example.herman.or_demo_2_withscoringandsubs.Info.Event;

public class createNewEvent extends Activity
{
    private Data data = Data.getInstance();

    private String message;

    private Button tryBtn;
    private Button penaltyBtn;
    private Button dropBtn;
    private Button scrumBtn;
    private Button substituteBtn;
    private Button disciplineBtn;
    private Button turnoverBtn;
    private Button lineOutBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        tryBtn = (Button) findViewById(R.id.tryBtn);
        penaltyBtn = (Button) findViewById(R.id.penaltyBtn);
        dropBtn = (Button) findViewById(R.id.dropBtn);
        lineOutBtn = (Button) findViewById(R.id.lineOutBtn);
        scrumBtn = (Button) findViewById(R.id.scrumBtn);
        substituteBtn = (Button) findViewById(R.id.substituteBtn);
        disciplineBtn = (Button) findViewById(R.id.disciplineBtn);
        turnoverBtn = (Button) findViewById(R.id.turnoverBtn);

        View.OnClickListener listener= new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(new Intent(createNewEvent.this, TeamSelect.class));
                EditText et;

                switch(v.getId()) {

                    case R.id.penaltyBtn:
                        data.setFunctionType("Score");
                        data.setSelectedScoreType("Penalty Kick");

                        et = (EditText) findViewById(R.id.timeText);

                        if(isValidString(et.getText().toString())) {
                            data.setCustomTimeStamp(true);
                            data.setCustomTimeStampString(et.getText().toString());
                        }
                        else
                        {
                            data.setCustomTimeStamp(false);
                            Toast.makeText(getApplicationContext(), "Invalid string, set to current time" , Toast.LENGTH_SHORT).show();
                        }
                        startActivity(intent);
                        break;
                    case R.id.tryBtn:
                        data.setFunctionType("Score");
                        data.setSelectedScoreType("Try");
                        et = (EditText) findViewById(R.id.timeText);

                        if(isValidString(et.getText().toString())) {
                            data.setCustomTimeStamp(true);
                            data.setCustomTimeStampString(et.getText().toString());
                        }
                        else
                        {
                            data.setCustomTimeStamp(false);
                            Toast.makeText(getApplicationContext(), "Invalid string, set to current time" , Toast.LENGTH_SHORT).show();
                        }
                        startActivity(intent);
                        break;
                    case R.id.dropBtn:
                        data.setFunctionType("Score");
                        data.setSelectedScoreType("Drop Kick");
                        et = (EditText) findViewById(R.id.timeText);

                        if(isValidString(et.getText().toString())) {
                            data.setCustomTimeStamp(true);
                            data.setCustomTimeStampString(et.getText().toString());
                        }
                        else
                        {
                            data.setCustomTimeStamp(false);
                            Toast.makeText(getApplicationContext(), "Invalid string, set to current time" , Toast.LENGTH_SHORT).show();
                        }
                        startActivity(intent);
                        break;
                    case R.id.substituteBtn:
                        data.setFunctionType("Substitution");
                        et = (EditText) findViewById(R.id.timeText);

                        if(isValidString(et.getText().toString())) {
                            data.setCustomTimeStamp(true);
                            data.setCustomTimeStampString(et.getText().toString());
                        }
                        else
                        {
                            data.setCustomTimeStamp(false);
                            Toast.makeText(getApplicationContext(), "Invalid string, set to current time" , Toast.LENGTH_SHORT).show();
                        }
                        startActivity(intent);
                        break;
                    case R.id.disciplineBtn:
                        data.setFunctionType("Discipline");
                        et = (EditText) findViewById(R.id.timeText);

                        if(isValidString(et.getText().toString())) {
                            data.setCustomTimeStamp(true);
                            data.setCustomTimeStampString(et.getText().toString());
                        }
                        else
                        {
                            data.setCustomTimeStamp(false);
                            Toast.makeText(getApplicationContext(), "Invalid string, set to current time" , Toast.LENGTH_SHORT).show();
                        }
                        startActivity(intent);
                        break;
                    case R.id.turnoverBtn:
                        data.setFunctionType("Turnover");
                        et = (EditText) findViewById(R.id.timeText);

                        if(isValidString(et.getText().toString())) {
                            data.setCustomTimeStamp(true);
                            data.setCustomTimeStampString(et.getText().toString());
                        }
                        else
                        {
                            data.setCustomTimeStamp(false);
                            Toast.makeText(getApplicationContext(), "Invalid string, set to current time" , Toast.LENGTH_SHORT).show();
                        }
                        startActivity(intent);
                        break;
                    case R.id.lineoutBtn:
                        data.setFunctionType("LineOut");

                        et = (EditText) findViewById(R.id.timeText);

                        if(isValidString(et.getText().toString())) {
                            data.setCustomTimeStamp(true);
                            data.setCustomTimeStampString(et.getText().toString());
                        }
                        else
                        {
                            data.setCustomTimeStamp(false);
                            Toast.makeText(getApplicationContext(), "Invalid string, set to current time" , Toast.LENGTH_SHORT).show();
                        }
                        startActivity(intent);
                        break;
                    case R.id.scrumBtn:
                        data.setFunctionType("Scrum");
                        et = (EditText) findViewById(R.id.timeText);

                        if(isValidString(et.getText().toString())) {
                            data.setCustomTimeStamp(true);
                            data.setCustomTimeStampString(et.getText().toString());
                        }
                        else
                        {
                            data.setCustomTimeStamp(false);
                            Toast.makeText(getApplicationContext(), "Invalid string, set to current time" , Toast.LENGTH_SHORT).show();
                        }
                        startActivity(intent);
                        break;
                    case R.id.lineOutBtn:
                        data.setFunctionType("LineOut");

                        et = (EditText) findViewById(R.id.timeText);

                        if(isValidString(et.getText().toString())) {
                            data.setCustomTimeStamp(true);
                            data.setCustomTimeStampString(et.getText().toString());
                        }
                        else
                        {
                            data.setCustomTimeStamp(false);
                            Toast.makeText(getApplicationContext(), "Invalid string, set to current time" , Toast.LENGTH_SHORT).show();
                        }
                        startActivity(intent);
                        break;
                    default:
                        data.setFunctionType("");


                        break;

                }

                data.setEndOfFunction(true);
                finish();
            }
        };

        tryBtn.setOnClickListener(listener);
        penaltyBtn.setOnClickListener(listener);
        dropBtn.setOnClickListener(listener);
        lineOutBtn.setOnClickListener(listener);
        scrumBtn.setOnClickListener(listener);
        substituteBtn.setOnClickListener(listener);
        disciplineBtn.setOnClickListener(listener);
        turnoverBtn.setOnClickListener(listener);
    }

    public Boolean isValidString(String time)
    {
        String inTime = time;
        String hours, minutes,seconds;
        int intHours, intMinutes, intSeconds;
        int j = 0;

        if(time == null || time.equals("") || time.equals("::"))
            return false;

        for(int i = 0; i < time.length(); i++)
        {
            if(time.charAt(i) == ':')
                j++;
        }

        if( j != 2)
            return false;

        hours = time.substring(0, time.indexOf(":"));
        time = time.substring(time.indexOf(":") + 1);
        minutes = time.substring(0, time.indexOf(":"));
        time = time.substring(time.indexOf(":") + 1);
        seconds = time;

        intHours = Integer.parseInt(hours);
        intMinutes = Integer.parseInt(minutes);
        intSeconds = Integer.parseInt(seconds);

        Event startTime = data.getEventAtIndex(0);
        String startTimeStamp = startTime.getDescription().substring(0,8);

        if(inTime.compareTo(startTimeStamp) < 0)
            return false;

        if(intHours > 23)
            return false;
        else if(intMinutes > 59)
            return false;
        else if(intSeconds > 59)
            return false;
        else
            return true;
    }
}