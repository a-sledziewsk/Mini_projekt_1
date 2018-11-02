package com.example.adam.mini_projekt_1;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;



public class SharedPreferencesDB {


    private SharedPreferences sharedPreferences;
    private final static String MY_PREFS_NAME = "Color preferences";
    private final static int MODE_PRIVATE = 0;
    private final static String COLOR_KEY = "Color";
    private static final int COLOR_BLACK = Color.BLACK;
    private static final String RADIO_BUTTON_STATUS_KEY = "radio_but_stat";



    public static void saveColorInSharedPreferencesEditor(Context context, int input){

        SharedPreferences settings = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(COLOR_KEY, input);
        editor.commit();
    }

    public static int getColorFromSharePreferences(Context context){
        SharedPreferences settings = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getInt(COLOR_KEY, COLOR_BLACK);
    }

    public static void saveRadioButtonCheckedsInSharedPreferencesDB(Context context, int index){
        SharedPreferences settings = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(RADIO_BUTTON_STATUS_KEY, index);
        editor.commit();
    }

    public static int getRadioStatusFromSharedPreferencesDB(Context context){
        SharedPreferences settings = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getInt(RADIO_BUTTON_STATUS_KEY, 1);
    }

}
