package com.yakin.fastpager.simple;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import com.yakin.fastpager.AbstractPage;
import com.yakin.fastpager.view.PageState;
import com.yakin.fastpager.view.TransformType;

public class Page1 extends AbstractPage {

    private final String TAG = Page1.class.getSimpleName();

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d(TAG,"onCreate was called");
        setPageState(PageState.TRANSIENT); // 退出后销毁
        setTransformType(TransformType.POPUP); // 弹出弹出

        TextView textView = new TextView(getContext());
        textView.setBackgroundColor(0xff4169E1);
        textView.setText("Page1");
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

    @Override
    public void onResult(int code, Intent data) {
        super.onResult(code, data);
        Log.d(TAG,"onResult was called, code[" + code+ "]");
    }
}
