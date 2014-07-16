package edu.hastings.hastingscollege.navdrawerfragments;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import edu.hastings.hastingscollege.Connection;
import edu.hastings.hastingscollege.R;
import edu.hastings.hastingscollege.map_db.LocationsDB;

import java.io.IOException;

public class FragmentMap extends Fragment {

    private GoogleMap mMap;
    private SupportMapFragment mMapFragment;
    private SQLiteDatabase mDB;

    public FragmentMap() {
        // Empty constructor required for fragment subclasses
    }

    public static Fragment newInstance(Context context) {
        return new FragmentMap();
    }

    @Override
    public void onResume() {
        super.onResume();
        // Zoom Point
        final LatLng HASTINGS_CENTER = new LatLng(40.593413, -98.36964);
        mMap = mMapFragment.getMap();
        LocationsDB locationsDB = null;
        try {
            locationsDB = new LocationsDB(getActivity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        mDB = locationsDB.getReadableDatabase();
        Cursor cursor = locationsDB.getData(mDB);

        int columns = cursor.getCount();

        if (mMap != null) {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

            if (columns == 0)
                Toast.makeText(getActivity(), "No Data",
                        Toast.LENGTH_LONG).show();
            else {
                addMapMarkerFromDB(cursor);
            }
            cursor.close();
            mDB.close();

            // Move the camera instantly to Hastings with a zoom of 15
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HASTINGS_CENTER, 15));
            // Zoom in, animating the camera.
            mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
        }
    }

    private void addMapMarkerFromDB(Cursor cursor) {
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String latitude = cursor.getString(1);
            String longitude = cursor.getString(2);
            String title = cursor.getString(3);
            final String snippet = cursor.getString(4);
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude)))
                    .title(title)
                    .snippet(snippet));
            cursor.moveToNext();
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
        if (mMapFragment == null) {
            mMapFragment = SupportMapFragment.newInstance();
            getChildFragmentManager().beginTransaction().replace(R.id.map, mMapFragment).commit();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return (ViewGroup) inflater.inflate(R.layout.map, null);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            MapsInitializer.initialize(this.getActivity());
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }
}