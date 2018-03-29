package com.amasaemi.javashikiapp.modules.list.mvp.presenters;

import com.amasaemi.javashikiapp.data.network.pojo.req.SearchingParams;
import com.amasaemi.javashikiapp.modules.base.mvp.presenters.ShikiPresenter;

/**
 * Created by Alex on 01.02.2018.
 */

public interface ShikiTitleListPresenter extends ShikiPresenter {
    /**
     * Метод выполняет поиск тайтлов по поисковым параметрам
     * @param params - параметры поиска
     */
    void findTitlesByParams(SearchingParams params);

    /**
     * Метод загружает следующую страницу списка
     */
    void loadNextPage();
}
