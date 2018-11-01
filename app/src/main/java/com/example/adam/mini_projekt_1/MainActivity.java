package com.example.adam.mini_projekt_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {
    private Button button_go_to_list;
    private Button button_settings;
    private Button button_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void goToListClick(View view){
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
    public void exitClick(View view){
        finish();
        System.exit(0);
    }

    public void settingsClick(View view){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

}
