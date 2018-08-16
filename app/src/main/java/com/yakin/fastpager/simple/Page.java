package com.yakin.fastpager.simple;

import android.os.Bundle;
import android.util.Log;

import com.yakin.fastpager.AbstractPage;
import com.yakin.fastpager.ViewContainer;
import com.yakin.fastpager.view.PageState;
import com.yakin.fastpager.view.PageTransformType;

public class Page extends AbstractPage {

    private final String TAG = Page.class.getSimpleName();

    private ViewContainer container;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d(TAG,"onCreate was called");
        setPageState(PageState.TRANSIENT);
        setPageTransformType(PageTransformType.STACK);

        setContentView(R.layout.view_main);

        container = (ViewContainer) findViewById(R.id.sm_view);

        container.addView(View1.class);
        container.addView(View2.class);
        container.addView(View3.class);

        container.setOnViewChangeListener(new ViewContainer.OnViewChangeListener() {
            @Override
            public void onViewSelected(int position) {
                Log.d(TAG,"position = " + position);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"onResume was called");
        container.resumeView();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"onPause was called");
        container.pauseView();
    }

    @Override
    public void onDestory() {
        super.onDestory();
        Log.d(TAG,"onDestory was called");
        container.destroyView();
    }
}
