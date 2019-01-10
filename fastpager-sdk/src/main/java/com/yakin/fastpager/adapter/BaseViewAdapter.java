package com.yakin.fastpager.adapter;

import android.support.v4.view.PagerAdapter;

import com.yakin.fastpager.animation.BaseTransformer;

public abstract class BaseViewAdapter extends PagerAdapter {

    public abstract BaseTransformer getTransformer(int position);
}
