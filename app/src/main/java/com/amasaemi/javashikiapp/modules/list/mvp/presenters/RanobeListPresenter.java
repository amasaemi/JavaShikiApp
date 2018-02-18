package com.amasaemi.javashikiapp.modules.list.mvp.presenters;

import com.amasaemi.javashikiapp.data.network.pojo.req.SearchingParams;
import com.amasaemi.javashikiapp.data.network.pojo.res.TitleListItemResponse;
import com.amasaemi.javashikiapp.data.services.RanobeService;
import com.amasaemi.javashikiapp.modules.base.mvp.presenters.BaseListPresenter;
import com.amasaemi.javashikiapp.modules.base.mvp.presenters.ShikiTitleListPresenter;

/**
 * Created by Alex on 01.02.2018.
 */

public class RanobeListPresenter extends BaseListPresenter<TitleListItemResponse> implements ShikiTitleListPresenter {
    // сервис
    private RanobeService mService = new RanobeService();
    // параметры поиска
    private SearchingParams mParams = new SearchingParams();

    public RanobeListPresenter() {
        mPresenterIsReady = true;
    }

    @Override
    public void loadOrRestoreData() {
        if (mPresenterIsReady) {
            if (!mList.isEmpty())
                getViewState().fillAdapter(mList);
            else
                getRanobeList();
        } else {
            throw new NullPointerException(String.format("%s presenter is not ready", this.getClass().getSimpleName()));
        }
    }

    @Override
    public void findTitlesByParams(SearchingParams params) {
        mCurrentPage = 0;
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
