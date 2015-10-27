package com.example.herman.or_demo_2_withscoringandsubs;

import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Estian on 8/11/2015.
 */
public class GameClock
{
    public boolean started = false;

    private boolean secondHalf = false;

    private boolean running = false;
    private boolean halfReset = false;

    private long pauseTimeElapsed = 0;
    private long timeElapsed = 0;

    public Chronometer chronometer;

    //==========================================================================
    //                              NEW CLOCK STUFF
    //==========================================================================

    public static Boolean gameStarted = false;

    public static Boolean clockPlaying = false;
    public static Boolean clockPaused = false;

    public static Boolean secondHalfStarted = false;

    public static Boolean gameEnded = false;


    public GameClock(Chronometer c)
    {
        this.chronometer=c;
    }

    public void start()
    {
        if(!secondHalfStarted)
            chronometer.setBase(SystemClock.elapsedRealtime() + pauseTimeElapsed);


        started = true;
        running = true;

        chronometer.start();
    }

    public void stop()
    {
        getCurrentClockTime();
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

    public String getCurrentClockTime()
    {
        Calendar CurrentDateTime = Calendar.getInstance();
        int CurrentDay = CurrentDateTime.get(Calendar.DAY_OF_MONTH);
        int CurrentMinutes = CurrentDateTime.get(Calendar.MINUTE);
        int currentHours = CurrentDateTime.get(Calendar.HOUR);


        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println(CurrentDateTime.get(Calendar.AM_PM));
        System.out.println("++++++++++++++++++++++++++++++++++");



        String a = CurrentDay + "_" + CurrentMinutes + "_" + currentHours;
        return a;
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

    public void startClock()
    {
        gameStarted = true;
        clockPlaying = true;
    }

    public void stopForBreak()
    {
        gameStarted = false;
        clockPlaying = false;
    }

    public void pauseClock()
    {
        clockPlaying = false;
        clockPaused = true;
    }

    public void playClock()
    {
        clockPlaying = true;
        clockPaused = false;
    }

}
