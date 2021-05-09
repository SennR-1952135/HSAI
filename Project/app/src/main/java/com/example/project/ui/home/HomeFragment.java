package com.example.project.ui.home;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.Product;
import com.example.project.Promotion;
import com.example.project.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private ArrayList<Promotion> mPromList;
    private PromotionAdapter mPromotionAdapter;
    private Menu mMenu;
    private ArrayList<Product> mDiscountedItems;
    private LinearLayoutManager mPromotionLayoutManager;

    public HomeFragment(){ }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mPromList = new ArrayList<Promotion>();
        mDiscountedItems = new ArrayList<Product>();
        mPromotionAdapter = new PromotionAdapter(mPromList);

        testData();


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupPromotions();
    }
    private void setupPromotions(){
        RecyclerView promotionRecyclerView = getView().findViewById(R.id.home_promotion_list);
        mPromotionLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mPromotionLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        promotionRecyclerView.setLayoutManager(mPromotionLayoutManager);
        promotionRecyclerView.setItemAnimator(new DefaultItemAnimator());
        promotionRecyclerView.setAdapter(mPromotionAdapter);
    }

    public void testData(){
        Drawable img = getResources().getDrawable(R.drawable.shirt);
        mPromList.add(new Promotion(img));
        mPromList.add(new Promotion(img));
        mPromList.add(new Promotion(img));
        mPromList.add(new Promotion(img));
        mPromList.add(new Promotion(img));
        mPromList.add(new Promotion(img));
        mPromotionAdapter.notifyDataSetChanged();

    }

}