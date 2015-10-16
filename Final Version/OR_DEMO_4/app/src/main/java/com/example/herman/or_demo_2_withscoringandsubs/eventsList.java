package com.example.herman.or_demo_2_withscoringandsubs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.onlyrugbyDemo2.R;
import com.example.herman.or_demo_2_withscoringandsubs.Info.Data;

import java.util.ArrayList;

/**
 * Created by Muller on 27/08/2015.
 */
public class eventsList extends Activity implements View.OnClickListener
{
    private Data data = Data.getInstance();

    private boolean started = false;

    public ArrayAdapter<String> eventsAdapter;
    private ArrayList<String> eventsList;

    Activity activity = this;

    private Button addNewEventBtn;

    private  String message;

    private ListView list;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        list = (ListView) findViewById(R.id.eventsList);
        eventsList = new ArrayList<String>();
        eventsAdapter = new CustomListAdapter(this , R.layout.custom_list , eventsList);

        System.out.println("eventsAdapter.toString() 1 " + eventsAdapter.toString());
        list.setAdapter(eventsAdapter);

        final int[] timesArr = data.getTimeEventIndexes();


        if(data.getAlterSwap())
        {
            System.out.println("swapAtIndexAndEnd");
            data.swapAtIndexAndEnd();
        }

        for(int i = 0; i < data.getEvents().size();i++)
        {
            eventsList.add(data.getDescriptionAt(i));
        }

        eventsAdapter.notifyDataSetChanged();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
                                    {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                                        {
                                            TextView helpText;
                                            if(position == timesArr[0] || position == timesArr[1] || position == timesArr[2])
                                                Toast.makeText(getApplicationContext(), "Time events cannot be altered or deleted", Toast.LENGTH_SHORT).show();
                                            else {
                                                Intent intent = new Intent(new Intent(eventsList.this, editOrDeleteEvent.class));

                                                data.setEventIndex(position);
                                                startActivity(intent);
                                            }
                                        }
                                    }



        );

        View.OnClickListener listener= new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                switch(v.getId())
                {
                    case R.id.addNewEventBtn:
                        Intent intent = new Intent(new Intent(eventsList.this, createNewEvent.class));

                        startActivity(intent);
                        break;
                }

                data.setEndOfFunction(true);
                finish();
            }
        };

        addNewEventBtn = (Button) findViewById(R.id.addNewEventBtn);
        eventsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.addNewEventBtn:
                Intent intent2 = new Intent(new Intent(eventsList.this, createNewEvent.class));
                startActivity(intent2);

                //this.eventsList.remove(0);
                eventsAdapter.notifyDataSetChanged();
                break;
        }
    }


    @Override
    public void onResume()
    {
        if(data.getAlterSwap())
        {
            //System.out.println("swapAtIndexAndEnd222");
            data.swapAtIndexAndEnd();
        }
        super.onResume();
        eventsList.clear();
        for(int i = 0; i < data.getEvents().size();i++)
        {
            eventsList.add(data.getDescriptionAt(i));
        }
        list.setAdapter(eventsAdapter);
        eventsAdapter.notifyDataSetChanged();
        data.setFunctionType("EventList");
    }
}