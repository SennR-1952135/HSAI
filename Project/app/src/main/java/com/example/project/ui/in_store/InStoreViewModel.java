package com.example.project.ui.in_store;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InStoreViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public InStoreViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Naam winkel");
    }

    public LiveData<String> getText() {
        return mText;
    }
}