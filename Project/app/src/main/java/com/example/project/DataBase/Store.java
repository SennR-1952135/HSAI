package com.example.project.DataBase;

import androidx.annotation.NonNull;
import androidx.room.*;

import java.util.ArrayList;

@Entity
public class Store {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "StoreID")
    private int mStoreID;

    @ColumnInfo(name = "Name")
    @NonNull
    private String mName;

    @ColumnInfo(name = "Lat")
    private double mLat;

    @ColumnInfo(name = "Long")
    private double mLongg;


    public Store(@NonNull String name, double lat, double longg){
        this.mName = name;
        this.mLat = lat;
        this.mLongg = longg;
    }

    /*
    Getters and Setters
     */

    public int getmStoreID() {
        return mStoreID;
    }

    @NonNull
    public String getmName() {
        return mName;
    }

    public void setmName(@NonNull String mName) {
        this.mName = mName;
    }

    public double getmLat() {
        return mLat;
    }

    public void setmLat(double mLat) {
        this.mLat = mLat;
    }

    public double getmLongg() {
        return mLongg;
    }

    public void setmLongg(double mLongg) {
        this.mLongg = mLongg;
    }


}
