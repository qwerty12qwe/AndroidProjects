package com.example.proyectoandroid.ui.pregunta_respuestas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import com.example.proyectoandroid.ReciclerViewAdapterResponses;
import com.example.proyectoandroid.models.QuestionResponses;
import com.example.proyectoandroid.models.Tag;
import com.example.proyectoandroid.ui.home.HomeFragment;
import com.example.proyectoandroid.ui.pregunta.Pregunta;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PreguntaRespuestas extends Fragment implements View.OnClickListener{
    private int idPregunta;
    private TextView titulo,descripcion,n_votos,usuario,n_respuestas;
    private Button upvote,downvote,enviarresp;
    private EditText input;

    private ReciclerViewAdapterResponses respuestas;
    private RecyclerView lay_resp;

    public PreguntaRespuestas(int idPregunta){
        this.idPregunta = idPregunta;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_pregunta_respuestas, container, false);

        titulo = root.findViewById(R.id.pregunta_respuestas_titulo);
        descripcion = root.findViewById(R.id.pregunta_respuestas_cuerpo);
        n_votos = root.findViewById(R.id.pregunta_respuestas_votos);
        n_respuestas = root.findViewById(R.id.pregunta_respuesta_n_respuestas);
        input = root.findViewById(R.id.pregunta_respuestas_input_respuesta);

        upvote = root.findViewById(R.id.pregunta_respuestas_upvote);
        downvote = root.findViewById(R.id.pregunta_respuestas_downvote);
        enviarresp = root.findViewById(R.id.pregunta_respuestas_btnenviar);

        lay_resp = root.findViewById(R.id.pregunta_respuestas_respuestas);
        lay_resp.setLayoutManager(new LinearLayoutManager(getActivity()));

        upvote.setOnClickListener(this);
        downvote.setOnClickListener(this);
        enviarresp.setOnClickListener(this);
        cargarFrag();



        return root;
    }
    private void cargarFrag() {

        Retrofit retrofit = Login.getretrofit();

        LoginApi restClient = retrofit.create(LoginApi.class);
        Call<QuestionResponses> call = restClient.getRespuestas(idPregunta,Nav.getBund().getString("email"));

        call.enqueue(new Callback<QuestionResponses>() {
            @Override
            public void onResponse(Call<QuestionResponses> call, Response<QuestionResponses> response) {

                QuestionResponses resp = response.body();

                titulo.setText(resp.getQuest().getTitle());
                descripcion.setText(resp.getQuest().getDescription());
                n_votos.setText(""+resp.getQuest().getVotes());


                // 0 = novote
                // 1 = upvote
                // 2 = downvote
                if (resp.getQuest().getVoted() == 1)
                    upvote.setBackgroundResource(R.drawable.upvotevotado);
                else if(resp.getQuest().getVoted() == 2)
                    downvote.setBackgroundResource(R.drawable.downvotevotado);


                n_respuestas.setText(resp.getResponses().size()+" Respuestas");

                respuestas = new ReciclerViewAdapterResponses(resp.getResponses(),PreguntaRespuestas.this);
                lay_resp.setAdapter(respuestas);
            }

            @Override
            public void onFailure(Call<QuestionResponses> call, Throwable t) {
                Toast.makeText(getActivity(), "error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void insertarRespuesta(){

        Retrofit retrofit = Login.getretrofit();

        LoginApi restClient = retrofit.create(LoginApi.class);

        Call<List<Integer>> call = restClient.setRespuesta(idPregunta,input.getText().toString().trim(), Nav.getBund().getString("email"));
        call.enqueue(new Callback<List<Integer>>() {
            @Override
            public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {
                getFragmentManager().popBackStack();

                PreguntaRespuestas secFrag = new PreguntaRespuestas(idPregunta);

                FragmentTransaction fragTransaction = getFragmentManager().beginTransaction();

                fragTransaction.replace(R.id.nav_host_fragment,secFrag);
                fragTransaction.addToBackStack(null);
                fragTransaction.commit();
            }

            @Override
            public void onFailure(Call<List<Integer>> call, Throwable t) {
                Toast.makeText(getActivity(), "error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void votarTipodePost(int tipodepost,String tipo){

        Retrofit retrofit = Login.getretrofit();

        LoginApi restClient = retrofit.create(LoginApi.class);

        Call<List<Integer>> call = restClient.votar(tipodepost, Nav.getBund().getString("email"),tipo);
        call.enqueue(new Callback<List<Integer>>() {
            @Override
            public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {

                getFragmentManager().popBackStack();

                PreguntaRespuestas secFrag = new PreguntaRespuestas(idPregunta);

                FragmentTransaction fragTransaction = getFragmentManager().beginTransaction();

                fragTransaction.replace(R.id.nav_host_fragment,secFrag);
                fragTransaction.addToBackStack(null);
                fragTransaction.commit();
            }

            @Override
            public void onFailure(Call<List<Integer>> call, Throwable t) {
                Toast.makeText(getActivity(), "error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    public boolean comprobarform(){
        boolean flag = true;
        String errores = "";


        if (input.getText().toString().trim().isEmpty()){
            flag = false;
            errores += "Tu respuesta esta vacia";
        }


        if (!errores.isEmpty())
            Toast.makeText(getActivity(), errores, Toast.LENGTH_SHORT).show();

        return flag;
    }

    @Override
    public void onClick(View v) {
        if (v == enviarresp){
            if (comprobarform()){
                insertarRespuesta();
            }
        }

        else if(v == upvote){
            votarTipodePost(idPregunta,"up");

        }
        else if(v == downvote){
            votarTipodePost(idPregunta,"down");
        }

    }

}