package com.yakin.fastpager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.yakin.fastpager.view.PageTransformType;
import com.yakin.fastpager.view.PageState;

public abstract class AbstractPage {

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

    public final void startPage(Class<? extends AbstractPage> clazz, PageTransformType type) {
        container.startPage(clazz, type);
    }

    public final void startPage(Class<? extends AbstractPage> clazz, Bundle bundle) {
        container.startPage(clazz, bundle);
    }

    public final void startPage(Class<? extends AbstractPage> clazz, Bundle bundle, PageTransformType type) {
        container.startPage(clazz, bundle, type);
    }

    private PageTransformType transformType = PageTransformType.NONE;

    final PageTransformType getPageTransformType() {
        return transformType;
    }

    final void setPageTransformType(PageTransformType type) {
        this.transformType = type;
    }

    private View view;

    public final void setContentView(View view) {
        this.view = view;
    }

    public final View getContentView() {
        return view;
    }

    private PageState state = PageState.TRANSIENT;

    public final void setPageState(PageState state) {
        this.state = state;
    }

    public final PageState getPageState() {
        return state;
    }

    public void onCreate(Bundle bundle) {
        // TODO
    }

    public void onResume() {
        // TODO
    }

    public void onPause() {
        // TODO
    }

    public void onDestory() {
        // TODO
    }
}