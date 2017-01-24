package com.example.tiuadmin.simplysafeconusmerapp.utilsApps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.tiuadmin.simplysafeconusmerapp.R;
import com.example.tiuadmin.simplysafeconusmerapp.Utility.ConnectionDetector;

public class RadioWebViewActivity extends AppCompatActivity {

    WebView webView;
    public static String loadUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_web_view);

        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);

        if (new ConnectionDetector(RadioWebViewActivity.this).isConnectingToInternet()) {
            //ll.setVisibility(View.VISIBLE);
            webView.loadUrl(loadUrl);
            webView.setWebViewClient(new WebViewClient() {
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    //Log.i(TAG, "Processing webview url click...");
                    view.loadUrl(url);
                    return true;
                }

                public void onPageFinished(WebView view, String url) {
                    //Log.i(TAG, "Finished loading URL: " + url);

//                    if (prefManager.isExpired()) {
//                        finish();
//                    } else {
//                        if (isExpiredDate()) {
//                            prefManager.setExpired(true);
//                            finish();
//                        } else {
                    if (new ConnectionDetector(RadioWebViewActivity.this).isConnectingToInternet()) {
//                        if (ll.getVisibility() == View.VISIBLE) {
//                            ll.setVisibility(View.GONE);
//                        }
                    } else {
                        Intent mainIntent = new Intent(RadioWebViewActivity.this, NoInternetActivity.class);
                        startActivity(mainIntent);
                        finish();
                    }
                    //}
                    // }
                }
            });

        } else {
            Intent mainIntent = new Intent(RadioWebViewActivity.this, NoInternetActivity.class);
            startActivity(mainIntent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }
}
