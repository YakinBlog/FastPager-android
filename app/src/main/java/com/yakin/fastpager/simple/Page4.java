package com.yakin.fastpager.simple;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.yakin.fastpager.AbstractPage;
import com.yakin.fastpager.animation.BaseTransformer;
import com.yakin.fastpager.router.PageRouter;

public class Page4 extends AbstractPage {

    private final String TAG = Page4.class.getSimpleName();

    @Override
    public BaseTransformer getTransformer() {
        return null; // 禁止滑动
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d(TAG,"onCreate was called");

        TextView textView = new TextView(getContext());
        textView.setBackgroundColor(0xff778899);
        textView.setText("Page4");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(40);
        setContentView(textView);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PageRouter.getInstance().backPage();
            }
        });
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
