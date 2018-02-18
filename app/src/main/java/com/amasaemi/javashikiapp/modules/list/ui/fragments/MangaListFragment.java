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

public class MangaListFragment extends BaseListFragment implements ShikiListView<TitleListItemResponse> {
    public static final String TAG = MangaListFragment.class.getSimpleName();

    public static MangaListFragment getInstance(Bundle bundle) {
        MangaListFragment fragment = new MangaListFragment();
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
