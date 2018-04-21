package com.amasaemi.javashikiapp.modules.info.mvp.presenters;

import com.amasaemi.javashikiapp.data.network.pojo.res.TitleInfoResponse;
import com.amasaemi.javashikiapp.data.services.AnimeService;
import com.amasaemi.javashikiapp.utils.ErrorReport;
import com.arellomobile.mvp.InjectViewState;

@InjectViewState
public final class AnimeInfoPresenter extends BaseInfoPresenter {
    // сервис
    private AnimeService mAnimeService = new AnimeService();

    @Override
    public void loadOrRestoreData() {
        if (mTitleInfo != null) {
            getViewState().setExternalLinksMenu(mTitleExternalLinks);
            getViewState().setScreenshotsMenu(mTitleScreens);
            getViewState().setTitleInfo(mTitleInfo);
        } else if (mTitleId != -1) {
            getAnime();
        } else {
            throw new NullPointerException("Presenter has not initialize");
        }
    }

    @Override
    public void getTitleById(int titleId) {
        mTitleId = titleId;
        loadOrRestoreData();
    }

    private void getAnime() {
        getViewState().stateLoadIndicator(true);

        mAnimeService.getSelectedAnime(mTitleId, (response) -> {
            // сохраняем полученный результат
            mTitleInfo = response;
            // запрашиваем все остальные данные о тайтле
            getExternalLinks();
            getScreens();
            // выводим информацию на экран
            getViewState().setTitleInfo(mTitleInfo);
        }, (throwable) -> {
            // выключаем индикатор загрузки
            getViewState().stateLoadIndicator(false);
            // TODO: 01.04.2018 вызвать диалог с ошибкой
        });
    }

    private void getExternalLinks() {
        mAnimeService.getExternalLinks(mTitleId, (response) -> {
            mTitleExternalLinks.clear();
            mTitleExternalLinks.addAll(response);
            getViewState().setExternalLinksMenu(mTitleExternalLinks);
        }, ErrorReport::sendReport);
    }

    private void getScreens() {
        mTitleScreens.clear();
        // добавляем перед всеми скриншотами постер тайтла
        mTitleScreens.add(new TitleInfoResponse.ScreenhostResponse(mTitleInfo.getOriginalPoster(), mTitleInfo.getPreviewPoster()));

        mAnimeService.getScreenhots(mTitleId, (response) -> {
            mTitleScreens.addAll(response);
            getViewState().setScreenshotsMenu(mTitleScreens);
        }, ErrorReport::sendReport);
    }
/*
    private void getRelated() {
        mAnimeService.getRelated(mTitleId, (response) -> {
            mTitleRelated.clear();
            mTitleRelated.addAll(response);
            getViewState().setRelatedMenu(mTitleRelated);
        }, ErrorReport::sendReport);
    }

    @Deprecated
    private void getSimilar() {
        mAnimeService.getSimilar(mTitleId, (response) -> {
            mTitleSimilar.clear();
            mTitleSimilar.addAll(response);
            getViewState().setSimilarMenu(mTitleSimilar);
        }, ErrorReport::sendReport);
    }

    private void getVideos() {
        mAnimeService.getVideos(mTitleId, (response) -> {
            mTitleVideos.clear();
            mTitleVideos.addAll(response);
            getViewState().setVideosMenu(mTitleVideos);
        }, ErrorReport::sendReport);
    }
    */
}
