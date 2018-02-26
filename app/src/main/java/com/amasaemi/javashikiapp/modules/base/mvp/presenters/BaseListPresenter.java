package com.amasaemi.javashikiapp.modules.base.mvp.presenters;

import com.amasaemi.javashikiapp.modules.base.mvp.views.ShikiListView;
import com.amasaemi.javashikiapp.utils.ErrorReport;
import com.arellomobile.mvp.MvpPresenter;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alex on 01.02.2018.
 */
public abstract class BaseListPresenter<T> extends MvpPresenter<ShikiListView<T>> implements ShikiPresenter {
    // маркер готовности презентера
    protected boolean mPresenterIsReady = false;
    // текущая страница
    protected int mCurrentPage = 1;
    // список текущих элементов
    protected final List<T> mList = new LinkedList<T>();

    @Override
    public boolean hasInitialized() {
        return mPresenterIsReady;
    }

    /**
     * Метод устанавливает и возвращает следующую по счету страницу
     * @return
     */
    protected final void setNextPage() {
        ++mCurrentPage;
    }

    /**
     * Метод заполняет адаптер
     * @param list
     */
    protected void fillAdapter(List<T> list) {
        if (!list.isEmpty()) {
            mList.addAll(list);
            getViewState().fillAdapter(list);
        }

        getViewState().stateVisibilityContainer(!mList.isEmpty());
    }

    /**
     * Метод очищает адаптер
     */
    protected final void clearAdapter() {
        mList.clear();
        getViewState().clearAdapter();
    }

    protected void parseError(Throwable t) {
        getViewState().stateLoadIndicator(false);
        // TODO: 01.02.2018 пропарсить ошибку, и в зависимости от ошибки вывести snackBar или диалог
        ErrorReport.parseError(t);
    }
}
