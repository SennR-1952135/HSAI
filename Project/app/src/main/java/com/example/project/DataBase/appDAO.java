package com.example.project.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface appDAO {

    //PRODUCT

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertProducts(ProductEntity products);

    @Update
    void updateProduct(ProductEntity product);

    @Delete
    void deleteProduct(ProductEntity product);

    @Query("Select id FROM Products WHERE name like :name LIMIT 1")
    long getPIDByName(String name);

    @Query("SELECT * FROM Products WHERE name LIKE :p_name LIMIT 1")
    ProductEntity getProduct(String p_name);

    @Query("SELECT * FROM Products WHERE id LIKE :p_id LIMIT 1")
    ProductEntity getProduct(int p_id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void createProduct(ProductEntity product);

    @Query("SELECT * FROM Products")
    List<ProductEntity> getAllProducts();

    @Query("DELETE FROM Products")
    void deleteProducts();

    @Query("SELECT * FROM Stores WHERE name LIKE :p_name LIMIT 1")
    StoreEntity getStore(String p_name);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void createStore(StoreEntity store);

    @Query("SELECT * FROM Stores")
    List<StoreEntity> getAllStores();

    @Query("DELETE FROM Stores")
    void deleteStores();


    /**
     * //////////////////////////////
     * ITEMS IN CART
     * /////////////////////////////
     */

    @Query("SELECT * FROM ProductsInCart")
    List<ProductInCart> getItemsOnCart();

    @Query(("SELECT * FROM ProductsInCart WHERE productid LIKE :product_id"))
    ProductInCart getItemOnCart(int product_id);

    @Query(("SELECT * FROM ProductsInCart WHERE productid LIKE :product_id AND size LIKE :size"))
    ProductInCart getItemOnCartWithGivenSize(int product_id, String size);

    @Query("UPDATE ProductsInCart SET count = :new_quantity WHERE productid LIKE :product_id AND size LIKE :sizee")
    void updateProductQuantity(int new_quantity, int product_id, String sizee);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createProductInCart(ProductInCart product);

    @Query("DELETE FROM ProductsInCart WHERE productid LIKE :product_id")
    void removeProductInCart(int product_id);

    @Query("DELETE FROM ProductsInCart WHERE productid LIKE :product_id AND size LIKE :size")
    void removeProductInCartWithGivenSize(int product_id,String size);

    @Query("DELETE FROM ProductsInCart")
    void removeAllInCart();

    /////////////////////////////

    @Query("SELECT * FROM ProductsInWishlist")
    List<ProductInWishlist> getItemsOnWishlist();

    @Query(("SELECT * FROM ProductsInWishlist WHERE productid LIKE :product_id"))
    ProductInWishlist getItemOnWishlist(int product_id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void createProductInWishlist(ProductInWishlist product);

    @Query("DELETE FROM ProductsInWishlist WHERE productid LIKE :product_id")
    void removeProductInWishlist(int product_id);

    @Query("DELETE FROM ProductsInWishlist")
    void removeAllInWishlist();


}
