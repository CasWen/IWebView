// ICallbackFromMainprocessToWebViewProcessInterface.aidl
package com.caswen.webview;

interface ICallbackFromMainprocessToWebViewProcessInterface {
    void onResult(String callbackname, String response);
}
