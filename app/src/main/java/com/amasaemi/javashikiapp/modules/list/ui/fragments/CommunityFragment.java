package com.amasaemi.javashikiapp.modules.list.ui.fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.amasaemi.javashikiapp.R;
import com.amasaemi.javashikiapp.modules.base.ui.fragments.BaseFragment;
import com.amasaemi.javashikiapp.modules.community.ui.fragments.ClubsFragments;
import com.amasaemi.javashikiapp.modules.community.ui.fragments.UsersFragment;

/**
 * Created by Alex on 04.02.2018.
 */

public class CommunityFragment  extends BaseFragment {
    public static final String TAG = CommunityFragment.class.getSimpleName();

    public static CommunityFragment getInstance(Bundle bundle) {
        CommunityFragment fragment = new CommunityFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    private TabLayout mTabLayout;
    private ViewPager mPager;
    private SectionsPagerAdapter mPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);

        AppBarLayout barLayout = new AppBarLayout(getActivity());

        mPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());

        mPager = new ViewPager(getActivity());
        mPager.setId(1456);
        mPager.setAdapter(mPagerAdapter);

        mTabLayout = new TabLayout(getActivity());
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mPager);

        barLayout.addView(mTabLayout);
        layout.addView(barLayout);
        layout.addView(mPager);

        return layout;
    }

    @Override
    public void stateLoadIndicator(Boolean state) {
        return;
    }

    @Override
    public void stateVisibilityContainer(Boolean state) {
        return;
    }

    @Override
    public void showRetrySnackbar(Runnable action) {
        Snackbar.make(mPager, R.string.error_retry, Snackbar.LENGTH_SHORT)
                .setAction(R.string.label_retry, (_bar) -> action.run()).show();
    }

    private class SectionsPagerAdapter extends FragmentPagerAdapter {
        private SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch (pos) {
                case 0: return UsersFragment.getInstance(null);
                case 1: return ClubsFragments.getInstance(null);
                default: throw new NullPointerException(String.format("%d is not valid index of adapter", pos));
            }
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int pos) {
            switch (pos) {
                case 0: return getString(R.string.caption_users);
                case 1: return getString(R.string.caption_clubs);
                default: throw new NullPointerException(String.format("%d is not valid index title of adapter", pos));
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
