package com.example.proyectoandroid.models;

import java.util.List;

public class UsuarioTag {

    private User user;
    private List<Tag> tags;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}