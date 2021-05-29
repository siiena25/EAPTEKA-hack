package com.eapteka.eaptekatests.test_models;

import java.util.ArrayList;

public class Question {
    public String title; //"Срок годности"
    public String desc; //Сколько лет/дней/месяцев?
    public QuestionType type;
    public String correctVariant; //3
    public ArrayList<String> variants = new ArrayList<>(); //[1, 2, 3, 100]
}
