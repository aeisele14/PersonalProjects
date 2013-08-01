package edu.hastings.hastingscollege;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * Created by Alex on 7/21/13.
 */
public class FragmentMap extends SupportMapFragment {

    static final LatLng HASTINGS = new LatLng(40.35, 98.22);
    private GoogleMap mMap;
    private SupportMapFragment mMapFragment;

    public FragmentMap(){
        // Empty constructor required for fragment subclasses
    }

    public static SupportMapFragment newInstance(Context context) {
        FragmentMap f = new FragmentMap();
        return f;
    }

    /*@Override
    public void onResume() {
        super.onResume();
        setUpMap();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapFragment.onPause();
    }

    @Override
    public void onDestroy() {
        mMapFragment.onDestroy();
        super.onDestroy();
    }*/

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        boolean connection = hasConnection(getActivity()
                .getApplicationContext());

        if (connection) {
            mMapFragment = ((SupportMapFragment) getActivity().getSupportFragmentManager()
                    .findFragmentById(R.id.map));
            if (mMapFragment == null)
            {
                mMapFragment = SupportMapFragment.newInstance();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.map, mMapFragment).commit();
            }
            mMap = mMapFragment.getMap();
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

            if (savedInstanceState == null)
            {
                // Move the camera instantly to Hastings with a zoom of 15
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HASTINGS, 15));
                // Zoom in, animating the camera.
                mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
            }

            // Set marker on Hastings
            mMap.addMarker(new MarkerOptions().position(HASTINGS)
                    .title("Hastings College")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));
        }
        else
        {
            Toast.makeText(getActivity().getApplicationContext(),
                    "Check Your Internet Connection, Wifi Or Mobile Data Must Be Enabled",
                    Toast.LENGTH_LONG).show();
        }
    }

    public static boolean hasConnection(Context c) {
        ConnectivityManager cm = (ConnectivityManager) c
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiNetwork = cm
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetwork != null && wifiNetwork.isConnected()) {
            return true;
        }

        NetworkInfo mobileNetwork = cm
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileNetwork != null && mobileNetwork.isConnected()) {
            return true;
        }

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return (activeNetwork != null && activeNetwork.isConnected());
    }

    private void setUpMap() {
        if (mMap != null)
            return;
        mMap = mMapFragment.getMap();
        if (mMap == null)
            return;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.map, null);
        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}