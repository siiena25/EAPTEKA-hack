package com.eapteka.eaptekatests;

import android.animation.Animator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.lifecycle.ViewModelProvider;

import com.eapteka.eaptekatests.adapters.FinishedTestsAdapter;
import com.eapteka.eaptekatests.adapters.StartedTestsAdapter;
import com.eapteka.eaptekatests.test.TestRepository;
import com.eapteka.eaptekatests.test_models.Test;

import java.util.ArrayList;

public class ListAvaibleTestFragment extends BaseFragment implements
        StartedTestsAdapter.OnStartedTestListener,
        FinishedTestsAdapter.OnFinishedTestListener {
    private View bReturn;
    private TextView scoreView;

    private final ArrayList<Test> listStartedTests = new ArrayList<>();
    private final ArrayList<Test> listFinishedTest = new ArrayList<>();

    private AccountVM viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(AccountVM.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_list_avaible_test, container, false);

        TestRepository repository = new TestRepository(getContext());
        repository.allTests.observe(getViewLifecycleOwner(), tests -> {
            listFinishedTest.clear();
            listStartedTests.clear();

            listStartedTests.addAll(tests);
            listFinishedTest.addAll(tests);

            RecyclerView startedTestsList = view.findViewById(R.id.started_tests_list);
            startedTestsList.setLayoutManager(new LinearLayoutManager(getActivity()));
            StartedTestsAdapter startedTestsAdapter = new StartedTestsAdapter(getActivity(), this, listStartedTests);
            startedTestsList.setAdapter(startedTestsAdapter);

            RecyclerView finishedTestsList = view.findViewById(R.id.finished_tests_list);
            finishedTestsList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            FinishedTestsAdapter finishedTestsAdapter = new FinishedTestsAdapter(getActivity(), this, listFinishedTest);
            finishedTestsList.setAdapter(finishedTestsAdapter);
        });
        repository.updateAllTests("Alexey");

        scoreView = view.findViewById(R.id.score_view);
        viewModel.accountData.observe(getViewLifecycleOwner(), accountData -> {
            if (Integer.parseInt(scoreView.getText().toString().split(" ")[0]) < accountData.coins)
                if (accountData.coins % 10 == 1)
                    scoreView.setText(accountData.coins + " балл");
                else if (accountData.coins % 10 != 0 && accountData.coins % 10 > 5)
                    scoreView.setText(accountData.coins + " баллов");
                else
                    scoreView.setText(accountData.coins + " балла");
        });


        bReturn = view.findViewById(R.id.return_to_profile);
        bReturn.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).popBackStack();
        });

        return view;
    }


    private final int animDuration = 400;
    private final float scale = 1.1f;
    @Override
    public void onFinishedTestClick(View view, int position) {
        Fragment fragment = this;
        view.animate().scaleX(scale).scaleY(scale).setDuration(animDuration).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                baseViewModel.selectedTest.setValue(listFinishedTest.get(position));
                NavHostFragment.findNavController(fragment).navigate(R.id.action_listAvaibleTestFragment_to_testFragment);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    public void onStartedTestClick(View view, int position) {
        Fragment fragment = this;
        view.animate().scaleY(scale).setDuration(animDuration).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                baseViewModel.selectedTest.setValue(listStartedTests.get(position));
                NavHostFragment.findNavController(fragment).navigate(R.id.action_listAvaibleTestFragment_to_testFragment);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

}
