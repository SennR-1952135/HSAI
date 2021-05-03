package com.example.project;

import androidx.room.*;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ProductDao {
    @Insert
    void insert(Product... products);

    @Update
    void update(Product user);

    @Delete
    void delete(Product user);

    @Query("Select * FROM Product where name LIKE :name")
    Product getByName(String name);

    @Query("Select * FROM Product where name LIKE :name AND store == :store")
    Product getByNameAndStore(String name, Store store);

    @Query("SELECT * FROM Product")
    ArrayList<Product> getAll();
}
