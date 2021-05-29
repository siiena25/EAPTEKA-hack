package com.eapteka.eaptekatests.test_models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Test {
    @SerializedName("Questions")
    private ArrayList<Question> questions = new ArrayList<>();

    @SerializedName("coins count")
    private Integer coinsCount;  //500 coins

    @SerializedName("discount")
    private Integer discount; //12%

    @SerializedName("title")
    private String title; //Тест на знания Но-шпа

    public void addNewQuestion(Question question) {
        questions.add(question);
    }

    public int getCountOfQuestions() {
        return questions.size();
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public Integer getCoinsCount() {
        return coinsCount;
    }

    public Integer getDiscount() {
        return discount;
    }

    public String getTitle() {
        return title;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public void setCoinsCount(Integer coinsCount) {
        this.coinsCount = coinsCount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
