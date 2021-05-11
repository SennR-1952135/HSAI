package com.example.project.DataBase;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.project.AppDatabase;

@Database(entities = {ProductEntity.class, StoreEntity.class}, version = 5)
public abstract class DataBasee extends RoomDatabase {

    private static DataBasee INSTANCE;
    public abstract appDAO mAppDao();
    public static DataBasee getDb(Context context){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DataBasee.class, "App_Database").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return INSTANCE;
    }
}
