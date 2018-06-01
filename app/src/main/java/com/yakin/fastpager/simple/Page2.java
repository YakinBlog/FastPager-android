package com.yakin.fastpager.simple;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yakin.fastpager.AbstractPage;
import com.yakin.fastpager.PageStyle;

/**
 * ****************************************************************************
 * Copyright (C) 2005-2016 UCWEB Corporation. All rights reserved
 * File        : CommonView.java
 * <p>
 * Description : CommonView
 * <p>
 * Creation    : 18/6/1
 * Author      : mailto:wyj80283@alibaba-inc.com
 * History     : Creation, 18/6/1, WYJ create the file
 * ****************************************************************************
 */
public class Page2 extends AbstractPage {

    @Override
    public View getView(Context context) {
        TextView textView = new TextView(context);
        textView.setBackgroundColor(0xff00cc00);
        textView.setText("Page2");
        return textView;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setType(PageStyle.Type.TRANSIENT);
    }
}
