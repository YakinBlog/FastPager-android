package com.yakin.fastpager.simple;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import com.yakin.fastpager.AbstractPage;

public class Page2 extends AbstractPage {

    private final String TAG = Page2.class.getSimpleName();

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d(TAG,"onCreate was called");

        TextView textView = new TextView(getContext());
        textView.setBackgroundColor(0xff778899);
        textView.setText("Page2");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(40);
        setContentView(textView);
    }

    @Override
    public void onResume() {
        super.onResume();
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
