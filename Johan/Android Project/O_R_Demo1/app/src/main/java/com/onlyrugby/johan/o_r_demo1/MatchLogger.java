package com.onlyrugby.johan.o_r_demo1;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URL;


public class MatchLogger extends Activity implements View.OnClickListener {

    private Chronometer chronometer;
    private TextView texty;
    private boolean started = false;


    TextView textMsg;
    final String textSource = "https://sites.google.com/site/androidersite/text.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_logger);

        chronometer = (Chronometer) findViewById(R.id.chronometer);
        chronometer.setOnClickListener(this);

        textMsg = (TextView) findViewById(R.id.textmsg);

        LoadInfo t = new LoadInfo();
        new LoadInfo.loadPreGameInfo().execute();
    }

    @Override
    public void onClick(View v) {
        String chronoText = chronometer.getText().toString();
        String array[] = chronoText.split(":");
        int stoppedMilliseconds = 0;

        TextView score = (TextView) findViewById(R.id.team1);
        Integer total = 0;

        switch(v.getId()) {
            case R.id.chronometer:
                if (array.length == 2) {
                    stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 1000 + Integer.parseInt(array[1]) * 1000;
                } else if (array.length == 3) {
                    stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 60 * 1000 + Integer.parseInt(array[1]) * 60 * 1000 + Integer.parseInt(array[2]) * 1000;
                }

                if (!started) {
                    chronometer.setBase(SystemClock.elapsedRealtime() - stoppedMilliseconds);
                    chronometer.start();
                    Toast.makeText(getApplicationContext(), "Timer Started", Toast.LENGTH_SHORT).show();
                    started = true;
                }
                else {
                    chronometer.stop();
                    Toast.makeText(getApplicationContext(), "Timer Stopped", Toast.LENGTH_SHORT).show();
                    started = false;
                }
                break;
            case R.id.tryBtn:
                total = Integer.parseInt(score.getText().toString()) + 5;
                System.out.println("\n\n\n\nHERE" + total + "\n\n\n\n");
                score.setText(String.valueOf(total));
                break;
            case R.id.penaltyBtn:
                total = Integer.parseInt(score.getText().toString()) + 3;
                System.out.println("\n\n\n\nHERE" + total + "\n\n\n\n");
                score.setText(String.valueOf(total));
                break;
            case R.id.conversionBtn:
                total = Integer.parseInt(score.getText().toString()) + 2;
                System.out.println("\n\n\n\nHERE" + total + "\n\n\n\n");
                score.setText(String.valueOf(total));
                break;
        }
    }

    private class MyTask extends AsyncTask<Void, Void, Void> {

        String textResult = "";

        @Override
        protected Void doInBackground(Void... params) {
            URL textUrl;

            try {
                /*textUrl = new URL(textSource);

                BufferedReader bufferReader = new BufferedReader(
                        new InputStreamReader(textUrl.openStream()));

                String stringBuffer;
                String stringText = "";
                while((stringBuffer = bufferReader.readLine()) != null) {
                    stringText += stringBuffer;
                }

                bufferReader.close();
                textResult = stringText;

            } catch(MalformedURLException e) {
                e.printStackTrace();
                textResult = e.toString();*/

                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI("http://www.leskommer.co.za/OnlyRugby/getusers.php"));
                HttpResponse response = client.execute(request);

                JSONArray json = null;

                if (response.getStatusLine().getStatusCode() == 200)
                {
                    HttpEntity entity = response.getEntity();
                    json = new JSONArray(EntityUtils.toString(entity));
                }

                try {
                    System.out.println(json.length());
                    // If no of array elements is not zero
                    if (json.length() != 0) {
                        // Loop through each array element, get JSON object which has userid and username
                        for (int i = 0; i < json.length(); i++) {
                            // Get JSON object
                            JSONObject obj = (JSONObject) json.get(i);
                            System.out.println(obj.get("userId"));
                            System.out.println(obj.get("userName"));
                            textResult += obj.get("userName") + "\n";
                        }
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

               // textResult = response.toString();
                System.out.println(textResult);

                //textResult = json.toString();
                //System.out.println(json.toString());

            } catch(Exception e) {
                e.printStackTrace();
                textResult = e.toString();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            textMsg.setText(textResult);
            super.onPostExecute(result);
        }
    }
}
