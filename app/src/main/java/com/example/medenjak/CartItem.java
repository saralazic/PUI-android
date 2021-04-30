package com.example.medenjak;

public class CartItem {
    public Product prod;
    public int quantity;

    public CartItem(Product p, int q){
        this.prod = p;
        this.quantity = q;
    }

    public int price(){
        return quantity * prod.price;
    }
}
