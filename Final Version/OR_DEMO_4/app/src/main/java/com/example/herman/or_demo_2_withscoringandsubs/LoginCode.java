package com.example.herman.or_demo_2_withscoringandsubs;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.onlyrugbyDemo2.R;
import com.example.herman.or_demo_2_withscoringandsubs.Info.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class LoginCode extends Activity implements View.OnClickListener {
    private static final String URL = "http://leskommer.co.za/OnlyRugby/getLoginData.php";
    private static final String PLAYERS_URL = "http://localhost/openRugby/public/index.php/dbInteract";

    //A handle to the singleton class Data
    private Data data = Data.getInstance();

    private static boolean loggedIn = false;

    Button btnLogin;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button)findViewById(R.id.loginBtn);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.loginBtn) {
            loadPage();
        }
    }

    private void continueLogin() {
        Intent intent = new Intent(new Intent(LoginCode.this, MainMenu.class));
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void loadPage() {
        EditText edtUsername = (EditText)findViewById(R.id.edtUsername);
        EditText edtPassword = (EditText)findViewById(R.id.edtPassword);

        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();

        new Login().execute(URL, username, password);
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
            if (result.equalsIgnoreCase("Success")) {
                //setContentView(R.layout.activity_match_logger);
                setLoggedIn(true);
                //new GetNewInfo(getApplicationContext()).execute(PLAYERS_URL);
            }
            else {
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                setLoggedIn(false);
            }
        }
    }

    private String loginUser(String urlString, String username, String password) throws IOException {
        HttpURLConnection conn;
        String response = "";

        try {

            conn = sendPostMessage(urlString, username, password);

            response = receiveUrlFeedback(conn);
        }
        catch(Exception ex){
            Toast.makeText(getApplicationContext(), "Exception: C1" + ex.getMessage(), Toast.LENGTH_LONG).show();
            return "Failure connecting to the database";
        }

        if (response.equalsIgnoreCase("")) {
            return "Server not responding";
        }

        return response;
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
        }

        return conn;
    }

    protected String receiveUrlFeedback(HttpURLConnection conn) {

        BufferedReader reader = null;
        StringBuilder sb = null;
        String returnStr;

        try {
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            sb = new StringBuilder();
            String line;

            // Read Server Response
            line = reader.readLine();

            while(line != null) {
                sb.append(line);
                line = reader.readLine();
            }

            /*while((line = reader.readLine()) != null)
            {
                sb.append(line);
                break; //what is this doing here??
            }*/
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

            returnStr = sb.toString();
        }

        return returnStr;
    }

    protected void setLoggedIn(boolean loggedIn){
        this.loggedIn = loggedIn;
        if (getLoggedIn()){
            continueLogin();
        }
    }

    protected boolean getLoggedIn(){
        return this.loggedIn;
    }
}
