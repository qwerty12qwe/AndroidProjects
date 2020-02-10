package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.proyectoandroid.models.Tag;

import java.util.ArrayList;
import java.util.List;

public class MakeQuestion extends AppCompatActivity {

    private RecyclerView reci;
    private ReciclerViewAdapterTagSelector tags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_question);

        reci = findViewById(R.id.question_tags_selector);
        reci.setLayoutManager(new LinearLayoutManager(this));


        List<Tag> tags = new ArrayList<>();
        Tag t = new Tag();
        t.setTitulo("Java");

        tags.add(t);

        t = new Tag();
        t.setTitulo("Python");
        tags.add(t);

        t = new Tag();
        t.setTitulo("Python");
        tags.add(t);

        t = new Tag();
        t.setTitulo("Python");
        tags.add(t);

        t = new Tag();
        t.setTitulo("Python");
        tags.add(t);


        this.tags = new ReciclerViewAdapterTagSelector(tags);

        reci.setAdapter(this.tags);

    }
}
