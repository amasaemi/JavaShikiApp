package com.amasaemi.javashikiapp.modules.list.ui.fragments;

import android.os.Bundle;

import com.amasaemi.javashikiapp.data.network.pojo.res.TitleListItemResponse;
import com.amasaemi.javashikiapp.modules.base.mvp.presenters.BaseListPresenter;
import com.amasaemi.javashikiapp.modules.base.mvp.views.ShikiListView;
import com.amasaemi.javashikiapp.modules.base.ui.fragments.BaseListFragment;

import java.util.List;

/**
 * Created by Alex on 04.02.2018.
 */

public class RanobeListFragment extends BaseListFragment implements ShikiListView<TitleListItemResponse> {
    public static final String TAG = RanobeListFragment.class.getSimpleName();

    private static final RanobeListFragment ourInstance = new RanobeListFragment();

    public static RanobeListFragment getInstance(Bundle bundle) {
        RanobeListFragment fragment = new RanobeListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected BaseListPresenter getPresenter() {
        return null;
    }

    @Override
    public void fillAdapter(List<TitleListItemResponse> list) {

    }

    @Override
    public void clearAdapter() {

    }

    @Override
    public void showRetrySnackbar(Runnable action) {

    }

    @Override
    protected void setupRecyclerView() {

    }

    @Override
    public void initialFragment() {

    }
}
