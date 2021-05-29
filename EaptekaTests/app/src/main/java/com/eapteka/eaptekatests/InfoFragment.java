package com.eapteka.eaptekatests;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;

import org.jetbrains.annotations.NotNull;

public class InfoFragment extends BaseFragment {
    private View bStartTest;
    private View bReturn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bStartTest = view.findViewById(R.id.start_test);
        bStartTest.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_infoFragment_to_testFragment);
        });

        bReturn  = view.findViewById(R.id.return_to_profile);
        bReturn.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).popBackStack();
        });
    }
}