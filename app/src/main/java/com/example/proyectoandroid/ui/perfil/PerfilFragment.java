package com.example.proyectoandroid.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoandroid.R;
import com.example.proyectoandroid.ReciclerViewAdapterTag;
import com.example.proyectoandroid.models.Tag;
import com.google.android.material.tabs.TabItem;

import java.util.ArrayList;
import java.util.List;

public class PerfilFragment extends Fragment implements View.OnClickListener{

    private PerfilViewModel perfilViewModel;
    private RecyclerView reci;
    private View r;
    private TabItem actividad;

    private ReciclerViewAdapterTag recicetiquetas;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        perfilViewModel =
                ViewModelProviders.of(this).get(PerfilViewModel.class);
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);

//        reci = root.findViewById(R.id.asd);
        reci.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<Tag> tags = new ArrayList<Tag>();
        Tag t = new Tag();
        t.setTitulo("tag 1");
        t.setDescripcion("Descripcion 1 Descripcion 1 Descripcion 1 Descripcion 1 Descripcion 1 Descripcion 1 Descripcion 1 Descripcion 1 Descripcion 1 Descripcion 1 Descripcion 1 Descripcion 1 ");

        tags.add(t);
        t = new Tag();
        t.setTitulo("tag 2");
        t.setDescripcion("Descripcion 2 Descripcion 2 Descripcion 2 Descripcion 1 Descripcion 1 Descripcion 1 Descripcion 1 Descripcion 1 Descripcion 1 Descripcion 1 Descripcion 1 Descripcion 1 ");
        tags.add(t);

        recicetiquetas = new ReciclerViewAdapterTag(tags);

        reci.setAdapter(recicetiquetas);

        return root;
    }

    @Override
    public void onClick(View v){
        if (actividad == v){
            reci.setVisibility(View.GONE);
        }
    }

}