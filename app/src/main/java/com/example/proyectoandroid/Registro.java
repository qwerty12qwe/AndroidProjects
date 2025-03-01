package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.proyectoandroid.API.LoginApi;
import com.example.proyectoandroid.models.LoginState;
import com.example.proyectoandroid.models.Tag;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Registro extends AppCompatActivity implements View.OnClickListener{

    private Button registrarse;
    private EditText nombre,email,contra,recontra;

    private ImageView img;

    private ReciclerViewAdapterTagSelector selector;
    private RecyclerView reci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        registrarse = findViewById(R.id.btnregistrarse);
        img = findViewById(R.id.registro_volver);
        nombre = findViewById(R.id.registro_name);
        email = findViewById(R.id.registro_email);
        contra = findViewById(R.id.registro_passwd);
        recontra = findViewById(R.id.registro_passwdre);


        reci = findViewById(R.id.registro_tags);
        reci.setLayoutManager(new LinearLayoutManager(this));

        leerTags();

        img.setOnClickListener(this);
        registrarse.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        if (v == registrarse){
            if (validarform()){
                loadJSON();
            }
        }
        else if(v == img){
            Intent it = new Intent(this,Login.class);
            startActivity(it);
            finish();
        }
    }

    private void loadJSON() {

        Retrofit retrofit = Login.getretrofit();

        LoginApi restClient = retrofit.create(LoginApi.class);

        String [] form = {nombre.getText().toString().trim(),email.getText().toString().trim(),contra.getText().toString()};

        Call<LoginState> call = restClient.registrologin(form[0],form[1],form[2],selector.idsseleccionados);

        call.enqueue(new Callback<LoginState>() {

            @Override
            public void onResponse(Call<LoginState> call, Response<LoginState> response) {
                LoginState d = response.body();
                if (d.getEstado() == 1){
                    Intent it = new Intent(Registro.this,Login.class);
                    startActivity(it);
                    finish();
                }
                else
                    Toast.makeText(Registro.this, "Usuario con email "+email.getText().toString()+" ya registrado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LoginState> call, Throwable t) {
                Toast.makeText(Registro.this, "error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void leerTags(){
        Retrofit retrofit = Login.getretrofit();

        LoginApi restClient = retrofit.create(LoginApi.class);

        Call<List<Tag>> call = restClient.gettags();

        call.enqueue(new Callback<List<Tag>>() {

            @Override
            public void onResponse(Call<List<Tag>> call, Response<List<Tag>> response) {
                List<Tag> d = response.body();
                selector = new ReciclerViewAdapterTagSelector(d);
                reci.setAdapter(selector);
            }
            @Override
            public void onFailure(Call<List<Tag>> call, Throwable t) {
                Toast.makeText(Registro.this, "error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public boolean validarform(){

        String [] form = {nombre.getText().toString().trim(),email.getText().toString().trim(),contra.getText().toString(),};

        String [] campos = {"nombre","email","contraseña"};

        String errores = "";
        boolean flag = true;

        for (int i = 0; i < form.length; i++){
            if (form[i].isEmpty()){
                errores += "Campo "+campos[i]+" esta vacio\n";
                flag = false;
            }
        }

        String reg = "\\w{3,}@\\w{3,}.\\w{2,4}";
        Pattern patron = Pattern.compile(reg);
        Matcher encaja = patron.matcher(form[1]);

        if (!encaja.find()){
            flag = false;
            errores+="Introduce un email valido\n";
        }

        if (!recontra.getText().toString().equals(form[2])){
            flag = false;
            errores+="Las contraseñas no son iguales\n";
        }

        if (selector.idsseleccionados.size() == 0){
            flag = false;
            errores+="Selecciona almenos una etiqueta\n";
        }

        if (!errores.isEmpty())
            Toast.makeText(this, errores, Toast.LENGTH_SHORT).show();

        return flag;
    }
}
