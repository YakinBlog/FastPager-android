package com.yakin.fastpager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.yakin.fastpager.adapter.BasePagerAdapter;
import com.yakin.fastpager.view.BaseViewPager;
import com.yakin.fastpager.view.PageState;
import com.yakin.fastpager.view.PageTransformType;

import java.util.ArrayList;

public class PageContainer extends BaseViewPager {

    private final String TAG = PageContainer.class.getSimpleName();

    private int oldPosition = 0;

    class Adapter extends BasePagerAdapter {

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
        public PageTransformType getTransformType(int position) {
            return list.get(position).getPageTransformType();
        }
    }

    class PageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

        @Override
        public void onPageSelected(final int position) {
            Log.d(TAG, "onPageSelected was called, [" + oldPosition + "] to [" + position + "]");
            if(getAdapter().getCount() > oldPosition) {
                getAdapter().getPage(oldPosition).onPause();
            }
            getAdapter().getPage(position).onResume();
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            Log.d(TAG,"onPageScrollStateChanged was called, [" + state + "]");
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                int position = getCurrentItem();
                Log.d(TAG, "[" + oldPosition + "] to [" + position + "]");
                if(position == oldPosition - 1 && getAdapter().getCount() > oldPosition) {
                    AbstractPage page = getAdapter().getPage(oldPosition);
                    if (page.getPageState() == PageState.TRANSIENT) {
                        finishPage(getAdapter().getPage(oldPosition));
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

    public void startPage(Class<? extends AbstractPage> page, PageTransformType anim) {
        startPage(page, new Bundle(), anim);
    }

    public void startPage(Class<? extends AbstractPage> page, Bundle bundle) {
        startPage(page, bundle, PageTransformType.NONE);
    }

    public void startPage(Class<? extends AbstractPage> clazz, Bundle bundle, PageTransformType type) {
        try {
            AbstractPage page = clazz.newInstance();
            page.setPageTransformType(type);
            page.setPageContainer(this);
            page.onCreate(bundle);
            getAdapter().addPage(page);
            setCurrentItem(getAdapter().getCount() - 1);
        } catch (Exception e) {
            Log.e(TAG, "start page failed:" + e.getLocalizedMessage());
        }
    }

    public void resultPage(int code, Intent data) {
        int size = getAdapter().getCount();
        if(size >= 1) {
            getAdapter().list.get(size - 1).onResult(code, data);
        }
    }

    public void finishPage(AbstractPage page) {
        finishPage(page, AbstractPage.CODE_NONE, new Intent());
    }

    public void finishPage(AbstractPage page, int code, Intent data) {
        if(getAdapter().list.indexOf(page) > -1) {
            if(getAdapter().getCount() == 1) {
                Context context = getContext();
                if(context instanceof Activity) {
                    ((Activity) context).finish();
                }
            } else {
                page.onPause();
                page.onDestory();
                getAdapter().removePage(page);
                resultPage(code, data);
            }
        }
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
        } else if(!getAdapter().getPage(position).onBack()){
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
