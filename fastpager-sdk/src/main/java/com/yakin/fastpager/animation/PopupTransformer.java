package com.yakin.fastpager.animation;

import com.nineoldandroids.view.ViewHelper;
import com.yakin.fastpager.view.BaseViewPager;

import android.view.View;

public class PopupTransformer implements BaseViewPager.PageTransformer {

    @Override
    public void transformPage(View page, float position) {
        int pageWidth = page.getWidth();
        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            ViewHelper.setAlpha(page, 1);
        } else if (position <= 0) { // [-1,0]
            ViewHelper.setAlpha(page, 1);
            ViewHelper.setTranslationX(page, -pageWidth * position);
        } else if (position <= 1) { // (0,1]
            // Fade the page out.
            ViewHelper.setTranslationX(page, -pageWidth * position);
            ViewHelper.setAlpha(page, 1 - position);
        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            ViewHelper.setAlpha(page, 1);
        }
    }
}
