package com.example.adam.mini_projekt_1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<ListItem> list;
    private Context context;
    private ArrayList<Information> data;
    private int previousPosition = 0;
    private DBAdapter myDB;



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

        public ViewHolder(View view) {
            super(view);
            product_name = (TextView) view.findViewById(R.id.product_name_text_view);
            price = (TextView) view.findViewById(R.id.price_text_view);
            quantity = (TextView) view.findViewById(R.id.quantity_text_view);
            checked = (CheckBox) view.findViewById(R.id.checked_checkbox);

            view.setOnClickListener(this);
            checked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        myDB.changeCheckbox(1, true);
                    }
                    else{
                        myDB.changeCheckbox(1, false);

                    };
                }
            });

        }


        @Override
        public void onClick(View v) {
            Toast.makeText(MyAdapter.this.context, "Editing item " + product_name.getText(),
                    Toast.LENGTH_LONG).show();

            //Intent add_item_intent = new Intent(v.getContext(), AddActivity.class);
            //context.startActivity(add_item_intent);

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
    public void onBindViewHolder(ViewHolder h, int position) {
        ListItem li = list.get(position);
        h.product_name.setText(li.getProductName());
        h.price.setText("" + li.getPrice());
        h.quantity.setText("" + li.getQuantity());

       if (li.isChecked())
           h.checked.setChecked(true);
       else
           h.checked.setChecked(false);
    }



    @Override
    public int getItemCount() {
        return list.size();
    }




    public class Information{
        public int rowId;
    }
}
