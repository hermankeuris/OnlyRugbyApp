package com.example.herman.or_demo_2_withscoringandsubs;

import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;

/**
 * Created by Estian on 8/11/2015.
 */
public class GameClock
{
    private boolean secondHalf = false;
    private boolean started = false;
    private long pauseTimeElapsed = 0;
    private long timeElapsed = 0;
    private boolean running = false;
    public Chronometer chronometer;
    private boolean halfReset = false;

    public GameClock(Chronometer c)
    {
        this.chronometer=c;
    }

    public void start()
    {
        chronometer.setBase(SystemClock.elapsedRealtime()+pauseTimeElapsed);
        //if pauseTimeElapsed > 0 then log maybe?
        started = true;
        running = true;

        chronometer.start();
    }

    public void stop()
    {
        chronometer.stop();
        running = false;
        pauseTimeElapsed = chronometer.getBase() - SystemClock.elapsedRealtime();
    }

    public void reset()
    {
        if(running())stop();
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseTimeElapsed = 0;
        started = false;
        running = false;

    }

    public long getCurrentClockTime()
    {
        return SystemClock.elapsedRealtime()-chronometer.getBase();
    }

    public void setCurrentTime(long time)
    {
        pauseTimeElapsed = time;
        chronometer.setBase(SystemClock.elapsedRealtime() - pauseTimeElapsed);
        pauseTimeElapsed = 0;
    }
    public boolean running()
    {
        return running;
    }

    public boolean started()
    {
        return started;
    }

    public boolean isSecondHalf()
    {
        return secondHalf;
    }

    public void switchHalf(View v)
    {

        secondHalf = true;

        v.postInvalidate();
    }

    public void halfReset()
    {
        if(running())stop();
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseTimeElapsed = 0;
        running = false;
        halfReset = true;
    }

    public boolean isHalfReset()
    {
        return halfReset;
    }
}
