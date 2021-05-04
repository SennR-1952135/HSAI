package com.example.project;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity {

    AppDatabse db;
    StoreDao storeDao;
    ProductDao productDao;
    StoreProductDao storeProductDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_in_store, R.id.navigation_search,
                R.id.navigation_home, R.id.navigation_wishlist, R.id.navigation_shopping_cart)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        databaseInit(true);
    }

    private void databaseInit(boolean loadData){
        db = Room.databaseBuilder(getApplicationContext(), AppDatabse.class, "App_Database").allowMainThreadQueries().build();
        storeDao = db.storeDao();
        productDao = db.productDao();
        storeProductDao = db.storeProductDao();

        if(loadData){
            storeDao.insert(new Store("C&A", "Diepenbeek"));
            storeDao.insert(new Store("H&M", "Hasselt"));
            productDao.insert(new Product("Levi's T-shirt", "een random tshirt van levi's", 63.2f, storeDao.getIDByName("C&A")));
            productDao.insert(new Product("Levi's T-shirt2", "een random tshirt van levi's", 60f, storeDao.getIDByName("H&M")));
            productDao.insert(new Product("Levi's T-shirt3", "een random tshirt van levi's", 63.2f, storeDao.getIDByName("C&A")));
            productDao.insert(new Product("Levi's T-shirt4", "een random tshirt van levi's", 50f, storeDao.getIDByName("H&M")));
            productDao.insert(new Product("Random andere T-shirt", "een random tshirt van levi's", 50f, storeDao.getIDByName("H&M")));
            productDao.insert(new Product("Random andere T-shirt", "een random tshirt van levi's", 50f, storeDao.getIDByName("C&A")));
            productDao.insert(new Product("Random Broek", "een random tshirt van levi's", 50f, storeDao.getIDByName("H&M")));
            productDao.insert(new Product("Random Broek", "een random tshirt van levi's", 50f, storeDao.getIDByName("C&A")));
            productDao.insert(new Product("Random andere Broek", "een random tshirt van levi's", 50f, storeDao.getIDByName("C&A")));
            productDao.insert(new Product("Levi's T-shirt11", "een random tshirt van levi's", 63.2f, storeDao.getIDByName("C&A")));
            productDao.insert(new Product("Levi's T-shirt22", "een random tshirt van levi's", 60f, storeDao.getIDByName("H&M")));
            productDao.insert(new Product("Levi's T-shirt33", "een random tshirt van levi's", 63.2f, storeDao.getIDByName("C&A")));
            productDao.insert(new Product("Levi's T-shirt44", "een random tshirt van levi's", 50f, storeDao.getIDByName("H&M")));
            productDao.insert(new Product("Random andere T-shirt1", "een random tshirt van levi's", 50f, storeDao.getIDByName("H&M")));
            productDao.insert(new Product("Random andere T-shirt22", "een random tshirt van levi's", 50f, storeDao.getIDByName("C&A")));
            productDao.insert(new Product("Random Broek2", "een random tshirt van levi's", 50f, storeDao.getIDByName("H&M")));
            productDao.insert(new Product("Random Broek3", "een random tshirt van levi's", 50f, storeDao.getIDByName("C&A")));
            productDao.insert(new Product("Random andere Broek1", "een random tshirt van levi's", 50f, storeDao.getIDByName("C&A")));
        }

        for (StoreProduct sp : storeProductDao.getStoresWithProducts()){
            System.out.println(sp);
        }
    }

}