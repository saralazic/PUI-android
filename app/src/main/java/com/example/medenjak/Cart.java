package com.example.medenjak;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;


public class Cart extends Page {

    private int page_cntr;
    private int PAGE_COUNT;

    private int f_id;
    private int s_id;
    private int t_id;

    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        SharedPreferences settings = getApplicationContext().getSharedPreferences(Constants.PREF_NAME, 0);
        Gson gson = new Gson();
        String json = settings.getString(Constants.USER_KEY, "");
        user = gson.fromJson(json, User.class);

        PAGE_COUNT =  (user.cart.size() / 3) - ((user.cart.size()%3==0)?1:0);
        page_cntr = 1;

        ImageButton btn;

        setVis();
        loadProducts();
    }

    public void delete(View view){
        int id = view.getId();
        int i;
        if (id == f_id) i = 0;
        else if (id == s_id) i = 1;
        else if (id == t_id) i = 2;
        else i = -1;

        if (i>-1){
            user.cart.removeElementAt(3*(page_cntr-1)+i);
            if(i==0 && page_cntr==PAGE_COUNT+1 && page_cntr>1)
            {
                page_cntr--;
            }
            PAGE_COUNT =  (user.cart.size() / 3) - ((user.cart.size()%3==0)?1:0);
            SharedPreferences settings = getApplicationContext().getSharedPreferences(Constants.PREF_NAME, 0);
            SharedPreferences.Editor prefsEditor = settings.edit();
            Gson gson = new Gson();
            String json = gson.toJson(user);
            prefsEditor.putString(Constants.USER_KEY, json);
            prefsEditor.commit();

          loadProducts();
        }
    }

    public void next(View view) {
        page_cntr++;
        ImageButton btn = findViewById(R.id.btn_prev);
        btn.setVisibility(View.VISIBLE);
        if (page_cntr >= PAGE_COUNT+1) {
            btn = findViewById(R.id.btn_next);
            btn.setVisibility(View.INVISIBLE);
        }
        loadProducts();
    }

    public void prev(View view) {
        page_cntr--;
        ImageButton btn = findViewById(R.id.btn_next);
        btn.setVisibility(View.VISIBLE);
        if (page_cntr <= 1) {
            btn = findViewById(R.id.btn_prev);
            btn.setVisibility(View.INVISIBLE);
        }
        loadProducts();
    }

    private void loadProducts() {
        ImageButton ibtn;

        setVis();

        if (user.cart.empty()) {
            TextView tv = findViewById(R.id.name1);
            tv.setText("Korpa je prazna!");
            tv.setVisibility(View.VISIBLE);

            invisible_first();
            invisible_second();
            invisible_third();

            TextView tv1 = findViewById(R.id.check);
            tv1.setText("Iznos: " + user.check()+"rsd");

            return;
        }

        TextView tv = findViewById(R.id.check);
        tv.setText("Iznos: " + user.check()+"rsd");

        CartItem ci = user.cart.get((page_cntr - 1) * 3);
        Product prod = ci.prod;
        ImageView iv = findViewById(R.id.pic1);
        iv.setImageResource(prod.photo);
        iv.setVisibility(View.VISIBLE);


        TextView name = findViewById(R.id.name1);
        name.setText(prod.name);
        name.setVisibility(View.VISIBLE);

        TextView price = findViewById(R.id.price1);
        price.setText(prod.price + ",00 rsd");
        price.setVisibility(View.VISIBLE);

        TextView quant;
        quant = findViewById(R.id.quant1);
        quant.setText("x"+ci.quantity);
        quant.setVisibility(View.VISIBLE);

        Button btn = (Button) findViewById(R.id.button1);
        btn.setVisibility(View.VISIBLE);
        f_id = btn.getId();


        if ((page_cntr - 1) * 3 + 1 < user.cart.size()) {

            ci = user.cart.get((page_cntr - 1) * 3+1);
            prod = ci.prod;
            iv = findViewById(R.id.pic2);
            iv.setImageResource(prod.photo);
            iv.setVisibility(View.VISIBLE);


            name = findViewById(R.id.name2);
            name.setText(prod.name);
            name.setVisibility(View.VISIBLE);

            price = findViewById(R.id.price2);
            price.setText(prod.price + ",00 rsd");
            price.setVisibility(View.VISIBLE);

            quant = findViewById(R.id.quant2);
            quant.setText("x"+ci.quantity+"");

            btn = (Button) findViewById(R.id.button2);
            btn.setVisibility(View.VISIBLE);
            s_id = btn.getId();

            if ((page_cntr - 1) * 3 + 2 < user.cart.size()) {
                ci = user.cart.get((page_cntr - 1) * 3+2);
                prod = ci.prod;
                iv = findViewById(R.id.pic3);
                iv.setImageResource(prod.photo);
                iv.setVisibility(View.VISIBLE);
                t_id = iv.getId();

                name = findViewById(R.id.name3);
                name.setText(prod.name);
                name.setVisibility(View.VISIBLE);

                price = findViewById(R.id.price3);
                price.setText(prod.price + ",00 rsd");
                price.setVisibility(View.VISIBLE);

                quant = findViewById(R.id.quant3);
                quant.setText("x"+ci.quantity+"");

                btn = (Button) findViewById(R.id.button3);
                btn.setVisibility(View.VISIBLE);

                t_id = btn.getId();
            }
            else{
                invisible_third();
            }
        }
        else{
           invisible_second();
           invisible_third();
        }
    }


    public void setVis(){
        ImageButton ibtn;

        if (page_cntr >= PAGE_COUNT+1) {
            ibtn = findViewById(R.id.btn_next);
            ibtn.setVisibility(View.INVISIBLE);
        }

        if (page_cntr<=1) {
            ibtn = findViewById(R.id.btn_prev);
            ibtn.setVisibility(View.INVISIBLE);
        }
    }

    public void order(View view){
        user.cart.removeAllElements();
        SharedPreferences settings = getApplicationContext().getSharedPreferences(Constants.PREF_NAME, 0);
        SharedPreferences.Editor prefsEditor = settings.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        prefsEditor.putString(Constants.USER_KEY, json);
        prefsEditor.commit();
        loadProducts();
    }


    public void invisible_second(){
        ImageView iv;
        TextView name, price, quant;
        Button btn;

        iv = findViewById(R.id.pic2);
        iv.setVisibility(View.INVISIBLE);

        name = findViewById(R.id.name2);
        name.setVisibility(View.INVISIBLE);

        price = findViewById(R.id.price2);
        price.setVisibility(View.INVISIBLE);

        quant = findViewById(R.id.quant2);
        quant.setVisibility(View.INVISIBLE);

        btn = (Button) findViewById(R.id.button2);
        btn.setVisibility(View.INVISIBLE);
    }

    public void  invisible_third(){
        ImageView iv;
        TextView name, price, quant;
        Button btn;


        iv = findViewById(R.id.pic3);
        iv.setVisibility(View.INVISIBLE);

        name = findViewById(R.id.name3);
        name.setVisibility(View.INVISIBLE);

        price = findViewById(R.id.price3);
        price.setVisibility(View.INVISIBLE);

        quant = findViewById(R.id.quant3);
        quant.setVisibility(View.INVISIBLE);

        btn = (Button) findViewById(R.id.button3);
        btn.setVisibility(View.INVISIBLE);
    }

    public void invisible_first(){
        ImageView iv1 = findViewById(R.id.pic1);
        iv1.setVisibility(View.INVISIBLE);

        TextView price1 = findViewById(R.id.price1);
        price1.setVisibility(View.INVISIBLE);

        TextView quant1;
        quant1 = findViewById(R.id.quant1);
        quant1.setVisibility(View.INVISIBLE);

        Button btn1 = (Button) findViewById(R.id.button1);
        btn1.setVisibility(View.INVISIBLE);
    }
}
