package com.amasaemi.javashikiapp.modules.community.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.amasaemi.javashikiapp.R;
import com.amasaemi.javashikiapp.data.network.pojo.res.ClubsListResponse;
import com.amasaemi.javashikiapp.modules.base.adapters.SimpleRecyclerAdapter;
import com.amasaemi.javashikiapp.modules.base.mvp.views.ShikiListView;
import com.amasaemi.javashikiapp.modules.base.ui.fragments.BaseListFragment;
import com.amasaemi.javashikiapp.modules.community.models.CommunityCardModel;
import com.amasaemi.javashikiapp.modules.community.mvp.presenters.ClubsPresenter;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alex on 22.02.2018.
 */

public class ClubsFragments extends BaseListFragment implements ShikiListView<ClubsListResponse> {
    public static final String TAG = ClubsFragments.class.getSimpleName();

    public static ClubsFragments getInstance(Bundle bundle) {
        ClubsFragments fragment = new ClubsFragments();
        fragment.setArguments(bundle);
        return fragment;
    }

    @InjectPresenter
    ClubsPresenter mPresenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        setRecyclerEndlessListener(() -> getPresenter().loadNextPage());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        menu.clear();
        inflater.inflate(R.menu.menu_list, menu);
        // отключаем иконку смены вида
        menu.findItem(R.id.list_menu_sort).setVisible(false);

        SearchView searchView = (SearchView) menu.findItem(R.id.list_menu_search).getActionView();
        searchView.setQueryHint(getResources().getStringArray(R.array.finding_hints)[4]);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                onBackPressed();

                // выполняем поисковой запрос
                mPresenter.getClubsList(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    @Override
    protected ClubsPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void fillAdapter(List<ClubsListResponse> list) {
        new Thread(() -> {
            SimpleRecyclerAdapter<CommunityCardModel> adapter = (SimpleRecyclerAdapter<CommunityCardModel>) mBinding.container.getAdapter();
            adapter.addItems(mapTo(list));

            stateVisibilityContainer(!adapter.isEmpty());
        }).run();
    }

    @Override
    public void clearAdapter() {
        ((SimpleRecyclerAdapter) mBinding.container.getAdapter()).clearItems();
    }

    @Override
    protected void setupRecyclerView() {
        // устанавливаем менеджер recyclerView
        setupRecyclerViewLayoutManager(new int[] {1, 2, 3, 1, 2, 3, 2, 3, 4, 2, 3, 4});
        // назначаем адаптер
        createRecyclerViewAdapter(new SimpleRecyclerAdapter<CommunityCardModel>(R.layout.card_community_long));
    }

    /**
     * Метод приводит элементы list к ListCardModel
     * @param list - входной список
     * @return
     */
    private List<CommunityCardModel> mapTo(List<ClubsListResponse> list) {
        List<CommunityCardModel> mapResult = new LinkedList<>();

        for (ClubsListResponse i : list)
            mapResult.add(new CommunityCardModel(getActivity(), i));

        return mapResult;
    }
}
