package com.fennec.allojib.controller.ui.panier;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PanierViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PanierViewModel()
    {
        mText = new MutableLiveData<>();
        mText.setValue("This is Commande fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}