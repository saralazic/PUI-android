package com.example.medenjak;
import android.content.Intent;
import android.os.Bundle;



import android.view.View;

public class Logout extends Page {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
    }

    public void logout(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}