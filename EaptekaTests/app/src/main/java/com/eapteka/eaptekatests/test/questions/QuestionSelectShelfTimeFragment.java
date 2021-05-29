package com.eapteka.eaptekatests.test.questions;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.eapteka.eaptekatests.R;
import com.eapteka.eaptekatests.custom_view.CustomGradientTextView;
import com.eapteka.eaptekatests.custom_view.CustomViewCrossOut;
import com.eapteka.eaptekatests.test.view_models.QuestionShelfTimeVM;
import com.eapteka.eaptekatests.test.TestBaseFragment;

public class QuestionSelectShelfTimeFragment extends TestBaseFragment {
    private QuestionShelfTimeVM viewModelLocal;
    private CustomViewCrossOut tvSelectedTime;
    private CustomGradientTextView tvApply;
    private View bPlus;
    private View bMinus;

    public static QuestionSelectShelfTimeFragment newInstance() {
        QuestionSelectShelfTimeFragment fragment = new QuestionSelectShelfTimeFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_question_select_shelf_time, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bPlus = getView().findViewById(R.id.b_plus);
        bMinus = getView().findViewById(R.id.b_minus);
        tvSelectedTime = getView().findViewById(R.id.tv_count);
        tvApply = getView().findViewById(R.id.b_apply);

        viewModel.question.observe(getViewLifecycleOwner(), question -> {
            fillBy(question);
        });
        viewModelLocal = new ViewModelProvider(this).get(QuestionShelfTimeVM.class);
        viewModelLocal.selectedTime.observe(getViewLifecycleOwner(), time -> {
            tvSelectedTime.setText(time.toString());
        });
        viewModelLocal.isSelectedCurrent.observe(getViewLifecycleOwner(), isSelectedCurrent -> {
            if (!isSelectedCurrent) {
                tvSelectedTime.startAnim();
                tvApply.setText("Правильный ответ: " + viewModel.question.getValue().correctVariant);
            } else {
                tvSelectedTime.animate()
                        .scaleX(1.1f)
                        .scaleY(1.1f)
                        .setDuration(1000);
                tvApply.setText("Правильный ответ!");
            }
            parentViewModel.addAnswer(isSelectedCurrent);
            showNextButton();
        });

        bPlus.setOnClickListener(v -> {
            viewModelLocal.increase();
        });
        bMinus.setOnClickListener(v -> {
            viewModelLocal.decrease();
        });

        tvApply.setOnClickListener(v -> {
            tvApply.setClickable(false);
            bNext.setClickable(false);
            bMinus.setClickable(false);
            viewModelLocal.apply(viewModel.question.getValue());
        });
    }


}
