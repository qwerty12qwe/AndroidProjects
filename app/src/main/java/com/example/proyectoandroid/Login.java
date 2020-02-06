package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity implements View.OnClickListener{

    Button but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        but = findViewById(R.id.iniciarsesion);
        but.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent it = new Intent(this,Nav.class);
        startActivity(it);
    }
}
