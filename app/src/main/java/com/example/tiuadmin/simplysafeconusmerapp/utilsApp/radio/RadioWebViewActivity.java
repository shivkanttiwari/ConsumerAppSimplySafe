package com.example.tiuadmin.simplysafeconusmerapp.utilsApp.radio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.example.tiuadmin.simplysafeconusmerapp.R;

public class RadioWebViewActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_web_view);

        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);

        String url = "http://gaana.com/radiomirchi";
        webView.loadUrl(url);
    }
}
