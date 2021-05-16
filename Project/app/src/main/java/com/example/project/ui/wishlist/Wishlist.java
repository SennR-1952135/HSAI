package com.example.project.ui.wishlist;


import java.util.ArrayList;
import java.util.Observable;

public class Wishlist extends Observable {

    private ArrayList<WishListItem> mItems;

    public Wishlist(){
        mItems = new ArrayList<WishListItem>();
    }

    // Getters:
    public ArrayList<WishListItem> getItems() { return mItems; }
    public WishListItem getItemAtIndex(int index){ return mItems.get(index); }

    // Functions:
    public void removeItem(int index){
        mItems.remove(index);
        setChanged();
        notifyObservers();
    }
}