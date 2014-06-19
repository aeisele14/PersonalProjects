package edu.hastings.hastingscollege.tabfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import edu.hastings.hastingscollege.R;

/**
 * Created by Alex on 6/18/2014.
 */
public class DinnerFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_dinner, container, false);

        return rootView;
    }
}
