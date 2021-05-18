package com.example.project.ui.shopping_cart;

import com.example.project.Product;

public class ShoppingCartItem {
    private Product mItem;
    private int mQuantity;
    private String mSize;

    public ShoppingCartItem(Product item){
        mItem = item;
        mQuantity = 1;
    }
    // Getters:
    public int getQuantity(){return mQuantity;}
    public Product getItem(){return mItem;}

    // Setters:
    public void setQuantity(int quantity){mQuantity = quantity;}

    // Functions:
    public void addOneQuantity(){
        mQuantity++;
    }
    public void removeOneQuantity(){
        if(mQuantity>0){
            mQuantity--;
        }
    }

    public void setSize(String size) {
        mSize = size;
    }
    public String getSize(){return mSize;}
}