package com.eapteka.eaptekatests.test;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.eapteka.eaptekatests.test_models.Question;
import com.eapteka.eaptekatests.test_models.Test;


public class TestVM extends ViewModel {
    MutableLiveData<Test> test = new MutableLiveData<>();
    MutableLiveData<Integer> currentQuestionPos = new MutableLiveData<>(0);
    MutableLiveData<String> currentProgressString = new MutableLiveData<>();
    MutableLiveData<Integer> currentProgressInt = new MutableLiveData<>();

    public TestBaseFragment getTextQuestionFragment() {
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
            }

            fragment.setQuestion(question);

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
}
