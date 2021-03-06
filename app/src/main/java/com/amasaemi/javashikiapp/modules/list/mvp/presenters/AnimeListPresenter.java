package com.amasaemi.javashikiapp.modules.list.mvp.presenters;

import com.amasaemi.javashikiapp.data.network.pojo.req.SearchingParams;
import com.amasaemi.javashikiapp.data.network.pojo.res.TitleListItemResponse;
import com.amasaemi.javashikiapp.data.services.AnimeService;
import com.amasaemi.javashikiapp.modules.base.mvp.presenters.BaseListPresenter;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Alex on 01.02.2018.
 */
@InjectViewState
public final class AnimeListPresenter extends BaseListPresenter<TitleListItemResponse> implements ShikiTitleListPresenter {
    // сервис
    private AnimeService mService = new AnimeService();
    // параметры поиска
    private SearchingParams mParams = new SearchingParams();

    @Override
    public void loadOrRestoreData() {
        if (!mList.isEmpty())
            getViewState().fillAdapter(mList);
        else
            getAnimeList();
    }

    @Override
    public void findTitlesByParams(SearchingParams params) {
        mCurrentPage = 1;
        if (params != null) mParams = params;

        clearAdapter();
        getAnimeList();
    }

    private void getAnimeList() {
        getViewState().stateLoadIndicator(true);
        // загружаем список аниме
        mService.getAnimeList(mCurrentPage, mParams, this::fillAdapter, this::parseError);
    }

    @Override
    public void loadNextPage() {
        setNextPage();
        getAnimeList();
    }
}
