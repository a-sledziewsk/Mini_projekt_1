package com.example.adam.mini_projekt_1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String productName = intent.getStringExtra("Product name");
        String quantity = intent.getStringExtra("Quantity");
        String price = intent.getStringExtra("Price");
        String msg = quantity + " " + productName + " added " + "with cost of " + price + " each.";

        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
