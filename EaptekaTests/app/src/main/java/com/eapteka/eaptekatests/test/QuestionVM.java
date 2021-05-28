package com.eapteka.eaptekatests.test;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.eapteka.eaptekatests.test_models.Question;

public class QuestionVM extends ViewModel {
    public MutableLiveData<Question> question = new MutableLiveData<>();
}
