package com.yakin.fastpager.simple.animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class CircleAnimationLayout extends FrameLayout {

    private Path mPath = new Path();
    private float mRadius;

    public CircleAnimationLayout(@NonNull Context context) {
        this(context, null);
    }

    public CircleAnimationLayout(@NonNull Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleAnimationLayout(@NonNull Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        super.setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        int width = getWidth();
        mPath.addCircle(width, 0, mRadius, Path.Direction.CW);
        canvas.clipPath(mPath);
    }

    public void drawRadius(float radius) {
        mRadius = radius;
        invalidate();
    }
}
