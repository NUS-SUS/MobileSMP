package com.example.mobilesmp.ui.campaign;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CampaignViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CampaignViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is campaign fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}