package com.example.medenjak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

public class DetailsActivity extends Page {

    private int cnt;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        loadDetails();
        cnt=1;

        SharedPreferences settings = getApplicationContext().getSharedPreferences(Constants.PREF_NAME, 0);
        Gson gson = new Gson();
        String json = settings.getString(Constants.USER_KEY, "");
        user = gson.fromJson(json, User.class);
    }

    private void loadDetails(){
        TextView tv = findViewById(R.id.name);
        tv.setText("Proizvod: "+Constants.details.name);

        tv = findViewById(R.id.price);
        tv.setText("Cena: "+Constants.details.price);

        tv = findViewById(R.id.descr);
        tv.setText("Opis: "+Constants.details.description);

        tv = findViewById(R.id.use);
        tv.setText("Upotreba: "+Constants.details.usage);


        ImageView img = findViewById(R.id.pic);
        img.setImageResource(Constants.details.photo);
    }

    public void up(View view){
        cnt++;
        TextView tv = findViewById(R.id.cnt);
        tv.setText(cnt+"");
    }

    public void down(View view){
        if (cnt>1) cnt--;
        TextView tv = findViewById(R.id.cnt);
        tv.setText(cnt+"");
    }

    public void addToCart(View view){
        user.addItem(Constants.details, cnt);

        SharedPreferences settings = getApplicationContext().getSharedPreferences(Constants.PREF_NAME, 0);
        SharedPreferences.Editor prefsEditor = settings.edit();

        Gson gson = new Gson();
        String json = gson.toJson(user);
        prefsEditor.putString(Constants.USER_KEY, json);
        prefsEditor.commit();

        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
}