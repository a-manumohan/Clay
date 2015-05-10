package com.clay.clay.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.clay.clay.R;
import com.clay.clay.fragment.HomeFragment;
import com.clay.clay.fragment.SettingsFragment;
import com.clay.clay.model.Door;
import com.clay.clay.view.SlidingTabLayout;


public class MainActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener,
        SettingsFragment.OnFragmentInteractionListener {
    private String[] mTabTitles = {"Doors", "Settings"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.home_pager);
        HomePagerAdapter homePagerAdapter = new HomePagerAdapter(getSupportFragmentManager(), mTabTitles);
        viewPager.setAdapter(homePagerAdapter);

        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(viewPager);

    }

    @Override
    public void doorAdded(Door door) {

    }

    public class HomePagerAdapter extends FragmentPagerAdapter {
        private String[] tabTitles;

        public HomePagerAdapter(FragmentManager fm, String[] titles) {
            super(fm);
            tabTitles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return HomeFragment.newInstance();
                case 1:
                    return SettingsFragment.newInstance();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }

}
