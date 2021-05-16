package com.example.project.DataBase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "ProductsInCart")
public class ProductInCart {

    @PrimaryKey(autoGenerate = true)
    private int ID;


    @ColumnInfo(name = "productid")
    private int productid;

    @ColumnInfo(name = "productname")
    private String productname;

    @ColumnInfo(name = "shopname")
    private String shopname;

    @ColumnInfo(name = "price")
    private float price;

    @ColumnInfo(name = "oldprice")
    private float oldprice;

    @ColumnInfo(name = "size")
    private String size;



    @ColumnInfo(name = "count")
    private int count;

    public ProductInCart(){}
    public ProductInCart(int productid, String productName, String store, float price, float oldprice, String size) {
        this.productid = productid; this.productname = productName; shopname = store; this.price = price; this.oldprice = oldprice; this.size = size; this.count = 1;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getOldprice() {
        return oldprice;
    }

    public void setOldprice(float oldprice) {
        this.oldprice = oldprice;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String s) {
        this.size = s;
    }


}
