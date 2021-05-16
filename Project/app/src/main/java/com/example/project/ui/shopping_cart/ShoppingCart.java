package com.example.project.ui.shopping_cart;

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
    public void removeItem(int index){
        mItems.remove(index);
        setChanged();
        notifyObservers();
    }

    public float getTotal(){
        float total = 0;
        for(ShoppingCartItem i:mItems){
            if(i.getItem().getDiscountPrice()==0.0f){
                total+= i.getItem().getPrice() * i.getQuantity();
            }
            else{
                total+= i.getItem().getDiscountPrice() * i.getQuantity();
            }

        }
        return total;
    }
}
