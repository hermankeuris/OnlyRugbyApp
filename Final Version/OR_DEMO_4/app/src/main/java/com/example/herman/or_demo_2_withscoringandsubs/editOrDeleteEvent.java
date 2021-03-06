package com.example.herman.or_demo_2_withscoringandsubs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.onlyrugbyDemo2.R;
import com.example.herman.or_demo_2_withscoringandsubs.Info.Data;
import com.example.herman.or_demo_2_withscoringandsubs.Info.Event;

/**
 * Created by Muller on 8/22/2015.
 */
public class editOrDeleteEvent extends Activity
{
    //public static String selectedScoreType;
    private Data data = Data.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_or_delete_event);

        //TextView disciplineText = (TextView) findViewById(R.id.disciplineText);
        //disciplineText.setText("Give the following card to " + String.valueOf(data.getSelectedPlayer().getPlayerName()) + ":");

        data.setEndOfFunction(false);

        View.OnClickListener listener= new View.OnClickListener()
        {
            int playerIndexOne, playerIndexTwo;
            @Override
            public void onClick(View v)
            {
                // int index1, index2;

                switch(v.getId())
                {
                    case R.id.alterEventButton:
                        int index1 = data.getEventIndex();
                        Event temp1 = data.getEventAtIndex(index1);

                        Intent intent = new Intent(new Intent(editOrDeleteEvent.this, TeamSelect.class));
                        data.setAlteredAt(index1);

                        System.out.println("Altered at " + index1);
                        data.setSwapIndex(index1);
                        data.setSwapTimestamp(temp1.getTimeStamp());


                        if(temp1.getEvent().equals("Try"))
                        {
                            data.setFunctionType("Score");
                            data.setSelectedScoreType("Try");
                            startActivity(intent);
                        }
                        else if(temp1.getEvent().equals("TryConversion"))
                        {
                            data.setFunctionType("Score");
                            data.setSelectedScoreType("Try");
                            startActivity(intent);
                        }
                        else if(temp1.getEvent().equals("PenaltyKick"))
                        {
                            data.setFunctionType("Score");
                            data.setSelectedScoreType("Penalty Kick");
                            startActivity(intent);
                        }
                        else if(temp1.getEvent().equals("DropKick"))
                        {
                            data.setFunctionType("Score");
                            data.setSelectedScoreType("Drop Kick");
                            startActivity(intent);
                        }
                        else if(temp1.getEvent().equals("LineOut"))//?? how to increment/decrement lossess
                        {
                            data.setFunctionType("LineOut");
                            startActivity(intent);
                        }
                        else if(temp1.getEvent().equals("Turnover"))
                        {
                            data.setFunctionType("Turnover");
                            startActivity(intent);
                        }
                        else if(temp1.getEvent().equals("Substitution"))
                        {
                            data.setFunctionType("Substitution");
                            startActivity(intent);
                        }
                        else if(temp1.getEvent().equals("Discipline"))
                        {
                            data.setFunctionType("Discipline");
                            startActivity(intent);
                        }
                        else if(temp1.getEvent().equals("Scrum"))
                        {
                            data.setFunctionType("Scrum");
                            startActivity(intent);
                        }

                        data.setEndOfFunction(true);
                        finish();
                        break;
                    //******************************************************************************************************************
                    case R.id.deleteEventBtn:
                        //AlertDialog.Builder alertDialog = new AlertDialog.Builder(AlertDialogActivity.this);
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(editOrDeleteEvent.this);

                        //alertDialog.setCancelable(false);

                        // Setting Dialog Title
                        alertDialog.setTitle("Deleting event..");

                        // Setting Dialog Message
                        alertDialog.setMessage("Are you sure you want to delete this event?");

                        // Setting Positive "Yes" Button
                        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which)
                            {

                                int index = data.getEventIndex();
                                Event temp = data.getEventAtIndex(index);

                                if(temp.getEvent().equals("Try")) {
                                    if(temp.getTeamOne().hashCode() == data.getTeamOne().hashCode()) {
                                        System.out.println("Team " + temp.getTeamOne().getTeamName());

                                        data.getTeamOne().decreaseTries();
                                        data.getTeamOne().decreaseScore(5);

                                        for (int i = 0; i < data.getTeamOne().getPlayers().size(); i++) {
                                            if (temp.getPlayerOne().hashCode() == data.getTeamOne().getPlayers().get(i).hashCode()) {
                                                System.out.println("Player " + temp.getPlayerOne().getPlayerName());
                                                playerIndexOne = i;
                                            }
                                        }

                                        data.getTeamOne().getPlayers().get(playerIndexOne).decreaseTries();
                                    }
                                    else
                                    {
                                        data.getTeamTwo().decreaseTries();
                                        data.getTeamTwo().decreaseScore(5);

                                        for(int i = 0; i < data.getTeamTwo().getPlayers().size(); i++)
                                        {
                                            if(temp.getPlayerOne().hashCode() == data.getTeamTwo().getPlayers().get(i).hashCode()) {
                                                playerIndexOne = i;
                                            }
                                        }

                                        data.getTeamTwo().getPlayers().get(playerIndexOne).decreaseTries();
                                    }
                                }
                                else if(temp.getEvent().equals("TryConversion"))
                                {
                                    if(temp.getTeamOne().hashCode() == data.getTeamOne().hashCode())
                                    {
                                        data.getTeamOne().decreaseTries();
                                        data.getTeamOne().decreaseConversionKicks();
                                        data.getTeamOne().decreaseScore(7);

                                        for(int i = 0; i < data.getTeamOne().getPlayers().size(); i++)
                                        {
                                            if(temp.getPlayerOne().hashCode() == data.getTeamOne().getPlayers().get(i).hashCode()) {
                                                playerIndexOne = i;
                                            }
                                        }

                                        data.getTeamOne().getPlayers().get(playerIndexOne).decreaseTries();

                                        for(int i = 0; i < data.getTeamOne().getPlayers().size(); i++)
                                        {
                                            if(temp.getPlayerTwo().hashCode() == data.getTeamOne().getPlayers().get(i).hashCode()) {
                                                playerIndexOne = i;
                                            }
                                        }
                                        data.getTeamOne().getPlayers().get(playerIndexTwo).decreaseConversionKicks();
                                    }
                                    else
                                    {
                                        data.getTeamTwo().decreaseTries();
                                        data.getTeamTwo().decreaseConversionKicks();
                                        data.getTeamTwo().decreaseScore(7);

                                        for(int i = 0; i < data.getTeamTwo().getPlayers().size(); i++)
                                        {
                                            if(temp.getPlayerOne().hashCode() == data.getTeamTwo().getPlayers().get(i).hashCode()) {
                                                playerIndexOne = i;
                                            }
                                        }

                                        data.getTeamTwo().getPlayers().get(playerIndexOne).decreaseTries();

                                        for(int i = 0; i < data.getTeamOne().getPlayers().size(); i++)
                                        {
                                            if(temp.getPlayerTwo().hashCode() == data.getTeamTwo().getPlayers().get(i).hashCode()) {
                                                playerIndexOne = i;
                                            }
                                        }

                                        data.getTeamOne().getPlayers().get(playerIndexTwo).decreaseConversionKicks();
                                    }
                                }
                                if(temp.getEvent().equals("PenaltyKick"))
                                {
                                    if(temp.getTeamOne().hashCode() == data.getTeamOne().hashCode())
                                    {
                                        data.getTeamOne().decreasePenaltyKicks();
                                        data.getTeamOne().decreaseScore(3);

                                        for(int i = 0; i < data.getTeamOne().getPlayers().size(); i++)
                                        {
                                            if(temp.getPlayerOne().hashCode() == data.getTeamOne().getPlayers().get(i).hashCode()) {
                                                playerIndexOne = i;
                                            }
                                        }

                                        data.getTeamOne().getPlayers().get(playerIndexOne).decreasePenaltyKicks();
                                    }
                                    else
                                    {
                                        data.getTeamTwo().decreasePenaltyKicks();
                                        data.getTeamTwo().decreaseScore(3);

                                        for(int i = 0; i < data.getTeamTwo().getPlayers().size(); i++)
                                        {
                                            if(temp.getPlayerOne().hashCode() == data.getTeamTwo().getPlayers().get(i).hashCode()) {
                                                playerIndexOne = i;
                                            }
                                        }

                                        data.getTeamTwo().getPlayers().get(playerIndexOne).decreasePenaltyKicks();
                                    }
                                }
                                else if(temp.getEvent().equals("DropKick"))
                                {
                                    if(temp.getTeamOne().hashCode() == data.getTeamOne().hashCode())
                                    {
                                        data.getTeamOne().decreaseDropKicks();
                                        data.getTeamOne().decreaseScore(3);

                                        for(int i = 0; i < data.getTeamOne().getPlayers().size(); i++)
                                        {
                                            if(temp.getPlayerOne().hashCode() == data.getTeamOne().getPlayers().get(i).hashCode()) {
                                                playerIndexOne = i;
                                            }
                                        }

                                        data.getTeamOne().getPlayers().get(playerIndexOne).decreaseDropKicks();
                                    }
                                    else
                                    {
                                        data.getTeamTwo().decreaseDropKicks();
                                        data.getTeamTwo().decreaseScore(3);

                                        for(int i = 0; i < data.getTeamTwo().getPlayers().size(); i++)
                                        {
                                            if(temp.getPlayerOne().hashCode() == data.getTeamTwo().getPlayers().get(i).hashCode()) {
                                                playerIndexOne = i;
                                            }
                                        }

                                        data.getTeamTwo().getPlayers().get(playerIndexOne).decreaseDropKicks();
                                    }
                                }
                                else if(temp.getEvent().equals("Scrum"))
                                {
                                    if(temp.getTeamOne().hashCode() == data.getTeamOne().hashCode())
                                    {
                                        data.getTeamOne().decreaseScrumsWon();
                                        data.getTeamTwo().decreaseScrumsLost();
                                    }
                                    else
                                    {
                                        System.out.println("Team " + temp.getTeamOne().getTeamName());

                                        data.getTeamTwo().decreaseScrumsWon();
                                        data.getTeamOne().decreaseScrumsLost();
                                    }
                                }
                                else if(temp.getEvent().equals("LineOut"))//?? how to increment/decrement lossess
                                {
                                    if(temp.getTeamOne().hashCode() == data.getTeamOne().hashCode())
                                    {
                                        //data.getTeamOne().decreaseRuckWon();
                                        //data.getTeamTwo().decreaseRuckLost();
                                    }
                                    else
                                    {
                                        //data.getTeamTwo().decreaseRuckWon();
                                        //data.getTeamOne().decreaseRuckLost();
                                    }
                                }
                                else if(temp.getEvent().equals("Discipline"))
                                {
                                    if (temp.getTeamOne().hashCode() == data.getTeamOne().hashCode()) {
                                        for (int i = 0; i < data.getTeamOne().getPlayers().size(); i++) {
                                            if (temp.getPlayerOne().hashCode() == data.getTeamOne().getPlayers().get(i).hashCode()) {
                                                playerIndexOne = i;
                                            }
                                        }

                                        if (data.getTeamOne().getPlayers().get(playerIndexOne).getYellowCard()) {
                                            data.getTeamOne().getPlayers().get(playerIndexOne).removeYellowCard();
                                        } else
                                        {
                                            data.getTeamOne().getPlayers().get(playerIndexOne).removeRedCard();
                                        }
                                    } else {
                                        System.out.println("Team " + temp.getTeamOne().getTeamName());

                                        for (int i = 0; i < data.getTeamOne().getPlayers().size(); i++) {
                                            if (temp.getPlayerOne().hashCode() == data.getTeamTwo().getPlayers().get(i).hashCode()) {
                                                playerIndexOne = i;
                                            }
                                        }

                                        if (data.getTeamTwo().getPlayers().get(playerIndexOne).getYellowCard()) {
                                            data.getTeamTwo().getPlayers().get(playerIndexOne).removeYellowCard();
                                        } else
                                        {System.out.println("rrrrrrrrrrrrrrr" + data.getTeamTwo().getPlayers().get(playerIndexOne).getName());
                                            data.getTeamTwo().getPlayers().get(playerIndexOne).removeRedCard();
                                        }
                                    }
                                }
                                else if(temp.getEvent().equals("Turnover"))
                                {
                                    if(temp.getTeamOne().hashCode() == data.getTeamOne().hashCode())
                                    {
                                        data.getTeamOne().decreaseTurnoverWon();
                                        data.getTeamTwo().decreaseTurnoverLost();
                                    }
                                    else
                                    {
                                        data.getTeamTwo().decreaseTurnoverWon();
                                        data.getTeamOne().decreaseTurnoverLost();
                                    }
                                }
                                else if(temp.getEvent().equals("Substitution"))
                                {
                                    if(temp.getTeamOne().hashCode() == data.getTeamOne().hashCode())
                                    {
                                        for(int i = 0; i < data.getTeamOne().getPlayers().size(); i++)
                                        {
                                            if(temp.getPlayerTwo().hashCode() == data.getTeamOne().getPlayers().get(i).hashCode()) {
                                                playerIndexOne = i;
                                            }
                                        }

                                        for(int i = 0; i < data.getTeamOne().getPlayers().size(); i++)
                                        {
                                            if(temp.getPlayerOne().hashCode() == data.getTeamOne().getPlayers().get(i).hashCode()) {
                                                playerIndexTwo = i;
                                            }
                                        }

                                        //data.getTeamOne().swapOnFieldAndReserves(playerIndexOne, playerIndexTwo);
                                    }
                                    else
                                    {
                                        for (int i = 0; i < data.getTeamOne().getPlayers().size(); i++) {
                                            if (temp.getPlayerTwo().hashCode() == data.getTeamTwo().getPlayers().get(i).hashCode()) {
                                                playerIndexOne = i;
                                            }
                                        }

                                        for (int i = 0; i < data.getTeamTwo().getPlayers().size(); i++) {
                                            if (temp.getPlayerOne().hashCode() == data.getTeamTwo().getPlayers().get(i).hashCode()) {
                                                playerIndexTwo = i;
                                            }
                                        }

                                        //data.getTeamTwo().swapOnFieldAndReserves(playerIndexOne, playerIndexTwo);
                                    }
                                }

                                data.removeEventAtIndex(index);
                                data.setDeletedAt(index);
                                data.setEndOfFunction(true);
                                finish();
                            }
                        });

                        // Intent intent = new Intent(new Intent(editOrDeleteEvent.this, eventsList.class));
                        // Intent intent = data.getMainIntent();
                        //startActivity(intent);

                        //setContentView(R.layout.main);

                        //Setting Negative "NO" Button
                        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        // Showing Alert Message
                        alertDialog.show();
                        break;
                }

                /*data.setEndOfFunction(true);
                finish();*/
            }
        };

        Button deleteBtn =(Button) findViewById(R.id.deleteEventBtn);
        Button alterBtn =(Button) findViewById(R.id.alterEventButton);

        deleteBtn.setOnClickListener(listener);
        alterBtn.setOnClickListener(listener);

    }
}
