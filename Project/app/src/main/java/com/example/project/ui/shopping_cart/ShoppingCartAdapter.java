package com.example.project.ui.shopping_cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.example.project.DataBase.Product;

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
        long itemID = cartItem.getItemID();
//        if(itemID != null ){ //voor een reden kunt ge een long nie vergelijken met null, geen idee wat je hier wilt doen ma ask me als het niet lukt. KOBE
//            //nothing
//        }
        return view;
    }

}
