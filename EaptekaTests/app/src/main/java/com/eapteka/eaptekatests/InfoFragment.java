package com.eapteka.eaptekatests;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.eapteka.eaptekatests.database.Pill;
import com.eapteka.eaptekatests.database.PillViewModel;
import com.eapteka.eaptekatests.test.InfoVM;
import com.eapteka.eaptekatests.test.TestVM;

import org.jetbrains.annotations.NotNull;

public class InfoFragment extends BaseFragment {
    private View bStartTest;
    private View bReturn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        InfoVM viewModel = new ViewModelProvider(getActivity()).get(InfoVM.class);
        String name;
        switch (viewModel.name.getValue()) {
            case "Но-Шпа" :
                name = "No-shpa";
                break;
            case "Мезим" :
                name = "Mezim";
                break;
            case "Арбидол" :
                name = "Arbidol";
                break;
            default: name = "Arbidol";
        }
        bStartTest = view.findViewById(R.id.start_test);
        PillViewModel pillViewModel = new ViewModelProvider(this).get(PillViewModel.class);
        TextView textView = (TextView)view.findViewById(R.id.info_text);
        Observer<Pill> obs = pill -> {
            if (pill != null) {
                textView.setText(pill.getApply());
            }
        };


        pillViewModel.updatePill(name);
        pillViewModel.getPill().observe(getActivity(), obs);
        bStartTest.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_infoFragment_to_testFragment);
        });

        bReturn  = view.findViewById(R.id.return_to_profile);
        bReturn.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).popBackStack();
        });
    }
}