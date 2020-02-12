package com.example.proyectoandroid.ui.etiquetas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoandroid.API.LoginApi;
import com.example.proyectoandroid.Login;
import com.example.proyectoandroid.Nav;
import com.example.proyectoandroid.R;
import com.example.proyectoandroid.ReciclerViewAdapterTag;
import com.example.proyectoandroid.models.LoginState;
import com.example.proyectoandroid.models.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EtiquetasFragment extends Fragment implements View.OnKeyListener {

    private ReciclerViewAdapterTag recicetiquetas;
    private RecyclerView etiquetas;
    private EditText buscador;
    private List<Tag> data;
    private View root;

    private boolean cargado;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_etiquetas, container, false);

        this.root = root;

        etiquetas = root.findViewById(R.id.etiquetas);
        etiquetas.setLayoutManager(new LinearLayoutManager(getActivity()));

        cargado = false;

        buscador = root.findViewById(R.id.buscador_etiquetas);
        buscador.setOnKeyListener(this);

        loadJSON();

        return root;
    }

    private void loadJSON() {

        Retrofit retrofit = Login.getretrofit();

        LoginApi restClient = retrofit.create(LoginApi.class);
        Call<List<Tag>> call = restClient.gettags();

        call.enqueue(new Callback<List<Tag>>() {
            @Override
            public void onResponse(Call<List<Tag>> call, Response<List<Tag>> response) {
                data = response.body();

                recicetiquetas = new ReciclerViewAdapterTag(data);
                etiquetas.setAdapter(recicetiquetas);

                cargado = true;
            }

            @Override
            public void onFailure(Call<List<Tag>> call, Throwable t) {
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        if (cargado){
            if (keyCode == KeyEvent.KEYCODE_ENTER){
                List<Tag> busqueda = data.stream().filter(x -> x.getTitulo().contains(buscador.getText().toString())).collect(Collectors.toList());

                recicetiquetas = new ReciclerViewAdapterTag(busqueda);
                etiquetas.setAdapter(recicetiquetas);
            }
        }
        return false;
    }

}