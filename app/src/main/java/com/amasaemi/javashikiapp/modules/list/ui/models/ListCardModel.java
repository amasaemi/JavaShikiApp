package com.amasaemi.javashikiapp.modules.list.ui.models;

import android.content.Context;

import com.amasaemi.javashikiapp.R;
import com.amasaemi.javashikiapp.data.managers.ActivityManager;
import com.amasaemi.javashikiapp.data.network.pojo.constants.TitleType;
import com.amasaemi.javashikiapp.data.network.pojo.res.TitleListItemResponse;
import com.amasaemi.javashikiapp.modules.base.adapters.interfaces.ViewModel;
import com.amasaemi.javashikiapp.modules.base.ui.models.SimpleShortCardModel;
import com.amasaemi.javashikiapp.utils.DateParser;

/**
 * Created by Alex on 18.02.2018.
 */

public class ListCardModel extends SimpleShortCardModel implements ViewModel {
    public String basicInfo;
    public String airedOn;
    public String episodes;

    public ListCardModel(Context context, TitleListItemResponse model) {
        id = model.getId();

        cardClick = () -> ActivityManager.startTitleInfoActivity(context, model.getId(), model.getTitleType());
        ruName = model.getRuName();
        enName = model.getEnName();
        poster = model.getPoster();
        basicInfo = String.format("%s  •  %s", model.getKind().toLocalString(context),
                model.getStatus().toLocalString(context));

        if (model.getTitleType() == TitleType.ANIME) {
            airedOn = String.format("•  %s  •", context.getString(R.string.field_anime_aired_on,
                    DateParser.parse("dd MMMM yyyy", model.getAiredOn(), context.getString(R.string.status_unknown))));
        } else {
            airedOn = String.format("•  %s  •", context.getString(R.string.field_manga_aired_on,
                    DateParser.parse("dd MMMM yyyy", model.getAiredOn(), context.getString(R.string.status_unknown))));
        }

        if (model.getTitleType() == TitleType.MANGA || model.getTitleType() == TitleType.RANOBE) {
            episodes = context.getString(R.string.field_chapters,
                    (model.getEpisodes()[0] == 0) ? "?" : model.getEpisodes()[0]);
        } else if (model.isOngoing()) {
            episodes = context.getString(R.string.field_episodes_ongoing,
                    (model.getEpisodes()[1] == 0) ? "?" : model.getEpisodes()[1],
                    (model.getEpisodes()[0] == 0) ? "?" : model.getEpisodes()[0]);
        } else {
            episodes = context.getString(R.string.field_episodes,
                    (model.getEpisodes()[0] == 0) ? "?" : model.getEpisodes()[0]);
        }
    }
}
