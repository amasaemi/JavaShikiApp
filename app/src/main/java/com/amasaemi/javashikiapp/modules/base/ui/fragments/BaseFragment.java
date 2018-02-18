package com.amasaemi.javashikiapp.modules.base.ui.fragments;

import android.databinding.ViewDataBinding;
import android.support.v7.app.AlertDialog;

import com.amasaemi.javashikiapp.R;
import com.amasaemi.javashikiapp.modules.base.mvp.views.ShikiView;
import com.arellomobile.mvp.MvpFragment;

/**
 * Created by Alex on 31.01.2018.
 */

public abstract class BaseFragment extends MvpFragment implements ShikiView {
    // фрагмент запущен впервые
    protected boolean mFragmentIsFirstRun = false;

    protected int getOrientation() {
        return getResources().getConfiguration().orientation;
    }

    protected double getScreenSize() {
        return Math.sqrt(Math.pow((getResources().getDisplayMetrics().widthPixels
                / getResources().getDisplayMetrics().densityDpi), 2.0)
                    + Math.pow((getResources().getDisplayMetrics().heightPixels
                        / getResources().getDisplayMetrics().densityDpi), 2.0));
    }

    @Override
    final public void showDialog(String title, String message) {
        stateLoadIndicator(false);

        new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.label_ok, (di, i) -> di.dismiss());
    }

    public boolean onBackPressed() {
        return true;
    }
}
