package com.amasaemi.javashikiapp.modules.list.mvp.presenters;

import com.amasaemi.javashikiapp.data.network.pojo.res.CalendarResponse;
import com.amasaemi.javashikiapp.data.services.AnimeService;
import com.amasaemi.javashikiapp.modules.base.mvp.presenters.BaseListPresenter;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Alex on 01.02.2018.
 */
@InjectViewState
public final class CalendarPresenter extends BaseListPresenter<CalendarResponse> {
    private AnimeService mService = new AnimeService();

    @Override
    public void loadOrRestoreData() {
        if (!mList.isEmpty())
            getViewState().fillAdapter(mList);
        else
            getCalendar();
    }

    private void getCalendar() {
        getViewState().stateLoadIndicator(true);
        // загружаем календарь
        mService.getCalendar(this::fillAdapter, this::parseError);
    }
}
