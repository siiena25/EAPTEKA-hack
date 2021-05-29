package com.eapteka.eaptekatests.test;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.eapteka.eaptekatests.BaseFragment;
import com.eapteka.eaptekatests.R;
import com.eapteka.eaptekatests.test.view_models.QuestionVM;
import com.eapteka.eaptekatests.test_models.Question;

public class TestBaseFragment extends BaseFragment {
    protected QuestionVM viewModel;
    protected TestVM parentViewModel;
    private Question question;
    private StepLoader stepLoader;
    protected TextView tvTitle;
    protected TextView tvDesc;
    protected View bNext;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(QuestionVM.class);
        if (question != null)
            viewModel.question.setValue(question);
        if(stepLoader != null)
            viewModel.stepLoader.setValue(stepLoader);

        parentViewModel = new ViewModelProvider(getParentFragment()).get(TestVM.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvTitle = view.findViewById(R.id.tv_title);
        tvDesc = view.findViewById(R.id.tv_desc);
        bNext = view.findViewById(R.id.b_next);
        bNext.setVisibility(View.GONE);

        bNext.setClickable(false);
        bNext.setOnClickListener(v -> {
            viewModel.loadNextStep();
        });
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void setStepLoader(StepLoader stepLoader) {
        this.stepLoader = stepLoader;
    }

    protected void fillBy(Question question) {
        tvTitle.setText(question.title);
        tvDesc.setText(question.desc);
    }

    protected void showNextButton() {
        bNext.setAlpha(0f);
        bNext.setVisibility(View.VISIBLE);
        bNext.setClickable(true);
        bNext.animate().alpha(1f).setDuration(1000);
    }
}
