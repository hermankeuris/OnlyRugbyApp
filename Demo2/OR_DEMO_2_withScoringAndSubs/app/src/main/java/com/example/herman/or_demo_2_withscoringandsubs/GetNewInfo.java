package com.example.herman.or_demo_2_withscoringandsubs;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.herman.or_demo_2_withscoringandsubs.Info.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class GetNewInfo extends AsyncTask<String, Void, String> {

    private static Context context = null;
    private static boolean pingServer = false;
    private static Data data;

    public GetNewInfo(Context context, Data data) {
        this.context = context;
        this.data = data;
    }

    /*public GetNewInfo(Context context, String command) {
        this.context = context;

        if (command.equalsIgnoreCase("ping")) {
            pingServer = true;
        }
    }*/

    @Override
    protected String doInBackground(String... urls) {
        try {
            if (!pingServer) {
                return playerInfo(urls[0]);
            }
            else {
                return getLastUpdated(urls[0], urls[1]);
            }

        } catch (IOException e) {
            return "ERROR 101";
        }
    }

    @Override
    protected void onPostExecute(String result) {
        //Toast.makeText(context, "Result: " + result + ", Length: " + result.length(), Toast.LENGTH_LONG).show();
        DatabaseHelper db = new DatabaseHelper(context, data);

        db.getNewestInfo(result);
        db.printAllMatchInfo();
    }

    private String getLastUpdated(String urlString, String match_id) throws IOException {
        HttpURLConnection conn = null;
        String response = "";

        try {

            String data = URLEncoder.encode("pingUpdate", "UTF-8") + "=" + URLEncoder.encode(match_id, "UTF-8");

            conn = sendPostMessage(urlString, data);

            response = receiveUrlFeedback(conn);
        }
        catch(Exception ex){
            Toast.makeText(context, "Exception: P1" + ex.getMessage(), Toast.LENGTH_LONG).show();
            //return "failure";
            return response;
        }
        finally {
            return response.toString();
        }
    }

    private String playerInfo(String urlString) throws IOException {
        HttpURLConnection conn = null;
        String response = "";

        try {

            String data = URLEncoder.encode("interact", "UTF-8") + "=" + URLEncoder.encode("{\"instructs\":[{\"instruct\":\"preGameLoad\"},{\"instruct\":\"testInstructionString2\"}]}", "UTF-8");
            data += "&" + URLEncoder.encode("uid", "UTF-8") + "=" + URLEncoder.encode("4", "UTF-8");

            conn = sendPostMessage(urlString, data);

            response = receiveUrlFeedback(conn);
        }
        catch(Exception ex){
            Toast.makeText(context, "Exception: P1" + ex.getMessage(), Toast.LENGTH_LONG).show();
            //return "failure";
            return response;
        }
        finally {
            return response.toString();
        }
    }

    private HttpURLConnection sendPostMessage(String urlString, String data) {
        OutputStreamWriter writer = null;
        HttpURLConnection conn = null;

        try {
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
            Toast.makeText(context, "Exception: W1: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            }
            catch (Exception ex) {
                Toast.makeText(context, "Exception: W2: " + ex.getMessage(), Toast.LENGTH_LONG).show();
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
            Toast.makeText(context, "Exception: R1: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        finally {

            try {
                if (reader != null) {
                    reader.close();
                }
            }
            catch (Exception ex) {
                Toast.makeText(context, "Exception: R2: " + ex.getMessage(), Toast.LENGTH_LONG).show();
            }

            return sb.toString();
        }
    }
}
