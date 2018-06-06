package com.yakin.fastpager.simple;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.yakin.fastpager.AbstractPage;

public class Page1 extends AbstractPage {

    private final String TAG = Page1.class.getSimpleName();

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d(TAG,"onCreate was called");
        TextView textView = new TextView(getContext());
        textView.setBackgroundColor(Color.GRAY);
        textView.setText("Page1");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(40);
        setContentView(textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPage(Page2.class);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getContentView().invalidate();
        Log.d(TAG,"onResume was called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"onPause was called");
    }

    @Override
    public void onDestory() {
        super.onDestory();
        Log.d(TAG,"onDestory was called");
    }
}
