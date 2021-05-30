package com.eapteka.eaptekatests;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class BaseFragment extends Fragment {
    protected BaseFragmentVM baseViewModel;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseViewModel = new ViewModelProvider(getActivity()).get(BaseFragmentVM.class);
    }
}
