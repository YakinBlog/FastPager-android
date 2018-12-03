package com.yakin.fastpager;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.yakin.fastpager.adapter.BasePagerAdapter;
import com.yakin.fastpager.view.BaseViewPager;
import com.yakin.fastpager.animation.TransformType;

import java.util.ArrayList;

public class ViewContainer extends BaseViewPager {

    private final String TAG = ViewContainer.class.getSimpleName();

    private int oldPosition = 0;

    class Adapter extends BasePagerAdapter {

        private ArrayList<AbstractView> list = new ArrayList<>();

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

        public void addView(AbstractView page) {
            list.add(page);
            notifyDataSetChanged();
        }

        public AbstractView getView(int position) {
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
        public TransformType getTransformType(int position) {
            TransformType type = getView(position).getTransformType();
            if(type == null || type == TransformType.NONE) {
                return TransformType.DEFAULT;
            }
            return type;
        }
    }

    class PageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

        @Override
        public void onPageSelected(final int position) {
            Log.d(TAG, "onPageSelected was called, [" + oldPosition + "] to [" + position + "]");
            if(getCount() > oldPosition) {
                getView(oldPosition).onPause();
                if(!isSmoothScroll()) { // setCurrentItem关闭平滑切换时不会执行onPageScrollStateChanged
                    oldPosition = position;
                }
            } else {
                oldPosition = position;
            }
            getView(position).onResume();
            if(viewListener != null) {
                viewListener.onViewSelected(position);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            Log.d(TAG,"onPageScrollStateChanged was called, [" + state + "]");
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                int position = getCurrentItem();
                Log.d(TAG, "[" + oldPosition + "] to [" + position + "]");
                oldPosition = position;
            }
        }
    }

    public ViewContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        super.setAdapter(new Adapter());
        super.addOnPageChangeListener(new PageChangeListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(getView().getTransformType() == TransformType.NONE) {
            return false;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(getView().getTransformType() == TransformType.NONE) {
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

    public interface OnViewChangeListener {
        void onViewSelected(int position);
    }

    private OnViewChangeListener viewListener;

    public void setOnViewChangeListener(OnViewChangeListener listener) {
        this.viewListener = listener;
    }

    public void addView(Class<? extends AbstractView> page) {
        addView(page, new Bundle());
    }

    public void addView(Class<? extends AbstractView> clazz, Bundle bundle) {
        try {
            AbstractView view = clazz.newInstance();
            view.setViewContainer(this);
            view.onCreate(bundle);
            getAdapter().addView(view);
        } catch (Exception e) {
            Log.e(TAG, "start page failed:" + e.getLocalizedMessage());
        }
    }

    public void resumeView() {
        if(getCount() > 0) {
            getView().onResume();
        }
    }

    public void pauseView() {
        if(getCount() > 0) {
            getView().onPause();
        }
    }

    public void destroyView() {
        for (AbstractView page: getAdapter().list) {
            page.onDestory();
        }
    }

    public int getCount() {
        return getAdapter().getCount();
    }

    public AbstractView getView() {
        return getView(getCurrentItem());
    }

    public AbstractView getView(int position) {
        return getAdapter().getView(position);
    }
}
