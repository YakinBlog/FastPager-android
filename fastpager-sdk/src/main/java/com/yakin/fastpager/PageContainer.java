package com.yakin.fastpager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.yakin.fastpager.adapter.BaseViewAdapter;
import com.yakin.fastpager.view.BaseViewPager;
import com.yakin.fastpager.animation.BaseTransformer;

import java.util.ArrayList;

public class PageContainer extends BaseViewPager {

    private final String TAG = PageContainer.class.getSimpleName();

    private int oldPosition = 0;

    class Adapter extends BaseViewAdapter {

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
            View view = list.get(position).getContentView();
            if(container.indexOfChild(view) != -1) {
                container.removeView(view);
            }
            view.setClickable(true);
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
            for (int i = 0; i < list.size(); i++) {
                Object obj = list.get(i).getContentView();
                if (obj == object)
                    return POSITION_UNCHANGED;
            }
            return POSITION_NONE;
        }

        @Override
        public BaseTransformer getTransformer(int position) {
            return getPage(position).getTransformer();
        }
    }

    class PageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

        @Override
        public void onPageSelected(final int position) {
            Log.d(TAG, "onPageSelected was called, [" + oldPosition + "] to [" + position + "]");
            if(getCount() > oldPosition) {
                getPage(oldPosition).onPause();
            } else {
                oldPosition = position;
            }
            getPage(position).onResume();
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            Log.d(TAG,"onPageScrollStateChanged was called, [" + state + "]");
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                int position = getCurrentItem();
                Log.d(TAG, "[" + oldPosition + "] to [" + position + "]");
                if(position == oldPosition - 1 && getCount() > oldPosition) {
                    AbstractPage page = getPage(oldPosition);
                    finishPage(page, AbstractPage.RESULT_CODE_NONE, new Intent(), true);
                }
                oldPosition = position;
            }
        }
    }

    public PageContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        super.setAdapter(new Adapter());
        super.addOnPageChangeListener(new PageChangeListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(getPage().getTransformer() == null) {
            return false;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(getPage().getTransformer() == null) {
            return false;
        }
        return super.onInterceptTouchEvent(ev);
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
            setCurrentItem(getCount() - 1);
        } catch (Exception e) {
            Log.e(TAG, "start page failed:" + e.getLocalizedMessage());
        }
    }

    public void resultPage(int code, Intent data) {
        int count = getCount();
        if(count >= 1) {
            getPage(count - 1).onResult(code, data);
        }
    }

    public void finishPage(AbstractPage page) {
        finishPage(page, AbstractPage.RESULT_CODE_NONE, new Intent());
    }

    public void finishPage(AbstractPage page, int code, Intent data) {
        finishPage(page, code, data, false);
    }

    private void finishPage(AbstractPage page, int code, Intent data, boolean isPaused) {
        int index = getAdapter().list.indexOf(page);
        if(index > -1) {
            if(getCount() == 1) {
                Context context = getContext();
                if(context instanceof Activity) {
                    ((Activity) context).finish();
                }
            } else {
                if(!isPaused) {
                    page.onPause();
                }
                page.onDestory();
                getAdapter().removePage(page);
                resultPage(code, data);
            }
        }
    }

    public void resumePage() {
        if(getCount() > 0) {
            getPage().onResume();
        }
    }

    public void pausePage() {
        if(getCount() > 0) {
            getPage().onPause();
        }
    }

    public void backPage() {
        int position = getCurrentItem();
        if(position < 1) {
            Context context = getContext();
            if(context instanceof Activity) {
                ((Activity) context).finish();
            }
        } else if(!getPage(position).onBack()){
            setCurrentItem(position - 1);
        }
    }

    public void destroyPage() {
        for (AbstractPage page: getAdapter().list) {
            page.onDestory();
        }
    }

    public int getCount() {
        return getAdapter().getCount();
    }

    public AbstractPage getPage() {
        return getPage(getCurrentItem());
    }

    public AbstractPage getPage(int position) {
        return getAdapter().getPage(position);
    }
}
