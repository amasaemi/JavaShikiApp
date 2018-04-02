package com.amasaemi.javashikiapp.modules.list.mvp.presenters;

import com.amasaemi.javashikiapp.data.network.pojo.req.SearchingParams;
import com.amasaemi.javashikiapp.data.network.pojo.res.TitleListItemResponse;
import com.amasaemi.javashikiapp.data.services.MangaService;
import com.amasaemi.javashikiapp.modules.base.mvp.presenters.BaseListPresenter;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Alex on 01.02.2018.
 */
@InjectViewState
public final class MangaListPresenter extends BaseListPresenter<TitleListItemResponse> implements ShikiTitleListPresenter {
    // сервис
    private MangaService mService = new MangaService();
    // параметры поиска
    private SearchingParams mParams = new SearchingParams();

    @Override
    public void loadOrRestoreData() {
        if (!mList.isEmpty())
            getViewState().fillAdapter(mList);
        else
            getMangaList();
    }

    @Override
    public void findTitlesByParams(SearchingParams params) {
        mCurrentPage = 1;
        if (params != null) mParams = params;

        clearAdapter();
        getMangaList();
    }

    private void getMangaList() {
        getViewState().stateLoadIndicator(true);
        // загружаем список аниме
        mService.getMangaList(mCurrentPage, mParams, this::fillAdapter, this::parseError);
    }

    @Override
    public void loadNextPage() {
        setNextPage();
        getMangaList();
    }
}
