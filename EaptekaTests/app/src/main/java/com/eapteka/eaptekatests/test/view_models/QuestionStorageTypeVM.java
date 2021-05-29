package com.eapteka.eaptekatests.test.view_models;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.eapteka.eaptekatests.test_models.Question;

public class QuestionStorageTypeVM extends ViewModel {
    public Boolean isAnswerCurrent(Question question, Integer selectedPos) {
        return selectedPos == getCurrentPos(question);
    }

    public Integer getCurrentPos(Question question) {
        for(String s: question.variants) {
            if(s.equals(question.correctVariant))
                return question.variants.indexOf(s);
        }
        return -1;
    }
}
