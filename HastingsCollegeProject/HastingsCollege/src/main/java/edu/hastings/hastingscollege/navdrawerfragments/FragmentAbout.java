package edu.hastings.hastingscollege.navdrawerfragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.InputStream;

import edu.hastings.hastingscollege.R;

public class FragmentAbout extends Fragment {
    public static Fragment newInstance(Context context) { return new FragmentAbout(); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = (ViewGroup) inflater.inflate(R.layout.about, null);

        new DownloadImageTask((ImageView) rootView.findViewById(R.id.aboutImage)).execute(getString(R.string.about_image_url));

        return rootView;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
            bmImage.setScaleType(ImageView.ScaleType.FIT_XY);
            getView().findViewById(R.id.aboutProgress).setVisibility(View.GONE);
            bmImage.setVisibility(View.VISIBLE);
        }
    }
}
