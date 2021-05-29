package com.eapteka.eaptekatests.test.questions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.eapteka.eaptekatests.R;
import com.eapteka.eaptekatests.test.TestBaseFragment;

public class QuestionSelectVariantFragment extends TestBaseFragment {
    private GridLayout gridLayout;
    private int currentViewPos;

    public static QuestionSelectVariantFragment newInstance() {
        QuestionSelectVariantFragment fragment = new QuestionSelectVariantFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_question_select_variants, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gridLayout = getView().findViewById(R.id.container_grid);

        viewModel.question.observe(getViewLifecycleOwner(), question -> {
            fillBy(question);
            for (String variant : question.variants) {
                TextView variantView = (TextView) LayoutInflater.from(getContext())
                        .inflate(R.layout.item_question_variant, gridLayout, false);
                variantView.setText(variant);

                Boolean isCurrentAnswer = variant.equals(question.correctVariant);
                int viewPos = question.variants.indexOf(variant);
                if (isCurrentAnswer)
                    currentViewPos = viewPos;


                variantView.setOnClickListener(v -> {
                    animate(currentViewPos, isCurrentAnswer, viewPos);
                });

                gridLayout.addView(variantView);
            }
        });
    }


    private void animate(int currentViewPos, Boolean isCurrentAnswer, int selectedViewPos) {
        int durationTime = 1000;
        float scaleUp = 1.1f;
        float scaleDown = 0.6f;
        float alphaDown = 0.1f;

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            View view = gridLayout.getChildAt(i);
            view.setClickable(false);

            if (isCurrentAnswer) {
                if (i != currentViewPos)
                    view.animate().alpha(alphaDown).setDuration(durationTime);
                else
                    view.animate().scaleY(scaleUp).scaleX(scaleUp).setDuration(durationTime);
            } else {
                if(i == selectedViewPos)
                    view.animate().scaleY(scaleDown).scaleX(scaleDown).setDuration(durationTime);
                else if(i == currentViewPos)
                    view.animate().scaleY(scaleUp).scaleX(scaleUp).setDuration(durationTime);
                else
                    view.animate().alpha(alphaDown).setDuration(durationTime);
            }
        }
        showNextButton();
    }
}
