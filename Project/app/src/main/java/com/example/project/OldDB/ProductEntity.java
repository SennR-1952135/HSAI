package com.example.project.OldDB;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.project.Enums.Category;

@Entity(tableName = "Products")
public class ProductEntity {


    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int ID;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "price")
    private float price;

    @ColumnInfo(name = "shop")
    private String shop;

    @ColumnInfo(name = "description")
    private String description;


    @ColumnInfo(name = "discount")
    private float discount;

    @ColumnInfo(name = "category")
    private String category;

    //GETTERS AND SETTERS

    @NonNull
    public int getID() {
        return ID;
    }

    public void setID(@NonNull int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getDiscount() {
        return discount;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getShop() {
        return shop;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public String getCategory(){return this.category;}
    public void setCategory(String newCategory){this.category = newCategory;}

    public Category getCategoryInEnum(){
        if (category != null)
            return Category.valueOf(this.category);
        else {
            return null;}
    }

    public void setCategory(Category cat){this.category = cat.toString();}

    public Category getEnumCategory(){
        if (category != null)
            return Category.valueOf(this.category);
        else {
            Log.v("prodentity", "category=null");
            return null;
        }}
}
