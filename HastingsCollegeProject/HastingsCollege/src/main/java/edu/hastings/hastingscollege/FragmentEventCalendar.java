package edu.hastings.hastingscollege;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Alex on 7/21/13.
 */
public class FragmentEventCalendar extends Fragment {

    public static Fragment newInstance(Context context) {
        FragmentEventCalendar f = new FragmentEventCalendar();

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.event_calendar, null);
        return root;
    }
}
