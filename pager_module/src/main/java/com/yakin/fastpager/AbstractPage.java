package com.yakin.fastpager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

public abstract class AbstractPage {

    public enum Type {
        RESIDENT, TRANSIENT
    }

    private Type type = Type.RESIDENT;

    /**
     * RESIDENT时页面滑出后会自动销毁，默认是RESIDENT
     *
     * @param type
     */
    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void onCreate(Bundle bundle) {
        // TODO
    }

    public void onDestory() {
        // TODO
    }

    public abstract View getView(Context context);
}
