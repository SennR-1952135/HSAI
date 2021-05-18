package com.example.project.ui.in_store;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.DataBase.DataBasee;
import com.example.project.DataBase.ProductEntity;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.example.project.R;

import java.io.IOException;
import java.util.List;

public class scanFragment extends Fragment {


    private SurfaceView surfaceView;
    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;
    private static final int REQUEST_CAMERA_PERMISSION = 201;
    private ToneGenerator toneGen1;
    private TextView barcodeText;
    private String barcodeData;
    private View rootView;
    private scanFragment frag;
    private List<ProductEntity> productEntities;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_scanner, container, false);
        toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
        surfaceView = rootView.findViewById(R.id.surface_view);
        barcodeText = rootView.findViewById(R.id.barcode_text);
        DataBasee db = DataBasee.getDb(getActivity());
        productEntities = db.mAppDao().getAllProducts();
        initialiseDetectorsAndSources();
        return rootView;
    }

    private void initialiseDetectorsAndSources() {

        Toast.makeText(getContext(), "Barcode scanner started", Toast.LENGTH_SHORT).show();

        barcodeDetector = new BarcodeDetector.Builder(getContext())
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build();

        cameraSource = new CameraSource.Builder(getContext(), barcodeDetector)
                .setRequestedPreviewSize(1920, 1080)
                .setAutoFocusEnabled(true) //you should add this feature
                .build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        cameraSource.start(surfaceView.getHolder());
                    } else {
                        ActivityCompat.requestPermissions(getActivity(), new
                                String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });


        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
                // Toast.makeText(getApplicationContext(), "To prevent memory leaks barcode scanner has been stopped", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if (barcodes.size() != 0) {


                    barcodeText.post(new Runnable() {

                        @Override
                        public void run() {

                            if (barcodes.valueAt(0).email != null) {
                                barcodeText.removeCallbacks(null);
                                barcodeData = barcodes.valueAt(0).email.address;
                                barcodeText.setText(barcodeData);
                                toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP, 150);
                            } else {
                                barcodeData = barcodes.valueAt(0).displayValue;
                                barcodeText.setText(barcodeData);
                                toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP, 150);
                            }
                            Bundle bundle = new Bundle();
                            bundle.putString("itemId", Integer.toString(productEntities.get(1).getId()));
                            NavHostFragment.findNavController(getParentFragment()).navigate(R.id.productFragment, bundle);
                        }
                    });

                }
            }
        });
    }
}

/*
import android.os.Build;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;

import com.example.project.DataBase.DataBasee;
import com.example.project.DataBase.ProductEntity;
import com.example.project.DataBase.ProductInWishlist;
import com.example.project.Product;
import com.example.project.R;
import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import info.androidhive.barcode.BarcodeReader;
import com.example.project.ui.in_store.InStoreFragment;

public class scanFragment extends Fragment {

    private BarcodeReader barcodeReader;
    private View rootView;
    private scanFragment frag;
    private List<ProductEntity> products;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_scanner, container, false);
        barcodeReader = (BarcodeReader) getChildFragmentManager().findFragmentById(R.id.barcode_fragment);
        frag = this;
        DataBasee db = DataBasee.getDb(getActivity());
        products = db.mAppDao().getAllProducts();
        barcodeReader.setListener(new BarcodeReader.BarcodeReaderListener() {
            @Override
            public void onScanned(Barcode barcode) {
                Bundle bundle = new Bundle();
                bundle.putString("itemId", Integer.toString(products.get(0).getId()));
                NavHostFragment.findNavController(getParentFragment()).navigate(R.id.productFragment, bundle);
            }

            @Override
            public void onScannedMultiple(List<Barcode> list) {

            }

            @Override
            public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

            }

            @Override
            public void onScanError(String s) {

            }

            @Override
            public void onCameraPermissionDenied() {
                Toast.makeText(getActivity().getApplicationContext(), "Camera permission denied!", Toast.LENGTH_LONG).show();
            }
        });
        return rootView;
    }
}*/
