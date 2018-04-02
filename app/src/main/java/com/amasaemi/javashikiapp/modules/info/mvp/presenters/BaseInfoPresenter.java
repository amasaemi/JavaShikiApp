package com.amasaemi.javashikiapp.modules.info.mvp.presenters;

import com.amasaemi.javashikiapp.data.network.pojo.constants.UserRate;
import com.amasaemi.javashikiapp.data.network.pojo.res.TitleInfoResponse;
import com.amasaemi.javashikiapp.data.network.pojo.res.TitleListItemResponse;
import com.amasaemi.javashikiapp.modules.info.mvp.views.ShikiInfoView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BaseInfoPresenter extends MvpPresenter<ShikiInfoView> implements ShikiTitleInfoPresenter {
    // id текущего тайтла
    protected int mTitleId = -1;
    // объект с информацией о тайтле
    protected TitleInfoResponse mTitleInfo;
    // список со скриншотами тайтла
    protected List<TitleInfoResponse.ScreenhostResponse> mTitleScreens = new ArrayList<>();
    // список с внешними ссылками тайтла
    protected List<TitleInfoResponse.ExternalLinksResponse> mTitleExternalLinks = new ArrayList<>();
    // список с похожими тайтлами
    protected List<TitleListItemResponse> mTitleSimilar = new ArrayList<>();
    // список со связанными тайтлами
    protected List<TitleInfoResponse.RelatedResponse> mTitleRelated = new ArrayList<>();
    // список с видео (op, ed, etc) тайтла
    protected List<TitleInfoResponse.VideoResponse> mTitleVideos = new ArrayList<>();

    @Override
    public void putUserRate(Map<UserRate, String> userRateQueryMap) {

    }

    @Override
    public void deleteUserRate() {

    }

    @Override
    public void changeFavoriteStatus(boolean status) {
        // TODO: 01.04.2018
    }
}
