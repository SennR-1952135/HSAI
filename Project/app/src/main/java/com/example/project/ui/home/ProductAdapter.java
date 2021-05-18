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
import com.example.project.DataBase.DataBasee;
import com.example.project.DataBase.ProductInWishlist;
import com.example.project.Product;
import com.example.project.R;
import java.util.ArrayList;


public class ProductAdapter extends RecyclerView.Adapter<com.example.project.ui.home.ProductAdapter.MyViewHolder> {
    private ArrayList<Product> mProductList;
    private Fragment mContext;
    boolean addedToWishlist = false;

    public ProductAdapter(ArrayList<Product> list, Fragment context){
        mProductList = list;
        mContext = context;
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageButton addToWishlist;
        ImageButton removeFromWishlist;
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
            removeFromWishlist = itemView.findViewById(R.id.remove_btn);
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

        Product mProd = mProductList.get(pos);
        holder.prodImage.setImageDrawable(mProd.getImage());
        holder.itemName.setText(mProd.getName());
        holder.storeName.setText(mProd.getStore());
        if(mProd.getDiscountPrice()!=0.0f){
            holder.price.setText("€ " + String.format("%.2f", mProd.getDiscountPrice()));
            holder.price.setTextColor(mContext.getResources().getColor(R.color.red));
            holder.oldPrice.setText("€ " + String.format("%.2f", mProd.getPrice()));
            holder.oldPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
        else{
            holder.price.setText("€ " + String.format("%.2f", mProd.getPrice()));
            holder.oldPrice.setText("");
        }
        DataBasee db = DataBasee.getDb(mContext.getActivity());

        if(db.mAppDao().getItemOnWishlist(mProd.getProductId()) !=null){
            holder.addToWishlist.setVisibility(View.INVISIBLE);
            holder.removeFromWishlist.setVisibility(View.VISIBLE);
        }

        holder.addToWishlist.setOnClickListener(v -> {
            holder.removeFromWishlist.setVisibility(View.VISIBLE);
            holder.addToWishlist.setVisibility(View.INVISIBLE);
            Toast.makeText(mContext.getActivity(), "Item added to wishlist!", Toast.LENGTH_SHORT).show();
            db.mAppDao().createProductInWishlist(new ProductInWishlist(mProd.getProductId(), mProd.getName(), mProd.getStore(),mProd.getPrice(),mProd.getDiscountPrice()));
        });
        holder.removeFromWishlist.setOnClickListener(v-> {
            holder.addToWishlist.setVisibility(View.VISIBLE);
            holder.removeFromWishlist.setVisibility(View.INVISIBLE);
            Toast.makeText(mContext.getActivity(), "Item removed from wishlist!", Toast.LENGTH_SHORT).show();
            db.mAppDao().removeProductInWishlist(mProd.getProductId());
        });

        holder.cardView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("itemId", Integer.toString(mProd.getProductId()));
            (NavHostFragment.findNavController(mContext)).navigate(R.id.productFragment, bundle);
        });
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }


}
