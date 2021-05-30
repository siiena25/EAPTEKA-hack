package com.eapteka.eaptekatests.test_models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Question {

    @SerializedName("title")
    public String title; //"Срок годности"

    @SerializedName("description")
    public String desc; //Сколько лет/дней/месяцев?

    @SerializedName("Question Type")
    public QuestionType type;

    @SerializedName("correct answer")
    public String correctVariant; //3

    @SerializedName("variants")
    public ArrayList<String> variants = new ArrayList<>(); //[1, 2, 3, 100]

    public Question(String title, String desc, QuestionType type, String correctVariant, ArrayList<String> variants) {
        this.title = title;
        this.desc = desc;
        this.type = type;
        this.correctVariant = correctVariant;
        this.variants = variants;
    }
}
