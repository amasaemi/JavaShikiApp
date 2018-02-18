package com.amasaemi.javashikiapp.modules.list.ui.fragments;

import android.os.Bundle;

import com.amasaemi.javashikiapp.modules.base.ui.fragments.BaseFragment;

/**
 * Created by Alex on 04.02.2018.
 */

public class NewsFragment extends BaseFragment {
    public static final String TAG = NewsFragment.class.getSimpleName();

    public static NewsFragment getInstance(Bundle bundle) {
        NewsFragment fragment = new NewsFragment();
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
