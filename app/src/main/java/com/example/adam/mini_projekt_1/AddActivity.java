package com.example.adam.mini_projekt_1;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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
    DBAdapter myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        add_product_name = (EditText) findViewById(R.id.add_product_name_input);
        add_quantity = (EditText) findViewById(R.id.add_quantity_input);
        add_price = (EditText) findViewById(R.id.add_price_input);
        bought = (CheckBox)findViewById((R.id.add_product_checkbox));

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

        if (productName.length()>0 && quantity!=null && price!=null) {
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
