package com.yakin.fastpager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

public abstract class AbstractPage {

    private PageStyle.Type type = PageStyle.Type.RESIDENT;

    /**
     * RESIDENT时页面滑出后会自动销毁，默认是RESIDENT
     *
     * @param type
     */
    public void setType(PageStyle.Type type) {
        this.type = type;
    }

    public PageStyle.Type getType() {
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
