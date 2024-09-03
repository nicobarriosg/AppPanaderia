package com.example.evaluacion1;

public class Producto {
    private String name;
    private int quantity;
    private String barcode;
    private int imageResId;

    public Producto(String name, int quantity, String barcode, int imageResId) {
        this.name = name;
        this.quantity = quantity;
        this.barcode = barcode;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getBarcode() {
        return barcode;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
