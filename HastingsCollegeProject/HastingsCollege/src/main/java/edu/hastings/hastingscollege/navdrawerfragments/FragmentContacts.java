package edu.hastings.hastingscollege.navdrawerfragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import edu.hastings.hastingscollege.R;

public class FragmentContacts extends Fragment {

    public static Fragment newInstance(Context context) { return new FragmentContacts(); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return (ViewGroup) inflater.inflate(R.layout.contacts, null);
    }
}
