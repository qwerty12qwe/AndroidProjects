package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class Splash extends AppCompatActivity {

    MediaPlayer pool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        pool = MediaPlayer.create(this, R.raw.descarga_electrica);
        pool.start();

        YoYo.with(Techniques.Shake)
                .duration(300)
                .repeat(10)
                .playOn(findViewById(R.id.imagen_splash));

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Handler().postDelayed(()->{
            pool.stop();
            Intent run = new Intent(Splash.this,Login.class);
            startActivity(run);
            finish();
        },3000);


    }

}
