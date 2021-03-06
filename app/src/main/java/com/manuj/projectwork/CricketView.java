package com.manuj.projectwork;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class CricketView extends Fragment {
    WebView webView;
    ProgressDialog progress;

    public CricketView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cricket_view, container, false);

        webView = (WebView)view.findViewById(R.id.webViewcricket);

        WebViewClient client = new WebViewClient();
        webView.setWebViewClient(client);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(false);
        String url = "https://www.cricbuzz.com/";



        webView.loadUrl(url);

        progress= new ProgressDialog(getActivity());
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView rootview, String url) {

                progress.setMessage("Please wait...");
                progress.setIndeterminate(false);
                progress.setCancelable(false);
                progress.show();
                Log.i("progress show","progressbar");
                rootview.loadUrl(url);
                return true;
            }
            @Override
            public void onPageFinished(WebView view, final String url) {
                progress.dismiss();
                Log.i("progress dismiss","progressbar");

            }
        });

        return view;

    }



}
