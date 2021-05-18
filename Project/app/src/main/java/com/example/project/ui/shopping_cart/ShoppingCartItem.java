package com.example.project.ui.shopping_cart;

import com.example.project.ProductClass;

public class ShoppingCartItem {
    private ProductClass mItem;
    private int mQuantity;
    private String mSize;

    public ShoppingCartItem(ProductClass item){
        mItem = item;
        mQuantity = 1;
    }
    // Getters:
    public int getQuantity(){return mQuantity;}
    public ProductClass getItem(){return mItem;}

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