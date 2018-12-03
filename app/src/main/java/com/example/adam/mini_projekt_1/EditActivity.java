package com.example.adam.mini_projekt_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class EditActivity extends Activity {

    private Button add_to_list_button;
    private EditText add_product_name;
    private EditText add_quantity;
    private EditText add_price;
    private CheckBox bought;

    private TextView productNameTextView;
    private TextView quantityTextView;
    private TextView priceTextView;

    Intent intentDelivery = getIntent();
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        add_product_name = (EditText) findViewById(R.id.edit_product_name_input);
        add_quantity = (EditText) findViewById(R.id.edit_quantity_input);
        add_price = (EditText) findViewById(R.id.edit_price_input);
        bought = (CheckBox)findViewById((R.id.edit_product_checkbox));
        add_to_list_button = findViewById(R.id.edit_to_list_button);

        this.intentDelivery = getIntent();

        productNameTextView = findViewById(R.id.edit_product_name_label);
        quantityTextView = findViewById(R.id.edit_quantity_label);
        priceTextView = findViewById(R.id.edit_price_label);

        productNameTextView.setTextColor(SharedPreferencesDB.getColorFromSharePreferences(this));
        quantityTextView.setTextColor(SharedPreferencesDB.getColorFromSharePreferences(this));
        priceTextView.setTextColor(SharedPreferencesDB.getColorFromSharePreferences(this));
        bought.setTextColor(SharedPreferencesDB.getColorFromSharePreferences(this));
        add_to_list_button.setTextColor(SharedPreferencesDB.getColorFromSharePreferences(this));


        productNameTextView.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(this));
        quantityTextView.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(this));
        priceTextView.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(this));
        bought.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(this));
        add_to_list_button.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(this));

    }



    public void editProductDetailsClick(View view){
        String productName = add_product_name.getText().toString();
        String quantity = add_quantity.getText().toString();
        String price = add_price.getText().toString();
        boolean boughtBoolean = bought.isChecked();
        String prod_name_id = this.intentDelivery.getStringExtra("key");

        if (productName.length()>0 && price.length()>0&& quantity.length()>0) {
            //TODO Editing done
            mDatabase = FirebaseDatabase.getInstance().getReference("ProductList");
            Query productQuery = mDatabase.orderByChild("productName").equalTo(prod_name_id);

            productQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot productSnapshot: dataSnapshot.getChildren()){
                        productSnapshot.getRef().removeValue();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            DatabaseReference childRef = mDatabase.push();
            ListItem listItem = new ListItem(productName, Float.parseFloat(price), Integer.parseInt(quantity), boughtBoolean);
            childRef.setValue(listItem);            Intent returnToListActivity = new Intent(this, ListActivity.class);
            startActivity(returnToListActivity);
        }
        else{
            Toast.makeText(this, "Fill all text fields!",
                    Toast.LENGTH_LONG).show();
        }
    }
}
