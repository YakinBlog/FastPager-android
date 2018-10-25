package com.yakin.fastpager.simple;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.yakin.fastpager.AbstractPage;
import com.yakin.fastpager.router.PageRouter;
import com.yakin.fastpager.view.PageState;
import com.yakin.fastpager.view.TransformType;

public class Page3 extends AbstractPage {

    private final String TAG = Page3.class.getSimpleName();

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d(TAG,"onCreate was called");
        setPageState(PageState.TRANSIENT); // 退出后不销毁,需要主动finishPage
        setTransformType(TransformType.POPUP); // 弹入弹出
        TextView textView = new TextView(getContext());
        textView.setBackgroundColor(0xff4682B4);
        textView.setText("Page3");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(40);
        setContentView(textView);

//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                PageRouter.getInstance().finishPage(Page3.this);
//            }
//        });
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
