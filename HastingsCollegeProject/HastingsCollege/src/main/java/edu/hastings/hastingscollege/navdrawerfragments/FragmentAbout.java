package edu.hastings.hastingscollege.navdrawerfragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.hastings.hastingscollege.R;

/**
 * Created by Alex on 7/1/2014.
 */
public class FragmentAbout extends Fragment {
    public static Fragment newInstance(Context context) { return new FragmentAbout(); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return (ViewGroup) inflater.inflate(R.layout.about, null);
    }
}
