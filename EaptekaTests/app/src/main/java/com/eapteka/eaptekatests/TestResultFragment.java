package com.eapteka.eaptekatests;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.emitters.StreamEmitter;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class TestResultFragment extends Fragment {

    private static final String RIGHT_ANSWERS_COUNT = "right_answers_count";
    private static final String IS_RIGHT_ANSWERS = "is_answer_right";
    private int rightAnswersCount = 0;
    private boolean[] isAnswerRight = new boolean[5];
    private ImageView image;
    private View view;

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
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_test_result, container, false);
        image = view.findViewById(R.id.test_result_image);
        if (rightAnswersCount < 5) {
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
        answerImages[4] = view.findViewById(R.id.question_4_result);
        for (int i = 0; i < 5; i++) {
            if (!isAnswerRight[i]){
                int imageId = getActivity().
                        getResources().
                        getIdentifier("ic_wrong_answer", "drawable", getActivity().getPackageName());
                answerImages[i].setBackground(AppCompatResources.getDrawable(getActivity(), imageId));
            }

        }
        showCongratulation(view);
        return view;
    }

    public void showCongratulation(View view) {
        final KonfettiView konfettiView = view.findViewById(R.id.view_confetti);
        konfettiView.build()
                .addColors(getActivity().getResources().getColor(R.color.teal_700),
                        getActivity().getResources().getColor(R.color.teal_200),
                        getActivity().getResources().getColor(R.color.purple_500))
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE)
                .addSizes(new Size(12, 5f))
                .setPosition(0, (float)konfettiView.getWidth(), 0f, 0f)
                .streamFor(400, 10000L);
    }
}