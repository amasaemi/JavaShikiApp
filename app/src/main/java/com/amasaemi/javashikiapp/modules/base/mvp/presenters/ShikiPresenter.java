package com.amasaemi.javashikiapp.modules.base.mvp.presenters;

import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Alex on 31.01.2018.
 */

public interface ShikiPresenter {
    /**
     * Метод обновляет данные адаптера (если список не пустой - заполняет адаптер данными из списка
     * иначе загружает данные из интернета)
     */
    void loadOrRestoreData();
}
