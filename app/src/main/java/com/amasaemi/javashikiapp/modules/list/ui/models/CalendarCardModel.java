package com.amasaemi.javashikiapp.modules.list.ui.models;

import android.content.Context;

import com.amasaemi.javashikiapp.R;
import com.amasaemi.javashikiapp.data.managers.ActivityManager;
import com.amasaemi.javashikiapp.data.network.pojo.constants.TitleType;
import com.amasaemi.javashikiapp.data.network.pojo.res.CalendarResponse;
import com.amasaemi.javashikiapp.modules.base.adapters.DoubleRecyclerAdapter;
import com.amasaemi.javashikiapp.modules.base.ui.models.SimpleShortCardModel;
import com.amasaemi.javashikiapp.utils.DateParser;

/**
 * Created by Alex on 02.02.2018.
 */

public class CalendarCardModel extends SimpleShortCardModel implements DoubleRecyclerAdapter.ItemModel {
    public String basicInfo;
    public String airedOn;
    public String nextEpisode;

    public CalendarCardModel(Context context, CalendarResponse model) {
        id = model.getAnime().getId();

        cardClick = () -> ActivityManager.startTitleInfoActivity(context, model.getAnime().getId(), TitleType.ANIME);
        ruName = model.getAnime().getRuName();
        enName = model.getAnime().getEnName();
        poster = model.getAnime().getPoster();
        basicInfo = String.format("%s  •  %s", model.getAnime().getKind().toLocalString(context),
                                                model.getAnime().getStatus().toLocalString(context));
        airedOn = String.format("•  %s  •", context.getString(R.string.field_anime_aired_on,
                DateParser.parse("dd MMMM yyyy", model.getAnime().getAiredOn(), context.getString(R.string.status_unknown))));
        nextEpisode = String.format("%s  •  %s", context.getString(R.string.field_next_episode, model.getNextEpisode()),
                context.getString(R.string.field_airtime, DateParser.parse("HH:mm", model.getNextEpisodeAt(),
                        context.getString(R.string.status_unknown))));
    }
}
