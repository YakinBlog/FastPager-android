package com.yakin.fastpager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

public abstract class AbstractPage {

    private PageContainer container;

    final void setPageContainer(PageContainer container) {
        this.container = container;
    }

    public final Context getContext() {
        return container.getContext();
    }

    public final void startPage(Class<? extends AbstractPage> clazz) {
        startPage(clazz, new Bundle());
    }

    public final void startPage(Class<? extends AbstractPage> clazz, PageAnim anim) {
        startPage(clazz, new Bundle(), anim);
    }

    public final void startPage(Class<? extends AbstractPage> clazz, Bundle bundle) {
        startPage(clazz, bundle, PageAnim.NONE);
    }

    public final void startPage(Class<? extends AbstractPage> clazz, Bundle bundle, PageAnim anim) {
        container.startPage(clazz, bundle, anim);
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