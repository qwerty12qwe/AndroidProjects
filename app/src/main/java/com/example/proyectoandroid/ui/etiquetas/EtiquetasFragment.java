package com.example.proyectoandroid.ui.etiquetas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.proyectoandroid.R;

public class EtiquetasFragment extends Fragment {

    private EtiquetasViewModel etiquetasViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        etiquetasViewModel =
                ViewModelProviders.of(this).get(EtiquetasViewModel.class);
        View root = inflater.inflate(R.layout.fragment_etiquetas, container, false);





        return root;
    }
}