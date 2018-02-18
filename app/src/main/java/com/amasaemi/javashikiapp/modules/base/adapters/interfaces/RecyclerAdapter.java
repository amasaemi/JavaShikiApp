package com.amasaemi.javashikiapp.modules.base.adapters.interfaces;

import java.util.List;

/**
 * Created by Alex on 31.01.2018.
 */

public interface RecyclerAdapter<T extends ViewModel> {
    /**
     * Метод возвращает элементы текущего адаптера
     * @return
     */
    List<T> getItems();

    /**
     * Метод очищает текущий список и добавляет в него элементы
     * @param items - добавленные элементы
     */
    void setItems(List<T> items);

    /**
     * Метод добавляет новые элементы к текущим
     * @param items - добавленные элементы
     */
    void addItems(List<T> items);

    /**
     * Метод очищает адаптер
     */
    void clearItems();

    /**
     * Метод возвращает true, если адаптер пуст
     * @return
     */
    public boolean isEmpty();
}
