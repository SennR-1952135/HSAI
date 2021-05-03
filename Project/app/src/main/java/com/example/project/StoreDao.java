package com.example.project;

import androidx.room.*;

import java.util.ArrayList;

@Dao
public interface StoreDao {
    @Insert
    void insert(Store... stores);

    @Update
    void update(Store winkel);

    @Delete
    void delete(Store winkel);

    @Query("Select * FROM Store where mname LIKE :name")
    Store getByName(String name);

    @Query("SELECT * FROM Store")
    ArrayList<Store> getAll();
}
