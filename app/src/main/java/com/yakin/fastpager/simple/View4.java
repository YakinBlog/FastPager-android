package com.yakin.fastpager.simple;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.yakin.fastpager.AbstractView;
import com.yakin.fastpager.router.PageRouter;
import com.yakin.fastpager.view.TransformType;

public class View4 extends AbstractView {

    private final String TAG = View4.class.getSimpleName();

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d(TAG,"onCreate was called");
        setTransformType(TransformType.NONE); // 不允许当前页面左右滑动
        TextView textView = new TextView(getContext());
        textView.setBackgroundColor(Color.DKGRAY);
        textView.setText("View4");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(40);
        setContentView(textView);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PageRouter.getInstance().startPage(Page4.class);
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
