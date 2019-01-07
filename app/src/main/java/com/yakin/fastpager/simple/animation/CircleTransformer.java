package com.yakin.fastpager.simple.animation;

import android.view.View;

import com.nineoldandroids.view.ViewHelper;
import com.yakin.fastpager.animation.BaseTransformer;

public class CircleTransformer extends BaseTransformer {

    @Override
    public void transformPage(View page, float position) {
        if(!(page instanceof CircleAnimationLayout)) {
            return;
        }
        CircleAnimationLayout layout = (CircleAnimationLayout) page;
        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
        } else if (position <= 0) { // [-1,0]
            // Use the default slide transition when
            // moving to the left page
            ViewHelper.setTranslationX(page, 0);
        } else if (position <= 1) { // (0,1]
            // Fade the page out.
            int pageWidth = page.getWidth();
            int pageHeight = page.getHeight();
            int pageLength = (int) Math.sqrt(pageWidth * pageWidth + pageHeight * pageHeight);
            layout.drawRadius(pageLength * (1 - position));
            if(position == 1) {
                ViewHelper.setTranslationX(page, 0);
            } else {
                ViewHelper.setTranslationX(page, -pageWidth * position);
            }
        } else { // (1,+Infinity]
            // This page is way off-screen to the right
        }
    }
}
