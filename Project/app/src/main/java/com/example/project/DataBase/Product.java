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
    private long mProductID;

    @ColumnInfo(name = "Price")
    private float mPrice;

    @ColumnInfo(name = "Name")
    private String mName;

    @ColumnInfo(name = "Discription")
    private String mDiscription;

    @ColumnInfo(name = "StoreID")
    private long mStoreID;

    @ColumnInfo(name = "Color")
    private Color mColor;

    @ColumnInfo(name = "Discounted")
    private boolean mDiscounted;

    @ColumnInfo(name = "Gender")
    private Gender mGender;

    @ColumnInfo(name = "DiscountAmount")
    private float mDiscountAmount;

    @ColumnInfo(name = "Image")
    private int mImage;

    @ColumnInfo(name = "Category")
    private Category mCategory;

    @ColumnInfo(name = "Size")
    private Size mSize;


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

    /*
    Getters and Setters
     */

    public long getID() { return mProductID; }

    public float getmPrice() { return mPrice; }

    public void setmPrice(float mPrice) {
        this.mPrice = mPrice;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDiscription() {
        return mDiscription;
    }

    public void setmDiscription(String mDiscription) {
        this.mDiscription = mDiscription;
    }

    public long getmStoreID() {
        return mStoreID;
    }

    public void setmStoreID(long mStoreID) {
        this.mStoreID = mStoreID;
    }

    public Color getmColor() {
        return mColor;
    }

    public void setmColor(Color mColor) {
        this.mColor = mColor;
    }

    public boolean ismDiscounted() {
        return mDiscounted;
    }

    public void setmDiscounted(boolean mDiscounted) {
        this.mDiscounted = mDiscounted;
    }

    public Gender getmGender() {
        return mGender;
    }

    public void setmGender(Gender mGender) {
        this.mGender = mGender;
    }

    public float getmDiscountAmount() {
        return mDiscountAmount;
    }

    public void setmDiscountAmount(float mDiscountAmount) {
        this.mDiscountAmount = mDiscountAmount;
    }

    public int getmImage() {
        return mImage;
    }

    public void setmImage(int mImage) {
        this.mImage = mImage;
    }

    public Category getmCategory() {
        return mCategory;
    }

    public void setmCategory(Category mCategory) {
        this.mCategory = mCategory;
    }

    public Size getmSize() {
        return mSize;
    }

    public void setmSize(Size mSize) {
        this.mSize = mSize;
    }
}
