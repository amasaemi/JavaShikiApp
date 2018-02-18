package com.amasaemi.javashikiapp.data.network.pojo.res;

import android.support.annotation.NonNull;

import com.amasaemi.javashikiapp.utils.ErrorReport;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CalendarResponse {
    // формат даты выхода следующей серии
    // string -> date
    private final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    @SerializedName("next_episode")
    @Expose
    private int nextEpisode;

    @SerializedName("next_episode_at")
    @Expose
    private String nextEpisodeAt;

    @SerializedName("duration")
    @Expose
    private String duration;

    @SerializedName("anime")
    @Expose
    private TitleListItemResponse anime;

    /**
     * Метод вернет номер следующего эпизода
     * @return
     */
    public int getNextEpisode() {
        return nextEpisode;
    }

    /**
     * Метод вернет дату выхода следующего эпизода
     * @return
     */
    public Date getNextEpisodeAt() {
        try {
            return new SimpleDateFormat(DATE_PATTERN, Locale.getDefault()).parse(nextEpisodeAt);
        } catch (ParseException pe) {
            ErrorReport.sendReport(String.format("%s %s", this.getClass().getSimpleName(), pe.getMessage()));
            return null;
        }
    }

    /**
     * Метод вернет аниме контекста
     * @return
     */
    @NonNull
    public TitleListItemResponse getAnime() {
        return anime;
    }
}
