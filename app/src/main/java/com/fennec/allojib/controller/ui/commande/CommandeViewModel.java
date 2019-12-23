package com.fennec.allojib.controller.ui.commande;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CommandeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CommandeViewModel()
    {
        mText = new MutableLiveData<>();
        mText.setValue("This is Commande fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
