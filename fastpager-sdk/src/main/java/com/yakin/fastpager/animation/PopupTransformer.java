package com.yakin.fastpager.animation;

import com.nineoldandroids.view.ViewHelper;
import com.yakin.fastpager.view.BaseViewPager;

import android.util.Log;
import android.view.View;

public class PopupTransformer implements BaseViewPager.PageTransformer {

    @Override
    public void transformPage(View page, float position) {
        int pageWidth = page.getWidth();
        int pageHeight = page.getHeight();
        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
        } else if (position <= 0) { // [-1,0]
            // Use the default slide transition when
            // moving to the left page
            ViewHelper.setTranslationX(page, -pageWidth * position);
        } else if (position <= 1) { // (0,1]
            // Fade the page out.
            ViewHelper.setTranslationX(page, -pageWidth * position);
            ViewHelper.setTranslationY(page, pageHeight * position);
        } else { // (1,+Infinity]
            // This page is way off-screen to the right
        }
    }
}
