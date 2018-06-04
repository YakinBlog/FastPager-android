package com.yakin.fastpager.simple;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.yakin.fastpager.AbstractPage;

public class Page2 extends AbstractPage {

    @Override
    public View getView(Context context) {
        TextView textView = new TextView(context);
        textView.setBackgroundColor(Color.BLUE);
        textView.setText("Page2");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(40);
        return textView;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setType(Type.TRANSIENT);
    }
}
