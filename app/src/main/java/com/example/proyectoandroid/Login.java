package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private Button but;
    private TextView crearcuenta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        but = findViewById(R.id.iniciarsesion);
        crearcuenta = findViewById(R.id.btncrearcuenta);

        but.setOnClickListener(this);
        crearcuenta.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        if (but == v){
            Intent it = new Intent(this,Nav.class);
            startActivity(it);
        }
        else if(crearcuenta == v){
            Intent it = new Intent(this,Registro.class);
            startActivity(it);
        }
    }
}
