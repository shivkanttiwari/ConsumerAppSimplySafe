package com.example.tiuadmin.simplysafeconusmerapp.utilsApps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.tiuadmin.simplysafeconusmerapp.R;
import com.example.tiuadmin.simplysafeconusmerapp.Utility.ConnectionDetector;

public class RadioWebViewActivity extends AppCompatActivity {

    WebView webView;
    public static String loadUrl;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_web_view);

        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);

        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);

        progressDialog = new ProgressDialog(RadioWebViewActivity.this);
        progressDialog.getWindow().setBackgroundDrawableResource(R.color.base);
        progressDialog.getWindow().setGravity(Gravity.CENTER);
        // Set the progress dialog to display a horizontal progress bar
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Loading...");

        if (new ConnectionDetector(RadioWebViewActivity.this).isConnectingToInternet()) {
            progressDialog.show();
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
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
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
