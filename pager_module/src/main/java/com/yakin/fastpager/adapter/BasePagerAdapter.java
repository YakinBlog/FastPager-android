package com.yakin.fastpager.adapter;

import android.support.v4.view.PagerAdapter;

import com.yakin.fastpager.view.PageTransformType;

public abstract class BasePagerAdapter extends PagerAdapter {

    public abstract PageTransformType getTransformType(int position);
}
