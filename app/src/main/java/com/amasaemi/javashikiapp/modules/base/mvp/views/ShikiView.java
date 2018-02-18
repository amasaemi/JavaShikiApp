package com.amasaemi.javashikiapp.modules.base.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Alex on 31.01.2018.
 */
public interface ShikiView extends MvpView {
    /**
     * Метод управляет состоянием индикатора загрузки
     */
    void stateLoadIndicator(Boolean state);

    /**
     * Метод для вызова snackbar о необходимости перезагрузить страничку
     */
    void showRetrySnackbar(Runnable action);

    /**
     * Метод сменяет видимость корневого контейнера элементов
     */
    @StateStrategyType(SingleStateStrategy.class)
    void stateVisibilityContainer(Boolean state);

    /**
     * Метод для вызова alertdialog
     */
    @StateStrategyType(SingleStateStrategy.class)
    void showDialog(String title, String message);
}
