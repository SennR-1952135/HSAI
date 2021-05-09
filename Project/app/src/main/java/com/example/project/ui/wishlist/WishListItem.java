package com.example.project.ui.wishlist;


import com.example.project.Product;

public class WishListItem {
    private Product mItem;
    private int mQuantity;
    private boolean mChecked;

    public WishListItem(Product item){
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
