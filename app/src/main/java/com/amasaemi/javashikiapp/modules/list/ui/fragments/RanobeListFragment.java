package com.amasaemi.javashikiapp.modules.list.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;

import com.amasaemi.javashikiapp.R;
import com.amasaemi.javashikiapp.data.network.pojo.req.SearchingParams;
import com.amasaemi.javashikiapp.modules.list.mvp.presenters.ShikiTitleListPresenter;
import com.amasaemi.javashikiapp.modules.list.mvp.presenters.RanobeListPresenter;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by Alex on 04.02.2018.
 */

public class RanobeListFragment extends BaseListTitleFragment {
    public static final String TAG = RanobeListFragment.class.getSimpleName();

    public static RanobeListFragment getInstance(Bundle bundle) {
        RanobeListFragment fragment = new RanobeListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @InjectPresenter
    RanobeListPresenter mPresenter;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        SearchView searchView = (SearchView) menu.findItem(R.id.list_menu_search).getActionView();
        searchView.setQueryHint(getResources().getStringArray(R.array.finding_hints)[2]);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                onBackPressed();

                // выполняем поисковой запрос
                mPresenter.findTitlesByParams(new SearchingParams(query));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    @Override
    protected ShikiTitleListPresenter getPresenter() {
        return mPresenter;
    }
}
