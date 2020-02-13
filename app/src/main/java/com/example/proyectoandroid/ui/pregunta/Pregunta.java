package com.example.proyectoandroid.ui.pregunta;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoandroid.API.LoginApi;
import com.example.proyectoandroid.Login;
import com.example.proyectoandroid.Nav;
import com.example.proyectoandroid.R;
import com.example.proyectoandroid.ReciclerViewAdapterActividad;
import com.example.proyectoandroid.ReciclerViewAdapterTagSelector;
import com.example.proyectoandroid.models.Tag;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Pregunta extends Fragment implements View.OnClickListener{

    private RecyclerView reci;
    private ReciclerViewAdapterTagSelector tags;
    private Button preguntar;
    private EditText titulo,descripcion;
    private boolean cargado;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_pregunta, container, false);

        reci = root.findViewById(R.id.question_tags_selector2);
        reci.setLayoutManager(new LinearLayoutManager(getActivity()));

        preguntar = root.findViewById(R.id.make_question_btnpreguntar2);
        titulo = root.findViewById(R.id.make_question_titulo2);
        descripcion = root.findViewById(R.id.make_question_descripcion2);
        cargado = false;
        cargarTags();
        preguntar.setOnClickListener(this);

        cargarTags();

        return root;
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
                Toast.makeText(getActivity(), "error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getActivity(), "error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
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
            Toast.makeText(getActivity(), errores.trim(), Toast.LENGTH_SHORT).show();
        return flag;
    }

    @Override
    public void onClick(View v) {
        if (v == preguntar && cargado){
            if (comprobarform()){
                insertarPregunta();
                FragmentManager mana = getActivity().getSupportFragmentManager();
                FragmentTransaction fragTransaction = getFragmentManager().beginTransaction();
                fragTransaction.remove(this);
                fragTransaction.commit();
                mana.popBackStack();
            }
        }
    }

}