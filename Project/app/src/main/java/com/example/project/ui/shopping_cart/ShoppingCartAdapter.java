package com.example.project.ui.shopping_cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.example.project.Product;

public class ShoppingCartAdapter extends ArrayAdapter<ShoppingCartItem> {

    private ShoppingCart mCart;
    private int resLayout;
    private Context mContext;

    public ShoppingCartAdapter(@NonNull Context context, int res, ShoppingCart cart){
        super(context, res, cart.getItems());
        mContext = context;
        resLayout = res;
        mCart = cart;
    }

    public View getView(final int index, View convertView, ViewGroup parent){
        View view = convertView;
        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(resLayout, null);
        }
        ShoppingCartItem cartItem = getItem(index);
        Product item = cartItem.getItem();
        if(item!= null){
            //nothing
        }
        return view;
    }

}