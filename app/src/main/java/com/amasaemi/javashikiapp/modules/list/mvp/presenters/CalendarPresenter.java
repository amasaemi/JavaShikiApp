package com.amasaemi.javashikiapp.modules.list.mvp.presenters;

import com.amasaemi.javashikiapp.data.network.pojo.res.CalendarResponse;
import com.amasaemi.javashikiapp.data.services.AnimeService;
import com.amasaemi.javashikiapp.modules.base.mvp.presenters.BaseListPresenter;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Alex on 01.02.2018.
 */
@InjectViewState
public class CalendarPresenter extends BaseListPresenter<CalendarResponse> {
    private AnimeService mService = new AnimeService();

    public CalendarPresenter() {
        mPresenterIsReady = true;
    }

    @Override
    public void loadOrRestoreData() {
        if (mPresenterIsReady) {
            if (!mList.isEmpty())
                getViewState().fillAdapter(mList);
            else
                getCalendar();
        } else {
            throw new NullPointerException(String.format("%s presenter is not ready", this.getClass().getSimpleName()));
        }
    }

    private void getCalendar() {
        getViewState().stateLoadIndicator(true);
        // загружаем календарь
        mService.getCalendar(this::fillAdapter, this::parseError);
    }
}
