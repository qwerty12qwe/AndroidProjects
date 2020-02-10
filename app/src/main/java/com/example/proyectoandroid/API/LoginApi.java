package com.example.proyectoandroid.API;

import com.example.proyectoandroid.models.LoginState;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginApi {

    @POST("login.php")
    Call<LoginState> postlogin(
            @Query("email") String email,
            @Query("password") String password
    );



}
