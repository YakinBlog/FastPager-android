package com.yakin.fastpager.adapter;

import android.support.v4.view.PagerAdapter;

import com.yakin.fastpager.view.TransformType;

public abstract class BasePagerAdapter extends PagerAdapter {

    public abstract TransformType getTransformType(int position);
}
