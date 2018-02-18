package com.amasaemi.javashikiapp.modules.list.ui.fragments;

import android.os.Bundle;

import com.amasaemi.javashikiapp.modules.base.ui.fragments.BaseFragment;

/**
 * Created by Alex on 04.02.2018.
 */

public class SettingsFragment extends BaseFragment {
    public static final String TAG = SettingsFragment.class.getSimpleName();

    private static final SettingsFragment ourInstance = new SettingsFragment();

    public static SettingsFragment getInstance(Bundle bundle) {
        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void stateLoadIndicator(Boolean state) {

    }

    @Override
    public void showRetrySnackbar(Runnable action) {

    }

    @Override
    public void stateVisibilityContainer(Boolean state) {

    }
}
