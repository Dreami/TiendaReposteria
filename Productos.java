package com.example.maple.tiendareposteria;

/**
 * Created by maple on 9/8/2017.
 */

public class Productos {

    private int leche = 0;
    private int harina = 0;
    private int huevo = 0;
    private int levadura = 0;
    private float precio = 0f;

    public void agregar(String nombre) {
        switch(nombre) {
            case "Leche":
                this.leche++;
                this.precio += 10.5f;
                break;
            case "Harina":
                this.harina++;
                this.precio += 25f;
                break;
            case "Huevo":
                this.huevo++;
                this.precio += 6f;
                break;
            case "Levadura":
                this.levadura++;
                this.precio += 12.5f;
                break;
        }
    }

    public void restar(String nombre) {
        switch(nombre) {
            case "Leche":
                if(this.leche > 0) {
                    this.leche--;
                    this.precio += 10.5f;
                }
                break;
            case "Harina":
                if(this.harina > 0) {
                    this.harina--;
                    this.precio += 25f;
                }
                break;
            case "Huevo":
                if(this.huevo > 0) {
                    this.huevo--;
                    this.precio += 6f;
                }
                break;
            case "Levadura":
                if(this.levadura > 0) {
                    this.levadura--;
                    this.precio += 12.5f;
                }
                break;
        }
    }

    public String getPrecio() {
        return Float.toString(precio);
    }

    public String getLeche() {
        return Integer.toString(leche);
    }

    public String getHarina() {
        return Integer.toString(harina);
    }

    public String getHuevo() {
        return Integer.toString(huevo);
    }

    public String getLevadura() {
        return Integer.toString(levadura);
    }
    public String getSumaUnidades() {
        return Integer.toString(leche + harina + huevo + levadura);
    }
}
