package com.amasaemi.javashikiapp.modules.info.mvp.views;

import com.amasaemi.javashikiapp.data.network.pojo.res.TitleInfoResponse;
import com.amasaemi.javashikiapp.data.network.pojo.res.TitleListItemResponse;
import com.amasaemi.javashikiapp.modules.base.mvp.views.ShikiView;

import java.util.List;

/**
 * Created by Alex on 25.03.2018.
 */

public interface ShikiInfoView extends ShikiView {
    /**
     * Метод настраивает основную информацию о тайтле
     */
    void setTitleInfo(TitleInfoResponse response);

    /**
     * Метод инициализирует меню с внешними ссылками
     */
    void setExternalLinksMenu(List<TitleInfoResponse.ExternalLinksResponse> response);

    /**
     * Метод инициализирует меню с видео
     */
    void setVideosMenu(List<TitleInfoResponse.VideoResponse> response);

    /**
     * Метод инициализирует меню со связанными тайтлами
     */
    void setRelatedMenu(List<TitleInfoResponse.RelatedResponse> response);

    /**
     * Метод инициализирует меню с похожими тайтлами
     */
    void setSimilarMenu(List<TitleListItemResponse> response);

    /**
     * Метод инициализирует меню со скриншотами
     */
    void setScreenshotsMenu(List<TitleInfoResponse.ScreenhostResponse> response);
}
