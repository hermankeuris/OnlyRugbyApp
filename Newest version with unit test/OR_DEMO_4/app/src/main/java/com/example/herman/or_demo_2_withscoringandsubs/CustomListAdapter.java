package com.example.herman.or_demo_2_withscoringandsubs;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.onlyrugbyDemo2.R;
import com.example.herman.or_demo_2_withscoringandsubs.Info.Data;

import java.util.List;

/**
 * Created by Herman on 2015-10-01.
 */
public class CustomListAdapter extends ArrayAdapter {

    private Context currentContext;
    private int adapterId;
    private List<String> items ;
    private Data data = Data.getInstance();

    public CustomListAdapter(Context context, int id, List<String> list)
    {
        super(context, id, list);
        currentContext = context;
        adapterId = id;
        items = list ;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        View currentView = view ;
        if(currentView == null){
            LayoutInflater vi = (LayoutInflater)currentContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            currentView = vi.inflate(adapterId, null);
        }

        TextView text = (TextView) currentView.findViewById(R.id.textView);

        if(items.get(position) != null )
        {
            text.setTextColor(Color.BLACK);
            text.setText(items.get(position));
            //text.setBackgroundColor(Color.RED);
            if (data.getFunctionType() != "Help") {
                if (data.getSelectedTeam().getOnField().get(position).getRedCard() && data.getOnFieldPlayers()) {
                    text.setBackgroundColor(Color.argb(200, 255, 64, 64));
                } else if (data.getSelectedTeam().getOnField().get(position).getYellowCard() && data.getOnFieldPlayers()) {
                    text.setBackgroundColor(Color.argb(200, 255, 255, 64));
                }
            }
        }
        return currentView;
    }

}
