package com.eapteka.eaptekatests.test;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.eapteka.eaptekatests.test.questions.QuestionSelectShelfTimeFragment;
import com.eapteka.eaptekatests.test.questions.QuestionSelectStorageType;
import com.eapteka.eaptekatests.test.questions.QuestionSelectVariantFragment;
import com.eapteka.eaptekatests.test_models.Question;
import com.eapteka.eaptekatests.test_models.Test;

import java.util.ArrayList;


public class TestVM extends ViewModel {
    MutableLiveData<Test> test = new MutableLiveData<>();
    MutableLiveData<Integer> currentQuestionPos = new MutableLiveData<>(0);
    MutableLiveData<String> currentProgressString = new MutableLiveData<>();
    MutableLiveData<Integer> currentProgressInt = new MutableLiveData<>();

    MutableLiveData<ArrayList<Boolean>> answers = new MutableLiveData<>(new ArrayList<>());


    public TestBaseFragment getTextQuestionFragment(StepLoader stepLoader) {
        try {
            int currentStep = currentQuestionPos.getValue();
            if (currentStep == test.getValue().questions.size()) return null;

            Question question = test.getValue().questions.get(currentStep);
            TestBaseFragment fragment = null;

            switch (question.type) {
                case SelectVariant:
                    fragment = QuestionSelectVariantFragment.newInstance();
                    break;
                case SelectShelfTime:
                    fragment = QuestionSelectShelfTimeFragment.newInstance();
                    break;
                case SelectStorageType:
                    fragment = QuestionSelectStorageType.newInstance();
                    break;
            }

            fragment.setQuestion(question);
            fragment.setStepLoader(stepLoader);

            int nextStep = currentStep + 1;
            currentProgressString.setValue(nextStep + "/" + test.getValue().questions.size());
            currentProgressInt.setValue(nextStep);
            currentQuestionPos.setValue(nextStep);
            return fragment;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void addAnswer(Boolean isAnswerCurrent) {
        answers.getValue().add(isAnswerCurrent);
    }

    public Integer getRightAnswerCount() {
        Integer count = 0;
        for (Boolean answer : answers.getValue())
            if (answer) count++;

        return count;
    }
}
