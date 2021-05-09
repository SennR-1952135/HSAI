package com.example.project.DataBase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface appDAO {

    /**
     * Gets information about a product with a certain ID
     * @param p_name
     */
    @Query("SELECT * FROM Products WHERE name LIKE :p_name LIMIT 1")
    ProductEntity getProduct(String p_name);

    /**
     * Creates and inserts a new product into the db
     * @param product
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void createProduct(ProductEntity product);

    /**
     * Gets all items from the database
     * @return
     */
    @Query("SELECT * FROM Products")
    List<ProductEntity> getAllProducts();

    /**
     * Deletes all products from the database
     */
    @Query("DELETE FROM Products")
    void deleteProducts();



    @Query("SELECT * FROM Stores WHERE name LIKE :p_name LIMIT 1")
    StoreEntity getStore(String p_name);


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void createStore(StoreEntity store);

    @Query("SELECT * FROM Stores")
    List<StoreEntity> getAllStores();

    /**
     * Deletes all products from the database
     */
    @Query("DELETE FROM Stores")
    void deleteStores();
}
