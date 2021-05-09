package com.example.project;

import android.graphics.drawable.Drawable;

public class Product {

    private String mName;
    private String mStore;
    private float mPrice;
    private float mWithoutDiscountPrice;
    private String mDiscription;
    private Drawable mImage;


    private Category mCat;


    public Product(String name, String store, String discription , float prodPrice, float withoutDiscount, Drawable img, Category cat){
        mName = name;
        mStore = store;
        mDiscription = discription;
        mPrice = prodPrice;
        mWithoutDiscountPrice = withoutDiscount;
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


}
