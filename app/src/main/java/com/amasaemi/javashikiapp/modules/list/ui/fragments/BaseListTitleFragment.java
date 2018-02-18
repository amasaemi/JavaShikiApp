package com.amasaemi.javashikiapp.modules.list.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.amasaemi.javashikiapp.R;
import com.amasaemi.javashikiapp.data.network.pojo.res.TitleListItemResponse;
import com.amasaemi.javashikiapp.modules.base.adapters.SimpleRecyclerAdapter;
import com.amasaemi.javashikiapp.modules.base.mvp.presenters.ShikiTitleListPresenter;
import com.amasaemi.javashikiapp.modules.base.mvp.views.ShikiListView;
import com.amasaemi.javashikiapp.modules.base.ui.fragments.BaseListFragment;
import com.amasaemi.javashikiapp.modules.list.ui.models.ListCardModel;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alex on 18.02.2018.
 */

public abstract class BaseListTitleFragment extends BaseListFragment implements ShikiListView<TitleListItemResponse> {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        // вешаем слушатель конца списка
        serRecyclerEndlessListener(() -> getPresenter().loadNextPage());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        menu.clear();
        inflater.inflate(R.menu.menu_list, menu);
        // настраиваем обработчик нажатия по меню смены вида
        menu.findItem(R.id.list_menu_sort)
                .setOnMenuItemClickListener((item) -> { changeRecyclerManager(); return true; });
    }

    @Override
    public void fillAdapter(List<TitleListItemResponse> list) {
        new Thread(() -> {
            SimpleRecyclerAdapter<ListCardModel> adapter = (SimpleRecyclerAdapter<ListCardModel>) mBinding.container.getAdapter();
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
        setupRecyclerViewLayoutManager(null);
        int itemLayout = listStyleIsShort() ? R.layout.card_simple_short : R.layout.card_list_item_long;
        // назначаем адаптер
        createRecyclerViewAdapter(new SimpleRecyclerAdapter<ListCardModel>(itemLayout));
    }

    @Override
    protected abstract ShikiTitleListPresenter getPresenter();

    /**
     * Метод приводит элементы list к ListCardModel
     * @param list - входной список
     * @return
     */
    private List<ListCardModel> mapTo(List<TitleListItemResponse> list) {
        List<ListCardModel> mapResult = new LinkedList<>();

        for (TitleListItemResponse i : list)
            mapResult.add(new ListCardModel(getActivity(), i));

        return mapResult;
    }
}
