package com.amasaemi.javashikiapp.modules.base.mvp.views;

import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

/**
 * Created by Alex on 01.02.2018.
 */
public interface ShikiListView<T> extends ShikiView {
    /**
     * Метод наполняет адаптер RecyclerView данными
     */
    @StateStrategyType(AddToEndStrategy.class)
    void fillAdapter(List<T> list);

    /**
     * Метод очищает адаптер RecyclerView от данных
     */
    @StateStrategyType(SkipStrategy.class)
    void clearAdapter();
}
