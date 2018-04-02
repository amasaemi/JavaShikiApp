package com.amasaemi.javashikiapp.modules.info.mvp.presenters;

import com.amasaemi.javashikiapp.data.network.pojo.constants.UserRate;
import com.amasaemi.javashikiapp.modules.base.mvp.presenters.ShikiPresenter;

import java.util.Map;

public interface ShikiTitleInfoPresenter extends ShikiPresenter {
    /**
     * Метод загружает текущий тайтл
     * @param titleId id текущего тайтла
     */
    void getTitleById(int titleId);

    /**
     * Метод добавляет или обновляет пользовательский рейтинг на сервере
     * @param userRateQueryMap - карта полей user rate
     */
    void putUserRate(Map<UserRate, String> userRateQueryMap);

    /**
     * Метод удаляет пользовательский рейтинг с сервера
     */
    void deleteUserRate();

    /**
     * Метод добавляет / удаляет тайтл в избранное
     * @param status - true - добавить, false - удалить
     */
    void changeFavoriteStatus(boolean status);
}
