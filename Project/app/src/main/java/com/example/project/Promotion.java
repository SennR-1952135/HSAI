package com.example.project;

import android.graphics.drawable.Drawable;

import com.example.project.DataBase.Product;

import java.util.ArrayList;

public class Promotion {
    private ArrayList<Product> mPromotedItems;
    private Drawable mPromImg;
    public Promotion(Drawable img){mPromImg = img;}

    public ArrayList<Product> getPromotedItems() { return mPromotedItems; }
    public Drawable getPromImg(){return mPromImg;}
    public void setPromImg(Drawable img) {mPromImg = img;}
}
