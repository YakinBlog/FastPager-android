package com.yakin.fastpager.simple;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yakin.fastpager.AbstractPage;

public class Page1 extends AbstractPage {

    @Override
    public View getView(Context context) {
        TextView textView = new TextView(context);
        textView.setBackgroundColor(0xff00ff00);
        textView.setText("Page1");
        return textView;
    }
}
