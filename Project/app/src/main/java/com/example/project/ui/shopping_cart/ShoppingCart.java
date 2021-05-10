package com.example.project.ui.shopping_cart;

import com.example.project.Product;
import com.example.project.ui.wishlist.WishListItem;

import java.util.ArrayList;
import java.util.Observable;

public class ShoppingCart extends Observable {

    private ArrayList<ShoppingCartItem> mItems;

    public ShoppingCart(){
        mItems = new ArrayList<ShoppingCartItem>();
    }

    // Getters:
    public ArrayList<ShoppingCartItem> getItems() { return mItems; }
    public ShoppingCartItem getItemAtIndex(int index){ return mItems.get(index); }
    public int getCount(){ return mItems.size(); }
    public float getTotal(){
        float total = 0;
        for(ShoppingCartItem i: mItems){
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
        ShoppingCartItem item = mItems.get(index);
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
        for(ShoppingCartItem i: mItems){
            if(i.getItem().getName().equals(item.getName())){
                i.addOneQuantity();
                setChanged();
                notifyObservers();
                return;
            }
        }
        ShoppingCartItem newItem = new ShoppingCartItem(item);
        mItems.add(newItem);
        setChanged();
        notifyObservers();
    }
}
