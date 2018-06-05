package com.yakin.fastpager.simple;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

import com.yakin.fastpager.AbstractPage;

public class Page2 extends AbstractPage {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setType(Type.TRANSIENT);
        TextView textView = new TextView(getContext());
        textView.setBackgroundColor(Color.BLUE);
        textView.setText("Page2");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(40);
        setContentView(textView);
    }
}
