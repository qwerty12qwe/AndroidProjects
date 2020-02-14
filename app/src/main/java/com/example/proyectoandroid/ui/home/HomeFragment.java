package com.example.proyectoandroid.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoandroid.API.LoginApi;
import com.example.proyectoandroid.Login;
import com.example.proyectoandroid.Nav;
import com.example.proyectoandroid.R;
import com.example.proyectoandroid.ReciclerViewAdapterQuestion;
import com.example.proyectoandroid.models.Question;
import com.example.proyectoandroid.models.QuestionTags;
import com.example.proyectoandroid.ui.pregunta.Pregunta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment implements  View.OnClickListener{


    private RecyclerView recicler;
    private ReciclerViewAdapterQuestion adapter;
    private Spinner filtros;
    private Button preguntar,pagnuevos,pagantiguos;

    private String order;
    private int paginacion;

    private List<QuestionTags> listapreguntas;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recicler = root.findViewById(R.id.preguntas);

        filtros = root.findViewById(R.id.filtros);
        preguntar = root.findViewById(R.id.btnpreguntar);

        paginacion = 0;
        pagnuevos = root.findViewById(R.id.home_fragment_nuevos);
        pagantiguos = root.findViewById(R.id.home_fragment_antiguos);

        pagnuevos.setOnClickListener(this);
        pagantiguos.setOnClickListener(this);

        recicler.setLayoutManager(new LinearLayoutManager(getActivity()));
        order = "interesting";
        loadJSON();

        String [] values = {"De interes","Lo mejor"};
        filtros.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_spinner_item,values));

        preguntar.setOnClickListener(this);
        filtros.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        order = "interesting";
                        break;
                    case 1:
                        order = "hot";
                        break;
                }
                loadJSON();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return root;
    }

    @Override
    public void onClick(View v){
        if (v == preguntar){
            Pregunta secFrag = new Pregunta();
            FragmentTransaction fragTransaction = getFragmentManager().beginTransaction();
            fragTransaction.replace(R.id.nav_host_fragment,secFrag);
            fragTransaction.addToBackStack(null);
            fragTransaction.commit();
        }
        else if(v == pagnuevos || v == pagantiguos){
            if(v == pagantiguos){
                paginacion++;
            }
            else{
                paginacion--;
            }

            setPreguntas();
        }


    }

    private void loadJSON() {

        Retrofit retrofit = Login.getretrofit();

        LoginApi restClient = retrofit.create(LoginApi.class);
        Call<List<QuestionTags>> call = restClient.getPreguntas(order,Nav.getBund().getString("email"));

        call.enqueue(new Callback<List<QuestionTags>>() {
            @Override
            public void onResponse(Call<List<QuestionTags>> call, Response<List<QuestionTags>> response) {
                listapreguntas = response.body();
                setPreguntas();
            }

            @Override
            public void onFailure(Call<List<QuestionTags>> call, Throwable t) {
                Toast.makeText(getActivity(), "error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void setPreguntas(){
        int cont = paginacion * 10;


        List<QuestionTags> questions = new ArrayList<>();
        for (int i = cont;i < cont+10; i++){
            if (i < listapreguntas.size()){
                questions.add(listapreguntas.get(i));
            }
            else{
                break;
            }
        }

        if (paginacion == 0)
            pagnuevos.setEnabled(false);
        else
            pagnuevos.setEnabled(true);

        if (cont +10  > listapreguntas.size()){
            pagantiguos.setEnabled(false);
        }
        else{
            pagantiguos.setEnabled(true);
        }


        adapter = new ReciclerViewAdapterQuestion(questions,HomeFragment.this);
        recicler.setAdapter(adapter);
    }

}