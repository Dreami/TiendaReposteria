package com.example.maple.tiendareposteria;

/**
 * Created by maple on 9/8/2017.
 */

public class Ingredientes {
    private String producto;
    private int thumbnail;
    private float price;

    public String getProducto() {
        return producto;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public String getPrice() {
        return Float.toString(price);
    }

    public Ingredientes(String producto, int thumbnail, float price) {
        this.producto = producto;
        this.thumbnail = thumbnail;

        this.price = price;
    }
}
