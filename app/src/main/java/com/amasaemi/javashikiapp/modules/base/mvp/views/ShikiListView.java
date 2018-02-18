package com.amasaemi.javashikiapp.modules.base.mvp.views;

import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

/**
 * Created by Alex on 01.02.2018.
 */
@StateStrategyType(SkipStrategy.class)
public interface ShikiListView<T> extends ShikiView {
    /**
     * Метод наполняет адаптер RecyclerView данными
     */
    void fillAdapter(List<T> list);

    /**
     * Метод очищает адаптер RecyclerView от данных
     */
    void clearAdapter();
}
