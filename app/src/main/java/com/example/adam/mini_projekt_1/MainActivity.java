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

        button_go_to_list = findViewById(R.id.go_to_list_button);
        button_exit = findViewById(R.id.exit_button);
        button_settings = findViewById(R.id.settings_button);

        button_settings.setTextColor(SharedPreferencesDB.getColorFromSharePreferences(this));
        button_exit.setTextColor(SharedPreferencesDB.getColorFromSharePreferences(this));
        button_go_to_list.setTextColor(SharedPreferencesDB.getColorFromSharePreferences(this));

        button_settings.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(this));
        button_exit.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(this));
        button_go_to_list.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(this));
    }
    protected void onResume() {

        super.onResume();

        setContentView(R.layout.activity_main);

        button_go_to_list = findViewById(R.id.go_to_list_button);
        button_exit = findViewById(R.id.exit_button);
        button_settings = findViewById(R.id.settings_button);

        button_settings.setTextColor(SharedPreferencesDB.getColorFromSharePreferences(this));
        button_exit.setTextColor(SharedPreferencesDB.getColorFromSharePreferences(this));
        button_go_to_list.setTextColor(SharedPreferencesDB.getColorFromSharePreferences(this));

        button_settings.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(this));
        button_exit.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(this));
        button_go_to_list.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(this));
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
