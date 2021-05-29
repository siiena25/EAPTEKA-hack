package com.eapteka.eaptekatests;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;
import uk.co.samuelwall.materialtaptargetprompt.extras.backgrounds.CirclePromptBackground;
import uk.co.samuelwall.materialtaptargetprompt.extras.focals.RectanglePromptFocal;

public class AccountFragment extends BaseFragment {

    private AccountVM viewModel;
    private ImageView moodView;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(AccountVM.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_account, container, false);

        moodView = view.findViewById(R.id.mood_view);
        viewModel.accountData.observe(getViewLifecycleOwner(), accountData -> {
            if (accountData.happyLevel > 0.85){
                int imageId = getActivity()
                        .getResources()
                        .getIdentifier("ic_mood_the_happiest", "drawable", getActivity().getPackageName());
                moodView.setBackground(AppCompatResources.getDrawable(getActivity(), imageId));
            }
            else if (accountData.happyLevel > 0.72){
                int imageId = getActivity()
                        .getResources()
                        .getIdentifier("ic_mood_very_happy", "drawable", getActivity().getPackageName());
                moodView.setBackground(AppCompatResources.getDrawable(getActivity(), imageId));
            }
            else if (accountData.happyLevel > 0.58){
                int imageId = getActivity()
                        .getResources()
                        .getIdentifier("ic_mood_satisfied", "drawable", getActivity().getPackageName());
                moodView.setBackground(AppCompatResources.getDrawable(getActivity(), imageId));
            }
            else if (accountData.happyLevel > 0.43){
                int imageId = getActivity()
                        .getResources()
                        .getIdentifier("ic_mood_neutral", "drawable", getActivity().getPackageName());
                moodView.setBackground(AppCompatResources.getDrawable(getActivity(), imageId));
            }
            else if (accountData.happyLevel > 0.29){
                int imageId = getActivity()
                        .getResources()
                        .getIdentifier("ic_mood_dissatisfied", "drawable", getActivity().getPackageName());
                moodView.setBackground(AppCompatResources.getDrawable(getActivity(), imageId));
            }
            else if (accountData.happyLevel > 0.15){
                int imageId = getActivity()
                        .getResources()
                        .getIdentifier("ic_mood_bad", "drawable", getActivity().getPackageName());
                moodView.setBackground(AppCompatResources.getDrawable(getActivity(), imageId));
            }
            else {
                int imageId = getActivity()
                        .getResources()
                        .getIdentifier("ic_mood_dead", "drawable", getActivity().getPackageName());
                moodView.setBackground(AppCompatResources.getDrawable(getActivity(), imageId));
            }

        });
        callButtonTestsTapTargetPrompt();
        return view;
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
                        if (state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED) {
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
                        if (state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED) {
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
}