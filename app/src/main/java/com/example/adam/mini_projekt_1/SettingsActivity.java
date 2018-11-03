package com.example.adam.mini_projekt_1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class SettingsActivity extends Activity {

    private RadioGroup radioGroup;
    private RadioButton radioColorButton;
    private Button acceptButton;

    private RadioGroup radioFontGroup;
    private RadioButton radioFontButton;
    private Button acceptFontButton;


    private TextView fontColorTextView;
    private TextView fontSizeTextView;

    private RadioButton redButton;
    private RadioButton blueButton;
    private RadioButton blackButton;

    private RadioButton smallButton;
    private RadioButton mediumButton;
    private RadioButton largeButton;


    private static final String BUTTON_SMALL_NAME = "Small";
    private static final String BUTTON_MEDIUM_NAME ="Medium";
    private static final String BUTTON_LARGE_NAME ="Large";

    private static final float SMALL_FONT = 10;
    private static final float MEDIUM_FONT = 14;
    private static final float LARGE_FONT = 18;

    private static final float LARGE_FONT_PIXELS = 54;
    private static final float SMALL_FONT_PIXELS = 30;
    private static final float MEDIUM_FONT_PIXELS = 42;


    private static final int COLOR_RED = Color.RED;
    private static final int COLOR_BLUE = Color.BLUE;
    private static final int COLOR_BLACK = Color.BLACK;


    private static final String BUTTON_RED_NAME = "Red";
    private static final String BUTTON_BLUE_NAME ="Blue";
    private static final String BUTTON_BLACK_NAME ="Black";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        fontColorTextView = findViewById(R.id.font_color_text_view);
        fontSizeTextView = findViewById(R.id.font_size_radio_group_text_view);
        addListenerOnButton();

        fontColorTextView.setTextColor(SharedPreferencesDB.getColorFromSharePreferences(SettingsActivity.this));
        acceptButton.setTextColor(SharedPreferencesDB.getColorFromSharePreferences(SettingsActivity.this));

        fontSizeTextView.setTextColor(SharedPreferencesDB.getColorFromSharePreferences(SettingsActivity.this));
        acceptFontButton.setTextColor(SharedPreferencesDB.getColorFromSharePreferences(SettingsActivity.this));

        redButton = findViewById(R.id.redColorButton);
        blueButton = findViewById(R.id.blueColorButton);
        blackButton = findViewById((R.id.blackColorButton));

        smallButton = findViewById(R.id.font_small);
        mediumButton = findViewById(R.id.font_medium);
        largeButton = findViewById(R.id.font_large);

        smallButton.setTextColor(SharedPreferencesDB.getColorFromSharePreferences(SettingsActivity.this));
        mediumButton.setTextColor(SharedPreferencesDB.getColorFromSharePreferences(SettingsActivity.this));
        largeButton.setTextColor(SharedPreferencesDB.getColorFromSharePreferences(SettingsActivity.this));



        fontColorTextView.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(SettingsActivity.this));
        fontSizeTextView.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(SettingsActivity.this));

        redButton.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(SettingsActivity.this));
        blackButton.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(SettingsActivity.this));
        blueButton.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(SettingsActivity.this));

        smallButton.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(SettingsActivity.this));
        mediumButton.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(SettingsActivity.this));
        largeButton.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(SettingsActivity.this));


        ColorStateList colorStateList = acceptButton.getTextColors();
        int butColor = colorStateList.getDefaultColor();

        switch (butColor){
            case COLOR_RED:
                redButton.setChecked(true);
                break;
            case COLOR_BLUE:
                blueButton.setChecked(true);
                break;
            case COLOR_BLACK:
                blackButton.setChecked(true);
                break;
        }

        int textSize = Math.round(acceptFontButton.getTextSize());
        float textSizeTextView = fontColorTextView.getTextSize();



        final int smallSizeInt = 10;
        final int mediumSizeInt = 14;
        final int largeSizeInt =18;

        switch (textSize){
            case smallSizeInt:
                smallButton.setChecked(true);
                break;
            case mediumSizeInt:
                mediumButton.setChecked(true);
                break;
            case largeSizeInt:
                largeButton.setChecked(true);
                break;


        }

    }


    private void addListenerOnButton() {
        acceptButton = findViewById(R.id.accept_color_button);
        acceptFontButton = findViewById(R.id.accept_font_size);

        radioGroup = findViewById(R.id.colorRadioGroup);
        radioFontGroup = findViewById(R.id.fontRadioGroup);


        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId = radioGroup.getCheckedRadioButtonId();

                radioColorButton = (RadioButton)findViewById(selectedId);
                SharedPreferencesDB.saveRadioButtonCheckedsInSharedPreferencesDB(SettingsActivity.this,
                        selectedId);


                String buttonName = (String) radioColorButton.getText();

                if (buttonName.equals(BUTTON_RED_NAME)){
                    SharedPreferencesDB.saveColorInSharedPreferencesEditor(SettingsActivity.this, COLOR_RED);
                }

                else if (buttonName.equals(BUTTON_BLUE_NAME)){
                    SharedPreferencesDB.saveColorInSharedPreferencesEditor(SettingsActivity.this, COLOR_BLUE);
                }

                else if (buttonName.equals(BUTTON_BLACK_NAME)){
                    SharedPreferencesDB.saveColorInSharedPreferencesEditor(SettingsActivity.this, COLOR_BLACK);
                }


                acceptButton.setTextColor(SharedPreferencesDB.getColorFromSharePreferences(SettingsActivity.this));
                fontColorTextView.setTextColor(SharedPreferencesDB.getColorFromSharePreferences(SettingsActivity.this));



                String msg = buttonName + " selected";
                Toast.makeText(SettingsActivity.this, msg , Toast.LENGTH_SHORT).show();
            }
        });

        //TODO: Dodac opcje zmiany rozmiaru czcionki
        acceptFontButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectFontId = radioFontGroup.getCheckedRadioButtonId();
                SharedPreferencesDB.saveRadioFontButtonCheckedInSharedPreferencesDB(
                        SettingsActivity.this, selectFontId);
                radioFontButton = (RadioButton) findViewById(selectFontId) ;

                String fontButtonName = (String) radioFontButton.getText();

                if (fontButtonName.equals(BUTTON_SMALL_NAME)){
                    SharedPreferencesDB.saveFontInSharedPreferencesEditor(SettingsActivity.this, SMALL_FONT);
                }

                else if (fontButtonName.equals(BUTTON_MEDIUM_NAME)){
                    SharedPreferencesDB.saveFontInSharedPreferencesEditor(SettingsActivity.this, MEDIUM_FONT);
                }

                else if (fontButtonName.equals(BUTTON_LARGE_NAME)){
                    SharedPreferencesDB.saveFontInSharedPreferencesEditor(SettingsActivity.this, LARGE_FONT);
                }

                acceptButton.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(SettingsActivity.this));
                acceptFontButton.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(SettingsActivity.this));
                fontColorTextView.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(SettingsActivity.this));


                fontSizeTextView.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(SettingsActivity.this));


                redButton.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(SettingsActivity.this));
                blackButton.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(SettingsActivity.this));
                blueButton.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(SettingsActivity.this));

                smallButton.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(SettingsActivity.this));
                mediumButton.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(SettingsActivity.this));
                largeButton.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(SettingsActivity.this));

                String msg = fontButtonName + " selected";
                Toast.makeText(SettingsActivity.this, msg , Toast.LENGTH_SHORT).show();


            }
        });

    }


}



