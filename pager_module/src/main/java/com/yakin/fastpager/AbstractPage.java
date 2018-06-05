package com.yakin.fastpager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

public abstract class AbstractPage {

    private Context context;
    private View view;

    final void setContext(Context context) {
        this.context = context;
    }

    public final Context getContext() {
        return context;
    }

    public final void setContentView(View view) {
        this.view = view;
    }

    public final View getContentView() {
        return view;
    }

    public enum Type {
        RESIDENT, TRANSIENT
    }

    private Type type = Type.RESIDENT;

    /**
     * RESIDENT时页面滑出后会自动销毁，默认是RESIDENT
     *
     * @param type
     */
    public final void setType(Type type) {
        this.type = type;
    }

    public final Type getType() {
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