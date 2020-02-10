package com.example.proyectoandroid.API;

import com.example.proyectoandroid.models.LoginState;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginApi {

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginState> postlogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("registro.php")
    Call<LoginState> registrologin(
            @Field("nombre") String nombre,
            @Field("email") String email,
            @Field("password") String password
    );

}
