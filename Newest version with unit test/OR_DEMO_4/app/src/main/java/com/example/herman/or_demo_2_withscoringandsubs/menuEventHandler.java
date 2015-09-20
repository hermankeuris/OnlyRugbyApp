package com.example.herman.or_demo_2_withscoringandsubs;

import android.view.MenuItem;
import android.widget.PopupMenu;

import com.example.android.onlyrugbyDemo2.R;

/**
 * Created by Estian on 8/13/2015.
 */
public class menuEventHandler implements MenuItem.OnMenuItemClickListener, PopupMenu.OnMenuItemClickListener
{
    private GameClock chronometer;

    public menuEventHandler(GameClock chronometer)
    {
        this.chronometer=chronometer;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item)
    {
        long stoppedMilliseconds =0;
        switch (item.getItemId()) {
            case R.id.startgame:
                //chronometer.setBase(SystemClock.elapsedRealtime() - stoppedMilliseconds);
                //chronometer.start();
                return true;
            default:
                return false;
        }
    }

}
