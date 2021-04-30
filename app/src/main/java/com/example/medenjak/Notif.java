package com.example.medenjak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.gson.Gson;

public class Notif extends Page {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif);

        SharedPreferences settings = getApplicationContext().getSharedPreferences(Constants.PREF_NAME, 0);
        Gson gson = new Gson();
        String json = settings.getString(Constants.USER_KEY, "");
        user = gson.fromJson(json, User.class);

        loadMessages();
    }


    private void loadMessages(){
        if(user.notifications.size()==0) return;
        TableLayout messages = (TableLayout) findViewById(R.id.tbl);
        messages.setStretchAllColumns(true);
        messages.bringToFront();
        if(user.notifications.size()==0) return;
        for(int i=user.notifications.size()-1; i>=0; i--){
            TableRow tr = new TableRow(this);
            TextView c1 = new TextView(this);
            c1.setText(user.notifications.get(i));
            c1.setWidth(150);
            c1.setHeight(150);
            c1.setTextColor(getResources().getColor(R.color.brown));
            c1.setTextSize(15);

            Button btn = new Button(this);
            btn.setId(i);
            btn.setText("Izbriši");
            btn.setOnClickListener(
                    v -> {
                        user.notifications.remove(v.getId());
                        SharedPreferences settings = getApplicationContext().getSharedPreferences(Constants.PREF_NAME, 0);
                        SharedPreferences.Editor prefsEditor = settings.edit();
                        Gson gson = new Gson();
                        String json = gson.toJson(user);
                        prefsEditor.putString(Constants.USER_KEY, json);
                        prefsEditor.commit();
                        loadMessages();
                    }
            );
      //      btn.setLayoutParams(new LinearLayout.LayoutParams(80, 20));

            tr.addView(c1);
            tr.addView(btn);
            messages.addView(tr);
        }
    }



}