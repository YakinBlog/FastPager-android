package com.yakin.fastpager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.yakin.fastpager.animation.NoneTransformer;
import com.yakin.fastpager.animation.StackTransformer;

import java.util.ArrayList;

public class PageContainer extends ViewPager {

    private final String TAG = PageContainer.class.getSimpleName();

    public static final int NONE = 0;
    public static final int STACK = 0;

    /**
     * 设置切换动画
     *
     * @param type
     */
    public void setType(int type) {
        if(type == STACK) {
            setPageTransformer(false, new StackTransformer());
        } else if(type == NONE) {
            setPageTransformer(false, new NoneTransformer());
        }
    }

    private int oldPosition = 0;

    class Adapter extends PagerAdapter {

        private ArrayList<AbstractPage> list = new ArrayList<>();

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            AbstractPage page = list.get(position);
            View view = page.getContentView();
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        public void addPage(AbstractPage page) {
            list.add(page);
            notifyDataSetChanged();
        }

        public void removePage(AbstractPage page) {
            list.remove(page);
            notifyDataSetChanged();
        }

        public AbstractPage getPage(int position) {
            return list.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }

    class PageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

        @Override
        public void onPageSelected(final int position) {
            Log.d(TAG, "onPageSelected was called, [" + oldPosition + "] to [" + position + "]");
            getAdapter().getPage(oldPosition).onPause();
            getAdapter().getPage(position).onResume();
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            Log.d(TAG,"onPageScrollStateChanged was called, [" + state + "]");
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                int position = getCurrentItem();
                Log.d(TAG, "[" + oldPosition + "] to [" + position + "]");
                if(position == oldPosition - 1) {
                    AbstractPage page = getAdapter().getPage(oldPosition);
                    if (page.getType() != AbstractPage.RESIDENT) {
                        finishPage(page);
                    }
                }
                oldPosition = position;
            }
        }
    }

    private PageChangeListener listener;

    public PageContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        super.setAdapter(new Adapter());
        super.addOnPageChangeListener(listener = new PageChangeListener());
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        Log.e(TAG, "setAdapter has been discarded");
    }

    @Override
    public Adapter getAdapter() {
        return (Adapter) super.getAdapter();
    }

    public void startPage(Class<? extends AbstractPage> page) {
        startPage(page, new Bundle());
    }

    public void startPage(Class<? extends AbstractPage> clazz, Bundle bundle) {
        try {
            AbstractPage page = clazz.newInstance();
            page.setPageContainer(this);
            page.onCreate(bundle);
            getAdapter().addPage(page);
            setCurrentItem(getAdapter().getCount() - 1);
        } catch (Exception e) {
            Log.e(TAG, "start page failed:" + e.getLocalizedMessage());
        }
    }

    public void finishPage(AbstractPage page) {
        page.onDestory();
        getAdapter().removePage(page);
    }

    public void resumePage() {
        if(getAdapter().getCount() > 0) {
            int position = getCurrentItem();
            AbstractPage page = getAdapter().getPage(position);
            page.onResume();
        }
    }

    public void pausePage() {
        if(getAdapter().getCount() > 0) {
            int position = getCurrentItem();
            AbstractPage page = getAdapter().getPage(position);
            page.onPause();
        }
    }

    public void backPage() {
        int position = getCurrentItem();
        if(position < 1) {
            Context context = getContext();
            if(context instanceof Activity) {
                ((Activity) context).finish();
            }
        } else {
            setCurrentItem(position - 1);
        }
    }

    public void deatoryPage() {
        for (AbstractPage page: getAdapter().list) {
            page.onDestory();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.removeOnPageChangeListener(listener);
        super.onDetachedFromWindow();
    }
}
