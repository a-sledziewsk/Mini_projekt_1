package com.example.adam.mini_projekt_1;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class AddActivity extends Activity {

    private Button add_to_list_button;
    private EditText add_product_name;
    private EditText add_quantity;
    private EditText add_price;
    private CheckBox bought;

    private TextView productNameTextView;
    private TextView quantityTextView;
    private TextView priceTextView;
    DBAdapter myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //add_to_list_button.setTextColor(SharedPreferencesDB.getColorFromSharePreferences(this));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //add_to_list_button.setTextColor(SharedPreferencesDB.getColorFromSharePreferences(this));
        add_product_name = (EditText) findViewById(R.id.add_product_name_input);
        add_quantity = (EditText) findViewById(R.id.add_quantity_input);
        add_price = (EditText) findViewById(R.id.add_price_input);
        bought = (CheckBox)findViewById((R.id.add_product_checkbox));
        add_to_list_button = findViewById(R.id.add_to_list_button);

        add_to_list_button.setTextColor(SharedPreferencesDB.getColorFromSharePreferences(this));
        add_quantity.setTextColor(SharedPreferencesDB.getColorFromSharePreferences(this));
        add_price.setTextColor(SharedPreferencesDB.getColorFromSharePreferences(this));
        bought.setTextColor(SharedPreferencesDB.getColorFromSharePreferences(this));


        add_to_list_button.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(this));
        add_quantity.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(this));
        add_price.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(this));
        bought.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(this));

        productNameTextView = findViewById(R.id.add_product_name_label);
        quantityTextView = findViewById(R.id.add_quantity_label);
        priceTextView = findViewById(R.id.add_price_label);

        productNameTextView.setTextColor(SharedPreferencesDB.getColorFromSharePreferences(this));
        quantityTextView.setTextColor(SharedPreferencesDB.getColorFromSharePreferences(this));
        priceTextView.setTextColor(SharedPreferencesDB.getColorFromSharePreferences(this));

        productNameTextView.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(this));
        quantityTextView.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(this));
        priceTextView.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(this));

        openDB();
    }

    private void openDB(){
        myDB = new DBAdapter(this);
        myDB.open();
    }



    public void addProductDetailsClick(View view){
        String productName = add_product_name.getText().toString();
        String quantity = add_quantity.getText().toString();
        String price = add_price.getText().toString();
        boolean boughtBoolean = bought.isChecked();

        if (productName.length()>0 &&  price.length()>0&& quantity.length()>0) {
            myDB.insertRow(productName, Integer.parseInt(quantity), Float.parseFloat(price), boughtBoolean);
            Intent returnToListActivity = new Intent(this, ListActivity.class);
            startActivity(returnToListActivity);
        }
        else{
            Toast.makeText(this, "Fill all text fields!",
                    Toast.LENGTH_LONG).show();
        }






    }


}
