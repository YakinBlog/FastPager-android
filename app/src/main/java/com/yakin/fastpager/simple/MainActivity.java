package com.yakin.fastpager.simple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yakin.fastpager.PageContainer;

public class MainActivity extends AppCompatActivity {

    private PageContainer viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (PageContainer) findViewById(R.id.sm_page);
        viewPager.setType(PageContainer.Type.STACK);
        viewPager.startPage(Page1.class);
        viewPager.startPage(Page2.class);
        viewPager.startPage(Page3.class);
        viewPager.startPage(Page4.class);
        viewPager.startPage(Page5.class);
//        viewPager.setCurrentItem(1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewPager.resumePage();
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewPager.pausePage();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewPager.deatoryPage();
    }

    @Override
    public void onBackPressed() {
        viewPager.backPage();
    }
}
