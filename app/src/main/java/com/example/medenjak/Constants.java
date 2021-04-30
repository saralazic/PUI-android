package com.example.medenjak;


import android.graphics.drawable.Drawable;

public final class Constants {
    public static final String PREF_NAME = "PersonalData";
    public static final String USER_KEY = "user";
    public static final String PRODUCTS_KEY = "prods";

    public static Product details = null;

    public static final Product[] PRODUCTS = {
            new Product(
                    0,
                    "Livadski med",
                    R.drawable.livadski,
                    380,
                    "Svež livadski med, 1kg",
                    "U ishrani, za kuvanje"
            ),
            new Product(
                    1,
                    "Bagremov med",
                    R.drawable.bagremov,
                    600,
                    "Svež bagremov med, 1kg",
                    "U ishrani, za kuvanje"
            ),
            new Product(
                    2,
                    "Saće",
                    R.drawable.sace,
                    380,
                    "Pčelinje saće, 200g",
                    "U ishrani"
            ),
            new Product(
                    3,
                    "Propolis kapi",
                    R.drawable.prop,
                    486,
                    "Propolis kapi, 150ml",
                    "Suplement ishrani"
            ),
            new Product(
                    4,
                    "Propolis Plus tablete",
                    R.drawable.propplus,
                    296,
                    "Propolis plus tablete sa ukusom narandže",
                    "Suplement ishrani"
            )
    };

}
