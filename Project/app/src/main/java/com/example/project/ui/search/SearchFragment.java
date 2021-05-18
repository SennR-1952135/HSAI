package com.example.project.ui.search;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.project.DataBase.AppDatabase;
import com.example.project.DataBase.Dao;
import com.example.project.DataBase.Store;
import com.example.project.R;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    //private SearchViewModel searchViewModel;
    Dao mdao;
    LinearLayout storeLL;
    LinearLayout discountLL;
    LinearLayout priceLL;
    LinearLayout colorLL;
    LinearLayout sizeLL;
    LinearLayout forLL;
    Button applyBtn;
    View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mdao = AppDatabase.getDb(getContext()).dao();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        view = inflater.inflate(R.layout.fragment_search, container, false);
        storeLL = view.findViewById(R.id.StoreLinearLayout);
        discountLL = view.findViewById(R.id.DiscountLinearLayout);
        priceLL = view.findViewById(R.id.PriceLinearLayout);
        colorLL = view.findViewById(R.id.ColorLinearLayout);
        sizeLL = view.findViewById(R.id.SizeLinearLayout);
        forLL = view.findViewById(R.id.ForLinearLayout);
        applyBtn = view.findViewById(R.id.ApplyFilters);


        createStoreLL();
//        createDiscountLL();
//        createPriceLL();
//        createColorLL();
//        createSizeLL();
//        createForLL();

        return view;
    }

    private void createStoreLL(){
        LinearLayout ll = new LinearLayout(getContext());
        ll.setOrientation(LinearLayout.HORIZONTAL);
        List<Store> allStores = mdao.getAllStores();
        for (Store st : allStores){
            CheckBox cb = new CheckBox(getContext());
//            cb.setText()
        }
    }
}