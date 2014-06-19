package edu.hastings.hastingscollege.navdrawerfragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import edu.hastings.hastingscollege.R;


/**
 * Created by Alex on 7/21/13.
 */
public class FragmentMap extends Fragment {

    private GoogleMap mMap;
    private SupportMapFragment mMapFragment;

    public FragmentMap(){
        // Empty constructor required for fragment subclasses
    }

    public static Fragment newInstance(Context context) {
        FragmentMap f = new FragmentMap();
        return f;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mMap == null)
        {
            boolean connection = hasConnection(getActivity().getApplicationContext());
            // Zoom Point
            LatLng HASTINGS_CENTER = new LatLng(40.593413, -98.36964);

            // Building locations
            LatLng Kiewett = new LatLng(40.590561, -98.373438);
            LatLng PerkinsLib = new LatLng(40.590246, -98.374471);
            LatLng HurleyMac = new LatLng(40.5907, -98.374554);
            LatLng MorrisonReeves = new LatLng(40.591074, -98.371947);
            LatLng DaughteryCenter = new LatLng(40.591074, -98.373674);
            LatLng Chapel = new LatLng(40.591628, -98.373567);
            LatLng McCormick = new LatLng(40.592288, -98.373674);
            LatLng ScottTheater = new LatLng(40.592321, -98.373299);
            LatLng WilsonCenter = new LatLng(40.592688, -98.37346);
            LatLng AltmanHall = new LatLng(40.592337, -98.372022);
            LatLng TaylorHall = new LatLng(40.592631, -98.374629);
            LatLng BabcockHall = new LatLng(40.593095, -98.374597);
            LatLng BroncHall = new LatLng(40.593071, -98.372226);
            LatLng WeyerHall = new LatLng(40.59391, -98.37184);
            LatLng HSU = new LatLng(40.593869, -98.372966);
            LatLng FuhrHall = new LatLng(40.594431, -98.371829);
            LatLng GrayCenter = new LatLng(40.595596, -98.373063);
            LatLng PFF = new LatLng(40.594625, -98.369629);
            LatLng Fleharty = new LatLng(40.595839, -98.370638);
            LatLng BarretAlum = new LatLng(40.595308, -98.364667);
            LatLng BroncoVillApart = new LatLng(40.594408, -98.365992);
            LatLng Maintenance = new LatLng(40.592653, -98.371067);
            LatLng House710 = new LatLng(40.58967, -98.373127);
            LatLng House714 = new LatLng(40.589703, -98.372692);
            LatLng HouseMckay = new LatLng(40.593561, -98.374983);
            LatLng PineKnoll1 = new LatLng(40.593558, -98.371051);
            LatLng PineKnoll2 = new LatLng(40.593961, -98.371051);
            //LatLng FootballField = new LatLng(1, 2);

            if (connection)
            {
                mMap = mMapFragment.getMap();

                if  (mMap != null) {
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                    // Move the camera instantly to Hastings with a zoom of 15
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HASTINGS_CENTER, 16));
                    // Zoom in, animating the camera.
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(16), 2000, null);

                    // Set markers on Hastings College Campus
                    //addMapMarker(Kiewett,);
                }
            }
            else
            {
                Toast.makeText(getActivity().getApplicationContext(),
                        "Check Your Internet Connection, Wifi Or Mobile Data Must Be Enabled",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        try {
            //Fragment fragment = (getChildFragmentManager().findFragmentById(R.id.map));
            getChildFragmentManager().beginTransaction().remove(mMapFragment).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mMapFragment == null){
            mMapFragment = SupportMapFragment.newInstance();
            getChildFragmentManager().beginTransaction().replace(R.id.map, mMapFragment).commit();
        }
    }

    public void addMapMarker(LatLng position, String title, String snippet) {
        mMap.addMarker(new MarkerOptions()
                .position(position)
                .title(title)
                .snippet(snippet));
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.map, null);
        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            MapsInitializer.initialize(this.getActivity());
        } catch (GooglePlayServicesNotAvailableException e){
            e.printStackTrace();
        }
    }
}