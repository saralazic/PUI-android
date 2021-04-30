package com.example.medenjak;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Page extends AppCompatActivity {

    public void goToHomePage(View view) {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void goToCartPage(View view) {
        Intent intent = new Intent(this, Cart.class);
        startActivity(intent);
    }

    public void goToNotifPage(View view) {
        Intent intent = new Intent(this, Notif.class);
        startActivity(intent);
    }

    public void goToDataPage(View view) {
        Intent intent = new Intent(this, Data.class);
        startActivity(intent);
    }

    public void goToLogoutPage(View view) {
        Intent intent = new Intent(this, Logout.class);
        startActivity(intent);
    }



}
