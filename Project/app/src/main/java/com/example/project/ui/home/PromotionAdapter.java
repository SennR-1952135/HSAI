package com.example.project.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.Promotion;
import com.example.project.R;

import java.util.ArrayList;

public class PromotionAdapter extends RecyclerView.Adapter<PromotionAdapter.MyViewHolder> {


    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView promImg;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            promImg = itemView.findViewById(R.id.promotion_img);
        }
    }


    private ArrayList<Promotion> mPromotionList;

    public PromotionAdapter(ArrayList<Promotion> prom){
        mPromotionList = prom;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_normal_item,parent,false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Promotion promotion = mPromotionList.get(position);
        holder.promImg.setImageDrawable(promotion.getPromImg());
    }

    @Override
    public int getItemCount() {
        return mPromotionList.size();
    }


}
