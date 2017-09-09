package com.example.maple.tiendareposteria;

/**
 * Created by maple on 9/8/2017.
 */

public class Ingredientes {
    private String producto;
    private int thumbnail;
    private float price;
    private float totalPrice;

    private int units;

    public Ingredientes(String producto, int thumbnail, float price) {
        this.producto = producto;
        this.thumbnail = thumbnail;
        this.price = price;
        this.units = 0;
        this.totalPrice = 0;
    }

    public void add() {
        units++;
        totalPrice = price * units;
    }

    public void substract() {
        if(units > 0) {
            units--;
            totalPrice = price * units;
        }
    }

    public String getProducto() {
        return producto;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public String getPrice() {
        return Float.toString(price);
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public String getUnits() {
        return Integer.toString(units);
    }
}
