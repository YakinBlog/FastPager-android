package com.yakin.fastpager;

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
import java.util.List;

public class PageContainer extends ViewPager {

    private final String TAG = PageContainer.class.getSimpleName();

    public enum Type {
        NONE, STACK
    }

    /**
     * 设置切换动画
     *
     * @param type
     */
    public void setType(Type type) {
        if(Type.STACK.equals(type)) {
            setPageTransformer(false, new StackTransformer());
        } else if(Type.NONE.equals(type)) {
            setPageTransformer(false, new NoneTransformer());
        }
    }

    private int oldPosition = 0;

    class Adapter extends PagerAdapter {

        private List<AbstractPage> list = new ArrayList<>();

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
            View view = page.getView(getContext());
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            AbstractPage page = list.get(position);
            View view = page.getView(getContext());
            container.removeView(view);
        }

        public void addPage(AbstractPage page) {
            list.add(page);
            notifyDataSetChanged();
        }

        public void removePage(AbstractPage page) {
            list.remove(page);
            notifyDataSetChanged();
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
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(position == oldPosition -1) {
                        AbstractPage page = getAdapter().list.get(oldPosition);
                        if (AbstractPage.Type.TRANSIENT.equals(page.getType())) {
                            finishPage(page);
                        }
                    }
                    oldPosition = position;
                }
            }, 100); // 避免滑动删除时过快
        }

        @Override
        public void onPageScrollStateChanged(int state) { }
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
            page.onCreate(bundle);
            getAdapter().addPage(page);
        } catch (Exception e) {
            Log.e(TAG, "start page failed:" + e.getLocalizedMessage());
        }
    }

    public void finishPage(AbstractPage page) {
        page.onDestory();
        getAdapter().removePage(page);
    }

    @Override
    protected void onDetachedFromWindow() {
        removeOnPageChangeListener(listener);
        super.onDetachedFromWindow();
    }
}
