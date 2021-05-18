package com.example.project.DataBase;

import android.content.Context;
import android.location.Geocoder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import android.location.Address;


@Entity(tableName = "Stores")
public class StoreEntity {
    static private int storeCount = 0;
    @PrimaryKey(autoGenerate = false)
    @NonNull
    private int ID;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "lat")
    private double lat;

    @ColumnInfo(name = "long")
    private double longg;


    //GETTERS AND SETTERS

    @NonNull
    public int getID() {
        return ID;
    }

    public void setID(@NonNull int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(Address location) {
        this.lat = location.getLatitude();
        this.longg = location.getLongitude();
    }

    public double getLat() {
        return this.lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLongg() {
        return this.longg;
    }

    public void setLongg(double longg) {
        this.longg = longg;
    }

    /*public StoreEntity(String name, Address address){
        this.name = name;
        this.setLocation(address);
    }*/

    public StoreEntity(String name, double lat, double longg) {
        this.name = name;
        this.lat = lat;
        this.longg = longg;
        this.ID = storeCount++;
    }

    /*public float getPrice() {return price;}
    public void setPrice(float price) {this.price = price;}*/

}
