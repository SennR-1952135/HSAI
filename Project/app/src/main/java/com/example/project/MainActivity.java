package com.example.project;

import android.os.Bundle;

//import com.example.project.OldDB.DataBasee;
import com.example.project.DataBase.*;
//import com.example.project.OldDB.ProductEntity;
import com.example.project.Enums.Category;
import com.example.project.Enums.Color;
import com.example.project.Enums.Gender;
import com.example.project.Enums.Size;
import com.example.project.ui.shopping_cart.ShoppingCart;

import com.example.project.ui.shopping_cart.ShoppingCartAdapter;
import com.example.project.ui.wishlist.WishListAdapter;
import com.example.project.ui.wishlist.Wishlist;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    private AppDatabase db;
    private Dao dao;
    private AppBarConfiguration mAppBarConfig;
    private BottomNavigationView mBottomNav;
    private NavController mNavController;


    private ShoppingCart mShoppingCart;
    private ShoppingCartAdapter mShoppingCartAdapter;

    private Wishlist mWishList;
    private WishListAdapter mWishlistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpBottomNav();
        databaseInit(true);
        mWishList = new Wishlist(dao);
        mShoppingCart = new ShoppingCart(dao);
    }
    public void setUpBottomNav(){
        mBottomNav = findViewById(R.id.nav_view);
        mAppBarConfig = new AppBarConfiguration.Builder(R.id.navigation_in_store, R.id.navigation_search, R.id.navigation_home, R.id.navigation_wishlist, R.id.navigation_shopping_cart).build();
        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, mNavController, mAppBarConfig);
        NavigationUI.setupWithNavController(mBottomNav, mNavController);
    }

    private void databaseInit(boolean loadData){
        db = AppDatabase.getDb(getApplicationContext());
        db.clearAllTables();
        dao = db.dao();

        if(loadData){
            Store s1 = new Store("zara", 50.930462, 5.337660);
            Store s2 = new Store("h&m", 50.934846, 5.336248);
            dao.insertStores(s1, s2);
            Product p0 = new Product("Zwarte Shirt", "Zwarte shirt met streep", 10.99f, 0f, R.drawable.shirt, Category.TSHIRT, s1.getmStoreID(), Color.BLACK, Gender.UNISEX, Size.M );
            Product p1 = new Product("Zwarte Shirt", "Zwarte shirt met streep", 10.99f, 0f, R.drawable.shirt, Category.TSHIRT, s1.getmStoreID(), Color.BLACK, Gender.UNISEX, Size.S );
            Product p2 = new Product("Zwarte Shirt", "Zwarte shirt met streep", 10.99f, 0f, R.drawable.shirt, Category.TSHIRT, s2.getmStoreID(), Color.BLACK, Gender.UNISEX, Size.L );
            Product p3 = new Product("Zwarte Shirt", "Zwarte shirt met streep", 10.99f, 0f, R.drawable.shirt, Category.TSHIRT, s1.getmStoreID(), Color.BLACK, Gender.UNISEX, Size.XL );
            Product p4 = new Product("Zwarte Shirt", "Zwarte shirt met streep", 10.99f, 0f, R.drawable.shirt, Category.TSHIRT, s2.getmStoreID(), Color.BLACK, Gender.UNISEX, Size.XS );
            dao.insertProducts(p0, p1, p2, p3, p4, p2, p3, p4, p1, p0, p2, p0);
//            ProductEntity p = new ProductEntity();
//            p.setName("Zwarte Shirt");p.setShop("H&M");p.setPrice(10.99f);p.setDiscount(0f);p.setCategory(Category.SHIRT);p.setDescription("Zwarte shirt met streep");p.setDiscount(8.55f);
//            db.mAppDao().createProduct(p);
//            db.mAppDao().createProduct(p);
//            db.mAppDao().createProduct(p);
//            db.mAppDao().createProduct(p);
//            db.mAppDao().createProduct(p);
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        //setCount(this, mShoppingCart.getCount());
        //updateTotalBasket();
        //mShoppingCartAdapter.notifyDataSetChanged();
    }

    public void addToWishlist(Product product) {
        mWishList.addItem(product);
        //WishlistAdapter.notifyDataSetChanged();
    }
}