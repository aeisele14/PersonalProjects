package edu.hastings.hastingscollege.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import edu.hastings.hastingscollege.tabfragments.BreakfastFragment;
import edu.hastings.hastingscollege.tabfragments.DinnerFragment;
import edu.hastings.hastingscollege.tabfragments.LunchFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new BreakfastFragment();
            case 1:
                return new LunchFragment();
            case 2:
                return new DinnerFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
