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

import com.example.project.DataBase.AppDatabase;
import com.example.project.DataBase.Dao;
import com.example.project.OldDB.DataBasee;
import com.example.project.OldDB.ProductEntity;
import com.example.project.DataBase.Product;
import com.example.project.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private ArrayList<Long> mPopularList;
    private ProductAdapter mPopularAdapter;
    private LinearLayoutManager mPopularLayoutManager;


    public HomeFragment(){ }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPopularList = new ArrayList<Long>();
        mPopularAdapter = new ProductAdapter(mPopularList, this, AppDatabase.getDb(getActivity()).dao());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupPopulars();
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

    public void getPopularItems(){
        Dao dao = AppDatabase.getDb(getActivity()).dao();
        mPopularList = new ArrayList<Long>(dao.getAllProductIDs());

//        Drawable img = getResources().getDrawable(R.drawable.shirt);
//        for(ProductEntity dbItem : products_from_db){
//            Product newProd = new Product(dbItem.getName(), dbItem.getShop(), dbItem.getDescription(),dbItem.getPrice(), dbItem.getDiscount(), img , dbItem.getCategoryInEnum());
//            mPopularList.add(newProd);
//        }



    }

}