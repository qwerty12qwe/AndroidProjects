package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectoandroid.API.LoginApi;
import com.example.proyectoandroid.models.LoginState;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button but;
    private TextView crearcuenta, contrasena_olvidada;
    private EditText email, password;
    private CheckBox recordar;

    private String temail, tpassword;

    public static final String MYURL = "https://pruebabasesexternas.000webhostapp.com/stack/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        but = findViewById(R.id.iniciarsesion);
        crearcuenta = findViewById(R.id.btncrearcuenta);

        contrasena_olvidada = findViewById(R.id.login_recuperar_pass);
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        recordar = findViewById(R.id.login_recordar);


        but.setOnClickListener(this);
        crearcuenta.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        if (but == v) {

            if (!isOnline()) {
                showLoginError("ERROR DE ACCESO A LA RED");
                return;
            }
            else{
                loadJSON();
            }

        } else if (crearcuenta == v) {
            Intent it = new Intent(this, Registro.class);
            startActivity(it);
        }
    }

    private void loadJSON() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MYURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        LoginApi restClient = retrofit.create(LoginApi.class);

        Call<LoginState> call = restClient.postlogin(temail, tpassword);

        call.enqueue(new Callback<LoginState>() {
            @Override
            public void onResponse(Call<LoginState> call, Response<LoginState> response) {
                    LoginState data = response.body();
                    Toast.makeText(Login.this, data.getUsuario().getName(), Toast.LENGTH_SHORT).show();

//                    Bundle b = new Bundle();
//                    Intent it = new Intent(Login.this, Nav.class);
//                    startActivity(it);
            }

            @Override
            public void onFailure(Call<LoginState> call, Throwable t) {
                Toast.makeText(Login.this, "error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showLoginError(String error) {
        Toast.makeText(getBaseContext(), error, Toast.LENGTH_LONG).show();
    }

    private boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }
}