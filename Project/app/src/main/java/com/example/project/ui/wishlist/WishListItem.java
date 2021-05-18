package com.example.project.ui.wishlist;


import com.example.project.ProductClass;

public class WishListItem {
    private ProductClass mItem;

    public WishListItem(ProductClass item){
        mItem = item;
    }
    // Getters:
    public ProductClass getItem(){return mItem;}

}
