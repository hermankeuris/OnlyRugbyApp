package com.example.herman.Info;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Herman on 2015-08-12.
 */
public class Main {

    /**
     * Created by Herman on 2015-08-12.
     */
    public class Data {
        /**
         * Global variables used between all functions
         * */
        //An arraylist containing the two teams which are currently playing
        private ArrayList<Team> teams = new ArrayList<>(2);
        //A variable which keeps track of which team currently has possession of the ball.
        private Team possession = null;
        //The date/time the match began
        private Date matchStart = null;
        //The date/time the first half of the match ended
        private Date firstHalfEnd = null;
        //The date/time the second half of the match started
        private Date firstHalfStart = null;
        //The date/time the match ended
        private Date matchEnd = null;

    }

}
