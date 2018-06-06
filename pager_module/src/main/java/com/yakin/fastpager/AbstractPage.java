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

    public final void startPage(Class<? extends AbstractPage> clazz, Bundle bundle) {
        container.startPage(clazz, bundle);
    }

    private View view;

    public final void setContentView(View view) {
        this.view = view;
    }

    public final View getContentView() {
        return view;
    }


    public static final int RESIDENT = 0;
    public static final int TRANSIENT = 1;
    private int type = RESIDENT;

    /**
     * RESIDENT时页面滑出后会自动销毁，默认是RESIDENT
     *
     * @param type
     */
    public final void setType(int type) {
        this.type = type;
    }

    public final int getType() {
        return type;
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