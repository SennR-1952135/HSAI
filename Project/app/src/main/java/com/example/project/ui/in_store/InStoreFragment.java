package com.example.project.ui.in_store;


import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.DataBase.DataBasee;
import com.example.project.DataBase.ProductEntity;
import com.example.project.DataBase.StoreEntity;
import com.example.project.Product;
import com.example.project.R;
import com.example.project.ui.home.ProductAdapter;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class InStoreFragment extends Fragment {

    private List<StoreEntity> stores;
    private String global_in_store_name;

    private InStoreViewModel inStoreViewModel;
    private scanFragment scanFragment;
    private ImageButton btnQueueCheckout;
    private ImageButton btnQueueFitting;
    private TextView storeNameView;
    private Button btnSearchNewStore;
    private Button btnScanner;

    private View rootView;
    private View rootMapView;

    private MapView mapView;
    private GoogleMap map;
    private CameraPosition cameraPosition;

    // creating a variable for map search view.
    SearchView searchView;
    AutoCompleteTextView searchStoreView;

    // The entry point to the Fused Location Provider.
    private FusedLocationProviderClient fusedLocationProviderClient;

    // A default location (Sydney, Australia) and default zoom to use when location permission is
    // not granted.
    private final LatLng defaultLocation = new LatLng(50.93172813962564, 5.338214611361976);
    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean locationPermissionGranted;

    // The geographical location where the device is currently located. That is, the last-known location retrieved by the Fused Location Provider.
    private Location lastKnownLocation;

    // Keys for storing activity state.
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";


    private ArrayList<Product> mPromotionsList;
    private ProductAdapter mPromotionsAdapter;
    private LinearLayoutManager mPromotionsLayoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mPromotionsList = new ArrayList<Product>();
        mPromotionsAdapter = new ProductAdapter(mPromotionsList, this);

        inStoreViewModel =
                new ViewModelProvider(this).get(InStoreViewModel.class);
        rootView = inflater.inflate(R.layout.fragment_in_store, container, false);
        setupPromotions(rootView);
        rootMapView = inflater.inflate(R.layout.activity_maps, container, false);
        loadStores();
        global_in_store_name = getGlobalStoreName();
        storeNameView = rootView.findViewById(R.id.text_in_store_store_name);
        storeNameView.setText(global_in_store_name);

        scanFragment = new scanFragment();
        btnScanner = rootView.findViewById(R.id.btn_in_store_scanner);
        btnScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction t = getFragmentManager().beginTransaction();
                if (Build.VERSION.SDK_INT>= 26) {
                    t.setReorderingAllowed(false);
                }
                t.replace(((ViewGroup)getView().getParent()).getId(), scanFragment, "scanFragment").commit();
            }
        });

        btnSearchNewStore = rootView.findViewById(R.id.btn_in_store_find_new_store);
        btnSearchNewStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setGlobalInStoreName("");
                FragmentTransaction t = getFragmentManager().beginTransaction();
                if (Build.VERSION.SDK_INT>= 26) {
                    t.setReorderingAllowed(false);
                }
                t.detach(InStoreFragment.this).attach(InStoreFragment.this).commit();
            }
        });

        //Help btns
        btnQueueCheckout = (ImageButton) rootView.findViewById(R.id.btn_in_store_help_queue_checkout);
        btnQueueCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog("Queue Checkout", "There is only a few people in line\n\nEstimated wating time: 1 minute");
            }
        });
        btnQueueFitting = (ImageButton) rootView.findViewById(R.id.btn_in_store_help_queue_fitting_rooms);
        btnQueueFitting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog("Queue Fitting Rooms", "The line at the fitting rooms is very long at this moment!\n\nEstimated wating time: 9 minutes");
            }
        });
        mapView = (MapView) rootMapView.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume(); // needed to get the map to display immediately
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (savedInstanceState != null) {
            lastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            cameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }

        // Construct a FusedLocationProviderClient.
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

        //init store search autocomplete
        searchStoreView = rootMapView.findViewById(R.id.autoCompleteStore);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1, getStoreNames() );
        searchStoreView.setAdapter(adapter);
        searchStoreView.setThreshold(1);
        searchStoreView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                int index = getStoreIndex(item);
                LatLng location = new LatLng(stores.get(index).getLat(), stores.get(index).getLongg());
                map.moveCamera(CameraUpdateFactory
                        .newLatLngZoom(location, DEFAULT_ZOOM));
            }
        });

        //init map view
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                // Use a custom info window adapter to handle multiple lines of text in the
                // info window contents.
                map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

                    @Override
                    // Return null here, so that getInfoContents() is called next.
                    public View getInfoWindow(Marker arg0) {
                        return null;
                    }

                    @Override
                    public View getInfoContents(Marker marker) {
                        // Inflate the layouts for the info window, title and snippet.
                        View infoWindow = getLayoutInflater().inflate(R.layout.map_info,
                                (FrameLayout) rootMapView.findViewById(R.id.map), false);

                        if (infoWindow.findViewById(R.id.map_info_title) != null) {
                            TextView title = infoWindow.findViewById(R.id.map_info_title);
                            title.setText(marker.getTitle());
                        }

                        if (infoWindow.findViewById(R.id.map_info_snippet) != null) {
                            TextView snippet = infoWindow.findViewById(R.id.map_info_snippet);
                            snippet.setText(marker.getSnippet());
                        }
                        return infoWindow;
                    }
                });

                map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        setGlobalInStoreName(marker.getTitle());
                        FragmentTransaction t = getFragmentManager().beginTransaction();
                        if (Build.VERSION.SDK_INT>= 26) {
                            t.setReorderingAllowed(false);
                        }
                        t.detach(InStoreFragment.this).attach(InStoreFragment.this).commit();
                    }
                });

                // Prompt the user for permission.
                getLocationPermission();

                // Turn on the My Location layer and the related control on the map.
                updateLocationUI();

                // Get the current location of the device and set the position of the map.
                getDeviceLocation();

                for(StoreEntity store : stores){
                    map.addMarker(new MarkerOptions()
                    .position(new LatLng(store.getLat(), store.getLongg()))
                    .title(store.getName())
                    .snippet("Opening hours: \nMonday: 08:00 - 18:00\nTuesday: 08:00 - 18:00\nWednesday: 08:00 - 18:00\nThursday: 08:00 - 18:00\nFriday: 08:00 - 18:00\nSaturday: 08:00 - 20:00\nSunday: closed"));
                }
                map.moveCamera(CameraUpdateFactory
                        .newLatLngZoom(defaultLocation, DEFAULT_ZOOM));
            }
        });

        if(global_in_store_name.isEmpty())
            return rootMapView;
        else
            return rootView;
    }

    private void setupPromotions(View k) {
        RecyclerView promotionsRecyclerView = k.findViewById(R.id.promotions_list_store);
        mPromotionsLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mPromotionsLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        promotionsRecyclerView.setLayoutManager(mPromotionsLayoutManager);
        promotionsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        promotionsRecyclerView.setAdapter(mPromotionsAdapter);
        getPromotionsItems();
    }
    public void getPromotionsItems(){
        DataBasee db = DataBasee.getDb(getActivity());
        List<ProductEntity> products_from_db = db.mAppDao().getAllProducts();

        Drawable img = getResources().getDrawable(R.drawable.shirt);
        for(ProductEntity dbItem : products_from_db){
            if(dbItem.getShop().equals(getGlobalStoreName()) && dbItem.getDiscount() != 0.0f){
                Product newProd = new Product(dbItem.getId(),dbItem.getName(), dbItem.getShop(), dbItem.getDescription(), dbItem.getPrice(), dbItem.getDiscount(), img, dbItem.getCategory());
                mPromotionsList.add(newProd);
            }
        }
    }

    public void openDialog(String title, String text) {
        infoDialog d = new infoDialog(title, text);
        d.show(getFragmentManager(), "info dialog");
    }

    /**
     * Saves the state of the map when the activity is paused.
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (map != null) {
            outState.putParcelable(KEY_CAMERA_POSITION, map.getCameraPosition());
            outState.putParcelable(KEY_LOCATION, lastKnownLocation);
        }
        super.onSaveInstanceState(outState);
    }

    /**
     * Gets the current location of the device, and positions the map's camera.
     */
    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (locationPermissionGranted) {
                Task<Location> locationResult = fusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(getActivity(), new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            lastKnownLocation = task.getResult();
                            if (lastKnownLocation != null) {
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                        new LatLng(lastKnownLocation.getLatitude(),
                                                lastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                            }
                        } else {
                            map.moveCamera(CameraUpdateFactory
                                    .newLatLngZoom(defaultLocation, DEFAULT_ZOOM));
                            map.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                        map.moveCamera(CameraUpdateFactory
                                .newLatLngZoom(defaultLocation, DEFAULT_ZOOM));
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage(), e);
        }
    }

    /**
     * Prompts the user for permission to use the device location.
     */
    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    /**
     * Handles the result of the request for location permissions.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        locationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locationPermissionGranted = true;
                }
            }
        }
        updateLocationUI();
    }

    /**
     * Updates the map's UI settings based on whether the user has granted location permission.
     */
    private void updateLocationUI() {
        if (map == null) {
            return;
        }
        try {
            if (locationPermissionGranted) {
                map.setMyLocationEnabled(true);
                map.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                map.setMyLocationEnabled(false);
                map.getUiSettings().setMyLocationButtonEnabled(false);
                lastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e) {
            //Log.e("Exception: %s", e.getMessage());
        }
    }

    private void loadStores() {
        DataBasee db = DataBasee.getDb(getActivity());
        stores = db.mAppDao().getAllStores();
    }

    private String[] getStoreNames() {
        String[] s = new String[stores.size()];
        for (int i = 0; i < stores.size(); i++) {
            s[i] = stores.get(i).getName();
        }
        return s;
    }
    private int getStoreIndex(String name) {
        for (int i = 0; i < stores.size(); i++) {
            if (stores.get(i).getName().equals(name))
                return i;
        }
        return 0;
    }
    private String getGlobalStoreName() {
        TextView t = getActivity().findViewById(R.id.in_store_name_global);
        return t.getText().toString();
    }
    private void setGlobalInStoreName(String name) {
        TextView t = getActivity().findViewById(R.id.in_store_name_global);
        t.setText(name);
    }
}