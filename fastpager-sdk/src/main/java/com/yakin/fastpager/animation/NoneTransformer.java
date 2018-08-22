package com.yakin.fastpager.animation;

import android.view.View;

import com.yakin.fastpager.view.BaseViewPager;

public class NoneTransformer implements BaseViewPager.PageTransformer {

    @Override
    public void transformPage(View page, float position) {
        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
        } else if (position <= 0) { // [-1,0]
            // Use the default slide transition when
            // moving to the left page
        } else if (position <= 1) { // (0,1]
            // Fade the page out.
        } else { // (1,+Infinity]
            // This page is way off-screen to the right
        }
    }
}
