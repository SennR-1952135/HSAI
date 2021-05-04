package com.example.project;

import androidx.room.*;

@Entity(tableName = "Product")
public class Product {

    @PrimaryKey(autoGenerate = true)
    public long productID;

    public float mPrice;
    public String mName;
    public String mBeschrijving;
    public long mWinkelID;

    public Product(String name, String beschrijving, float price, long winkelID){
        mPrice = price;
        mName = name;
        mBeschrijving = beschrijving;
        mPrice = price;
        mWinkelID = winkelID;
    }


}
