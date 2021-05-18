package com.example.project.ui.home;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.DataBase.DataBasee;
import com.example.project.DataBase.ProductEntity;
import com.example.project.Product;
import com.example.project.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private ArrayList<Product> mPopularList;
    private ProductAdapter mPopularAdapter;
    private LinearLayoutManager mPopularLayoutManager;

    private ArrayList<Product> mPromoList;
    private ProductAdapter mPromoAdapter;
    private LinearLayoutManager mPromoLayoutManager;


    public HomeFragment(){ }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPopularList = new ArrayList<Product>();
        mPopularAdapter = new ProductAdapter(mPopularList, this);

        mPromoList = new ArrayList<Product>();
        mPromoAdapter = new ProductAdapter(mPromoList, this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupPopulars();
        setupPromos();
    }

    private void setupPopulars(){
        RecyclerView popularRecyclerView = getView().findViewById(R.id.popular_list);
        mPopularLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mPopularLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        popularRecyclerView.setLayoutManager(mPopularLayoutManager);
        popularRecyclerView.setItemAnimator(new DefaultItemAnimator());
        popularRecyclerView.setAdapter(mPopularAdapter);
        getPopularItems();
    }
    private void setupPromos(){
        RecyclerView promoRecyclerView = getView().findViewById(R.id.promotion_list);
        mPromoLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mPromoLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        promoRecyclerView.setLayoutManager(mPromoLayoutManager);
        promoRecyclerView.setItemAnimator(new DefaultItemAnimator());
        promoRecyclerView.setAdapter(mPromoAdapter);
        getPromoItems();
    }

    public void getPopularItems(){
        DataBasee db = DataBasee.getDb(getActivity());
        List<ProductEntity> products_from_db = db.mAppDao().getAllProducts();

        Drawable img = getResources().getDrawable(R.drawable.shirt);
        for(ProductEntity dbItem : products_from_db){
            if(dbItem.getDiscount()==0.0f) {
                Product newProd = new Product(dbItem.getId(),dbItem.getName(), dbItem.getShop(), dbItem.getDescription(), dbItem.getPrice(), dbItem.getDiscount(), img, dbItem.getCategory());
                mPopularList.add(newProd);
            }
        }

    }
    public void getPromoItems(){
        DataBasee db = DataBasee.getDb(getActivity());
        List<ProductEntity> products_from_db = db.mAppDao().getAllProducts();

        Drawable img = getResources().getDrawable(R.drawable.shirt);
        for(ProductEntity dbItem : products_from_db){
            if(dbItem.getDiscount()!=0.0f){
                Product newProd = new Product(dbItem.getId(),dbItem.getName(), dbItem.getShop(), dbItem.getDescription(),dbItem.getPrice(), dbItem.getDiscount(), img , dbItem.getCategory());
                mPromoList.add(newProd);
            }
        }
    }

}