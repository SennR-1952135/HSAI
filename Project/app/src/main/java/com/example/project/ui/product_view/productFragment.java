package com.example.project.ui.product_view;

import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.project.DataBase.DataBasee;
import com.example.project.DataBase.ProductEntity;
import com.example.project.DataBase.ProductInCart;
import com.example.project.DataBase.ProductInWishlist;
import com.example.project.Product;
import com.example.project.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

/**
 * @author Melih Demirel
 */

public class productFragment extends Fragment {
    private Product mProd;
    private final productFragment m_self_ref = this;
    public productFragment(){}
    private DataBasee db;
    private ProductEntity dbP;
    static productFragment newInstance(int itemID){
        productFragment productFragment = new productFragment();
        Bundle args = new Bundle();
        args.putInt("itemId", itemID);
        productFragment.setArguments(args);
        return productFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String prodId = getArguments().getString("itemId");
        if(prodId!=null){
            Drawable img = getResources().getDrawable(R.drawable.shirt);
            db = DataBasee.getDb(getActivity());
            int prodIdd = Integer.parseInt(prodId);
            dbP = db.mAppDao().getProduct(prodIdd);
            mProd = new Product(dbP.getId(),dbP.getName(), dbP.getShop(), dbP.getDescription(), dbP.getPrice(), dbP.getDiscount(), img , dbP.getCategory());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){

        return inflater.inflate(R.layout.fragment_item, viewGroup, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        setupFragment(view);
    }
    public void setupFragment(@NonNull View view){

        ImageView img = view.findViewById(R.id.product_img);
        TextView name = view.findViewById(R.id.product_name);
        TextView store = view.findViewById(R.id.product_store);
        ImageButton addToWishlist = view.findViewById(R.id.addToWishlistBtn);
        ImageButton addToCart = view.findViewById(R.id.addToCartBtn);
        ImageButton removeFromWishlist = view.findViewById(R.id.remove_from_whislist);
        ImageButton share = view.findViewById(R.id.shareBtn);

        TextView description = view.findViewById(R.id.description);
        TextView reviewsTitle = view.findViewById(R.id.reviewTitle);
        TextView reviewsPunten = view.findViewById(R.id.punten);

        TextView price = view.findViewById(R.id.price);
        TextView oldPrice = view.findViewById(R.id.old_price);
        TextView color = view.findViewById(R.id.product_color);
        color.setText(dbP.getColor().toString());
        name.setText(mProd.getName());
        img.setImageDrawable(mProd.getImage());
        store.setText(mProd.getStore());
        description.setText(mProd.getDiscription());

        if(mProd.getDiscountPrice()!=0.0f){
            price.setText("€ " + String.format("%.2f", mProd.getDiscountPrice()));
            price.setTextColor(getResources().getColor(R.color.red));
            oldPrice.setText("€ " + String.format("%.2f", mProd.getPrice()));
            oldPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
        else{
            price.setText("€ " + String.format("%.2f", mProd.getPrice()));
            oldPrice.setText("");
        }

        if(db.mAppDao().getItemOnWishlist(mProd.getProductId()) !=null){
            addToWishlist.setVisibility(View.INVISIBLE);
            removeFromWishlist.setVisibility(View.VISIBLE);
        }

        addToCart.setOnClickListener(v -> {

            showBottomSheetDialog();

        });

        addToWishlist.setOnClickListener(v -> {
            removeFromWishlist.setVisibility(View.VISIBLE);
            addToWishlist.setVisibility(View.INVISIBLE);
            Toast.makeText(getActivity(), "Item added to wishlist!", Toast.LENGTH_SHORT).show();
            db.mAppDao().createProductInWishlist(new ProductInWishlist(mProd.getProductId(), mProd.getName(), mProd.getStore(),mProd.getPrice(),mProd.getDiscountPrice()));
        });
        removeFromWishlist.setOnClickListener(v-> {
            addToWishlist.setVisibility(View.VISIBLE);
            removeFromWishlist.setVisibility(View.INVISIBLE);
            Toast.makeText(getActivity(), "Item removed from wishlist!", Toast.LENGTH_SHORT).show();
            db.mAppDao().removeProductInWishlist(mProd.getProductId());
        });

        share.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "Link is copied on your clipboard!", Toast.LENGTH_SHORT).show();
        });


    }

    private void showBottomSheetDialog() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this.getActivity());
        bottomSheetDialog.setContentView(R.layout.fragment_choose_size);
        bottomSheetDialog.show();
        ConstraintLayout s = bottomSheetDialog.findViewById(R.id.constraintLayoutS);
        ConstraintLayout xl = bottomSheetDialog.findViewById(R.id.conXL);
        if(s!= null && xl!=null){
            s.setOnClickListener(v -> {
                ProductInCart q = db.mAppDao().getItemOnCartWithGivenSize(mProd.getProductId(), "S");
                if(q==null){
                    db.mAppDao().createProductInCart(new ProductInCart(mProd.getProductId(), mProd.getName(), mProd.getStore(),mProd.getPrice(),mProd.getDiscountPrice(),"S"));
                }
                else{
                    db.mAppDao().updateProductQuantity(q.getCount()+1, mProd.getProductId(),"S");
                }
                Toast.makeText(getActivity(), "Item added to cart", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getActivity(), "Item added to cart", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            });
        }

    }
}
