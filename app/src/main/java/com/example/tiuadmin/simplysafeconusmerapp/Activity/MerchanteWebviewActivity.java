package com.example.tiuadmin.simplysafeconusmerapp.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.tiuadmin.simplysafeconusmerapp.R;

public class MerchanteWebviewActivity extends AppCompatActivity {

    WebView webView;
    ProgressDialog progressDialog;
    String MerchantURL;
    String Passkey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchante_webview);



        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                MerchantURL= null;
            } else {
                MerchantURL= extras.getString("MerchantURL");
                Passkey= extras.getString("Passkey");
            }
        } else {
            MerchantURL= (String) savedInstanceState.getSerializable("MerchantURL");
            Passkey= (String) savedInstanceState.getSerializable("Passkey");
        }

        webView=(WebView)findViewById(R.id.webview);

        webView.getSettings().setJavaScriptEnabled(true);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);

        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);

        progressDialog = new ProgressDialog(MerchanteWebviewActivity.this);
        progressDialog.getWindow().setBackgroundDrawableResource(R.color.base);
        progressDialog.getWindow().setGravity(Gravity.CENTER);
        // Set the progress dialog to display a horizontal progress bar
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(MerchanteWebviewActivity.this, "Error:" + description, Toast.LENGTH_SHORT).show();

            }
        });
       // webView.loadUrl(url);
        webView.loadUrl(MerchantURL+"/shop?"+Passkey);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        new AlertDialog.Builder(this)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setTitle("Closing Merchant Shop")
                                .setMessage("Are you sure you want to close this shop?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }

                                })
                                .setNegativeButton("No", null)
                                .show();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }
}
