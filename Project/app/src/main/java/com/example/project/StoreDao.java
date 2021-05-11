package com.example.project;

import androidx.room.*;

import com.example.project.DataBase.Store;

import java.util.List;

@Dao
public interface StoreDao {
    @Insert
    void insert(Store... stores);

    @Update
    void update(Store winkel);

    @Delete
    void delete(Store winkel);

    @Query("Select * FROM Store where mName LIKE :name")
    Store getByName(String name);

    @Query("SELECT * FROM Store")
    List<Store> getAll();

    @Query("SELECT storeID FROM Store WHERE mName LIKE :name")
    long getIDByName(String name);
}
