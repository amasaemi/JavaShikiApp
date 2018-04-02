package com.amasaemi.javashikiapp.modules.list.ui.fragments;

import android.os.Bundle;

import com.amasaemi.javashikiapp.modules.base.ui.fragments.BaseFragment;

/**
 * Created by Alex on 04.02.2018.
 */

public final class AboutFragment extends BaseFragment {
    public static final String TAG = AboutFragment.class.getSimpleName();

    private static final AboutFragment ourInstance = new AboutFragment();

    public static AboutFragment getInstance(Bundle bundle) {
        AboutFragment fragment = new AboutFragment();
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
