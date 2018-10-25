package com.yakin.fastpager.router;

import android.content.Intent;
import android.os.Bundle;

import com.yakin.fastpager.AbstractPage;

public class PageRouter implements IPageRouter {

    private PageRouter() {
        // TODO Auto-generated constructor stub
    }

    private static class SingletonHolder {
        private static PageRouter INSTANCE = new PageRouter();
    }

    public static PageRouter getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private IPageRouter router;

    public void setRouter(IPageRouter router) {
        this.router = router;
    }

    @Override
    public void startPage(Class<? extends AbstractPage> clazz) {
        if(router != null) {
            router.startPage(clazz);
        }
    }

    @Override
    public void startPage(Class<? extends AbstractPage> clazz, Bundle bundle) {
        if(router != null) {
            router.startPage(clazz, bundle);
        }
    }

    @Override
    public void finishPage(AbstractPage page) {
        if(router != null) {
            router.finishPage(page);
        }
    }

    @Override
    public void finishPage(AbstractPage page, int code, Intent data) {
        if(router != null) {
            router.finishPage(page, code, data);
        }
    }

    @Override
    public void backPage() {
        if(router != null) {
            router.backPage();
        }
    }
}
