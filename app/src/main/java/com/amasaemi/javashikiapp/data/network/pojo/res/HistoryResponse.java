package com.amasaemi.javashikiapp.data.network.pojo.res;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;

import com.amasaemi.javashikiapp.data.network.pojo.constants.TitleType;
import com.amasaemi.javashikiapp.utils.ErrorReport;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Alex on 23.12.2017.
 */

public class HistoryResponse {
    // string -> date
    private final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    /**
     * GET /api/users/500033/history
     *
     * {
     * "id": Int, - id записи
     * "created_at": Date, - дата создания записи
     * "description": String, - описание записи
     * "target": AbstractListResponse? - обьект с тайтлом
     * }
     */

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("created_at")
    @Expose
    @NonNull
    private String dateCreateOf;

    @SerializedName("description")
    @Expose
    @NonNull
    private String eventDescription;

    @SerializedName("target")
    @Expose
    @Nullable
    private TitleListItemResponse target;

    @NonNull
    public Date getCreateAt() {
        try {
            return new SimpleDateFormat(DATE_PATTERN, Locale.getDefault()).parse(dateCreateOf);
        } catch (ParseException pe) {
            ErrorReport.sendReport(String.format("%s %s", this.getClass().getSimpleName(), pe.getMessage()));
            return null;
        }
    }

    @NonNull
    public String getEventDescription() {
        return Html.fromHtml(eventDescription).toString();
    }

    @Nullable
    public TitleListItemResponse getTarget() {
        return target;
    }

    @NonNull
    public int getId() {
        return target != null ? target.getId() : -1;
    }

    @Nullable
    public String getEnName() {
        return target != null ? target.getEnName() : null;
    }

    @Nullable
    public String getRuName() {
        return target != null ? target.getRuName() : null;
    }

    @Nullable
    public Uri getPoster() {
        return target != null ? target.getSmallPoster() : null;
    }

    @NonNull
    public TitleType getTitleType() {
        return target != null ? target.getTitleType() : TitleType.NONE;
    }
}
