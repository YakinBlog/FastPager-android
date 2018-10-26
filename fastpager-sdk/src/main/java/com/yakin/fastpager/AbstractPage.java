package com.yakin.fastpager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.yakin.fastpager.animation.TransformType;

public abstract class AbstractPage extends LifeCycle {

    public enum PageState {
        TRANSIENT, // 退出后销毁
        RESIDENT // 退出后不销毁
    }

    public static final int CODE_NONE = -1;

    private PageContainer container;

    final void setPageContainer(PageContainer container) {
        this.container = container;
    }

    public final Context getContext() {
        return container.getContext();
    }

    public final void startPage(Class<? extends AbstractPage> clazz) {
        container.startPage(clazz);
    }

    public final void startPage(Class<? extends AbstractPage> clazz, Bundle bundle) {
        container.startPage(clazz, bundle);
    }

    public final void finishPage() {
        container.finishPage(this);
    }

    public final void finishPage(int code, Intent data) {
        container.finishPage(this, code, data);
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

    private PageState state = PageState.TRANSIENT;

    public final void setPageState(PageState state) {
        this.state = state;
    }

    public final PageState getPageState() {
        return state;
    }

    public boolean onBack() {
        return false;
    }

    public void onResult(int code, Intent data) {
        // TODO
    }
}