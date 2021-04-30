package com.example.medenjak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Home extends Page {

    private int page_cntr;
    private int PAGE_COUNT;

    private int f_id;
    private int s_id;
    private int t_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        PAGE_COUNT = Constants.PRODUCTS.length / 3;
        page_cntr = 1;
        loadProducts();

        ImageButton btn = findViewById(R.id.btn_prev);
        btn.setVisibility(View.INVISIBLE);

        if (page_cntr >= PAGE_COUNT+1) {
            btn = findViewById(R.id.btn_next);
            btn.setVisibility(View.INVISIBLE);
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

    public void details(View view){
        int id = view.getId();
        int i;
        if (id == f_id) i = 0;
        else if (id == s_id) i = 1;
            else if (id==t_id) i =2;
            else i = -1;

        if (i>-1){
            Constants.details = Constants.PRODUCTS[3*(page_cntr-1)+i];
            Intent intent = new Intent(this, DetailsActivity.class);
            startActivity(intent);
        }
        else
            Constants.details = null;
    }

    private void loadProducts() {
        Product prod = Constants.PRODUCTS[(page_cntr - 1) * 3];
        ImageView iv = findViewById(R.id.pic1);
        iv.setImageResource(prod.photo);
        f_id = iv.getId();


        TextView name = findViewById(R.id.name1);
        name.setText(prod.name);

        TextView price = findViewById(R.id.price1);
        price.setText(prod.price + ",00 rsd");


        if ((page_cntr - 1) * 3 + 1 < Constants.PRODUCTS.length) {


            prod = Constants.PRODUCTS[(page_cntr - 1) * 3 + 1];
            iv = findViewById(R.id.pic2);
            iv.setImageResource(prod.photo);
            iv.setVisibility(View.VISIBLE);
            s_id = iv.getId();

            name = findViewById(R.id.name2);
            name.setText(prod.name);
            name.setVisibility(View.VISIBLE);

            price = findViewById(R.id.price2);
            price.setText(prod.price + ",00 rsd");
            price.setVisibility(View.VISIBLE);


            if ((page_cntr - 1) * 3 + 2 < Constants.PRODUCTS.length) {
                prod = Constants.PRODUCTS[(page_cntr - 1) * 3 + 2];
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
            }
            else{
                iv = findViewById(R.id.pic3);
                iv.setVisibility(View.INVISIBLE);

                name = findViewById(R.id.name3);
                name.setVisibility(View.INVISIBLE);

                price = findViewById(R.id.price3);
                price.setVisibility(View.INVISIBLE);
            }
        }
        else{
            iv = findViewById(R.id.pic2);
            iv.setVisibility(View.INVISIBLE);

            name = findViewById(R.id.name2);
            name.setVisibility(View.INVISIBLE);

            price = findViewById(R.id.price2);
            price.setVisibility(View.INVISIBLE);

            iv = findViewById(R.id.pic3);
            iv.setVisibility(View.INVISIBLE);

            name = findViewById(R.id.name3);
            name.setVisibility(View.INVISIBLE);

            price = findViewById(R.id.price3);
            price.setVisibility(View.INVISIBLE);
        }
    }
}