package com.amasaemi.javashikiapp.modules.info.mvp.presenters;

import com.amasaemi.javashikiapp.data.network.pojo.constants.TitleType;
import com.amasaemi.javashikiapp.data.network.pojo.res.TitleInfoResponse;
import com.amasaemi.javashikiapp.data.services.AnimeService;
import com.amasaemi.javashikiapp.data.services.MangaService;
import com.amasaemi.javashikiapp.modules.base.mvp.presenters.BaseListPresenter;
import com.amasaemi.javashikiapp.utils.ErrorReport;
import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;
import java.util.List;

@InjectViewState
public final class RelatedInfoPresenter extends BaseListPresenter<TitleInfoResponse.RelatedResponse> {
    // id текущего тайтла
    protected int mTitleId = -1;
    // titleType текущего тайтла
    private TitleType mTitleType = TitleType.NONE;

    public final void initPresenter(int titleId, TitleType titleType) {
        mTitleId = titleId;
        mTitleType = titleType;
    }

    @Override
    public final void loadOrRestoreData() {
        if (!mList.isEmpty()) {
            getViewState().fillAdapter(mList);
        } else if (mTitleId != -1 && mTitleType != TitleType.NONE) {
            switch (mTitleType) {
                case ANIME:
                    getAnimeList();
                    break;
                case MANGA:
                case RANOBE:
                    getMangaList();
                    break;
            }
        } else {
            throw new NullPointerException("Presenter has not initialize");
        }
    }

    private void getAnimeList() {
        AnimeService animeService = new AnimeService();

        animeService.getRelated(mTitleId, (response) -> {
            mList.clear();
            fillAdapter(response);
        }, ErrorReport::sendReport);
    }

    private void getMangaList() {
        MangaService mangaService = new MangaService();

        mangaService.getRelated(mTitleId, (response) -> {
            mList.clear();
            fillAdapter(response);
        }, ErrorReport::sendReport);
    }
}
