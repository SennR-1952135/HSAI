package com.example.project.DataBase;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.project.Enums.Category;
import com.example.project.Enums.Color;
import com.example.project.Enums.Gender;
import com.example.project.Enums.Size;

@Entity(tableName = "Products")
public class ProductEntity{

    static private long productCount = 0;
    @PrimaryKey(autoGenerate = false)
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

    @ColumnInfo(name = "StoreID")
    private long StoreID;

    @ColumnInfo(name = "Color")
    private Color Color;

    @ColumnInfo(name = "Discounted")
    private boolean Discounted;

    @ColumnInfo(name = "Gender")
    private Gender Gender;

    @ColumnInfo(name = "DiscountAmount")
    private float DiscountAmount;

    @ColumnInfo(name = "Image")
    private int Image;


    @ColumnInfo(name = "Size")
    private Size Size;

    @ColumnInfo(name = "Category")
    public Category Category;


    public ProductEntity(){}
    public ProductEntity(String name, String discription , float price, float discountAmount, int image, Category category, int storeID, String storeName, Color color, Gender gender, Size size){
        this.name = name;
        this.id = (int) productCount++;
        this.StoreID = storeID;
        this.price = price;
        this.description = discription;
        this.Color = color;
        this.Discounted = (discountAmount == 0);
        this.DiscountAmount = discountAmount;
        this.discount = (this.Discounted ? 0.0f : this.getPrice() * (1- (this.getDiscountAmount() / 100)));
        this.shop = storeName;
        this.Gender = gender;
        this.Image = image;
        this.Category = category;
        this.Size = size;
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

    public Category getCategory(){return this.Category;}
    public void setCategory(Category newCategory){this.Category = newCategory;}


    public long getStoreID() {
        return StoreID;
    }

    public void setStoreID(long storeID) {
        StoreID = storeID;
    }

    public com.example.project.Enums.Color getColor() {
        return Color;
    }

    public void setColor(com.example.project.Enums.Color color) {
        Color = color;
    }

    public boolean isDiscounted() {
        return Discounted;
    }

    public void setDiscounted(boolean discounted) {
        Discounted = discounted;
    }

    public com.example.project.Enums.Gender getGender() {
        return Gender;
    }

    public void setGender(com.example.project.Enums.Gender gender) {
        Gender = gender;
    }

    public float getDiscountAmount() {
        return DiscountAmount;
    }

    public void setDiscountAmount(float discountAmount) {
        DiscountAmount = discountAmount;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public com.example.project.Enums.Size getSize() {
        return Size;
    }

    public void setSize(com.example.project.Enums.Size size) {
        Size = size;
    }

}
