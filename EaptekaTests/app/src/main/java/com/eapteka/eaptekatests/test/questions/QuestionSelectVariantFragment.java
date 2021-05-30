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

import java.util.ArrayList;

public class QuestionSelectVariantFragment extends TestBaseFragment {
    private GridLayout gridLayout;
    private int currentViewPos;
    private boolean[] choosed = {false, false, false, false};

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

                int viewPos = question.variants.indexOf(variant);

                variantView.setOnClickListener(v -> {
                    int durationTime = 1000;
                    float scaleUp = 1.1f;
                    if (choosed[viewPos]){
                        variantView.animate().scaleY(1).scaleX(1).setDuration(durationTime);
                        choosed[viewPos] = false;
                    }
                    else{
                        variantView.animate().scaleY(scaleUp).scaleX(scaleUp).setDuration(durationTime);
                        choosed[viewPos] = true;
                    }
                });

                gridLayout.addView(variantView);
            }
        });

        viewModel.isSelectedCurrent.observe(getViewLifecycleOwner(), isSelectedCurrent -> {
            animate(viewModel.question.getValue().correctVariant, isSelectedCurrent, choosed);
        });

        bNext.setVisibility(View.VISIBLE);
        bNext.setClickable(true);
        bNext.setOnClickListener(v -> {
            ((TextView)bNext).setText(R.string.next);
            bNext.setOnClickListener(s -> {
                viewModel.loadNextStep();
            });
            viewModel.apply(viewModel.question.getValue(), choosed);
        });
    }


    private void animate(String correctVariant, Boolean isSelectedCurrent, boolean[] choosed) {
        int durationTime = 1000;
        float scaleUp = 1.1f;
        float scaleDown = 0.6f;
        float alphaDown = 0.1f;

        String[] correctVariants = correctVariant.split(",");
        ArrayList<String> corrects = new ArrayList<>();
        for (int i = 0; i < correctVariants.length; i++) {
            corrects.add(correctVariants[i]);
        }

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            View view = gridLayout.getChildAt(i);
            view.setClickable(false);

            String curVariant = viewModel.question.getValue().variants.get(i);
            if (isSelectedCurrent) {
                if (!corrects.contains(curVariant))
                    view.animate().alpha(alphaDown).setDuration(durationTime);
                else
                    view.animate().scaleY(scaleUp).scaleX(scaleUp).setDuration(durationTime);
            } else {
                if(choosed[i] && !corrects.contains(curVariant))
                    view.animate().scaleY(scaleDown).scaleX(scaleDown).setDuration(durationTime);
                else if(corrects.contains(curVariant))
                    view.animate().scaleY(scaleUp).scaleX(scaleUp).setDuration(durationTime);
                else
                    view.animate().alpha(alphaDown).setDuration(durationTime);
            }
        }
        parentViewModel.addAnswer(isSelectedCurrent);
        showNextButton();
    }
}
