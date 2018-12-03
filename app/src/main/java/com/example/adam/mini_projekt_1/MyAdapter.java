package com.example.adam.mini_projekt_1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<ListItem> list;
    private Context context;
    private DBAdapter myDB;
    private DatabaseReference mDatabase;



    public MyAdapter(List<ListItem> list_item_list, Context ctx){
        list = list_item_list;
        context = ctx;
        openDB();
    }

    private void openDB(){
        myDB = new DBAdapter(MyAdapter.this.context);
        myDB.open();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView product_name;
        public TextView price;
        public TextView quantity;
        public CheckBox checked;
        public View v;

        public TextView product_name_label;
        public TextView price_label;
        public TextView quantity_label;

        public ViewHolder(View view) {
            super(view);
            this.v = view;

            product_name = (TextView) view.findViewById(R.id.product_name_text_view);
            price = (TextView) view.findViewById(R.id.price_text_view);
            quantity = (TextView) view.findViewById(R.id.quantity_text_view);
            checked = (CheckBox) view.findViewById(R.id.checked_checkbox);

            product_name_label = view.findViewById(R.id.prod_name_label);
            price_label = view.findViewById(R.id.price_label);
            quantity_label = view.findViewById(R.id.quantity_label);


            product_name_label.setTextColor(SharedPreferencesDB.getColorFromSharePreferences(context));
            price_label.setTextColor(SharedPreferencesDB.getColorFromSharePreferences(context));
            quantity_label.setTextColor(SharedPreferencesDB.getColorFromSharePreferences(context));

            product_name_label.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(context));
            price_label.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(context));
            quantity_label.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(context));

            //TODO checkbox gets value from DB


            view.setOnClickListener(this);
            checked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
                    String prod_name = product_name.getText().toString();
                    mDatabase = FirebaseDatabase.getInstance().getReference("ProductList");
                    Query productQuery = mDatabase.orderByChild("productName").equalTo(prod_name);

                    productQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot productSnapshot: dataSnapshot.getChildren()){
                                productSnapshot.getRef().child("checked").setValue(isChecked);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
            });

        }


        @Override
        public void onClick(View v) {
        }
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder h, final int position) {

        ListItem li = list.get(position);
        h.product_name.setText(li.getProductName());
        h.price.setText("" + li.getPrice());
        h.quantity.setText("" + li.getQuantity());

        h.product_name.setTextColor(SharedPreferencesDB.getColorFromSharePreferences(context));
        h.price.setTextColor(SharedPreferencesDB.getColorFromSharePreferences(context));
        h.quantity.setTextColor(SharedPreferencesDB.getColorFromSharePreferences(context));;
        h.checked.setTextColor(SharedPreferencesDB.getColorFromSharePreferences(context));

        h.product_name.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(context));
        h.price.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(context));
        h.quantity.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(context));
        h.checked.setTextSize(SharedPreferencesDB.getFontFromSharePreferences(context));



       if (li.isChecked())
           h.checked.setChecked(true);
       else
           h.checked.setChecked(false);

       h.v.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
               Toast.makeText(MyAdapter.this.context, "Editing" ,
                       Toast.LENGTH_LONG).show();

               TextView prod_name_view;
               prod_name_view = (TextView) v.findViewById(R.id.product_name_text_view);
               String prod_text = prod_name_view.getText().toString();

               Intent goToEdit =  new Intent(MyAdapter.this.context, EditActivity.class);
               goToEdit.putExtra("key", prod_text);
               MyAdapter.this.context.startActivity(goToEdit);

           }
       });

       h.v.setOnLongClickListener(new View.OnLongClickListener(){
           @Override
           public boolean onLongClick(View v){
               TextView prod_name_view;
               prod_name_view = (TextView) v.findViewById(R.id.product_name_text_view);
               String prod_text = prod_name_view.getText().toString();
               String msg = "Item " + prod_text + " deleted";


               //TODO Deletion of item in list
               mDatabase = FirebaseDatabase.getInstance().getReference("ProductList");
               Query productQuery = mDatabase.orderByChild("productName").equalTo(prod_text);

               productQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       for (DataSnapshot productSnapshot: dataSnapshot.getChildren()){
                           productSnapshot.getRef().removeValue();
                       }
                       removeItem(position);

                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {

                   }
               });

               myDB.deleteRow(prod_text);
               Toast.makeText(MyAdapter.this.context, msg ,
                       Toast.LENGTH_LONG).show();


               return  true;
           }
       });

    }

    public void removeItem(int position){
        list.remove(position);
        notifyItemRemoved(position);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

}
