package com.example.project.DataBase;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.project.Category;

@Entity(tableName = "Products")
public class ProductEntity {


    @PrimaryKey(autoGenerate = true)
    private int id;

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

    public ProductEntity(){}
    public ProductEntity(int id, String name, String shop , String description, float price , float discount, Category category ){
        this.id = id; this.name = name; this.shop = shop; this.description = description; this.price = price; this.discount = discount; this.category = category.toString();
    }
    //GETTERS AND SETTERS

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
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
