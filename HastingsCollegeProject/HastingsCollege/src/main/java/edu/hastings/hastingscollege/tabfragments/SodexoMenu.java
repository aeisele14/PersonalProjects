package edu.hastings.hastingscollege.tabfragments;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import edu.hastings.hastingscollege.R;
import edu.hastings.hastingscollege.adapter.TabsPagerAdapter;

public class SodexoMenu extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sodexo_menu);
        ViewPager mViewPager = (ViewPager) findViewById(R.id.pager);

        final String KEY_DAY = "dayname";
        Intent in = getIntent();
        String day = in.getStringExtra(KEY_DAY);

        ActionBar actionBar = getActionBar();
        actionBar.setTitle("Sodexo Menu for " + day);
        actionBar.setDisplayHomeAsUpEnabled(true);
        mViewPager.setAdapter(new TabsPagerAdapter(this.getSupportFragmentManager(), day));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}