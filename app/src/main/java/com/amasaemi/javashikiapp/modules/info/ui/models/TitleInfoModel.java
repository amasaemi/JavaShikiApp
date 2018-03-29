package com.amasaemi.javashikiapp.modules.info.ui.models;

import android.content.Context;
import android.databinding.BaseObservable;
import android.net.Uri;

import com.amasaemi.javashikiapp.data.network.pojo.constants.TitleType;
import com.amasaemi.javashikiapp.data.network.pojo.res.TitleInfoResponse;
import com.amasaemi.javashikiapp.modules.base.adapters.interfaces.ViewModel;
import com.amasaemi.javashikiapp.utils.ErrorReport;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by Alex on 25.03.2018.
 */

public class TitleInfoModel extends BaseObservable implements ViewModel {
    private final String DATE_PATTERN = "MMM dd, yyyy";
    private final String AIRTIME_PATTERN = "HH:mm";

    public int id;
    public String ruName;
    public String enName;
    public String jpName;
    public Uri poster;
    public boolean hasFavorite;
    public int[] episodes;
    public String nextEpisode;
    public String airtime;
    public int duration;
    public String kind;
    public String status;
    public String startAired;
    public String finishAired;
    public String ageRating;
    public float rating;
    public String description;
    public List<TitleInfoResponse.Genre> genres;
    public String studios;

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
            this.nextEpisode = new SimpleDateFormat(DATE_PATTERN, Locale.getDefault()).format(response.getNextEpisodeAt());
        } catch (Exception e) {
            ErrorReport.sendReport(e);
            this.nextEpisode = null;
        }

        try {
            this.airtime = new SimpleDateFormat(AIRTIME_PATTERN, Locale.getDefault()).format(response.getNextEpisodeAt());
        } catch (Exception e) {
            ErrorReport.sendReport(e);
            this.airtime = null;
        }

        try {
            this.duration = response.getDuration();
        } catch (Exception e) {
            ErrorReport.sendReport(e);
            this.duration = 0;
        }

        try {
            this.kind = response.getKind().toLocalString(context);
        } catch (Exception e) {
            ErrorReport.sendReport(e);
            this.kind = "?";
        }

        try {
            this.status = response.getStatus().toLocalString(context);
        } catch (Exception e) {
            ErrorReport.sendReport(e);
            this.status = "?";
        }
        try {
            this.startAired = new SimpleDateFormat(DATE_PATTERN, Locale.getDefault()).format(response.getAiredOn());
        } catch (Exception e) {
            ErrorReport.sendReport(e);
            this.startAired = "?";
        }
        try {
            this.finishAired = new SimpleDateFormat(DATE_PATTERN, Locale.getDefault()).format(response.getReleasedOn());
        } catch (Exception e) {
            ErrorReport.sendReport(e);
            this.finishAired = "?";
        }
        try {
            this.ageRating = response.getRating().toLocalString(context);
        } catch (Exception e) {
            ErrorReport.sendReport(e);
            this.ageRating = "?";
        }
        try {
            this.rating = response.getScore();
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
            this.studios = response.getStudios().subList(0,1).toString();
        } catch (Exception e) {
            ErrorReport.sendReport(e);
            this.studios = "-";
        }

        try {
            this.titleType = response.getTitleType();
        } catch (Exception e) {
            ErrorReport.sendReport(e);
            this.titleType = TitleType.NONE;
        }

        emptyDescription = (description != null) && description.equals("Нет описания");
    }
}