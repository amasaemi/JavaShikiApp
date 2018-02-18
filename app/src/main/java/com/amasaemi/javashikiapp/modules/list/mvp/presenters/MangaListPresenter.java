package com.amasaemi.javashikiapp.modules.list.mvp.presenters;

import com.amasaemi.javashikiapp.data.network.pojo.req.SearchingParams;
import com.amasaemi.javashikiapp.data.network.pojo.res.TitleListItemResponse;
import com.amasaemi.javashikiapp.data.services.MangaService;
import com.amasaemi.javashikiapp.modules.base.mvp.presenters.BaseListPresenter;
import com.amasaemi.javashikiapp.modules.base.mvp.presenters.ShikiTitleListPresenter;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Alex on 01.02.2018.
 */
@InjectViewState
public class MangaListPresenter extends BaseListPresenter<TitleListItemResponse> implements ShikiTitleListPresenter {
    // сервис
    private MangaService mService = new MangaService();
    // параметры поиска
    private SearchingParams mParams = new SearchingParams();

    public MangaListPresenter() {
        mPresenterIsReady = true;
    }

    @Override
    public void loadOrRestoreData() {
        if (mPresenterIsReady) {
            if (!mList.isEmpty())
                getViewState().fillAdapter(mList);
            else
                getMangaList();
        } else {
            throw new NullPointerException(String.format("%s presenter is not ready", this.getClass().getSimpleName()));
        }
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
