package com.example.medenjak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView view = (TextView) findViewById(R.id.err);
        view.setVisibility(View.INVISIBLE);

        initData();
    }

    public void login(View view) {
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);

        // Get from the SharedPreferences
        SharedPreferences settings = getApplicationContext().getSharedPreferences(Constants.PREF_NAME, 0);
        Gson gson = new Gson();
        String json = settings.getString(Constants.USER_KEY, "");
        User obj = gson.fromJson(json, User.class);

        if(username.getText().toString().equals(obj.uname) && password.getText().toString().equals(obj.password)){

            TextView tview = (TextView) findViewById(R.id.err);
            tview.setText("Ulogovan si");
            tview.setVisibility(View.INVISIBLE);

            goToHomePage();
        }
        else{
            TextView tview = (TextView) findViewById(R.id.err);
            tview.setText("Pogrešni kredencijali!");
            tview.setVisibility(View.VISIBLE);
        }
    }

    private void goToHomePage(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    private void initData(){

        SharedPreferences settings = getApplicationContext().getSharedPreferences(Constants.PREF_NAME, 0);
        if (settings.getString(Constants.USER_KEY, null)==null) {
            SharedPreferences.Editor editor = settings.edit();

            SharedPreferences.Editor prefsEditor = settings.edit();
            User usr = new User("pera", "Petar", "Petrovic", "064/0914218", "Vardarska 12, 14000 Valjevo", "pera123");

            Gson gson = new Gson();
            String json = gson.toJson(usr);
            prefsEditor.putString(Constants.USER_KEY, json);
            prefsEditor.commit();

        }
    }
}