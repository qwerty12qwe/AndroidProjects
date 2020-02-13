package com.example.proyectoandroid.models;

import com.google.gson.annotations.SerializedName;

public class Question {

    private int id;
    private int visites; // cantidad de visitas
    private int votes; // cantidad de votos
    private int responses; // cantidad de respuestas
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("user")
    private String user;
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVisites() {
        return visites;
    }

    public void setVisites(int visites) {
        this.visites = visites;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public int getResponses() {
        return responses;
    }

    public void setResponses(int responses) {
        this.responses = responses;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
