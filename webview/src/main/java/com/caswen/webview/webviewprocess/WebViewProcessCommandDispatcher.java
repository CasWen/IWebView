package com.caswen.webview.webviewprocess;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.caswen.base.BaseApplication;
import com.caswen.webview.ICallbackFromMainprocessToWebViewProcessInterface;
import com.caswen.webview.IWebviewProcessToMainProcessInterface;
import com.caswen.webview.mainprocess.MainProcessCommandService;

public class WebViewProcessCommandDispatcher implements ServiceConnection {
    private static WebViewProcessCommandDispatcher sInstance;
    private IWebviewProcessToMainProcessInterface iWebviewProcessToMainProcessInterface;

    public static WebViewProcessCommandDispatcher getInstance() {
        if (sInstance == null) {
            synchronized (WebViewProcessCommandDispatcher.class) {
                sInstance = new WebViewProcessCommandDispatcher();
            }
        }
        return sInstance;
    }

    public void initAidlConnection(){
        Intent intent = new Intent(BaseApplication.sApplication, MainProcessCommandService.class);
        BaseApplication.sApplication.bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        iWebviewProcessToMainProcessInterface = IWebviewProcessToMainProcessInterface.Stub.asInterface(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        iWebviewProcessToMainProcessInterface = null;
        initAidlConnection();
    }

    @Override
    public void onBindingDied(ComponentName name) {
        iWebviewProcessToMainProcessInterface = null;
        initAidlConnection();
    }

    public void executeCommand(String commandName, String params, final BaseWebView baseWebView) {
        if(iWebviewProcessToMainProcessInterface != null) {
            try {
                iWebviewProcessToMainProcessInterface.handleWebCommand(commandName, params, new ICallbackFromMainprocessToWebViewProcessInterface.Stub() {
                    @Override
                    public void onResult(String callbackname, String response) throws RemoteException {
                        baseWebView.handleCallback(callbackname, response);
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
