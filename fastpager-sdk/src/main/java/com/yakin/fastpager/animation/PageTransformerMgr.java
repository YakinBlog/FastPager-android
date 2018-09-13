package com.yakin.fastpager.animation;

import com.yakin.fastpager.view.TransformType;
import com.yakin.fastpager.view.BaseViewPager;

import java.util.HashMap;

public class PageTransformerMgr {

    private HashMap<TransformType, BaseViewPager.PageTransformer> transformMapCache = new HashMap<>();

    public BaseViewPager.PageTransformer getTransformByType(TransformType type) {
        BaseViewPager.PageTransformer transformer;
        if (type != null) {
            transformer = transformMapCache.get(type);
            if (transformer == null) {
                transformer = newTransformerInstanceByType(type);
                transformMapCache.put(TransformType.DEFAULT, transformer);
            }
        } else {
            transformer = newTransformerInstanceByType(TransformType.DEFAULT);
            transformMapCache.put(TransformType.DEFAULT, transformer);
        }
        return transformer;
    }

    private BaseViewPager.PageTransformer newTransformerInstanceByType(TransformType type) {
        BaseViewPager.PageTransformer transformer;
        switch (type) {
            case STACK:
                transformer = new StackTransformer();
                break;
            case POPUP:
                transformer = new PopupTransformer();
                break;
            default:
                transformer = new DefaultTransformer();
                break;
        }
        return transformer;
    }
}
