package com.eapteka.eaptekatests;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;
import uk.co.samuelwall.materialtaptargetprompt.extras.backgrounds.CirclePromptBackground;
import uk.co.samuelwall.materialtaptargetprompt.extras.focals.RectanglePromptFocal;

public class AccountFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_account, container, false);
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