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


public class InfoVM extends AndroidViewModel {

    public MutableLiveData<String> name= new MutableLiveData<>();

    public InfoVM(@NonNull Application application) {
        super(application);
    }
}