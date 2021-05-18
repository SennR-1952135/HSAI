package com.example.project.DataBase;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


@Database(entities = {ProductEntity.class, StoreEntity.class, ProductInCart.class, ProductInWishlist.class, UserEntity.class}, version = 24)
@TypeConverters({Converters.class})
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
