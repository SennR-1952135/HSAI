package com.example.project;

import android.os.Bundle;

import com.example.project.DataBase.DataBasee;
import com.example.project.DataBase.ProductEntity;
import com.example.project.DataBase.Store;
import com.example.project.Enums.Category;


import com.example.project.Enums.Color;
import com.example.project.Enums.Gender;
import com.example.project.Enums.Size;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    private DataBasee db;
    private AppBarConfiguration mAppBarConfig;
    private BottomNavigationView mBottomNav;
    private NavController mNavController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpBottomNav();

        db = DataBasee.getDb(getApplicationContext());
        insertData();
    }
    public void setUpBottomNav(){
        mBottomNav = findViewById(R.id.nav_view);
        mAppBarConfig = new AppBarConfiguration.Builder(R.id.navigation_in_store, R.id.navigation_search, R.id.navigation_home, R.id.navigation_wishlist, R.id.navigation_shopping_cart).build();
        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, mNavController, mAppBarConfig);
        NavigationUI.setupWithNavController(mBottomNav, mNavController);
    }
    private void insertData(){
        db.clearAllTables();
        db.mAppDao().deleteProducts();
        db.mAppDao().removeAllInCart();


        Store s1 = new Store("ZARA", 50.930462, 5.337660);
        Store s2 = new Store("H&M", 50.934846, 5.336248);
        Store s3 = new Store("BERSHKA", 50.934846, 5.336248);
        db.mAppDao().createStore(s1);db.mAppDao().createStore(s2);db.mAppDao().createStore(s3);

        db.mAppDao().createProduct(new ProductEntity("Zwarte Shirt", "Zwarte shirt met streep", 10.99f, 0f, R.drawable.shirt, Category.TSHIRT, s1.getID(),s1.getName(), Color.GREEN, Gender.UNISEX, Size.M ));
        db.mAppDao().createProduct(new ProductEntity("Blue Pants", "Some blue pants with stripes", 10.99f, 0f, R.drawable.shirt, Category.TSHIRT, s2.getID(),s2.getName(), Color.GREEN, Gender.UNISEX, Size.M ));
        db.mAppDao().createProduct(new ProductEntity("Zwarte Shirt", "Zwarte shirt met streep", 20.99f, 25.0f, R.drawable.shirt, Category.PANTS, s2.getID(),s2.getName(), Color.GREEN, Gender.FEMALE, Size.S ));
        db.mAppDao().createProduct(new ProductEntity("Zwarte Shirt", "Zwarte shirt met streep", 10.99f, 10.0f, R.drawable.shirt, Category.TSHIRT, s3.getID(),s3.getName(), Color.GREEN, Gender.MALE, Size.M ));


    }

}