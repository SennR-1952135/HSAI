package com.example.project.DataBase;

import androidx.room.*;

import java.util.ArrayList;

@Entity
public class Store {
    @PrimaryKey(autoGenerate = true)
    public long storeID;

    public String mName;
    public String mLocatie;


    public Store(String name, String locatie){
        mName = name;
        mLocatie = locatie;
    }

}
