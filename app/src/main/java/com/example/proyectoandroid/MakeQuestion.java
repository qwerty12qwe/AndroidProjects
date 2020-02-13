package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectoandroid.API.LoginApi;
import com.example.proyectoandroid.models.Question;
import com.example.proyectoandroid.models.QuestionTags;
import com.example.proyectoandroid.models.Tag;
import com.example.proyectoandroid.models.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MakeQuestion extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView reci;
    private ReciclerViewAdapterTagSelector tags;
    private Button preguntar;
    private EditText titulo,descripcion;
    private boolean cargado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_question);

        reci = findViewById(R.id.question_tags_selector);
        reci.setLayoutManager(new LinearLayoutManager(this));

        preguntar = findViewById(R.id.make_question_btnpreguntar);
        titulo = findViewById(R.id.make_question_titulo);
        descripcion = findViewById(R.id.make_question_descripcion);
        cargado = false;
        cargarTags();
        preguntar.setOnClickListener(this);
    }

    private void cargarTags() {

        Retrofit retrofit = Login.getretrofit();

        LoginApi restClient = retrofit.create(LoginApi.class);
        Call<List<Tag>> call = restClient.gettags();

        call.enqueue(new Callback<List<Tag>>() {
            @Override
            public void onResponse(Call<List<Tag>> call, Response<List<Tag>> response) {
                List<Tag> data = response.body();
                tags = new ReciclerViewAdapterTagSelector(data);
                reci.setAdapter(tags);
                cargado = true;
            }

            @Override
            public void onFailure(Call<List<Tag>> call, Throwable t) {
                Toast.makeText(MakeQuestion.this, "error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void insertarPregunta(){

        Retrofit retrofit = Login.getretrofit();

        LoginApi restClient = retrofit.create(LoginApi.class);

        String titulo = this.titulo.getText().toString().trim();
        String descripcion = this.descripcion.getText().toString().trim();
        String usuario = Nav.getBund().getString("email");


        Call<List<Tag>> call = restClient.setPregunta(usuario,titulo,descripcion,tags.idsseleccionados);

        call.enqueue(new Callback<List<Tag>>() {
            @Override
            public void onResponse(Call<List<Tag>> call, Response<List<Tag>> response) {
                List<Tag> data = response.body();
            }

            @Override
            public void onFailure(Call<List<Tag>> call, Throwable t) {
                Toast.makeText(MakeQuestion.this, "error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    public boolean comprobarform(){
        boolean flag = true;
        String errores = "";
        String titulo = this.titulo.getText().toString().trim();
        if (titulo.isEmpty()){
            flag = false;
            errores = "El titulo no puede estar vacio\n";
        }

        if (tags.idsseleccionados.size() == 0){
            flag = false;
            errores = "Selecciona almenos un tag!";
        }

        if (!errores.isEmpty())
            Toast.makeText(this, errores.trim(), Toast.LENGTH_SHORT).show();
        return flag;
    }

    @Override
    public void onClick(View v) {
        if (v == preguntar && cargado){
            if (comprobarform()){
                insertarPregunta();
                finish();
            }
        }
    }
}
