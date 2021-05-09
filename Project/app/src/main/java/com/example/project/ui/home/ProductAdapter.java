package com.example.project.ui.home;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;


import com.example.project.MainActivity;
import com.example.project.Product;
import com.example.project.R;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    private ArrayList<Product> mProductList;
    private Fragment mContext;

    public ProductAdapter(ArrayList<Product> list, Fragment context){
        mProductList = list;
        mContext = context;
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageButton addToWishlist;
        ImageView prodImage;
        CardView cardView;
        TextView itemName;
        TextView storeName;
        TextView oldPrice;
        TextView price;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            itemName = itemView.findViewById(R.id.product_name);
            storeName = itemView.findViewById(R.id.product_store);
            price = itemView.findViewById(R.id.price);
            oldPrice = itemView.findViewById(R.id.old_price);
            prodImage = itemView.findViewById(R.id.product_img);
            cardView = itemView.findViewById(R.id.product_container);
            addToWishlist = itemView.findViewById(R.id.add_to_list_button);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = null;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int pos){
        Product prod = mProductList.get(pos);
        holder.prodImage.setImageDrawable(prod.getImage());
        holder.itemName.setText(prod.getName());
        holder.storeName.setText(prod.getStore());
        if(prod.getDiscountPrice()!=0.0f){
            holder.price.setText("€ " + new DecimalFormat("###.##").format(prod.getDiscountPrice()));
            holder.price.setTextColor(mContext.getResources().getColor(R.color.red));
            holder.oldPrice.setText("€ " + new DecimalFormat("###.##").format(prod.getPrice()));
            holder.oldPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
        else{
            holder.price.setText("€ " + new DecimalFormat("###.##").format(prod.getPrice()));
            holder.oldPrice.setText("");
        }


        holder.addToWishlist.setOnClickListener(v -> {
            ((MainActivity)mContext.getActivity()).addToWishlist(mProductList.get(pos));
            Toast.makeText(mContext.getActivity(), "Added to wishlist!", Toast.LENGTH_SHORT).show();
        });

        holder.cardView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("itemName", prod.getName());
            (NavHostFragment.findNavController(mContext)).navigate(R.id.productFragment);
        });
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }


}
