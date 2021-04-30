package com.example.medenjak;

import android.content.SharedPreferences;


import java.util.Stack;

public class User {
    public String uname, fname, lname, phone, addr, password;
    public Stack<CartItem> cart = new Stack<CartItem>();
    public Stack<String> notifications= new Stack<String>();

    public User(String u, String f, String l, String ph, String a, String pass) {
        this.uname = u;
        this.fname = f;
        this.lname = l;
        this.phone = ph;
        this.addr = a;
        this.password = pass;

        this.notifications.push("Vaša porudžbina je odobrena. Prispeće paketa možete očekivati 2020-12-4");
    }

    public int check(){
        int p=0;
        for(int i=0; i<cart.size(); i++){
            p+=cart.get(i).price();
        }
        return p;
    }

    public void addItem(Product prod, int quant){
        boolean ind = false;
        CartItem c;
        for(int i=0; i<cart.size(); i++){
            c = cart.get(i);
            if (c.prod.id==prod.id){
                c.quantity+=quant;
                ind = true;
                return;
            }
        }
        if(!ind) {
                cart.push(new CartItem(prod, quant));
        }
    }

    public void deleteItem(int index){
        cart.removeElementAt(index);
    }
}
