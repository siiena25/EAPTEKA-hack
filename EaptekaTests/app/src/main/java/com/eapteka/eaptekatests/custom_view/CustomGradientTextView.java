package com.eapteka.eaptekatests.custom_view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomGradientTextView extends androidx.appcompat.widget.AppCompatTextView {


    public CustomGradientTextView(Context context) {
        super(context, null, -1);
    }

    public CustomGradientTextView(Context context, AttributeSet attrs) {
        super(context, attrs, -1);
    }

    public CustomGradientTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int[] colors = new int[3];
        colors[0] = Color.parseColor("#8DE6D8");
        colors[1] = Color.parseColor("#729AE5");
        colors[2] = Color.parseColor("#7A74EC");

        if (changed) {
            getPaint().setShader(
                    new LinearGradient(0, 0, getWidth(),
                            0,
                            colors,
                            null,
                            Shader.TileMode.CLAMP));
        }
    }
}
