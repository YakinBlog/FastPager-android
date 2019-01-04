package com.yakin.fastpager.simple.animation;

import android.view.View;

import com.nineoldandroids.view.ViewHelper;
import com.yakin.fastpager.animation.BaseTransformer;

public class PopupTransformer extends BaseTransformer {

    @Override
    public void transformPage(View page, float position) {
        int pageWidth = page.getWidth();
        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            ViewHelper.setAlpha(page, 1);
        } else if (position <= 0) { // [-1,0]
            // Use the default slide transition when
            // moving to the left page
            ViewHelper.setAlpha(page, 1 + position);
            ViewHelper.setTranslationX(page, -pageWidth * position);
        } else if (position <= 1) { // (0,1]
            // Fade the page out.
            if(position == 1) {
                ViewHelper.setAlpha(page, 0);
                ViewHelper.setTranslationX(page, 0);
            } else {
                ViewHelper.setAlpha(page, 1 - position);
                ViewHelper.setTranslationX(page, -pageWidth * position);
            }
        } else { // (1,+Infinity]
            // This page is way off-screen to the right
            ViewHelper.setAlpha(page, 1);
        }
    }
}
