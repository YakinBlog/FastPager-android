package com.yakin.fastpager.simple;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.yakin.fastpager.AbstractPage;
import com.yakin.fastpager.animation.BaseTransformer;
import com.yakin.fastpager.simple.animation.CircleAnimationLayout;
import com.yakin.fastpager.simple.animation.CircleTransformer;

public class Page1 extends AbstractPage {

    private final String TAG = Page1.class.getSimpleName();

    @Override
    public BaseTransformer getTransformer() {
        return new CircleTransformer();
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d(TAG,"onCreate was called");

        TextView textView = new TextView(getContext());
        textView.setBackgroundColor(0xff4169E1);
        textView.setText("Page1\n测试内容测试内容测试内容测试内容测试内容测试内容测试内容" +
                "测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内" +
                "容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试" +
                "内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测" +
                "试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容");
        textView.setTextSize(40);

        CircleAnimationLayout layout = new CircleAnimationLayout(getContext());
        layout.addView(textView);
        setContentView(layout);
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

    @Override
    public void onResult(int code, Intent data) {
        super.onResult(code, data);
        Log.d(TAG,"onResult was called, code[" + code + "]");
    }

    @Override
    public boolean onBack() {
        return true;
    }
}
