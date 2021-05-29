package com.eapteka.eaptekatests;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class TestResultFragment extends Fragment {

    private static final String RIGHT_ANSWERS_COUNT = "right_answers_count";
    private static final String IS_RIGHT_ANSWERS = "is_answer_right";
    private static final String MOOD_LEVEL = "moodLevel";
    private int rightAnswersCount = 0;
    private boolean[] isAnswerRight = new boolean[5];
    private ImageView image;
    private View view;
    private TextView tvResultPresent;
    private String coinsCount;
    private String discount;

    private AccountVM viewModel;

    public TestResultFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            rightAnswersCount = getArguments().getInt(RIGHT_ANSWERS_COUNT);
            isAnswerRight = getArguments().getBooleanArray(IS_RIGHT_ANSWERS);
        }
        viewModel = new ViewModelProvider(getActivity()).get(AccountVM.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_test_result, container, false);
        image = view.findViewById(R.id.test_result_image);
        tvResultPresent = view.findViewById(R.id.your_additional_scores);


        tvResultPresent.setText(new StringBuilder()
                .append("ВАШ БОНУС - ")
                .append(discount)
                .append("% СКИДКА")
                .toString());

        if (rightAnswersCount < 4) {
            int imageId = getActivity().
                    getResources().
                    getIdentifier("thumb_down_test", "drawable", getActivity().getPackageName());
            image.setBackground(AppCompatResources.getDrawable(getActivity(), imageId));
        }

        ImageView[] answerImages = new ImageView[5];
        answerImages[0] = view.findViewById(R.id.question_0_result);
        answerImages[1] = view.findViewById(R.id.question_1_result);
        answerImages[2] = view.findViewById(R.id.question_2_result);
        answerImages[3] = view.findViewById(R.id.question_3_result);

        float delta = 0;
        for (int i = 0; i < isAnswerRight.length; i++) {
            if (!isAnswerRight[i]) {
                int imageId = getActivity().
                        getResources().
                        getIdentifier("ic_wrong_answer", "drawable", getActivity().getPackageName());
                answerImages[i].setBackground(AppCompatResources.getDrawable(getActivity(), imageId));
            }
            else {
                delta += 0.05;
            }
        }
        viewModel.updateHappyLevel(delta);
        showCongratulation(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_testResultFragment_to_accountFragment2);
        });
    }

    public void showCongratulation(View view) {
        final KonfettiView konfettiView = view.findViewById(R.id.view_confetti);
        konfettiView.build()
                .addColors(getActivity().getResources().getColor(R.color.gradient_start_color),
                        getActivity().getResources().getColor(R.color.gradient_center_color),
                        getActivity().getResources().getColor(R.color.gradient_end_color))
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE)
                .addSizes(new Size(12, 5f))
                .setPosition(0, (float)konfettiView.getWidth(), 0f, 0f)
                .streamFor(200, 1000L);
    }
}
