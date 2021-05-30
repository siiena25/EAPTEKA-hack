package com.eapteka.eaptekatests;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eapteka.eaptekatests.adapters.AchievAdapter;

import org.jetbrains.annotations.NotNull;

public class AchievementsFragment extends BaseFragment {
    private AccountVM viewModel;
    private TextView scoreView;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_achievements, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(getActivity()).get(AccountVM.class);
        scoreView = view.findViewById(R.id.score_view);

        viewModel.accountData.observe(getViewLifecycleOwner(), accountData -> {
            if (Integer.parseInt(scoreView.getText().toString().split(" ")[0]) < accountData.coins)
                if (accountData.coins % 10 == 1)
                    scoreView.setText(accountData.coins + " балл");
                else if (accountData.coins % 10 != 0 && accountData.coins % 10 > 5)
                    scoreView.setText(accountData.coins + " баллов");
                else
                    scoreView.setText(accountData.coins + " баллов");
        });

        view.findViewById(R.id.return_to_profile).setOnClickListener(v -> {
            NavHostFragment.findNavController(this).popBackStack();
        });

        RecyclerView recyclerView = view.findViewById(R.id.achievements_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new AchievAdapter());

    }
}
