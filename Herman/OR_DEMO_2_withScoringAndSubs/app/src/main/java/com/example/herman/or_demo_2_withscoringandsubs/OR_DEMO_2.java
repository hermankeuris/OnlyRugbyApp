package com.example.herman.or_demo_2_withscoringandsubs;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.onlyrugbyDemo2.R;
import com.example.herman.or_demo_2_withscoringandsubs.Info.Data;
import com.example.herman.or_demo_2_withscoringandsubs.Info.Player;
import com.example.herman.or_demo_2_withscoringandsubs.Info.Team;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class OR_DEMO_2 extends Activity implements View.OnClickListener {
    public static final String WIFI = "Wi-Fi";
    public static final String ANY = "Any";
    private static final String URL = "http://leskommer.co.za/OnlyRugby/getLoginData.php";

    private static boolean backPressedTwice = false;

    // Check Wi-Fi connection.
    private static boolean wifiConnected = false;
    // Check mobile connection.
    private static boolean mobileConnected = false;
    // Check if the display should be refreshed.
    public static boolean refreshDisplay = true;

    // The user's current network preference setting.
    public static String sPref = null;

    // The BroadcastReceiver that tracks network connectivity changes.
    private NetworkReceiver receiver = new NetworkReceiver();

    //A handle to the singleton calss Data
    private Data data = Data.getInstance();

    Button btnLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);
        setContentView(R.layout.activity_login);
        // Register BroadcastReceiver to track connection changes.
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        receiver = new NetworkReceiver();
        this.registerReceiver(receiver, filter);

        btnLogin = (Button)findViewById(R.id.loginBtn);

        btnLogin.setOnClickListener(this);

        /**Initialise teams
         *
         */
        if (data.getTeamOne() == null && data.getTeamTwo() == null) {
            data.setTeamOne(new Team("Pretoria Highschool"));
            data.setTeamTwo(new Team("Bloemfontein Highschool"));

            //Team One's on field players
            data.getTeamOne().addPlayer(new Player("John One", 1, false));
            data.getTeamOne().addPlayer(new Player("John Two", 2, false));
            data.getTeamOne().addPlayer(new Player("John Three", 3, false));
            data.getTeamOne().addPlayer(new Player("John Four", 4, false));
            data.getTeamOne().addPlayer(new Player("John Five", 5, false));
            data.getTeamOne().addPlayer(new Player("John Six", 6, false));
            data.getTeamOne().addPlayer(new Player("John Seven", 7, false));
            data.getTeamOne().addPlayer(new Player("John Eight", 8, false));
            data.getTeamOne().addPlayer(new Player("John Nine", 9, false));
            data.getTeamOne().addPlayer(new Player("John Ten", 10, false));
            data.getTeamOne().addPlayer(new Player("John Eleven", 11, false));
            data.getTeamOne().addPlayer(new Player("John Twelve", 12, false));
            data.getTeamOne().addPlayer(new Player("John Thirteen", 13, false));
            data.getTeamOne().addPlayer(new Player("John Fourteen", 14, false));
            data.getTeamOne().addPlayer(new Player("John Fifteen", 15, false));

            //Team One's reserve players
            data.getTeamOne().addPlayer(new Player("John Sixteen", 16, true));
            data.getTeamOne().addPlayer(new Player("John Seventeen", 17, true));
            data.getTeamOne().addPlayer(new Player("John Eighteen", 18, true));
            data.getTeamOne().addPlayer(new Player("John Nineteen", 19, true));
            data.getTeamOne().addPlayer(new Player("John Twenty", 20, true));
            data.getTeamOne().addPlayer(new Player("John TwentyOne", 21, true));
            data.getTeamOne().addPlayer(new Player("John TwentyTwo", 22, true));

            //Team Two's on field players
            data.getTeamTwo().addPlayer(new Player("Paul One", 1, false));
            data.getTeamTwo().addPlayer(new Player("Paul Two", 2, false));
            data.getTeamTwo().addPlayer(new Player("Paul Three", 3, false));
            data.getTeamTwo().addPlayer(new Player("Paul Four", 4, false));
            data.getTeamTwo().addPlayer(new Player("Paul Five", 5, false));
            data.getTeamTwo().addPlayer(new Player("Paul Six", 6, false));
            data.getTeamTwo().addPlayer(new Player("Paul Seven", 7, false));
            data.getTeamTwo().addPlayer(new Player("Paul Eight", 8, false));
            data.getTeamTwo().addPlayer(new Player("Paul Nine", 9, false));
            data.getTeamTwo().addPlayer(new Player("Paul Ten", 10, false));
            data.getTeamTwo().addPlayer(new Player("Paul Eleven", 11, false));
            data.getTeamTwo().addPlayer(new Player("Paul Twelve", 12, false));
            data.getTeamTwo().addPlayer(new Player("Paul Thirteen", 13, false));
            data.getTeamTwo().addPlayer(new Player("Paul Fourteen", 14, false));
            data.getTeamTwo().addPlayer(new Player("Paul Fifteen", 15, false));

            //Team Two's reserve players
            data.getTeamTwo().addPlayer(new Player("Paul Sixteen", 16, true));
            data.getTeamTwo().addPlayer(new Player("Paul Seventeen", 17, true));
            data.getTeamTwo().addPlayer(new Player("Paul Eighteen", 18, true));
            data.getTeamTwo().addPlayer(new Player("Paul Nineteen", 19, true));
            data.getTeamTwo().addPlayer(new Player("Paul Twenty", 20, true));
            data.getTeamTwo().addPlayer(new Player("Paul TwentyOne", 21, true));
            data.getTeamTwo().addPlayer(new Player("Paul TwentyTwo", 22, true));
        }
        /**
         *
         */


        Button btnTeamOne = (Button)findViewById(R.id.team1);
        Button btnTeamTwo = (Button)findViewById(R.id.team2);

        btnTeamOne.setText(data.getTeamOne().getScore());
        btnTeamTwo.setText(data.getTeamTwo().getScore());
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.loginBtn) {
            loadPage();
        }

        TextView score = (TextView) findViewById(R.id.team1);
        Integer total = 0;

        Intent intent = new Intent(new Intent(OR_DEMO_2.this, TeamSelect.class));

        switch(view.getId()) {
            case R.id.tryBtn:
                /**total = Integer.parseInt(score.getText().toString()) + 5;
                System.out.println("\n\n\n\nHERE" + total + "\n\n\n\n");
                score.setText(String.valueOf(total));**/
                data.setFunctionType("Score");
                data.setSelectedScoreType("Try");
                startActivity(intent);
                break;
            case R.id.penaltyBtn:
                /**total = Integer.parseInt(score.getText().toString()) + 3;
                System.out.println("\n\n\n\nHERE" + total + "\n\n\n\n");
                score.setText(String.valueOf(total));**/
                data.setFunctionType("Score");
                data.setSelectedScoreType("Penalty Kick");
                startActivity(intent);
                break;
            case R.id.dropBtn:
                /**total = Integer.parseInt(score.getText().toString()) + 2;
                System.out.println("\n\n\n\nHERE" + total + "\n\n\n\n");
                score.setText(String.valueOf(total));**/
                data.setFunctionType("Score");
                data.setSelectedScoreType("Drop Kick");
                startActivity(intent);
                break;
            case R.id.substituteBtn:
                data.setFunctionType("Substitution");
                startActivity(intent);
                break;
        }
    }

    // Refreshes the display if the network connection and the
    // pref settings allow it.
    @Override
    public void onStart() {
        super.onStart();

        // Gets the user's network preference settings
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        // Retrieves a string value for the preferences. The second parameter
        // is the default value to use if a preference value is not found.
        sPref = sharedPrefs.getString("listPref", "Wi-Fi");

        updateConnectedFlags();

        if (refreshDisplay) {
            //loadPage();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (receiver != null) {
            this.unregisterReceiver(receiver);
        }
    }

    /**
     * Exist after second back press. Timer lasts 2 seconds.
     */
    @Override
    public void onBackPressed() {
        if (backPressedTwice) {
            super.onBackPressed();
            return;
        }

        this.backPressedTwice = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                backPressedTwice = false;
            }
        }, 2000);
    }

    // Checks the network connection and sets the wifiConnected and mobileConnected variables accordingly.
    private void updateConnectedFlags() {
        ConnectivityManager connMgr =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
        if (activeInfo != null && activeInfo.isConnected()) {
            wifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
            mobileConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;
        } else {
            wifiConnected = false;
            mobileConnected = false;
        }
    }

    private void loadPage() {
        if (((sPref.equals(ANY)) && (wifiConnected || mobileConnected)) || ((sPref.equals(WIFI)) && (wifiConnected))) {
            // AsyncTask subclass
            EditText edtUsername = (EditText)findViewById(R.id.edtUsername);
            EditText edtPassword = (EditText)findViewById(R.id.edtPassword);

            String username = edtUsername.getText().toString();
            String password = edtPassword.getText().toString();

            new Login().execute(URL, username, password);
        } else {
            showErrorPage();
        }
    }

    // Displays an error if the app is unable to load content.
    private void showErrorPage() {
        //setContentView(R.layout.main);
        Toast.makeText(getApplicationContext(), "Error connecting to network", Toast.LENGTH_LONG).show();
        /**
         * TODO
         * Create/populate elements with error message
         */
    }

    // Populates the activity's options menu.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    // Handles the user's menu selection.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent settingsActivity = new Intent(getBaseContext(), SettingsActivity.class);
                startActivity(settingsActivity);
                return true;
        /*case R.id.refresh:
            loadPage();
            return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class Login extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                return loginUser(urls[0], urls[1], urls[2]);
            } catch (IOException e) {
                return getResources().getString(R.string.connection_error);
            }
        }

        @Override
        protected void onPostExecute(String result) {
            //Toast.makeText(getApplicationContext(), "Result: " + result + ", Length: " + result.length(), Toast.LENGTH_LONG).show();
            if (result.equalsIgnoreCase("Success")) {
                setContentView(R.layout.activity_match_logger);
            }
            else {
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            }
        }
    }

    private String loginUser(String urlString, String username, String password) throws IOException {
        HttpURLConnection conn = null;
        String response = null;

        try {

            conn = sendPostMessage(urlString, username, password);

            response = receiveUrlFeedback(conn);
        }
        catch(Exception ex){
            Toast.makeText(getApplicationContext(), "Exception: C1" + ex.getMessage(), Toast.LENGTH_LONG).show();
            return "failure";
        }
        finally {
            return response.toString();
        }
    }

    private HttpURLConnection sendPostMessage(String urlString, String username, String password) {
        OutputStreamWriter writer = null;
        HttpURLConnection conn = null;

        try {
            String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

            URL url = new URL(urlString);

            conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setDoOutput(true);
            conn.connect();

            writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write(data);
            writer.flush();
        }
        catch (IOException ex) {
            Toast.makeText(getApplicationContext(), "Exception: W1: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            }
            catch (Exception ex) {
                Toast.makeText(getApplicationContext(), "Exception: W2: " + ex.getMessage(), Toast.LENGTH_LONG).show();
            }

            return conn;
        }

    }

    // Given a string representation of a URL, sets up a connection and gets
    // an input stream.
    protected String receiveUrlFeedback(HttpURLConnection conn) {

        BufferedReader reader = null;
        StringBuilder sb = null;

        try {
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                sb.append(line);
                break;
            }
        }
        catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Exception: R1: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        finally {

            try {
                if (reader != null) {
                    reader.close();
                }
            }
            catch (Exception ex) {
                Toast.makeText(getApplicationContext(), "Exception: R2: " + ex.getMessage(), Toast.LENGTH_LONG).show();
            }

            return sb.toString();
        }
    }

    /**
     *
     * This BroadcastReceiver intercepts the android.net.ConnectivityManager.CONNECTIVITY_ACTION,
     * which indicates a connection change. It checks whether the type is TYPE_WIFI.
     * If it is, it checks whether Wi-Fi is connected and sets the wifiConnected flag in the
     * main activity accordingly.
     *
     */
    public class NetworkReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connMgr =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            // Checks the user prefs and the network connection. Based on the result, decides
            // whether
            // to refresh the display or keep the current display.
            // If the userpref is Wi-Fi only, checks to see if the device has a Wi-Fi connection.
            if (WIFI.equals(sPref) && networkInfo != null
                    && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                // If device has its Wi-Fi connection, sets refreshDisplay
                // to true. This causes the display to be refreshed when the user
                // returns to the app.
                refreshDisplay = true;
                Toast.makeText(context, R.string.wifi_connected, Toast.LENGTH_SHORT).show();

                // If the setting is ANY network and there is a network connection
                // (which by process of elimination would be mobile), sets refreshDisplay to true.
            } else if (ANY.equals(sPref) && networkInfo != null) {
                refreshDisplay = true;

                // Otherwise, the app can't download content--either because there is no network
                // connection (mobile or Wi-Fi), or because the pref setting is WIFI, and there
                // is no Wi-Fi connection.
                // Sets refreshDisplay to false.
            } else {
                refreshDisplay = false;
                Toast.makeText(context, R.string.lost_connection, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
