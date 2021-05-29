package com.eapteka.eaptekatests;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.eapteka.eaptekatests.test_models.Test;

public class BaseFragmentVM extends ViewModel {
    public MutableLiveData<Test> selectedTest = new MutableLiveData<>();
}
