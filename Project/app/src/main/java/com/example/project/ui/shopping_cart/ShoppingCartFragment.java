package com.example.project.ui.shopping_cart;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.DataBase.DataBasee;
import com.example.project.DataBase.ProductInCart;
import com.example.project.Product;
import com.example.project.R;


import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ShoppingCartFragment extends Fragment implements Observer {

    private ShoppingCart mShoppingCart;
    private ShoppingCartAdapter mShoppingCartAdapter;
    private LinearLayoutManager mLayoutManager;

    public ShoppingCartFragment(){ }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shopping_cart, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mShoppingCart = new ShoppingCart();
        mShoppingCartAdapter = new ShoppingCartAdapter(this, mShoppingCart);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getItems();
        setupItems();

    }

    public void setupItems(){
        RecyclerView recyclerView = getView().findViewById(R.id.shoppingcart_list);
        TextView price = getView().findViewById(R.id.shoppingcart_total_price);
        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mShoppingCartAdapter);
        price.setText("€ " + String.format("%.2f", mShoppingCart.getTotal()));


    }

    private void getItems() {
        DataBasee db = DataBasee.getDb(getActivity());
        List<ProductInCart> p = db.mAppDao().getItemsOnCart();
        Drawable img = getResources().getDrawable(R.drawable.shirt);

        for(ProductInCart i:p){
            ShoppingCartItem m = new ShoppingCartItem(new Product(i.getProductid(),i.getProductname(),i.getShopname(),i.getPrice(),i.getOldprice(),img));
            m.setQuantity(i.getCount());
            m.setSize(i.getSize());
            mShoppingCart.getItems().add(m);
        }
    }


    @Override
    public void update(Observable o, Object arg) {
        DataBasee db = DataBasee.getDb(getActivity());
        List<ProductInCart> p = db.mAppDao().getItemsOnCart();

        //Update db with new quantities


        boolean shouldBeRemovedBecauseNotInCartAnymore = true;
        for(ProductInCart i:p){

            for(ShoppingCartItem m: mShoppingCart.getItems()){
                if(i.getProductid()==m.getItem().getProductId() && i.getSize().equals(m.getSize())){
                    shouldBeRemovedBecauseNotInCartAnymore = false;
                    db.mAppDao().updateProductQuantity(m.getQuantity(),i.getProductid(), i.getSize());
                }

            }
            if(shouldBeRemovedBecauseNotInCartAnymore){
                System.out.println("been shouldBeRemovedBecauseNotInCartAnymore");
                if(mShoppingCart.getItems().size()==0){
                    db.mAppDao().removeAllInCart();
                }
                else{
                    db.mAppDao().removeProductInCartWithGivenSize(i.getProductid(), i.getSize());
                }

            }
            shouldBeRemovedBecauseNotInCartAnymore = true;
        }
        TextView price = getView().findViewById(R.id.shoppingcart_total_price);
        price.setText("€ " + String.format("%.2f", mShoppingCart.getTotal()));
        mShoppingCartAdapter.notifyDataSetChanged();
    }
}
