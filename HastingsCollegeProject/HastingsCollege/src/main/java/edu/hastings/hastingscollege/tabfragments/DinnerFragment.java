package edu.hastings.hastingscollege.tabfragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.hastings.hastingscollege.R;
import edu.hastings.hastingscollege.SodexoXmlParser;

/**
 * Created by Alex on 6/18/2014.
 */
public class DinnerFragment extends Fragment {

    final String KEY_ITEM_DATE = "menudate";
    final String KEY_DAY = "dayname";
    final String KEY_MEAL = "meal";
    final String KEY_ITEM_NAME = "item_name";
    final String KEY_ITEM_DESC = "item_desc";
    final String URI = "";

    private ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_dinner, container, false);
        mListView = (ListView) rootView.findViewById(R.id.dinnerList);

        new DownloadXmlTask().execute(URI);
        return rootView;
    }

    private List<HashMap<String, String>> getLunchItems(List<HashMap<String, String>> menuItems) {
        List<HashMap<String, String>> lunchItems = new ArrayList<HashMap<String, String>>();

        for (HashMap<String, String> menuItem : menuItems) {
            if (menuItem.get(KEY_MEAL).equals("Dinner")) {
                lunchItems.add(menuItem);
            }
        }
        return lunchItems;
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
                    stream = getActivity().getAssets().open("Menu.xml");
                    menuItems = sodexoXmlParser.parse(stream);
                    // Makes sure that the InputStream is closed after the app is
                    // finished using it.
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

            String[] from = { KEY_ITEM_NAME, KEY_ITEM_DESC };
            int[] to = {R.id.item_name, R.id.item_desc};

            SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(),
                    getLunchItems(menuItems),
                    R.layout.list_item_sodexo,
                    from,
                    to);

            mListView.setAdapter(adapter);
        }
    }
}
