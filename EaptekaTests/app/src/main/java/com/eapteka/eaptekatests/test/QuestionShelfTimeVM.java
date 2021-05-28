package com.eapteka.eaptekatests.test;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.eapteka.eaptekatests.test_models.Question;

public class QuestionShelfTimeVM extends ViewModel {
    MutableLiveData<Integer> selectedTime = new MutableLiveData<>(1);
    public MutableLiveData<Boolean> isSelectedCurrent = new MutableLiveData<>();


    void increase() {
        selectedTime.setValue(selectedTime.getValue() + 1);
    }

    void decrease() {
        int value = selectedTime.getValue();
        if (value == 1) return;
        selectedTime.setValue(value - 1);
    }


    public void apply(Question question) {
        isSelectedCurrent.setValue(selectedTime.getValue() == Integer.parseInt(question.correctVariant));
    }
}
