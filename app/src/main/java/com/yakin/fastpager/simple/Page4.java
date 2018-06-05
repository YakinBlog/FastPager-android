package com.yakin.fastpager.simple;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

import com.yakin.fastpager.AbstractPage;

public class Page4 extends AbstractPage {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        TextView textView = new TextView(getContext());
        textView.setBackgroundColor(Color.YELLOW);
        textView.setText("Page4");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(40);
        setContentView(textView);
    }
}
