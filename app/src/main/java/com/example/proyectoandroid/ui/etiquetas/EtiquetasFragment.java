package com.example.proyectoandroid.ui.etiquetas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoandroid.R;
import com.example.proyectoandroid.ReciclerViewAdapterTag;
import com.example.proyectoandroid.models.Tag;

import java.util.ArrayList;
import java.util.List;

public class EtiquetasFragment extends Fragment {

    private EtiquetasViewModel etiquetasViewModel;
    private ReciclerViewAdapterTag recicetiquetas;
    private RecyclerView etiquetas;
    private EditText buscador;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        etiquetasViewModel =
                ViewModelProviders.of(this).get(EtiquetasViewModel.class);
        View root = inflater.inflate(R.layout.fragment_etiquetas, container, false);


        etiquetas = root.findViewById(R.id.etiquetas);
        etiquetas.setLayoutManager(new LinearLayoutManager(getActivity()));

        buscador = root.findViewById(R.id.buscador_etiquetas);

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

        etiquetas.setAdapter(recicetiquetas);

        return root;
    }
}