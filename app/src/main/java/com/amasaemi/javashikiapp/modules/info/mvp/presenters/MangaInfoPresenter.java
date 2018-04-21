package com.amasaemi.javashikiapp.modules.info.mvp.presenters;

import com.amasaemi.javashikiapp.data.network.pojo.res.TitleInfoResponse;
import com.amasaemi.javashikiapp.data.services.MangaService;
import com.amasaemi.javashikiapp.utils.ErrorReport;
import com.arellomobile.mvp.InjectViewState;

@InjectViewState
public final class MangaInfoPresenter extends BaseInfoPresenter {
    // сервис
    private MangaService mMangaService = new MangaService();

    @Override
    public void loadOrRestoreData() {
        if (mTitleInfo != null) {
            getViewState().setExternalLinksMenu(mTitleExternalLinks);
            getViewState().setScreenshotsMenu(mTitleScreens);
            getViewState().setTitleInfo(mTitleInfo);
        } else if (mTitleId != -1) {
            getManga();
        } else {
            throw new NullPointerException("Presenter has not initialize");
        }
    }

    @Override
    public void getTitleById(int titleId) {
        mTitleId = titleId;
        loadOrRestoreData();
    }

    private void getManga() {
        getViewState().stateLoadIndicator(true);

        mMangaService.getSelectedManga(mTitleId, (response) -> {
            // сохраняем полученный результат
            mTitleInfo = response;
            // запрашиваем все остальные данные о тайтле
            getExternalLinks();
            // добавляем постер манги
            mTitleScreens.clear();
            mTitleScreens.add(new TitleInfoResponse.ScreenhostResponse(mTitleInfo.getOriginalPoster(), mTitleInfo.getPreviewPoster()));
            getViewState().setScreenshotsMenu(mTitleScreens);
            // выводим информацию на экран
            getViewState().setTitleInfo(mTitleInfo);
        }, (throwable) -> {
            // выключаем индикатор загрузки
            getViewState().stateLoadIndicator(false);
            // TODO: 01.04.2018 вызвать диалог с ошибкой
        });
    }

    private void getExternalLinks() {
        mMangaService.getExternalLinks(mTitleId, (response) -> {
            mTitleExternalLinks.clear();
            mTitleExternalLinks.addAll(response);
            getViewState().setExternalLinksMenu(mTitleExternalLinks);
        }, ErrorReport::sendReport);
    }
/*
    private void getRelated() {
        mMangaService.getRelated(mTitleId, (response) -> {
            mTitleRelated.clear();
            mTitleRelated.addAll(response);
            getViewState().setRelatedMenu(mTitleRelated);
        }, ErrorReport::sendReport);
    }

    private void getSimilar() {
        mMangaService.getSimilar(mTitleId, (response) -> {
            mTitleSimilar.clear();
            mTitleSimilar.addAll(response);
            getViewState().setSimilarMenu(mTitleSimilar);
        }, ErrorReport::sendReport);
    }
    */
}
