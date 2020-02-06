package com.example.proyectoandroid.ui.etiquetas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EtiquetasViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EtiquetasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}