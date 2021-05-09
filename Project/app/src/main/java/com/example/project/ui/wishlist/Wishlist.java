package com.example.project.ui.wishlist;

import com.example.project.Product;

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
    public int getCount(){ return mItems.size(); }
    public float getTotal(){
        float total = 0;
        for(WishListItem i: mItems){
            total+= i.getItem().getPrice() * i.getQuantity();
        }
        return total;
    }

    // Functions:
    public void addItemQuantity(int index){
        mItems.get(index).addOneQuantity();
        setChanged();
        notifyObservers();
    }
    public void removeItemQuantity(int index){
        WishListItem item = mItems.get(index);
        if (item.getQuantity() > 1){
            item.removeOneQuantity();
        }
        else{
            mItems.remove(index);
        }
        setChanged();
        notifyObservers();
    }
    public void addItem(Product item){
        // First check if item is already in wishlist.
        for(WishListItem i: mItems){
            if(i.getItem().getName().equals(item.getName())){
                i.addOneQuantity();
                setChanged();
                notifyObservers();
                return;
            }
        }
        WishListItem newItem = new WishListItem(item);
        mItems.add(newItem);
        setChanged();
        notifyObservers();
    }
}