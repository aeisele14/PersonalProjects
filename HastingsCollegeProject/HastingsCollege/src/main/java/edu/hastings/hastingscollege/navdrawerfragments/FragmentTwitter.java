package edu.hastings.hastingscollege.navdrawerfragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import edu.hastings.hastingscollege.R;

public class FragmentTwitter extends Fragment{

    public static final String TAG = "FragmentTwitter";

    public static Fragment newInstance(Context context) { return new FragmentTwitter(); }

    WebView myWebView;
    ViewGroup mRootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myWebView = (WebView) mRootView.findViewById(R.id.webview);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setBuiltInZoomControls(true);
        myWebView.setWebViewClient(new MyWebViewClient());
       // myWebView.loadDataWithBaseURL(baseURl, widgetInfo, "text/html", "UTF-8", null);
        myWebView.loadUrl(getString(R.string.twitter_widget_host_url));
        myWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    WebView webView = (WebView) view;

                    switch (i) {
                        case KeyEvent.KEYCODE_BACK:
                            if (webView.canGoBack()) {
                                webView.goBack();
                                return true;
                            }
                            break;
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void onDestroy() {
        if (myWebView != null) {
            mRootView.removeView(myWebView);
            myWebView.removeAllViews();
            myWebView.clearCache(true);
            myWebView.clearHistory();
            myWebView.destroy();
        }
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = (ViewGroup) inflater.inflate(R.layout.twitter, container, false);
        return mRootView;
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            //hide loading image
            mRootView.findViewById(R.id.twitterProgress).setVisibility(View.GONE);
            //Show webview
            mRootView.findViewById(R.id.webview).setVisibility(View.VISIBLE);
        }
    }
}
