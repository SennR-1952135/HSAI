package com.example.project.ui.wishlist;

import com.example.project.DataBase.AppDatabase;
import com.example.project.DataBase.Dao;
import com.example.project.DataBase.Product;
import com.example.project.MainActivity;

import java.util.ArrayList;
import java.util.Observable;

public class Wishlist extends Observable {

    private ArrayList<WishListItem> mItems;
    private Dao dao;

    public Wishlist(Dao dao){
        mItems = new ArrayList<WishListItem>();
        this.dao = dao;
    }

    // Getters:
    public ArrayList<WishListItem> getItems() { return mItems; }
    public WishListItem getItemAtIndex(int index){ return mItems.get(index); }
    public int getCount(){ return mItems.size(); }
    public float getTotal(){
        float total = 0;
        for(WishListItem i: mItems){
            total+= dao.getPPriceByID(i.getItemID()) * i.getQuantity();
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
    public void addItem(long item){
        // First check if item is already in wishlist.
        for(WishListItem i: mItems){
            if(dao.getPNameByID(i.getItemID()).equals(dao.getPNameByID(item))){
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