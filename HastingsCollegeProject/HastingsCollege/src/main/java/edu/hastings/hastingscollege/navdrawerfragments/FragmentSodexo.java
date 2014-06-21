package edu.hastings.hastingscollege.navdrawerfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import edu.hastings.hastingscollege.R;
import edu.hastings.hastingscollege.adapter.TabsPagerAdapter;

import java.lang.reflect.Field;

public class FragmentSodexo extends Fragment
{
    public static final String TAG = FragmentSodexo.class.getSimpleName();
    private ViewPager mViewPager;

    public static FragmentSodexo newInstance() {
        return new FragmentSodexo();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sodexo, container, false);

        mViewPager = (ViewPager) view.findViewById(R.id.pager);
        mViewPager.setAdapter(new TabsPagerAdapter(getChildFragmentManager()));

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class
                    .getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
