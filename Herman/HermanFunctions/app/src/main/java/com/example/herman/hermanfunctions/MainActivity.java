package com.example.herman.hermanfunctions;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.herman.hermanfunctions.Info.Data;

public class MainActivity extends AppCompatActivity {

    private Data data = Data.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(new Intent(MainActivity.this, TeamSelect.class));
                startActivity(intent);

                switch(v.getId()){
                    case R.id.scoringBtn:
                        selectScore();
                        break;
                    case R.id.substitutionBtn:
                        selectSub();
                        break;
                }
            }
        };
        Button score =(Button) findViewById(R.id.scoringBtn);
        Button subs =(Button) findViewById(R.id.substitutionBtn);
        score.setOnClickListener(listener);
        subs.setOnClickListener(listener);

    }

    /**@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }**/

    public void selectScore()
    {
        data.setFunctionType("Score");
    }

    public void selectSub()
    {
        data.setFunctionType("Substitution");
    }
}
