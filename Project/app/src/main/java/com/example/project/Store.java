package com.example.project;

import androidx.room.*;

import java.util.ArrayList;

@Entity
public class Store {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    public String mName;
    public String mLocatie;

    public ArrayList<Product> productArrayList;


    public Store(String name, String locatie){
        mName = name;
        mLocatie = locatie;
    }

}
