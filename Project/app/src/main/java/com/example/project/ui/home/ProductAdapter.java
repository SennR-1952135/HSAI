package com.example.project.ui.home;

import android.graphics.Paint;
import android.os.Bundle;
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


import com.example.project.DataBase.Dao;
import com.example.project.MainActivity;
import com.example.project.DataBase.Product;
import com.example.project.R;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    private ArrayList<Long> mProductList;
    private Fragment mContext;
    private Dao mdao;

    public ProductAdapter(ArrayList<Long> list, Fragment context, Dao dao){
        this.mProductList = list;
        this.mContext = context;
        this.mdao = dao;
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
        long prodID = mProductList.get(pos);
        holder.prodImage.setImageResource(mdao.getPImageResourceByID(prodID));
        holder.itemName.setText(mdao.getPNameByID(prodID));
        holder.storeName.setText(mdao.getSNameByID(mdao.getStoreIDByProductID(prodID)));
        if(mdao.getPDiscountedByID(prodID)){
            float discountedPrice = mdao.getPPriceByID(prodID) * (1- (mdao.getPDiscountAmountByID(prodID) / 100));
            holder.price.setText("€ " + new DecimalFormat("###.##").format(discountedPrice));
            holder.price.setTextColor(mContext.getResources().getColor(R.color.red));
            holder.oldPrice.setText("€ " + new DecimalFormat("###.##").format(mdao.getPPriceByID(prodID)));
            holder.oldPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
        else{
            holder.price.setText("€ " + new DecimalFormat("###.##").format(mdao.getPPriceByID(prodID)));
            holder.oldPrice.setText("");
        }


        holder.addToWishlist.setOnClickListener(v -> {
            ((MainActivity)mContext.getActivity()).addToWishlist(mProductList.get(pos));
            Toast.makeText(mContext.getActivity(), "Added to wishlist!", Toast.LENGTH_SHORT).show();
        });

        holder.cardView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("itemName", mdao.getPNameByID(prodID));
            (NavHostFragment.findNavController(mContext)).navigate(R.id.productFragment);
        });
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }


}
