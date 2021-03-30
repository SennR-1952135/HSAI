package com.example.project.ui.in_store;

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

public class InStoreFragment extends Fragment {

    private InStoreViewModel inStoreViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        inStoreViewModel =
                new ViewModelProvider(this).get(InStoreViewModel.class);
        View root = inflater.inflate(R.layout.fragment_in_store, container, false);
        final TextView textView = root.findViewById(R.id.text_in_store_store_name);
        inStoreViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}