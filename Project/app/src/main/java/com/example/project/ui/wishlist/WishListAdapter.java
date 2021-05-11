package com.example.project.ui.wishlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.example.project.DataBase.Product;

public class WishListAdapter extends ArrayAdapter<WishListItem> {
    private  int resLayout;
    private Context mContext;
    private Wishlist mWishlist;

    public WishListAdapter(@NonNull Context context, int resLayout, Wishlist list){
        super(context, resLayout, list.getItems());
        this.resLayout = resLayout;
        mContext = context;
        mWishlist = list;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;
        if(view==null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(resLayout, null);
        }
        WishListItem item = getItem(position);
        Product prod = item.getItem();
        if(item!=null){
            //ImageView imageView = (ImageView) view.findViewById()
        }
        return view;
    }
}
