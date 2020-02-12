package com.example.proyectoandroid.models;

import java.util.List;

public class QuestionTags {

    private Question quest;
    private List<Tag> tags;

    public Question getQuest() {
        return quest;
    }

    public void setQuest(Question quest) {
        this.quest = quest;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
