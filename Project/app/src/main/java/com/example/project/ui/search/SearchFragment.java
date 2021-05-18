package com.example.project.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.project.DataBase.*;
import com.example.project.Enums.Color;
import com.example.project.Enums.Gender;
import com.example.project.Enums.Size;
import com.example.project.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    //private SearchViewModel searchViewModel;
    appDAO mdao;
    LinearLayout storeLL;
    LinearLayout discountLL;
    LinearLayout priceLL;
    LinearLayout colorLL;
    LinearLayout sizeLL;
    LinearLayout genderLL;
    Button applyBtn;
    View view;
    List<CheckBox> storeCB;
    List<CheckBox> discountCB;
    float maxPrice;
    List<CheckBox> colorCB;
    List<CheckBox> sizeCB;
    List<CheckBox> genderCB;
    List<ProductEntity> allProductEntities;
    List<ProductEntity> filteredProductEntities;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mdao = DataBasee.getDb(getContext()).mAppDao();
        storeCB = new ArrayList<CheckBox>();
        discountCB = new ArrayList<CheckBox>();
        colorCB = new ArrayList<CheckBox>();
        sizeCB = new ArrayList<CheckBox>();
        genderCB = new ArrayList<CheckBox>();
        allProductEntities = mdao.getAllProducts();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);
        storeLL = view.findViewById(R.id.StoreLinearLayout);
        discountLL = view.findViewById(R.id.DiscountLinearLayout);
        priceLL = view.findViewById(R.id.PriceLinearLayout);
        colorLL = view.findViewById(R.id.ColorLinearLayout);
        sizeLL = view.findViewById(R.id.SizeLinearLayout);
        genderLL = view.findViewById(R.id.GenderLinearLayout);
        applyBtn = view.findViewById(R.id.ApplyFilters);

