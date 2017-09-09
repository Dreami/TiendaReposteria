package com.example.maple.tiendareposteria;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements notifyChanges{
    ListView listView = null;
    ArrayList<Ingredientes> shoppingList = null;
    TextView priceTotal;
    TextView unitTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list_products);
        priceTotal = (TextView) findViewById(R.id.precioTotal);
        unitTotal = (TextView) findViewById(R.id.unidadesTotal);

        shoppingList = new ArrayList<>();
        Ingredientes leche = new Ingredientes("Leche", R.drawable.lechelala, 10.5f);
        Ingredientes harina = new Ingredientes("Harina", R.drawable.harina, 25f);
        Ingredientes huevo = new Ingredientes("Huevo", R.drawable.huevo, 6f);
        Ingredientes levadura = new Ingredientes("Levadura", R.drawable.levadura, 12.5f);
        Collections.addAll(shoppingList, leche, harina, huevo, levadura);

        MyListAdapter myListAdapter = new MyListAdapter(this, R.layout.product, shoppingList);
        listView.setAdapter(myListAdapter);
    }

    @Override
    public void update() {
        float totalPrice = 0;
        int totalUnit = 0;
        for (Ingredientes ing : shoppingList) {
            totalUnit += Integer.parseInt(ing.getUnits());
            totalPrice += ing.getTotalPrice();
        }
        priceTotal.setText(Float.toString(totalPrice));
        unitTotal.setText(Float.toString(totalUnit));
    }


    private class MyListAdapter extends ArrayAdapter<Ingredientes> {

        private int layout;
        private List<Ingredientes> mObjects;

        private MyListAdapter(Context context, int resource, List<Ingredientes> objects) {
            super(context, resource, objects);
            mObjects = objects;
            layout = resource;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder mainViewholder = null;
            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewHolder = new ViewHolder();

                viewHolder.add = (Button) convertView.findViewById(R.id.add);
                viewHolder.substract = (Button) convertView.findViewById(R.id.substract);
                viewHolder.amount = (TextView) convertView.findViewById(R.id.amount);
                viewHolder.title = (TextView) convertView.findViewById(R.id.productName);
                viewHolder.price = (TextView) convertView.findViewById(R.id.productPrice);
                viewHolder.thumbnail = (ImageView) convertView.findViewById(R.id.productThumbnail);
                viewHolder.priceTotal = (TextView) convertView.findViewById(R.id.priceTotal);
                convertView.setTag(viewHolder);
            }

            mainViewholder = (ViewHolder) convertView.getTag();

            mainViewholder.title.setText(getItem(position).getProducto());
            mainViewholder.price.setText(getItem(position).getPrice());
            mainViewholder.thumbnail.setImageResource(getItem(position).getThumbnail());
            mainViewholder.amount.setText(getItem(position).getUnits());
            mainViewholder.priceTotal.setText(Float.toString(getItem(position).getTotalPrice()));

            mainViewholder.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getItem(position).add();
                    ViewGroup viewGroup = (ViewGroup) v.getParent();
                    TextView amount = (TextView) viewGroup.findViewById(R.id.amount);
                    TextView priceTotal = (TextView) viewGroup.findViewById(R.id.priceTotal);
                    amount.setText(getItem(position).getUnits());
                    priceTotal.setText(Float.toString(getItem(position).getTotalPrice()));
                    update();
                }
            });

            mainViewholder.substract.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getItem(position).substract();
                    ViewGroup viewGroup = (ViewGroup) v.getParent();
                    TextView amount = (TextView) viewGroup.findViewById(R.id.amount);
                    TextView priceTotal = (TextView) viewGroup.findViewById(R.id.priceTotal);
                    amount.setText(getItem(position).getUnits());
                    priceTotal.setText(Float.toString(getItem(position).getTotalPrice()));
                    update();
                }
            });
            return convertView;
        }

    }

    public class ViewHolder {
        TextView title;
        TextView price;
        ImageView thumbnail;
        Button add;
        Button substract;
        TextView priceTotal;
        TextView amount;
    }

}
