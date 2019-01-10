package com.yakin.fastpager.simple;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yakin.fastpager.AbstractPage;
import com.yakin.fastpager.PageContainer;
import com.yakin.fastpager.router.IPageRouter;
import com.yakin.fastpager.router.PageRouter;

public class MainActivity extends AppCompatActivity {

    private PageContainer viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (PageContainer) findViewById(R.id.sm_page);
        viewPager.startPage(Page.class);

        // 虽然AbstractPage都有如下方法可以使用，还是建议所有的路由都由统一的管理比较好
        PageRouter.getInstance().setRouter(new IPageRouter() {
            @Override
            public void startPage(Class<? extends AbstractPage> clazz) {
                viewPager.startPage(clazz);
            }

            @Override
            public void startPage(Class<? extends AbstractPage> clazz, Bundle bundle) {
                viewPager.startPage(clazz, bundle);
            }

            @Override
            public void finishPage(AbstractPage page) {
                viewPager.finishPage(page);
            }

            @Override
            public void finishPage(AbstractPage page, int code, Intent data) {
                viewPager.finishPage(page, code, data);
            }

            @Override
            public void backPage() {
                viewPager.backPage();
            }
        });
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
