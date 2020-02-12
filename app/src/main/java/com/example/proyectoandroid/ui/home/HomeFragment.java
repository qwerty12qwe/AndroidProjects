package com.example.proyectoandroid.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoandroid.API.LoginApi;
import com.example.proyectoandroid.Login;
import com.example.proyectoandroid.MakeQuestion;
import com.example.proyectoandroid.Nav;
import com.example.proyectoandroid.R;
import com.example.proyectoandroid.ReciclerViewAdapterQuestion;
import com.example.proyectoandroid.models.LoginState;
import com.example.proyectoandroid.models.Question;
import com.example.proyectoandroid.models.QuestionTags;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment implements  View.OnClickListener{


    private RecyclerView recicler;
    private ReciclerViewAdapterQuestion adapter;
    private Spinner filtros;
    private Button preguntar;

    private String order;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recicler = root.findViewById(R.id.preguntas);
        recicler.setLayoutManager(new LinearLayoutManager(getActivity()));

        filtros = root.findViewById(R.id.filtros);
        preguntar = root.findViewById(R.id.btnpreguntar);

        preguntar.setOnClickListener(this);

        order = "";

        String [] values = {"Filtro1","Filtro2"};
        filtros.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_spinner_item,values));

        loadJSON();

        return root;
    }

    @Override
    public void onClick(View v){
        if (v == preguntar){
            Intent it = new Intent(getActivity(), MakeQuestion.class);
            startActivity(it);
        }


    }

    private void loadJSON() {

        Retrofit retrofit = Login.getretrofit();

        LoginApi restClient = retrofit.create(LoginApi.class);
        Call<List<QuestionTags>> call = restClient.getPreguntas(order,Nav.getBund().getString("email"));

        call.enqueue(new Callback<List<QuestionTags>>() {
            @Override
            public void onResponse(Call<List<QuestionTags>> call, Response<List<QuestionTags>> response) {
                List<QuestionTags> d= response.body();

                adapter = new ReciclerViewAdapterQuestion(d);
                recicler.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<QuestionTags>> call, Throwable t) {
                Toast.makeText(getActivity(), "error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }

}