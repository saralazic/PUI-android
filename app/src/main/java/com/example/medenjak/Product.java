package com.example.medenjak;

import android.graphics.drawable.Drawable;

public class Product {
    public int id;
    public String name;
    public int photo;
    public int price;
    public String description;
    public String usage;

    public Product(int id, String name, int photo, int price, String desc, String usg) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.photo = photo;
        this.description = desc;
        this.usage = usg;
    }
}
