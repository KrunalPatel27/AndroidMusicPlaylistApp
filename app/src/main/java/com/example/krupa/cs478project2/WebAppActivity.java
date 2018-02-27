package com.example.krupa.cs478project2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebAppActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_app);
        webView = (WebView)findViewById(R.id.web_view);
        String URL = getIntent().getStringExtra("URL");
        webView.loadUrl(URL);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
