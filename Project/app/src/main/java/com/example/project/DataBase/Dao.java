package com.example.project.DataBase;
import android.graphics.drawable.Drawable;

import androidx.room.*;

import com.example.project.Enums.Category;
import com.example.project.Enums.Color;
import com.example.project.Enums.Size;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    // Product

    @Insert
    void insertProducts(Product... products);

    @Update
    void updateProduct(Product product);

    @Delete
    void deleteProduct(Product product);

    @Query("SELECT * FROM Product")
    List<Product> getAllProducts();

    @Query("Select ProductID FROM Product WHERE Name like :name LIMIT 1")
    long getPIDByName(String name);

//    @Query("SELECT Name FROM Product WHERE ProductID == :ID")
//    String getPNameByID(long ID);
//
//    @Query("SELECT Price FROM Product WHERE ProductID == :ID")
//    float getPPriceByID(long ID);
//
//    @Query("SELECT Discription FROM Product WHERE ProductID == :ID")
//    String getPDiscriptionByID(long ID);
//
//    @Query("SELECT Color FROM Product WHERE ProductID == :ID")
//    Color getPColorByID(long ID);
//
//    @Query("SELECT Discounted FROM Product WHERE ProductID == :ID")
//    boolean getPDiscountedByID(long ID);
//
//    @Query("SELECT DiscountAmount FROM Product WHERE ProductID == :ID")
//    float getPDiscountAmountByID(long ID);
//
//    @Query("SELECT Image FROM product WHERE ProductID == :ID")
//    int getPImageResourceByID(long ID);
//
//    @Query("SELECT Category FROM Product WHERE ProductID == :ID")
//    Category getPCategoryByID(long ID);
//
//    @Query("SELECT StoreID FROM Product WHERE ProductID == :ID")
//    long getStoreIDByProductID(long ID);
//
//    @Query("SELECT Size FROM Product WHERE ProductID == :ID")
//    Size getPSizeByID(long ID);


    //STORE
    @Insert
    void insertStores(Store... stores);

    @Update
    void updateStore(Store store);

    @Delete
    void deleteStore(Store store);

    @Query("SELECT * FROM Store")
    List<Store> getAllStores();

    @Query("SELECT * FROM Store WHERE StoreID == :ID")
    Store getStoreByID(long ID);

//    @Query("SELECT Name FROM Store WHERE StoreID == :ID")
//    String getSNameByID(long ID);
//
//    @Query("SELECT Lat FROM Store WHERE StoreID == :ID")
//    double getSLatByID(long ID);
//
//    @Query("SELECT Long FROM Store WHERE StoreID == :ID")
//    double getSLongByID(long ID);

}
