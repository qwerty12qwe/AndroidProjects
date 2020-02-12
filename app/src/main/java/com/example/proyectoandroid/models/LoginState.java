package com.example.proyectoandroid.models;

import java.util.List;

public class LoginState {

    private Integer estado;
    private List<User> usuario;

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public List<User> getUsuario() {
        return usuario;
    }

    public void setUsuario(List<User> usuario) {
        this.usuario = usuario;
    }

}


