package com.example.proyectoandroid.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoandroid.R;
import com.example.proyectoandroid.ReciclerViewAdapter;
import com.example.proyectoandroid.models.Question;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private RecyclerView recicler;
    private ReciclerViewAdapter adapter;
    private Spinner filtros;
    private Button preguntar;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);



        recicler = root.findViewById(R.id.preguntas);
        recicler.setLayoutManager(new LinearLayoutManager(getActivity()));

        filtros = root.findViewById(R.id.filtros);
        preguntar = root.findViewById(R.id.btnpreguntar);

        List<Question> preguntas = new ArrayList<>();
        Question q = new Question();
        q.setTitulo("Primer titulo");
        q.setVisitas(100);
        q.setRespuestas(1);
        q.setVotos(29);
        preguntas.add(q);

        q = new Question();
        q.setTitulo("Segundo titulo");
        q.setVisitas(100);
        q.setRespuestas(24);
        q.setVotos(58);
        preguntas.add(q);

        adapter = new ReciclerViewAdapter(preguntas);
        recicler.setAdapter(adapter);

        String [] values = {"Filtro1","Filtro2"};
        filtros.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_spinner_item,values));


        return root;
    }
}