package com.yakin.fastpager.simple;

import android.content.Intent;
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
        viewPager.startPage(Page1.class);
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
        viewPager.destroyPage();
    }

    @Override
    public void onBackPressed() {
        viewPager.backPage();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        viewPager.resultPage(requestCode, data);
    }
}
