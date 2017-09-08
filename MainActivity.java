package com.example.maple.tiendareposteria;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView = null;
    ArrayList<Ingredientes> shoppingList = null;
    Productos productos;
    TextView priceTotal;
    TextView unitTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        productos = new Productos();

        listView = (ListView) findViewById(R.id.list_products);
        priceTotal = (TextView) findViewById(R.id.precioTotal);
        unitTotal = (TextView) findViewById(R.id.unidadesTotal);

        shoppingList = new ArrayList<>();
        shoppingList.add(new Ingredientes("Leche", R.drawable.lechelala, 10.5f));
        shoppingList.add(new Ingredientes("Harina", R.drawable.harina, 25f));
        shoppingList.add(new Ingredientes("Huevo", R.drawable.huevo, 6f));
        shoppingList.add(new Ingredientes("Levadura", R.drawable.lechelala, 12.5f));
        listView.setAdapter(new MyListAdapter(this, R.layout.product, shoppingList, productos));

        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                priceTotal.setText(productos.getPrecio());
                priceTotal.setText(productos.getSumaUnidades());
            }
        });
    }


    private class MyListAdapter extends ArrayAdapter<Ingredientes> {
        ViewHolder mainViewholder = null;

        private int layout;
        private List<Ingredientes> mObjects;
        private Productos productos;

        private MyListAdapter(Context context, int resource, List<Ingredientes> objects, Productos productos) {
            super(context, resource, objects);
            mObjects = objects;
            layout = resource;
            this.productos = productos;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

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
                convertView.setTag(viewHolder);
            }

            mainViewholder = (ViewHolder) convertView.getTag();
            mainViewholder.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    productos.agregar(getItem(position).getProducto());
                    switch(getItem(position).getProducto()) {
                        case "Leche":
                            mainViewholder.amount.setText(productos.getLeche());
                            break;
                        case "Harina":
                            mainViewholder.amount.setText(productos.getHarina());
                            break;
                        case "Huevo":
                            mainViewholder.amount.setText(productos.getHuevo());
                            break;
                        case "Levadura":
                            mainViewholder.amount.setText(productos.getLevadura());
                            break;
                    }
                }
            });

            mainViewholder.substract.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    productos.restar(getItem(position).getProducto());
                    switch(getItem(position).getProducto()) {
                        case "Leche":
                            mainViewholder.amount.setText(productos.getLeche());
                            break;
                        case "Harina":
                            mainViewholder.amount.setText(productos.getHarina());
                            break;
                        case "Huevo":
                            mainViewholder.amount.setText(productos.getHuevo());
                            break;
                        case "Levadura":
                            mainViewholder.amount.setText(productos.getLevadura());
                            break;
                    }
                }
            });
            mainViewholder.title.setText(getItem(position).getProducto());
            mainViewholder.price.setText(getItem(position).getPrice());
            mainViewholder.thumbnail.setImageResource(getItem(position).getThumbnail());

            return convertView;
        }
    }

    public class ViewHolder {
        TextView title;
        TextView price;
        ImageView thumbnail;
        Button add;
        Button substract;
        TextView amount;
    }

}
