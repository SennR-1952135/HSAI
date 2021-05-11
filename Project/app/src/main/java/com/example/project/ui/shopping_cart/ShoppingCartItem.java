package com.example.project.ui.shopping_cart;

import com.example.project.DataBase.Product;

public class ShoppingCartItem {
    private Product mItem;
    private int mQuantity;
    private boolean mChecked;

    public ShoppingCartItem(Product item){
        mItem = item;
        mChecked = false;
        mQuantity = 1;
    }
    // Getters:
    public int getQuantity(){return mQuantity;}
    public boolean isChecked(){return mChecked;}
    public Product getItem(){return mItem;}

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