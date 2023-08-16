package com.caswen.webview;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.caswen.base.autoservice.CaswenServiceLoader;
import com.caswen.common.autoservice.IWebViewService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.open_webviewactivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IWebViewService webviewService = CaswenServiceLoader.load(IWebViewService.class);
                if(webviewService != null) {
                    webviewService.startDemoHtml(MainActivity.this);
                }
            }
        });
    }
}