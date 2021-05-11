//package com.example.project.DataBase;
//
//
//import androidx.room.Embedded;
//import androidx.room.Entity;
//import androidx.room.Relation;
//
//import com.example.project.DataBase.Product;
//import com.example.project.DataBase.Store;
//
//import java.util.List;
//
//@Entity
//public class StoreProduct {
//    @Embedded public Store store;
//    @Relation(
//            parentColumn = "storeID",
//            entityColumn = "productID"
//    )
//    public List<Product> products;
//}
