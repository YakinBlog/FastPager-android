package com.yakin.fastpager.simple;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

import com.yakin.fastpager.AbstractPage;

public class Page3 extends AbstractPage {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        TextView textView = new TextView(getContext());
        textView.setBackgroundColor(Color.LTGRAY);
        textView.setText("Page3");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(40);
        setContentView(textView);
    }
}
