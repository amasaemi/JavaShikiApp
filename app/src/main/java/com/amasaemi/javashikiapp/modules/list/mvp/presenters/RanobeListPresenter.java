package com.amasaemi.javashikiapp.modules.list.mvp.presenters;

import com.amasaemi.javashikiapp.data.network.pojo.req.SearchingParams;
import com.amasaemi.javashikiapp.data.network.pojo.res.TitleListItemResponse;
import com.amasaemi.javashikiapp.data.services.RanobeService;
import com.amasaemi.javashikiapp.modules.base.mvp.presenters.BaseListPresenter;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Alex on 01.02.2018.
 */
@InjectViewState
public final class RanobeListPresenter extends BaseListPresenter<TitleListItemResponse> implements ShikiTitleListPresenter {
    // сервис
    private RanobeService mService = new RanobeService();
    // параметры поиска
    private SearchingParams mParams = new SearchingParams();

    @Override
    public void loadOrRestoreData() {
        if (!mList.isEmpty())
            getViewState().fillAdapter(mList);
        else
            getRanobeList();
    }

    @Override
    public void findTitlesByParams(SearchingParams params) {
        mCurrentPage = 1;
        if (params != null) mParams = params;

        clearAdapter();
        getRanobeList();
    }

    private void getRanobeList() {
        getViewState().stateLoadIndicator(true);
        // загружаем список аниме
        mService.getRanobeList(mCurrentPage, mParams, this::fillAdapter, this::parseError);
    }

    @Override
    public void loadNextPage() {
        setNextPage();
        getRanobeList();
    }
}
