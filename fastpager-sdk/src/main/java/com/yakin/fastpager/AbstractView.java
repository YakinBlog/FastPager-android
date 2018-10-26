package com.yakin.fastpager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.yakin.fastpager.animation.TransformType;

public abstract class AbstractView extends LifeCycle {

    private ViewContainer container;

    final void setViewContainer(ViewContainer container) {
        this.container = container;
    }

    public final Context getContext() {
        return container.getContext();
    }

    private TransformType transformType;

    public TransformType getTransformType() {
        return transformType;
    }

    public void setTransformType(TransformType type) {
        this.transformType = type;
    }

    private View view;

    public final void setContentView(int resID) {
        this.view = LayoutInflater.from(getContext()).inflate(resID, null);
    }

    public final void setContentView(View view) {
        this.view = view;
    }

    public final View getContentView() {
        return view;
    }

    public final <T extends View> T findViewById(int resID) {
        return view.findViewById(resID);
    }
}