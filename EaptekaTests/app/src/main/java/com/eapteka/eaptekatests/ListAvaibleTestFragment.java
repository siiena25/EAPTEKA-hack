package com.eapteka.eaptekatests;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eapteka.eaptekatests.adapters.FinishedTestsAdapter;
import com.eapteka.eaptekatests.adapters.StartedTestsAdapter;
import com.eapteka.eaptekatests.test_models.Question;
import com.eapteka.eaptekatests.test_models.QuestionType;
import com.eapteka.eaptekatests.test_models.Test;

import java.util.ArrayList;

public class ListAvaibleTestFragment extends BaseFragment implements
        StartedTestsAdapter.OnStartedTestListener,
        FinishedTestsAdapter.OnFinishedTestListener {
    private View bReturn;

    private final ArrayList<Test> listStartedTests = new ArrayList<>();
    private final ArrayList<Test> listFinishedTest = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_list_avaible_test, container, false);

        Test exampleTest = exampleTestInit();

        listStartedTests.add(exampleTest);
        listFinishedTest.add(exampleTest);

        RecyclerView startedTestsList = view.findViewById(R.id.started_tests_list);
        startedTestsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        StartedTestsAdapter startedTestsAdapter = new StartedTestsAdapter(getActivity(), this, listStartedTests);
        startedTestsList.setAdapter(startedTestsAdapter);

        RecyclerView finishedTestsList = view.findViewById(R.id.finished_tests_list);
        finishedTestsList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        FinishedTestsAdapter finishedTestsAdapter = new FinishedTestsAdapter(getActivity(), this, listFinishedTest);
        finishedTestsList.setAdapter(finishedTestsAdapter);

        bReturn = view.findViewById(R.id.return_to_profile);
        bReturn.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).popBackStack();
        });

        return view;
    }

    private Test exampleTestInit() {
        Test test1 = new Test();
        test1.setTitle("Но-Шпа");
        test1.setCoinsCount(5);

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

        test1.setQuestions(questions);

        return test1;
    }

    @Override
    public void onFinishedTestClick(View view, int position) {
        baseViewModel.selectedTest.setValue(listFinishedTest.get(position));
        NavHostFragment.findNavController(this).navigate(R.id.action_listAvaibleTestFragment_to_testFragment);
    }

    @Override
    public void onStartedTestClick(View view, int position) {
        baseViewModel.selectedTest.setValue(listStartedTests.get(position));
        NavHostFragment.findNavController(this).navigate(R.id.action_listAvaibleTestFragment_to_testFragment);
    }

}