package com.caswen.webview;

import com.kingja.loadsir.core.LoadSir;
import com.caswen.base.BaseApplication;
import com.caswen.base.loadsir.CustomCallback;
import com.caswen.base.loadsir.EmptyCallback;
import com.caswen.base.loadsir.ErrorCallback;
import com.caswen.base.loadsir.LoadingCallback;
import com.caswen.base.loadsir.TimeoutCallback;

public class CaswenWebViewApplication extends BaseApplication {

    @Override
    public void onCreate(){
        super.onCreate();
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();
    }
}
