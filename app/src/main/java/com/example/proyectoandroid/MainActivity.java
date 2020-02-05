package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.proyectoandroid.models.Question;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recicler;
    private ReciclerViewAdapter adapter;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recicler = findViewById(R.id.preguntas);
        recicler.setLayoutManager(new LinearLayoutManager(this));

        List<Question> preguntas = new ArrayList<>();
        Question q = new Question();
        q.setTitulo("PRimer titulo");
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
    }

}
