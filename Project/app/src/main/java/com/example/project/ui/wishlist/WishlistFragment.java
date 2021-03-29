package com.example.project.ui.wishlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.project.R;

public class WishlistFragment extends Fragment {

    private WishlistViewModel WishlistViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        WishlistViewModel =
                new ViewModelProvider(this).get(WishlistViewModel.class);
        View root = inflater.inflate(R.layout.fragment_wishlist, container, false);
        final TextView textView = root.findViewById(R.id.text_wishlist);
        WishlistViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
