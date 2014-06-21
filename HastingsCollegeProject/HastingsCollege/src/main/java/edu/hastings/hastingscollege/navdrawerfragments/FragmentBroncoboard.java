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
import android.widget.Toast;
import edu.hastings.hastingscollege.Connection;
import edu.hastings.hastingscollege.R;

public class FragmentBroncoboard extends Fragment {

    WebView myWebView;

    public static Fragment newInstance(Context context) { return new FragmentBroncoboard(); }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        boolean connection = new Connection().hasConnection(getActivity());

        if (connection) {
            myWebView = (WebView) getView().findViewById(R.id.webview);
            myWebView.getSettings().setJavaScriptEnabled(true);
            myWebView.setWebViewClient(new MyWebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    // hide loading image
                    getView().findViewById(R.id.broncoBoardProgress).setVisibility(View.GONE);
                    // Show webview
                    getView().findViewById(R.id.webview).setVisibility(View.VISIBLE);
                }
            });
            myWebView.loadUrl(getString(R.string.broncoboard_url));
            myWebView.getSettings().setBuiltInZoomControls(true);

            myWebView.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View view, int i, KeyEvent event) {
                    if (event.getAction() == KeyEvent.ACTION_DOWN) {
                        WebView webView = (WebView) view;
                        switch (i) {
                            case KeyEvent.KEYCODE_BACK:
                                if(webView.canGoBack()) {
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
        else {
            Toast.makeText(getActivity(),
                    "Check Your Internet Connection, Wifi Or Mobile Data Must Be Enabled",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return (ViewGroup) inflater.inflate(R.layout.broncoboard, container, false);
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
