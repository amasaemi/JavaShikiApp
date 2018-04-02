package com.amasaemi.javashikiapp.modules.info.ui.models;

import android.content.Context;
import android.databinding.BaseObservable;
import android.net.Uri;
import android.text.Html;
import android.text.Spanned;

import com.amasaemi.javashikiapp.R;
import com.amasaemi.javashikiapp.data.network.pojo.constants.TitleType;
import com.amasaemi.javashikiapp.data.network.pojo.res.TitleInfoResponse;
import com.amasaemi.javashikiapp.modules.base.adapters.interfaces.ViewModel;
import com.amasaemi.javashikiapp.utils.DateParser;
import com.amasaemi.javashikiapp.utils.ErrorReport;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by Alex on 25.03.2018.
 */

public class TitleInfoModel extends BaseObservable implements ViewModel {
    private final String AIRTIME_PATTERN = "EEEE, HH:mm";
    private final String FULL_DATE_PATTERN = "dd MMM yyyy";

    private Uri mUrl;

    public int id;
    public String ruName;
    public String enName;
    public String jpName;
    public Uri poster;
    public boolean hasFavorite;
    public int[] episodes;
    public String nextEpisode;
    public String airtime;
    public String duration;
    public String kind;
    public Spanned status;
    public String season;
    public String airedStart;
    public String airedFinish;
    public String ageRating;
    public float rating;
    public String description;
    public List<TitleInfoResponse.Genre> genres;
    public String studios;
    public boolean hasAnimeOngoing;
    public int inUsersListCount;
    public int bestStarringCount;

    public boolean emptyDescription;

    public TitleType titleType;

    public TitleInfoModel(Context context, TitleInfoResponse response) {
        try {
            this.id = response.getId();
        } catch (Exception e) {
            ErrorReport.sendReport(e);
            this.id = -1;
        }

        try {
            this.ruName = response.getRuName();
        } catch (Exception e) {
            ErrorReport.sendReport(e);
            this.ruName = null;
        }

        try {
            this.enName = response.getEnName();
        } catch (Exception e) {
            ErrorReport.sendReport(e);
            this.enName = null;
        }

        try {
            this.jpName = response.getJpName();
        } catch (Exception e) {
            ErrorReport.sendReport(e);
            this.jpName = null;
        }

        try {
            this.poster = response.getOriginalPoster();
        } catch (Exception e) {
            ErrorReport.sendReport(e);
            this.poster = null;
        }

        try {
            this.hasFavorite = response.hasFavoured();
        } catch (Exception e) {
            ErrorReport.sendReport(e);
            this.hasFavorite = false;
        }

        try {
            this.episodes = response.getEpisodes();
        } catch (Exception e) {
            ErrorReport.sendReport(e);
            this.episodes = new int[] {0, 0};
        }

        try {
            this.nextEpisode = DateParser.parse(FULL_DATE_PATTERN, response.getNextEpisodeAt(), null);
        } catch (Exception e) {
            ErrorReport.sendReport(e);
            this.nextEpisode = null;
        }

        try {
            StringBuilder airtimeBuilder = new StringBuilder(DateParser.parse(AIRTIME_PATTERN, response.getNextEpisodeAt(), "-"));
            airtimeBuilder.replace(0, 1, String.valueOf(airtimeBuilder.charAt(0)).toUpperCase());
            this.airtime = airtimeBuilder.toString();
        } catch (Exception e) {
            ErrorReport.sendReport(e);
            this.airtime = null;
        }

        try {
            this.duration = String.format(context.getString(R.string.combine_field_duration), response.getDuration());
        } catch (Exception e) {
            ErrorReport.sendReport(e);
            this.duration = "?";
        }

        try {
            this.kind = response.getKind().toLocalString(context);
        } catch (Exception e) {
            ErrorReport.sendReport(e);
            this.kind = "?";
        }

        try {
            this.status = Html.fromHtml(String.format("<span style=\"color:%s\">%s</span>",
                    response.getStatus().getStatusColor(),
                    response.getStatus().toLocalString(context)));
        } catch (Exception e) {
            ErrorReport.sendReport(e);
            this.status = Html.fromHtml("?");
        }
        try {
            this.season = DateParser.parseSeason(response.getAiredOn(), context.getResources().getStringArray(R.array.age_time));
        } catch (Exception e) {
            ErrorReport.sendReport(e);
            this.season = "?";
        }
        try {
            this.airedStart = DateParser.parse(FULL_DATE_PATTERN, response.getAiredOn(), "?");
        } catch (Exception e) {
            ErrorReport.sendReport(e);
            this.airedStart = "?";
        }
        try {
            this.airedFinish = DateParser.parse(FULL_DATE_PATTERN, response.getReleasedOn(), "?");
        } catch (Exception e) {
            ErrorReport.sendReport(e);
            this.airedFinish = "?";
        }
        try {
            this.ageRating = response.getRating().toLocalString(context);
        } catch (Exception e) {
            ErrorReport.sendReport(e);
            this.ageRating = "?";
        }
        try {
            this.rating = response.getScore() / 2;
        } catch (Exception e) {
            ErrorReport.sendReport(e);
            this.rating = 0.0f;
        }
        try {
            this.description = response.getDescription();
        } catch (Exception e) {
            ErrorReport.sendReport(e);
            this.description = "-";
        }

        try {
            this.genres = response.getGenres();
        } catch (Exception e) {
            ErrorReport.sendReport(e);
            this.genres = null;
        }

        try {
            StringBuilder studios = new StringBuilder();

            for (TitleInfoResponse.Studio studio : response.getStudios()){
                studios.append(studio.getStudioName());
                studios.append(", ");
            }

            studios.deleteCharAt(studios.length() - 2);
            this.studios = studios.toString();
        } catch (Exception e) {
            ErrorReport.sendReport(e);
            this.studios = "?";
        }

        try {
            for (TitleInfoResponse.TitleRate rate : response.getRatesStatuses()) {
                this.inUsersListCount += rate.getValue();
            }
        } catch (Exception e) {
            ErrorReport.sendReport(e);
            this.inUsersListCount = 0;
        }

        try {
            for (TitleInfoResponse.TitleRate rate : response.getRatesScores().subList(0, 2)) {
                this.bestStarringCount += rate.getValue();
            }
        } catch (Exception e) {
            ErrorReport.sendReport(e);
            this.bestStarringCount = 0;
        }

        try {
            this.titleType = response.getTitleType();
        } catch (Exception e) {
            ErrorReport.sendReport(e);
            this.titleType = TitleType.NONE;
        }

        hasAnimeOngoing = titleType == TitleType.ANIME && response.isOngoing();

        emptyDescription = description == null;

        mUrl = response.getUrl();
    }

    public Uri getUrl() {
        return mUrl;
    }
}