//        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);

        createLayouts();
        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterProducts();
            }
        });
        return view;
    }

    private void createLayouts(){
        createStoreLL();
        createDiscountLL();
        createPriceLL();
        createColorLL();
        createSizeLL();
        createGenderLL();
    }

    private void createStoreLL() {
        LinearLayout llv = new LinearLayout(getContext());
        llv.setOrientation(LinearLayout.VERTICAL);
        LinearLayout llh = new LinearLayout(getContext());
        List<Store> allStores = mdao.getAllStores();
        int storeCount = 0;
        for (Store st : allStores) {
            if(storeCount++ % 3 == 0){
                llv.addView(llh);
                llh = new LinearLayout(getContext());
            }
            CheckBox cb = new CheckBox(getContext());
            cb.setText(st.getmName());
            storeCB.add(cb);
            llh.addView(cb);
        }
        llv.addView(llh);
        storeLL.addView(llv);
    }

    private void createDiscountLL(){
        LinearLayout llv = new LinearLayout(getContext());
        llv.setOrientation(LinearLayout.VERTICAL);
        LinearLayout llh = new LinearLayout(getContext());
        List<Float> discounts = mdao.getAllDiscounts();
        if (discounts.size() > 0) {
            int discountCount = 0;
            for (Float discountamount : discounts) {
                if (discountCount++ % 3 == 0) {
                    llv.addView(llh);
                    llh = new LinearLayout(getContext());
                }
                CheckBox cb = new CheckBox(getContext());
                cb.setText(new DecimalFormat("###.##").format(discountamount));
                discountCB.add(cb);
                llh.addView(cb);
            }
            llv.addView(llh);
        } else {
            TextView noDiscount = new TextView(getContext());
            noDiscount.setText("There are no discounts to filter from");
            llv.addView(noDiscount);
        }
        discountLL.addView(llv);
    }

    private void createPriceLL(){
        SeekBar sb = priceLL.findViewById(R.id.PriceSeekbar);
        int max = (int)Math.ceil(mdao.getMaxPrice());
        sb.setMax(max);
        TextView tvmax = priceLL.findViewById(R.id.MaxSeekbarLable);
        TextView tvcur = priceLL.findViewById(R.id.SeekbarSelectedAmount);
        tvmax.setText(String.valueOf(max));
        tvcur.setText(String.valueOf(sb.getProgress()));
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvcur.setText(String.valueOf(progress));
                maxPrice = (float) progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void createColorLL(){
        LinearLayout llv = new LinearLayout(getContext());
        llv.setOrientation(LinearLayout.VERTICAL);
        LinearLayout llh = new LinearLayout(getContext());
        List<Color> colors = mdao.getAllColors();
        if(colors.size() > 0){
            int count = 0;
            for (Color col : colors){
                if(count++ % 3 == 0){
                    llv.addView(llh);
                    llh = new LinearLayout(getContext());
                }
                CheckBox cb = new CheckBox(getContext());
                cb.setText(col.toString());
                colorCB.add(cb);
                llh.addView(cb);;
            }
            llv.addView(llh);
        } else {
            TextView noCol = new TextView(getContext());
            noCol.setText("There are no colors to filter from");
            llv.addView(noCol);
        }
        colorLL.addView(llv);
    }

    private void createSizeLL(){
        LinearLayout sizegroup = sizeLL.findViewById(R.id.SizeGroupLayout);
        for(int i = 0; i < sizegroup.getChildCount(); i++){
            sizeCB.add((CheckBox) sizegroup.getChildAt(i));
        }
    }

    private void createGenderLL(){
        LinearLayout llv = new LinearLayout(getContext());
        llv.setOrientation(LinearLayout.VERTICAL);
        LinearLayout llh = new LinearLayout(getContext());
        List<Gender> genders = mdao.getAllGenders();
        if(genders.size() > 0){
            int count = 0;
            for (Gender gen : genders){
                if(count++ % 3 == 0){
                    llv.addView(llh);
                    llh = new LinearLayout(getContext());
                }
                CheckBox cb = new CheckBox(getContext());
                cb.setText(gen.toString());
                genderCB.add(cb);
                llh.addView(cb);;
            }
            llv.addView(llh);
        } else {
            TextView noCol = new TextView(getContext());
            noCol.setText("There are no genders to filter from");
            llv.addView(noCol);
        }
        genderLL.addView(llv);
    }

    private void filterProducts(){
        filteredProductEntities = new ArrayList<>(allProductEntities);
        filterStore();
        filterDiscount();
        filterPrice();
        filterColor();
        filterSize();
        filterGender();
        System.out.println("test");
    }

    private void filterStore(){
        List<ProductEntity> newFilter = new ArrayList<ProductEntity>();
        List<String> stores = new ArrayList<String>();
        for(CheckBox cb : storeCB) if(cb.isChecked()) stores.add((String) cb.getText());
        List<Long> storeIDs = new ArrayList<Long>();
        for (String name : stores) storeIDs.add(mdao.getStoreIDByName(name));
        for(ProductEntity p : filteredProductEntities){
            for(Long id : storeIDs){
                if (p.getmStoreID() == id && !newFilter.contains(p)) newFilter.add(p);
            }
        }
        filteredProductEntities = new ArrayList<>(newFilter);
    }

    private void filterDiscount(){

    }

    private void filterPrice(){
        List<ProductEntity> newFilter = new ArrayList<ProductEntity>();
        for(ProductEntity p : filteredProductEntities) if(p.getmPrice() <= maxPrice) newFilter.add(p);
        filteredProductEntities = new ArrayList<>(newFilter);
    }

    private void filterColor(){
        List<ProductEntity> newFilter = new ArrayList<ProductEntity>();
        List<Color> colors = new ArrayList<Color>();
        for(CheckBox cb : colorCB) if(cb.isChecked()) colors.add(Color.valueOf((String) cb.getText()));
        for(ProductEntity p : filteredProductEntities){
            for(Color col : colors){
                if (p.getmColor() == col && !newFilter.contains(p)) newFilter.add(p);
            }
        }
        filteredProductEntities = new ArrayList<>(newFilter);
    }

    private void filterSize(){
        List<ProductEntity> newFilter = new ArrayList<ProductEntity>();
        List<Size> sizes = new ArrayList<Size>();
        for(CheckBox cb : sizeCB) if(cb.isChecked()) sizes.add(Size.valueOf((String) cb.getText()));
        for(ProductEntity p : filteredProductEntities){
            for(Size s : sizes){
                if (p.getmSize() == s && !newFilter.contains(p)) newFilter.add(p);
            }
        }
        filteredProductEntities = new ArrayList<>(newFilter);
    }

    private void filterGender(){
        List<ProductEntity> newFilter = new ArrayList<ProductEntity>();
        List<Gender> genders = new ArrayList<Gender>();
        for(CheckBox cb : genderCB) if(cb.isChecked()) genders.add(Gender.valueOf((String) cb.getText()));
        for(ProductEntity p : filteredProductEntities){
            for(Gender gen : genders){
                if (p.getmGender() == gen && !newFilter.contains(p)) newFilter.add(p);
            }
        }
        filteredProductEntities = new ArrayList<>(newFilter);
    }


}