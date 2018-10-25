package com.yakin.fastpager.router;

import android.content.Intent;
import android.os.Bundle;

import com.yakin.fastpager.AbstractPage;

public interface IPageRouter {

    void startPage(Class<? extends AbstractPage> clazz);

    void startPage(Class<? extends AbstractPage> clazz, Bundle bundle);

    void finishPage(AbstractPage page);

    void finishPage(AbstractPage page, int code, Intent data);

    void backPage();
}
