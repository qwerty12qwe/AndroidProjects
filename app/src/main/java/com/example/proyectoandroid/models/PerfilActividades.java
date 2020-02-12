package com.example.proyectoandroid.models;

import java.util.List;

public class PerfilActividades {

    private User usuario;
    private List<Question> actividad;

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public List<Question> getActividad() {
        return actividad;
    }

    public void setActividad(List<Question> actividad) {
        this.actividad = actividad;
    }
}
