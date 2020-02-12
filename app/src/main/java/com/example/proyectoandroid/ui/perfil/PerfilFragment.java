package com.example.proyectoandroid.ui.perfil;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoandroid.API.LoginApi;
import com.example.proyectoandroid.Login;
import com.example.proyectoandroid.Nav;
import com.example.proyectoandroid.R;
import com.example.proyectoandroid.ReciclerViewAdapterActividad;
import com.example.proyectoandroid.models.LoginState;
import com.example.proyectoandroid.models.PerfilActividades;
import com.example.proyectoandroid.models.Question;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PerfilFragment extends Fragment implements View.OnClickListener{

    private RecyclerView reci;

    private TextView nombre,descripcion,reputacion;


    private ReciclerViewAdapterActividad recicetiquetas;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_perfil, container, false);

        reci = root.findViewById(R.id.perfil_actividad);
        reci.setLayoutManager(new LinearLayoutManager(getActivity()));

        nombre = root.findViewById(R.id.perfil_nombre);
        descripcion = root.findViewById(R.id.perfil_descripcion);
        reputacion = root.findViewById(R.id.perfil_reputacion);


        loadJSON();

        return root;
    }

    @Override
    public void onClick(View v){
        if (!isOnline()) {
            showLoginError("ERROR DE ACCESO A LA RED");
            return;
        }
    }

    private void loadJSON() {

        Retrofit retrofit = Login.getretrofit();

        LoginApi restClient = retrofit.create(LoginApi.class);

        Call<PerfilActividades> call = restClient.getperfil(Nav.getBund().getString("email"));

        call.enqueue(new Callback<PerfilActividades>() {
            @Override
            public void onResponse(Call<PerfilActividades> call, Response<PerfilActividades> response) {
                PerfilActividades data = response.body();

                nombre.setText(data.getUsuario().getName());
                descripcion.setText(data.getUsuario().getDescription());
                reputacion.setText(""+data.getUsuario().getReputation());

                try {
                    List<Question> q = data.getActividad();
                    recicetiquetas = new ReciclerViewAdapterActividad(q);
                    reci.setAdapter(recicetiquetas);
                }
                catch (Exception e){
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<PerfilActividades> call, Throwable t) {
                Toast.makeText(getActivity(), "error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showLoginError(String error) {
        Toast.makeText(getActivity().getBaseContext(), error, Toast.LENGTH_LONG).show();
    }

    private boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }


}