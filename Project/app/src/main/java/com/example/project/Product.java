package com.example.project;

import androidx.room.*;

@Entity(tableName = "Product")
public class Product {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    public float mPrice;
    public String mName;
    public String mBeschrijving;
    public Store mWinkel;

    public Product(String name, String beschrijving, float price, Store winkel){
        mPrice = price;
        mName = name;
        mBeschrijving = beschrijving;
        mPrice = price;
        mWinkel = winkel;
    }


}
