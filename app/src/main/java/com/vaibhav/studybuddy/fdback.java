package com.vaibhav.studybuddy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class fdback extends AppCompatActivity {
    ProgressBar progressBar1;
    WebView webview1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fdback);

        progressBar1= (ProgressBar) findViewById(R.id.progressbar1);
        webview1 = (WebView) findViewById(R.id.webview1);

        if (haveNetworkConnection() == true) {
            webview1.setVisibility(View.INVISIBLE);
            webview1.getSettings().setJavaScriptEnabled(true);
            webview1.setWebViewClient(new WebViewClient());
            webview1.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);

                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    progressBar1.setVisibility(View.GONE);
                    webview1.setVisibility(View.VISIBLE);
                }
            });
            webview1.loadUrl("https://goo.gl/forms/9N1TuXSkQ1DI4EU93");

        } else {
            Intent zx = new Intent(fdback.this, nocon.class);
            startActivity(zx);
        }
    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
}

