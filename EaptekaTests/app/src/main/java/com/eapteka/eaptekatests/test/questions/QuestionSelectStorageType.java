package com.eapteka.eaptekatests.test.questions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.eapteka.eaptekatests.R;
import com.eapteka.eaptekatests.test.TestBaseFragment;
import com.eapteka.eaptekatests.test.view_models.QuestionStorageTypeVM;

import java.util.ArrayList;

public class QuestionSelectStorageType extends TestBaseFragment {
    private View bSuperCold;
    private View bCold;
    private View bHeat;
    private View bSuperHeat;
    private View bApply;
    private ArrayList<View> buttons = new ArrayList<>();
    private QuestionStorageTypeVM viewModelLocal;

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
        viewModelLocal = new ViewModelProvider(this).get(QuestionStorageTypeVM.class);

        for (View button : buttons) {
            button.setOnClickListener(v -> {
                Integer pos = buttons.indexOf(button);
                Integer currentPos = viewModelLocal.getCurrentPos(viewModel.question.getValue());
                Boolean isAnswerCurrent = viewModelLocal.isAnswerCurrent(viewModel.question.getValue(), pos);
                animate(currentPos, isAnswerCurrent, pos);
            });
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    private void animate(int currentViewPos, Boolean isCurrentAnswer, int selectedViewPos) {
        int durationTime = 1000;
        float scaleUp = 1.1f;
        float scaleDown = 0.6f;
        float alphaDown = 0.1f;

        for (View view: buttons) {
            Integer i = buttons.indexOf(view);
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
