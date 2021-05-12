package com.example.project.ui.shopping_cart;

import com.example.project.DataBase.Product;

public class ShoppingCartItem {
    private long mItemID;
    private int mQuantity;
    private boolean mChecked;

    public ShoppingCartItem(long itemID){
        mItemID = itemID;
        mChecked = false;
        mQuantity = 1;
    }
    // Getters:
    public int getQuantity(){return mQuantity;}
    public boolean isChecked(){return mChecked;}
    public long getItemID(){return mItemID;}

    // Setters:
    public void setQuantity(int quantity){mQuantity = quantity;}
    public void setChecked(boolean checked){mChecked = checked;}

    // Functions:
    public void addOneQuantity(){
        mQuantity++;
    }
    public void removeOneQuantity(){
        if(mQuantity>0){
            mQuantity--;
        }
    }
}