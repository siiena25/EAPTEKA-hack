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

        viewModel = new ViewModelProvider(this).get(TestVM.class);
        viewModel.test.setValue(baseViewModel.selectedTest.getValue());

        //viewModel.updateTestInformation("Alexey", 0);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvProgress = getView().findViewById(R.id.tv_progress);
        pbProgress = getView().findViewById(R.id.pb_progress);

        viewModel.test.observe(getViewLifecycleOwner(), test -> {
            pbProgress.setMax(test.getCountOfQuestions());
            loadNextStep();
        });
        viewModel.currentProgressInt.observe(getViewLifecycleOwner(), progress -> pbProgress.setProgress(progress));
        viewModel.currentProgressString.observe(getViewLifecycleOwner(), progress -> tvProgress.setText(progress));

        bBackToMenu.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_testFragment_to_listAvaibleTestFragment);
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
        else {
            Bundle bundle = new Bundle();
            ArrayList<Boolean> answers = viewModel.answers.getValue();
            boolean[] answersBool = new boolean[answers.size()];
            for (int i = 0; i < answers.size(); i++)
                answersBool[i] = answers.get(i);

            bundle.putBooleanArray("is_answer_right", answersBool);
            bundle.putInt("right_answers_count", viewModel.getRightAnswerCount());
            bundle.putString("coins_count", viewModel.test.getValue().getCoinsCount().toString());
            bundle.putString("discount", viewModel.test.getValue().getDiscount().toString());

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