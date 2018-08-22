package com.yakin.fastpager.animation;

import android.view.View;

import com.nineoldandroids.view.ViewHelper;
import com.yakin.fastpager.view.BaseViewPager;

public class StackTransformer implements BaseViewPager.PageTransformer {

    @Override
    public void transformPage(View page, float position) {
        int pageWidth = page.getWidth();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
        } else if (position <= 0) { // [-1,0]
            // Use the default slide transition when
            // moving to the left page
            ViewHelper.setTranslationX(page, -pageWidth * position);
        } else if (position <= 1) { // (0,1]
            // Fade the page out.
            ViewHelper.setTranslationX(page, 0);
        } else { // (1,+Infinity]
            // This page is way off-screen to the right
        }
    }
}
