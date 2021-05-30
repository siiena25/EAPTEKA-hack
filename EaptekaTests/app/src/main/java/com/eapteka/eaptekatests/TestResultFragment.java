package com.eapteka.eaptekatests;

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
    private int countOfQuestions = 3;
    private int maximumDiscount = 5;
    private boolean[] arrAnswerCurrentOrMistake;
    private ImageView image;
    private View view;
    private TextView tvResultPresent;
    private ViewGroup containerAnswerView;
    //private String coinsCount;
//    private String discount;

    private AccountVM viewModel;

    public TestResultFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            rightAnswersCount = getArguments().getInt(RIGHT_ANSWERS_COUNT);
            arrAnswerCurrentOrMistake = getArguments().getBooleanArray(IS_RIGHT_ANSWERS);
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
        containerAnswerView = view.findViewById(R.id.questions_result_layout);


        tvResultPresent.setText(new StringBuilder()
                .append("ВАШ БОНУС - ")
                .append(Math.round(rightAnswersCount * maximumDiscount / countOfQuestions))
                .append("% СКИДКА")
                .toString());

        if (rightAnswersCount < 4)
            image.setBackgroundResource(R.drawable.thumb_down_test);


        for(Boolean isCurrentAnswer: arrAnswerCurrentOrMistake) {
            int resId;
            if(isCurrentAnswer)
                resId = R.layout.item_current_answer;
            else
                resId = R.layout.item_mistake_answer;
            View view = LayoutInflater.from(getContext()).inflate(resId, null, false);
            containerAnswerView.addView(view);
        }


        float delta = 0;
        for (int i = 0; i < arrAnswerCurrentOrMistake.length; i++) {
            if (arrAnswerCurrentOrMistake[i])
                delta += 0.05;
            else
                delta -= 0.05;
        }
        viewModel.updateHappyLevel(delta);
        viewModel.updateСoins(rightAnswersCount);
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
