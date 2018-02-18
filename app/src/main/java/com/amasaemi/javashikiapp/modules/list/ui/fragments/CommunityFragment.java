package com.amasaemi.javashikiapp.modules.list.ui.fragments;

import android.os.Bundle;

import com.amasaemi.javashikiapp.modules.base.ui.fragments.BaseFragment;

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
