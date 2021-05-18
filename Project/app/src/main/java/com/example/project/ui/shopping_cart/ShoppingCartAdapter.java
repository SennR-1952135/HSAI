package com.example.project.ui.shopping_cart;

import android.app.AlertDialog;

import android.content.DialogInterface;
import android.graphics.Paint;

import android.os.Bundle;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.DataBase.DataBasee;
import com.example.project.DataBase.ProductInWishlist;

import com.example.project.Product;
import com.example.project.R;


public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.MyViewHolder> {

    private ShoppingCart mCart;
    private ShoppingCartFragment fragment;

    public ShoppingCartAdapter(ShoppingCartFragment fragment,ShoppingCart cart){
        mCart = cart;
        this.fragment = fragment;
        mCart.addObserver(fragment);
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name;
        TextView size;
        TextView oldprice;
        TextView price;
        ImageView plus;
        ImageView minus;
        ImageView delete;
        ImageView addtoWishlist;
        ImageView removeFromWishlist;
        EditText count;
        CardView itemView;

        public MyViewHolder(@NonNull View view){
            super(view);
            imageView = view.findViewById(R.id.shoppingcart_img);
            name = view.findViewById(R.id.shoppingcart_itemname);
            size = view.findViewById(R.id.shoppingcart_itemsize);
            oldprice = view.findViewById(R.id.shoppingcart_old_price);
            price = view.findViewById(R.id.shoppingcart_itemprice);
            plus = view.findViewById(R.id.shoppingcart_addCount);
            minus = view.findViewById(R.id.shoppingcart_removeCount);
            delete = view.findViewById(R.id.cart_delete);
            addtoWishlist = view.findViewById(R.id.cart_addToWishlist);
            removeFromWishlist = view.findViewById(R.id.cart_removeFromWishlist);
            count = view.findViewById(R.id.shoppingcart_itemcount);
            itemView = view.findViewById(R.id.shoppingcart_item);
        }
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = null;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shoppingcart_item,parent,false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int pos){

        ShoppingCartItem cartItem = mCart.getItems().get(pos);
        Product mProd = cartItem.getItem();
        if(mProd!=null){

            holder.name.setText(mProd.getName()+" - "+mProd.getStore());
            holder.size.setText(cartItem.getSize());
            holder.count.setText(String.valueOf(cartItem.getQuantity()));
            holder.imageView.setImageDrawable(mProd.getImage());
            holder.count.setText(String.valueOf(cartItem.getQuantity()));
            if (mProd.getDiscountPrice() != 0.0f) {
                holder.price.setText("€ " + String.format("%.2f", mProd.getDiscountPrice()));
                holder.price.setTextColor(fragment.getResources().getColor(R.color.red));
                holder.oldprice.setText("€ " + String.format("%.2f", mProd.getPrice()));
                holder.oldprice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                holder.price.setText("€ " + String.format("%.2f", mProd.getPrice()));
                holder.oldprice.setText("");
            }


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("itemId", Integer.toString(mProd.getProductId()));
                    (NavHostFragment.findNavController(fragment)).navigate(R.id.productFragment, bundle);

                }
            });
            DataBasee db = DataBasee.getDb(fragment.getActivity());

            if(db.mAppDao().getItemOnWishlist(mProd.getProductId()) !=null){
                holder.addtoWishlist.setVisibility(View.INVISIBLE);
                holder.removeFromWishlist.setVisibility(View.VISIBLE);
            }

            holder.plus.setOnClickListener(v -> {
                mCart.addItemQuantity(pos);
                notifyDataSetChanged();
            });

            holder.addtoWishlist.setOnClickListener(v -> {
                holder.removeFromWishlist.setVisibility(View.VISIBLE);
                holder.addtoWishlist.setVisibility(View.INVISIBLE);
                Toast.makeText(fragment.getActivity(), "Item added to wishlist!", Toast.LENGTH_SHORT).show();
                db.mAppDao().createProductInWishlist(new ProductInWishlist(mProd.getProductId(), mProd.getName(), mProd.getStore(),mProd.getPrice(),mProd.getDiscountPrice()));
            });
            holder.removeFromWishlist.setOnClickListener(v-> {
                holder.addtoWishlist.setVisibility(View.VISIBLE);
                holder.removeFromWishlist.setVisibility(View.INVISIBLE);
                Toast.makeText(fragment.getActivity(), "Item removed from wishlist!", Toast.LENGTH_SHORT).show();
                db.mAppDao().removeProductInWishlist(mProd.getProductId());
            });

            holder.delete.setOnClickListener(v -> {
                mCart.removeItem(pos);
                notifyDataSetChanged();
            });

            holder.count.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        mCart.getItemAtIndex(pos).setQuantity(Integer.parseInt(holder.count.getText().toString()));
                        notifyDataSetChanged();
                    }

                    return false;
                }
            });

            holder.minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCart.getItemAtIndex(pos).getQuantity() <= 1) {
                        new AlertDialog.Builder(fragment.getContext())
                                .setTitle("Delete product")
                                .setMessage("Are you sure you want to delete this?")

                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        mCart.removeItemQuantity(pos);
                                    }
                                })
                                .setNegativeButton(android.R.string.no, null)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();


                    } else {
                        mCart.removeItemQuantity(pos);
                    }

                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mCart.getItems().size();
    }
}
