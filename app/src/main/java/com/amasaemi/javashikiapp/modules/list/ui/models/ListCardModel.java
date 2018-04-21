package com.amasaemi.javashikiapp.modules.list.ui.models;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;

import com.amasaemi.javashikiapp.R;
import com.amasaemi.javashikiapp.data.managers.ActivityManager;
import com.amasaemi.javashikiapp.data.network.pojo.constants.TitleType;
import com.amasaemi.javashikiapp.data.network.pojo.res.TitleInfoResponse;
import com.amasaemi.javashikiapp.data.network.pojo.res.TitleListItemResponse;
import com.amasaemi.javashikiapp.modules.base.adapters.interfaces.ViewModel;
import com.amasaemi.javashikiapp.modules.base.ui.models.SimpleShortCardModel;
import com.amasaemi.javashikiapp.utils.DateParser;

/**
 * Created by Alex on 18.02.2018.
 */

public final class ListCardModel extends SimpleShortCardModel implements ViewModel {
    private final String AIRED_ON_DATE_PATTERN = "dd MMMM yyyy";

    public Spanned basicInfo;
    public String airedOn;
    public String episodes;

    public ListCardModel(Context context, TitleListItemResponse model) {
        super(model, () -> ActivityManager.startTitleInfoActivity(context, model.getId(), model.getTitleType()), null);

        basicInfo = Html.fromHtml(context.getString(R.string.combine_field_base_info,
                model.getKind().toLocalString(context),
                model.getStatus().toLocalString(context),
                model.getStatus().getStatusColor()));

        if (model.getTitleType() == TitleType.ANIME) {
            airedOn = context.getString(R.string.combine_field_anime_airing_on,
                    DateParser.parse(AIRED_ON_DATE_PATTERN, model.getAiredOn(), context.getString(R.string.status_unknown)));
        } else {
            airedOn = context.getString(R.string.combine_field_manga_airing_on,
                    DateParser.parse(AIRED_ON_DATE_PATTERN, model.getAiredOn(), context.getString(R.string.status_unknown)));
        }

        if (model.getTitleType() == TitleType.MANGA || model.getTitleType() == TitleType.RANOBE) {
            episodes = context.getString(R.string.combine_field_chapters,
                    (model.getEpisodes()[0] == 0) ? "?" : model.getEpisodes()[0]);
        } else if (model.isOngoing()) {
            episodes = context.getString(R.string.combine_field_episodes_ongoing,
                    (model.getEpisodes()[1] == 0) ? "?" : model.getEpisodes()[1],
                    (model.getEpisodes()[0] == 0) ? "?" : model.getEpisodes()[0]);
        } else {
            episodes = context.getString(R.string.combine_field_episodes,
                    (model.getEpisodes()[0] == 0) ? "?" : model.getEpisodes()[0]);
        }
    }

    // TODO: 05.04.2018 заменить на relatedCardModel
    public ListCardModel(Context context, TitleInfoResponse.RelatedResponse model) {
        super(model, () -> ActivityManager.startTitleInfoActivity(context, model.getTitle().getId(), model.getTitleType()), null);

        basicInfo = Html.fromHtml(context.getString(R.string.combine_field_base_info,
                model.getTitle().getKind().toLocalString(context),
                model.getTitle().getStatus().toLocalString(context),
                model.getTitle().getStatus().getStatusColor()));

        if (model.getTitleType() == TitleType.ANIME) {
            airedOn = context.getString(R.string.combine_field_anime_airing_on,
                    DateParser.parse(AIRED_ON_DATE_PATTERN, model.getTitle().getAiredOn(), context.getString(R.string.status_unknown)));
        } else {
            airedOn = context.getString(R.string.combine_field_manga_airing_on,
                    DateParser.parse(AIRED_ON_DATE_PATTERN, model.getTitle().getAiredOn(), context.getString(R.string.status_unknown)));
        }

        if (model.getTitleType() == TitleType.MANGA || model.getTitleType() == TitleType.RANOBE) {
            episodes = context.getString(R.string.combine_field_chapters,
                    (model.getTitle().getEpisodes()[0] == 0) ? "?" : model.getTitle().getEpisodes()[0]);
        } else if (model.getTitle().isOngoing()) {
            episodes = context.getString(R.string.combine_field_episodes_ongoing,
                    (model.getTitle().getEpisodes()[1] == 0) ? "?" : model.getTitle().getEpisodes()[1],
                    (model.getTitle().getEpisodes()[0] == 0) ? "?" : model.getTitle().getEpisodes()[0]);
        } else {
            episodes = context.getString(R.string.combine_field_episodes,
                    (model.getTitle().getEpisodes()[0] == 0) ? "?" : model.getTitle().getEpisodes()[0]);
        }
    }
}
