package com.example.project;

import android.os.Bundle;

import com.example.project.OldDB.DataBasee;
import com.example.project.DataBase.Product;
import com.example.project.OldDB.ProductEntity;
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


    /*
    private StoreDao storeDao;
    private ProductDao productDao;
    private StoreProductDao storeProductDao;
    */
    private DataBasee db;
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
    }
    public void setUpBottomNav(){
        mBottomNav = findViewById(R.id.nav_view);
        mAppBarConfig = new AppBarConfiguration.Builder(R.id.navigation_in_store, R.id.navigation_search, R.id.navigation_home, R.id.navigation_wishlist, R.id.navigation_shopping_cart).build();
        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, mNavController, mAppBarConfig);
        NavigationUI.setupWithNavController(mBottomNav, mNavController);
    }
    private void databaseInit(boolean loadData){
        db = DataBasee.getDb(getApplicationContext());
        db.clearAllTables();

        if(loadData){
            /*
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
             */
            ProductEntity p = new ProductEntity();
            p.setName("Zwarte Shirt");p.setShop("H&M");p.setPrice(10.99f);p.setDiscount(0f);p.setCategory(Category.SHIRT);p.setDescription("Zwarte shirt met streep");p.setDiscount(8.55f);
            db.mAppDao().createProduct(p);
            db.mAppDao().createProduct(p);
            db.mAppDao().createProduct(p);
            db.mAppDao().createProduct(p);
            db.mAppDao().createProduct(p);
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