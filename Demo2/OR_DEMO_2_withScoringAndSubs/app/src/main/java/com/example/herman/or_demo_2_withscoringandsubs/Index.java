package com.example.herman.or_demo_2_withscoringandsubs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.example.android.onlyrugbyDemo2.R;
import com.example.herman.or_demo_2_withscoringandsubs.Info.Data;

public class Index extends Activity implements View.OnClickListener{
    //A handle to the singleton class Data
    private Data data = Data.getInstance();
    private static boolean backPressedTwice = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.selectLoginBtn) {
            System.out.println("Login");
            Intent intent = new Intent(new Intent(Index.this, LoginCode.class));
            startActivity(intent);
        }
        else if (v.getId() == R.id.offlineBtn)
        {
            data.setOfflineMode(true);
            Intent intent = new Intent(new Intent(Index.this, MainMenu.class));
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        if (backPressedTwice) {
            super.onBackPressed();
            Intent intent=new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            System.exit(0);
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
}
