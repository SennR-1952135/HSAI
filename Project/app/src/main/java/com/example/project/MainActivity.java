package com.example.project;

import android.location.Geocoder;
import android.os.Bundle;

import com.example.project.DataBase.DataBasee;
import com.example.project.DataBase.ProductEntity;
import com.example.project.DataBase.StoreEntity;
import com.example.project.ui.shopping_cart.ShoppingCart;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.IOException;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

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
        //insertData();
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

        db.mAppDao().createProduct(new ProductEntity(1, "Black Shirt", "H&M", "Black shirt with logo.",10.99f, 0f,Category.SHIRT));
        db.mAppDao().createProduct(new ProductEntity(2, "Blue Pants", "H&M", "Some blue pants with stripes.",20.99f, 15.55f,Category.PANTS));
        db.mAppDao().createProduct(new ProductEntity(3, "Shoes", "ZARA", "Zwarte shirt met streep",10.99f, 8.55f,Category.SHOES));
        db.mAppDao().createProduct(new ProductEntity(5, "Zwarte Shirt", "BERSHKA", "Zwarte shirt met streep",10.99f, 8.55f,Category.SHIRT));
            db.mAppDao().createStore(new StoreEntity("zara", 50.930462, 5.337660));
            db.mAppDao().createStore(new StoreEntity("h&m", 50.934846, 5.336248));

    }




    public void navigateTo(int actionId, Bundle bundle){
        //if (mDrawerLayout.isDrawerOpen(GravityCompat.END)){ // If you tap on an item but the shopping cart is open, close it
            //mDrawerLayout.closeDrawer(GravityCompat.END);
        //}
        mNavController.navigate(actionId, bundle);

    }
}