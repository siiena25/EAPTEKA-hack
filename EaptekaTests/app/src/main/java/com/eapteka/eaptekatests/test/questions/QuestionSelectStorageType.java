package com.eapteka.eaptekatests.test.questions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.eapteka.eaptekatests.R;
import com.eapteka.eaptekatests.test.TestBaseFragment;

import java.util.ArrayList;

public class QuestionSelectStorageType extends TestBaseFragment {
    private View bSuperCold;
    private View bCold;
    private View bHeat;
    private View bSuperHeat;
    private View bApply;
    private ArrayList<View> buttons = new ArrayList<>();
    private boolean[] choosed = {false, false, false, false};

    public static QuestionSelectStorageType newInstance() {
        QuestionSelectStorageType fragment = new QuestionSelectStorageType();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_question_select_storage_type, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.question.observe(getViewLifecycleOwner(), question -> {
            fillBy(question);
        });

        bSuperCold = view.findViewById(R.id.b_cold);
        bCold = view.findViewById(R.id.b_super_cold);
        bHeat = view.findViewById(R.id.b_heat);
        bSuperHeat = view.findViewById(R.id.b_super_heat);
        bApply = view.findViewById(R.id.b_apply);
        buttons.add(bSuperCold);
        buttons.add(bCold);
        buttons.add(bHeat);
        buttons.add(bSuperHeat);

        for (View button : buttons) {
            button.setOnClickListener(v -> {
                Integer pos = buttons.indexOf(button);
                int durationTime = 1000;
                float scaleUp = 1.1f;
                if (choosed[pos]) {
                    button.animate().scaleY(1).scaleX(1).setDuration(durationTime);
                    choosed[pos] = false;
                } else {
                    button.animate().scaleY(scaleUp).scaleX(scaleUp).setDuration(durationTime);
                    choosed[pos] = true;
                }
            });


            viewModel.isSelectedCurrent.observe(getViewLifecycleOwner(), isSelectedCurrent -> {
                animate(viewModel.question.getValue().correctVariant, isSelectedCurrent, choosed);
            });

            bNext.setVisibility(View.VISIBLE);
            bNext.setClickable(true);
            bNext.setOnClickListener(v -> {
                ((TextView) bNext).setText(R.string.next);
                bNext.setOnClickListener(s -> {
                    viewModel.loadNextStep();
                });
                viewModel.apply(viewModel.question.getValue(), choosed);
            });
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private Boolean isGet = false;
    private void animate(String correctVariant, Boolean isSelectedCurrent, boolean[] choosed) {
        if(isGet) return;
        isGet = true;

        int durationTime = 1000;
        float scaleUp = 1.1f;
        float scaleDown = 0.6f;
        float alphaDown = 0.1f;

        String[] correctVariants = correctVariant.split(",");
        ArrayList<String> corrects = new ArrayList<>();
        for (int i = 0; i < correctVariants.length; i++) {
            corrects.add(correctVariants[i]);
        }

        for (int i = 0; i < buttons.size(); i++) {
            View view = buttons.get(i);
            view.setClickable(false);

            String curVariant = viewModel.question.getValue().variants.get(i);
            if (isSelectedCurrent) {
                if (!corrects.contains(curVariant))
                    view.animate().alpha(alphaDown).setDuration(durationTime);
                else
                    view.animate().scaleY(scaleUp).scaleX(scaleUp).setDuration(durationTime);
            } else {
                if (choosed[i] && !corrects.contains(curVariant))
                    view.animate().scaleY(scaleDown).scaleX(scaleDown).setDuration(durationTime);
                else if (corrects.contains(curVariant))
                    view.animate().scaleY(scaleUp).scaleX(scaleUp).setDuration(durationTime);
                else
                    view.animate().alpha(alphaDown).setDuration(durationTime);
            }
        }
        parentViewModel.addAnswer(isSelectedCurrent);
        showNextButton();
    }
}
