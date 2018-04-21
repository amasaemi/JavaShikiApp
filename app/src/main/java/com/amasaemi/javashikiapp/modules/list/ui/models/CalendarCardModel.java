package com.amasaemi.javashikiapp.modules.list.ui.models;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;

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

public final class CalendarCardModel extends SimpleShortCardModel implements DoubleRecyclerAdapter.ItemModel {
    private final String AIRED_ON_DATE_PATTERN = "dd MMMM yyyy";
    private final String AIRTIME_DATE_PATTERN = "HH:mm";

    public Spanned basicInfo;
    public String airedOn;
    public String nextEpisode;

    public CalendarCardModel(Context context, CalendarResponse model) {
        super(model, () -> ActivityManager.startTitleInfoActivity(context, model.getAnime().getId(), TitleType.ANIME), null);

        basicInfo = Html.fromHtml(context.getString(R.string.combine_field_base_info,
                model.getAnime().getKind().toLocalString(context),
                model.getAnime().getStatus().toLocalString(context),
                model.getAnime().getStatus().getStatusColor()));
        airedOn = context.getString(R.string.combine_field_anime_airing_on,
                DateParser.parse(AIRED_ON_DATE_PATTERN, model.getAnime().getAiredOn(), context.getString(R.string.status_unknown)));
        nextEpisode = context.getString(R.string.combine_field_next_episode, model.getNextEpisode(),
                DateParser.parse(AIRTIME_DATE_PATTERN, model.getNextEpisodeAt(), context.getString(R.string.status_unknown)));
    }
}
