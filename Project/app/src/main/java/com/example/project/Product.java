package com.example.project;

import androidx.room.*;

import java.util.ArrayList;

@Entity(tableName = "Product")
public class Product {

    enum Color {
        ROOD,
        GROEN,
        BLAUW,
        ORANJE,
        GEEL,
        WIT,
        ZWART,
        PAARS,
        ROOS
    }

    enum Size {
        XS,
        S,
        M,
        L,
        XL
    }

    enum Type {
        BROEK,
        TSHIRT,
        SHORT,
        KLEEDJE,
        JAS,
        ROK,
        SCHOEN
    }

    enum Gender {
        MAN,
        VROUW,
        KIND,
        UNISEX
    }

    @PrimaryKey(autoGenerate = true)
    private long productID;

    private float mPrice;
    private String mName;
    private String mBeschrijving;
    private long mWinkelID;
    private Color mKleur;
    private boolean mKorting;
    private ArrayList<Size> mMaaten;
    private Type mType;
    private Gender mbedoeldVoor;

    public Product(String name, String beschrijving, float price, long winkelID){
        mPrice = price;
        mName = name;
        mBeschrijving = beschrijving;
        mPrice = price;
        mWinkelID = winkelID;
    }

    public float getPrice() { return mPrice; }
    public String getName() { return mName; }
    public String getBeschrijving() { return mBeschrijving; }
    public long getWinkelID() { return mWinkelID; }


}
