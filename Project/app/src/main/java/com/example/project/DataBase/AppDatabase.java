package com.example.project.DataBase;


import android.content.Context;

import androidx.room.*;

import com.example.project.OldDB.DataBasee;
import com.example.project.OldDB.appDAO;

@Database(entities = {Product.class, Store.class}, version = 3)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract Dao dao();
    private static AppDatabase INSTANCE;
    public static AppDatabase getDb(Context context){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "App_Database").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return INSTANCE;
    }
}
