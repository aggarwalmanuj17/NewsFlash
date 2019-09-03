package com.manuj.projectwork;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DetailedNews extends AppCompatActivity {

    WebView webView1;

    ProgressDialog mProgressDialog;


    private class detailedNews extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.setVisibility(View.GONE);
            mProgressDialog.setTitle("Loading");
            mProgressDialog.show();
            mProgressDialog.setMessage("Loading " + url);
            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            mProgressDialog.dismiss();
            animate(view);
            view.setVisibility(View.VISIBLE);
            super.onPageFinished(view, url);
        }
    }

    private void animate(final WebView view) {
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),
                android.R.anim.slide_in_left);
        view.startAnimation(anim);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        WebView view = (WebView) findViewById(R.id.webView);
        if ((keyCode == KeyEvent.KEYCODE_BACK) && view.canGoBack()) {
            view.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_news);

        getSupportActionBar().setTitle("News");
        mProgressDialog = new ProgressDialog(this);


        webView1 = findViewById(R.id.webView);


        Intent rcv = getIntent();

        String url = rcv.getStringExtra("keyUrl");

        WebViewClient client = new WebViewClient();


        webView1.getSettings().setJavaScriptEnabled(true);
        webView1.loadUrl(url);
        webView1.setWebViewClient(new detailedNews());
    }
}
