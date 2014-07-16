package edu.hastings.hastingscollege.navdrawerfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import edu.hastings.hastingscollege.R;

public class FragmentHome extends Fragment {

    WebView myWebView;
    ViewGroup mRootView;

    public static Fragment newInstance() {
        return new FragmentHome();
    }

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
        myWebView.loadUrl(getString(R.string.home_url));
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = (ViewGroup) inflater.inflate(R.layout.home, container, false);
        return mRootView;
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

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            //hide loading image
            mRootView.findViewById(R.id.homeProgress).setVisibility(View.GONE);
            //Show webview
            myWebView.setVisibility(View.VISIBLE);
        }
    }
}
