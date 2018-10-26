package com.yakin.fastpager.simple;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.yakin.fastpager.AbstractView;
import com.yakin.fastpager.router.PageRouter;
import com.yakin.fastpager.animation.TransformType;

public class View3 extends AbstractView {

    private final String TAG = View3.class.getSimpleName();

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d(TAG,"onCreate was called");
        setTransformType(TransformType.STACK); // 滑动切换进出当前页面的动效，层叠进出
        TextView textView = new TextView(getContext());
        textView.setBackgroundColor(Color.CYAN);
        textView.setText("View3");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(40);
        setContentView(textView);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PageRouter.getInstance().startPage(Page3.class);
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
