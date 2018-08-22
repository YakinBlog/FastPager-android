package com.yakin.fastpager.animation;

import com.yakin.fastpager.view.PageTransformType;
import com.yakin.fastpager.view.BaseViewPager;

import java.util.HashMap;

public class PageTransformerMgr {

    private HashMap<PageTransformType, BaseViewPager.PageTransformer> transformMapCache = new HashMap<>();

    public BaseViewPager.PageTransformer getTransformByType(PageTransformType type) {
        BaseViewPager.PageTransformer transformer;
        if (type != null) {
            transformer = transformMapCache.get(type);
            if (transformer == null) {
                transformer = newTransformerInstanceByType(type);
                transformMapCache.put(PageTransformType.NONE, transformer);
            }
        } else {
            transformer = newTransformerInstanceByType(PageTransformType.NONE);
            transformMapCache.put(PageTransformType.NONE, transformer);
        }
        return transformer;
    }

    private BaseViewPager.PageTransformer newTransformerInstanceByType(PageTransformType type) {
        BaseViewPager.PageTransformer transformer;
        switch (type) {
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
