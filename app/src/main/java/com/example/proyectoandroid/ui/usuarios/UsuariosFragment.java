package com.example.proyectoandroid.ui.usuarios;

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

public class UsuariosFragment extends Fragment {

    private UsuariosViewModel usuariosViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        usuariosViewModel =
                ViewModelProviders.of(this).get(UsuariosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_usuarios, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        usuariosViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}