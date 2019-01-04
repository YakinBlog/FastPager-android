package com.yakin.fastpager.simple;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.yakin.fastpager.AbstractPage;
import com.yakin.fastpager.ViewContainer;

public class Page extends AbstractPage implements View.OnClickListener {

    private final String TAG = Page.class.getSimpleName();

    private ViewContainer container;
    private View tabHome;
    private View tabChat;
    private View tabFollow;
    private View tabProfile;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d(TAG,"onCreate was called");

        setContentView(R.layout.view_main);

        container = findViewById(R.id.sm_view);
        tabHome = findViewById(R.id.tab_home);
        tabChat = findViewById(R.id.tab_chat);
        tabFollow = findViewById(R.id.tab_follow);
        tabProfile = findViewById(R.id.tab_profile);

        container.addView(View1.class);
        container.addView(View2.class); // 弹出切换
        container.addView(View3.class); // 层叠切换
        container.addView(View4.class); // 禁止滑动
        tabHome.setSelected(true); // 默认显示第一页

        tabHome.setOnClickListener(this);
        tabChat.setOnClickListener(this);
        tabFollow.setOnClickListener(this);
        tabProfile.setOnClickListener(this);
        container.setOnViewChangeListener(new ViewContainer.OnViewChangeListener() {
            @Override
            public void onViewSelected(int position) {
                Log.d(TAG,"position = " + position);
                tabHome.setSelected(position == 0);
                tabChat.setSelected(position == 1);
                tabFollow.setSelected(position == 2);
                tabProfile.setSelected(position == 3);
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

    @Override
    public void onClick(View v) {
        if(v == tabHome) {
            container.setCurrentItem(0, false);
        } else if(v == tabChat) {
            container.setCurrentItem(1, false);
        } else if(v == tabFollow) {
            container.setCurrentItem(2, false);
        } else if(v == tabProfile) {
            container.setCurrentItem(3, false);
        }
    }

    @Override
    public void onResult(int code, Intent data) {
        super.onResult(code, data);
        Log.d(TAG,"onResult was called, code[" + code + "]");
    }
}
