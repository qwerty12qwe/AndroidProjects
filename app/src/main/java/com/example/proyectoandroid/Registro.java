package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Registro extends AppCompatActivity implements View.OnClickListener{

    private Button registrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        registrarse = findViewById(R.id.btnregistrarse);

        registrarse.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if (v == registrarse){
            Intent it = new Intent(this,Login.class);
            startActivity(it);
        }
    }
}
