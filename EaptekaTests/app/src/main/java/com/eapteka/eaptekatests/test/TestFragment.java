package com.eapteka.eaptekatests.test;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.eapteka.eaptekatests.BaseFragment;
import com.eapteka.eaptekatests.R;
import com.eapteka.eaptekatests.test_models.Question;
import com.eapteka.eaptekatests.test_models.QuestionType;
import com.eapteka.eaptekatests.test_models.Test;

import java.util.List;


public class TestFragment extends BaseFragment implements StepLoader {
    private ViewGroup containerQuestionsFragment;
    private Test test;
    private TestVM viewModel;
    private TextView tvProgress;
    private ProgressBar pbProgress;

    public TestFragment() {
    }


    public static TestFragment newInstance(Test test) {
        TestFragment fragment = new TestFragment();
        fragment.test = test;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        containerQuestionsFragment = view.findViewById(R.id.container_questions);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        test = new Test();
        /*

        Question question = new Question();
        question.type = QuestionType.SelectVariant;
        question.title = "Показания";
        question.desc = "asdasdaads";
        question.variants.add("первый вариант");
        question.variants.add("второй вариант");
        question.variants.add("пятый вариант");
        question.variants.add("и шестой вариант");
        question.correctVariant = question.variants.get(2);
        test.questions.add(question);

         */
        try {
            Question question1 = new Question();
            question1.type = QuestionType.SelectShelfTime;
            question1.title = "Показания";
            question1.desc = "asdasdaads";
            question1.variants.add("4");
            question1.variants.add("3");
            question1.variants.add("1");
            question1.variants.add("2");
            question1.correctVariant = question1.variants.get(2);
            test.questions.add(question1);
        } catch (Exception e) {
            e.printStackTrace();
        }



        viewModel = new ViewModelProvider(this).get(TestVM.class);
        if (test != null)
            viewModel.test.setValue(test);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvProgress = getView().findViewById(R.id.tv_progress);
        pbProgress = getView().findViewById(R.id.pb_progress);

        viewModel.test.observe(getViewLifecycleOwner(), test -> {
            pbProgress.setMax(test.questions.size());
            loadNextStep();
        });
        viewModel.currentProgressInt.observe(getViewLifecycleOwner(), progress -> pbProgress.setProgress(progress));
        viewModel.currentProgressString.observe(getViewLifecycleOwner(), progress -> tvProgress.setText(progress));
    }

    @Override
    public void loadNextStep() {
        TestBaseFragment fragment = viewModel.getTextQuestionFragment();

        if (fragment != null)
            getChildFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_questions, fragment, null)
                    .addToBackStack(null)
                    .commit();
    }

    @Override
    public void cancelTest() {

    }

    @Override
    public void loadPreviousStep() {

    }
}