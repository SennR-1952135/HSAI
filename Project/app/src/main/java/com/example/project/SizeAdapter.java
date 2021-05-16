package com.example.project;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.ui.product_view.ClothingSize;

import java.util.ArrayList;

public class SizeAdapter extends RecyclerView.Adapter<SizeAdapter.MyViewHolder> {
    private ArrayList<ClothingSize> m;
    private Fragment mContext;
    private Product q;

    public SizeAdapter(ArrayList<ClothingSize> m, Product q, Fragment context){
        this.m = m;
        this.q = q;
        mContext = context;
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView status;
        TextView size;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            status = itemView.findViewById(R.id.size_status);
            size = itemView.findViewById(R.id.size_text);
            cardView = itemView.findViewById(R.id.size_container);

        }
    }

    @NonNull
    @Override
    public SizeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = null;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);
        return new SizeAdapter.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull SizeAdapter.MyViewHolder holder, int pos){

        boolean doSmthg;
        if(q.isSizeAvailable(m.get(pos))){
            doSmthg = true;
            holder.status.setText("Available!");
            holder.size.setText(m.get(pos).toString());
            holder.status.setTextColor(Color.rgb(0,0,0));
            holder.size.setTextColor(Color.rgb(0,0,0));


        }
        else{
            doSmthg = false;
            holder.status.setText("Not available!");
            holder.size.setText(m.get(pos).toString());
            holder.status.setTextColor(Color.rgb(128,128,128));
            holder.size.setTextColor(Color.rgb(128,128,128));
        }
        holder.cardView.setOnClickListener(v -> {
            if(doSmthg){
                System.out.println("KEK");
            }
        });

    }

    @Override
    public int getItemCount() {
        return 1;
    }


}