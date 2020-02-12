package com.example.proyectoandroid.ui.usuarios;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoandroid.API.LoginApi;
import com.example.proyectoandroid.Login;
import com.example.proyectoandroid.Nav;
import com.example.proyectoandroid.R;
import com.example.proyectoandroid.ReciclerViewAdapterUser;
import com.example.proyectoandroid.Registro;
import com.example.proyectoandroid.models.LoginState;
import com.example.proyectoandroid.models.User;
import com.example.proyectoandroid.models.UsuarioTag;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UsuariosFragment extends Fragment implements  View.OnClickListener{

    private RecyclerView mostrarlista;
    private ReciclerViewAdapterUser adapterusers;

    private String type;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_usuarios, container, false);


        mostrarlista = root.findViewById(R.id.lista_de_usuarios);
        mostrarlista.setLayoutManager(new LinearLayoutManager(getActivity()));

        type = "reputation";
        loadJSON();
        return root;
    }


    @Override
    public void onClick(View v) {

        if (!isOnline()) {
            showLoginError("ERROR DE ACCESO A LA RED");
            return;
        }
    }

    private void loadJSON() {

        Retrofit retrofit = Login.getretrofit();

        LoginApi restClient = retrofit.create(LoginApi.class);

        Call<List<UsuarioTag>> call = restClient.getusuarios(type);

        call.enqueue(new Callback<List<UsuarioTag>>() {
            @Override
            public void onResponse(Call<List<UsuarioTag>> call, Response<List<UsuarioTag>> response) {
                List<UsuarioTag> data = response.body();

                adapterusers = new ReciclerViewAdapterUser(data);
                mostrarlista.setAdapter(adapterusers);
            }

            @Override
            public void onFailure(Call<List<UsuarioTag>> call, Throwable t) {
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