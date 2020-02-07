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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoandroid.R;
import com.example.proyectoandroid.ReciclerViewAdapterUser;
import com.example.proyectoandroid.models.User;

import java.util.ArrayList;
import java.util.List;

public class UsuariosFragment extends Fragment {

    private UsuariosViewModel usuariosViewModel;
    private RecyclerView mostrarlista;
    private ReciclerViewAdapterUser adapterusers;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        usuariosViewModel =
                ViewModelProviders.of(this).get(UsuariosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_usuarios, container, false);


        mostrarlista = root.findViewById(R.id.lista_de_usuarios);
        mostrarlista.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<User> users = new ArrayList<>();
        User u = new User();
        u.setNombre("Nombre 1");
        u.setReputacion(100);

        users.add(u);

        u = new User();
        u.setNombre("Luis");
        u.setReputacion(12345);
        users.add(u);

        adapterusers = new ReciclerViewAdapterUser(users);
        mostrarlista.setAdapter(adapterusers);


        return root;
    }
}