package com.yakin.fastpager.animation;

import android.support.v4.view.ViewPager;

import com.yakin.fastpager.PageAnim;

import java.util.HashMap;

public class PageTransformerMgr {

    private HashMap<PageAnim, ViewPager.PageTransformer> transformMapCache = new HashMap<>();

    public ViewPager.PageTransformer getTransformer(PageAnim anim) {
        ViewPager.PageTransformer transformer;
        if (anim != null) {
            transformer = transformMapCache.get(anim);
            if (transformer == null) {
                transformer = newTransformerInstanceByType(anim);
                transformMapCache.put(PageAnim.NONE, transformer);
            }
        } else {
            transformer = newTransformerInstanceByType(PageAnim.NONE);
            transformMapCache.put(PageAnim.NONE, transformer);
        }
        return transformer;
    }

    private ViewPager.PageTransformer newTransformerInstanceByType(PageAnim anim) {
        ViewPager.PageTransformer transformer;
        switch (anim) {
            case STACK:
                transformer = new StackTransformer();
                break;
            case POPUP:
                transformer = new PopupTransformer();
                break;
            default:
                transformer = new NoneTransformer();
                break;
        }
        return transformer;
    }
}
