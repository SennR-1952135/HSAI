package com.example.project;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

@Entity(tableName = "Product")
public class Product {



    enum Size {
        XS,
        S,
        M,
        L,
        XL
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
    private float mDiscount;
    private Drawable mImage;

    private Category mCat;


    public Product(String name, String store, String discription , float prodPrice, float withoutDiscount, Drawable img, Category cat){
        mName = name;
        mStore = store;
        mDiscription = discription;
        mPrice = prodPrice;
        mdiscount = withoutDiscount;
        mImage = img;
        mCat = cat;
    }

    /*
    Getters AND SETTERS
     */

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getStore() {
        return mStore;
    }

    public void setStore(String mStore) {
        this.mStore = mStore;
    }

    public float getPrice() {
        return mPrice;
    }

    public void setPrice(float mPrice) {
        this.mPrice = mPrice;
    }

    public float getDiscountPrice() {
        return mWithoutDiscountPrice;
    }

    public void setDiscountPrice(float mDiscountPrice) {
        this.mWithoutDiscountPrice = mDiscountPrice;
    }

    public String getDiscription() {
        return mDiscription;
    }

    public void setDiscription(String mDiscription) {
        this.mDiscription = mDiscription;
    }

    public Drawable getImage() {
        return mImage;
    }

    public void setImage(Drawable mImage) {
        this.mImage = mImage;
    }
    public Category getCategory() {
        return mCat;
    }

    public void setCategory(Category mCat) {
        this.mCat = mCat;
    }

    public float getPrice() { return mPrice; }
    public String getName() { return mName; }
    public String getBeschrijving() { return mBeschrijving; }
    public long getWinkelID() { return mWinkelID; }


}
