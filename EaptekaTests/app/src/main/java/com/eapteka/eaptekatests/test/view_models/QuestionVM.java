package com.eapteka.eaptekatests.test.view_models;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.eapteka.eaptekatests.test.StepLoader;
import com.eapteka.eaptekatests.test_models.Question;

import java.util.ArrayList;

public class QuestionVM extends ViewModel {
    public MutableLiveData<Question> question = new MutableLiveData<>();
    public MutableLiveData<StepLoader> stepLoader = new MutableLiveData<>();

    public void loadNextStep() {
        stepLoader.getValue().loadNextStep();
    }

}
