package com.example.project;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface StoreProductDao {
    @Transaction
    @Query("Select * FROM Store")
    public List<StoreProduct> getStoresWithProducts();
}
