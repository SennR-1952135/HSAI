package com.example.project;

import android.graphics.drawable.Drawable;
import androidx.room.*;

import com.example.project.Enums.*;


import java.util.ArrayList;

public class Product {

    private String mName;
    private String mStore;
    private float mPrice;
    private float mWithoutDiscountPrice;
    private String mDiscription;
    private Drawable mImage;

    public ArrayList<Size> getAvailableSizes() {
        return availableSizes;
    }

    public void setAvailableSizes(ArrayList<Size> availableSizes) {
        this.availableSizes = availableSizes;
    }
    public void setSizes(ArrayList<Size> sizes){
        this.sizes = sizes;
    }

    private ArrayList<Size> availableSizes;

    public ArrayList<Size> getSizes() {
        return sizes;
    }

    private ArrayList<Size> sizes;


    private ArrayList<Drawable> mImages;
    private int mProductId;

    private String mWinkel;

    private Category mCat;


    public Product(int id, String name, String store, String discription , float prodPrice, float withoutDiscount, Drawable img, Category cat){
        mProductId = id;
        mName = name;
        mStore = store;
        mDiscription = discription;
        mPrice = prodPrice;
        mWithoutDiscountPrice = withoutDiscount;
        mImage = img;
        mCat = cat;
    }

    public Product(int id, String name, String store, float prodPrice, float withoutDiscount, Drawable img){
        mProductId = id;
        mName = name;
        mStore = store;
        mPrice = prodPrice;
        mWithoutDiscountPrice = withoutDiscount;
        mImage = img;
    }


    public boolean isSizeAvailable(Size a){
        for(Size q: availableSizes){
            if(q.equals(a)){
                return true;
            }
        }
        return false;
    }
    /*
    Getters AND SETTERS
     */

    public ArrayList<Drawable> getImages() { return mImages; }

    public void setImages(ArrayList<Drawable> mImages) { this.mImages = mImages; }

    public int getProductId() { return mProductId; }

    public void setProductId(int mID) { this.mProductId = mID; }
    public String getName() {
        return mName;
    }

//    public void setName(String mName) {
//        this.mName = mName;
//    }

    public String getStore() {
        return mStore;
    }

//    public void setStore(String mStore) {
//        this.mStore = mStore;
//    }

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
