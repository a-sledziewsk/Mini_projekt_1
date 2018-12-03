package com.example.adam.mini_projekt_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AddActivity extends Activity {

    private Button add_to_list_button;
    private EditText add_product_name;
    private EditText add_quantity;
    private EditText add_price;
    private CheckBox bought;

    private TextView productNameTextView;
    private TextView quantityTextView;
    private TextView priceTextView;
    private DatabaseReference mDatabase;

    DBAdapter myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        mDatabase = FirebaseDatabase.getInstance().getReference("ProductList");


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

            Intent returnToListActivity = new Intent(this, ListActivity.class);

            //TODO: writing to DB

            writeNewProduct(productName, Float.parseFloat(price), Integer.parseInt(quantity),  boughtBoolean);

            final Intent intent = new Intent();
            intent.setAction("com.example.adam.mini_projekt_1");
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            intent.putExtra("Product name", productName);
            intent.putExtra("Quantity", quantity);
            intent.putExtra("Price", price);
            sendBroadcast(intent, "com.example.adam.mini_projekt_1.permission.MY_PERMISSION");
            startActivity(returnToListActivity);

        }
        else{
            Toast.makeText(this, "Fill all text fields!",
                    Toast.LENGTH_LONG).show();
        }

    }


    //TODO adding to DB
    private void writeNewProduct(String productName, float price, int quantity, boolean bought) {
        DatabaseReference childRef = mDatabase.push();

        ListItem listItem = new ListItem(productName, price, quantity, bought);
        childRef.setValue(listItem);

    }
}
