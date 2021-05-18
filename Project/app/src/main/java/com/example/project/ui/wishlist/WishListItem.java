package com.example.project.ui.wishlist;


import com.example.project.Product;

public class WishListItem {
    private Product mItem;

    public WishListItem(Product item){
        mItem = item;
    }
    // Getters:
    public Product getItem(){return mItem;}

}
