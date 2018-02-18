package com.amasaemi.javashikiapp.modules.list.ui.models;

import android.app.Activity;
import android.content.Context;
import android.databinding.Bindable;
import android.net.Uri;

import com.amasaemi.javashikiapp.R;
import com.amasaemi.javashikiapp.data.managers.ActivityManager;
import com.amasaemi.javashikiapp.data.network.pojo.constants.TitleType;
import com.amasaemi.javashikiapp.data.network.pojo.res.CalendarResponse;
import com.amasaemi.javashikiapp.modules.base.adapters.DoubleRecyclerAdapter;
import com.amasaemi.javashikiapp.modules.base.ui.models.SimpleShortCardModel;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Alex on 02.02.2018.
 */

public class CalendarCardModel extends SimpleShortCardModel implements DoubleRecyclerAdapter.ItemModel {
    public Runnable menuClick;
    public String basicInfo;
    public String airedOn;
    public String nextEpisode;

    public CalendarCardModel(Context context, CalendarResponse model) {
        id = model.getAnime().getId();

        cardClick = () -> ActivityManager.startTitleInfoActivity(context, model.getAnime().getId(), TitleType.ANIME);
        ruName = model.getAnime().getRuName();
        enName = model.getAnime().getEnName();
        poster = model.getAnime().getPreviewPoster(); // TODO: 02.02.2018 сделать постер в зависимости от настроек качества
        basicInfo = String.format("%s  •  %s", model.getAnime().getKind().toLocalString(context),
                                                model.getAnime().getStatus().toLocalString(context));
        airedOn = String.format("•  %s%s  •", context.getString(R.string.field_aired_on),
                                        new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
                                            .format(model.getAnime().getAiredOn()));
        nextEpisode = String.format("%s%d  •  %s%s", context.getString(R.string.field_next_episode),
                                                        model.getNextEpisode(),
                                                        context.getString(R.string.field_airtime),
                                                        new SimpleDateFormat("HH:mm", Locale.getDefault())
                                                            .format(model.getNextEpisodeAt()));
    }
}
