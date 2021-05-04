package com.example.project;


import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class StoreProduct {
    @Embedded public Store store;
    @Relation(
            parentColumn = "storeID",
            entityColumn = "productID"
    )
    public List<Product> products;
}
