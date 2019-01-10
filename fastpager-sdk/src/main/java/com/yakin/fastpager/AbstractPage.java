package com.yakin.fastpager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.yakin.fastpager.animation.BaseTransformer;
import com.yakin.fastpager.animation.PageTransformer;

public abstract class AbstractPage extends LifeCycle {

    public static final int RESULT_CODE_NONE = -1;

    private PageContainer container;
    private View view;

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

    public BaseTransformer getTransformer() {
        return new PageTransformer();
    }

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

    public boolean onBack() {
        return false;
    }

    public void onResult(int code, Intent data) {
        // TODO
    }
}