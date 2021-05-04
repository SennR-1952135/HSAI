package com.example.project;


import androidx.room.*;

@Database(entities = {Product.class, Store.class}, version = 2)
public abstract class AppDatabse extends RoomDatabase {
    public abstract ProductDao productDao();
    public abstract StoreDao storeDao();
    public abstract StoreProductDao storeProductDao();

}
