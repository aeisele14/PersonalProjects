package edu.hastings.hastingscollege;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Alex on 7/21/13.
 */
public class FragmentHome extends Fragment {

    public static Fragment newInstance(Context context){
        FragmentHome f = new FragmentHome();
        return f;
    }

    WebView myWebView;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myWebView = (WebView) getView().findViewById(R.id.webview);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new MyWebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                //hide loading image
                getView().findViewById(R.id.homeProgress).setVisibility(View.GONE);
                //Show webview
                getView().findViewById(R.id.webview).setVisibility(View.VISIBLE);
            }
        });
        myWebView.loadUrl(getString(R.string.home_url));

        myWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    WebView webView = (WebView) view;

                    switch (i)
                    {
                        case KeyEvent.KEYCODE_BACK:
                            if(webView.canGoBack())
                            {
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
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.home, null);
        return root;
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //if (Uri.parse(url).getHost().equals("http://catchfiredevprojects.com/hastings/homepage.html#"))
                return false;
            //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            //startActivity(intent);
            //return true;
        }
    }
}
