package com.eapteka.eaptekatests.test;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.eapteka.eaptekatests.database.Logger;
import com.eapteka.eaptekatests.database.PillRepository;
import com.eapteka.eaptekatests.test.questions.QuestionSelectShelfTimeFragment;
import com.eapteka.eaptekatests.test.questions.QuestionSelectStorageType;
import com.eapteka.eaptekatests.test.questions.QuestionSelectVariantFragment;
import com.eapteka.eaptekatests.test_models.Question;
import com.eapteka.eaptekatests.test_models.Test;

import java.util.ArrayList;


public class TestVM extends AndroidViewModel {

    private static final String LOG_TAG = "TestViewModel";
    private final Logger logger;

    public MutableLiveData<Test> test;
    public MutableLiveData<ArrayList<Test>> allTests;
    MutableLiveData<Integer> currentQuestionPos = new MutableLiveData<>(0);
    MutableLiveData<String> currentProgressString = new MutableLiveData<>();
    MutableLiveData<Integer> currentProgressInt = new MutableLiveData<>();

    TestRepository testRepository;

    MutableLiveData<ArrayList<Boolean>> answers = new MutableLiveData<>(new ArrayList<>());

    public TestVM(@NonNull Application application) {
        super(application);
        logger = new Logger(LOG_TAG, true);

        testRepository = new TestRepository(getApplication());
        test = testRepository.getTestData();
        allTests = testRepository.getAllTests();
    }

    public TestBaseFragment getTextQuestionFragment(StepLoader stepLoader) {
        try {
            int currentStep = currentQuestionPos.getValue();
            Test test1 = test.getValue();
            if (currentStep == test1.getCountOfQuestions()) return null;

            Question question = test.getValue().getQuestions().get(currentStep);
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
            currentProgressString.setValue(nextStep + "/" + test.getValue().getCountOfQuestions());
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

    public LiveData<ArrayList<Test>> getAllTests() {
        return allTests;
    }

    public void updateAllTests(final String username) {
        testRepository.updateAllTests(username);
    }
}
