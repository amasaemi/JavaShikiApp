package com.amasaemi.javashikiapp.modules.list.ui.models;

import android.databinding.BaseObservable;

import com.amasaemi.javashikiapp.data.network.pojo.res.CalendarResponse;
import com.amasaemi.javashikiapp.modules.base.adapters.DoubleRecyclerAdapter;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Alex on 02.02.2018.
 */

public class CalendarDelimiterModel extends BaseObservable implements DoubleRecyclerAdapter.HeaderModel {
    public String date;

    public CalendarDelimiterModel(CalendarResponse item) {
        date = new SimpleDateFormat("EEEE, dd MMMM", Locale.getDefault())
                .format(item.getNextEpisodeAt());
    }
}
