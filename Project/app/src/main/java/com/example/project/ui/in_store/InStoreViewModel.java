package com.example.project.ui.in_store;

import android.widget.ImageButton;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InStoreViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private ImageButton btnQueueCheckout;
    private ImageButton btnQueueFitting;

    public InStoreViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Naam winkel");
    }

    public LiveData<String> getText() {
        return mText;
    }
}