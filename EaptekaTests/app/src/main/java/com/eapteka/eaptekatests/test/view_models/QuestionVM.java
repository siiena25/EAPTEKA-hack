package com.eapteka.eaptekatests.test.view_models;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.eapteka.eaptekatests.test.StepLoader;
import com.eapteka.eaptekatests.test_models.Question;

import java.util.ArrayList;

public class QuestionVM extends ViewModel {
    public MutableLiveData<Question> question = new MutableLiveData<>();
    public MutableLiveData<StepLoader> stepLoader = new MutableLiveData<>();
    public MutableLiveData<Boolean> isSelectedCurrent = new MutableLiveData<>();

    public void loadNextStep() {
        stepLoader.getValue().loadNextStep();
    }

    public void apply(Question question, boolean[] choosed) {
        boolean ans = true;
        ArrayList<String> corrects = new ArrayList<>();
        String arr[] = question.correctVariant.split(",");
        for (String a : arr)
            corrects.add(a);

        for (int i = 0; i < choosed.length; i++){
            if ((choosed[i] && !corrects.contains(question.variants.get(i))) ||
                    (!choosed[i] && corrects.contains(question.variants.get(i))))
                ans = false;
        }
        isSelectedCurrent.setValue(ans);
        return;
    }

}
