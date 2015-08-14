package com.onlyrugby.johan.o_r_demo1;

import android.os.AsyncTask;

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
import java.util.ArrayList;

/**
 * Created by Fireblade on 2015-08-12.
 */
public class LoadInfo {
    public class loadPreGameInfo extends AsyncTask<Void, Void, Void> {
        ArrayList<String> playerNames;

        {
            playerNames = new ArrayList<String>();
        }

        @Override
        protected Void doInBackground(Void... params) {
            URL textUrl;

            try {
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI("http://www.leskommer.co.za/OnlyRugby/preGameInfo.php"));
                HttpResponse response = client.execute(request);

                JSONArray json = null;

                if (response.getStatusLine().getStatusCode() == 200) {
                    HttpEntity entity = response.getEntity();
                    json = new JSONArray(EntityUtils.toString(entity));
                }

                try {
                    System.out.println(json.length());
                    if (json.length() != 0) {
                        for (int i = 0; i < json.length(); i++) {
                            JSONObject obj = (JSONObject) json.get(i);
                            playerNames.add(obj.get("name") + " " + obj.get("surname"));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                while (true) {
                    if (playerNames.isEmpty()) {
                        break;
                    }

                    popupPage.playerNames.add(playerNames.get(0));
                    playerNames.remove(0);
                }

            } catch (Exception e) {
                e.printStackTrace();
                playerNames.clear();
                playerNames.add(e.toString());
            }
            return null;
        }
    }
}
