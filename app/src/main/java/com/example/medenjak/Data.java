package com.example.medenjak;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;


public class Data extends Page {

    String ime, prezime, lozinka, adresa, telefon, potvrda;

    User usr, nusr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        getData();
        TextView tview;
        tview = findViewById(R.id.greska);
        tview.setVisibility(View.INVISIBLE);
    }

    private void getData(){
        SharedPreferences settings = getApplicationContext().getSharedPreferences(Constants.PREF_NAME, 0);

        Gson gson = new Gson();
        String json = settings.getString(Constants.USER_KEY, "");
        usr = gson.fromJson(json, User.class);


        EditText name = findViewById(R.id.ime);
        name.setText(usr.fname);

        EditText last = findViewById(R.id.prezime);
        last.setText(usr.lname);

        EditText phone = findViewById(R.id.telefon);
        phone.setText(usr.phone);

        EditText addr = findViewById(R.id.adresa);
        addr.setText(usr.addr);

        EditText pass = findViewById(R.id.lozinka);
        pass.setText(usr.password);

        EditText dbl = findViewById(R.id.potvrda);
        dbl.setText(usr.password);
    }

    public void setData(View view) {
        EditText edit;
        TextView tview;
        tview = findViewById(R.id.greska);

        edit = findViewById(R.id.ime);
        ime = edit.getText().toString();

        edit = findViewById(R.id.prezime);
        prezime = edit.getText().toString();

        edit = findViewById(R.id.telefon);
        telefon = edit.getText().toString();

        edit = findViewById(R.id.adresa);
        adresa = edit.getText().toString();

        edit = findViewById(R.id.lozinka);
        lozinka = edit.getText().toString();

        edit = findViewById(R.id.potvrda);
        potvrda = edit.getText().toString();

        if (ime==null || ime.equals("")
            || prezime==null || prezime.equals("")
            || adresa==null || adresa.equals("")
            || telefon==null || telefon .equals("")
            || adresa==null || adresa.equals("")
            || lozinka==null || lozinka.equals("")
                || potvrda==null || potvrda.equals("")){
                getData();
                tview.setText("Sva polja su obavezna!");
                tview.setVisibility(View.VISIBLE);
                return;
        }

        if (!potvrda.equals(lozinka)){
            tview.setText("Lozinke se ne poklapaju!");
            tview.setVisibility(View.VISIBLE);
            return;
        }


        SharedPreferences settings = getApplicationContext().getSharedPreferences(Constants.PREF_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();

        User nusr = new User(usr.uname, ime, prezime, telefon, adresa, lozinka);

        Gson gson = new Gson();
        String json = gson.toJson(nusr);
        editor.putString(Constants.USER_KEY, json);
        editor.commit();
        usr = nusr;

        tview.setText("Uspešna promena podataka!");
        tview.setVisibility(View.VISIBLE);
        return;
    }
}