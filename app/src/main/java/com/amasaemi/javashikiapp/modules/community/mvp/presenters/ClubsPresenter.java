package com.amasaemi.javashikiapp.modules.community.mvp.presenters;

import com.amasaemi.javashikiapp.data.network.pojo.res.ClubsListResponse;
import com.amasaemi.javashikiapp.data.services.ClubService;
import com.amasaemi.javashikiapp.modules.base.mvp.presenters.BaseListPresenter;
import com.arellomobile.mvp.InjectViewState;

import java.util.List;

/**
 * Created by Alex on 25.02.2018.
 */
@InjectViewState
public class ClubsPresenter extends BaseListPresenter<ClubsListResponse> {
    // сервис
    private ClubService mService = new ClubService();
    // поисковой запрос
    private String mQuery;

    @Override
    public void loadOrRestoreData() {
        if (!mList.isEmpty())
            getViewState().fillAdapter(mList);
        else
            getClubsList();
    }

    public void getClubsList(String query) {
        mCurrentPage = 1;
        mQuery = query;

        clearAdapter();
        getClubsList();
    }

    private void getClubsList() {
        getViewState().stateLoadIndicator(true);
        // загружаем список аниме
        mService.getClubsList(mCurrentPage, mQuery, this::fillAdapter, this::parseError);
    }

    public void loadNextPage() {
        mCurrentPage++;
        getClubsList();
    }

    @Override
    protected void fillAdapter(List<ClubsListResponse> list) {
        // пагинация списка
        // если список не пуст, то возвращает все элементы без последнего
        // иначе возвращаем список от 0 до 0, и обрабатываем пустой список
        super.fillAdapter(list.subList(0, (!list.isEmpty()) ? list.size() - 1 : list.size()));
    }
}
