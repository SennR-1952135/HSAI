package com.example.project.ui.search;

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
import com.example.project.ui.home.ProductAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchResultFragment extends Fragment {

    private ArrayList<Product> mFilteredList;
    private ProductAdapter mFilteredAdapter;
    private LinearLayoutManager mFilteredLayoutManager;
    private List<ProductEntity> mFilteredListEntities;



    public SearchResultFragment(){ }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_result, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFilteredList = new ArrayList<Product>();
        mFilteredAdapter = new ProductAdapter(mFilteredList, this);
        mFilteredListEntities = (ArrayList<ProductEntity>)getArguments().getSerializable("filteredlist");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupFiltered();
    }

    private void setupFiltered(){
        RecyclerView popularRecyclerView = getView().findViewById(R.id.SearchResultRV);
        mFilteredLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mFilteredLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        popularRecyclerView.setLayoutManager(mFilteredLayoutManager);
        popularRecyclerView.setItemAnimator(new DefaultItemAnimator());
        popularRecyclerView.setAdapter(mFilteredAdapter);
        getFilteredItems();
    }


    public void getFilteredItems(){
        for(ProductEntity dbItem : mFilteredListEntities){
            Drawable img = getResources().getDrawable(dbItem.getImage());
            Product newProd = new Product(dbItem.getId(),dbItem.getName(), dbItem.getShop(), dbItem.getDescription(), dbItem.getPrice(), dbItem.getDiscount(), img, dbItem.getCategory());
            mFilteredList.add(newProd);
        }


    }
}