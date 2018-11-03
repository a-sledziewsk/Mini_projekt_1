package com.example.adam.mini_projekt_1;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class ListActivity extends Activity {

    private RecyclerView irv;
    private Button add_to_list_button;
    private DBAdapter myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        openDB();

        add_to_list_button = findViewById(R.id.add_to_list_button);
        add_to_list_button.setTextColor(SharedPreferencesDB.getColorFromSharePreferences(this));
        add_to_list_button.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(this));

        irv = (RecyclerView) findViewById(R.id.rolling_list);

        LinearLayoutManager rlm = new LinearLayoutManager(this);
        irv.setLayoutManager(rlm);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                                                        irv.getContext(), rlm.getOrientation());

        irv.addItemDecoration(dividerItemDecoration);

        MyAdapter rva = new MyAdapter(getItems(), this);
        irv.setAdapter(rva);
    }



    private void openDB(){
        myDB = new DBAdapter(this);
        myDB.open();
    }



    public void onAddClick(View v){
        Intent goToAddItem = new Intent(this, AddActivity.class);
        startActivity(goToAddItem);
    }


    private List<ListItem> getItems(){
        Cursor cursor = myDB.getAllRows();
        String[] stringFormatter = {DBAdapter.getProductColumn(), DBAdapter.getQuantityColumn(),
        DBAdapter.getPriceColumn(), DBAdapter.getBoughtColumn()};
        String prodName;
        float price;
        int quantity;
        int boughtIntBool;
        List<ListItem> il = new ArrayList<ListItem>();


        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){

            prodName = cursor.getString((cursor.getColumnIndex(DBAdapter.getProductColumn())));
            price = cursor.getFloat(cursor.getColumnIndex(DBAdapter.getPriceColumn()));
            quantity = cursor.getInt(cursor.getColumnIndex(DBAdapter.getPriceColumn()));
            boughtIntBool = cursor.getInt(cursor.getColumnIndex(DBAdapter.getBoughtColumn()));

            if (boughtIntBool==1)
                il.add(new ListItem(prodName, price, quantity, true));
            else if(boughtIntBool==0)
                il.add(new ListItem(prodName, price, quantity, false));
            else
                System.out.println("DUPA");
        }

        return il;
    }



}
