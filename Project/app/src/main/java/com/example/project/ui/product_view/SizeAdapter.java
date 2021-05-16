package com.example.project.ui.product_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.example.project.Product;

public class SizeAdapter extends ArrayAdapter<ClothingSize> {
    private int resLayout;
    private Context mContext;
    private Product mProd;

    public SizeAdapter(@NonNull Context context, int res, Product prod){
        super(context, res, prod.getAvailableSizes());
        resLayout =res;
        mContext = context;
        mProd = prod;
    }

    public View getView(int pos, View convertView, ViewGroup viewGroup){
        View view = convertView;
        if(view==null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(resLayout, null);
        }
        return view;
    }

}
