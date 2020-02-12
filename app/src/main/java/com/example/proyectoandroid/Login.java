package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

    //    411I5hM9zT


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

        SharedPreferences prefs = getSharedPreferences("ficherologin",Context.MODE_PRIVATE);
        if (prefs.getAll().size() != 0){
            temail = prefs.getString("email","defecto");
            tpassword = prefs.getString("password","defecto");
            loadJSON();
        }

    }


    @Override
    public void onClick(View v) {

        if (but == v) {

            if (!isOnline()) {
                showLoginError("ERROR DE ACCESO A LA RED");
                return;
            }
            else{
                temail = email.getText().toString();
                tpassword = password.getText().toString();
                loadJSON();
            }

        } else if (crearcuenta == v) {
            Intent it = new Intent(this, Registro.class);
            startActivity(it);
            finish();
        }
    }

    public static Retrofit getretrofit(){
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setDateFormat("yyyy-MM-dd")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MYURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit;
    }
    private void loadJSON() {

        Retrofit retrofit = getretrofit();

        LoginApi restClient = retrofit.create(LoginApi.class);
        Call<LoginState> call = restClient.postlogin(temail, tpassword);

        call.enqueue(new Callback<LoginState>() {
            @Override
            public void onResponse(Call<LoginState> call, Response<LoginState> response) {
                    LoginState data = response.body();

                    switch(data.getEstado()){

                        case 1:

                            if (recordar.isChecked()){
                                SharedPreferences prefs = getSharedPreferences("ficherologin",Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = prefs.edit();
                                editor.putString("email", data.getUsuario().get(0).getEmail());
                                editor.putString("password",tpassword);
                                editor.commit();
                            }

                            Bundle b = new Bundle();
                            b.putString("nombre",data.getUsuario().get(0).getName());
                            b.putString("email",data.getUsuario().get(0).getEmail());

                            Intent it = new Intent(Login.this, Nav.class);
                            it.putExtras(b);
                            startActivity(it);

                            finish();
                            break;
                        case 2:
                            Toast.makeText(Login.this, "Este usuario no esta registrado", Toast.LENGTH_SHORT).show();
                            break;

                    }
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