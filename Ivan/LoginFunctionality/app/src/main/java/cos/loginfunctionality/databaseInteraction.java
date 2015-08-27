package cos.loginfunctionality;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class databaseInteraction extends AsyncTask<String,Void,String>
{
    private Context context;
    private SharedPreferences userData;

    public databaseInteraction(Context context, SharedPreferences state)
    {
        this.context = context;
        this.userData = state;
    }

    protected void onPreExecute()
    {

    }

    @Override
    protected String doInBackground(String... arg0)
    {
        try{
            String username = (String)arg0[0];
            String password = (String)arg0[1];

            String link="http://www.leskommer.co.za/OnlyRugby/getLoginData.php";
            String data  = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

            writer.write( data );
            writer.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                sb.append(line);
                break;
            }
            return sb.toString();
        }
        catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result)
    {
        try {
            String username;
            JSONObject json= (JSONObject) new JSONTokener(result).nextValue();
            JSONObject json2 = json.getJSONObject("username");
            username = (String) json2.get("name");
            if(username != null)
            {
                //Logged in
                SharedPreferences.Editor editor = userData.edit();
                editor.putString("username", result);
                editor.commit();
            }
            else
            {
                //error incorrect details
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}