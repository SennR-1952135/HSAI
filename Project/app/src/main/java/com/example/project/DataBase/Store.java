package com.example.project.DataBase;

import androidx.annotation.NonNull;
import androidx.room.*;

import java.util.ArrayList;

@Entity
public class Store {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "StoreID")
    public int ID;

    @ColumnInfo(name = "Name")
    @NonNull
    public String mName;

    @ColumnInfo(name = "Lat")
    public double mLat;

    @ColumnInfo(name = "Long")
    public double mLongg;


    public Store(String name, double lat, double longg){
        this.mName = name;
        this.mLat = lat;
        this.mLongg = longg;
    }

}
