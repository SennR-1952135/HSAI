package com.example.project.DataBase;


import androidx.room.*;

@Database(entities = {Product.class, Store.class}, version = 3)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract Dao dao();

}
