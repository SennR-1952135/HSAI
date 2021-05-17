package com.example.project.DataBase;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.room.*;

import com.example.project.Enums.Category;
import com.example.project.Enums.Gender;
import com.example.project.Enums.Color;
import com.example.project.Enums.Size;

@Entity(tableName = "Product")
public class Product {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ProductID")
    public long mProductID;

    @ColumnInfo(name = "Price")
    public float mPrice;

    @ColumnInfo(name = "Name")
    public String mName;

    @ColumnInfo(name = "Discription")
    public String mDiscription;

    @ColumnInfo(name = "StoreID")
    public long mStoreID;

    @ColumnInfo(name = "Color")
    public Color mColor;

    @ColumnInfo(name = "Discounted")
    public boolean mDiscounted;
    //private ArrayList<Size> mMaaten;

    @ColumnInfo(name = "Gender")
    public Gender mGender;

    @ColumnInfo(name = "DiscountAmount")
    public float mDiscountAmount;

    @ColumnInfo(name = "Image")
    public int mImage;

    @ColumnInfo(name = "Category")
    public Category mCategory;

    @ColumnInfo(name = "Size")
    public Size mSize;


    public Product(String name, String discription , float price, float discountAmount, int image, Category category, long storeID, Color color, Gender gender, Size size){
        this.mName = name;
        this.mStoreID = storeID;
        this.mPrice = price;
        this.mDiscription = discription;
        this.mColor = color;
        this.mDiscounted = (discountAmount == 0);
        this.mDiscountAmount = discountAmount;
        this.mGender = gender;
        this.mImage = image;
        this.mCategory = category;
        this.mSize = size;
    }

    public long getID() { return mProductID; }

//    /*
//    Getters AND SETTERS
//     */
//
//    public String getName() {
//        return mName;
//    }
//
////    public void setName(String mName) {
////        this.mName = mName;
////    }
//
//    public String getStore() {
//        return mStore;
//    }
//
////    public void setStore(String mStore) {
////        this.mStore = mStore;
////    }
//
//    public float getPrice() {
//        return mPrice;
//    }
//
//    public void setPrice(float mPrice) {
//        this.mPrice = mPrice;
//    }
//
//    public float getDiscountPrice() {
//        return mWithoutDiscountPrice;
//    }
//
//    public void setDiscountPrice(float mDiscountPrice) {
//        this.mWithoutDiscountPrice = mDiscountPrice;
//    }
//
//    public String getDiscription() {
//        return mDiscription;
//    }
//
//    public void setDiscription(String mDiscription) {
//        this.mDiscription = mDiscription;
//    }
//
//    public Drawable getImage() {
//        return mImage;
//    }
//
//    public void setImage(Drawable mImage) {
//        this.mImage = mImage;
//    }
//    public Category getCategory() {
//        return mCat;
//    }
//
//    public void setCategory(Category mCat) {
//        this.mCat = mCat;
//    }
//
//    public float getPrice() { return mPrice; }
//    public String getName() { return mName; }
//    public String getBeschrijving() { return mBeschrijving; }
//    public long getWinkelID() { return mWinkelID; }


}
