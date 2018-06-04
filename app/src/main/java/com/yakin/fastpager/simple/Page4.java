package com.yakin.fastpager.simple;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.yakin.fastpager.AbstractPage;

public class Page4 extends AbstractPage {

    @Override
    public View getView(Context context) {
        TextView textView = new TextView(context);
        textView.setBackgroundColor(Color.YELLOW);
        textView.setText("Page4");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(40);
        return textView;
    }
}
