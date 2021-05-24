package com.example.project.ui.wishlist;


import android.graphics.Paint;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.DataBase.DataBasee;
import com.example.project.DataBase.ProductInCart;
import com.example.project.Product;
import com.example.project.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;


public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.MyViewHolder> {
    private WishlistFragment fragment;
    private Wishlist mWishlist;
    private Product mProd;

    public WishListAdapter(WishlistFragment fragment, Wishlist list){
        mWishlist = list;
        this.fragment = fragment;
        mWishlist.addObserver(fragment);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        TextView oldprice;
        TextView price;
        ImageView delete;
        ImageView addtocart;
        CardView itemView;

        public MyViewHolder(@NonNull View view) {
            super(view);
            imageView = view.findViewById(R.id.whishlist_img);
            name = view.findViewById(R.id.whishlist_itemname);
            oldprice = view.findViewById(R.id.whishlist_old_price);
            price = view.findViewById(R.id.whishlist_itemprice);
            delete = view.findViewById(R.id.whishlist_delete);
            addtocart = view.findViewById(R.id.whishlist_addtocart);
            itemView = view.findViewById(R.id.wishlist_item);
        }
    }
    @NonNull
    @Override
    public WishListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = null;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_item,parent,false);
        return new WishListAdapter.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull WishListAdapter.MyViewHolder holder, int pos){

        WishListItem wishListItem = mWishlist.getItemAtIndex(pos);
        mProd = wishListItem.getItem();
        if(mProd!=null){

            holder.name.setText(mProd.getName()+" - "+mProd.getStore());
            holder.imageView.setImageDrawable(mProd.getImage());
            if (mProd.getDiscountPrice() != 0.0f) {
                holder.price.setText("€ " + String.format("%.2f", mProd.getDiscountPrice()));
                holder.price.setTextColor(fragment.getResources().getColor(R.color.red));
                holder.oldprice.setText("€ " + String.format("%.2f", mProd.getPrice()));
                holder.oldprice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                holder.price.setText("€ " + String.format("%.2f", mProd.getPrice()));
                holder.oldprice.setText("");
            }


            if (holder.itemView != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("itemId", Integer.toString(mProd.getProductId()));
                        (NavHostFragment.findNavController(fragment)).navigate(R.id.productFragment, bundle);

                    }
                });
            }
            if (holder.addtocart != null) {
                holder.addtocart.setOnClickListener(v -> {
                    showBottomSheetDialog();
                });
            }

            if (holder.delete != null) {
                holder.delete.setOnClickListener(v -> {
                    Toast.makeText(fragment.getContext(), "Item removed from wishlist!", Toast.LENGTH_SHORT).show();
                    mWishlist.removeItem(pos);
                    notifyDataSetChanged();
                });
            }
        }
    }

    private void showBottomSheetDialog() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(fragment.getActivity());
        bottomSheetDialog.setContentView(R.layout.fragment_choose_size);
        bottomSheetDialog.show();
        ConstraintLayout s = bottomSheetDialog.findViewById(R.id.constraintLayoutS);
        ConstraintLayout xl = bottomSheetDialog.findViewById(R.id.conXL);
        DataBasee db = DataBasee.getDb(fragment.getActivity());
        if(s!= null && xl!=null){
            s.setOnClickListener(v -> {
                ProductInCart q = db.mAppDao().getItemOnCartWithGivenSize(mProd.getProductId(), "S");
                if(q==null){
                    db.mAppDao().createProductInCart(new ProductInCart(mProd.getProductId(), mProd.getName(), mProd.getStore(),mProd.getPrice(),mProd.getDiscountPrice(),"S"));
                }
                else{
                    db.mAppDao().updateProductQuantity(q.getCount()+1, mProd.getProductId(),"S");
                }
                Toast.makeText(fragment.getActivity(), "Item added to cart", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            });
            xl.setOnClickListener(v -> {
                ProductInCart q = db.mAppDao().getItemOnCartWithGivenSize(mProd.getProductId(), "XL");
                if(q==null){
                    db.mAppDao().createProductInCart(new ProductInCart(mProd.getProductId(), mProd.getName(), mProd.getStore(),mProd.getPrice(),mProd.getDiscountPrice(),"XL"));
                }
                else{
                    db.mAppDao().updateProductQuantity(q.getCount()+1, mProd.getProductId(),"XL");
                }
                Toast.makeText(fragment.getActivity(), "Item added to cart", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            });
        }

    }

    @Override
    public int getItemCount() {
        return mWishlist.getItems().size();
    }
}
