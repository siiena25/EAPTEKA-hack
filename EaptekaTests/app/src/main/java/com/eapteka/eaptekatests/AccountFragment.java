package com.eapteka.eaptekatests;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.jetbrains.annotations.NotNull;

import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;
import uk.co.samuelwall.materialtaptargetprompt.extras.backgrounds.CirclePromptBackground;
import uk.co.samuelwall.materialtaptargetprompt.extras.focals.RectanglePromptFocal;

public class AccountFragment extends BaseFragment {
    private NavController navController;

    private AccountVM viewModel;
    private ImageView moodView;

    boolean isFirstEnterInProfileFragment = true;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(AccountVM.class);

        if (savedInstanceState != null) {
            isFirstEnterInProfileFragment = savedInstanceState.getBoolean("isFirstEnterInProfileFragment", false);
        }
        else {
            isFirstEnterInProfileFragment = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_account, container, false);

        moodView = view.findViewById(R.id.mood_view);
        viewModel.accountData.observe(getViewLifecycleOwner(), accountData -> {
            String name;
            if (accountData.happyLevel > 0.85)
                name = "ic_mood_the_happiest";
            else if (accountData.happyLevel > 0.72)
                name = "ic_mood_very_happy";
            else if (accountData.happyLevel > 0.58)
                name = "ic_mood_satisfied";
            else if (accountData.happyLevel > 0.43)
                name = "ic_mood_neutral";
            else if (accountData.happyLevel > 0.29)
                name = "ic_mood_dissatisfied";
            else if (accountData.happyLevel > 0.15)
                name = "ic_mood_bad";
            else
                name = "ic_mood_dead";

            int imageId = getActivity()
                    .getResources()
                    .getIdentifier(name, "drawable", getActivity().getPackageName());
            moodView.setBackground(AppCompatResources.getDrawable(getActivity(), imageId));
        });

        navController = NavHostFragment.findNavController(this);

        AppCompatButton buttonTests = view.findViewById(R.id.button_tests);
        buttonTests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_accountFragment_to_listAvaibleTestFragment);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (isFirstEnterInProfileFragment) {
            callButtonTestsTapTargetPrompt();
            isFirstEnterInProfileFragment = false;
        }
    }

    private void callButtonTestsTapTargetPrompt() {
        new MaterialTapTargetPrompt.Builder(getActivity())
                .setTarget(R.id.button_tests)
                .setPrimaryText("Тесты от ЕАПТЕКИ")
                .setSecondaryText("Вы можете проходить образовательные тесты по любому лекарству из ЕАПТЕКИ. " +
                        "По нажатию на эту кнопку вы перейдете к пройденным и рекомендуемым тестам.")
                .setAnimationInterpolator(new FastOutSlowInInterpolator())
                .setPromptBackground(new CirclePromptBackground())
                .setPromptFocal(new RectanglePromptFocal().setCornerRadius(90, 90))
                .setBackgroundColour(getResources().getColor(R.color.transparent_end_color))
                .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener() {
                    @Override
                    public void onPromptStateChanged(@NonNull MaterialTapTargetPrompt prompt, int state) {
                        if (state == MaterialTapTargetPrompt.STATE_NON_FOCAL_PRESSED) {
                            callScoreViewTapTargetPrompt();
                        }
                    }
                })
                .show();
    }

    private void callScoreViewTapTargetPrompt() {
        new MaterialTapTargetPrompt.Builder(getActivity())
                .setTarget(R.id.score_view)
                .setPrimaryText("Система начисления баллов")
                .setSecondaryText("Успешно проходя тесты, Вы получаете баллы, которыми можете оплачивать следующие покупки.")
                .setAnimationInterpolator(new FastOutSlowInInterpolator())
                .setPromptBackground(new CirclePromptBackground())
                .setPromptFocal(new RectanglePromptFocal().setCornerRadius(90, 90))
                .setBackgroundColour(getResources().getColor(R.color.transparent_end_color))
                .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener() {
                    @Override
                    public void onPromptStateChanged(@NonNull MaterialTapTargetPrompt prompt, int state) {
                        if ((state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED) ||
                                (state == MaterialTapTargetPrompt.STATE_NON_FOCAL_PRESSED)) {
                            callMoodTapTargetPrompt();
                        }
                    }
                })
                .show();
    }

    private void callMoodTapTargetPrompt() {
        new MaterialTapTargetPrompt.Builder(getActivity())
                .setTarget(R.id.mood_view)
                .setPrimaryText("Отражение успешного прохождения тестов")
                .setSecondaryText("Если результаты пройденных Вами тестов будут отличные, то Ваш смайл будет всегда отлично себя чувствовать.")
                .setAnimationInterpolator(new FastOutSlowInInterpolator())
                .setPromptBackground(new CirclePromptBackground())
                .setPromptFocal(new RectanglePromptFocal().setCornerRadius(150, 150))
                .setBackgroundColour(getResources().getColor(R.color.transparent_end_color))
                .show();
    }

    @Override
    public void onSaveInstanceState(@NonNull @NotNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("isFirstEnterInProfileFragment", false);
    }
}