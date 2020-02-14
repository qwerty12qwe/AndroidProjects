package com.example.proyectoandroid.models;

import java.util.List;

public class QuestionResponses {

    private Question quest;
    private List<Question> responses;

    public Question getQuest() {
        return quest;
    }

    public void setQuest(Question quest) {
        this.quest = quest;
    }

    public List<Question> getResponses() {
        return responses;
    }

    public void setResponses(List<Question> responses) {
        this.responses = responses;
    }
}
