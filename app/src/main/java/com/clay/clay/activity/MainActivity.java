package com.clay.clay.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.clay.clay.R;
import com.clay.clay.fragment.HomeFragment;
import com.clay.clay.fragment.SettingsFragment;
import com.clay.clay.model.Door;
import com.clay.clay.util.PreferenceUtil;
import com.clay.clay.view.SlidingTabLayout;


public class MainActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener,
        SettingsFragment.OnFragmentInteractionListener {
    private String[] mTabTitles = {"Doors", "Settings"};


    private HomePagerAdapter mHomePagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sign_out:
                signOut();
                break;
        }
        return true;
    }

    private void signOut() {
        PreferenceUtil.Session.setUserId(this,"");
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mViewPager = (ViewPager) findViewById(R.id.home_pager);
        mHomePagerAdapter = new HomePagerAdapter(getSupportFragmentManager(), mTabTitles);
        mViewPager.setAdapter(mHomePagerAdapter);

        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(mViewPager);

    }

    @Override
    public void doorAdded(Door door) {
        mHomePagerAdapter.notifyDataSetChanged();
        mViewPager.setCurrentItem(0);
        String homeFragmentTag = "android:switcher:"+R.id.home_pager+":"+0;
        HomeFragment homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(homeFragmentTag);
        homeFragment.updateViews();
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
