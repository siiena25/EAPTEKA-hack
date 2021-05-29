package com.eapteka.eaptekatests.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

public class CustomViewCrossOut extends CustomGradientTextView {
    private Boolean isAnimStart = false;
    public void startAnim() {
        isAnimStart = true;
    }

    public CustomViewCrossOut(Context context) {
        super(context);
    }

    public CustomViewCrossOut(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewCrossOut(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private int startX = 0;

    private int currentX = 0;
    private int currentY = 0;

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG) {
        {
            setDither(true);
            setColor(Color.RED);
            setStrokeWidth(5f);
        }
    };

    private final int speed = 5;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.drawLine(startX, 0, currentX, currentY, paint);
        int k = canvas.getHeight()/canvas.getWidth();
        if (currentY <= getHeight() && currentX >= 0 && isAnimStart) {
            currentY +=speed*k;
            currentX -= speed;

        }

        postInvalidateDelayed(10);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        startX = w;
        currentX = w;
    }
}
