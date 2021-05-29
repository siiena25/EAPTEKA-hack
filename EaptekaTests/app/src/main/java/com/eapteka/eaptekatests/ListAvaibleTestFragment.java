package com.eapteka.eaptekatests;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eapteka.eaptekatests.test_models.Question;
import com.eapteka.eaptekatests.test_models.QuestionType;
import com.eapteka.eaptekatests.test_models.Test;

import java.util.ArrayList;

public class ListAvaibleTestFragment extends BaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_list_avaible_test, container, false);
        RecyclerView finishedTestsList = view.findViewById(R.id.finished_tests_list);
        RecyclerView startedTestsList = view.findViewById(R.id.started_tests_list);
        Test exampleTest = exampleTestInit();
        return view;
    }

    private Test exampleTestInit() {
        Test test1 = new Test();
        test1.title = "Тест на знания Но-Шпа";
        test1.coinsCount = 5;

        ArrayList<String> variants1 = new ArrayList<>();
        variants1.add("Спазмы желудка и кишечника");
        variants1.add("Ангина");
        variants1.add("Аллергия");
        variants1.add("Грипп");
        ArrayList<String> variants2 = new ArrayList<>();
        variants2.add("1");
        variants2.add("3");
        variants2.add("5");
        ArrayList<String> variants3 = new ArrayList<>();
        ArrayList<Question> questions = new ArrayList<>();
        Question question1 = new Question(
                "ПОКАЗАНИЯ",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                QuestionType.SelectVariant,
                "1",
                variants1);
        Question question2 = new Question(
                "СРОК ХРАНЕНИЯ",
                "Сколько лет?",
                QuestionType.SelectStorageType,
                "5",
                variants2);
        Question question3 = new Question(
                "ХРАНЕНИЕ",
                "Выберите особенности хранения",
                QuestionType.SelectShelfTime,
                "4",
                variants3);
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);

        test1.questions = questions;

        return test1;
    }
}