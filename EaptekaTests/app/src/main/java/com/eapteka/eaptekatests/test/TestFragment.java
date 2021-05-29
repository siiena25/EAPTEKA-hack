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
import androidx.navigation.NavGraphNavigator;
import androidx.navigation.fragment.NavHostFragment;

import com.eapteka.eaptekatests.BaseFragment;
import com.eapteka.eaptekatests.R;
import com.eapteka.eaptekatests.test_models.Question;
import com.eapteka.eaptekatests.test_models.QuestionType;
import com.eapteka.eaptekatests.test_models.Test;

import java.util.ArrayList;
import java.util.List;


public class TestFragment extends BaseFragment implements StepLoader {
    private Test test;
    private TestVM viewModel;
    private TextView tvProgress;
    private ProgressBar pbProgress;
    private View bBackToMenu;

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
        bBackToMenu = view.findViewById(R.id.b_back_to_menu);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        test = new Test();
        test.discount = 12;
        test.coinsCount = 100;

        Question question = new Question();
        question.type = QuestionType.SelectVariant;
        question.title = "Показания";
        question.desc = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";
        question.variants.add("первый вариант");
        question.variants.add("второй вариант");
        question.variants.add("пятый вариант");
        question.variants.add("и шестой вариант");
        question.correctVariant = question.variants.get(2);
        test.questions.add(question);

        Question question1 = new Question();
        question1.type = QuestionType.SelectShelfTime;
        question1.title = "Срок годности";
        question1.desc = "Сколько месяцев?";
        question1.variants.add("4");
        question1.variants.add("3");
        question1.variants.add("1");
        question1.variants.add("2");
        question1.correctVariant = question1.variants.get(2);
        test.questions.add(question1);


        Question question2 = new Question();
        question2.type = QuestionType.SelectStorageType;
        question2.title = "хранение";
        question2.desc = "Выберите способ хранения";
        question2.variants.add("1");
        question2.variants.add("2");
        question2.variants.add("3");
        question2.variants.add("4");
        question2.correctVariant = question2.variants.get(2);
        test.questions.add(question2);

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

        bBackToMenu.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).popBackStack();
        });
    }

    @Override
    public void loadNextStep() {
        TestBaseFragment fragment = viewModel.getTextQuestionFragment(this);

        if (fragment != null)
            getChildFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_questions, fragment, null)
                    .addToBackStack(null)
                    .commit();
        else{
            Bundle bundle = new Bundle();
            bundle.putInt("right_answers_count", viewModel.getRightAnswerCount());
            ArrayList<Boolean> answers = viewModel.answers.getValue();
            boolean[] answersBool = new boolean[answers.size()];
            for(int i = 0; i < answers.size(); i++)
                answersBool[i] = answers.get(i);

            bundle.putBooleanArray("is_answer_right", answersBool);
            bundle.putString("coins_count", viewModel.test.getValue().coinsCount.toString());
            bundle.putString("discount", viewModel.test.getValue().discount.toString());

            NavHostFragment.findNavController(this).navigate(R.id.action_testFragment_to_testResultFragment, bundle);
        }
    }

    @Override
    public void cancelTest() {

    }

    @Override
    public void loadPreviousStep() {

    }
}