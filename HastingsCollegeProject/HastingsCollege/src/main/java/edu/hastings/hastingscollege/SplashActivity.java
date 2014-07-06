package edu.hastings.hastingscollege;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);    // Removes title bar
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,     WindowManager.LayoutParams.FLAG_FULLSCREEN);    // Removes notification bar

        setContentView(R.layout.splash);

        final String URL = "";
        new DownloadXmlTask().execute(URL);
    }

    // Given a string representation of a URL, sets up a connection and gets
    // an input stream.
    private InputStream downloadUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000 /* milliseconds */);
        conn.setConnectTimeout(15000 /* milliseconds */);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        // Starts the query
        conn.connect();
        InputStream stream = conn.getInputStream();
        return stream;
    }

    //Displays an error if the app is unable to load content.
    private void showSodexoError() {
        // The specified network connection is not available. Displays error message.
        Toast.makeText(this,
                "Unable to load feed, Check your internet connection",
                Toast.LENGTH_LONG).show();
    }

    // Implementation of AsyncTask used to download XML feed
    private class DownloadXmlTask extends AsyncTask<String, Void, List<HashMap<String, String>>> {

        @Override
        protected List<HashMap<String, String>> doInBackground(String... urls) {
            InputStream stream = null;
            SodexoXmlParser sodexoXmlParser = new SodexoXmlParser();
            List<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();
            try {
                try {
                    //stream = downloadUrl(urls[0]);
                    stream = getAssets().open("Menu.xml");
                    menuItems = sodexoXmlParser.parse(stream);
                } finally {
                    if (stream != null) {
                        stream.close();
                    }
                }
            } catch (IOException e) {
                // return getResources().getString(R.string.connection_error)
                Log.d("IOException", e.toString());
            } catch (XmlPullParserException e) {
                //return getResources().getString(R.string.xml_error);
                Log.d("XmlPullParserException", e.toString());
            }

            return menuItems;
        }

        @Override
        protected void onPostExecute(List<HashMap<String, String>> menuItems) {
            super.onPostExecute(menuItems);
            if (menuItems != null)
                Data.globalMenuItems = menuItems;
            else
                showSodexoError();
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(i);
            finish();
            // transition from splash to main menu
            overridePendingTransition(R.anim.fadein,
                    R.anim.fadeout);
        }
    }
}