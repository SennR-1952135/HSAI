package com.example.project.ui.wishlist;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.DataBase.DataBasee;
import com.example.project.DataBase.ProductInWishlist;
import com.example.project.Product;
import com.example.project.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class WishlistFragment extends Fragment implements Observer {

    private Wishlist mWishlist;
    private WishListAdapter mWishlistAdapter;
    private LinearLayoutManager mLayoutManager;


    boolean notRated = true;
    boolean up = false;
    public WishlistFragment(){

    }

    private String getGlobalStoreName() {
        TextView t = getActivity().findViewById(R.id.in_store_name_global);
        return t.getText().toString();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wishlist, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWishlist = new Wishlist();
        mWishlistAdapter = new WishListAdapter(this, mWishlist);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getItems();
        setupItems();

    }
    public void setupItems(){
        RecyclerView recyclerView = getView().findViewById(R.id.wishlist_list);
        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mWishlistAdapter);


        ImageView up = getView().findViewById(R.id.up);
        ImageView down = getView().findViewById(R.id.down);
        ImageView upempty = getView().findViewById(R.id.up_empty);
        ImageView downempty = getView().findViewById(R.id.down_empty);
        ImageView share = getView().findViewById(R.id.shareIcon);

        TextView emptyWishlist = getView().findViewById(R.id.k);
        if(mWishlist.getItems().size()==0){
            emptyWishlist.setVisibility(View.VISIBLE);
        }
        else emptyWishlist.setVisibility(View.INVISIBLE);

        up.setOnClickListener(v -> {
            upempty.setVisibility(View.VISIBLE);
            down.setVisibility(View.INVISIBLE);
            up.setVisibility(View.INVISIBLE);
            downempty.setVisibility(View.VISIBLE);
        });
        down.setOnClickListener(v -> {
            upempty.setVisibility(View.VISIBLE);
            down.setVisibility(View.INVISIBLE);
            up.setVisibility(View.INVISIBLE);
            downempty.setVisibility(View.VISIBLE);
        });
        upempty.setOnClickListener(v -> {
            upempty.setVisibility(View.INVISIBLE);
            down.setVisibility(View.INVISIBLE);
            up.setVisibility(View.VISIBLE);
            downempty.setVisibility(View.VISIBLE);
        });
        downempty.setOnClickListener(v -> {
            upempty.setVisibility(View.VISIBLE);
            down.setVisibility(View.VISIBLE);
            up.setVisibility(View.INVISIBLE);
            downempty.setVisibility(View.INVISIBLE);
        });
        share.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "Link in clipboard!", Toast.LENGTH_SHORT).show();
        });
    }

    private void getItems() {
        DataBasee db = DataBasee.getDb(getActivity());
        List<ProductInWishlist> p = db.mAppDao().getItemsOnWishlist();
        Drawable img = getResources().getDrawable(R.drawable.shirt);

        System.out.println("STORE"+getGlobalStoreName());
        if(getGlobalStoreName()==""){
            for(ProductInWishlist i:p){
                WishListItem m = new WishListItem(new Product(i.getProductid(),i.getProductname(),i.getShopname(),i.getPrice(),i.getOldprice(),img));
                mWishlist.getItems().add(m);
            }
        }
        else{
            ArrayList<WishListItem> rest = new ArrayList<>();
            for(ProductInWishlist i:p){
                WishListItem m = new WishListItem(new Product(i.getProductid(),i.getProductname(),i.getShopname(),i.getPrice(),i.getOldprice(),img));
                if(i.getShopname().equals(getGlobalStoreName())){
                    mWishlist.getItems().add(m);
                }
                else{
                    rest.add(m);
                }
            }
            for(WishListItem i:rest){
                mWishlist.getItems().add(i);
            }
        }

    }


    @Override
    public void update(Observable o, Object arg) {
        DataBasee db = DataBasee.getDb(getActivity());
        List<ProductInWishlist> p = db.mAppDao().getItemsOnWishlist();

        //Update db with new quantities
        boolean shouldBeRemovedBecauseNotInWishlistAnymore = true;
        for(ProductInWishlist i:p){

            for(WishListItem m: mWishlist.getItems()){
                if(i.getProductid()==m.getItem().getProductId()){
                    shouldBeRemovedBecauseNotInWishlistAnymore = false;
                }

            }
            if(shouldBeRemovedBecauseNotInWishlistAnymore){
                if(mWishlist.getItems().size()==0){
                    db.mAppDao().removeAllInWishlist();
                }
                else{
                    db.mAppDao().removeProductInWishlist(i.getProductid());
                }
            }
            shouldBeRemovedBecauseNotInWishlistAnymore = true;
        }
        mWishlistAdapter.notifyDataSetChanged();
    }


}
